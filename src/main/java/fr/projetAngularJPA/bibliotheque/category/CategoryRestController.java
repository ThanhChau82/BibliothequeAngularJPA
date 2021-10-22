/**
 * 
 */
package fr.projetAngularJPA.bibliotheque.category;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/rest/category/api")
@Api(value = "Book category Rest Controller")
public class CategoryRestController {
	@Autowired
	private ICategoryService categoryService;

	@GetMapping("/allBookCategories")
	@ApiOperation(value = "List all book categories in the library", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK: successfully listed"),
			@ApiResponse(code = 204, message = "No Content: no result found") })
	public ResponseEntity<List<CategoryDTO>> getAllBookCategories() {
		List<Category> categories = categoryService.findAllCategories();
		if (!CollectionUtils.isEmpty(categories)) {
			// Remove all null elements of list.
			categories.removeAll(Collections.singleton(null));

			List<CategoryDTO> categorieDTOs = categories.stream().map(category -> {
				return mapCategoryToCategoryDTO(category);
			}).collect(Collectors.toList());

			return new ResponseEntity<List<CategoryDTO>>(categorieDTOs, HttpStatus.OK);
		}

		return new ResponseEntity<List<CategoryDTO>>(HttpStatus.NO_CONTENT);
	}

	/**
	 * Transform a category in categoryDTO.
	 * 
	 * @param category
	 * @return
	 */
	private CategoryDTO mapCategoryToCategoryDTO(Category category) {
		ModelMapper modelMapper = new ModelMapper();
		CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);
		return categoryDTO;
	}
}
