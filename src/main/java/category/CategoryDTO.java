/**
 * 
 */
package category;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Thanh Chau
 *
 */
@ApiModel(value = "Category DTO model")
public class CategoryDTO {
	@ApiModelProperty(value = "Category code")
	private String code;

	@ApiModelProperty(value = "Category label")
	private String label;	

	public CategoryDTO() {
		super();
	}

	public CategoryDTO(String code, String label) {
		super();
		this.code = code;
		this.label = label;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
