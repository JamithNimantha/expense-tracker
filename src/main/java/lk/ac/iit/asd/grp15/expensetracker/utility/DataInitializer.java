package lk.ac.iit.asd.grp15.expensetracker.utility;

import lk.ac.iit.asd.grp15.expensetracker.models.User;
import lk.ac.iit.asd.grp15.expensetracker.services.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
            user.setCreatedAt(new Date());
            userService.save(user);
        } else {
            log.info("admin user already exists");
        }
    }

    private void insertCategories() {
    }
}
