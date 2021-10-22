/**
 * 
 */
package fr.projetAngularJPA.bibliotheque.loan;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Thanh Chau
 *
 */
@Repository
public interface ILoanDAO extends JpaRepository<Loan, LoanId> {
	/**
	 * Find all open loans of a given customer by his email.
	 * 
	 * @param email  of customer
	 * @param status ('OPEN') of loan
	 * @return
	 */
	@Query("SELECT l from Loan l INNER JOIN l.pk.customer c WHERE UPPER(c.email) = UPPER(:email) AND l.status = :status")
	public List<Loan> findAllOpenLoansOfThisCustomerByEmail(@Param("email") String email,
			@Param("status") LoanStatus status);

	/**
	 * Find all loans with end date before the given date.
	 * 
	 * @param endDate
	 * @return
	 */
	public List<Loan> findAllByEndDateBefore(LocalDate endDate);

	/**
	 * Find loan by bookId, customerId and status.
	 * 
	 * @param bookId
	 * @param customerId
	 * @param status
	 * @return
	 */
	@Query("SELECT l from Loan l INNER JOIN l.pk.book b INNER JOIN l.pk.customer c WHERE b.bookId = :bookId AND c.customerId = :customerId AND l.status = :status")
	public Loan findLoanByCriteria(@Param("bookId") Long bookId, @Param("customerId") Long customerId,
			@Param("status") LoanStatus status);
}
