public class Railroad extends Bank {
    public Railroad(String name){
        super(name, 200, 0);
    }

    @Override
    public int getRent() {
        int pay;

        switch(owner.getNumRailroads()){
            case 1:
                pay = 25;
                break;
            case 2:
                pay = 50;
                break;
            case 3:
                pay = 100;
                break;
            case 4:
                pay = 200;
                break;
            default:
                pay = -1;
                break;
        }

        return pay;
    }
}
