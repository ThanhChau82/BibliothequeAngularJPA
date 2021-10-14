/**
 * 
 */
package category;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Thanh Chau
 *
 */
@Entity
@Table(name = "category")
public class Category {
	@Id
	@Column(name = "code")
	private String code;

	@Column(name = "label", nullable = false)
	private String label;

	public Category() {
		super();
	}

	public Category(String code, String label) {
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
