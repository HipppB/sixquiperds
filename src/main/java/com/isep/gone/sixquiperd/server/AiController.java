package com.isep.gone.sixquiperd.server;

import com.isep.gone.sixquiperd.core.Card;
import org.springframework.web.bind.annotation.GetMapping;

public class AiController {
    // do a GET request on /ai/chooseCard

    @GetMapping(value = "/users")
    public Card chooseCard() {
        return new Card(1, 1);
    }


}
