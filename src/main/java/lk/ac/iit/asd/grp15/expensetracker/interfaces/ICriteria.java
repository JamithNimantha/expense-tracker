package lk.ac.iit.asd.grp15.expensetracker.interfaces;


import lk.ac.iit.asd.grp15.expensetracker.models.Transaction;

import java.util.List;

public interface ICriteria {
    List<Transaction> meets(List<Transaction> transactions);
}
