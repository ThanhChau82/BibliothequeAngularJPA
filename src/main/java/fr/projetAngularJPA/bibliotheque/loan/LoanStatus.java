/**
 * 
 */
package fr.projetAngularJPA.bibliotheque.loan;

/**
 * @author Thanh Chau
 *
 */
public enum LoanStatus {
	CLOSED("Loan closed"), OPENED("Loan opened");

	private String label;

	private LoanStatus(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}
