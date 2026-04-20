package ru.job4j.ood.tdd;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

import java.util.Calendar;
import java.util.List;

@Disabled("Тесты отключены. Удалить аннотацию после реализации всех методов по заданию.")

class Cinema3DTest {
    Account account = new AccountCinema();
    Cinema cinema = new Cinema3D();
    Calendar date = Calendar.getInstance();
    Session session = new Session3D();

    @Test
    public void whenBuyThenGetTicket() {
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket).isEqualTo(new Ticket3D());
    }

    @Test
    public void whenAddSessionThenItExistsBetweenAllSessions() {
        cinema.add(session);
        List<Session> sessions = cinema.find(data -> true);
        assertThat(sessions).contains(session);
    }

    @Test
    public void whenBuyOnInvalidRowThenGetException() {
        assertThatThrownBy(() -> cinema.buy(account, -1, 1, date))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenSeatIsInvalidThenException() {
        assertThatThrownBy(() -> cinema.buy(account, 1, 0, date))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenBuySameSeatTwiceThenException() {
        cinema.buy(account, 1, 1, date);
        assertThatThrownBy(() -> cinema.buy(account, 1, 1, date))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void whenBuyDifferentSeatsThenDifferentTickets() {
        Ticket t1 = cinema.buy(account, 1, 1, date);
        Ticket t2 = cinema.buy(account, 1, 2, date);
        assertThat(t1).isNotEqualTo(t2);
    }

    @Test
    void whenFindWithPredicateThenReturnFilteredSessions() {
        Session s1 = new Session3D();
        Session s2 = new Session3D();
        cinema.add(s1);
        cinema.add(s2);
        List<Session> result = cinema.find(s -> s == s1);
        assertThat(result).containsExactly(s1);
    }
}