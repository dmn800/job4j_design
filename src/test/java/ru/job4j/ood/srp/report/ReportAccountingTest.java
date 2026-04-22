package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.CurrencyConverter;
import ru.job4j.ood.srp.currency.InMemoryCurrencyConverter;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

public class ReportAccountingTest {

    @Test
    void whenSalaryUSDThenSalaryRUB() {
        Store store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);

        CurrencyConverter converter = new InMemoryCurrencyConverter();
        Report report = new ReportAccounting(
                store,
                converter,
                Currency.USD,
                Currency.RUB
        );
        StringBuilder expected = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(" ")
                .append(worker.getSalary() * 65D)
                .append(System.lineSeparator());

        assertThat(report.generate(employee -> true)).isEqualTo(expected.toString());
    }
}