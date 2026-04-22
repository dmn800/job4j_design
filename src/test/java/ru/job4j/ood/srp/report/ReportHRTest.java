package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

public class ReportHRTest {

    @Test
    void whenGeneratedReportSortedHR() {
        Store store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Sergey", now, now, 200);
        Employee worker3 = new Employee("Petr", now, now, 50);
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);

        Report report = new ReportHR(store);
        String result = report.generate(employee -> true);

        int indexIvan = result.indexOf("Ivan");
        int indexSergey = result.indexOf("Sergey");
        int indexPetr = result.indexOf("Petr");

        assertThat(indexPetr).isLessThan(indexIvan);
        assertThat(indexIvan).isLessThan(indexSergey);
    }
}