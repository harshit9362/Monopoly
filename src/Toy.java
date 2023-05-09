
public abstract class Toy {
	
	    String message;

	    public Toy(String message){
	        this.message = message;
	    }

	    abstract public void action();

	    public String toString(){
	        return message;
	    }
	}

	class ListPropertiesOption extends Toy{
	    Player player;

	    public ListPropertiesOption(Player currentPlayer){
	        super("Show Properties");
	        player = currentPlayer;
	    }

	    public void action(){
	        player.listProperties();
	    }
	}

	class BuyHouseOption extends Toy{
	    Player player;

	    public BuyHouseOption(Player currentPlayer){
	        super("Buy Houses for property");
	        player = currentPlayer;
	    }

	    public void action(){
	        ColorCell houseProperty = (ColorCell) Input.selectOptions(player.getHouseableProperties(), "Select property to purchase house on: ");

	        if(houseProperty == null){
	            System.out.println("You do not have any properties to place a house on");
	        } else {
	            houseProperty.addHouse();
	        }
	    }
	}


	class SellPropertyOption extends Toy{
	    Player player;

	    public SellPropertyOption(Player currentPlayer){
	        super("Sell Unimproved Properties");
	        player = currentPlayer;
	    }

	    public void action(){
	        Bank sellProperty = (Bank) Input.selectOptions(player.getUnimprovedProperties(), "Select a property to sell");

	        if(sellProperty == null){
	            System.out.println("You do not have properties to sell.");
	        } else {
	            player.sell(sellProperty);
	        }
	    }
	}

	class EndTurnOption extends Toy{
	    Game game;
	    Player player;

	    public EndTurnOption(Game game, Player currentPlayer){
	        super("End Turn");
	        this.game = game;
	        player = currentPlayer;
	    }

	    public void action(){
	        game.endTurn(player);
	    }
	}

	class PayBailOption extends Toy{
	    Dice dice;
	    Player player;
	    Board board;

	    public PayBailOption(Dice dice, Player currentPlayer, Board board){
	        super("Pay $50");
	        this.dice = dice;
	        player = currentPlayer;
	        this.board = board;
	    }

	    public void action(){
	        player.addMoney(-50);
	        player.inJail = false;
	        player.move(dice.roll(), board);
	    }
	}

	class RollOptionJail extends Toy{
	    Dice d;
	    Player p;
	    Board b;

	    public RollOptionJail(Dice dice, Player currentPlayer, Board board){
	        super("Roll");
	        this.d = dice;
	        p = currentPlayer;
	        this.b = board;
	    }

	    public void action(){
	        int roll = d.roll();

	        if(d.isDouble()){
	            p.inJail = false;
	            p.move(roll, b);
	        }
	    }
	}
