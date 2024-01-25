package lk.ac.iit.asd.grp15.expensetracker.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;
import java.util.Set;
@Getter
@Entity
@Table(name="`user`")
@Setter
@ToString
public class User extends EntityAudit{
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name="id")
//    private Long id;

    @Id
    @Column(nullable=false, unique = true)
    private String username;

    private String password;

    @Transient
    private String passwordConfirm;
}
