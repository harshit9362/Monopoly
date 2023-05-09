import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {
    private final Jail j;
    private final Dice d;
    private final Board b;
    private ArrayList<Player> players = new ArrayList<Player>();

    public Game(Jail jail, Dice dice, Board board, ArrayList<Player> players){
        this.j = jail;
        this.d = dice;
        this.b = board;
        this.players = players;
    }

    public ArrayList<Player> getPlayers (){
        return players;
    }
   
    public void turn(Player currentPlayer){
        System.out.println("\n Now it is " + currentPlayer.getName() + "'s turn!"+"\nBalance: " + currentPlayer.getMoney());

        if(currentPlayer.inJail){ 

            if(!j.jailTurn(currentPlayer, d, b)) {
                showOptions(currentPlayer);
            }
        } else { 
            System.out.println("Position on Board: " + b.getCurrentSquare(currentPlayer));
            int num = 0;

            do{
                currentPlayer.move(d.roll(), b);
                num++;

                if(num == 3){
                    j.sendToJail(currentPlayer);
                }
            } while (num < 3 && d.isDouble());
        }

        showOptions(currentPlayer);
    }

    public void endTurn(Player currentPlayer){
        int currentIndex = players.indexOf(currentPlayer);
        if(currentIndex + 1 == players.size()){
            turn(players.get(0));
        } else {
            turn(players.get(currentIndex + 1));
        }
    }

    
    private void showOptions(Player currentPlayer){
        List<Toy> options = Arrays.asList(
                new ListPropertiesOption(currentPlayer),
                new BuyHouseOption(currentPlayer),
                new EndTurnOption(this, currentPlayer)
        );

        Toy selectedOption = (Toy) Input.selectOptions(options, "More Actions:");
        selectedOption.action();

        showOptions(currentPlayer); 
    }
}
