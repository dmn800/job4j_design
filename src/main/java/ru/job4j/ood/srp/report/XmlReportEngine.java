package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.model.EmployeesWrapper;
import ru.job4j.ood.srp.model.XmlEmployee;
import ru.job4j.ood.srp.store.Store;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class XmlReportEngine implements Report {

    private final Store store;
    private final DateTimeParser<Calendar> parser = new ReportDateTimeParser();

    public XmlReportEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        try {

            List<XmlEmployee> list = store.findBy(filter).stream()
                    .map(e -> new XmlEmployee(
                            e.getName(),
                            parser.parse(e.getHired()),
                            parser.parse(e.getFired()),
                            e.getSalary()
                    ))
                    .collect(Collectors.toList());

            EmployeesWrapper wrapper = new EmployeesWrapper(list);

            JAXBContext context = JAXBContext.newInstance(EmployeesWrapper.class);
            Marshaller marshaller = context.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            StringWriter writer = new StringWriter();
            marshaller.marshal(wrapper, writer);

            return writer.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
