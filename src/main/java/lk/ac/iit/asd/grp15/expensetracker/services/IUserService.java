package lk.ac.iit.asd.grp15.expensetracker.services;

import lk.ac.iit.asd.grp15.expensetracker.entity.User;
public interface IUserService {
    void save(User user);

    User findByUsername(String username);
}
