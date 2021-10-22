/**
 * 
 */
package fr.projetAngularJPA.bibliotheque.customer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * @author Thanh Chau
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceImplMockitoJUnitTest {
	private static final String TEST_STRING = "test_string";

	@InjectMocks
	private CustomerServiceImpl customerService;

	@Mock
	private ICustomerDAO customerDAOMock;

	@SuppressWarnings("deprecation")
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Test method for
	 * {@link customer.CustomerServiceImpl#addCustomer(customer.Customer)}.
	 */
	@Test
	public void testAddCustomer() {
		// Initializer mock.
		Customer customer = new Customer();

		Customer returnedCustomer = new Customer();
		returnedCustomer.setCustomerId(Long.valueOf(1));

		when(customerDAOMock.save(customer)).thenReturn(returnedCustomer);

		// Test.
		Customer result = customerService.addCustomer(customer);

		// Verify.
		assertEquals(result, returnedCustomer);
		verify(customerDAOMock, times(1)).save(customer);
	}

	/**
	 * Test method for
	 * {@link customer.CustomerServiceImpl#findCustomerById(java.lang.Long)}.
	 */
	@Test
	public void testFindCustomerById() {
		// Initializer mock.
		Customer customer = new Customer();
		customer.setCustomerId(Long.valueOf(1));

		when(customerDAOMock.getById(customer.getCustomerId())).thenReturn(customer);

		// Test.
		Customer result = customerService.findCustomerById(customer.getCustomerId());

		// Verify.
		assertEquals(result, customer);
		verify(customerDAOMock, times(1)).getById(customer.getCustomerId());
	}

	/**
	 * Test method for
	 * {@link customer.CustomerServiceImpl#findCustomerByEmail(java.lang.String)}.
	 */
	@Test
	public void testFindCustomerByEmail() {
		// Initializer mock.
		Customer customer = new Customer();
		customer.setEmail(TEST_STRING);

		when(customerDAOMock.findByEmailIgnoreCase(TEST_STRING)).thenReturn(customer);

		// Test.
		Customer result = customerService.findCustomerByEmail(TEST_STRING);

		// Verify.
		assertEquals(result, customer);
		verify(customerDAOMock, times(1)).findByEmailIgnoreCase(TEST_STRING);
	}

	/**
	 * Test method for
	 * {@link customer.CustomerServiceImpl#findCustomersByLastNameOrPartLastName(java.lang.String)}.
	 */
	@Test
	public void testFindCustomersByLastNameOrPartLastName() {
		// Initializer mock.
		Customer customer1 = new Customer();
		customer1.setLastName(TEST_STRING);

		Customer customer2 = new Customer();
		customer2.setLastName(TEST_STRING);

		List<Customer> customers = new ArrayList();
		customers.add(customer1);
		customers.add(customer2);

		when(customerDAOMock.findByLastNameLikeIgnoreCase(
				new StringBuilder().append("%").append(TEST_STRING).append("%").toString())).thenReturn(customers);

		// Test.
		List<Customer> results = customerService.findCustomersByLastNameOrPartLastName(TEST_STRING);
		;

		// Verify.
		assertEquals(2, results.size());
		assertEquals(TEST_STRING, results.get(0).getLastName());
		assertEquals(TEST_STRING, results.get(1).getLastName());
		verify(customerDAOMock, times(1)).findByLastNameLikeIgnoreCase(
				new StringBuilder().append("%").append(TEST_STRING).append("%").toString());
	}

	/**
	 * Test method for
	 * {@link customer.CustomerServiceImpl#updateCustomer(customer.Customer)}.
	 */
	@Test
	public void testUpdateCustomer() {
		// Initializer mock.
		Customer customer = new Customer();

		when(customerDAOMock.save(customer)).thenReturn(customer);

		// Test.
		Customer result = customerService.updateCustomer(customer);

		// Verify.
		assertEquals(customer, result);
		verify(customerDAOMock, times(1)).save(customer);
	}

	/**
	 * Test method for
	 * {@link customer.CustomerServiceImpl#deleteCustomer(java.lang.Long)}.
	 */
	@Test
	public void testDeleteCustomer() {
		// Initializer mock.
		ArgumentCaptor<Long> valueCapture = ArgumentCaptor.forClass(Long.class);
		doNothing().when(customerDAOMock).deleteById(valueCapture.capture());

		// Test.
		customerService.deleteCustomer(Long.valueOf(1));

		// Verify.
		assertEquals(Long.valueOf(1), valueCapture.getValue());
		verify(customerDAOMock, times(1)).deleteById(Long.valueOf(1));
	}

	/**
	 * Test method for
	 * {@link customer.CustomerServiceImpl#checkIfCustomerIdExists(java.lang.Long)}.
	 */
	@Test
	public void testCheckIfCustomerIdExists() {
		// Initializer mock.
		when(customerDAOMock.existsById(Long.valueOf(1))).thenReturn(true);

		// Test.
		boolean result = customerService.checkIfCustomerIdExists(Long.valueOf(1));

		// Verify.
		assertTrue(result);
		verify(customerDAOMock, times(1)).existsById(Long.valueOf(1));
	}
}
