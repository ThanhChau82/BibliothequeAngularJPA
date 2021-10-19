/**
 * 
 */
package loan;

import java.time.LocalDate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Thanh Chau
 *
 */
@ApiModel(value = "Loan DTO model")
public class LoanDTO {
	@ApiModelProperty(value = "Book id concerned by the loan")
	private Long bookId;

	@ApiModelProperty(value = "Customer id concerned by the loan")
	private Long customerId;

	@ApiModelProperty(value = "Loan begining date")
	private LocalDate beginDate;

	@ApiModelProperty(value = "Loan ending date")
	private LocalDate endDate;

	public LoanDTO() {
		super();
	}

	public LoanDTO(Long bookId, Long customerId) {
		super();
		this.bookId = bookId;
		this.customerId = customerId;
	}

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

	public LocalDate getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(LocalDate beginDate) {
		this.beginDate = beginDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

}
