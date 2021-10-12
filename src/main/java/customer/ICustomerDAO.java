/**
 * 
 */
package customer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Thanh Chau
 *
 */
@Repository
public interface ICustomerDAO extends JpaRepository<Customer, Long> {
	/**
	 * Find a customer by his email.
	 * @param email
	 * @return
	 */
	public Customer findByEmailIgnoreCase(String email);
	
	/**
	 * Find all customer that the last name likes the given string.
	 * @return
	 */
	public List<Customer> findByLastNameLikeIgnoreCase(String lastName);
}
