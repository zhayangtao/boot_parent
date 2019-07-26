package com.example.boot_start_learning.chapter6;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/19
 */
@Getter
@Setter
@ToString
public class Album implements Serializable {
    private static final long serialVersionUID = -7142525868418310488L;

    private Long id;
    private Long singerId;
    private String title;
    private Date releaseDate;

}
