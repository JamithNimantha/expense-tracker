package lk.ac.iit.asd.grp15.expensetracker.configuration;

import lk.ac.iit.asd.grp15.expensetracker.entity.Category;
import lk.ac.iit.asd.grp15.expensetracker.entity.User;
import lk.ac.iit.asd.grp15.expensetracker.services.ICategoryService;
import lk.ac.iit.asd.grp15.expensetracker.services.ISecurityService;
import lk.ac.iit.asd.grp15.expensetracker.services.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author Jamith Nimantha
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final IUserService userService;
    private final ISecurityService securityService;
    private final ICategoryService categoryService;
    @Override
    @Transactional
    public void run(String... args) throws Exception {

        insertUsers();
        insertCategories();

    }

    private void insertUsers() {
        User admin = userService.findByUsername("admin");
        if (admin == null) {
            User user = new User();
            user.setUsername("admin");
            user.setPassword("admin");
            user.setCreatedBy("admin");
            user.setModifiedBy("admin");
            user.setCreatedDate(new Date());
            user.setModifiedDate(new Date());
            userService.save(user);
        } else {
            log.info("admin user already exists");
        }
    }

    private void insertCategories() {
        if (categoryService.findAll().isEmpty()) {
            Category category = new Category();
            category.setName("Food");
            category.setDescription("Food related");
            category.setCreatedBy("admin");
            category.setModifiedBy("admin");
            category.setCreatedDate(new Date());
            category.setModifiedDate(new Date());
            categoryService.save(category);

            Category category1 = new Category();
            category1.setName("Transport");
            category1.setDescription("Transport related");
            category1.setCreatedBy("admin");
            category1.setModifiedBy("admin");
            category1.setCreatedDate(new Date());
            category1.setModifiedDate(new Date());
            categoryService.save(category1);


            Category category2 = new Category();
            category2.setName("Entertainment");
            category2.setDescription("Entertainment related");
            category2.setCreatedBy("admin");
            category2.setModifiedBy("admin");
            category2.setCreatedDate(new Date());
            category2.setModifiedDate(new Date());
            categoryService.save(category2);

            Category category3 = new Category();
            category3.setName("Salary");
            category3.setDescription("Salary Income");
            category3.setCreatedBy("admin");
            category3.setModifiedBy("admin");
            category3.setCreatedDate(new Date());
            category3.setModifiedDate(new Date());
            categoryService.save(category3);
        } else {
            log.info("categories already exists");
        }
    }
}
