/**
 * 
 */
package fr.projetAngularJPA.bibliotheque.loan;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.projetAngularJPA.bibliotheque.book.Book;
import fr.projetAngularJPA.bibliotheque.book.BookDTO;
import fr.projetAngularJPA.bibliotheque.customer.Customer;
import fr.projetAngularJPA.bibliotheque.customer.CustomerDTO;
import fr.projetAngularJPA.bibliotheque.category.Category;
import fr.projetAngularJPA.bibliotheque.category.CategoryDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author Thanh Chau
 *
 */
@RestController
@RequestMapping("/rest/loan/api")
@Api(value = "Loan Rest Controller: contains all operations for managing loans")
public class LoanRestController {
	@Autowired
	private ILoanService loanService;

	@PostMapping("/addLoan")
	@ApiOperation(value = "Add a new Loan of customer", response = Boolean.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created: the loan is successfully inserted"),
			@ApiResponse(code = 304, message = "Not Modified: the loan is unsuccessfully inserted"),
			@ApiResponse(code = 409, message = "Conflit: the loan already existed") })
	public ResponseEntity<Boolean> addNewLoan(@RequestBody LoanDTO loanDTORequest) {
		if (loanService.checkIfLoanExists(loanDTORequest)) {
			return new ResponseEntity<Boolean>(false, HttpStatus.CONFLICT);
		}

		Loan loanRequest = mapLoanDTOToLoan(loanDTORequest);
		Loan loan = loanService.addLoan(loanRequest);
		if (loan != null) {
			return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
		}

		return new ResponseEntity<Boolean>(false, HttpStatus.NOT_MODIFIED);
	}

	/**
	 * Transform loanDTO in loan.
	 * 
	 * @param loanDTORequest
	 */
	private Loan mapLoanDTOToLoan(LoanDTO loanDTORequest) {
		Book book = new Book(loanDTORequest.getBookId());
		Customer customer = new Customer(loanDTORequest.getCustomerId());
		LoanId loanId = new LoanId(book, customer);

		Loan loan = new Loan(loanId, loanDTORequest.getBeginDate(), loanDTORequest.getEndDate(), LoanStatus.OPENED);
		return loan;
	}

	@GetMapping("/searchLoansOfCustomer")
	@ApiOperation(value = "Search all open loans of customer by his email", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK: successfully research"),
			@ApiResponse(code = 204, message = "No Content: no result found") })
	public ResponseEntity<List<LoanDTO>> searchOpenLoansOfCustomerByMail(@RequestParam("email") String email) {
		List<Loan> loans = loanService.findAllOpenLoansOfCustomer(email);
		return createResponseFromLoansFound(loans);
	}

	/**
	 * Create ResponseEntity depending on loans found.
	 * 
	 * @param loans
	 * @return
	 */
	private ResponseEntity<List<LoanDTO>> createResponseFromLoansFound(List<Loan> loans) {
		if (!CollectionUtils.isEmpty(loans)) {
			// Remove all null elements of list.
			loans.removeAll(Collections.singleton(null));

			List<LoanDTO> loanDTOs = loans.stream().map(loan -> {
				return mapLoanToLoanDTO(loan);
			}).collect(Collectors.toList());

			return new ResponseEntity<List<LoanDTO>>(loanDTOs, HttpStatus.OK);
		}

		return new ResponseEntity<List<LoanDTO>>(HttpStatus.NO_CONTENT);
	}

	/**
	 * Transform loan in loanDTO.
	 * 
	 * @param loan
	 * @return
	 */
	private LoanDTO mapLoanToLoanDTO(Loan loan) {
		Book book = loan.getPk().getBook();
		Category category = book.getCategory();
		CategoryDTO categoryDTO = new CategoryDTO(category.getCode(), category.getLabel());
		;
		BookDTO bookDTO = new BookDTO(book.getBookId(), book.getIsbn(), book.getTitle(), book.getReleaseDate(),
				book.getRegisterDate(), book.getAuthor(), book.getTotalExemplaries(), categoryDTO);

		Customer customer = loan.getPk().getCustomer();
		CustomerDTO customerDTO = new CustomerDTO(customer.getCustomerId(), customer.getFirstName(),
				customer.getLastName(), customer.getJob(), customer.getAdresse(), customer.getEmail(),
				customer.getRegisterDate());

		LoanDTO loanDTO = new LoanDTO(loan.getBeginDate(), loan.getEndDate(), bookDTO, customerDTO);
		return loanDTO;
	}

	@GetMapping("/searchLoansBeforeEndDate")
	@ApiOperation(value = "Search all open loans that the end dates are before the maxEndDate", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK: successfully research"),
			@ApiResponse(code = 204, message = "No Content: no result found") })
	public ResponseEntity<List<LoanDTO>> searchLoansBeforeEndDate(@RequestParam("maxEndDate") LocalDate maxEndDate) {
		List<Loan> loans = loanService.findAllLoansBeforeEndDate(maxEndDate);
		return createResponseFromLoansFound(loans);
	}

	@PutMapping("/updateLoan")
	@ApiOperation(value = "Update loan", response = LoanDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK: the loan is successfully updated"),
			@ApiResponse(code = 304, message = "Not modified: the loan is unsuccessfully updated"),
			@ApiResponse(code = 404, message = "Not found: the loan does not exist") })
	public ResponseEntity<LoanDTO> updateLoan(@RequestBody LoanDTO loanDTORequest) {
		if (!loanService.checkIfLoanExists(loanDTORequest)) {
			return new ResponseEntity<LoanDTO>(HttpStatus.NOT_FOUND);
		}

		Loan loanRequest = mapLoanDTOToLoan(loanDTORequest);
		Loan loan = loanService.updateLoan(loanRequest);
		if (loan != null) {
			LoanDTO loanDTO = mapLoanToLoanDTO(loan);
			return new ResponseEntity<LoanDTO>(loanDTO, HttpStatus.OK);
		}

		return new ResponseEntity<LoanDTO>(HttpStatus.NOT_MODIFIED);
	}

	@DeleteMapping("/closeLoan")
	@ApiOperation(value = "Mark as closed a loan in the library", response = Boolean.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK: the loan is successfully closed"),
			@ApiResponse(code = 304, message = "Not modified: the loan is unsuccessfully closed"),
			@ApiResponse(code = 404, message = "Not found: the loan does not exist") })
	public ResponseEntity<Boolean> closeLoan(@RequestBody LoanDTO loanDTORequest) {
		Loan existingLoan = loanService.findLoanById(loanDTORequest);
		if (existingLoan == null) {
			return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
		}

		Loan loan = loanService.closeLoan(existingLoan);
		if (loan != null) {
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}

		return new ResponseEntity<Boolean>(HttpStatus.NOT_MODIFIED);
	}
}
