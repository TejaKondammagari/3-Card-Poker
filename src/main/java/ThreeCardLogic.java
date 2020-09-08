import java.util.ArrayList;
import java.util.Collections;

public class ThreeCardLogic
{
    //goes through the cards in ____'s hand and checks for the type of 3 card hand they have
    public static int evalHand(ArrayList<Card> hand)
    {
        //boolean values for evaluation at the end
        boolean flush = false, straight = false, pair = false, kind= false;

        //temporary ArrayList
        ArrayList<Integer> temp = new ArrayList<>();
        hand.forEach(card -> temp.add(card.getValue()));

        //this sorts the cards in order of descending for evaluating straight and straight flush
        Collections.sort(temp);

        // checks for 3 of a kind
        if(hand.get(0).getValue() == hand.get(1).getValue() &&
                hand.get(2).getValue() == hand.get(1).getValue())
            kind = true;
        // checks for pair
        if(hand.get(0).getValue() == hand.get(1).getValue() ||
                hand.get(1).getValue() == hand.get(2).getValue() ||
                hand.get(0).getValue() == hand.get(2).getValue())
            pair = true;

        // checks for flush/straight/straight flush
        // flush
        if (hand.get(0).getSuit() == hand.get(1).getSuit() &&
                hand.get(1).getSuit() == hand.get(2).getSuit())
                flush = true;
        // straight
        if (temp.get(0) == temp.get(1) - 1 && temp.get(1) == temp.get(2) - 1)
            straight = true;

        if(flush && straight)
            return 1;
        else if(kind)
            return 2;
        else if(pair)
            return 5;
        else if(straight)
            return 3;
        else if(flush)
            return 4;

        return 0;
    }

    // evaluates how much the player won based on pair plus bet, returns 0 if lost
    public static int evalPPWinnings (Player p1)
    {
        // Sending the player array of cards to evalHand
        int playWagerDecider = evalHand(p1.getHand());

        // if player wins then the following conditions need to be evaluated
        // if player got straight flush
        if (playWagerDecider == 1)
        {
            int winnings = p1.getPairPlayBet();
            winnings = winnings*40;
            return winnings;
        }
        // if player got three of a kind
        if (playWagerDecider == 2)
        {
            int winnings = p1.getPairPlayBet();
            winnings = winnings*30;
            return winnings;
        }
        // if player got a straight
        if (playWagerDecider == 3)
        {
            int winnings = p1.getPairPlayBet();
            winnings = winnings*6;
            return winnings;
        }
        // if player got a flush
        if (playWagerDecider == 4)
        {
            int winnings = p1.getPairPlayBet();
            winnings = winnings*3;
            return winnings;
        }
        // if player got a pair
        if (playWagerDecider == 5)
        {
            int winnings = p1.getPairPlayBet();
            winnings = winnings*1;
            return winnings;
        }
        // meaning player got some regular number, and hence lost the pair plus
        return 0;
    }

    // Compares both the dealers hand and the players hand to see who won
    public static int compareHands (Player p1, Dealer Deal)
    {
        //---------------------------- EVALUATION BASED ON EVAL PP WINNINGS STARTED
        // Evaluation based on evalPPWinnings
        // Assuming player has better ranking
        System.out.println("P1 EVAL PP: " + p1.getEvalPPWinnings());
        System.out.println("DEALER EVAL PP: " + Deal.getEvalPPWinnings());
        if (p1.getEvalPPWinnings() < Deal.getEvalPPWinnings())
        {
            return 1;
        }
        // Assuming dealer has better ranking
        if (p1.getEvalPPWinnings() > Deal.getEvalPPWinnings())
        {
            return 2;
        }
        //---------------------------- EVALUATION BASED ON EVAL PP WINNINGS CONCLUDED
        //---------------------------- EVALUATION BASED ON IF BOTH DEALER AND PLAYER HAVE SAME PAIR PLUS CARD
        //---------------------------- EVALUATION BASED ON HIGHEST VALUE CARD STARTED
        // if both dealer and player have high card, then we search for the highest value card
        if ((p1.getEvalPPWinnings() == Deal.getEvalPPWinnings()) && p1.getEvalPPWinnings() == 0)
        {
            // searches for the highest pair card in player's hand
            int highestCardP1 = 0;
            for (int i = 0; i < 3; i++)
            {
                if (p1.hand.get(i).getValue() > highestCardP1)
                {
                    highestCardP1 = p1.hand.get(i).getValue();
                }
            }
            // searches for the highest pair card in dealer's hand
            int highestCardDealer = 0;
            for (int i = 0; i < 3; i++)
            {
                if (Deal.dealersHand.get(i).getValue() > highestCardDealer)
                {
                    highestCardDealer = Deal.dealersHand.get(i).getValue();
                }
            }
            // means player got the highest card
            if (highestCardP1 > highestCardDealer)
            {
                return 2;
            }
            // means the dealer got the highest card
            if (highestCardP1 < highestCardDealer)
            {
                return 1;
            }
            // means both the player and the dealer have the same value card
            else
                return 0;
            //---------------------------- EVALUATION BASED ON HIGHEST VALUE CARD CONCLUDED
        }
        //---------------------------- EVALUATION BASED ON HIGHEST PAIR CARD STARTED
        if ((p1.getEvalPPWinnings() == Deal.getEvalPPWinnings()) && p1.getEvalPPWinnings() == 5)
        {
            // taking note of the pair that player has
            int highestPlayerPair = 0;
            // if the pair card for player is the first and second card
            if (p1.hand.get(0).getValue() == p1.hand.get(1).getValue())
            {
                highestPlayerPair = p1.hand.get(0).getValue();
            }
            // if the pair card for playeris the first and third card
            if (p1.hand.get(0).getValue() == p1.hand.get(2).getValue())
            {
                highestPlayerPair = p1.hand.get(0).getValue();
            }
            // if the pair card for player is second and third card
            else
                highestPlayerPair = p1.hand.get(2).getValue();

            // taking note of the pair that dealer has
            int higherDealerPair = 0;
            // if the pair card for dealer is the first and second card
            if (Deal.dealersHand.get(0).getValue() == Deal.dealersHand.get(1).getValue())
            {
                higherDealerPair = Deal.dealersHand.get(0).getValue();
            }
            // if the pair card for dealer is the first and third card
            if (Deal.dealersHand.get(0).getValue() == Deal.dealersHand.get(2).getValue())
            {
                higherDealerPair = Deal.dealersHand.get(0).getValue();
            }
            // else it means that the pair card for the player is the second and third card
            else
                higherDealerPair = Deal.dealersHand.get(2).getValue();
            // Now, we evaluate the player and the dealer pair value
            // means dealer has higher pair
            if (highestPlayerPair < higherDealerPair)
            {
                return 1;
            }
            // player has higher pair
            else if (highestPlayerPair > higherDealerPair)
            {
                return 2;
            }
            // else it means it is equal, so we need to search for the highest value card after pair
            else
            {
                // To see the highest value card for player after pair
                int highestSinglePlayerCard = 0;
                //temporary ArrayList, to sort the player's elements
                ArrayList<Integer> temp = new ArrayList<>();
                for (int i = 0; i < 3; i++)
                {
                    temp.add(p1.hand.get(i).getValue());
                }
                // sorting the elements
                Collections.sort(temp);
                // We search for the highest single player card
                if (temp.get(0) == temp.get(1))
                {
                    highestSinglePlayerCard = temp.get(2);
                }
                // else it is the first card
                else
                    highestSinglePlayerCard = temp.get(0);

                // To see the highest value card for dealer after pair
                // temporary Array List, to sort the dealer's hand
                ArrayList<Integer> temp2 = new ArrayList<>();
                // adding values into the temporary arrayList
                for (int i = 0; i < 3; i++)
                {
                    temp2.add(Deal.dealersHand.get(i).getValue());
                }
                // sorting the temporary list
                Collections.sort(temp2);
                int highestSingleDealerCard = 0;
                if (temp2.get(0) == temp2.get(1))
                {
                    highestSingleDealerCard = temp2.get(2);
                }
                // else it is the first card
                else
                    highestSingleDealerCard = temp2.get(0);

                // Now we evaluate to see if the dealer's third card is greater or the player's third card is greater
                if (highestSingleDealerCard > highestSinglePlayerCard)
                {
                    return 1;
                }
                // if the player's third card is greater than the dealer
                if (highestSingleDealerCard < highestSinglePlayerCard)
                {
                    return 2;
                }
                // if they are the same
                if (highestSingleDealerCard == highestSinglePlayerCard)
                {
                    return 0;
                }
            }
        }
        //---------------------------- EVALUATION BASED ON HIGHEST PAIR CARD CONCLUDED
        //---------------------------- EVALUATION BASED ON FLUSH STARTED
        // evaluation occurs based on flush, means both have flush
        if ((p1.getEvalPPWinnings() == Deal.getEvalPPWinnings()) && p1.getEvalPPWinnings() == 4)
        {
            return 0;
        }
        //---------------------------- EVALUATION BASED ON FLUSH ENDED
        //---------------------------- EVALUATION BASED ON STRAIGHT STARTED
        if ((p1.getEvalPPWinnings() == Deal.getEvalPPWinnings()) && p1.getEvalPPWinnings() == 3)
        {
            int highestThirdPlayerCard = 0;
            //temporary ArrayList, to sort the player's elements
            ArrayList<Integer> temp = new ArrayList<>();
            for (int i = 0; i < 3; i++)
            {
                temp.add(p1.hand.get(i).getValue());
            }
            // sorting the elements
            Collections.sort(temp);
            // getting the third card from the player's hand
            highestThirdPlayerCard = temp.get(2);

            // organizing dealer's hand into temp array to sort them
            ArrayList<Integer> temp2 = new ArrayList<>();
            // adding values into the temporary arrayList
            for (int i = 0; i < 3; i++)
            {
                temp2.add(Deal.dealersHand.get(i).getValue());
            }
            // sorting the temporary list
            Collections.sort(temp2);
            int highestThirdDealerCard = temp2.get(2);
            // if dealer has the third highest card
            if (highestThirdDealerCard > highestThirdPlayerCard)
            {
                return 1;
            }
            // if player has the third highest card
            if (highestThirdDealerCard < highestThirdPlayerCard)
            {
                return 2;
            }
            else
                return 0;
        }
        //---------------------------- EVALUATION BASED ON STRAIGHT ENDED
        //---------------------------- EVALUATION BASED ON THREE OF A KIND
        if ((p1.getEvalPPWinnings() == Deal.getEvalPPWinnings()) && p1.getEvalPPWinnings() == 2)
        {
            // means player's straight is better
            if (p1.hand.get(0).getValue() > Deal.dealersHand.get(0).getValue())
            {
                return 2;
            }
            // means dealer's straight is better
            if (p1.hand.get(0).getValue() < Deal.dealersHand.get(0).getValue())
            {
                return 1;
            }
            // means they both have the same value
            else
                return 0;
        }
        //---------------------------- EVALUATION BASED ON THREE OF A KIND
        //---------------------------- EVALUATION BASED ON STRAIGHT FLUSH
        if ((p1.getEvalPPWinnings() == Deal.getEvalPPWinnings()) && p1.getEvalPPWinnings() == 1)
        {
            // means player's straight is better
            if (p1.hand.get(0).getValue() > Deal.dealersHand.get(0).getValue())
            {
                return 2;
            }
            // means dealer's straight is better
            if (p1.hand.get(0).getValue() < Deal.dealersHand.get(0).getValue())
            {
                return 1;
            }
            // means they both have the same value
            else
                return 0;
        }

        return 0;
    }

    // Evaluate if dealer qualifies
    public static int dealerQualify (Dealer Deal)
    {
        for (int i = 0; i < 3; i++)
        {
            // sees if the dealer qualifies to play
            if (Deal.dealersHand.get(i).getValue() >= 12)
            {
                return 1;
            }
        }
        return 0;
    }


}
