package com.example.boot_start_learning.chapter6;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/19
 */
@Getter
@Setter
@ToString
public class Singer implements Serializable {
    private static final long serialVersionUID = -6376142080192586245L;

    private Long id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private List<Album> albums;

    public boolean addAlbum(Album album) {
        if (albums == null) {
            albums = new ArrayList<>();
            return albums.add(album);
        } else if (albums.contains(album)) {
            return false;
        }
        return albums.add(album);
    }
}
