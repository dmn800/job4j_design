package ru.job4j.ood.srp.report;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.model.JsonEmployee;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class JsonReportEngine implements Report {

    private final Store store;
    private final DateTimeParser<Calendar> parser = new ReportDateTimeParser();
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public JsonReportEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<JsonEmployee> list = store.findBy(filter).stream()
                .map(e -> new JsonEmployee(
                        e.getName(),
                        parser.parse(e.getHired()),
                        parser.parse(e.getFired()),
                        e.getSalary()
                ))
                .collect(Collectors.toList());

        return gson.toJson(list);
    }
}
