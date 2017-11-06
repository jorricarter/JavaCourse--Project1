import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
//Thinking of having this do all the println and method calls
public class Main {
    //get player input
    private static Scanner newScanner = new Scanner(System.in);
    //get numerical input
// private static int intInput = newScanner.nextInt();
    //keep track of number of players
    private static int quantPlayers;
    private static ArrayList<Player> allPlayers = new ArrayList<>();
    //keep track of who's turn it is
    private static int currentPlayer;
    //keep track of who plays first next round and to determine winner of last round.
    private static int firstPlayer;
    //track current card being played to compare it to current winning card.
    private static Card playedCard;
    //track winning card to check next card for same suit && higher value
    private static Card winningCard;
    //Start game
    public static void main(String[] args) {
        //message at beginning of game
        System.out.println("Welcome to the game AGRAM!");
        //figure out number of players. Keep asking until valid number received.
        while(true) {
            //ask for number of players
            System.out.print("How many players would you like to play with?");
            //get number of players
            quantPlayers = newScanner.nextInt();
            //ensure 2-5 players since there's only enough cards for 5 people.
            if (quantPlayers > 1 && quantPlayers < 6) {
                //exit loop once valid number received
                break;
            }
            //let user know why their number was not accepted
            System.out.println("Please select a number between 2 and 5.");
        }
//create players. they will be kept track of by their order in the list.(; this will create one extra player for coding convenience);
        for (int counter = 0; counter <= quantPlayers; counter++){
            Player newPlayer = new Player();
            //add player to list of players
            allPlayers.add(newPlayer);
        }
        //randomly; select dealer
        Random rand = new Random();
        //select dealer = "since random number will be between 0 and quantPlayer-1" + 1
        int dealerPlayer = rand.nextInt(quantPlayers) + 1;
        //"first player will be to the left of the dealer" if (dealer is last player) {firstPlayer is player 1};
        if (dealerPlayer == quantPlayers) firstPlayer = 1;
        //since dealer isn't last player in quantPlayers {firstPlayer is the player after dealer};
        else firstPlayer = dealerPlayer + 1;
        //first player will be the first currentPlayer
        currentPlayer = firstPlayer;
        //notify user of who dealer is.
        System.out.println("Player " + dealerPlayer + " is the dealer.");
        //fill in user of some of the rules
        System.out.println("Aces are high.");
        //insert pauses in between lines so user isn't ambushed with a paragraph.
        System.out.println("When the first person plays a card, everyone has to try to play the same suit.");
        System.out.println("The highest valued card of the current suit wins the stack.");
        System.out.println("Whoever wins the round gets to play the next card.");
        System.out.println("If you win the 6th round, you win the game!");
        System.out.println("The dealer will now deal the cards!");
//get the deck "already shuffled. If I used lists instead of arrays, I wouldve shuffled next line instead of in class."
        Deck deck = new Deck();
        //deal cards(deal twice to each player){deal 3 cards at a time}
        for (int dealing=1; dealing<=(quantPlayers*2); dealing++){
            //add pauses in between dealing cards
            System.out.println("Player "+ dealerPlayer +" dealt 3 cards to player "+currentPlayer);
            //deal cards to player hands.
            allPlayers.get(currentPlayer).addToHand(deck.dealCard());
            allPlayers.get(currentPlayer).addToHand(deck.dealCard());
            allPlayers.get(currentPlayer).addToHand(deck.dealCard());
            //if current player would exceed number of players, start over with player 1.
            advanceTurn();
        }
        //display hand
        System.out.println("Your cards are: ");
        //since I created an extra player, index 1 is player 1
        allPlayers.get(1).lookAtHand();
        playGame();
        System.out.println("Player "+firstPlayer+" won the game!");
    }
    private static void advanceTurn(){
        if (currentPlayer == quantPlayers) currentPlayer = 1;
        else currentPlayer++;
    }
    private static void playGame(){        //loop through all 6 rounds
        for (int rounds = 6; rounds > 0; rounds--){
            //loop through each player
            for (int player = 1; player <= quantPlayers; player++){
                //what to do if it's player's turn
                if (currentPlayer == 1) playerTurn(player);
                //since it's not the user's turn, let the computer play
                else computerTurn(player);
                //change current player to next player
                advanceTurn();
            }
            //tell who the winner is
            System.out.println("Player "+firstPlayer+" won the round!");
            //make the winner go first this round
            currentPlayer = firstPlayer;
        }
    }
    private static void playerTurn(int player){
        //initialize value to exist after loop
        int handSelection;
        //while (user didnt pick a valid card) {keep asking}
        while(true){
            //show player their cards to choose from
            System.out.println("Your cards are: ");
            allPlayers.get(1).lookAtHand();
            //get selection from player
            System.out.println("Which card would you like to play?");
            handSelection = newScanner.nextInt();
            // if (cardSelection is in hand) {end loop};
            if ((handSelection > -1) && (handSelection < allPlayers.get(1).getHandSize()))break;
        }
        //play selected card
        playedCard = allPlayers.get(1).playCard(handSelection);
        //if (first player to play card) {it is currently winning card by default};
        if (player==1) {winningCard = playedCard;}
        //since there is already a card played (compare to winning card)
        if (playedCard.suit.equals(winningCard.suit) && playedCard.value>winningCard.value) {
            //since card won, it is the new current winning card!
            winningCard = playedCard;
            //since card won, the player is the new current winner of the book/stack/trick
            firstPlayer = currentPlayer;
        }

    }
    private static void computerTurn(int player){
        //make computer look at it's hand
        ArrayList<Card> currentHand = allPlayers.get(currentPlayer).getHand();
        //computer will will look at one card at a time starting with the first
        int lowestCard = 0;
        //when the computer finds cards of the necessary suit, they will remember them here.
        int lowestSuited = 10;
        //computer will compare each card to find the lowest values
        for (int handIndex = 0; handIndex<currentHand.size(); handIndex++){
            //if the next card is lower than the lowest card, remember it.
            if (currentHand.get(handIndex).value<currentHand.get(lowestCard).value) lowestCard = handIndex;
            //if ((current card's suit matches required suit for the round) && (haven't found a card in this suit || this is the lowest value card of this suit)
            if ((currentHand.get(handIndex).suit.equals(winningCard.suit)) && (lowestSuited==10 || currentHand.get(handIndex).value<currentHand.get(lowestSuited).value)) {
                //current card is new lowest suited
                lowestSuited = handIndex;
            }
        }
        //if computer went first, playe lowest card ignoring suit and it is the current winning card
        if (player==1) {
            playedCard = allPlayers.get(currentPlayer).playCard(lowestCard);
            //since computer went first, its card is automatically the current winning card
            winningCard = playedCard;
            //if computer didnt go first, compare suit and value to determine if computer is new current winner
        }else{
            //if lowest suited card is not default"10" play it, otherwise, it cannot win the trick/book/stack
            if (lowestSuited!=10) {
                playedCard = allPlayers.get(currentPlayer).playCard(lowestSuited);
                //"since suit matches" check if (new card is higher);
                if (playedCard.value>winningCard.value){
                    //"since new card is higher"{new card is winning card;this player is winning player};
                    winningCard=playedCard;
                    firstPlayer=currentPlayer;
                }
                //since suit doesn't match, dont compare values
            }else playedCard = allPlayers.get(currentPlayer).playCard(lowestCard);
        }
        //display what card was played for user to know what's going on
        System.out.println("Player "+currentPlayer+" played the "+playedCard.name+" of "+playedCard.suit+"!");
    }
}
