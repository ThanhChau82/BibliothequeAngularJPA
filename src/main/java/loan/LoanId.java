/**
 * 
 */
package loan;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import book.Book;
import customer.Customer;

/**
 * @author Thanh Chau
 *
 */
@Embeddable
public class LoanId {
	@ManyToOne
	private Book book;
	
	@ManyToOne
	private Customer customer;
	
	@Column(name="creation_date")
	private LocalDateTime creationDate;
	
	public LoanId() {
		super();
	}
	public LoanId(Book book, Customer customer) {
		super();
		this.book = book;
		this.customer = customer;
		this.creationDate = LocalDateTime.now();
	}
	
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public LocalDateTime getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}	
	
}
