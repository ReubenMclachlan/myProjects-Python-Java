
import java.awt.event.InputMethodListener;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class main {
	
	static Boolean quite = false;

	public static void main(String[] args) {
		
		//Reads external text file
		try {
			Scanner input = new Scanner(new File("input.txt"));
			input.useDelimiter("\n");
			
			//Goes through each line and creates a project object
			while(input.hasNext()) {
				String projectNum = input.next();
				String inputName = input.next();
				String inputBuildingType = input.next();
				String inputBuildingAddress = input.next();
				String inputErfNum = input.next();
				String inputProjectFee = input.next();
				String inputCurrentPaidAmount = input.next();
				String inputDueDate = input.next();
				
				//Creates a architect object
				String role1 = input.next();
				String architectName = input.next();
				String architectTelephone = input.next();
				String architectEmail = input.next();
				String architectAddress = input.next();
				newPerson inputArchitect = new newPerson(role1, architectName, architectTelephone, architectEmail, architectAddress);
				
				//Adds person to person object list in the newPerson class
				newPerson.People.add(inputArchitect);

				String architect = inputArchitect.toString();
				
				//Creates a contractor object
				String role2 = input.next();
				String contractorName = input.next();
				String contractorTelephone = input.next();
				String contractorEmail = input.next();
				String contractorAddress = input.next();
				newPerson inputContractor = new newPerson(role2, contractorName, contractorTelephone, contractorEmail, contractorAddress);
				newPerson.People.add(inputContractor);
				
				String contractor = inputContractor.toString();

				
				//Creates a customer object
				String role3 = input.next();
				String customerName = input.next();
				String customerTelephone = input.next();
				String customerEmail = input.next();
				String customerAddress = input.next();
				newPerson inputCustomer = new newPerson(role3, customerName, customerTelephone, customerEmail, customerAddress);
				newPerson.People.add(inputCustomer);
				
				String customer = inputCustomer.toString();

				boolean inputFinalization = input.nextBoolean();
				
				newProject project = new newProject(inputName, projectNum,  inputBuildingType,  inputBuildingAddress,  inputErfNum,  inputProjectFee,  inputCurrentPaidAmount,  inputDueDate,  architect,  contractor,  customer,  inputFinalization);
				
				//Adds new project to the project object list in the newProject class
			    newProject.Projects.add(project);
			    
			    newProject.totalProjects += 1;
			    
			    System.out.println("The total projects added from external file:" + newProject.totalProjects);
			}
		}
		catch(Exception e) {
			System.out.println("File not found");
		}
	
		while(quite.equals(false)) {
	    Scanner in = new Scanner(System.in);
		System.out.println("The following options are given, please choose the one you want to exicute:\n"
				+ "1: Create a new project:\n" + "2: Create a person of a project:\n" +"3: Change the due date of a project:\n" + "4: Change the total amount of fees payed to date of a project:\n"
				+ "5: Finalize a project (mark as finished):\n" + "6: Change contact details of contractor\n" + "7: See a list of projects that are not completed\n" + "8: See projects that are past their due date\n" + "9: Quite app:\n" +  "Enter the number of your choice here:" );
		String userChoice = in.nextLine();
		
		
		//if user chooses to create a new project
		if(userChoice.equals("1")) {
			
			newProject.createProject();
		}
		
		//if user chooses to add a person 
		if(userChoice.equals("2")) {
			newPerson.createPerson();
		}
		
		//if user chooses to change the due date of a project
		else if(userChoice.equals("3")) {
			
            newProject.changeDueDate();
		}
		
		//if user chooses to change the total amount of fees payed to date 
		else if(userChoice.equals("4")) {
			
			newProject.changeFeesPaid();
		}
		
		//if user chooses to finalize a project
		else if(userChoice.equals("5")) {
			
			newProject.finalizeProject();
		}
		
		//if user chooses to change the contact details of a specific contractor
		else if(userChoice.equals("6")) {
			
			newPerson.changeContactDetails();
			
		}
		
		//if user chooses to see a list of uncompleted projects
		else if(userChoice.equals("7")) {
			
			newProject.uncompletedList();
		}
		
		//if user chooses to see a list of projects past duedate
		else if(userChoice.equals("8")) {
			
			newProject.pastDueDate();
		}
		
		//if user chooses to quit
		else if(userChoice.equals("9")) {
			try {
				//File writers are created to write updated date to files
				FileWriter myWriter = new FileWriter("completeProjects.txt");
				 
				FileWriter myWriter2 = new FileWriter("input.txt");
				
				//Writes a list of completed projects to completeprojects.txt with today as the day they were finalized
				if(newProject.completeProjects != null) {
					SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
					Date date = new Date();
					String today = formatter.format(date);
						
					for(int i = 0; i < newProject.completeProjects.size(); i++) {
						String project = newProject.completeProjects.get(i).toString();
						myWriter.append(project + "\r\n" + today); 
					}
					myWriter.close();
				}
				
				//Writes updated projects back to input.txt file 
				if(newProject.Projects != null) {
						
					for(int i = 0; i < newProject.Projects.size(); i++) {
						String project = newProject.Projects.get(i).toString();
						myWriter2.append(project + "\r\n"); 
					}
					 myWriter2.close();
				}
				 
			}
			catch(Exception e) {
				System.out.println("Error occurd");
			}
			
			
			System.out.println("App ended");
			break;
		}
	}

	}
		
	}