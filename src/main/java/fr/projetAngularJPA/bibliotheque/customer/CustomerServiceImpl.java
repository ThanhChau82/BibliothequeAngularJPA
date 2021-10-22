/**
 * 
 */
package fr.projetAngularJPA.bibliotheque.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Thanh Chau
 *
 */
@Service("customerService")
@Transactional
public class CustomerServiceImpl implements ICustomerService {
	@Autowired
	private ICustomerDAO customerDAO;

	@Override
	public Customer addCustomer(Customer customer) {
		return customerDAO.save(customer);
	}

	@Override
	public Customer findCustomerById(Long customerId) {
		return customerDAO.getById(customerId);
	}

	@Override
	public Customer findCustomerByEmail(String email) {
		return customerDAO.findByEmailIgnoreCase(email);
	}

	@Override
	public List<Customer> findCustomersByLastNameOrPartLastName(String lastName) {
		return customerDAO
				.findByLastNameLikeIgnoreCase(new StringBuilder().append("%").append(lastName).append("%").toString());
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		return customerDAO.save(customer);
	}

	@Override
	public void deleteCustomer(Long customerId) {
		customerDAO.deleteById(customerId);
	}

	@Override
	public boolean checkIfCustomerIdExists(Long customerId) {		
		return customerDAO.existsById(customerId);
	}

}
