package com.zilberoman.cardrecognizer.helpers.files;

import com.zilberoman.cardrecognizer.filters.IFileFilter;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.stream.Stream;

public class FilesHelper {

    public Stream<File> getFiles(String path, IFileFilter fileFilter) {
        File imageDirectory = new File(path);

        if (!imageDirectory.exists()) {
            System.out.printf("Directory %s does not exist. Returning null.%n", path);
            return null;
        }

        File[] files = imageDirectory.listFiles();

        if (files == null) {
            System.out.println("Directory is empty");
        }

        return files != null ? Arrays.stream(files).filter(fileFilter) : Stream.empty();
    }

    public Stream<File> getFilesFromResources(String path, IFileFilter fileFilter) {
        ClassLoader classLoader = getClass().getClassLoader();
        URL url = classLoader.getResource(path);

        if (url == null) {
            System.out.printf("Directory %s does not exist. Returning null.%n", path);
            return null;
        }

        return getFiles(url.getPath(), fileFilter);
    }
}
