/**
 * 
 */
package loan;

import java.time.LocalDate;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

/**
 * @author Thanh Chau
 *
 */
@Entity
@Table(name = "loan")
@AssociationOverrides({ @AssociationOverride(name = "pk.book", joinColumns = @JoinColumn(name = "book_id")),
		@AssociationOverride(name = "pk.customer", joinColumns = @JoinColumn(name = "customer_id")) })
public class Loan {
	@EmbeddedId
	private LoanId pk = new LoanId();

	@Column(name = "begin_date", nullable = false)
	private LocalDate beginDate;

	@Column(name = "end_date", nullable = false)
	private LocalDate endDate;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private LoanStatus status;

	public Loan() {
		super();
	}

	public Loan(LoanId pk, LocalDate beginDate, LocalDate endDate, LoanStatus status) {
		super();
		this.pk = pk;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.status = status;
	}

	public LoanId getPk() {
		return pk;
	}

	public void setPk(LoanId pk) {
		this.pk = pk;
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

	public LoanStatus getStatus() {
		return status;
	}

	public void setStatus(LoanStatus status) {
		this.status = status;
	}
}
