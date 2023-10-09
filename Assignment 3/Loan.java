import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Loan {
	private PriorityQueue<Applicant> activeList;
	private ArrayList<Applicant> approvedList;
	private ArrayList<Applicant> rejectedList;
	private int budget;
	
	public Loan () {
		activeList = new PriorityQueue<Applicant>();
		approvedList = new ArrayList<Applicant>();
		rejectedList = new ArrayList<Applicant>();
		budget = 0;
	}
	
	public void run() {
		/* print the options for the Loan app  and execute them
		 * 
		 * Possible operations:
		 * Load applications
         * Set the budget
         * Make a decision
         * Print
         * Update an application (there is a method find() in PriorityQueue 
         *                        that you can use to find the applicant 
         *                        you would like to update)
		*/
		
		System.out.println("Welcome to Loan Approval System");
		printOptions();
		
		while(true) {
			switch(getInput()) {
			case 1:
				loadApplications();
				printOptions();
				break;
				
			case 2:
				setBudget();
				printOptions();
				break;
			
			case 3:
				makeDecision();
				printOptions();
				break;
			
			case 4: 
				printApplications();
				printOptions();
				break;
				
			case 5:
				updateApplications();
				printOptions();
				break;
			
			case 6:
				System.out.println("Thank you for using the Loan Approval System!");
				System.exit(0);
				break;
			
			default:
				System.out.println("Please enter a valid option number");
				printOptions();
			}
		}
		
	}
	
	public void printOptions() {
		System.out.println();
		System.out.println("Select the command from the following list");
		System.out.println("To select a command, simply input the number corresponding to the command");
		System.out.println("For e.g. to load applications into the system, simply enter 1");
		System.out.println();
		System.out.println("1. Load applications");
		System.out.println("2. Set the budget");
		System.out.println("3. Make a decision");
		System.out.println("4. Print active, approved and rejected applicants");
		System.out.println("5. Update an application");
		System.out.println("6. Exit");
	}
		
	public int [] getIncome (String [] s) {
		int [] income = new int [30];
		int j = 0;
		for( int i = 4; i < s.length; i++) {
			income[j] = Integer.parseInt(s[i]);
			j++;
		}
		
		return income;
	}
	
	public int calScore (int [] s) {
		int score = 0;
		for( int i = 0; i < s.length; i++ ) {
			score += (s[i]/(i+1));
		}
		return score;
	}
	
	public void setBudget() {
		System.out.println("Enter the budget");
		System.out.print("budget = ");
		budget = getInput();
		
		while( budget < 0) {
			System.out.println("Enter the budget greater than zero");
			System.out.print("budget = ");
			
		}
	}
	
	public int getInput() {
		Scanner read = new Scanner (System.in);
		if (read.hasNextInt())
			return read.nextInt();
		return 0;
	}
	
	public String getInput1() {
		Scanner read = new Scanner (System.in);
		return read.nextLine();
	}
	
	public void loadApplications() {
		activeList.clear();
		rejectedList.clear();
		try {
			// read and write variables
			Scanner read = new Scanner (new File ("application.txt"));
			
			// loading applications
			while(read.hasNextLine()) {
				
				//converting each line into string array
				String [] tokens = read.nextLine().trim().split("\t");
				
				// creating new applicant
				Applicant a1 = new Applicant(tokens[0], tokens[1], tokens[2], tokens[3], calScore(getIncome(tokens)) ,getIncome(tokens) );
				
				// check eligibility by calling method isRejected from Applicant class
				if(a1.isRejected()) {
					
				// add to reject list
					rejectedList.add(a1);
				}
				else {	
				
				// push applicant to priority queue
					activeList.push(a1);
				}
			}
			
			// close files for security
			read.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println("File not found");
			e.printStackTrace();
		}
	}
	
	public void makeDecision() {
		if(activeList.isEmpty()) {
			System.err.println("Please load the applications first");
			return;
		}
				
		if(budget == 0) {
			System.err.println("Please set the budget greater than zero");
			return;
		}
		approvedList.clear();
		int currBudget = budget;
		try {
			FileWriter active = new FileWriter( new File ("active.txt"));
			PriorityQueue<Applicant> activeList1 = new PriorityQueue<Applicant>();
			while(!activeList.isEmpty()) {
				Applicant a1 = activeList.pop();
				if (a1.getLoan() < currBudget) {
					currBudget -= a1.getLoan();
					approvedList.add(a1);
				}
				else {
					activeList1.push(a1);
				}
			}
			activeList = activeList1;
			active.close();
		} catch(IOException e) {
			e.printStackTrace();
		}		
		
					
	}
	
	public void printApplications() {
		try {
			FileWriter active = new FileWriter( new File ("active.txt"));
			FileWriter approved = new FileWriter( new File ("approved.txt"));
			FileWriter reject = new FileWriter( new File ("rejected.txt"));
			Applicant a1 = new Applicant ();
			PriorityQueue<Applicant> activeList1 = new PriorityQueue<Applicant>();
			ArrayList<Applicant> approvedList1 = new ArrayList<Applicant>();
			ArrayList<Applicant> rejectedList1 = new ArrayList<Applicant>();
			
			System.out.println("active.txt");
			while(!activeList.isEmpty()) {
				a1 = activeList.pop();
				active.write(a1.toString("active") + "\n");
				System.out.println(a1.toString("approved"));
				activeList1.push(a1);
			}
			
			System.out.println();
			System.out.println("approved.txt");
			while(!approvedList.isEmpty()) {
				a1 = approvedList.remove(0);
				approved.write(a1.toString("approved") + "\n");
				approvedList1.add(a1);
				System.out.println(a1.toString("approved"));
			}

			System.out.println();
			System.out.println("rejected.txt");
			while(!rejectedList.isEmpty()) {
				a1 = rejectedList.remove(0);
				reject.write(a1.toString("rejected") + "\n");
				rejectedList1.add(a1);
				System.out.println(a1.toString("rejected"));
			}
			activeList = activeList1;
			rejectedList = rejectedList1;
			approvedList = approvedList1;
			
			active.close();
			approved.close();
			reject.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void updateApplications() {
		try {
			
			System.out.println("Please enter the updated applications file name.");
			System.out.println("For eg, if file name is uap.txt then enter full name as uap.txt");
			// read variable
			Scanner read = new Scanner (new File (getInput1()));
			PriorityQueue<Applicant> activeList1 = new PriorityQueue<Applicant>();
			// loading applications
			while(read.hasNextLine()) {
				
				//converting each line into string array
				String [] tokens = read.nextLine().trim().split("\t");
				
				// creating new applicant
				Applicant upApp = new Applicant(tokens[0], tokens[1], tokens[2], tokens[3], calScore(getIncome(tokens)) ,getIncome(tokens) );
				
				// check eligibility by calling method isRejected from Applicant class
				if(activeList.isEmpty()) {
					System.out.println("There is no applicant in the active list");
					run();
				}
				
				while(!activeList.isEmpty()) {
					Applicant a1 = activeList.pop();
					if(a1.equals(upApp)) {
						activeList1.push(upApp);
						System.out.println("Update Successful!");
					}
					else {
						activeList1.push(a1);
					}
				}
				
			}
			activeList = activeList1;
			// close files for security
			read.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println("File not found");
			e.printStackTrace();
		}
	}

}
