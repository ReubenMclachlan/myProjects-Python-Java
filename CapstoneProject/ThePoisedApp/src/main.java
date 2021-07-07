
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;

public class main {
	
	static Boolean quite = false;

	public static void main(String[] args) {
		
		//Reads external text file
		try {
			//Creating a connection to PoisedPMS database
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PoisedPMS?allowPublicKeyRetrieval=true&useSSL=false", "otheruser", "swordfish");
			
			//Creating a direct line to the database for running queries
			Statement statement = connection.createStatement();
			ResultSet results;
			int rowsAffected;
			
			//Adds all used person identification numbers to a array list in class new person
			results = statement.executeQuery("SELECT Person_Id FROM Architect");
			while(results.next()) {
				newPerson.usedIdentification.add(results.getString("Person_Id"));
			}
			results = statement.executeQuery("SELECT Person_Id FROM Contractor");
			while(results.next()) {
				newPerson.usedIdentification.add(results.getString("Person_Id"));
			}
			results = statement.executeQuery("SELECT Person_Id FROM Customer");
			while(results.next()) {
				newPerson.usedIdentification.add(results.getString("Person_Id"));
			}
				
			//Adds all used project numbers to a array list in class new projects
			results = statement.executeQuery("SELECT Proj_Num FROM Projects");
			while(results.next()) {
				newProject.usedProjectNums.add(results.getString("Proj_Num"));
			}
			results = statement.executeQuery("SELECT Proj_Num FROM CompleteProjects");
			while(results.next()) {
				newProject.usedProjectNums.add(results.getString("Proj_Num"));
			}
	
		while(quite.equals(false)) {
	    Scanner in = new Scanner(System.in);
		System.out.println("The following options are given, please choose the one you want to exicute:\n"
				+ "1: Create a new project:\n" + "2: Create a person of a project:\n" +"3: Change the due date of a project:\n" + "4: Change the total amount of fees payed to date of a project:\n"
				+ "5: Finalize a project (mark as finished):\n" + "6: Change contact details of contractor\n" + "7: See a list of projects that are not completed\n" + "8: See projects that are past their due date\n" + "9: Quite app:\n" +  "Enter the number of your choice here:" );
		String userChoice = in.nextLine();
		
		
		//if user chooses to create a new project
		if(userChoice.equals("1")) {
			String Architect = "";
			String Contractor ="";
			String Customer = "";
			
			newProject.createProject();
			
			//Converting some values from string into their appropriate data types
			int Proj_Num = Integer.parseInt(newProject.projectInfo.get(0));
			int Erf_Num = Integer.parseInt(newProject.projectInfo.get(4));
			int Proj_Finalization = Integer.parseInt(newProject.projectInfo.get(8));
			String Proj_Address = newProject.projectInfo.get(3);
			
			//Gathering the rest of the persons info/data from their tables
			results = statement.executeQuery("SELECT * FROM Architect WHERE Person_Id = '"+newProject.projectInfo.get(9)+"'");
			while(results.next()) {
				 Architect = results.getInt("Person_Id") + ", " + results.getString("Person_Name") + ", " + results.getString("Person_Telephone") +
						", " + results.getString("Person_Email") + ", " + results.getString("Person_Address");
			}
			//Gathering the rest of the persons info/data from their tables
			results = statement.executeQuery("SELECT * FROM Contractor WHERE Person_Id = '"+newProject.projectInfo.get(10)+"'");
			while(results.next()) {
				 Contractor = results.getInt("Person_Id") + ", " + results.getString("Person_Name") + ", " + results.getString("Person_Telephone") +
						", " + results.getString("Person_Email") + ", " + results.getString("Person_Address");
			}
			//Gathering the rest of the persons info/data from their tables
			results = statement.executeQuery("SELECT * FROM Customer WHERE Person_Id = '"+newProject.projectInfo.get(11)+"'");
			while(results.next()) {
				 Customer = results.getInt("Person_Id") + ", " + results.getString("Person_Name") + ", " + results.getString("Person_Telephone") +
						", " + results.getString("Person_Email") + ", " + results.getString("Person_Address");
			}
	
			//Adding data into project table
			rowsAffected = statement.executeUpdate("INSERT INTO Projects VALUES('"+Proj_Num+"', '"+newProject.projectInfo.get(1)+"', '"+newProject.projectInfo.get(2)+"', '"+Proj_Address+"', '"+Erf_Num+"', '"+newProject.projectInfo.get(5)+"', '"+newProject.projectInfo.get(6)+"', Date'"+newProject.projectInfo.get(7)+"', '"+Proj_Finalization+"', '"+Architect+"', '"+Contractor+"', '"+Customer+"')"); 

		}
		
		//if user chooses to add a person 
		if(userChoice.equals("2")) {
			newPerson.createPerson();
			//Changing id num into integer
			int userId = Integer.parseInt(newPerson.userInfo.get(0));
			
			//Determines which table the person should be added to
			if(newPerson.userInfo.get(1).equalsIgnoreCase("Architect")) {
				rowsAffected = statement.executeUpdate("INSERT INTO Architect VALUES('"+userId+"', '"+newPerson.userInfo.get(2)+"', '"+newPerson.userInfo.get(3)+"', '"+newPerson.userInfo.get(4)+"', '"+newPerson.userInfo.get(5)+"')");
			}
			
			else if(newPerson.userInfo.get(1).equalsIgnoreCase("Contractor")) {
				rowsAffected = statement.executeUpdate("INSERT INTO Contractor VALUES('"+userId+"', '"+newPerson.userInfo.get(2)+"', '"+newPerson.userInfo.get(3)+"', '"+newPerson.userInfo.get(4)+"', '"+newPerson.userInfo.get(5)+"')");
			}
			
			else if(newPerson.userInfo.get(1).equalsIgnoreCase("Customer")) {
				rowsAffected = statement.executeUpdate("INSERT INTO Customer VALUES('"+userId+"', '"+newPerson.userInfo.get(2)+"', '"+newPerson.userInfo.get(3)+"', '"+newPerson.userInfo.get(4)+"', '"+newPerson.userInfo.get(5)+"')");
			}
			
			
		}
		
		//if user chooses to change the due date of a project
		else if(userChoice.equals("3")) {
			
		    try {
		    	System.out.println("What is the number of the project?:");
		    	String Num = in.nextLine();
		    	int projectNum = Integer.parseInt(Num);
			
		    	System.out.println("What is the new due date of the project (2021-12-20):");
		    	String newDuedate = in.nextLine();

		    	//Changes the deadline/duedate of the project in the projects table
		    	rowsAffected = statement.executeUpdate("UPDATE Projects SET Proj_Deadline = Date'"+newDuedate+"' WHERE Proj_Num = '"+projectNum+"'");
		
		    	System.out.println("Duedate has been updated\n");
		    }
		    //Displays a error message if Project was not found
		    catch(Exception e) {
		    	System.out.println("Project could not be found ");
		    }
		}
		
		//if user chooses to change the total amount of fees payed to date 
		else if(userChoice.equals("4")) {
			 try {
			    	System.out.println("What is the number of the project?:");
			    	String Num = in.nextLine();
			    	int projectNum = Integer.parseInt(Num);
								
			    	System.out.println("What is the new total fees payed for the project(R100000):");
			    	String newCurrentPaidAmount = in.nextLine();
			    	
			    	//Changes the current paid amount of the project
			    	rowsAffected = statement.executeUpdate("Update Projects SET Paid_Amount = '"+newCurrentPaidAmount+"' WHERE Proj_Num = '"+projectNum+"'");
			    	System.out.println("Current paid amount has been updated");

			    }
			    //Displays a error message if Project was not found
			    catch(Exception e) {
			    	System.out.println("Project could not be found");
			    }
		}
		
		//if user chooses to finalize a project
		
			else if(userChoice.equals("5")) {
					
					String projectName = "";
					String buildingType = "";
					String projectAddress = "";
					int erfNum = 0;
					String projectFee = "";
				    String paidAmount = "";
				    String projectDeadline = "";
				    String Architect = "";
				    String Contractor = "";
				    String Customer = "";
				    //Finalizes project
					int projectFinalization = 1;
				
				    System.out.println("What is the number of the project?:");
				   	String Num = in.nextLine();
				   	int projectNum = Integer.parseInt(Num);
		
				   	//Gathering the rest of the project info/data
					results = statement.executeQuery("SELECT * FROM Projects WHERE Proj_Num = '"+projectNum+"'");
					while(results.next()) {
						//Breaking the project up into parts to add to the complete projects table
						 projectName = results.getString("Proj_Name");
						 buildingType = results.getString("Building_Type");
						 projectAddress = results.getString("Physical_Address");
						 erfNum = results.getInt("Erf_Num");
						 projectFee = results.getString("Proj_Fee");
						 paidAmount = results.getString("Paid_Amount");
						 projectDeadline = results.getString("Proj_Deadline");
						 Architect = results.getString("Proj_Architect");
						 Contractor = results.getString("Proj_Contractor");
						 Customer = results.getString("Proj_Customer");
						  
					}
					  //Inserts finalized project info into completed projects table with their completion date which is today
				    rowsAffected = statement.executeUpdate("INSERT INTO CompleteProjects Values('"+projectNum+"', '"+projectName+"', '"+buildingType+"', '"+projectAddress+"', '"+erfNum+"', '"+projectFee+"'"
				    		+ ", '"+paidAmount+"', Date'"+projectDeadline+"', '"+projectFinalization+"', '"+Architect+"', '"+Contractor+"', '"+Customer+"', Now())");
					
				  	System.out.println("\nProject has been finalized\n");
				  	
				    //Removes project from the projects table
				    rowsAffected = statement.executeUpdate("DELETE FROM Projects WHERE Proj_Num = '"+projectNum+"'");
			    


		}		
		
		//if user chooses to change the contact details of a specific contractor
		else if(userChoice.equals("6")) {
			try {
				System.out.println("What is the persons identification number?:");
				String Num = in.nextLine();
			   	int personNum = Integer.parseInt(Num);
							
				System.out.println("What is the persons new telephoneNum:");
				String newTelephoneNum = in.nextLine();
			
				System.out.println("What is the persons new email");
				String newEmail = in.nextLine();
			
				//Changes the contact details of the contractor in the contractor table
				rowsAffected = statement.executeUpdate("UPDATE Contractor SET Person_Telephone = '"+newTelephoneNum+"', Person_Email = '"+newEmail+"' WHERE Person_Id = '"+personNum+"'");	
		    	
				System.out.println("\nPersons contact detail have been updated\n");
			}
		    //Displays a error message if Project was not found
			catch(Exception e) {
				System.out.println("This user was not found");
			}
			
		}
		
		//if user chooses to see a list of uncompleted projects
		else if(userChoice.equals("7")) {
			//Gathering the rest of the project info/data
			results = statement.executeQuery("SELECT * FROM Projects");
			
			while(results.next()) {
				//Breaking the project up into parts and displaying each field
				System.out.println(results.getInt("Proj_Num") + "," + results.getString("Proj_Name") + ", " + results.getString("Building_Type") + ", " 
						+ results.getString("Physical_Address") + ", " + results.getInt("Erf_Num") + ", " + results.getString("Proj_Fee") + ", " +  results.getString("Paid_Amount") 
						+ ", " + results.getString("Proj_Deadline") + ", " + results.getInt("Proj_Finalization") + ", " + results.getString("Proj_Architect") + ", " +  results.getString("Proj_Contractor")
						+ ", " + results.getString("Proj_Customer"));
			}
		}
		
		//if user chooses to see a list of projects past duedate
		else if(userChoice.equals("8")) {
			
			//Selects projects with deadlines that are smaller than todays date
			results  = statement.executeQuery("SELECT * FROM Projects WHERE Proj_Deadline < Now()");
			while(results.next()) {
				//Breaking the project up into parts and displaying each field
				System.out.println(results.getInt("Proj_Num") + "," + results.getString("Proj_Name") + ", " + results.getString("Building_Type") + ", " 
						+ results.getString("Physical_Address") + ", " + results.getInt("Erf_Num") + ", " + results.getString("Proj_Fee") + ", " +  results.getString("Paid_Amount") 
						+ ", " + results.getString("Proj_Deadline") + ", " + results.getInt("Proj_Finalization") + ", " + results.getString("Proj_Architect") + ", " +  results.getString("Proj_Contractor")
						+ ", " + results.getString("Proj_Customer"));
			}
		}
		
		//if user chooses to quit
		else if(userChoice.equals("9")) {
			System.exit(0);
			results.close();
			statement.close();
			connection.close();
		}
	}

	
	}
	catch(Exception e) {
		System.out.println(e);
	}
		
	}
}