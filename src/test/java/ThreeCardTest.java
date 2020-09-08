import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;

class ThreeCardTest {

	private Dealer dealer;
	private Player player;

	@BeforeEach
	public void init()
	{
		dealer = new Dealer();
		player = new Player();
	}

	@Test
	public void playerWinningsIncrement()
	{
		player.setTotalWinnings(3);
		player.setTotalWinnings(player.getTotalWinnings()+1);
		assertEquals(4, player.getTotalWinnings(), "totalWinnings() doesn't increment properly ");
	}
	@Test
	public void playerWinningsDecrement()
	{
		player.setTotalWinnings(3);
		player.setTotalWinnings(player.getTotalWinnings()-1);
		assertEquals(2, player.getTotalWinnings(), "totalWinnings() doesn't decrement properly ");
	}
	@Test
	public void playerGets3Cards()
	{
		dealer.dealHand();
		dealer.DrawCards(player);
		assertEquals(3, player.getHand().size(), "player doesn't get 3 cards");
	}
	@Test
	public void dealerGets3Cards()
	{
		dealer.dealHand();
		assertEquals(3, dealer.dealersHand.size(), "player doesn't get 3 cards");
	}

	@Test
	public void testDealerInit()
	{
		assertEquals("Dealer", dealer.getClass().getName(), "init of Dealer doesn't work");
	}
	@Test
	public void testPlayerInit()
	{
		assertEquals("Player", player.getClass().getName(), "init of Player doesn't work");
	}
	@Test
	public void dealerAndPlayerHaveDifferentFirstCard()
	{
		dealer.dealHand();
		dealer.DrawCards(player);
		String dealerCard = ""+dealer.dealersHand.get(0).getSuit() + dealer.dealersHand.get(0).getValue();
		String playerCard = ""+player.getHand().get(0).getSuit() + player.getHand().get(0).getValue();
		assertNotEquals(dealerCard, playerCard, "dealer and player have the same card");
	}
	@Test
	public void dealerAndPlayerHaveDifferentSecondCard()
	{
		dealer.dealHand();
		dealer.DrawCards(player);
		String dealerCard = ""+dealer.dealersHand.get(1).getSuit() + dealer.dealersHand.get(1).getValue();
		String playerCard = ""+player.getHand().get(1).getSuit() + player.getHand().get(1).getValue();
		assertNotEquals(dealerCard, playerCard, "dealer and player have the same card");
	}
	@Test
	public void dealerAndPlayerHaveDifferentThirdCard()
	{
		dealer.dealHand();
		dealer.DrawCards(player);
		String dealerCard = ""+dealer.dealersHand.get(2).getSuit() + dealer.dealersHand.get(2).getValue();
		String playerCard = ""+player.getHand().get(2).getSuit() + player.getHand().get(2).getValue();
		assertNotEquals(dealerCard, playerCard, "dealer and player have the same card");
	}
	@Test
	public void dealerGetsNewHand()
	{
		dealer.dealHand();
		ArrayList<Card> oldHand = dealer.dealersHand;
		dealer.dealHand();
		ArrayList<Card> newHand = dealer.dealersHand;

		String o_dealerCard = ""+oldHand.get(0).getSuit() + oldHand.get(0).getValue();
		String n_dealerCard = ""+newHand.get(0).getSuit() + newHand.get(0).getValue();
		assertNotEquals(o_dealerCard, n_dealerCard, "dealer and player have the same card");
	}
	@Test
	public void straightHandTest()
	{
		ArrayList<Card> straightHand = new ArrayList<Card>();
		straightHand.add(new Card('C', 2));
		straightHand.add(new Card('H', 3));
		straightHand.add(new Card('D', 4));

		dealer.dealersHand = straightHand;
		assertEquals(3, ThreeCardLogic.evalHand(dealer.dealersHand), "dealer doesn't have a straight");
	}
	@Test
	public void threeOfAKindHandTest()
	{
		ArrayList<Card> threeHand = new ArrayList<Card>();
		threeHand.add(new Card('C', 2));
		threeHand.add(new Card('H', 2));
		threeHand.add(new Card('D', 2));

		dealer.dealersHand = threeHand;
		assertEquals(2, ThreeCardLogic.evalHand(dealer.dealersHand), "dealer doesn't have a three of a kind");
	}
	@Test
	public void flushHandTest()
	{
		ArrayList<Card> flushHand = new ArrayList<Card>();
		flushHand.add(new Card('C', 2));
		flushHand.add(new Card('C', 7));
		flushHand.add(new Card('C', 4));

		dealer.dealersHand = flushHand;
		assertEquals(4, ThreeCardLogic.evalHand(dealer.dealersHand), "dealer doesn't have a flush");
	}
	@Test
	public void straightFlushHandTest()
	{
		ArrayList<Card> straightFlushHand = new ArrayList<Card>();
		straightFlushHand.add(new Card('C', 2));
		straightFlushHand.add(new Card('C', 3));
		straightFlushHand.add(new Card('C', 4));

		dealer.dealersHand = straightFlushHand;
		assertEquals(1, ThreeCardLogic.evalHand(dealer.dealersHand), "dealer doesn't have a straight flush");
	}
	@Test
	public void pairHandTest()
	{
		ArrayList<Card> pairHand = new ArrayList<Card>();
		pairHand.add(new Card('C', 2));
		pairHand.add(new Card('H', 2));
		pairHand.add(new Card('D', 4));

		dealer.dealersHand = pairHand;
		assertEquals(5, ThreeCardLogic.evalHand(dealer.dealersHand), "dealer doesn't have a pair");
	}
	@Test
	public void n_straightHandTest()
	{
		ArrayList<Card> straightHand = new ArrayList<Card>();
		straightHand.add(new Card('C', 2));
		straightHand.add(new Card('H', 3));
		straightHand.add(new Card('D', 4));

		dealer.dealersHand = straightHand;
		assertNotEquals(1, ThreeCardLogic.evalHand(dealer.dealersHand), "dealer doesn't have a straight");
	}
	@Test
	public void n_threeOfAKindHandTest()
	{
		ArrayList<Card> threeHand = new ArrayList<Card>();
		threeHand.add(new Card('C', 2));
		threeHand.add(new Card('H', 2));
		threeHand.add(new Card('D', 2));

		dealer.dealersHand = threeHand;
		assertNotEquals(0, ThreeCardLogic.evalHand(dealer.dealersHand), "dealer doesn't have a three of a kind");
	}
	@Test
	public void n_flushHandTest()
	{
		ArrayList<Card> flushHand = new ArrayList<Card>();
		flushHand.add(new Card('C', 2));
		flushHand.add(new Card('C', 7));
		flushHand.add(new Card('C', 4));

		dealer.dealersHand = flushHand;
		assertNotEquals(2, ThreeCardLogic.evalHand(dealer.dealersHand), "dealer doesn't have a flush");
	}
	@Test
	public void n_straightFlushHandTest()
	{
		ArrayList<Card> straightFlushHand = new ArrayList<Card>();
		straightFlushHand.add(new Card('C', 2));
		straightFlushHand.add(new Card('C', 3));
		straightFlushHand.add(new Card('C', 4));

		dealer.dealersHand = straightFlushHand;
		assertNotEquals(5, ThreeCardLogic.evalHand(dealer.dealersHand), "dealer doesn't have a straight flush");
	}
	@Test
	public void n_pairHandTest()
	{
		ArrayList<Card> pairHand = new ArrayList<Card>();
		pairHand.add(new Card('C', 2));
		pairHand.add(new Card('H', 2));
		pairHand.add(new Card('D', 4));

		dealer.dealersHand = pairHand;
		assertNotEquals(3, ThreeCardLogic.evalHand(dealer.dealersHand), "dealer doesn't have a pair");
	}
	@Test
	public void evalPP_straightHandTest()
	{
		ArrayList<Card> straightHand = new ArrayList<Card>();
		straightHand.add(new Card('C', 2));
		straightHand.add(new Card('H', 3));
		straightHand.add(new Card('D', 4));

		player.setHand(straightHand);
		player.setPairPlayBet(1);
		assertEquals(6, ThreeCardLogic.evalPPWinnings(player),"evalPPWinnings calculation for pair are wrong");
	}
	@Test
	public void evalPP_threeOfAKindHandTest()
	{
		ArrayList<Card> threeHand = new ArrayList<Card>();
		threeHand.add(new Card('C', 2));
		threeHand.add(new Card('H', 2));
		threeHand.add(new Card('D', 2));

		player.setHand(threeHand);
		player.setPairPlayBet(1);
		assertEquals(30, ThreeCardLogic.evalPPWinnings(player), "evalPPWinnings calculation for pair are wrong");
	}
	@Test
	public void evalPP_flushHandTest()
	{
		ArrayList<Card> flushHand = new ArrayList<Card>();
		flushHand.add(new Card('C', 2));
		flushHand.add(new Card('C', 7));
		flushHand.add(new Card('C', 4));

		player.setHand(flushHand);
		player.setPairPlayBet(1);
		assertEquals(3, ThreeCardLogic.evalPPWinnings(player), "evalPPWinnings calculation for pair are wrong");
	}
	@Test
	public void evalPP_straightFlushHandTest()
	{
		ArrayList<Card> straightFlushHand = new ArrayList<Card>();
		straightFlushHand.add(new Card('C', 2));
		straightFlushHand.add(new Card('C', 3));
		straightFlushHand.add(new Card('C', 4));

		player.setHand(straightFlushHand);
		player.setPairPlayBet(1);
		assertEquals(40, ThreeCardLogic.evalPPWinnings(player), "evalPPWinnings calculation for pair are wrong");
	}
	@Test
	public void evalPP_pairHandTest()
	{
		ArrayList<Card> pairHand = new ArrayList<Card>();
		pairHand.add(new Card('C', 2));
		pairHand.add(new Card('H', 2));
		pairHand.add(new Card('D', 4));

		player.setHand(pairHand);
		player.setPairPlayBet(1);
		assertEquals(1, ThreeCardLogic.evalPPWinnings(player), "evalPPWinnings calculation for pair are wrong");
	}
	@Test
	public void n_evalPP_straightHandTest()
	{
		ArrayList<Card> straightHand = new ArrayList<Card>();
		straightHand.add(new Card('C', 2));
		straightHand.add(new Card('H', 3));
		straightHand.add(new Card('D', 4));

		player.setHand(straightHand);
		player.setPairPlayBet(1);
		assertNotEquals(631, ThreeCardLogic.evalPPWinnings(player),"evalPPWinnings calculation for pair are wrong");
	}
	@Test
	public void n_evalPP_threeOfAKindHandTest()
	{
		ArrayList<Card> threeHand = new ArrayList<Card>();
		threeHand.add(new Card('C', 2));
		threeHand.add(new Card('H', 2));
		threeHand.add(new Card('D', 2));

		player.setHand(threeHand);
		player.setPairPlayBet(1);
		assertNotEquals(320, ThreeCardLogic.evalPPWinnings(player), "evalPPWinnings calculation for three of a kind are wrong");
	}
	@Test
	public void n_evalPP_flushHandTest()
	{
		ArrayList<Card> flushHand = new ArrayList<Card>();
		flushHand.add(new Card('C', 2));
		flushHand.add(new Card('C', 7));
		flushHand.add(new Card('C', 4));

		player.setHand(flushHand);
		player.setPairPlayBet(1);
		assertNotEquals(321, ThreeCardLogic.evalPPWinnings(player), "evalPPWinnings calculation for flush are wrong");
	}
	@Test
	public void n_evalPP_straightFlushHandTest()
	{
		ArrayList<Card> straightFlushHand = new ArrayList<Card>();
		straightFlushHand.add(new Card('C', 2));
		straightFlushHand.add(new Card('C', 3));
		straightFlushHand.add(new Card('C', 4));

		player.setHand(straightFlushHand);
		player.setPairPlayBet(1);
		assertNotEquals(404, ThreeCardLogic.evalPPWinnings(player), "evalPPWinnings calculation for straight flush are wrong");
	}
	@Test
	public void n_evalPP_pairHandTest()
	{
		ArrayList<Card> pairHand = new ArrayList<Card>();
		pairHand.add(new Card('C', 2));
		pairHand.add(new Card('H', 2));
		pairHand.add(new Card('D', 4));

		player.setHand(pairHand);
		player.setPairPlayBet(1);
		assertNotEquals(456, ThreeCardLogic.evalPPWinnings(player), "evalPPWinnings calculation for pair are wrong");
	}
}
