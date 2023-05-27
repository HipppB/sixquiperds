package com.isep.gone.sixquiperd.ui;

import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class CardUi {
    private Integer cardNumber;

    CardUi(Integer cardNumber) {
        this.cardNumber = cardNumber;
        System.out.println(cardNumber);
    }

    // Get image from card number
    public StackPane getCard() {
        StackPane stackPane = new StackPane();
        ImageView imageView = new ImageView();
        stackPane.getStyleClass().add("card");
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setCache(true);
        imageView.setPickOnBounds(true);
        imageView.setImage(
                new javafx.scene.image.Image(
                        getClass().getResourceAsStream(
                                "cards/" + cardNumber + ".png"
                        )
                )
        );
        stackPane.getChildren().add(imageView);
        return stackPane;
    }

}
