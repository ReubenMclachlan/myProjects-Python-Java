import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class newProject {
	
	//Creates a ArrayList of the persons info
	public static List<String> projectInfo = new ArrayList<String>();
	
	//List of taken numbers/business ids
	public static List<String> usedProjectNums = new ArrayList<String>();
	
	//Method to create a projects
	public static void createProject() {
		 //Clears the project info array list for the next project to be created
	    projectInfo.clear();
		
	    Scanner in = new Scanner(System.in);
	    System.out.println("What is the projects number:");
		String inputNum = in.nextLine();
		projectInfo.add(inputNum);
		
		//Checks if project number has been used by a other person
		if(usedProjectNums.contains(inputNum)) {
			System.out.print("This project number has already been used");
			System.exit(0);
		}
		else {
			//Adding id number to used id numbers if it has not already been used
			usedProjectNums.add(inputNum);
		}
			    
		System.out.println("What is the projects name:");
		String inputName = in.nextLine();
		projectInfo.add(inputName);
		
		System.out.println("What building type is being built:");
		String inputBuildingType = in.nextLine();
		projectInfo.add(inputBuildingType);
		
		System.out.println("What is the building address:");
		String inputBuildingAddress = in.nextLine();
		projectInfo.add(inputBuildingAddress);
		
		System.out.println("What is the ERF number:");
		String inputErfNum = in.nextLine();
		projectInfo.add(inputErfNum);
		
		System.out.println("What is the total project fee(R100000):");
		String inputProjectFee = in.nextLine();
		projectInfo.add(inputProjectFee);
		
		System.out.println("What amount has already been paid by today(R100000):");
	    String inputCurrentPaidAmount = in.nextLine();
	    projectInfo.add(inputCurrentPaidAmount);
	    
		System.out.println("What is the due date of this project (2020-12-10):");
		String inputDueDate = in.nextLine();
		projectInfo.add(inputDueDate);
		
		//When a project is created it is not yet finalized
	    String Finalization = "0";
	    projectInfo.add(Finalization);
		
		//Architect
		System.out.println("What is the Id number of the architect?:");
		String architectId = in.nextLine();
		projectInfo.add(architectId);
        
	    //Contractor
		System.out.println("What is the Id number of the constructor?:");  
		String contractorId = in.nextLine();
		projectInfo.add(contractorId);

		//Customer
		System.out.println("What is the Id number of the customer?:");
		String customerId = in.nextLine();
		projectInfo.add(customerId);
		   
	    //if user has not entered a name for the project ,it creates a name by adding the building type to the customers surname
	    if(inputName.isBlank()) {
	    	System.out.println("What is the name of the customer?:");
	    	String customerName = in.nextLine();
	    	
			//Removes surname to help create a name for the project
			String[] customerSurname = customerName.split("\\ ");
	    	inputName = customerSurname[1].concat(inputBuildingType);
	    	
	    	//Replacing blank value of the project name in array list with the new created name
	    	projectInfo.set(1, inputName);
	    }
	    
		
	    //Creates invoice for user
	    int projectFee = Integer.parseInt(inputProjectFee.substring(1));
	    int currentPaidAmount = Integer.parseInt(inputCurrentPaidAmount.substring(1));
	    if(projectFee != currentPaidAmount) {
	    	int outstandingAmount = (projectFee - currentPaidAmount);
	    	System.out.println("\nCustomer invoice\n The amount outstanding: " + outstandingAmount + "\n");		    	
	    }

	}
	
}
	
