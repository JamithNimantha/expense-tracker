package lk.ac.iit.asd.grp15.expensetracker.repositories;

import lk.ac.iit.asd.grp15.expensetracker.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("select t from Transaction t where t.createdBy = :username")
    List<Transaction> findByUsername(String username);

    @Query("select t from Transaction t where t.createdBy = :username and t.description like %:query% ")
    List<Transaction> findByDescriptionContaining(String username, String query);
}
