/**
 * 
 */
package customer;

import java.time.LocalDate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Thanh Chau
 *
 */
@ApiModel(value = "Customer DTO model")
public class CustomerDTO {
	@ApiModelProperty(value = "Customer id")
	private Long customerId;

	@ApiModelProperty(value = "Customer first name")
	private String firstName;

	@ApiModelProperty(value = "Customer last name")
	private String lastName;

	@ApiModelProperty(value = "Customer job")
	private String job;

	@ApiModelProperty(value = "Customer adresse")
	private String adresse;

	@ApiModelProperty(value = "Customer email")
	private String email;

	@ApiModelProperty(value = "Customer registration date in the library")
	private LocalDate registerDate;

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(LocalDate registerDate) {
		this.registerDate = registerDate;
	}

}
