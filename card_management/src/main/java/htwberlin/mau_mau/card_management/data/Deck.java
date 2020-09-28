package htwberlin.mau_mau.card_management.data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * The type Deck.
 * Describes deck of cards used for drawing deck, playing deck and players' hands.
 */
@Entity
public class Deck implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * Collection of type Stack representing a deck of cards.
     */
    @OneToMany(cascade = CascadeType.ALL)
    private List<Card> cards;

    /**
     * Instantiates a new Deck.
     */
    public Deck() {
        cards = new LinkedList<>();
    }

    /**
     * Gets cards.
     *
     * @return the cards
     */
    public List<Card> getCards() {
        return cards;
    }

    public Card pop(){
        return cards.remove(cards.size()-1);
    }

    public void push(Card card){
        cards.add(card);
    }

    public Card peek() {
        return cards.get(cards.size()-1);
    }
}
