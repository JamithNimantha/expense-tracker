package lk.ac.iit.asd.grp15.expensetracker.services.imp;


import lk.ac.iit.asd.grp15.expensetracker.models.User;
import lk.ac.iit.asd.grp15.expensetracker.repositories.UserRepository;
import lk.ac.iit.asd.grp15.expensetracker.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements IUserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
