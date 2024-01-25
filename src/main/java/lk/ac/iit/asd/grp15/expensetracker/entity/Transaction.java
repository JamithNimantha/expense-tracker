package lk.ac.iit.asd.grp15.expensetracker.entity;

import jakarta.persistence.*;
import lk.ac.iit.asd.grp15.expensetracker.enums.TransactionType;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "transaction")
@Getter
@Setter
@ToString
public class Transaction extends EntityAudit{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, name = "description")
    private String description;

    @Column(nullable = false, name = "type" )
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @Column(nullable=false, name = "amount")
    private BigDecimal amount;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable=false, name = "transaction_date")
    private Date transactionDate;

//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(nullable=false, name = "created_at")
//    @CreatedDate
//    private Date createdAt;

    public BigDecimal getAmount() {
        if(this.type == TransactionType.EXPENSE) {
            return this.amount.negate();
        }
        return this.amount;
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

