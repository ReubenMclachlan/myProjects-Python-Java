

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class newPerson {
	
	//Creates a ArrayList of the persons info
	public static List<String> userInfo = new ArrayList<String>();
	
	//List of taken numbers/business ids
	public static List<String> usedIdentification = new ArrayList<String>();

	//Method to create a person object
	public static void createPerson() {
		//Clears array list for next person to be created
		userInfo.clear();
		
		String Results =" ";
		
	    Scanner in = new Scanner(System.in);
	    
		System.out.print("What is the persons business identification number?:\n");
		String idNum = in.nextLine();
		userInfo.add(idNum);
		
		//Checks if Id number has been used by a other person
		if(usedIdentification.contains(idNum)) {
			System.out.print("This number of identification has already been used");
			System.exit(0);
		}
		else {
			//Adding id number to used id numbers if it has not already been used
			usedIdentification.add(idNum);
		}
	    
		System.out.println("What is the persons role?:");
		String Role = in.nextLine();
		userInfo.add(Role);
		
		//Checks if entered role is valid
		if(Role.equalsIgnoreCase("Architect") || Role.equalsIgnoreCase("Contractor") || Role.equalsIgnoreCase("Customer")) {
			
			System.out.println("What is the full name of this person?:");
			String Name = in.nextLine();
			userInfo.add(Name);
			
			System.out.println("What is this persons telephone num?:");
			String telephoneNum = in.nextLine();
			userInfo.add(telephoneNum);
			
			System.out.println("What is this persons email address?:");
			String Email = in.nextLine();
			userInfo.add(Email);
			
			System.out.println("What is this persons physical address? (12 Round St, Milnerton):");
			String physicalAddress = in.nextLine();
			userInfo.add(physicalAddress);
			
			//Creates a string containing all the users info
			Results = String.join(", ", userInfo);

		}
		else {
			System.out.println("The role entered is not valid");
			System.exit(0);
		}	
	}

}
	

