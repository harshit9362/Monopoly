public class Tax extends Cell{
    private int taxAmount;

    public Tax(String name, int taxAmount){
        super(name);
        this.taxAmount = taxAmount;
    }

    
    @Override
    public void doAction(Player p) {

    }
}
