package lk.ac.iit.asd.grp15.expensetracker.configuration;

import lk.ac.iit.asd.grp15.expensetracker.entity.User;
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
    }
}
