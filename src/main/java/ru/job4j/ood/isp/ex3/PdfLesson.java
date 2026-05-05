package ru.job4j.ood.isp.ex3;

public class PdfLesson implements LearningMaterial {
    @Override
    public void open() {
        System.out.println("Открыть PDF");
    }

    @Override
    public void playAudio() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void printPages() {
        System.out.println("Печать страниц");
    }
}
