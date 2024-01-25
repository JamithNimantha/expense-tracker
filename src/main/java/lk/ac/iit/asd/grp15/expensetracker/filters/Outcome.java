package lk.ac.iit.asd.grp15.expensetracker.filters;

import lk.ac.iit.asd.grp15.expensetracker.enums.TransactionType;
import lk.ac.iit.asd.grp15.expensetracker.interfaces.ICriteria;
import lk.ac.iit.asd.grp15.expensetracker.entity.Transaction;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class Outcome implements ICriteria {
    @Override
    public List<Transaction> meets(List<Transaction> transactions) {
        return transactions.stream().filter(transaction -> transaction.getType().equals(TransactionType.EXPENSE)).collect(toList());
    }
}
