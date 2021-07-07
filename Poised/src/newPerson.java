
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class newPerson {
	
	//List of all the people that have been created
	public static List<newPerson> People = new ArrayList<newPerson>(); 
	
	// Attributes
	private String Role;
	private String Name;
	private String telephoneNum;
	private String Email;
	private String physicalAddress;
	
	//Constructor
	public newPerson(String Role, String Name, String telephoneNum, String Email, String physicalAddress) {
		this.Role = Role;
		this.Name = Name;
		this.telephoneNum = telephoneNum;
		this.Email = Email;
		this.physicalAddress = physicalAddress;
	}

	
	public String getRole() {
		
		return Role;
	}
	
	public String getName() {
		
		return Name;
	}
	
	public void setTelephoneNum(String inputTelephoneNum) {
		
		telephoneNum = inputTelephoneNum;
	}
	
	public void setEmail(String inputEmail) {
		
		Email = inputEmail;
	}
	
	public void setPhysicalAddress(String inputAddress) {
		
		physicalAddress = inputAddress;
	}
	
	//Method to create a person object
	public static void createPerson() {		
	    Scanner in = new Scanner(System.in);
		System.out.println("What is the persons role?:");
		String Role = in.nextLine();
		
		//Checks if entered role is valid
		if(Role.equalsIgnoreCase("Architect") || Role.equalsIgnoreCase("Contractor") || Role.equalsIgnoreCase("Customer")) {
			
			System.out.println("What is the name of this person?:");
			String Name = in.nextLine();
			
			System.out.println("What is this persons telephone num?:");
			String telephoneNum = in.nextLine();
			
			System.out.println("What is this persons email address?:");
			String Email = in.nextLine();
			
			System.out.println("What is this persons physical address?:");
			String physicalAddress = in.nextLine();
			
			newPerson Person = new newPerson(Role, Name, telephoneNum, Email, physicalAddress);
			
			//Adds new person object to list
			People.add(Person);	
		}			
		else {
			System.out.println("The role entered is not valid");
			System.exit(0);
		}		
	}
    
	//Method to get a specific person from the list 
	public static String getPersonObject(String personName, String personRole) {
		String Person = "Person was not found";
		
		//Goes through each object in the list
		for(int i = 0; i < People.size(); i++) {
			
			//Gets person objects name and role 
			newPerson Object = People.get(i);
			String objectName = Object.getName();
			String objectRole = Object.getRole();
			
			//Checks if the name and role matches
			if(objectName.equalsIgnoreCase(personName) && objectRole.equalsIgnoreCase(personRole)) {
				Person = Object.personToString();
				
			}
		}
		return Person;
	}
	
	//Method to change the contact details of the contractor 
	public static void changeContactDetails() {
			Scanner in = new Scanner(System.in);

			System.out.println("What is the contractors full name:");
			String personName = in.nextLine();
						
			System.out.println("What is the persons new telephoneNum:");
			String newTelephoneNum = in.nextLine();
		
			System.out.println("What is the persons new email");
			String newEmail = in.nextLine();
			
			try {
				//Searches for obj with matching parameters and updates the contact details
				for(int i = 0; i < People.size(); i++) {
					
					//Gets person objects name and role 
					newPerson Object = People.get(i);
					String objectName = Object.getName();
					String objectRole = Object.getRole();		
					
					//Checks if the name and role matches
					if(objectName.equalsIgnoreCase(personName) && objectRole.equalsIgnoreCase("Contractor")) {
						Object.setTelephoneNum(newTelephoneNum);
						Object.setEmail(newEmail);
						System.out.println(Object.personToString() + "\nContractor contact details have been updated");
					}
				}
			}
			catch(Exception e) {
				System.out.println("This user was not found");
			}
	}

	//To string method for person objects
	public String personToString() {
		return Role+ "\n" + Name + "\n" + telephoneNum + "\n" + Email + "\n" + physicalAddress ;
	}
}
