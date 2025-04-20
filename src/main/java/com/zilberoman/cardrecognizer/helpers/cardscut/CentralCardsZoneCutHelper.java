package com.zilberoman.cardrecognizer.helpers.cardscut;

import java.awt.image.BufferedImage;
import java.util.List;

public class CentralCardsZoneCutHelper {
    private static final List<Integer> Y_PIXELS = List.of(580, 680);
    private static final List<Integer> X_PIXELS = List.of(140, 500);
    private static final List<Integer> SUIT_X_PIXELS = List.of(26, 60);
    private static final List<Integer> SUIT_Y_PIXELS = List.of(52, 87);
    private static final List<Integer> VALUE_X_PIXELS = List.of(6, 44);
    private static final List<Integer> VALUE_Y_PIXELS = List.of(7, 38);

    /**
     * Cuts the central zone with cards from the image.
     * @param image image to cut.
     * @return image with the central zone with cards.
     */
    public static BufferedImage cutCentralZoneCards(BufferedImage image) {
        return image.getSubimage(X_PIXELS.get(0), Y_PIXELS.get(0), X_PIXELS.get(1) - X_PIXELS.get(0),
                Y_PIXELS.get(1) - Y_PIXELS.get(0));
    }

    public static BufferedImage cutCard(BufferedImage image, int number) {
        int cardWidth = (X_PIXELS.get(1) - X_PIXELS.get(0)) / 5;
        int cardHeight = Y_PIXELS.get(1) - Y_PIXELS.get(0);
        return image.getSubimage(number * cardWidth, 0, cardWidth, cardHeight);
    }

    public static BufferedImage getSuit(BufferedImage cardImage) {
        int suitWidth = SUIT_X_PIXELS.get(1) - SUIT_X_PIXELS.get(0);
        int suitHeight = SUIT_Y_PIXELS.get(1) - SUIT_Y_PIXELS.get(0);
        return cardImage.getSubimage(SUIT_X_PIXELS.get(0), SUIT_Y_PIXELS.get(0), suitWidth, suitHeight);
    }

    public static BufferedImage getValue(BufferedImage cardImage) {
        int valueWidth = VALUE_X_PIXELS.get(1) - VALUE_X_PIXELS.get(0);
        int valueHeight = VALUE_Y_PIXELS.get(1) - VALUE_Y_PIXELS.get(0);
        return cardImage.getSubimage(VALUE_X_PIXELS.get(0), VALUE_Y_PIXELS.get(0), valueWidth,valueHeight);
    }
}