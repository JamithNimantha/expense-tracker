package lk.ac.iit.asd.grp15.expensetracker.services;

import lk.ac.iit.asd.grp15.expensetracker.entity.Category;

import java.util.List;
import java.util.Optional;

/**
 * @author Jamith Nimantha
 */
public interface ICategoryService {


    List<Category> findAll();

    Category save(Category category);

    Optional<Category> findById(Long id);

    void deleteById(Long id);

    Category update(Category category);

    List<Category> findAllByUser(String username);

    Category findByName(String name);

    List<Category> findAllByUserAndNameContaining(String username, String name);


}
