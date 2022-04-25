import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; 
import java.util.*;
import java.lang.*;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
public class WalmartFinal {
	// Import the Scanner class to read text files

	/* This method is the main executable method. It reads the file, populates the theater
	 * and writes out the results of the populating. 
	 * */
	  public static void main(String[] args) {
		ArrayList<Res> reservation = new ArrayList<>(); //The input reservation list.
		/*
		 * Read the text file and update the reservation list
		 */
	    try {
	      //File myObj = new File("/Users/nikpil23/eclipse-workspace/Walmart_hw/src/input_file.txt");
	      File myObj = new File(args[0]);
	      Scanner myReader = new Scanner(myObj);
	      
	      while (myReader.hasNextLine()) {
	        String data = myReader.nextLine();
	        reservation.add(new Res(data.substring(0, data.indexOf(' ')), Integer.parseInt(data.substring(data.indexOf(' ') + 1))));
	      }
	      myReader.close();
	    } catch (FileNotFoundException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	    }
	    
	    MainTheater obj = new MainTheater();//This is the actual theater object
	    /*
	     * This populates the theater object and assings seats when and if possible. 
	     */
	    for (int i = 0; i < reservation.size(); i++) {
	    	obj.genSeat(reservation.get(i));
	    }
	    /*
	     *  Interpret the hashmap of the theater object and create final strings to be written to output file
	     */
	    ArrayList<String> finalSeats = new ArrayList<String>();
	    for (int i = 0; i < reservation.size(); i++) {
	    	String keyVal = "" + reservation.get(i).resNum;
	    	String seatsAssigned = "";
	    	if (obj.map.get(reservation.get(i).resNum) != null) {
	    		if (obj.map.get(reservation.get(i).resNum).size() == reservation.get(i).totalDes){
		    		seatsAssigned = obj.getStringRep(obj.map.get(reservation.get(i).resNum));
		    	}else if (obj.map.get(reservation.get(i).resNum).size() == 0) {
		    		seatsAssigned = "could not assign";
		    	}else {
		    		seatsAssigned = obj.getStringRep(obj.map.get(reservation.get(i).resNum)) + " Partial assignment";
		    	}
	    	}
	    	finalSeats.add(keyVal + " " + seatsAssigned);
	    }
	    /*
	     * Write to the desired output file location. 
	     */
	    try {
	        FileWriter myWriter = new FileWriter("/Users/nikpil23/Desktop/output_file.txt");
	        for (int i = 0; i < finalSeats.size(); i++) {
	        	myWriter.write(finalSeats.get(i) + "\n");
	        }
	        myWriter.close();
	        System.out.println("Successfully wrote to the file.");
	      } catch (IOException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	      }
	  }
	
	

}
