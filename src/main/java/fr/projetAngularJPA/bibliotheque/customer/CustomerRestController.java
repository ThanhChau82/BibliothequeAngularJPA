/**
 * 
 */
package fr.projetAngularJPA.bibliotheque.customer;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author Thanh Chau
 *
 */
@RestController
@RequestMapping("/rest/customer/api")
@Api(value = "Customer Rest Controller: contains all operations for managing customers")
public class CustomerRestController {
	@Autowired
	private ICustomerService customerService;

	@Autowired
	private JavaMailSender javaMailSender;

	@PostMapping("/addCustomer")
	@ApiOperation(value = "Add a new customer of the library", response = CustomerDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created: the customer is successfully inserted"),
			@ApiResponse(code = 304, message = "Not Modified: the customer is unsuccessfully inserted"),
			@ApiResponse(code = 409, message = "Conflit: the customer already existed") })
	public ResponseEntity<CustomerDTO> addNewCustomer(@RequestBody CustomerDTO customerDTORequest) {
		Customer existingCustomer = customerService.findCustomerByEmail(customerDTORequest.getEmail());
		if (existingCustomer != null) {
			return new ResponseEntity<CustomerDTO>(HttpStatus.CONFLICT);
		}

		Customer customerRequest = mapCustomerDTOToCustomer(customerDTORequest);
		Customer customer = customerService.addCustomer(customerRequest);
		if (customer != null && customer.getCustomerId() != null) {
			CustomerDTO customerDTO = mapCustomerToCustomerDTO(customer);
			return new ResponseEntity<CustomerDTO>(customerDTO, HttpStatus.CREATED);
		}

		return new ResponseEntity<CustomerDTO>(HttpStatus.NOT_MODIFIED);
	}

	/**
	 * Transform a customerDTO in customer.
	 * 
	 * @param customerDTO
	 * @return
	 */
	private Customer mapCustomerDTOToCustomer(CustomerDTO customerDTO) {
		ModelMapper modelMapper = new ModelMapper();
		Customer customer = modelMapper.map(customerDTO, Customer.class);
		return customer;
	}

	/**
	 * Transform a customer in customerDTO.
	 * 
	 * @param customer
	 * @return
	 */
	private CustomerDTO mapCustomerToCustomerDTO(Customer customer) {
		ModelMapper modelMapper = new ModelMapper();
		CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);
		return customerDTO;
	}

	@GetMapping("/searchByEmail")
	@ApiOperation(value = "Search a customer by email", response = CustomerDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK: successful research"),
			@ApiResponse(code = 204, message = "No Content: no result found") })
	public ResponseEntity<CustomerDTO> searchCustomerByEmail(@RequestParam("email") String email) {
		Customer customer = customerService.findCustomerByEmail(email);
		if (customer != null) {
			CustomerDTO customerDTO = mapCustomerToCustomerDTO(customer);
			return new ResponseEntity<CustomerDTO>(customerDTO, HttpStatus.OK);
		}

		return new ResponseEntity<CustomerDTO>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/searchByLastName")
	@ApiOperation(value = "Search customers by last name or part of last name", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK: successful research"),
			@ApiResponse(code = 204, message = "No Content: no result found") })
	public ResponseEntity<List<CustomerDTO>> searchCustomersByLastName(@RequestParam("lastName") String lastName) {
		List<Customer> customers = customerService.findCustomersByLastNameOrPartLastName(lastName);
		if (!CollectionUtils.isEmpty(customers)) {
			// Remove all null elements of list.
			customers.removeAll(Collections.singleton(null));

			List<CustomerDTO> customerDTOs = customers.stream().map(customer -> {
				return mapCustomerToCustomerDTO(customer);
			}).collect(Collectors.toList());

			return new ResponseEntity<List<CustomerDTO>>(customerDTOs, HttpStatus.OK);
		}

		return new ResponseEntity<List<CustomerDTO>>(HttpStatus.NO_CONTENT);
	}

	@PutMapping("/updateCustomer")
	@ApiOperation(value = "Update an existing customer of the library", response = CustomerDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK: the customer is successfully updated"),
			@ApiResponse(code = 304, message = "Not Modified: the customer is unsuccessfully updated"),
			@ApiResponse(code = 404, message = "Not found: the customer does not exist") })
	public ResponseEntity<CustomerDTO> updateCustomer(@RequestBody CustomerDTO customerDTORequest) {
		if (!customerService.checkIfCustomerIdExists(customerDTORequest.getCustomerId())) {
			return new ResponseEntity<CustomerDTO>(HttpStatus.NOT_FOUND);
		}

		Customer customerRequest = mapCustomerDTOToCustomer(customerDTORequest);
		Customer customer = customerService.updateCustomer(customerRequest);
		if (customer != null) {
			CustomerDTO customerDTO = mapCustomerToCustomerDTO(customer);
			return new ResponseEntity<CustomerDTO>(customerDTO, HttpStatus.OK);
		}

		return new ResponseEntity<CustomerDTO>(HttpStatus.NOT_MODIFIED);
	}

	@DeleteMapping("/deleteCustomer/{customerId}")
	@ApiOperation(value = "Delete an existing customer. If the customer does not exist, nothing is done", response = String.class)
	@ApiResponse(code = 204, message = "No Content: customer successfully deleted")
	public ResponseEntity<String> deleteCustomer(@PathVariable Long customerId) {
		customerService.deleteCustomer(customerId);
		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
	}

	@PutMapping("/sendEmailToCustomer")
	@ApiOperation(value = "Send an email to customer of the library", response = Boolean.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK: Email successfully sent"),
			@ApiResponse(code = 403, message = "Forbidden: Email cannot be sent"),
			@ApiResponse(code = 404, message = "Not found: no customer found, or wrong email") })
	public ResponseEntity<Boolean> sendMailToCustomer(@RequestBody MailDTO mailDTO) {
		Customer customer = customerService.findCustomerById(mailDTO.getCustomerId());
		if (customer == null) {
			return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
		} else if (customer != null && StringUtils.isEmpty(customer.getEmail())) {
			return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
		}

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom(mailDTO.getMAIL_FROM());
		mail.setTo(customer.getEmail());
		mail.setSubject(mailDTO.getEmailSubject());
		mail.setText(mailDTO.getEmailContent());
		mail.setSentDate(new Date());

		try {
			javaMailSender.send(mail);
		} catch (MailException e) {
			return new ResponseEntity<Boolean>(false, HttpStatus.FORBIDDEN);
		}

		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
}
