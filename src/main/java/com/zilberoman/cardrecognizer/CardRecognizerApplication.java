package com.zilberoman.cardrecognizer;

import com.zilberoman.cardrecognizer.filters.PictureFilter;
import com.zilberoman.cardrecognizer.helpers.cardscut.CentralCardsZoneCutHelper;
import com.zilberoman.cardrecognizer.helpers.files.FilesHelper;
import com.zilberoman.cardrecognizer.services.RecognizeService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class CardRecognizerApplication {
    private static final String SUITS_SOURCE_FOLDER = "searching/suitmarks";
    private static final String VALUES_SOURCE_FOLDER = "searching/values";
    private static final Integer MAX_NUMBER_OF_CARDS = 5;

    public static void main(String[] args) {
        if (args.length > 0) {
            PictureFilter pictureFilter = new PictureFilter();
            FilesHelper filesHelper = new FilesHelper();
            RecognizeService recognizeService = new RecognizeService(pictureFilter, filesHelper, SUITS_SOURCE_FOLDER,
                    VALUES_SOURCE_FOLDER);
            filesHelper.getFiles(args[0], pictureFilter)
                    .forEach(picture -> {
                        try {
                            BufferedImage bufferedImage = ImageIO.read(picture);
                            BufferedImage cutImage = CentralCardsZoneCutHelper.cutCentralZoneCards(bufferedImage);
                            StringBuilder result = new StringBuilder(picture.getName())
                                    .append(" - ");

                            for (int i = 0; i < MAX_NUMBER_OF_CARDS; i++) {
                                BufferedImage card = CentralCardsZoneCutHelper.cutCard(cutImage, i);
                                BufferedImage value = CentralCardsZoneCutHelper.getValue(card);
                                BufferedImage suit = CentralCardsZoneCutHelper.getSuit(card);
                                result.append(recognizeService.recognizeValue(value))
                                        .append(recognizeService.recognizeSuit(suit));
                            }

                            System.out.println(result);
                        } catch (IOException e) {
                            System.out.printf("Unable to read image %s: Getting the exception: %s%n",
                                    picture.getAbsolutePath(), e);
                        }
                    });
        }
    }
}
