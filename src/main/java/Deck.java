import java.util.ArrayList;
import java.util.Collections;

//ONLY USE ArrayList THINGS!!!!
public class Deck extends ArrayList<Card>
{
    //creates the deck of 52 cards and all the cards are randomly sorted
    Deck()
    {
        char[] suits = {'C', 'H', 'D', 'S'};
        for(int i = 0; i < suits.length; i++) //for getting each suit
        {
            for(int j = 2; j <= 14; j++) //for getting each card value
            {
                this.add(new Card(suits[i], j)); //creates a new Card into ArrayList
            }
        }
        Collections.shuffle(this); //shuffles the Cards
    }

    //creates a NEW deck of 52 cards and shuffles them and discards the old deck of cards
    public void newDeck()
    {
        // clears the deck
        this.clear();
        // creates a NEW deck of 52 cards and shuffles them
        new Deck();
    }

//    public Card get(int index) {
//
//        return this.get(index);
//    }


    // helper function to print out the deck
//    public void print()
//    {
//        char[] suits = {'C', 'H', 'D', 'S'};
//        for(int i = 0; i < suits.length; i++) //for getting each suit
//        {
//            for(int j = 2; j <= 14; j++) //for getting each card value
//            {
//                System.out.println("Suit: " + suits[i] + "Value: " + [j]); //creates a new Card into ArrayList
//            }
//        }
//    }

}
