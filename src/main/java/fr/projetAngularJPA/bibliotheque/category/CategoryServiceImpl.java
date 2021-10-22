/**
 * 
 */
package fr.projetAngularJPA.bibliotheque.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Thanh Chau
 *
 */
@Service("categoryService")
public class CategoryServiceImpl implements ICategoryService {
	@Autowired
	private ICategoryDAO categoryDAO;

	@Override
	public List<Category> findAllCategories() {
		return categoryDAO.findAll();
	}

}
