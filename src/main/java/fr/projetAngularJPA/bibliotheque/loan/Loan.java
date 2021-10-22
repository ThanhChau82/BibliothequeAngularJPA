/**
 * 
 */
package fr.projetAngularJPA.bibliotheque.loan;

import java.io.Serializable;
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
public class Loan implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((beginDate == null) ? 0 : beginDate.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((pk == null) ? 0 : pk.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Loan other = (Loan) obj;
		if (beginDate == null) {
			if (other.beginDate != null)
				return false;
		} else if (!beginDate.equals(other.beginDate))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (pk == null) {
			if (other.pk != null)
				return false;
		} else if (!pk.equals(other.pk))
			return false;
		if (status != other.status)
			return false;
		return true;
	}
}
