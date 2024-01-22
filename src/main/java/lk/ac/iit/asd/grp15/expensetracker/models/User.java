package lk.ac.iit.asd.grp15.expensetracker.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.Set;
@Getter
@Entity
@Table(name="`user`")
@Setter
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    @Transient
    private String passwordConfirm;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable=false, name = "created_at")
    private Date createdAt;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "user")
    private Set<Transaction> transactions;
}
