import java.util.*;
import java.lang.*;
public class MainTheater {
    public static final int numRows =10;
	public Row[] rows = new Row[numRows];
	public boolean[] rowBool = new boolean[numRows];
	public char[] rowsch = {'J', 'I', 'H', 'G', 'F', 'E', 'D', 'C', 'B', 'A'};
	public HashMap<String, ArrayList<String>> map = new HashMap<>();
	public MainTheater() {
		for (int k = 0; k < numRows; k++) {
			rows[k] = new Row();
		}
	}
	/*
	 * Find seats based on the desired seat requirement. Follows greedy approach working down from 9 to 0 and 
	 * 0 to 20. Appropriately places the safety requirements listed and priorities placing as large groups
	 * as possible. Updates map based on priority and adds to the existing seat array if applicable. 
	 * 
	 * Return: If seats can be assigned for the existing group, return True.
	 */
	public boolean findSeats(int des, String priority) {
		boolean satisfied = false;
		int index = 0;
		while (satisfied == false && index < numRows) {
			if (rows[index].findCurrentContinous() >= des) {
				int tempList = rows[index].openSeats(des);
				ArrayList<String> seats = rows[index].assignSeats(tempList, tempList + des, rowsch[index]);
				if (map.get(priority) == null) {
					map.put(priority, seats);
				}else {
					map.get(priority).addAll(seats);
				}
				if (index != 0 && index != numRows - 1) {
					rows[index-1].killSeats(tempList, tempList + des);
					rows[index+1].killSeats(tempList, tempList + des);
				}else if (index == 0) {
					rows[index+1].killSeats(tempList, tempList + des);
				}else if (index == 9) {
					rows[index-1].killSeats(tempList, tempList + des);
				}
				rows[index].killSeats(tempList - 3, tempList);
				rows[index].killSeats(tempList + des, tempList + des + 3);
				satisfied = true;
			}
			index++;
		}
		return satisfied;
	}
	/* 
	 * Works to separate the seats based on the largest groups that can be kept together. Uses Recursive
	 * binary structure to acheive the task.
	 * 
	 * Return: True if a separation is at all possible. 
	 */
	public boolean seatSeparate(int des, String priority) {
		if (des == 1 && findSeats(des, priority) == true) {
			return true;
		}else if (des == 1 && findSeats(des, priority) == false) {
			return false;
		}else if (findSeats(des, priority) == true) {
			return true;
		}
		return seatSeparate(des - 1, priority) && findSeats(1, priority);
		
	}
	/*
	 * Implements the seatSeparate and findSeats method as required. 
	 */
	public String genSeat(Res reservation) {
		if (findSeats(reservation.totalDes, reservation.resNum)) {
			return "Your whole party was seated!";
		}else if(seatSeparate(reservation.totalDes, reservation.resNum)) {
			return "Your party had to be split";
		}else {
			return "your party was either not completely seated or none were able to be seated all";
		}
		
	}
	public String getStringRep(ArrayList<String> listarr) {
		String r = "";
		for (int i = 0; i < listarr.size() - 1; i++) {
			r += listarr.get(i) + ", ";
		}
		r += listarr.get(listarr.size() - 1);
		return r;
	}
	
}
  