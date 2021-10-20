/**
 * 
 */
package loan;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Thanh Chau
 *
 */
public interface ILoanService {
	/**
	 * Add a new Loan.
	 * 
	 * @param loan
	 * @return
	 */
	public Loan addLoan(Loan loan);

	/**
	 * Find a loan by his id.
	 * 
	 * @param loanDTO
	 * @return
	 */
	public Loan findLoanById(LoanDTO loanDTO);

	/**
	 * Find all open loans of a given customer by his email.
	 * 
	 * @param email
	 * @return
	 */
	public List<Loan> findAllOpenLoansOfCustomer(String email);

	/**
	 * Find all loans that the end dates are before the given date.
	 * 
	 * @param maxEndDate
	 * @return
	 */
	public List<Loan> findAllLoansBeforeEndDate(LocalDate maxEndDate);

	/**
	 * Modify a loan.
	 * 
	 * @param loan
	 * @return
	 */
	public Loan updateLoan(Loan loan);

	/**
	 * Close a loan (logical delete).
	 * 
	 * @param loan
	 * @return
	 */
	public Loan closeLoan(Loan loan);

	/**
	 * Check if loan exists.
	 * 
	 * @param loanDTO
	 * @return
	 */
	public boolean checkIfLoanExists(LoanDTO loanDTO);

}
