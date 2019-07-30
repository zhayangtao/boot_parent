package com.example.boot_start_learning.chapter9_transaction.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/24
 */
@Entity
@Table(name = "singer")
@Getter
@Setter
@ToString
@NamedQueries({@NamedQuery(name = Singer.FIND_ALL_WITH_ALBUM,
        query = "select distinct s from Singer s " +
                "left join fetch s.albums a " +
                "left join fetch s.instruments i"),
        @NamedQuery(name = Singer.FIND_ALL, query = "select s from Singer s"),
        @NamedQuery(name = Singer.FIND_SINGER_BY_ID, query = "select distinct s from Singer s " +
                "left join fetch s.albums a " +
                "left join fetch s.instruments i " +
                "where s.id=:id")})
@SqlResultSetMapping(name = "singerResult", entities = @EntityResult(entityClass = Singer.class))
public class Singer implements Serializable {
    private static final long serialVersionUID = 256267619521758215L;

    public static final String FIND_ALL = "Singer.findAll";
    public static final String FIND_SINGER_BY_ID = "Singer.findById";
    public static final String FIND_ALL_WITH_ALBUM = "Singer.findAllWithAlbum";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    // 定义一对多关系
    @OneToMany(mappedBy = "singer", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Album> albums = new HashSet<>();

    // 多对多映射
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "singer_instrument", joinColumns = @JoinColumn(name = "SINGER_ID"),
            inverseJoinColumns = @JoinColumn(name = "INSTRUMENT_ID"))
    private Set<Instrument> instruments = new HashSet<>();

    public void addAlbum(Album album) {
        albums.add(album);
    }

    public void removeAlbum(Album album) {
        albums.remove(album);
    }
}
