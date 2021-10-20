/**
 * 
 */
package book;

import java.time.LocalDate;

import category.CategoryDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Thanh Chau
 *
 */
@ApiModel(value = "Book DTO model")
public class BookDTO {
	@ApiModelProperty(value = "Book id")
	private Long bookId;

	@ApiModelProperty(value = "Book isbn number")
	private String isbn;

	@ApiModelProperty(value = "Book title")
	private String title;

	@ApiModelProperty(value = "Book release date by the editor")
	private LocalDate releaseDate;

	@ApiModelProperty(value = "Book registration date in the library")
	private LocalDate registerDate;

	@ApiModelProperty(value = "Book author name")
	private String author;

	@ApiModelProperty(value = "Book total exemplaries")
	private Integer totalExemplaries;

	@ApiModelProperty(value = "Book category")
	private CategoryDTO category;	
	
	public BookDTO() {
		super();
	}

	public BookDTO(Long bookId, String isbn, String title, LocalDate releaseDate, LocalDate registerDate, String author,
			Integer totalExemplaries, CategoryDTO category) {
		super();
		this.bookId = bookId;
		this.isbn = isbn;
		this.title = title;
		this.releaseDate = releaseDate;
		this.registerDate = registerDate;
		this.author = author;
		this.totalExemplaries = totalExemplaries;
		this.category = category;
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

	public CategoryDTO getCategory() {
		return category;
	}

	public void setCategory(CategoryDTO category) {
		this.category = category;
	}

}
