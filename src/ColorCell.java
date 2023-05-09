

public class ColorCell extends Bank {
	

	    private final Group group;

	    private int numHouses = 0; 
	    private final int houseCost;

	   
	    private final int r1;
	    private final int r2;
	    private final int r3;
	    private final int r4;
	    private final int r5;

	    public enum Group{
	        BROWN(2),
	        SKY(3),
	        PINK(3),
	        ORANGE(3),
	        RED(3),
	        YELLOW(3),
	        GREEN(3),
	        BLUE(2);

	        public final int maxInGroup;

	        Group(int maxInGroup){
	            this.maxInGroup = maxInGroup;
	        }
	    }

	    public ColorCell(String name, Group group, int price, int rent, int rent1, int rent2, int rent3, int rent4, int rentH){
	        super(name, price, rent);
	        this.group = group;
	        this.r1 = rent1;
	        this.r2 = rent2;
	        this.r3 = rent3;
	        this.r4 = rent4;
	        this.r5 = rentH;

	        switch(group){
	            case BROWN:
	            case SKY:
	                houseCost = 50;
	                break;
	            case PINK:
	            case ORANGE:
	                houseCost = 100;
	                break;
	            case RED:
	            case YELLOW:
	                houseCost = 150;
	                break;
	            case GREEN:
	            case BLUE:
	                houseCost = 200;
	                break;
	            default:
	                houseCost = -1;
	        }
	    }

	    public Group getGroup() { return group; }

	    public int getNumHouses() { return numHouses; }

	    public int getHouseCost() {
	        return houseCost;
	    }

	    public void addHouse(){
	        getOwner().addMoney(-houseCost);
	        numHouses++;
	        if(numHouses == 5){
	            System.out.println("Hotel built on " + name + " for " + houseCost);
	        } else {
	            System.out.println("House built on " + name + " for " + houseCost);
	        }
	    }

	    @Override
	    public int getRent() {
	        int rent = 0;
	        switch(numHouses){
	            case 0:
	                rent = super.getRent();
	                if(getOwner().ownsGroup(group)){
	                    rent *= 2;
	                }
	                break;
	            case 1:
	                rent = r1;
	                break;
	            case 2:
	                rent = r2;
	                break;
	            case 3:
	                rent = r3;
	                break;
	            case 4:
	                rent = r4;
	                break;
	            case 5:
	                rent = r5;
	                break;
	        }

	        return rent;
	    }
	}


