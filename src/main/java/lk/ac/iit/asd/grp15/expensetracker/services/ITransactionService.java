package lk.ac.iit.asd.grp15.expensetracker.services;

import lk.ac.iit.asd.grp15.expensetracker.models.Transaction;
import java.math.BigDecimal;
import java.util.List;

public interface ITransactionService {
    void save(Transaction transaction);

    void deleteById(Long id);

    List<Transaction> findByDescriptionContaining(String username, String query);

    List<Transaction> findByUsername(String username);

    BigDecimal sum(List<Transaction> transactions);
}
