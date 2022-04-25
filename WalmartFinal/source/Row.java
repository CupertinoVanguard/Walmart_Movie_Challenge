import java.util.*;
import java.lang.*;
public class Row{
	public static final int num_cols = 20;
    public int[] seated = new int[num_cols]; //0 - open, 1 - seated, 2 - blocked off
    public static final int openS = 0;
    public static final int shut = 2;
    public static final int seatS = 1;
    public int open;
    public int closed;
    public int continuousOpen;
    
    public Row(){
        open = num_cols;
        closed = 0;
        continuousOpen = num_cols;
    }
    /*
     * Finds longest consecutive stretch of seats in row. 
     * 
     * Return: Integer with the longest stretch possible. 
     */
    public int findCurrentContinous(){
        int co = 0;
        int max = 0;
        for (int i = 0; i < seated.length; i++){
            if (seated[i] == openS){
                co++;
            }else if (seated[i] == seatS || seated[i] == shut){
                co = 0;
            }
            if (co > max){
                max = co;
            }
        }
        return max;
    }
    /*
     * Finds the indeces of the available seats of the desired size. Utilizes greedy approach and so
     * chooses the first index available.
     * 
     * Return: Integer pertaining to the first index of the consecutive run. 
     * 
     */
    public int openSeats(int size){
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < seated.length - size+1; i++){
            int counter = 0;
            for (int y = i; y < i+size; y++){
                if (seated[y] == openS){
                    counter++;
                }
            }
            if (counter >= size){
                list.add(i);
            }
        }
        return list.get(0);
    }
    /*
     * Method works to assign the seats based on the start index and end index and appropriate provides the row
     */
    public ArrayList<String> assignSeats(int startIndex, int end, char rowval){
    	ArrayList<String> asSeats = new ArrayList<String>();
        for (int i = startIndex; i < end; i++){
            seated[i] = 1;
            asSeats.add("" + rowval + "" + i);
        }
        return asSeats;
    }
    /*
     * This method blocks of the seats based on the public saftey requirements. 
     */
    public void killSeats(int start, int end) {
    	if (start >= 0 && end<=20) {
    		for (int i = start; i < end; i++) {
    			seated[i] = shut;
    		}
    	}else if (start < 0) {
    		for (int i = 0; i < end; i++) {
    			seated[i] = shut;
    		}
    	}else if (end > 20) {
    		for (int i = start; i < 19; i++){
    			seated[i] = shut;
    		}
    	}
    }
}