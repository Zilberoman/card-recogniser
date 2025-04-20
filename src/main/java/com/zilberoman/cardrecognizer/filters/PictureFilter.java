package com.zilberoman.cardrecognizer.filters;

import java.io.File;
import java.util.Arrays;

public class PictureFilter implements IFileFilter {
    private static final String[] PICTURE_EXTENSIONS = {"jpg", "jpeg", "png"};

    @Override
    public boolean test(File file) {
        if (file == null) {
            return false;
        }

        String fileName = file.getName().toLowerCase();
        return Arrays.stream(PICTURE_EXTENSIONS).anyMatch(fileName::endsWith);
    }
}
