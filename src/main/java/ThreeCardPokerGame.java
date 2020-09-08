import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.awt.*;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.text.*;
import javafx.scene.layout.BorderPane;

public class ThreeCardPokerGame extends Application
{
	//general elements of the GUI
	private Text announcement;
	private Button startBtn;
	private HBox announceBox;
	private VBox menuBox;
	private MenuBar menuBar;
	private HBox bottomForm;
	private HBox p1Box;
	private HBox p2Box;
	private HBox dealBox;
	private HBox anteBox;
	private HBox pairBox;
	private HBox playBox;
	private HBox totalBox;
	private Text anteTxt;
	private Text pairTxt;
	private Text playTxt;
	private Text totalTxt;
	private BorderPane rootPane;

	//for dealer
	private HBox dealerCards;
	private Dealer dealer;
	private Text dealerTxt;


	//for player1
	private Player playerOne;
	private Text p1Txt;
	private HBox playerOneCards;
	private VBox playerOnePane;
	private TextField ante1;
	private TextField pairPlus1;
	private TextField playWager1;
	private Text totalEarn1;
	private Button foldBtn1;
	private Button playBtn1;
	private boolean p1Fold = false;

	//for player2
	private Player playerTwo;
	private Text p2Txt;
	private HBox playerTwoCards;
	private VBox playerTwoPane;
	private TextField ante2;
	private TextField pairPlus2;
	private TextField playWager2;
	private Text totalEarn2;
	private Button foldBtn2;
	private Button playBtn2;
	private boolean p2Fold = false;

	//images
	ImageView dealCard1;
	ImageView dealCard2;
	ImageView dealCard3;
	ImageView play1Card1;
	ImageView play1Card2;
	ImageView play1Card3;
	ImageView play2Card1;
	ImageView play2Card2;
	ImageView play2Card3;
	static String backOfCard = new String("blue_back.png");
	static Font currentFont = new Font("Comic Sans MC", 20);
	int formLook = 0;

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		dealer = new Dealer();
		playerOne = new Player();
		playerTwo = new Player();

		final int buttonSizeWidth = 70;
		final int buttonSizeHeight = 50;
		final int textFieldWidth = 50;
		final int textFieldHeight = 50;
		final int textFieldFontSize = 17;
		int cardSize = 150;

		//for the options
		menuBar = new MenuBar();
		Menu mOne = new Menu("Options");

		MenuItem exitOp = new MenuItem("Exit"); //should exit game
		MenuItem freshStart = new MenuItem("Fresh Start"); //gives a fresh start of the program and resets each players winnings
		MenuItem newLook = new MenuItem("New Look"); //changes the look of the board

		exitOp.setOnAction(e->primaryStage.close());

		freshStart.setOnAction(e->{
			restartGame();
		});

		newLook.setOnAction(e->{
			if(formLook % 2 == 0)
			{
				formLook++;
				rootPane.setStyle("-fx-background-color: #696969");
				backOfCard = "green_back.png";
			}
			else
			{
				formLook++;
				rootPane.setStyle("-fx-background-color: darkred");
				backOfCard = "blue_back.png";
			}

		});

		//on the top of the GUI
		primaryStage.setTitle("Let's Play Three Card Poker!!!");

		//for the announcement
		announcement = new Text();
		announcement.setFont(currentFont);
		announcement.setTextAlignment(TextAlignment.CENTER);
		announcement.setText("Press the Start button to play");

		//for dealer
		dealerTxt = new Text();
		dealerTxt.setFont(currentFont);
		dealerTxt.setTextAlignment(TextAlignment.CENTER);
		dealerTxt.setText("Dealer");

		//for player 1
		p1Txt = new Text();
		p1Txt.setFont(currentFont);
		p1Txt.setTextAlignment(TextAlignment.CENTER);
		p1Txt.setText("Player 1");

		//for player 2
		p2Txt = new Text();
		p2Txt.setFont(currentFont);
		p2Txt.setTextAlignment(TextAlignment.CENTER);
		p2Txt.setText("Player 2");

		//for text in the middle of the screen
		anteTxt = new Text();
		anteTxt.setFont(currentFont);
		anteTxt.setTextAlignment(TextAlignment.CENTER);
		anteTxt.setText("Ante Bet");

		pairTxt = new Text();
		pairTxt.setFont(currentFont);
		pairTxt.setTextAlignment(TextAlignment.CENTER);
		pairTxt.setText("Pair Plus Bet");

		playTxt = new Text();
		playTxt.setFont(currentFont);
		playTxt.setTextAlignment(TextAlignment.CENTER);
		playTxt.setText("Play Wager");

		totalTxt = new Text();
		totalTxt.setFont(currentFont);
		totalTxt.setTextAlignment(TextAlignment.CENTER);
		totalTxt.setText("Total Earnings");


		//buttons that go on the bottom of the screen
		startBtn = new Button("Start");
		foldBtn1 = new Button("Fold");
		playBtn1 = new Button("Play");
		foldBtn2 = new Button("Fold");
		playBtn2 = new Button("Play");

		startBtn.setMinSize(buttonSizeWidth,buttonSizeHeight);
		foldBtn1.setMinSize(buttonSizeWidth,buttonSizeHeight);
		playBtn1.setMinSize(buttonSizeWidth,buttonSizeHeight);
		foldBtn2.setMinSize(buttonSizeWidth,buttonSizeHeight);
		playBtn2.setMinSize(buttonSizeWidth,buttonSizeHeight);

		//player one things
		ante1 = new TextField();
		pairPlus1 = new TextField();
		playWager1 = new TextField();
		totalEarn1 = new Text();

		ante1.setMinSize(textFieldWidth,textFieldHeight);
		pairPlus1.setMinSize(textFieldWidth,textFieldHeight);
		playWager1.setMinSize(textFieldWidth,textFieldHeight);
		totalEarn1.setFont(new Font(textFieldFontSize));
		ante1.setFont(new Font(textFieldFontSize));
		pairPlus1.setFont(new Font(textFieldFontSize));
		playWager1.setFont(new Font(textFieldFontSize));
		totalEarn1.setFont(new Font(textFieldFontSize));
		totalEarn1.setText("$0");

		//player two things
		ante2 = new TextField();
		pairPlus2 = new TextField();
		playWager2 = new TextField();
		totalEarn2 = new Text();

		ante2.setMinSize(textFieldWidth,textFieldHeight);
		pairPlus2.setMinSize(textFieldWidth,textFieldHeight);
		playWager2.setMinSize(textFieldWidth,textFieldHeight);
		totalEarn2.setFont(new Font(textFieldFontSize));
		totalEarn2.setText("$0");

		ante2.setFont(new Font(textFieldFontSize));
		pairPlus2.setFont(new Font(textFieldFontSize));
		playWager2.setFont(new Font(textFieldFontSize));
		totalEarn2.setFont(new Font(textFieldFontSize));

		startBtn.setStyle("-fx-background-color: green;");
		foldBtn1.setStyle("-fx-background-color: gray;");
		foldBtn2.setStyle("-fx-background-color: gray;");
		playBtn1.setStyle("-fx-background-color: gray;");
		playBtn2.setStyle("-fx-background-color: gray;");

		foldBtn1.setDisable(true);
		foldBtn2.setDisable(true);
		playBtn1.setDisable(true);
		playBtn2.setDisable(true);

		ante1.setEditable(false);
		ante2.setEditable(false);
		pairPlus1.setEditable(false);
		pairPlus2.setEditable(false);
		playWager1.setEditable(false);
		playWager2.setEditable(false);

		//iTwo.setOnAction(e->{	/*"resets" the game*/		});

	// START/CONTINUE BUTTON
		startBtn.setOnAction(e->{
			startBtn.setStyle("-fx-background-color: gray;");
			foldBtn1.setStyle("-fx-background-color: gray;");
			foldBtn2.setStyle("-fx-background-color: gray;");
			playBtn1.setStyle("-fx-background-color: gray;");
			playBtn2.setStyle("-fx-background-color: gray;");

			ante1.setText("");
			pairPlus1.setText("");
			playWager1.setText("");

			ante2.setText("");
			pairPlus2.setText("");;
			playWager2.setText("");

			startBtn.setDisable(true);
			foldBtn1.setDisable(true);
			foldBtn2.setDisable(true);
			playBtn1.setDisable(true);
			playBtn2.setDisable(true);

			//for dealer's cards
			Image dealCardImage = new Image(backOfCard);
			dealCard1 = new ImageView(dealCardImage);

			dealCard1.setFitHeight(cardSize);
			dealCard1.setFitWidth(cardSize);
			dealCard1.setPreserveRatio(true);

			Image dealCardImage2 = new Image(backOfCard);
			dealCard2 = new ImageView(dealCardImage2);

			dealCard2.setFitHeight(cardSize);
			dealCard2.setFitWidth(cardSize);
			dealCard2.setPreserveRatio(true);

			Image dealCardImage3 = new Image(backOfCard);
			dealCard3 = new ImageView(dealCardImage3);

			dealCard3.setFitHeight(cardSize);
			dealCard3.setFitWidth(cardSize);
			dealCard3.setPreserveRatio(true);

			//player1's cards
			Image play1CardImage = new Image(backOfCard);
			play1Card1 = new ImageView(play1CardImage);

			play1Card1.setFitHeight(cardSize);
			play1Card1.setFitWidth(cardSize);
			play1Card1.setPreserveRatio(true);

			Image play1CardImage2 = new Image(backOfCard);
			play1Card2 = new ImageView(play1CardImage2);

			play1Card2.setFitHeight(cardSize);
			play1Card2.setFitWidth(cardSize);
			play1Card2.setPreserveRatio(true);

			Image play1CardImage3 = new Image(backOfCard);
			play1Card3 = new ImageView(play1CardImage3);

			play1Card3.setFitHeight(cardSize);
			play1Card3.setFitWidth(cardSize);
			play1Card3.setPreserveRatio(true);

			//player 2's cards
			Image play2CardImage = new Image(backOfCard);
			play2Card1 = new ImageView(play2CardImage);

			play2Card1.setFitHeight(cardSize);
			play2Card1.setFitWidth(cardSize);
			play2Card1.setPreserveRatio(true);

			Image play2CardImage2 = new Image(backOfCard);
			play2Card2 = new ImageView(play2CardImage2);

			play2Card2.setFitHeight(cardSize);
			play2Card2.setFitWidth(cardSize);
			play2Card2.setPreserveRatio(true);

			Image play2CardImage3 = new Image(backOfCard);
			play2Card3 = new ImageView(play2CardImage3);

			play2Card3.setFitHeight(cardSize);
			play2Card3.setFitWidth(cardSize);
			play2Card3.setPreserveRatio(true);

			dealerCards = new HBox(20, dealCard1, dealCard2, dealCard3);

			playerOneCards = new HBox(20, play1Card1, play1Card2, play1Card3);
			playerTwoCards = new HBox(20, play2Card1, play2Card2, play2Card3);

			dealerCards.setLayoutX(475);
			dealerCards.setLayoutY(100);
			playerOneCards.setLayoutX(50);
			playerOneCards.setLayoutY(300);
			playerTwoCards.setLayoutX(880);
			playerTwoCards.setLayoutY(300);

			rootPane.getChildren().addAll(dealerCards, playerOneCards, playerTwoCards);

			startGame();
		});

	//PLAYER ONE BUTTONS AND FUNCTIONALITY
		foldBtn1.setOnAction(e->{//player1 decides to fold
			foldBtn1.setDisable(true);
			foldBtn2.setDisable(false);
			playBtn1.setDisable(true);
			playBtn2.setDisable(false);

			foldBtn1.setStyle("-fx-background-color: red;");
			playBtn1.setStyle("-fx-background-color: gray;");

			announcement.setText("Player One Folds. Player Two choose Play or Fold");
			p1Fold = true;
		});

		playBtn1.setOnAction(e->{//player1 decides to play
			foldBtn1.setDisable(true);
			foldBtn2.setDisable(false);
			playBtn1.setDisable(true);
			playBtn2.setDisable(false);

			foldBtn1.setStyle("-fx-background-color: gray;");
			playBtn1.setStyle("-fx-background-color: red;");

			p1Fold = false;

			announcement.setText("Player One Plays. Player Two choose Play or Fold");
		});

		//for ante bet for playerOne
		ante1.setOnKeyPressed(e->{if(e.getCode().equals(KeyCode.ENTER)){
				playerOne.setAnteBet((Integer.parseInt(ante1.getText())));
				//System.out.println("P1 Ante: "+ playerOne.getAnteBet());
				announcement.setText("Player Two: Please place Ante bet and press ENTER once finished (this also places play wager)");

				playWager1.setText(ante1.getText());

				playerOne.setPlayBet((Integer.parseInt(playWager1.getText())));

				ante1.setEditable(false);
				ante2.setEditable(true);
				pairPlus1.setEditable(false);
				pairPlus2.setEditable(false);
				playWager1.setEditable(false);
				playWager2.setEditable(false);
			}
		});

		//for pair plus for playerOne
		pairPlus1.setOnKeyPressed(e->{if(e.getCode().equals(KeyCode.ENTER)){
			playerOne.setPairPlayBet((Integer.parseInt(pairPlus1.getText())));
			//System.out.println("P1 Ante: "+ playerOne.getAnteBet());
			announcement.setText("Player Two, place pair plus bet (optional) press ENTER to place or put 0 then ENTER to continue");
			ante1.setEditable(false);
			ante2.setEditable(false);
			pairPlus1.setEditable(false);
			pairPlus2.setEditable(true);
			playWager1.setEditable(false);
			playWager2.setEditable(false);
		}
		});

	//PLAYER 2 BUTTONS AND FUNCTIONALITY
		foldBtn2.setOnAction(e->{//player 2 decides to fold
			foldBtn2.setDisable(true);
			playBtn2.setDisable(true);

			foldBtn2.setStyle("-fx-background-color: red;");
			playBtn2.setStyle("-fx-background-color: gray;");

			announcement.setText("Player Two Folds. Dealer show hand.");
			showDealerCards(dealer);

			// Checks if dealer qualifies
			int qualifyOne = ThreeCardLogic.dealerQualify(dealer);
			int dealerEvalPP;
			boolean dealerQualify = true;
			if (qualifyOne != 1)
			{
				dealerQualify = false;
			}
			else
			{
				dealerEvalPP = ThreeCardLogic.evalHand(dealer.dealersHand); // the type of cards dealer has
			}
			int playerOneEvalPP = ThreeCardLogic.evalHand(playerOne.getHand());
			int p1Winnings = playerOne.getAnteBet() + playerOne.getPlayBet() + (playerOne.getPairPlayBet() * playerOneEvalPP);
			int playerTwoEvalPP = ThreeCardLogic.evalHand(playerTwo.getHand());
			int p2Winnings = playerTwo.getPlayBet() + playerTwo.getPlayBet() + (playerTwo.getPairPlayBet()*playerTwoEvalPP);
			String output = new String();

			// players 1 and 2 win while dealer loses
			if (dealerQualify == false)
			{
				// Calculations + total earnings of playerOne
				if(p1Fold == false) //playerOne played but playerTwo folded
				{
					playerOne.setTotalWinnings(playerOne.getTotalWinnings() + p1Winnings);
				}
				else
				{
					// this means playerOne folded
					playerOne.setTotalWinnings(playerOne.getTotalWinnings() - p1Winnings);
				}
				// Calculations + total earnings of playerTwo
				playerTwo.setTotalWinnings(playerTwo.getTotalWinnings() - p2Winnings);

				output += "Dealer does not qualify P1 has: " + playerOne.getTotalWinnings() + " P2 has: " + playerTwo.getTotalWinnings();
			}
			// means dealer qualifies
			else
			{
				// if playerOne played
				if (p1Fold == false) // playerOne decided to play
				{
					int deciderOne = ThreeCardLogic.compareHands(playerOne, dealer);
					if (deciderOne == 0)
					{
						// no one wins
						output += "P1 v. Dealer: No one wins";
					}
					else if (deciderOne == 1)
					{
						// dealer wins
						output += "P1 v. Dealer: Dealer wins";
						playerOne.setTotalWinnings(playerOne.getTotalWinnings() - p1Winnings);
					}
					else if (deciderOne == 2)
					{
						// player wins
						output += "P1 v. Dealer: Player One wins";
						playerOne.setTotalWinnings(playerOne.getTotalWinnings() + p1Winnings);
					}
				}
				else
				{
					output += "P1 v. Dealer: Player One folded. Dealer wins.";
					playerOne.setTotalWinnings(playerOne.getTotalWinnings() - p1Winnings);
				}
				output += " | P2 v. Dealer: Player Two folded. Dealer wins.";
				playerTwo.setTotalWinnings(playerTwo.getTotalWinnings() - p2Winnings);
			}

			totalEarn1.setText("$"+playerOne.getTotalWinnings());
			totalEarn2.setText("$"+playerTwo.getTotalWinnings());
			announcement.setText(output);

			startBtn.setDisable(false);
			startBtn.setText("Continue");
			startBtn.setStyle("-fx-background-color: green;");
			foldBtn1.setStyle("-fx-background-color: gray;");
			foldBtn2.setStyle("-fx-background-color: gray;");
			playBtn1.setStyle("-fx-background-color: gray;");
			playBtn2.setStyle("-fx-background-color: gray;");

		});

		playBtn2.setOnAction(e->{ //player2 wants to play
			foldBtn2.setDisable(true);
			playBtn2.setDisable(true);

			foldBtn2.setStyle("-fx-background-color: gray;");
			playBtn2.setStyle("-fx-background-color: red;");

			announcement.setText("Player Two Plays. Dealer show hand.");
			showDealerCards(dealer);
			// Checks if dealer qualifies
			int qualifyOne = ThreeCardLogic.dealerQualify(dealer);
			System.out.println("Qualify: " + qualifyOne);
			int dealerEvalPP;
			boolean dealerQualify = true;
			if (qualifyOne != 1)
			{
				dealerQualify = false;
			}
			else
			{
				dealerEvalPP = ThreeCardLogic.evalHand(dealer.dealersHand); // the type of cards dealer has
				dealer.setEvalPPWinnings(dealerEvalPP);
				System.out.println("Dealer Eval PP: " + dealerEvalPP);
			}
			int playerOneEvalPP = ThreeCardLogic.evalHand(playerOne.getHand());
			playerOne.setEvalPPWinnings(playerOneEvalPP);
			System.out.println("PlayerOne Eval PP: " + playerOneEvalPP);
			int p1Winnings = playerOne.getAnteBet() + playerOne.getPlayBet() + (playerOne.getPairPlayBet() * playerOneEvalPP);
			int playerTwoEvalPP = ThreeCardLogic.evalHand(playerTwo.getHand());
			playerTwo.setEvalPPWinnings(playerTwoEvalPP);
			System.out.println("PlayerTwo Eval PP: " + playerTwoEvalPP);
			int p2Winnings = playerTwo.getAnteBet() + playerTwo.getPlayBet() + (playerTwo.getPairPlayBet()*playerTwoEvalPP);
			String output = new String();
			// players 1 and 2 win while dealer loses
			if (dealerQualify == false)
			{
				output+= "Dealer does not qualify | ";
				// Calculations + total earnings of playerOne
				if(p1Fold == false) //playerOne played and playerTwo played
				{
					playerOne.setTotalWinnings(playerOne.getTotalWinnings() + p1Winnings);
					output += "Player 1 wins | ";
				}
				else
				{
					// this means playerOne folded
					playerOne.setTotalWinnings(playerOne.getTotalWinnings() - p1Winnings);
				}
				// Calculations + total earnings of playerTwo
				playerTwo.setTotalWinnings(playerTwo.getTotalWinnings() + p2Winnings);
				System.out.println("Player2 total winnings: " + playerTwo.getTotalWinnings());
				output += "Player 2 wins";
			}
			// means dealer qualifies
			else
			{
				// if playerOne
				if (p1Fold == false) // playerOne decided to play
				{
					int deciderOne = ThreeCardLogic.compareHands(playerOne, dealer);
					System.out.println("Decider One value: " + deciderOne);
					if (deciderOne == 0)
					{
						// no one wins
						output += "P1 v. Dealer: No one wins";
					}
					else if (deciderOne == 1)
					{
						// dealer wins
						output += "P1 v. Dealer: Dealer wins";
						playerOne.setTotalWinnings(playerOne.getTotalWinnings() - p1Winnings);
					}
					else if (deciderOne == 2)
					{
						// player wins
						output += "P1 v. Dealer: Player One wins";
						playerOne.setTotalWinnings(playerOne.getTotalWinnings() + p1Winnings);
					}
				}
				else
				{
					output += "P1 v. Dealer: Player One folded. Dealer wins.";
					playerOne.setTotalWinnings(playerOne.getTotalWinnings() - p1Winnings);
				}

				int deciderTwo = ThreeCardLogic.compareHands(playerTwo, dealer);
				System.out.println("Decider One value: " + deciderTwo);
				if (deciderTwo == 0)
				{
					// no one wins
					output += " | P2 v. Dealer: No one wins";
				}
				else if (deciderTwo == 1)
				{
					// dealer wins
					output += " | P2 v. Dealer: Dealer wins";
					playerTwo.setTotalWinnings(playerTwo.getTotalWinnings() - p2Winnings);
				}
				else if (deciderTwo == 2)
				{
					// player wins
					output += " | P2 v. Dealer: Player Two wins";
					playerTwo.setTotalWinnings(playerTwo.getTotalWinnings() + p2Winnings);
				}
			}
			totalEarn1.setText("$"+playerOne.getTotalWinnings());
			totalEarn2.setText("$"+playerTwo.getTotalWinnings());
			announcement.setText(output);

			startBtn.setDisable(false);
			startBtn.setText("Continue");
			startBtn.setStyle("-fx-background-color: green;");
			foldBtn1.setStyle("-fx-background-color: gray;");
			foldBtn2.setStyle("-fx-background-color: gray;");
			playBtn1.setStyle("-fx-background-color: gray;");
			playBtn2.setStyle("-fx-background-color: gray;");
		});

		//for ante bet for playerTwo
		ante2.setOnKeyPressed(e->{if(e.getCode().equals(KeyCode.ENTER)){
				playerTwo.setAnteBet((Integer.parseInt(ante2.getText())));
				//System.out.println("P2 Ante: "+ playerTwo.getAnteBet());

				announcement.setText("Player One, place pair plus bet (optional) press ENTER to place or put 0 then ENTER to continue");

				playWager2.setText(ante2.getText());

				playerTwo.setPlayBet((Integer.parseInt(playWager2.getText())));

				ante1.setEditable(false);
				ante2.setEditable(false);
				pairPlus1.setEditable(true);
				pairPlus2.setEditable(false);
				playWager1.setEditable(false);
				playWager2.setEditable(false);
			}
		});

		//for pair plus for playerTwo
		pairPlus2.setOnKeyPressed(e->{if(e.getCode().equals(KeyCode.ENTER)){
			playerTwo.setPairPlayBet((Integer.parseInt(pairPlus2.getText())));
			//System.out.println("P1 Ante: "+ playerOne.getAnteBet());
			announcement.setText("All bets are set. Player One choose Play or Fold");

			ante1.setEditable(false);
			ante2.setEditable(false);
			pairPlus1.setEditable(false);
			pairPlus2.setEditable(false);
			playWager1.setEditable(false);
			playWager2.setEditable(false);

			foldBtn1.setStyle("-fx-background-color: green;");
			foldBtn2.setStyle("-fx-background-color: green;");
			playBtn1.setStyle("-fx-background-color: green;");
			playBtn2.setStyle("-fx-background-color: green;");

			foldBtn1.setDisable(false);
			foldBtn2.setDisable(true);
			playBtn1.setDisable(false);
			playBtn2.setDisable(true);

			drawCards(playerOne, playerTwo);
		}
		});

		mOne.getItems().addAll(exitOp,freshStart,newLook);

		menuBar.getMenus().addAll(mOne);

		rootPane = new BorderPane();
		rootPane.setStyle("-fx-background-color: darkred");

		menuBox = new VBox(20, menuBar);
		announceBox = new HBox(20, announcement);

		playerOnePane = new VBox(20, ante1, pairPlus1, playWager1, totalEarn1);
		playerTwoPane = new VBox(20, ante2, pairPlus2, playWager2, totalEarn2);

		playerOnePane.setLayoutX(450);
		playerOnePane.setLayoutY(300);
		playerTwoPane.setLayoutX(770);
		playerTwoPane.setLayoutY(300);


		anteBox = new HBox(anteTxt);
		pairBox = new HBox(pairTxt);
		playBox = new HBox(playTxt);
		totalBox = new HBox(totalTxt);
		bottomForm = new HBox( 40, playBtn1, foldBtn1,  startBtn, playBtn2, foldBtn2);

		p1Box = new HBox(p1Txt);
		p2Box = new HBox(p2Txt);
		dealBox = new HBox(dealerTxt);

		announceBox.setLayoutX(50);
		announceBox.setLayoutY(20);

		anteBox.setLayoutX(595);
		anteBox.setLayoutY(310);

		pairBox.setLayoutX(580);
		pairBox.setLayoutY(380);

		playBox.setLayoutX(585);
		playBox.setLayoutY(450);

		totalBox.setLayoutX(575);
		totalBox.setLayoutY(520);

		p1Box.setLayoutX(175);
		p1Box.setLayoutY(200);

		p2Box.setLayoutX(1000);
		p2Box.setLayoutY(200);

		dealBox.setLayoutX(600);
		dealBox.setLayoutY(55);

		bottomForm.setLayoutX(375);
		bottomForm.setLayoutY(600);

		rootPane.getChildren().addAll(menuBox, announceBox, dealBox, p1Box, p2Box, playerOnePane, playerTwoPane, anteBox, pairBox,
										playBox, totalBox, bottomForm);
		Scene scene = new Scene(rootPane);
		primaryStage.setScene(scene);
		primaryStage.setMaximized(true);
		primaryStage.show();


	}

	//for starting the game and the drawing of cards
	public void startGame()
	{
		System.out.println("NEW GAME HAS STARTED");
		dealer.dealHand(); //dealer gives themselves cards
		dealer.DrawCards(playerOne); //dealer gives playerOne cards
		dealer.DrawCards(playerTwo); //dealer gives playerTwo cards

		//drawCards(playerOne, playerTwo);

		announcement.setText("Player One: Please place Ante bet and press ENTER once finished (this also places play wager)");

		ante1.setEditable(true);
		ante2.setEditable(false);
		pairPlus1.setEditable(false);
		pairPlus2.setEditable(false);
		playWager1.setEditable(false);
		playWager2.setEditable(false);
	}

	//restarting the game when "Fresh Start" is chosen
	public void restartGame()
	{
		startGame();
		playerOne.setTotalWinnings(0);
		playerTwo.setTotalWinnings(0);
		totalEarn1.setText("$0");
		totalEarn2.setText("$0");

		ante1.setText("");
		pairPlus1.setText("");
		playWager1.setText("");

		ante2.setText("");
		pairPlus2.setText("");;
		playWager2.setText("");
	}

	public String cardCreatorHelper(int val, char suit)
	{
		String output = new String();

		if(val == 14)//Ace
		{
			output = "A"+suit;
		}
		else if(val == 13)//King
		{
			output = "K"+suit;
		}
		else if(val == 12)//Queen
		{
			output = "Q"+suit;
		}
		else if(val == 11)//Jack
		{
			output = "J"+suit;
		}
		else//everyone else
		{
			output = ""+val+suit;
		}

		return output+".png";
	}

	public void showDealerCards(Dealer d)
	{
		final int cardSize = 150;

		String dCard1 = cardCreatorHelper(d.dealersHand.get(0).getValue(), d.dealersHand.get(0).getSuit());
		String dCard2 = cardCreatorHelper(d.dealersHand.get(1).getValue(), d.dealersHand.get(1).getSuit());
		String dCard3 = cardCreatorHelper(d.dealersHand.get(2).getValue(), d.dealersHand.get(2).getSuit());

		//for dealer's cards
		Image dealCardImage = new Image(dCard1);
		dealCard1 = new ImageView(dealCardImage);

		dealCard1.setFitHeight(cardSize);
		dealCard1.setFitWidth(cardSize);
		dealCard1.setPreserveRatio(true);

		Image dealCardImage2 = new Image(dCard2);
		dealCard2 = new ImageView(dealCardImage2);

		dealCard2.setFitHeight(cardSize);
		dealCard2.setFitWidth(cardSize);
		dealCard2.setPreserveRatio(true);

		Image dealCardImage3 = new Image(dCard3);
		dealCard3 = new ImageView(dealCardImage3);

		dealCard3.setFitHeight(cardSize);
		dealCard3.setFitWidth(cardSize);
		dealCard3.setPreserveRatio(true);

		dealerCards = new HBox(20, dealCard1, dealCard2, dealCard3);

		dealerCards.setLayoutX(475);
		dealerCards.setLayoutY(100);

		rootPane.getChildren().add(dealerCards);
	}


	//shows the cards of both players
	public void drawCards(Player p1, Player p2)
	{
		final int cardSize = 150;

		String p1Card1 = cardCreatorHelper(p1.hand.get(0).getValue(), p1.hand.get(0).getSuit());
		String p1Card2 = cardCreatorHelper(p1.hand.get(1).getValue(), p1.hand.get(1).getSuit());
		String p1Card3 = cardCreatorHelper(p1.hand.get(2).getValue(), p1.hand.get(2).getSuit());
		String p2Card1 = cardCreatorHelper(p2.hand.get(0).getValue(), p2.hand.get(0).getSuit());
		String p2Card2 = cardCreatorHelper(p2.hand.get(1).getValue(), p2.hand.get(1).getSuit());
		String p2Card3 = cardCreatorHelper(p2.hand.get(2).getValue(), p2.hand.get(2).getSuit());

		//player1's cards
		Image play1CardImage = new Image(p1Card1);
		ImageView play1Card1 = new ImageView(play1CardImage);

		play1Card1.setFitHeight(cardSize);
		play1Card1.setFitWidth(cardSize);
		play1Card1.setPreserveRatio(true);

		Image play1CardImage2 = new Image(p1Card2);
		ImageView play1Card2 = new ImageView(play1CardImage2);

		play1Card2.setFitHeight(cardSize);
		play1Card2.setFitWidth(cardSize);
		play1Card2.setPreserveRatio(true);

		Image play1CardImage3 = new Image(p1Card3);
		ImageView play1Card3 = new ImageView(play1CardImage3);

		play1Card3.setFitHeight(cardSize);
		play1Card3.setFitWidth(cardSize);
		play1Card3.setPreserveRatio(true);

		//player 2's cards
		Image play2CardImage = new Image(p2Card1);
		ImageView play2Card1 = new ImageView(play2CardImage);

		play2Card1.setFitHeight(cardSize);
		play2Card1.setFitWidth(cardSize);
		play2Card1.setPreserveRatio(true);

		Image play2CardImage2 = new Image(p2Card2);
		ImageView play2Card2 = new ImageView(play2CardImage2);

		play2Card2.setFitHeight(cardSize);
		play2Card2.setFitWidth(cardSize);
		play2Card2.setPreserveRatio(true);

		Image play2CardImage3 = new Image(p2Card3);
		ImageView play2Card3 = new ImageView(play2CardImage3);

		play2Card3.setFitHeight(cardSize);
		play2Card3.setFitWidth(cardSize);
		play2Card3.setPreserveRatio(true);

		playerOneCards = new HBox(20, play1Card1, play1Card2, play1Card3);
		playerTwoCards = new HBox(20, play2Card1, play2Card2, play2Card3);

		playerOneCards.setLayoutX(50);
		playerOneCards.setLayoutY(300);
		playerTwoCards.setLayoutX(880);
		playerTwoCards.setLayoutY(300);

		rootPane.getChildren().addAll(playerOneCards, playerTwoCards);
	}

}
