public class Account {

	private String accountEmail;
	private String accountName;
	private boolean isLoggedIn; 

	// Construct Account Object - for sign up
	public Account(String n, String e) {
		accountName = n;
		accountEmail = e;
	}
	//Contructor that is logged in
	public Account(String n, String e, boolean t){
		accountName = n; 
		accountEmail = e; 
		isLoggedIn = t; 
		//ADD HERE IF U WANT TO DO SOMETHING WITH THIS!!!
	}
	//Get Methods
	public String getAccountEmail() {
		return accountEmail;
	}

	public String getAccountName() {
		return accountName;
	}
	
	//Checks if email is valid
	public static boolean isValidEmail(String email) {
		return email.contains("@") && email.contains(".") && email.length() > 5;
	}

	//toString Method
	public String toString() {
		return "Account name: " + accountName + " email: " + accountEmail;

	}
}
