/**
 * 
 */
package book;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Thanh Chau
 *
 */
@Repository
public interface IBookDAO extends JpaRepository<Book, Long> {
	/**
	 * Find a book by his isbn.
	 * 
	 * @param isbn
	 * @return
	 */
	public Book findByIsbnIgnoreCase(String isbn);

	/**
	 * Find all books that the title likes the given string.
	 * 
	 * @param title
	 * @return
	 */
	public List<Book> findByTitleLikeIngnoreCase(String title);

	/**
	 * Find all books of a given category code.
	 * 
	 * @param categoryCode
	 * @return
	 */
	@Query("SELECT b FROM Book b INNER JOIN b.category cat WHERE cat.code = :code")
	public List<Book> findByCategoryCode(@Param("code") String categoryCode);
}
