/**
 * 
 */
package loan;

/**
 * @author Thanh Chau
 *
 */
public enum LoanStatus {
	AVAILABLE("A", "Available"),
	BORROWED("B", "Borrowed"),
	RESERVED("R", "Reserved");
	
	private String code;
	private String label;
	
	private LoanStatus(String code, String label) {
		this.code = code;
		this.label = label;
	}

	public static LoanStatus getLoanStatusByCode(String code) {
		for (LoanStatus loanStatus : LoanStatus.values()) {
			if (loanStatus.getCode().equals(code)) {
				return loanStatus;
			}
		}
		
		return null;
	}

	public String getCode() {
		return code;
	}
	public String getLabel() {
		return label;
	}	
}
