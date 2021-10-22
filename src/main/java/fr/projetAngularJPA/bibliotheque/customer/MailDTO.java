/**
 * 
 */
package fr.projetAngularJPA.bibliotheque.customer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Thanh Chau
 *
 */
@ApiModel(value = "Mail DTO model")
public class MailDTO {
	@ApiModelProperty(value = "Mail sender address")
	private final String MAIL_FROM = "noreply.library.test@gmail.com";

	@ApiModelProperty(value = "Customer id")
	private Long customerId;

	@ApiModelProperty(value = "Email subject")
	private String emailSubject;

	@ApiModelProperty(value = "Email content")
	private String emailContent;

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getEmailSubject() {
		return emailSubject;
	}

	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}

	public String getEmailContent() {
		return emailContent;
	}

	public void setEmailContent(String emailContent) {
		this.emailContent = emailContent;
	}

	public String getMAIL_FROM() {
		return MAIL_FROM;
	}

}
