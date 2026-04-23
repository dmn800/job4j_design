package ru.job4j.ood.srp.model;

public class JsonEmployee {

    private String name;
    private String hired;
    private String fired;
    private double salary;

    public JsonEmployee(String name, String hired, String fired, double salary) {
        this.name = name;
        this.hired = hired;
        this.fired = fired;
        this.salary = salary;
    }
}
