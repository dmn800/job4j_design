package ru.job4j.ood.srp.model;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "employees")
public class EmployeesWrapper {

    private List<XmlEmployee> employees;

    public EmployeesWrapper() {
    }

    public EmployeesWrapper(List<XmlEmployee> employees) {
        this.employees = employees;
    }

    @XmlElement(name = "employee")
    public List<XmlEmployee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<XmlEmployee> employees) {
        this.employees = employees;
    }
}
