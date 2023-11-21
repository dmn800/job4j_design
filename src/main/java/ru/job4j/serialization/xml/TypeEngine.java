package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "type")
public class TypeEngine {
    @XmlAttribute
    private String type;

    public TypeEngine() {
    }

    public TypeEngine(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "TypeEngine{"
                + "type='" + type + '\''
                + '}';
    }
}
