package com.zilberoman.cardrecognizer.services;

import com.zilberoman.cardrecognizer.converters.BinaryStringConverter;
import com.zilberoman.cardrecognizer.filters.PictureFilter;
import com.zilberoman.cardrecognizer.helpers.files.FilesHelper;
import com.zilberoman.cardrecognizer.models.Suits;
import com.zilberoman.cardrecognizer.models.Values;
import com.zilberoman.cardrecognizer.utils.MathUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RecognizeService {
    private final Map<String, String> suitsMap;
    private final Map<String, String> valuesMap;

    public RecognizeService(PictureFilter pictureFilter, FilesHelper filesHelper, String suitsSourceFolder,
                            String valuesSourceFolder) {
        suitsMap = initializeSuits(pictureFilter, filesHelper, suitsSourceFolder);
        valuesMap = initializeValues(pictureFilter, filesHelper, valuesSourceFolder);
    }

    private static Map<String, String> initializeSuits(PictureFilter pictureFilter,
                                                       FilesHelper filesHelper,
                                                       String suitsSourceFolder) {
        return initialize(pictureFilter, filesHelper, suitsSourceFolder, Suits::lookupValue);
    }

    private static Map<String, String> initializeValues(PictureFilter pictureFilter, FilesHelper filesHelper,
                                                 String valuesSourceFolder) {
        return initialize(pictureFilter, filesHelper, valuesSourceFolder, Values::lookupValue);
    }

    private static Map<String, String> initialize(PictureFilter pictureFilter, FilesHelper filesHelper,
                                                  String sourceFolder, Function<String, String> fileNameConverter) {
        HashMap<String, String> result = new HashMap<>();

        filesHelper.getFilesFromResources(sourceFolder, pictureFilter)
                .forEach(picture -> {
                    try {
                        BufferedImage bufferedImage = ImageIO.read(picture);
                        result.put(fileNameConverter.apply(picture.getName().split("\\.")[0]),
                                BinaryStringConverter.convert(bufferedImage));
                    } catch (IOException e) {
                        System.out.println("Unable to read image");
                    }
                });

        return result;
    }

    public String recognizeValue(BufferedImage value) {
        return recognize(value, valuesMap);
    }

    public String recognizeSuit(BufferedImage suit) {
        return recognize(suit, suitsMap);
    }

    public String recognize(BufferedImage value, Map<String, String> source) {
        String binaryString = BinaryStringConverter.convert(value);

        if (!binaryString.contains(BinaryStringConverter.SPACE)) {

            // missed card
            return "";
        }

        int minDistance = Integer.MAX_VALUE;
        String recognizedValue = "";

        for (Map.Entry<String, String> entry : source.entrySet()) {
            int distance = MathUtils.getLevenshteinDistance(entry.getValue(), binaryString);

            if (distance < minDistance) {
                minDistance = distance;
                recognizedValue = entry.getKey();
            }
        }

        return recognizedValue;
    }
}
