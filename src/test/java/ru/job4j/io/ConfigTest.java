package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class ConfigTest {
    @Test
    void whenPairWithoutComment() {
        String path = "data/app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.driver_class"))
                .isEqualTo("org.postgresql.Driver");
    }

    @Test
    void whenEmptyAndComment() {
        String path = "data/test_empty_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThatThrownBy(() -> config.value("test"))
                .isInstanceOf(UnsupportedOperationException.class);

    }

    @Test
    void whenIllegalArgument() {
        String path = "data/test_error.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class);

    }

}