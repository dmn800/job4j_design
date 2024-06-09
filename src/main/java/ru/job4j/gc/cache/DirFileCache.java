package ru.job4j.gc.cache;

import java.io.*;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    private static final Logger LOG = LoggerFactory.getLogger(DirFileCache.class.getName());

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader input = new BufferedReader(
                new FileReader(Paths.get(cachingDir, key).toString()))) {
            while (input.ready()) {
                sb.append(input.readLine());
            }
        } catch (IOException e) {
            LOG.error("Error loading file");
        }
        return sb.toString();
    }

}
