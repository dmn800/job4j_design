package ru.job4j.iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

class ListUtilsTest {
    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1,   2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddAfterWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenRemoveIfIsTrue() {
        ListUtils.removeIf(input, i -> i > 1);
        assertThat(input).hasSize(1).containsSequence(1);
    }

    @Test
    void whenReplaceIfIsTrue() {
        ListUtils.replaceIf(input, i -> i > 1, 2);
        assertThat(input).hasSize(2).containsSequence(1, 2);
    }

    @Test
    void whenRemoveAll() {
        ListUtils.removeAll(input, Arrays.asList(1, 3));
        assertThat(input).hasSize(0).containsSequence();
    }
    @Test
    void whenRemove3() {
        ListUtils.removeAll(input, Arrays.asList(3));
        assertThat(input).hasSize(1).containsSequence(1);
    }
}