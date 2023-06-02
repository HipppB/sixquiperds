package com.isep.gone.sixquiperd.ui;

import com.isep.gone.sixquiperd.core.Card;
import javafx.scene.image.Image;

public interface ICard {
    static Image getImage(Card card) {
        if (card.isHidden()) {
            return new javafx.scene.image.Image(
                    ICard.class.getResourceAsStream(
                            "cards/backside.png"
                    )
            );
        } else {
            return new javafx.scene.image.Image(
                    ICard.class.getResourceAsStream(
                            "cards/" + card.getCardNumber() + ".png"
                    )
            );
        }
    }
}
