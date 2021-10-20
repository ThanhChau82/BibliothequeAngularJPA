/**
 * 
 */
package book;

import java.util.List;

/**
 * @author Thanh Chau
 *
 */
public interface IBookService {
	/**
	 * Add a new book.
	 * 
	 * @param book
	 * @return
	 */
	public Book addBook(Book book);

	/**
	 * Find a book by his id.
	 * 
	 * @param bookId
	 * @return
	 */
	public Book findBookById(Long bookId);

	/**
	 * Find a book by his isbn.
	 * 
	 * @param isbn
	 * @return
	 */
	public Book findBookByIsbn(String isbn);

	/**
	 * Find book(s) by its title or part of its title.
	 * 
	 * @param title or part of title.
	 * @return
	 */
	public List<Book> findBooksByTitleOrPartTitle(String title);

	/**
	 * Find all books of a given categorie by categorie code.
	 * 
	 * @param categoryCode
	 * @return
	 */
	public List<Book> findBooksByCategory(String categoryCode);

	/**
	 * Modify a book.
	 * 
	 * @param book
	 * @return
	 */
	public Book updateBook(Book book);

	/**
	 * Delete a book.
	 * 
	 * @param bookId
	 */
	public void deleteBook(Long bookId);

	/**
	 * Check if bookId exists.
	 * 
	 * @param bookId
	 * @return
	 */
	public boolean checkIfBookIdExists(Long bookId);
}
