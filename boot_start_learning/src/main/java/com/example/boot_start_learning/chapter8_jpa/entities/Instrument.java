package com.example.boot_start_learning.chapter8_jpa.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/24
 */
@Entity
@Table(name = "instrument")
@Getter
@Setter
public class Instrument implements Serializable {
    private static final long serialVersionUID = 1713921383668751918L;
    @Id
    @Column(name = "INSTRUMENT_ID")
    private String instrumentId;

    // 定义多对多映射
    @ManyToMany
    @JoinTable(name = "singer_instrument", joinColumns = @JoinColumn(name = "INSTRUMENT_ID"),
            inverseJoinColumns = @JoinColumn(name = "SINGER_ID"))
    private Set<Singer> singers = new HashSet<>();

    @Override
    public String toString() {
        return "Instrument{" +
                "instrumentId='" + instrumentId + '\'' +
                '}';
    }
}
