package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "engine")
@XmlAccessorType(XmlAccessType.FIELD)
public class Engine {
    @XmlAttribute
    private boolean electric;

    @XmlAttribute
    private double price;

    @XmlAttribute
    private String company;

    private TypeEngine type;

    @XmlElementWrapper(name = "features")
    @XmlElement(name = "feature")
    private String[] features;

    public Engine() {
    }

    public Engine(boolean electric, double price, String company, TypeEngine type, String[] features) {
        this.electric = electric;
        this.price = price;
        this.company = company;
        this.type = type;
        this.features = features;
    }

    @Override
    public String toString() {
        return "Engine{"
                + "electric=" + electric
                + ", price=" + price
                + ", company='" + company + '\''
                + ", type=" + type
                + ", features=" + Arrays.toString(features)
                + '}';
    }

    public static void main(String[] args) throws Exception {
        final Engine engine = new Engine(true, 1000000, "Tesla",
                new TypeEngine("Electric"), new String[] {"25 MW", "400 km"}
        );

        JAXBContext context = JAXBContext.newInstance(Engine.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml;
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(engine, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Engine result = (Engine) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}
