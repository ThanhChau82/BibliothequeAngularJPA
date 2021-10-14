/**
 * 
 */
package loan;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Thanh Chau
 *
 */
@ApiModel(value = "Simple loan DTO")
public class LoanDTO {
	@ApiModelProperty(value = "Book id concerned by the loan")
	private Long bookId;

	@ApiModelProperty(value = "Customer id concerned by the loan")
	private Long customerId;

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

}
