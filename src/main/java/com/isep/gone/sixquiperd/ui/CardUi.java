package com.isep.gone.sixquiperd.ui;

import com.isep.gone.sixquiperd.core.Card;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import lombok.Getter;

public class CardUi {
    @Getter
    private Integer cardNumber;
    @Getter
    private ImageView imageView;

    private Image image;
    private Card card;

    CardUi(Card card) {
        this.card = card;
        this.cardNumber = card.getCardNumber();
        this.imageView = new ImageView();
        this.image = new javafx.scene.image.Image(
                getClass().getResourceAsStream(
                        "cards/" + cardNumber + ".png"
                )
        );
    }

    public Image getImage() {
        if (card.isHidden()) {
            return new javafx.scene.image.Image(
                    getClass().getResourceAsStream(
                            "cards/backside.png"
                    )
            );
        } else {
            return new javafx.scene.image.Image(
                    getClass().getResourceAsStream(
                            "cards/" + cardNumber + ".png"
                    )
            );
        }
    }

    // Get image from card number
    public ImageView getCard() {
        Pane stackPane = new Pane();

        stackPane.getStyleClass().add("card");
        imageView.setFitHeight(68);
        imageView.setFitWidth(45);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setImage(this.image);


        // Set the preferred size of the stack pane to the size of the image view
//        stackPane.setPrefSize(imageView.getFitWidth(), imageView.getFitHeight());
//
//        // detect mouse clicks on the image view

//        // Allow mouse events to pass through to the image view
//        stackPane.setMouseTransparent(true);
//
//        stackPane.getChildren().add(imageView);

        return imageView;
    }

}
