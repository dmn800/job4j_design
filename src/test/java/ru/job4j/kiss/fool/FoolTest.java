package ru.job4j.kiss.fool;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class FoolTest {

    @Test
    void when15ThenFizzBuzz() {
        assertThat(Fool.fizzBuzz(15)).isEqualTo("FizzBuzz");
    }

    @Test
    void when3ThenFizz() {
        assertThat(Fool.fizzBuzz(3)).isEqualTo("Fizz");
    }

    @Test
    void when5ThenBuzz() {
        assertThat(Fool.fizzBuzz(5)).isEqualTo("Buzz");
    }

    @Test
    void when2ThenNumber() {
        assertThat(Fool.fizzBuzz(2)).isEqualTo("2");
    }

    @Test
    void whenCorrectAnswerThenTrue() {
        assertThat(Fool.isCorrectAnswer(3, "Fizz")).isTrue();
    }

    @Test
    void whenWrongAnswerThenFalse() {
        assertThat(Fool.isCorrectAnswer(3, "Buzz")).isFalse();
    }

}