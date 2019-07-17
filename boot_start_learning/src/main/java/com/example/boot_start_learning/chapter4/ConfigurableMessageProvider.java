package com.example.boot_start_learning.chapter4;

import com.example.boot_start_learning.chapter2.MessageProvider;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/12
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ConfigurableMessageProvider implements MessageProvider {
    private String message = "Default message";
}
