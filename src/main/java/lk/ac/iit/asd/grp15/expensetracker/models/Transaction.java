package lk.ac.iit.asd.grp15.expensetracker.models;

import jakarta.persistence.*;
import lk.ac.iit.asd.grp15.expensetracker.enums.TransactionType;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "transaction")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "type" )
    private TransactionType type;

    @Column(nullable=false, name = "amount")
    private BigDecimal amount;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable=false, name = "transaction_date")
    private Date transactionDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable=false, name = "created_at")
    private Date createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @ToString.Exclude
    private User user;

    public BigDecimal getAmount() {
        if(type == TransactionType.EXPENSE) {
            return amount.multiply(BigDecimal.valueOf(-1));
        }
        return amount;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Transaction that = (Transaction) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}

