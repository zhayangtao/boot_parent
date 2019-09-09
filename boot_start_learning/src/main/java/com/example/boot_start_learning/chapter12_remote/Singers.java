package com.example.boot_start_learning.chapter12_remote;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/31
 */
@Getter
@Setter
@AllArgsConstructor
public class Singers implements Serializable {
    private static final long serialVersionUID = 4462322582513076844L;
    private List<Singer> singers;
}
