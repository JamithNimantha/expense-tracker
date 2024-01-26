package lk.ac.iit.asd.grp15.expensetracker.factories;


import lk.ac.iit.asd.grp15.expensetracker.enums.TransactionType;
import lk.ac.iit.asd.grp15.expensetracker.filters.Expense;
import lk.ac.iit.asd.grp15.expensetracker.filters.Income;
import lk.ac.iit.asd.grp15.expensetracker.interfaces.ICriteria;

public class FilterFactory {

    private static FilterFactory instance = null;

    private FilterFactory() {
    }

    public static FilterFactory getInstance() {
        if (instance == null) {
            synchronized (FilterFactory.class) {
                if (instance == null)
                    instance = new FilterFactory();
            }
        }

        return instance;
    }

    public ICriteria getFilter(String filterType) {
        if (filterType == null) {
            return null;
        }
        if (filterType.equalsIgnoreCase(TransactionType.INCOME.name())) {
            return new Income();
        } else if (filterType.equalsIgnoreCase(TransactionType.EXPENSE.name())) {
            return new Expense();
        }

        return null;
    }
}
