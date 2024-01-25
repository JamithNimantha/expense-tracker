package lk.ac.iit.asd.grp15.expensetracker.repositories;

import lk.ac.iit.asd.grp15.expensetracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
