
class Card {
    //each card has a suit
    String suit;
    //each card has a value <11 is ace>
    int value;
    //card name will equal value unless it's an 11"ace"
    String name;
    Card (String suit, int value) {
        this.suit = suit;
        this.value = value;
        //if card's value is 11, it is an ace, otherwise, it is named by it's value.
        if (value ==11)this.name = "Ace";
        else this.name = Integer.toString(value);
    }
    //for printing names of cards in hand
    void cardName(){
        //print name<value> and suit.
        System.out.println(this.name+" of "+this.suit);
    }
}
