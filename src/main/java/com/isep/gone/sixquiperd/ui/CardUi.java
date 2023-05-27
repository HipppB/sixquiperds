package com.isep.gone.sixquiperd.ui;

import javafx.scene.image.ImageView;

public class CardUi {
    private Integer cardNumber;

    CardUi(Integer cardNumber) {
        this.cardNumber = cardNumber;
        System.out.println(cardNumber);
    }

    // Get image from card number
    public ImageView getCard() {
        ImageView imageView = new ImageView();
        imageView.getStyleClass().add("card");
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setCache(true);
        imageView.setImage(
                new javafx.scene.image.Image(
                        getClass().getResourceAsStream(
                                "cards/" + cardNumber + ".png"
                        )
                )
        );
        return imageView;
    }

}
