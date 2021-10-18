/**
 * 
 */
package book;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import category.Category;

/**
 * @author Thanh Chau
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class BookServiceImplMockitoJUnitTest {
	private static final String TEST_STRING = "test_string";

	@InjectMocks
	private BookServiceImpl bookService;

	@Mock
	private IBookDAO bookDAOMock;

	@SuppressWarnings("deprecation")
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Test method for {@link book.BookServiceImpl#addBook(book.Book)}.
	 */
	@Test
	public void testAddBook() {
		// Initialize mock.
		Book book = new Book();
		book.setIsbn(TEST_STRING);

		Book returnedBook = new Book();
		returnedBook.setIsbn(TEST_STRING);
		returnedBook.setId(Long.valueOf(1));

		when(bookDAOMock.save(book)).thenReturn(returnedBook);

		// Test.
		Book result = bookService.addBook(book);

		// Verify test.
		assertEquals(result, returnedBook);
		verify(bookDAOMock, times(1)).save(book);
	}

	/**
	 * Test method for {@link book.BookServiceImpl#findBookById(java.lang.Long)}.
	 */
	@Test
	public void testFindBookById() {
		// Initialize mock.
		Book book = new Book();
		book.setId(Long.valueOf(1));

		when(bookDAOMock.getById(book.getId())).thenReturn(book);

		// Test.
		Book result = bookService.findBookById(book.getId());

		// Verify test.
		assertEquals(result, book);
		verify(bookDAOMock, times(1)).getById(book.getId());
	}

	/**
	 * Test method for
	 * {@link book.BookServiceImpl#findBookByIsbn(java.lang.String)}.
	 */
	@Test
	public void testFindBookByIsbn() {
		// Initialize mock.
		Book book = new Book();
		book.setIsbn(TEST_STRING);

		when(bookDAOMock.findByIsbnIgnoreCase(TEST_STRING)).thenReturn(book);

		// Test.
		Book result = bookService.findBookByIsbn(TEST_STRING);

		// Verify test.
		assertEquals(result, book);
		verify(bookDAOMock, times(1)).findByIsbnIgnoreCase(TEST_STRING);
	}

	/**
	 * Test method for
	 * {@link book.BookServiceImpl#findBooksByTitleOrPartTitle(java.lang.String)}.
	 */
	@Test
	public void testFindBooksByTitleOrPartTitle() {
		// Initialize mock.
		Book book1 = new Book();
		book1.setTitle(TEST_STRING);

		Book book2 = new Book();
		book2.setTitle(TEST_STRING);

		List<Book> books = new ArrayList();
		books.add(book1);
		books.add(book2);

		when(bookDAOMock
				.findByTitleLikeIngnoreCase(new StringBuilder().append("%").append(TEST_STRING).append("%").toString()))
						.thenReturn(books);

		// Test.
		List<Book> results = bookService.findBooksByTitleOrPartTitle(TEST_STRING);

		// Verify test.
		assertEquals(2, results.size());
		assertEquals(TEST_STRING, results.get(0).getTitle());
		assertEquals(TEST_STRING, results.get(1).getTitle());
		verify(bookDAOMock, times(1))
				.findByTitleLikeIngnoreCase(new StringBuilder().append("%").append(TEST_STRING).append("%").toString());
	}

	/**
	 * Test method for
	 * {@link book.BookServiceImpl#findBooksByCategory(java.lang.String)}.
	 */
	@Test
	public void testFindBooksByCategory() {
		// Initialize mock.
		Category category = new Category();
		category.setCode(TEST_STRING);

		Book book1 = new Book();
		book1.setCategory(category);

		Book book2 = new Book();
		book2.setCategory(category);

		List<Book> books = new ArrayList();
		books.add(book1);
		books.add(book2);

		when(bookDAOMock.findByCategoryCode(TEST_STRING)).thenReturn(books);

		// Test.
		List<Book> results = bookService.findBooksByCategory(TEST_STRING);

		// Verify test.
		assertEquals(2, results.size());
		assertEquals(TEST_STRING, results.get(0).getCategory().getCode());
		assertEquals(TEST_STRING, results.get(1).getCategory().getCode());
		verify(bookDAOMock, times(1)).findByCategoryCode(TEST_STRING);
	}

	/**
	 * Test method for {@link book.BookServiceImpl#updateBook(book.Book)}.
	 */
	@Test
	public void testUpdateBook() {
		// Initialize mock.
		Book book = new Book();
		book.setIsbn(TEST_STRING);

		when(bookDAOMock.save(book)).thenReturn(book);

		// Test.
		Book result = bookService.updateBook(book);

		// Verify test.
		assertEquals(result, book);
		verify(bookDAOMock, times(1)).save(book);
	}

	/**
	 * Test method for {@link book.BookServiceImpl#deleteBook(java.lang.Long)}.
	 */
	@Test
	public void testDeleteBook() {
		// Initialize mock.
		ArgumentCaptor<Long> valueCapture = ArgumentCaptor.forClass(Long.class);
		doNothing().when(bookDAOMock).deleteById(valueCapture.capture());

		// Test.
		bookService.deleteBook(Long.valueOf(1));

		// Verify test.
		assertEquals(Long.valueOf(1), valueCapture.getValue());
		verify(bookDAOMock, times(1)).deleteById(Long.valueOf(1));
	}

}
