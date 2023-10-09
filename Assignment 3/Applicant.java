import java.util.ArrayList;

public class Applicant implements Comparable<Applicant> {
	private String name;
	private int score;
	private int loan;
	private int education;
	private int experience;
	private int [] income;
	// add the rest of fields (education, experience, annual profit)
	
	//Complete this constructor 
	// you should calculate this.score based on estimated annual profit
	public Applicant () {
		this.name = "";
		this.education = 0;
		this.experience = 0;
		this.score = 0;
		this.loan = 0;
		this.income = new int [30];
	}
	public Applicant(String name, String loan, String education, String experience, int score, int [] income) {
		//Complete this constructor
		// you should calculate this.score based on estimated annual profit
		this.name = name;
		this.education = Integer.parseInt(education);
		this.experience = Integer.parseInt(experience);
		this.score = score;
		this.loan = Integer.parseInt(loan);
		this.income = income;
	}
	
	
	public int compareTo(Applicant other){
//		if (this.score > other.score)
//			return 1;
//		else if (this.score < other.score)
//			return -1;
//		else
//			return 0;
		// you can just write it in one line:
		return this.score - other.score;
	}
	
	// this method will be used to find an applicant
	public boolean equals(Applicant other) {
		return this.name.equals(other.name);
	}
	
	public boolean isRejected() {
		return (education + experience) < 10;
	}
	
	public int getLoan () {
		return loan;
	}
	
	public int getScore() {
		return score;
	}
	
	public String getName() {
		return name;
	}

	
	public String toString(String s) {
		//complete this method
		//it will be useful for print option in class Loan
		if(s == "active")
			return name + "\t" + String.valueOf(loan) + "\t" + String.valueOf(score);
		return name + "\t" + String.valueOf(loan);
	}
	
	
	// add any other method you need
}
