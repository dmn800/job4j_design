package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

public class ReportProgrammerTest {

    @Test
    void whenSomethingThenSomething() {
        Store store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker);

        Report report = new ReportProgrammer(store, parser);
        String result = report.generate(emp -> true);

        assertThat(result).contains("name,hired,fired,salary");
        assertThat(result).contains("Ivan");
        assertThat(result).contains(",");
        assertThat(result).contains("100");
    }
}