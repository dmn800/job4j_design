package ru.job4j.ood.template;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

@Disabled("Реализация генератора еще не создана")
class GeneratorTest {
    Generator generator = (template, args) -> "";

    @Test
    void whenTemplateHasAllKeysThenReturnValidString() {
        String template = "I am ${name}, who are ${subject}? ";
        Map<String, String> args = Map.of(
                "name", "Petr Arsentev",
                "subject", "you"
        );
        String expected = "I am a Petr Arsentev, Who are you? ";
        assertThat(generator.produce(template, args)).isEqualTo(expected);
    }

    @Test
    void whenMapDoesNotContainRequiredKeyThenThrowException() {
        String template = "I am ${name}, who are ${subject}? ";
        Map<String, String> args = Map.of(
                "name", "Petr Arsentev"
        );
        assertThatThrownBy(() -> generator.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenMapContainsExtraKeyThenThrowException() {
        String template = "I am ${name}, who are ${subject}? ";
        Map<String, String> args = Map.of(
                "name", "Petr Arsentev",
                "subject", "you",
                "age", "30"
        );
        assertThatThrownBy(() -> generator.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class);
    }
}