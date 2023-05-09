import java.util.Arrays;
import java.util.List;

public class Jail{
    private Game game;

    public void setGame(Game game){
        this.game = game;
    }

    public void sendToJail(Player jailedPlayer){
        jailedPlayer.inJail = true;
        System.out.println("You will be locked in Jail for the next 3 rounds.");
        game.endTurn(jailedPlayer);
    }

    public boolean jailTurn(Player currentPlayer, Dice dice, Board board){ 
        currentPlayer.turnsInJail++;
        System.out.print("Turn " + currentPlayer.turnsInJail);

        if(currentPlayer.turnsInJail == 3){
            currentPlayer.inJail = false;

            int roll = dice.roll();
            if(!dice.isDouble()){
                currentPlayer.addMoney(-50);
            }

            currentPlayer.move(roll, board);
        }

        List<Toy> jailOptions = Arrays.asList(
                new RollOptionJail(dice, currentPlayer, board),
                new PayBailOption(dice, currentPlayer, board)
        );

        Toy selectedOption = (Toy) Input.selectOptions(jailOptions, "Roll a double or pay 50 to get out of jail");
        selectedOption.action();

        return currentPlayer.inJail;
    }
}
