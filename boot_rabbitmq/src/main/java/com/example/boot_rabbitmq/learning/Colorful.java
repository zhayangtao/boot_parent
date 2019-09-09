package com.example.boot_rabbitmq.learning;

/**
 * @author no one
 * @version 1.0
 * @since 2019/09/03
 */
public enum Colorful {

    RED {
        @Override
        public String colorName() {
            return "red";
        }
    },

    YELLOW {
        @Override
        public String colorName() {
            return "yellow";
        }
    },

    GREEN {
        @Override
        public String colorName() {
            return "green";
        }
    };

    public abstract String colorName();
}
