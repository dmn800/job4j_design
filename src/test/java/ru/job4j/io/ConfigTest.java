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
    void whenDoubleEquals() {
        String path = "data/test_double_equal.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("key"))
                .isEqualTo("value=1");
    }

    @Test
    void whenDoubleEqualsAfterEmpty() {
        String path = "data/test_double_equals.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("key"))
                .isEqualTo("value=");
    }

    @Test
    void whenEmptyAndComment() {
        String path = "data/test_empty_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThatThrownBy(() -> config.value("test"))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Don't impl this method yet!");

    }

    @Test
    void whenStartsEqual() {
        String path = "data/test_error1.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid line: =value");

    }

    @Test
    void whenEndssEqual() {
        String path = "data/test_error2.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid line: key=");

    }

    @Test
    void whenNoEqual() {
        String path = "data/test_error3.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid line: keyvalue");

    }

}