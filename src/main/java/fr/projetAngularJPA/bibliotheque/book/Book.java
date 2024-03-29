/**
 * 
 */
package fr.projetAngularJPA.bibliotheque.book;

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

import fr.projetAngularJPA.bibliotheque.category.Category;
import fr.projetAngularJPA.bibliotheque.loan.Loan;

/**
 * @author Thanh Chau
 *
 */
@Entity
@Table(name = "book")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "book_id")
	private Long bookId;

	@Column(name = "isbn", nullable = false, unique = true)
	private String isbn;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "release_date", nullable = false)
	private LocalDate releaseDate;

	@Column(name = "register_date", nullable = false)
	private LocalDate registerDate;

	@Column(name = "author", nullable = false)
	private String author;

	@Column(name = "total_exemplaries")
	private Integer totalExemplaries;

	@ManyToOne(optional = false)
	@JoinColumn(name = "cat_code", referencedColumnName = "code")
	private Category category;

	@OneToMany(mappedBy = "pk.book", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Loan> loans = new HashSet<Loan>();

	public Book() {
		super();
	}

	public Book(Long bookId) {
		super();
		this.bookId = bookId;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((bookId == null) ? 0 : bookId.hashCode());
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		result = prime * result + ((loans == null) ? 0 : loans.hashCode());
		result = prime * result + ((registerDate == null) ? 0 : registerDate.hashCode());
		result = prime * result + ((releaseDate == null) ? 0 : releaseDate.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((totalExemplaries == null) ? 0 : totalExemplaries.hashCode());
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
		Book other = (Book) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (bookId == null) {
			if (other.bookId != null)
				return false;
		} else if (!bookId.equals(other.bookId))
			return false;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (isbn == null) {
			if (other.isbn != null)
				return false;
		} else if (!isbn.equals(other.isbn))
			return false;
		if (loans == null) {
			if (other.loans != null)
				return false;
		} else if (!loans.equals(other.loans))
			return false;
		if (registerDate == null) {
			if (other.registerDate != null)
				return false;
		} else if (!registerDate.equals(other.registerDate))
			return false;
		if (releaseDate == null) {
			if (other.releaseDate != null)
				return false;
		} else if (!releaseDate.equals(other.releaseDate))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (totalExemplaries == null) {
			if (other.totalExemplaries != null)
				return false;
		} else if (!totalExemplaries.equals(other.totalExemplaries))
			return false;
		return true;
	}

}
