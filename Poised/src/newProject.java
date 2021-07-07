
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class newProject {
	
	
	static Integer totalProjects = 0;
	
	//List of all created project objects 
	public static List<newProject> Projects = new ArrayList<newProject>(); 

	//List of all completed projects
	public static List<newProject> completeProjects = new ArrayList<newProject>();
	
	// Attributes
	private String projectName;
    private String projectNum;
	private String buildingType;
	private String buildingAddress;
	private String erfNum;
	private String projectFee;
	private String currentPaidAmount;
	private String dueDate;
	private String Architect;
	private String Contractor;
	private String Customer;
	private boolean Finalized;
	
	//Constructor
	public newProject(String projectName, String projectNum, String buildingType, String buildingAddress, String erfNum, String projectFee, String currentPaidAmount, String dueDate, String Architect, String Contractor, String Customer, boolean Finalized){
		this.projectName = projectName;
		this.projectNum  = projectNum;
		this.buildingType = buildingType;
		this.erfNum = erfNum;
		this.projectFee = projectFee;
		this.currentPaidAmount = currentPaidAmount;
		this.dueDate = dueDate;
		this.Architect = Architect;
		this.Contractor = Contractor;
		this.Customer = Customer;
		this.Finalized = Finalized;
	}
	
	private void setCurrentPaidAmount(String inputCurrentPaidAmount) {
		
		currentPaidAmount = inputCurrentPaidAmount;
	}
	
	private void setDuedate(String inputDueDate) {
		
		dueDate = inputDueDate;
	}
	
	private void setFinalization(boolean inputFinalization) {
		
		Finalized = inputFinalization;
	}
	
	private String getDueDate() {
		return dueDate;
	}
	
	private String getProjectName() {
		return projectName;
	}
	
	//Method to create a project object
	public static void createProject() {
		
		
	    Scanner in = new Scanner(System.in);
			    
		System.out.println("What is the projects name:");
		String inputName = in.nextLine();
		
		System.out.println("What building type is being built:");
		String inputBuildingType = in.nextLine();
		
		System.out.println("What is the building address:");
		String inputBuildingAddress = in.nextLine();
		
		System.out.println("What is the ERF number:");
		String inputErfNum = in.nextLine();
		
		System.out.println("What is the total project fee:");
		String inputProjectFee = in.nextLine();
		
		System.out.println("What amount has already been paid by today:");
	    String inputCurrentPaidAmount = in.nextLine();
	    
		System.out.println("What is the due date of this project (20/12/2020):");
		String inputDueDate = in.nextLine();
		
		//Architect
		System.out.println("What is the name of the architect?:");
		String architectName = in.nextLine();
        String architectObject = newPerson.getPersonObject(architectName, "Architect");
        
        //Checks if person exists and stops the create of a project object if not
        String inputArchitect = "";
        if(architectObject == "Person was not found") {
	    	System.out.println("Person was not found, please create person first");
	    	System.exit(0);
	    }
	    else {
	    	inputArchitect = architectObject;
	    }
		
	    //Contractor
		System.out.println("What is the name of the constructor?:");  
		String contractorName = in.nextLine();
	    String contractorObject = newPerson.getPersonObject(contractorName, "Contractor");
	    
	    String inputContractor = "";
	    if(contractorObject == "Person was not found") {
	    	System.out.println("Person was not found, please create person first");
	    	System.exit(0);
	    }
	    else {
	    	inputContractor = contractorObject;
	    }
		
		//Customer
		System.out.println("What is the name of the customer?:");
		String customerName = in.nextLine();
		

	    String customerObject = newPerson.getPersonObject(customerName, "Customer");
	    
	    String inputCustomer = "";
	    if(customerObject == "Person was not found") {
	    	System.out.println("Person was not found, please create person first");
	    	System.exit(0);
	    }
	    else {
	    	inputCustomer = customerObject;
	    }
	    
		//When a project is created it is not yet finalized
	    boolean Finalization = false;
	    
	    String projectNum = totalProjects.toString();
	    
	    //if user has not entered a name for the project ,it creates a name by add ding the building type to the customers surname
	    if(inputName.isBlank()) {
	    	
			//Removes surname to help create a name for the project
			String[] customerSurname = customerName.split("\\ ");
	    	inputName = customerSurname[1].concat(inputBuildingType);
	    }
	    
	    //Creates invoice for user
	    int projectFee = Integer.parseInt(inputProjectFee);
	    int currentPaidAmount = Integer.parseInt(inputCurrentPaidAmount);
	    
	    if(projectFee != currentPaidAmount) {
	    	int outstandingAmount = (projectFee - currentPaidAmount);
	    	
	    	System.out.println("\nCustomer invoice\n The amount outstanding: " + outstandingAmount + "\n" + inputCustomer);
	    	
	    	System.out.println("\nProject was created\n Project number is:\n" + projectNum + "\n");
	    	
	    }
	    newProject Project = new newProject(inputName, projectNum, inputBuildingType,  inputBuildingAddress, inputErfNum, inputProjectFee,  inputCurrentPaidAmount, inputDueDate, inputArchitect,  inputContractor,  inputCustomer,  Finalization);
		System.out.println("\nProject has been created\n");

        //Adds new project to list and adds to the count of total projects
        Projects.add(Project);
		totalProjects += 1;
		
		
	}
	
	//Method to change the due date of a project
	public static void changeDueDate() {
		
	    Scanner in = new Scanner(System.in);
	    
	    try {
	    	System.out.println("What is the number of the project?:");
	    	String projectNum = in.nextLine();
		
		
	    	System.out.println("What is the new due date of the project:");
	    	String newDueDate = in.nextLine();
	    	
	    	//Changes due date to new due date 
	    	Projects.get(Integer.parseInt(projectNum)).setDuedate(newDueDate);		
		
	    	System.out.println("Duedate has been updated\n");
	    }
	    catch(Exception e) {
	    	System.out.println("Project could not be found ");
	    }
	}
	
	//Method to change current paid fees of a project
	public static void changeFeesPaid() {
		
	    Scanner in = new Scanner(System.in);
	    
	    try {
	    	System.out.println("What is the number of the project?:");
	    	String projectNum = in.nextLine();
						
	    	System.out.println("What is the new total fees payed for the project:");
	    	String newCurrentPaidAmount = in.nextLine();
	    	
	    	//Changes the current paid amount of the project
	    	Projects.get(Integer.parseInt(projectNum)).setCurrentPaidAmount(newCurrentPaidAmount);

	    	System.out.println("\n Current paid amount has been updated");

	    }
	    catch(Exception e) {
	    	System.out.println("Project could not be found");
	    }
	}
	
	//Method to finalize a project
	public static void finalizeProject() {
		
	    Scanner in = new Scanner(System.in);
	    
	    try {
	    	System.out.println("What is the number of the project?:");
	    	int projectNum = in.nextInt();
			
	    	//Finalizes the project
	    	Projects.get(projectNum).setFinalization(true);
	    	
	    	//Adds it to the list of completed projects
	    	completeProjects.add(Projects.get(projectNum));

	    	System.out.println("\nProject has been finalized\n");
	    	
	    	Projects.remove(projectNum);
	    	totalProjects -= 1;
	    	
	    	
	    }
	    catch(Exception e) {
	    	System.out.println("Project could not be found");
	    }
	}
	
	//Method to print the list of not yet completed projects
	public static void uncompletedList() {
		if(Projects != null) {
			
			System.out.print("\nUncomplete projects:\n");
			for(int i = 0; i < Projects.size(); i++) {
				System.out.println(Projects.get(i).getProjectName() + "\n");
			}
		}
		else {
			System.out.println("List is empty");
		}
	}
	
	//Method to show a list of uncompleted projects that past due date
	public static void pastDueDate() {
		
		//Checks what todays date is 
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String today = formatter.format(date);
		
		if(Projects != null) {
			System.out.println("\nProject past duedate:\n");
			
			//Looks through list of projects and checks if todays date is past the projects duedate
			for(int i = 0; i < Projects.size();i++) {
				String dueDate = Projects.get(i).getDueDate();
				
				//if it is past due date it displays it
				if(today.compareTo(dueDate) < 0) {
					System.out.println(Projects.get(i).getProjectName() + "\n");
				}
				
			}
		}
		
	}
	
	public String toString() {
		return projectNum + "\n" + projectName + "\n" + buildingType + "\n" + buildingAddress + "\n" + erfNum + "\n" + projectFee + "\n" + currentPaidAmount + "\n" + dueDate + "\n" + Architect + "\n" + Contractor + "\n" + Customer + "\n" + Finalized;
	}
	
}
	
