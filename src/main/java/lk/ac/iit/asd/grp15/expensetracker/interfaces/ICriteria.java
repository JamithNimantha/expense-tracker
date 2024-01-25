package lk.ac.iit.asd.grp15.expensetracker.interfaces;


import lk.ac.iit.asd.grp15.expensetracker.entity.Transaction;

import java.util.List;

public interface ICriteria {
    List<Transaction> meets(List<Transaction> transactions);
}
