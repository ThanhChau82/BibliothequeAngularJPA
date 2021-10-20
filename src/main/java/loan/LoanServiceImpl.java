/**
 * 
 */
package loan;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Thanh Chau
 *
 */
@Service("loanService")
public class LoanServiceImpl implements ILoanService {
	@Autowired
	private ILoanDAO loanDAO;

	@Override
	public Loan addLoan(Loan loan) {
		return loanDAO.save(loan);
	}

	@Override
	public Loan findLoanById(LoanDTO loanDTO) {
		return loanDAO.findLoanByCriteria(loanDTO.getBookId(), loanDTO.getCustomerId(), LoanStatus.OPENED);
	}

	@Override
	public List<Loan> findAllOpenLoansOfCustomer(String email) {
		return loanDAO.findAllOpenLoansOfThisCustomerByEmail(email, LoanStatus.OPENED);
	}

	@Override
	public List<Loan> findAllLoansBeforeEndDate(LocalDate maxEndDate) {
		return loanDAO.findAllByEndDateBefore(maxEndDate);
	}

	@Override
	public Loan updateLoan(Loan loan) {
		return loanDAO.save(loan);
	}

	@Override
	public Loan closeLoan(Loan loan) {
		loan.setStatus(LoanStatus.CLOSED);
		return loanDAO.save(loan);
	}

	@Override
	public boolean checkIfLoanExists(LoanDTO loanDTO) {
		Loan existingLoan = loanDAO.findLoanByCriteria(loanDTO.getBookId(), loanDTO.getCustomerId(), LoanStatus.OPENED);
		if (existingLoan != null) {
			return true;
		}

		return false;
	}

}
