import java.sql.Array;
import java.util.ArrayList;

public class Dealer
{
    Deck theDeck;
    ArrayList<Card> dealersHand;
    // Making this addition for evalPPWinnings
    private int evalPPWinnings;

    // Is this what they mean by no arg constructor? - FIX ME,  also initialize the deck means which deck?
    Dealer()
    {
        theDeck = new Deck();
        this.evalPPWinnings = -1;
        dealersHand = new ArrayList<>();
    }
    // Setter and getter for evalPPWinnings
    public int getEvalPPWinnings (){return evalPPWinnings;}
    public void setEvalPPWinnings (int winning){this.evalPPWinnings = winning;}

    //Dealer's hand
    public ArrayList<Card> dealHand()
    {

        // if cards greater than 34 cards, then just remove the cards and continue
        if (theDeck.size() <= 34)
        {
           theDeck = new Deck();
        }

        // takes cards from deck and distributes them to the dealer
        dealersHand = new ArrayList<Card> (theDeck.subList(theDeck.size()-4, theDeck.size()-1));

        System.out.println("BEFORE Last index in deck array after dealer " + theDeck.size() );
        int length = theDeck.size()-1;
        //removes the dealer's cards from theDeck
        for (int i = length - 4; i < length-1; i++)
        {
            theDeck.remove(i);
        }
        int length2 = theDeck.size()-1;
        theDeck.remove(length2);
        System.out.println("AFTER Last index in deck array after dealer " + theDeck.size() );
        return dealersHand;
    }

    // Dealer draws cards and distributes to the players
    public ArrayList<Card> DrawCards(Player p1)
    {
        // Distributing to player
        p1.hand = new ArrayList<Card> (theDeck.subList(theDeck.size()-4, theDeck.size()-1));

        System.out.println("BEFORE Last index in deck array player " + theDeck.size() );
        //removes the dealer's cards from theDeck
        int length = theDeck.size()-1;
        //removes the player's cards from theDeck
        for (int i = length - 4; i < length-1; i++)
        {
            theDeck.remove(i);
        }
        int length2 = theDeck.size()-1;
        theDeck.remove(length2);
        System.out.println("AFTER Last index in deck array after player " + theDeck.size() );
        return p1.hand;
    }

}
