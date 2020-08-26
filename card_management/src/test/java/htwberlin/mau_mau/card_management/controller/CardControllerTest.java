package htwberlin.mau_mau.card_management.controller;

import htwberlin.mau_mau.card_management.model.Card;
import htwberlin.mau_mau.card_management.model.Deck;
import htwberlin.mau_mau.card_management.model.Rank;
import htwberlin.mau_mau.card_management.model.Suit;
import org.junit.*;

import static org.junit.Assert.*;

public class CardControllerTest {
private CardController cardController;
    @BeforeClass
    public static void initialize() {}

    @Before
    public void setUp() throws Exception {
        cardController = new CardControllerImpl();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testCreateDeckOfCards() {
        //Arrange
        //Act
        Deck drawingDeck = cardController.createDeckOfCards();
        //Assert
        Assert.assertEquals(32, drawingDeck.getCards().size());
        //TODO: test that cards are unique
    }

    @Test
    public void testRemoveCardFromHandAddToPlayingStack() {
        //Arrange
        Deck hand = new Deck();
        Card testCard = new Card(Suit.CLUBS, Rank.ACE);
        hand.getCards().add(testCard);
        hand.getCards().add(new Card(Suit.DIAMONDS, Rank.SEVEN));
        Deck playingStack = new Deck();
        playingStack.getCards().add(new Card(Suit.HEARTS, Rank.ACE));
        //Act
        cardController.removeCardFromHandAddToPlayingStack(hand, testCard, playingStack);
        //Assert
        Assert.assertEquals(1,hand.getCards().size());
        Assert.assertEquals(2, playingStack.getCards().size());
        Assert.assertEquals(Suit.DIAMONDS, hand.getCards().get(0).getSuit());
        Assert.assertEquals(Rank.SEVEN, hand.getCards().get(0).getRank());
        Assert.assertEquals(Suit.HEARTS, playingStack.getCards().get(0).getSuit());
        Assert.assertEquals(Rank.ACE, playingStack.getCards().get(0).getRank());
        Assert.assertEquals(Suit.CLUBS, playingStack.getCards().get(1).getSuit());
        Assert.assertEquals(Rank.ACE, playingStack.getCards().get(1).getRank());
    }

    @Test
    public void testAddCardFromDrawingStackToHand() {
        //Arrange
        Deck hand = new Deck();
        hand.getCards().add(new Card(Suit.DIAMONDS, Rank.SEVEN));
        Deck drawingStack = new Deck();
        drawingStack.getCards().add(new Card(Suit.HEARTS, Rank.ACE));
        drawingStack.getCards().add(new Card(Suit.SPADES, Rank.QUEEN));
        //Act
        cardController.addCardFromDrawingStackToHand(drawingStack,hand);
        //Assert
        Assert.assertEquals(2, hand.getCards().size());
        Assert.assertEquals(1, drawingStack.getCards().size());
        Assert.assertEquals(Suit.DIAMONDS, hand.getCards().get(0).getSuit());
        Assert.assertEquals(Rank.SEVEN, hand.getCards().get(0).getRank());

        Assert.assertEquals(Suit.SPADES, hand.getCards().get(1).getSuit());
        Assert.assertEquals(Rank.QUEEN, hand.getCards().get(1).getRank());

        Assert.assertEquals(Suit.HEARTS, drawingStack.getCards().get(0).getSuit());
        Assert.assertEquals(Rank.ACE, drawingStack.getCards().get(0).getRank());
    }
    @Test
    public void testAddCardFromDrawingStackToHandTurnOver(){
        //if the last card from the drawing stack is drawn, call turnOver
        //Arrange
        Deck playingStack = new Deck();
        Deck hand = new Deck();
        playingStack.getCards().add(new Card(Suit.DIAMONDS, Rank.SEVEN));
        playingStack.getCards().add(new Card(Suit.HEARTS, Rank.ACE));
        Deck drawingStack = new Deck();
        drawingStack.getCards().add(new Card(Suit.SPADES, Rank.QUEEN));
        //Act
        cardController.addCardFromDrawingStackToHand(drawingStack,hand);
        //Assert
        Assert.assertEquals(1, hand.getCards().size());
        Assert.assertEquals(1, drawingStack.getCards().size());
        Assert.assertEquals(1, playingStack.getCards().size());

        Assert.assertEquals(Suit.SPADES, hand.getCards().get(0).getSuit()); //got card from the drawing stack
        Assert.assertEquals(Rank.QUEEN, hand.getCards().get(0).getRank());

        Assert.assertEquals(Suit.DIAMONDS, drawingStack.getCards().get(0).getSuit());
        Assert.assertEquals(Rank.SEVEN, drawingStack.getCards().get(0).getRank());

        Assert.assertEquals(Suit.HEARTS, playingStack.getCards().get(0).getSuit()); //topmost card left in the playing stack after turnover
        Assert.assertEquals(Rank.ACE, playingStack.getCards().get(0).getRank());

    }

    @Test
    public void getCardByPositionFromHand() {
        //Arrange
        Deck hand = new Deck();
        hand.getCards().add(new Card(Suit.DIAMONDS, Rank.SEVEN));
        hand.getCards().add(new Card(Suit.HEARTS, Rank.ACE));
        //Act
        Card card = cardController.getCardByPositionFromHand(1,hand);
        //Assert
        Assert.assertEquals(Suit.HEARTS, card.getSuit());
        Assert.assertEquals(Rank.ACE, card.getRank());
    }

    @Test
    public void getTopmostCardFromPlayingStack() {
        //Arrange
        Deck playingStack = new Deck();
        playingStack.getCards().add(new Card(Suit.DIAMONDS, Rank.SEVEN));
        playingStack.getCards().add(new Card(Suit.HEARTS, Rank.ACE));
        //Act
        Card card = cardController.getTopmostCardFromPlayingStack(playingStack);
        //Assert
        Assert.assertEquals(Suit.HEARTS, card.getSuit());
        Assert.assertEquals(Rank.ACE, card.getRank());
     }

    @Test
    public void shuffleDrawingDeck() {
        //Arrange
        Deck drawingStack = new Deck();
        drawingStack.getCards().add(new Card(Suit.SPADES, Rank.QUEEN));
        drawingStack.getCards().add(new Card(Suit.DIAMONDS, Rank.SEVEN));
        //Act
        Deck shuffledStack = cardController.shuffleDrawingDeck(drawingStack);
        //Assert
        Assert.assertEquals(2, shuffledStack.getCards().size());
        //TODO: Check if order of cards has changed

    }
    @AfterClass
    public static void uninitialize() {}
}