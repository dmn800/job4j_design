package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.CurrencyConverter;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.function.Predicate;

public class ReportAccounting implements Report {

    private final Store store;
    private final CurrencyConverter converter;
    private final Currency source;
    private final Currency target;

    public ReportAccounting(Store store,
                            CurrencyConverter converter,
                            Currency source,
                            Currency target) {
        this.store = store;
        this.converter = converter;
        this.source = source;
        this.target = target;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;").append(System.lineSeparator());

        for (Employee employee : store.findBy(filter)) {
            double converted = converter.convert(source, employee.getSalary(), target);
            text.append(employee.getName())
                    .append(" ")
                    .append(converted)
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
