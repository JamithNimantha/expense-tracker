package lk.ac.iit.asd.grp15.expensetracker.services.imp;


import lk.ac.iit.asd.grp15.expensetracker.models.Transaction;
import lk.ac.iit.asd.grp15.expensetracker.repositories.TransactionRepository;
import lk.ac.iit.asd.grp15.expensetracker.services.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransactionServiceImp implements ITransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public void save(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    @Override
    public void deleteById(Long id) {
        transactionRepository.deleteById(id);
    }

    @Override
    public List<Transaction> findByDescriptionContaining(String username, String query) {
        return transactionRepository.findByDescriptionContaining(username, query);
    }

    @Override
    public List<Transaction> findByUsername(String username) {
        return transactionRepository.findByUsername(username);
    }

    @Override
    public BigDecimal sum(List<Transaction> transactions) {
        return transactions.stream()
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
