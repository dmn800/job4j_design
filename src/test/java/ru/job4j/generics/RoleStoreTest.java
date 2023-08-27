package ru.job4j.generics;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

class RoleStoreTest {
    @Test
    void whenAddAndFindThenUsernameIsSeller() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Seller"));
        Role result = store.findById("1");
        assertThat(result.getRole()).isEqualTo("Seller");
    }
    @Test
    void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Seller"));
        Role result = store.findById("10");
        assertThat(result).isNull();
    }

}