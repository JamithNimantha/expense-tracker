package lk.ac.iit.asd.grp15.expensetracker.services.imp;

import lk.ac.iit.asd.grp15.expensetracker.entity.Category;
import lk.ac.iit.asd.grp15.expensetracker.repositories.CategoryRepository;
import lk.ac.iit.asd.grp15.expensetracker.services.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * @author Jamith Nimantha
 */
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll()
                .stream()
//                .sorted(Comparator.comparing(Category::getModifiedDate).reversed())
                .sorted(Comparator.comparing(Category::getId))
                .toList();
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category update(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findAllByUser(String username) {
        return findAll().stream()
                .filter(category -> category.getCreatedBy().equals(username))
                .toList();
    }

    @Override
    public Category findByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public List<Category> findAllByUserAndNameContaining(String username, String name) {
        return findAll().stream()
                .filter(category -> category.getCreatedBy().equals(username))
                .filter(category -> category.getName().contains(name))
                .toList();
    }
}
