/**
 * 
 */
package fr.projetAngularJPA.bibliotheque.category;

import java.util.List;

/**
 * @author Thanh Chau
 *
 */
public interface ICategoryService {
	/**
	 * Find all categories.
	 * 
	 * @return
	 */
	public List<Category> findAllCategories();
}
