
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Player {
    private ArrayList<Bank> properties = new ArrayList<Bank>();
    private final String name;
    private int position;
    private int money = 2500;

    public boolean inJail = false;
    public int outOfJailCards = 0;
    public int turnsInJail = 0;

    public Player(String name){
        this.name = name;
        position = 0;
    }

    public String getName() { return name; }

    public int getPosition() { return position; }

    public int getMoney() { return money; }

    public void addMoney(int addMoney){
        if(money < -addMoney){
            broke(-addMoney - money);
        }

        this.money += addMoney;
    }

    private void broke(int amountNeeded){
        System.out.println("You are missing an amount of" + amountNeeded);
       
    }

    public void pay(Player receiving, int amount){
        receiving.addMoney(amount);
        addMoney(-amount);
    }

    public void move(int numSquares, Board board){
        position += numSquares;

        
        if(position >= 40){
            System.out.println(name + " passed GO and collected 200");
            money += 200;
            position %= 40;
        }

        System.out.println("Landed on " + board.getCurrentSquare(this));
        board.getCurrentSquare(this).doAction(this);
    }

    public void moveTo(int toPosition, Board board){
        move((40 - position + toPosition) % 40, board);
    }

  
    public void buy(Bank property){
        addMoney(-property.getPrice());
        properties.add(property);
        sortPropertiesByGroup(properties);
    }

    public void sell(Bank property){
        addMoney(property.getPrice() / 2);
        property.setOwner(null);
    }

    

    public int getWorth(){
        int total = 0;

        for(Bank p : properties){
            if(p instanceof ColorCell){
                total += (((ColorCell) p).getNumHouses() * ((ColorCell) p).getHouseCost()) / 2;
            }

            total += p.getPrice() / 2;
        }

        return total + money;
    }

    private void sortPropertiesByGroup(ArrayList<Bank> properties){
        ArrayList<Utility> utilities = new ArrayList<>();
        ArrayList<Railroad> railroads = new ArrayList<>();
        ArrayList<Bank> sorted = new ArrayList<>();

        for(Bank property : properties){
            if(property instanceof Utility){
                utilities.add((Utility) property);
            } else if(property instanceof Railroad){
                railroads.add((Railroad) property);
            } else {
                sorted.add(property);
            }
        }
        Collections.sort(utilities);
        Collections.sort(railroads);
        Collections.sort(sorted);

        sorted.addAll(railroads);
        sorted.addAll(utilities);

        this.properties = sorted;
    }

    public void listProperties(){
        if(properties.isEmpty()){
            System.out.println("You do not own any properties");
        }
        for(Bank property : properties){
            System.out.println(property);
        }
    }

    public int getNumRailroads(){
        int numRailroads = 0;
        for(Bank p : properties){
            if(p instanceof Railroad){
                numRailroads++;
            }
        }

        return numRailroads;
    }

    public int getNumUtilities(){
        int numUtilities = 0;
        for(Bank p : properties){
            if(p instanceof Utility){
                numUtilities++;
            }
        }

        return numUtilities;
    }

    
    public ArrayList<ColorCell> getOwnColorGroupList(){
        ArrayList<ColorCell> list = new ArrayList<>();
        for(Bank property: properties){
            if(property instanceof ColorCell && ownsGroup(((ColorCell) property).getGroup())){
                list.add((ColorCell) property);
            }
        }
        return list;
    }


    public ArrayList<ColorCell> getHouseableProperties(){
        ArrayList<ColorCell> houseable = new ArrayList<>();
        for(ColorCell i : getOwnColorGroupList()){
            boolean lowestHouses = true;

            for(ColorCell j : getOwnColorGroupList()){
                if(i.getGroup() == j.getGroup() && i.getNumHouses() > j.getNumHouses()){
                    lowestHouses = false;
                }
            }

            if(lowestHouses && i.getNumHouses() != 5){
                houseable.add(i);
            }
        }

        return houseable;
    }

    
    public ArrayList<Bank> getUnimprovedProperties(){
        ArrayList<Bank> unimproved = new ArrayList<>();
        for(Bank property : properties){
            if(property instanceof ColorCell && ((ColorCell) property).getNumHouses() != 0);
            else {
                unimproved.add(property);
            }
        }

        return unimproved;
    }

    
    private boolean owns(Bank property){
        return properties.contains(property);
    }

    
    public boolean ownsGroup(ColorCell.Group group){
        int count = 0;

        for(Bank property : properties){
            if(property instanceof ColorCell && ((ColorCell) property).getGroup() == group){
                count++;
            }
        }

        return (count == group.maxInGroup);
    }
}
