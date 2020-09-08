import java.util.ArrayList;

public class Player
{
    ArrayList<Card> hand; //will hold 3 Cards

    //types of bets
    private int anteBet; //a bet before playing or any cards are drawn from $2-$25
    private int playBet; //<-MUST BE THE SAME AS anteBet from $2-$25
    private int pairPlayBet; //a raise in the bet assuming hand is > dealer's hand

    private int totalWinnings; //number of winnings against dealer

    private int evalPPWinnings; // for evalPPWinnings

    //getters and setters
    public ArrayList<Card> getHand()
    {
        return hand;
    }
    public void setHand(ArrayList<Card> hand)
    {
        this.hand = hand;
    }
    public int getAnteBet()
    {
        return anteBet;
    }
    public void setAnteBet(int anteBet)
    {
        this.anteBet = anteBet;
    }
    public int getPairPlayBet()
    {
        return pairPlayBet;
    }
    public void setPairPlayBet(int pairPlayBet)
    {
        this.pairPlayBet = pairPlayBet;
    }
    public int getPlayBet()
    {
        return playBet;
    }
    public void setPlayBet(int playBet)
    {
        this.playBet = playBet;
    }
    public int getTotalWinnings()
    {
        return totalWinnings;
    }
    public void setTotalWinnings(int totalWinnings)
    {
        this.totalWinnings = totalWinnings;
    }
    // setter and getter for evalPPWinnings
    public int getEvalPPWinnings (){return evalPPWinnings;}
    public void setEvalPPWinnings (int winning){this.evalPPWinnings = winning;}


    // no argument constructor, initializing everything to 0
    Player()
    {
        this.anteBet = 0;
        this.playBet = 0;
        this.pairPlayBet = 0;
        this.totalWinnings = 0;
        this.evalPPWinnings = -1;
    }
}
