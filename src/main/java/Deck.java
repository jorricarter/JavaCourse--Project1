import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Deck {
    //list of cards in deck = there are 35 cards+the ace of spades to remove;
    private ArrayList<Card> deck = new ArrayList<>();
    //create suits {spades is last so the ace of spades can be cut after looping};
    String[] suits = {"Diamonds", "Clubs", "Hearts", "Spades"};
    //create values {11 is last so the Ace of spades can be sliced off};
    int[] cardValues = {3, 4, 5, 6, 7, 8, 9, 10, 11};
    //When Deck is called, create cards to put in deck.
    Deck() {
        //start index counter for deck
        int deckIndex = 0;
        //for each suit, create cards for that suit
        for (int suit = 0; suit < suits.length; suit++) {
            //for each value, create a card in the current suit(deckIndex increases each time this loop runs, tracking place in deck of cards);
            for (int value = 0; value < cardValues.length; value++, deckIndex++) {
               //Create card with the current suit and value as attributes
                Card cardInDeck = new Card(suits[suit], cardValues[value]);
                //in deck[at current deckIndex] = add the new card;
                deck.add(cardInDeck);
            }
        }
        //remove ace of spades(always 35th index).
        deck.remove(35);
        //shuffle list of cards
        Collections.shuffle(deck);
    }
    public Card dealCard(){
        Card dealtCard = deck.get(0);
        deck.remove(0);
        return dealtCard;
    }
}
