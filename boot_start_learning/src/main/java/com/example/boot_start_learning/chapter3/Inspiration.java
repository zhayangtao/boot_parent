package com.example.boot_start_learning.chapter3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/09
 */
@Component
public class Inspiration {
    private String lyric = "I can keep the cracked open, to let light through";

    public Inspiration(@Value("For all my running, i can understand") String lyric) {
        this.lyric = lyric;
    }

    public String getLyric() {
        return lyric;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
    }
}
