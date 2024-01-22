package lk.ac.iit.asd.grp15.expensetracker.services;

public interface ISecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
