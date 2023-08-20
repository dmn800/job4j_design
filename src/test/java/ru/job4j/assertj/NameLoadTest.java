package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.*;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void whenParseIsOk() {
        NameLoad nameLoad = new NameLoad();
        nameLoad.parse("start=finish", "new=old");
        assertThat(nameLoad.getMap()).isEqualTo(Map.of(
                "start", "finish",
                "new", "old"
        ));
    }

    @Test
    void whenNotContainEqual() {
        NameLoad nameLoad = new NameLoad();
        String name = "key:value";
        assertThatThrownBy(() -> nameLoad.parse(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching(
                        "this name: %s does not contain the symbol '='".formatted(name));
    }

    @Test
    void whenNotContainKey() {
        NameLoad nameLoad = new NameLoad();
        String name = "=value";
        assertThatThrownBy(() -> nameLoad.parse(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching(
                        "this name: %s does not contain a key".formatted(name)
                );
    }

    @Test
    void whenNotContainValue() {
        NameLoad nameLoad = new NameLoad();
        String name = "key=";
        assertThatThrownBy(() -> nameLoad.parse(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching(
                        "this name: %s does not contain a value".formatted(name)
                );
    }

    @Test
    void whenInputNameIsZero() {
        NameLoad nameLoad = new NameLoad();
        String[] names = {};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("empty");
    }
}