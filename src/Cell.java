
public class Cell implements Comparable<Cell> {
	
	    protected int index;
	    protected final String name;

	    public Cell(String name){
	        this.name = name;
	    }

	    public void doAction(Player currentPlayer){};

	    public String toString(){
	        return name;
	    }

	    public int compareTo(Cell s){
	        if(index < s.index){
	            return -1;
	        } else if (index == s.index){
	            return 0;
	        } else {
	            return 1;
	        }
	    }
	}

