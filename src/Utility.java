public class Utility extends Bank {
    private  Dice d;

    public Utility(String name, Dice dice){
        super(name, 150, 0);
        this.d = dice;
    }

    @Override
    public int getRent() {
        int pay;
        int thrw = d.currentRoll();

        switch(owner.getNumUtilities()){
            case 1:
                pay = 4 * thrw;
                break;
            case 2:
                pay = 10 * thrw;
                break;
            default:
                pay = -1;
                break;
        }

        return pay;
    }
}
