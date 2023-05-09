
public abstract class Bank extends Cell {
	
	    private final int price;
	    private final int rent;
	    protected Player owner;
	    public boolean mortgaged;

	    public Bank(String name, int price, int rent){
	        super(name);
	        this.price = price;
	        this.rent = rent;
	    }

	    public int getPrice(){
	        return price;
	    }

	    public int getRent(){
	        return rent;
	    }

	    public Player getOwner() {
	        return owner;
	    }

	    public void setOwner(Player newOwner){
	        owner = newOwner;
	    }

	    public void offerBuy(Player currentPlayer){
	        System.out.println("Do you want to buy " + name + " for " + price + "?");
	        String response = Input.read().toLowerCase();

	        if(response.contains("y")){
	            bought(currentPlayer);
	        }
	    }

	    public void bought(Player currentPlayer){
	        owner = currentPlayer;
	        currentPlayer.buy(this);
	    }

	    @Override
	    public void doAction(Player currentPlayer) {
	        if(currentPlayer == owner);
	           
	        else if(owner != null) {
	          
	            if(!mortgaged){
	                System.out.println("You have to pay " + owner.getName() + getRent() + " as rent");
	                currentPlayer.pay(owner, getRent());
	            }
	        } else {
	            
	            offerBuy(currentPlayer);
	        }
	    }
	}


