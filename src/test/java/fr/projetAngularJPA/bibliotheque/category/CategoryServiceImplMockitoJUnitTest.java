/**
 * 
 */
package fr.projetAngularJPA.bibliotheque.category;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * @author Thanh Chau
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceImplMockitoJUnitTest {

	@InjectMocks
	private CategoryServiceImpl categoryService;

	@Mock
	private ICategoryDAO categoryDAOMock;

	@SuppressWarnings("deprecation")
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Test method for {@link category.CategoryServiceImpl#findAllCategories()}.
	 */
	@Test
	public void testFindAllCategories() {
		// Initialize mock.
		List<Category> categories = new ArrayList();
		categories.add(new Category());
		categories.add(new Category());

		when(categoryDAOMock.findAll()).thenReturn(categories);

		// Test.
		List<Category> results = categoryService.findAllCategories();

		// Verify.
		assertEquals(2, results.size());
		verify(categoryDAOMock, times(1)).findAll();
	}

}
