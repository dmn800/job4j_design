package ru.job4j.ood.isp.ex3;

public class VideoLesson implements LearningMaterial {
    @Override
    public void open() {
        System.out.println("Открыть видео");
    }

    @Override
    public void playAudio() {
        System.out.println("Воспроизвести аудио");
    }

    @Override
    public void printPages() {
        throw new UnsupportedOperationException();
    }
}
