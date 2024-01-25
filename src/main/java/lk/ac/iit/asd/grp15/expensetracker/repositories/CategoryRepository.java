package lk.ac.iit.asd.grp15.expensetracker.repositories;

import lk.ac.iit.asd.grp15.expensetracker.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Jamith Nimantha
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllByCreatedBy(String username);
}
