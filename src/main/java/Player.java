import java.util.ArrayList;

public class Player {
//"gave up on Arrays, I'm using an arraylist for this part." List<of cards> is player's hand
    private ArrayList<Card> hand = new ArrayList<>();
//get methods
    //get/look at cards in hand
    public ArrayList<Card> getHand(){return hand;}
    //to show hand to user
    public void lookAtHand(){
        //for each card, print card name.
        for (int cardIndex = 0; cardIndex < this.hand.size(); cardIndex++){
            //each card with a number for selecting and on separate lines for readability since I won't have time to add method for sorting them.
            System.out.print(cardIndex+" - ");
            //this should print on same line since previous line used print instead of println
            this.getHand().get(cardIndex).cardName();
        }
    }
//set/add methods
    //add cards to hand<when dealing cards>
    public void addToHand(Card cards){this.hand.add(cards);}
    //constructor
    Player(){}
    //method for playing a selected card from your hand
    public Card playCard(int cardNumber){
        Card playedCard = this.hand.get(cardNumber);
        this.hand.remove(cardNumber);
        return playedCard;
    }
}
