/**
 * 
 */
package loan;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * @author Thanh Chau
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class LoanServiceImplMockitoJUnitTest {
	private static final String TEST_STRING = "test_string";

	@InjectMocks
	private LoanServiceImpl loanService;

	@Mock
	private ILoanDAO loanDAOMock;

	@SuppressWarnings("deprecation")
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Test method for {@link loan.LoanServiceImpl#addLoan(loan.Loan)}.
	 */
	@Test
	public void testAddLoan() {
		// Initializer mock.
		Loan loan = new Loan();

		Loan returnedLoan = new Loan();
		returnedLoan.setPk(new LoanId());

		when(loanDAOMock.save(loan)).thenReturn(returnedLoan);

		// Test.
		Loan result = loanService.addLoan(loan);

		// Verify.
		assertEquals(returnedLoan, result);
		verify(loanDAOMock, times(1)).save(loan);
	}

	/**
	 * Test method for {@link loan.LoanServiceImpl#findLoanById(loan.LoanDTO)}.
	 */
	@Test
	public void testFindLoanById() {
		// Initializer mock.
		LoanDTO loanDTO = new LoanDTO(Long.valueOf(0), Long.valueOf(1));
		Loan loan = new Loan();

		when(loanDAOMock.findLoanByCriteria(loanDTO.getBookId(), loanDTO.getCustomerId(), LoanStatus.OPENED))
				.thenReturn(loan);

		// Test.
		Loan result = loanService.findLoanById(loanDTO);

		// Verify.
		assertEquals(loan, result);
		verify(loanDAOMock, times(1)).findLoanByCriteria(loanDTO.getBookId(), loanDTO.getCustomerId(),
				LoanStatus.OPENED);
	}

	/**
	 * Test method for
	 * {@link loan.LoanServiceImpl#findAllOpenLoansOfCustomer(java.lang.String)}.
	 */
	@Test
	public void testFindAllOpenLoansOfThisCustomer() {
		// Initializer mock.
		Loan loan1 = new Loan();
		Loan loan2 = new Loan();

		List<Loan> loans = new ArrayList();
		loans.add(loan1);
		loans.add(loan2);

		when(loanDAOMock.findAllOpenLoansOfThisCustomerByEmail(TEST_STRING, LoanStatus.OPENED)).thenReturn(loans);

		// Test.
		List<Loan> results = loanService.findAllOpenLoansOfCustomer(TEST_STRING);

		// Verify.
		assertEquals(2, results.size());
		verify(loanDAOMock, times(1)).findAllOpenLoansOfThisCustomerByEmail(TEST_STRING, LoanStatus.OPENED);
	}

	/**
	 * Test method for
	 * {@link loan.LoanServiceImpl#findAllLoansBeforeEndDate(java.time.LocalDate)}.
	 */
	@Test
	public void testFindAllLoansBeforeEndDate() {
		// Initializer mock.
		Loan loan1 = new Loan();
		Loan loan2 = new Loan();

		List<Loan> loans = new ArrayList();
		loans.add(loan1);
		loans.add(loan2);

		LocalDate maxEndDate = LocalDate.of(2021, 10, 18);
		when(loanDAOMock.findAllByEndDateBefore(maxEndDate)).thenReturn(loans);

		// Test.
		List<Loan> results = loanService.findAllLoansBeforeEndDate(maxEndDate);

		// Verify.
		assertEquals(2, results.size());
		verify(loanDAOMock, times(1)).findAllByEndDateBefore(maxEndDate);
	}

	/**
	 * Test method for {@link loan.LoanServiceImpl#updateLoan(loan.Loan)}.
	 */
	@Test
	public void testUpdateLoan() {
		// Initializer mock.
		Loan loan = new Loan();

		when(loanDAOMock.save(loan)).thenReturn(loan);

		// Test.
		Loan result = loanService.updateLoan(loan);

		// Verify.
		assertEquals(loan, result);
		verify(loanDAOMock, times(1)).save(loan);
	}

	/**
	 * Test method for {@link loan.LoanServiceImpl#closeLoan(loan.Loan)}.
	 */
	@Test
	public void testCloseLoan() {
		// Initializer mock.
		Loan loan = new Loan();
		loan.setStatus(LoanStatus.OPENED);

		when(loanDAOMock.save(loan)).thenReturn(loan);

		// Test.
		Loan result = loanService.closeLoan(loan);

		// Verify.
		assertEquals(loan, result);
		assertEquals(LoanStatus.CLOSED, result.getStatus());
		verify(loanDAOMock, times(1)).save(loan);
	}

	/**
	 * Test method for
	 * {@link loan.LoanServiceImpl#checkIfLoanExists(loan.LoanDTO)}.<br>
	 * Case 1: loan exists.
	 */
	@Test
	public void testCheckIfLoanExistsOK() {
		// Initializer mock.
		LoanDTO loanDTO = new LoanDTO(Long.valueOf(1), Long.valueOf(2));

		when(loanDAOMock.findLoanByCriteria(loanDTO.getBookId(), loanDTO.getCustomerId(), LoanStatus.OPENED))
				.thenReturn(new Loan());

		// Test.
		boolean result = loanService.checkIfLoanExists(loanDTO);

		// Verify.
		assertTrue(result);
		verify(loanDAOMock, times(1)).findLoanByCriteria(loanDTO.getBookId(), loanDTO.getCustomerId(),
				LoanStatus.OPENED);
	}

	/**
	 * Test method for
	 * {@link loan.LoanServiceImpl#checkIfLoanExists(loan.LoanDTO)}.<br>
	 * Case 2: loan does not exist.
	 */
	@Test
	public void testCheckIfLoanExistsKO() {
		// Initializer mock.
		LoanDTO loanDTO = new LoanDTO(Long.valueOf(1), Long.valueOf(2));

		when(loanDAOMock.findLoanByCriteria(loanDTO.getBookId(), loanDTO.getCustomerId(), LoanStatus.OPENED))
				.thenReturn(null);

		// Test.
		boolean result = loanService.checkIfLoanExists(loanDTO);

		// Verify.
		assertFalse(result);
		verify(loanDAOMock, times(1)).findLoanByCriteria(loanDTO.getBookId(), loanDTO.getCustomerId(),
				LoanStatus.OPENED);
	}
}
