package lk.ac.iit.asd.grp15.expensetracker.services.imp;


import lk.ac.iit.asd.grp15.expensetracker.entity.Category;
import lk.ac.iit.asd.grp15.expensetracker.entity.User;
import lk.ac.iit.asd.grp15.expensetracker.repositories.UserRepository;
import lk.ac.iit.asd.grp15.expensetracker.services.ICategoryService;
import lk.ac.iit.asd.grp15.expensetracker.services.ISecurityService;
import lk.ac.iit.asd.grp15.expensetracker.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements IUserService {

    private final UserRepository userRepository;
    private final ICategoryService categoryService;
    private final ISecurityService securityService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        User savedUser = userRepository.save(user);
        securityService.autoLogin(savedUser.getUsername(), user.getPasswordConfirm());


        List<Category> defaultCategories = categoryService.findAllByUser("admin");
        defaultCategories.forEach(category -> {
            Category cat = new Category();
            cat.setName(category.getName());
            cat.setDescription(category.getDescription());
            cat.setCreatedBy(savedUser.getUsername());
            cat.setModifiedBy(savedUser.getUsername());
            cat.setCreatedDate(new Date());
            cat.setModifiedDate(new Date());
            categoryService.save(cat);
        });


    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
