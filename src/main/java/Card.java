public class Card
{
    private char suit; //will hold the suit of either C, D, S, H
    private int value; //will hold 2-14, 14 being Ace and 13 King and so on

    //constructor
    Card(char suit, int value)
    {
        this.suit = suit;
        this.value = value;
    }

    //getters and setters for suit and value
    public char getSuit()
    {
        return suit;
    }
    public void setSuit(char suit)
    {
        this.suit = suit;
    }
    public int getValue()
    {
        return value;
    }
    public void setValue(int value)
    {
        this.value = value;
    }
}
