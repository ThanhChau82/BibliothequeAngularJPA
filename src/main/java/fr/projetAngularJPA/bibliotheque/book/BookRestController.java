/**
 * 
 */
package fr.projetAngularJPA.bibliotheque.book;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author Thanh Chau
 *
 */
@RestController
@RequestMapping("/rest/book/api")
@Api(value = "Book Rest Controller: contains all operations for managing books")
public class BookRestController {
	@Autowired
	private IBookService bookService;

	@PostMapping("/addBook")
	@ApiOperation(value = "Add a new book in the library", response = BookDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created: the book is successfully inserted"),
			@ApiResponse(code = 304, message = "Not Modified: the book is unsuccessfully inserted"),
			@ApiResponse(code = 409, message = "Conflit: the book already existed") })
	public ResponseEntity<BookDTO> addNewBook(@RequestBody BookDTO bookDTORequest) {
		Book existingBook = bookService.findBookByIsbn(bookDTORequest.getIsbn());
		if (existingBook != null) {
			return new ResponseEntity<BookDTO>(HttpStatus.CONFLICT);
		}

		Book requestBook = mapBookDTOToBook(bookDTORequest);
		Book book = bookService.addBook(requestBook);
		if (book != null && book.getBookId() != null) {
			BookDTO bookDTO = mapBookToBookDTO(book);
			return new ResponseEntity<BookDTO>(bookDTO, HttpStatus.CREATED);
		}

		return new ResponseEntity<BookDTO>(HttpStatus.NOT_MODIFIED);
	}

	/**
	 * Transform a bookDTO in book.
	 * 
	 * @param bookDTO
	 * @return
	 */
	private Book mapBookDTOToBook(BookDTO bookDTO) {
		ModelMapper modelMapper = new ModelMapper();
		Book book = modelMapper.map(bookDTO, Book.class);
		return book;
	}

	/**
	 * Transform a book in bookDTO.
	 * 
	 * @param book
	 * @return
	 */
	private BookDTO mapBookToBookDTO(Book book) {
		ModelMapper modelMapper = new ModelMapper();
		BookDTO bookDTO = modelMapper.map(book, BookDTO.class);
		return bookDTO;
	}

	@GetMapping("/searchByIsbn")
	@ApiOperation(value = "Search a book by its isbn", response = BookDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK: successful research"),
			@ApiResponse(code = 204, message = "No Content: no result found") })
	public ResponseEntity<BookDTO> searchBookByIsbn(@RequestParam("isbn") String isbn) {
		Book book = bookService.findBookByIsbn(isbn);
		if (book != null) {
			BookDTO bookDTO = mapBookToBookDTO(book);
			return new ResponseEntity<BookDTO>(bookDTO, HttpStatus.OK);
		}

		return new ResponseEntity<BookDTO>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/searchByTitle")
	@ApiOperation(value = "Search books by title or part of title", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK: successful research"),
			@ApiResponse(code = 204, message = "No Content: no result found") })
	public ResponseEntity<List<BookDTO>> searchBooksByTitle(@RequestParam("title") String title) {
		List<Book> books = bookService.findBooksByTitleOrPartTitle(title);
		if (!CollectionUtils.isEmpty(books)) {
			// Remove all null elements of list.
			books.removeAll(Collections.singleton(null));

			List<BookDTO> bookDTOs = books.stream().map(book -> {
				return mapBookToBookDTO(book);
			}).collect(Collectors.toList());

			return new ResponseEntity<List<BookDTO>>(bookDTOs, HttpStatus.OK);
		}

		return new ResponseEntity<List<BookDTO>>(HttpStatus.NO_CONTENT);
	}

	@PutMapping("/updateBook")
	@ApiOperation(value = "Update an existing book in the library", response = BookDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK: the book is successfully updated"),
			@ApiResponse(code = 304, message = "Not Modified: the book is unsuccessfully updated"),
			@ApiResponse(code = 404, message = "Not found: the book does not exist") })
	public ResponseEntity<BookDTO> updateBook(@RequestBody BookDTO bookDTORequest) {
		if (!bookService.checkIfBookIdExists(bookDTORequest.getBookId())) {
			return new ResponseEntity<BookDTO>(HttpStatus.NOT_FOUND);
		}

		Book bookRequest = mapBookDTOToBook(bookDTORequest);
		Book book = bookService.updateBook(bookRequest);
		if (book != null) {
			BookDTO bookDTO = mapBookToBookDTO(book);
			return new ResponseEntity<BookDTO>(bookDTO, HttpStatus.OK);
		}

		return new ResponseEntity<BookDTO>(HttpStatus.NOT_MODIFIED);
	}

	@DeleteMapping("/deleteBook/{bookId}")
	@ApiOperation(value = "Delete an existing book. If the book does not exist, nothing is done", response = String.class)
	@ApiResponse(code = 204, message = "No Content: book successfully deleted")
	public ResponseEntity<String> deleteBook(@PathVariable Long bookId) {
		bookService.deleteBook(bookId);
		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
	}
}
