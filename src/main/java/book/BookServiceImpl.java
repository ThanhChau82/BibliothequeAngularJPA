/**
 * 
 */
package book;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Thanh Chau
 *
 */
@Service("bookService")
@Transactional
public class BookServiceImpl implements IBookService {
	@Autowired
	private IBookDAO bookDAO;

	@Override
	public Book addBook(Book book) {
		return bookDAO.save(book);
	}

	@Override
	public Book findBookById(Long bookId) {
		return bookDAO.getById(bookId);
	}

	@Override
	public Book findBookByIsbn(String isbn) {
		return bookDAO.findByIsbnIgnoreCase(isbn);
	}

	@Override
	public List<Book> findBooksByTitleOrPartTitle(String title) {
		return bookDAO.findByTitleLikeIngnoreCase(new StringBuilder().append("%").append(title).append("%").toString());
	}

	@Override
	public List<Book> findBooksByCategory(String categoryCode) {
		return bookDAO.findByCategoryCode(categoryCode);
	}

	@Override
	public Book updateBook(Book book) {
		return bookDAO.save(book);
	}

	@Override
	public void deleteBook(Long bookId) {
		bookDAO.deleteById(bookId);
	}

	@Override
	public boolean checkIfBookIdExists(Long bookId) {		
		return bookDAO.existsById(bookId);
	}

}
