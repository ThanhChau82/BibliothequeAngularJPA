/**
 * 
 */
package customer;

import java.util.List;

/**
 * @author Thanh Chau
 *
 */
public interface ICustomerService {
	/**
	 * Add a new customer.
	 * 
	 * @param customer
	 * @return
	 */
	public Customer addCustomer(Customer customer);

	/**
	 * Find a customer by his id.
	 * 
	 * @param customerId
	 * @return
	 */
	public Customer findCustomerById(Long customerId);

	/**
	 * Find a customer by his email.
	 * 
	 * @param email
	 * @return
	 */
	public Customer findCustomerByEmail(String email);

	/**
	 * Find customer(s) by his last name or part of his last name.
	 * 
	 * @param lastName
	 * @return
	 */
	public List<Customer> findCustomersByLastNameOrPartLastName(String lastName);

	/**
	 * Modify a customer.
	 * 
	 * @param customer
	 * @return
	 */
	public Customer updateCustomer(Customer customer);

	/**
	 * Delete a customer.
	 * 
	 * @param customerId
	 */
	public void deleteCustomer(Long customerId);
}
