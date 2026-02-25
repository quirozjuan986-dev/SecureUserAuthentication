import java.util.Scanner;
import javax.crypto.SecretKey;


public class CreateAccount {


	public static void signUp() throws Exception {
			Scanner kb = new Scanner(System.in); // Scanner
			//Make sure email is a real one
			//Make sure username hasnt been used



			System.out.println("Enter a username:");
			String name = kb.nextLine();
			//While all these statements are true..... 
			while(name.trim().isEmpty() || name.contains(" ") || usernameExist(name)){
				//Check if username is empty
				if(name.trim().isEmpty()){
					System.out.println("Username cannot be empty..");
				} else if (name.contains(" ")){ //Check if username has a space
					System.out.println("Username cannot have a space..");
				} else if (usernameExist(name)){
					System.out.println("Username already exists");
				} 
				name = kb.nextLine();
			}



			// Get and validate password
			String pass = "";
			int passwordScore = 0;

			while (passwordScore < 4) {
				System.out.println("Enter a password (minimum 8 characters):");
				pass = kb.nextLine();
				passwordScore = PasswordStrength.startScan(pass);
                
				//Return a String to inform user of their passwords Strength
				if (passwordScore < 5) {
					System.out.println("Password strength: " + PasswordStrength.getStrengthDescription(passwordScore));
					System.out.println("Password is too weak! Score: " + passwordScore + " (minimum required: 4)");
					System.out.println("Tips: Use uppercase, lowercase, numbers, and special characters.");
				}
			}

			//Get and Validate email
			String email = "";
			while (!Account.isValidEmail(email)) {
				System.out.println("Enter an email address:");
				email = kb.nextLine();
				if (!Account.isValidEmail(email) && !email.trim().isEmpty()) {
					System.out.println("Invalid email format. Please try again.");
				}
			}
			//Store and encrypt account data	
			//Check if a key has been made
			try{
				SecretKey key = Encryptor.loadKeyFromFile(Encryptor.KEY_FILE); 
				String encryptedPassword = Encryptor.encrypt(pass, key); 
				Encryptor.writeToFile(Encryptor.ACCOUNT_FILE, name, encryptedPassword, email);	
				System.out.println("Account is created");
			} catch (Exception e){
				System.out.println("Error saving account: " + e.getMessage());
				e.printStackTrace();
			}
			kb.close();
	}

	public static boolean usernameExist(String username) throws Exception{
		String accountInfo = Encryptor.readAccountFile(Encryptor.ACCOUNT_FILE);
		Scanner fileScanner = new Scanner(accountInfo);
		while (fileScanner.hasNextLine()){
			String tempUsername = fileScanner.next();
			fileScanner.next();
			fileScanner.next(); 
			if(username.equals(tempUsername)){
				return true;
			}
		}
		fileScanner.close();
		return false; 
	}



}