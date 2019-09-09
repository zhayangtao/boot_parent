package com.example.boot_start_learning.chapter16;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/30
 */
@Entity
@Getter
@Setter
public class Singer implements Serializable {
    private static final long serialVersionUID = 6668065849364643874L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Temporal(TemporalType.DATE)
    @Column(name = "BIRTH_DATE")
    private Date birthDate;
    @Version
    @Column(name = "VERSION")
    private int version;
}
