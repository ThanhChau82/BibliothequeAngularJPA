/**
 * 
 */
package fr.projetAngularJPA.bibliotheque.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Thanh Chau
 *
 */
@Repository
public interface ICategoryDAO extends JpaRepository<Category, String> {

}
