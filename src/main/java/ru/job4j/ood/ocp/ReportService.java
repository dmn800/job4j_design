package ru.job4j.ood.ocp;

/*
Нарушение OCP в том, что при добавлении нового типа отчета необходимо
изменять метод generate, добавив новое условие else-if
 */
public class ReportService {

    private static class PdfReport {
        public String build() {
            return "PDF report content";
        }
    }

    private static class HtmlReport {
        public String build() {
            return "HTML report content";
        }
    }

    public String generate(String type) {
        if (type.equals("PDF")) {
            return new PdfReport().build();
        } else if (type.equals("HTML")) {
            return new HtmlReport().build();
        }
        return "";
    }

    public static void main(String[] args) {
        ReportService service = new ReportService();

        System.out.println(service.generate("PDF"));
        System.out.println(service.generate("HTML"));
    }
}
