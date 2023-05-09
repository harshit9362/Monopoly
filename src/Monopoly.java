import java.util.ArrayList;
import java.util.Scanner;

public class Monopoly {
    public static void main(String[] args){
    	Scanner sc= new Scanner(System.in);
    	System.out.println("Enter number of players:");
    	int a = sc.nextInt();
        Dice dice = new Dice();
        Jail jail = new Jail();
        ArrayList<Player> players = createPlayers(a);
        Board board = new Board(jail, dice, players);

        Game game = new Game(jail, dice, board, players);
        jail.setGame(game);

        game.turn(players.get(0));
    }

    static ArrayList<Player> createPlayers(int numPlayers){
        ArrayList<Player> players = new ArrayList<>();

        for(int i = 1; i <= numPlayers; i++){
            System.out.print("Enter Player " + i + " name: ");
            players.add(new Player(Input.read()));
        }

        return players;
    }
}
