package com.example.boot_start_learning.chapter7_hibernate.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/24
 */
@Entity
@Table(name = "album")
@Getter
@Setter
public class Album implements Serializable {
    private static final long serialVersionUID = -3508213838738165165L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String title;
    @Temporal(TemporalType.DATE)
    @Column(name = "RELEASE_DATE")
    private Date releaseDate;
    @Column(name = "VERSION")
    private int version;

    // 定义多对一关系
    @ManyToOne
    @JoinColumn(name = "SINGER_ID")
    private Singer singer;

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", releaseDate=" + releaseDate +
                ", version=" + version +
                '}';
    }
}
