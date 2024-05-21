package com.sp.service.impl;

import com.sp.model.Card;
import com.sp.service.CardGenerator;

import java.util.Random;

public class CardGeneratorImpl implements CardGenerator {

    private static final Random random = new Random();
    private static final String[] nouns = {"Guardian", "Slayer", "Mage", "Beast"};
    private static final String[] adjectives = {"Mighty", "Agile", "Enchanted", "Furious"};
    private static final String[] verbs = {"protects", "attacks", "channels", "summons"};
    private static final String[] family = {"DC Comics", "Marvel"};

    private static String generateCardName(String cardType) {
        return String.format("%s %s %s", adjectives[random.nextInt(adjectives.length)], cardType, nouns[random.nextInt(nouns.length)]);
    }

    public Card generateNewCard() { //TODO: Add user id to the card
        Card newCard = new Card();
        // Set random values for most properties (assuming these are game stats)
        newCard.setName(generateCardName("gene"));
        newCard.setDescription(generateCardDescription("gene"));
        newCard.setImageUrl("path/to/image.jpg"); // TODO: Find image generate with id
        newCard.setFamily(family[random.nextInt(family.length)]);
        newCard.setAffinity("Random Affinity");
        newCard.setHp((int) (Math.random() * 100) + 1); // Random HP between 1 and 100
        newCard.setEnergy((int) (Math.random() * 50) + 1); // Random Energy between 1 and 50
        newCard.setAttack((int) (Math.random() * 80) + 1); // Random Attack between 1 and 80
        newCard.setDefense((int) (Math.random() * 80) + 1); // Random Defense between 1 and 80
        newCard.setPrice((int) (Math.random() * 1000) + 1); // Random Price between 1 and 1000
        // You can set userId based on your logic (e.g., logged in user)
        //newCard.setUserId(1L); // Replace with actual user ID
        return newCard;
    }

    private static String generateCardDescription(String cardType) {
        return String.format("A %s %s that %s.", adjectives[random.nextInt(adjectives.length)], cardType, verbs[random.nextInt(verbs.length)]);
    }

    private static String generateFlavorText() {
        StringBuilder flavorText = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            flavorText.append(generateSentence()).append(". ");
        }
        return flavorText.toString().trim();
    }

    private static String generateSentence() {
        // Implement your logic for generating a random sentence here
        return String.format("He %s %s", verbs[random.nextInt(verbs.length)], nouns[random.nextInt(nouns.length)]);
    }
}

