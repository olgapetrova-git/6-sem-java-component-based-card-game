package htwberlin.mau_mau.card_management.service;

import htwberlin.mau_mau.card_management.data.Card;
import htwberlin.mau_mau.card_management.data.Deck;
import htwberlin.mau_mau.card_management.data.Rank;
import htwberlin.mau_mau.card_management.data.Suit;
import org.junit.*;

import static org.junit.Assert.fail;

public class CardServiceTest {
    private CardService cardService;
    @BeforeClass
    public static void initialize() {}

    @Before
    public void setUp() throws Exception {
        cardService = new CardServiceImpl();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testCreateDeckOfCards() {
        //Arrange
        //Act
        Deck drawingDeck = cardService.createDrawingStack();
        //Assert
        Assert.assertEquals(32, drawingDeck.getCards().size());
        // Check that cards are unique
        for(int i=0;i<drawingDeck.getCards().size();i++){
            Card cardA = drawingDeck.getCards().get(i);
            for(int j=0;j<drawingDeck.getCards().size();j++) {
                Card cardB = drawingDeck.getCards().get(j);
                if(i!=j){
                    if(cardA.getRank() == cardB.getRank()
                            && cardA.getSuit() == cardB.getSuit()){
                        Assert.fail("Card is not unique: " + cardA.getSuit().toString() + " of " + cardA.getRank().toString());
                    }
                }
            }
        }
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
        try {
            cardService.playCard(hand, 0, playingStack);
        } catch (IncorrectCardPositionException e){
            Assert.fail("Unexpected IncorrectCardPositionException for position 0: " + e.getMessage());
        }
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
    public void testDrawCard() {
        //Arrange
        Deck hand = new Deck();
        hand.getCards().add(new Card(Suit.DIAMONDS, Rank.SEVEN));
        Deck drawingStack = new Deck();
        Deck playingStack = new Deck();
        drawingStack.getCards().add(new Card(Suit.HEARTS, Rank.ACE));
        drawingStack.getCards().add(new Card(Suit.SPADES, Rank.QUEEN));
        //Act
        try {
            cardService.drawCard(drawingStack, playingStack, hand);
        } catch (EmptyDrawingStackException | EmptyPlayingStackException e) {
            e.printStackTrace();
            fail("Exception occurred: " + e.getMessage());
        }
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
    public void testDrawCardTurnOver(){
        //if the last card from the drawing stack is drawn, call turnOver
        //Arrange
        Deck playingStack = new Deck();
        Deck hand = new Deck();
        playingStack.getCards().add(new Card(Suit.DIAMONDS, Rank.SEVEN));
        playingStack.getCards().add(new Card(Suit.HEARTS, Rank.ACE));
        Deck drawingStack = new Deck();
        drawingStack.getCards().add(new Card(Suit.SPADES, Rank.QUEEN));
        //Act
        try {
            cardService.drawCard(drawingStack, playingStack, hand);
        } catch (EmptyDrawingStackException | EmptyPlayingStackException e) {
            e.printStackTrace();
            Assert.fail("Unexpected Exception occurred: " + e.getMessage());
        }
        //Assert
        Assert.assertEquals(1, hand.getCards().size());
        Assert.assertEquals(1, drawingStack.getCards().size());
        Assert.assertEquals(1, playingStack.getCards().size());

        Assert.assertEquals(Suit.SPADES, hand.getCards().get(0).getSuit()); //got card from the drawing stack
        Assert.assertEquals(Rank.QUEEN, hand.getCards().get(0).getRank());

        Assert.assertEquals(Suit.DIAMONDS, drawingStack.getCards().get(0).getSuit());
        Assert.assertEquals(Rank.SEVEN, drawingStack.getCards().get(0).getRank());

        Assert.assertEquals(Suit.HEARTS, playingStack.getCards().get(0).getSuit()); //open top card left in the playing stack after turnover
        Assert.assertEquals(Rank.ACE, playingStack.getCards().get(0).getRank());

    }

    @Test(expected = EmptyPlayingStackException.class)
    public void testDrawCardThrowsEmptyPlayingStackException() throws EmptyDrawingStackException, EmptyPlayingStackException {
        //Arrange
        Deck hand = new Deck();
        hand.getCards().add(new Card(Suit.DIAMONDS, Rank.SEVEN));
        Deck drawingStack = new Deck();
        Deck playingStack = new Deck();
        drawingStack.getCards().add(new Card(Suit.HEARTS, Rank.ACE));
        //Act
        cardService.drawCard(drawingStack, playingStack, hand);
        //Assert
    }

    @Test
    public void testCreatePlayingStack(){
        Deck drawingStack = new Deck();
        drawingStack.getCards().add(new Card(Suit.HEARTS, Rank.ACE));
        drawingStack.getCards().add(new Card(Suit.SPADES, Rank.QUEEN));
        try{
            Deck playingStack = cardService.createPlayingStack(drawingStack);
        } catch (EmptyDrawingStackException | EmptyPlayingStackException e) {
            e.printStackTrace();
            Assert.fail("Unexpected Exception occurred: " + e.getMessage());
        }
        //TODO:
    }

    @Test
    public void testGetOpenCard() {
        //Arrange
        Deck playingStack = new Deck();
        playingStack.getCards().add(new Card(Suit.DIAMONDS, Rank.SEVEN));
        playingStack.getCards().add(new Card(Suit.HEARTS, Rank.ACE));
        //Act
        Card card = cardService.getOpenCard(playingStack);
        //Assert
        Assert.assertEquals(Suit.HEARTS, card.getSuit());
        Assert.assertEquals(Rank.ACE, card.getRank());
    }

    @Test
    public void testShuffleDrawingDeck() {
        //Arrange
        Deck drawingStack = new Deck();
        drawingStack.getCards().add(new Card(Suit.SPADES, Rank.QUEEN));
        drawingStack.getCards().add(new Card(Suit.DIAMONDS, Rank.SEVEN));
        //Act
        Deck shuffledStack = cardService.shuffleDrawingDeck(drawingStack);
        //Assert
        Assert.assertEquals(2, shuffledStack.getCards().size());
        //TODO: Check if order of cards has changed

    }
    @AfterClass
    public static void uninitialize() {}
}