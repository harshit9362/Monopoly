import java.util.Collections;
import java.util.LinkedList;

public class Deck {
    private LinkedList<Card> deck = new LinkedList<>();

   
    public void playTop(Player player){
        Card topCard = deck.removeFirst();
        add(topCard);
        topCard.play(player);
    }

    public void add(Card card){
        deck.addLast(card);
    }

    public void shuffle(){
        Collections.shuffle(deck);
    }
}
