/**
 * 
 */
package book;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import category.Category;
import loan.Loan;

/**
 * @author Thanh Chau
 *
 */
@Entity
@Table(name="book")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="book_id")
	private Long id;
	
	@Column(name="isbn", nullable = false, unique = true)
	private String isbn;
	
	@Column(name="title", nullable = false)
	private String title;
	
	@Column(name="release_date", nullable = false)
	private LocalDate releaseDate;
	
	@Column(name="register_date", nullable = false)
	private LocalDate registerDate;
	
	@Column(name="author", nullable = false)
	private String author;
	
	@Column(name="total_exemplaries")
	private Integer totalExemplaries;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="cat_code", referencedColumnName = "code")
	private Category category;
	
	@OneToMany(mappedBy = "pk.book", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Loan> loans = new HashSet<Loan>();
	
	public Book() {
		super();
	}

	public Book(Long id, String isbn, String title, LocalDate releaseDate, LocalDate registerDate, String author,
			Integer totalExemplaries, Category category, Set<Loan> loans) {
		super();
		this.id = id;
		this.isbn = isbn;
		this.title = title;
		this.releaseDate = releaseDate;
		this.registerDate = registerDate;
		this.author = author;
		this.totalExemplaries = totalExemplaries;
		this.category = category;
		this.loans = loans;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	public LocalDate getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(LocalDate registerDate) {
		this.registerDate = registerDate;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Integer getTotalExemplaries() {
		return totalExemplaries;
	}

	public void setTotalExemplaries(Integer totalExemplaries) {
		this.totalExemplaries = totalExemplaries;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Set<Loan> getLoans() {
		return loans;
	}

	public void setLoans(Set<Loan> loans) {
		this.loans = loans;
	}	
	
}
