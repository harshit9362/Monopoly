public class Dice {
    private int throw1;
    private int throw2;

    public int roll(){
        System.out.print("Click ENTER to roll your dice");
        Input.read();

        throw1 = (int) (Math.random() * 6 + 1);
        throw2 = (int) (Math.random() * 6 + 1);

        System.out.println("Congrats, you got " + throw1 + " and " + throw2);
        return throw1 + throw2;
    }

    
    public int currentRoll(){
        return throw1 + throw2;
    }

    public boolean isDouble(){
        return throw1 == throw2;
    }
}
