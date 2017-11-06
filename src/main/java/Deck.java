import java.util.ArrayList;
import java.util.Collections;

class Deck {
    //list of cards in deck = there are 35 cards+the ace of spades to remove;
    private ArrayList<Card> deck = new ArrayList<>();

    //When Deck is called, create cards to put in deck.
    Deck() {
        //start index counter for deck
        int deckIndex = 0;
        //for each suit, create cards for that suit
        String[] suits = {"Diamonds", "Clubs", "Hearts", "Spades"};
        for (String suit : suits) {
            //for each value, create a card in the current suit(deckIndex increases each time this loop runs, tracking place in deck of cards);
            int[] cardValues = {3, 4, 5, 6, 7, 8, 9, 10, 11};
            for (int value = 0; value < cardValues.length; value++, deckIndex++) {
                //Create card with the current suit and value as attributes
                Card cardInDeck = new Card(suit, cardValues[value]);
                //in deck[at current deckIndex] = add the new card;
                deck.add(cardInDeck);
            }
        }
        //remove ace of spades(always 35th index).
        deck.remove(35);
        //shuffle list of cards
        Collections.shuffle(deck);
    }
    Card dealCard(){
        Card dealtCard = deck.get(0);
        deck.remove(0);
        return dealtCard;
    }
}
