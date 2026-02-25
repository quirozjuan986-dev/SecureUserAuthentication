import java.util.Scanner;
import javax.crypto.SecretKey; 

public class LogIn{
    
    private static Account currentSession = null; 

    public static boolean tryToLogIn() throws Exception{
        // Create variables and scanners
       if (currentSession != null) {
        System.out.println("Already logged in as: " + currentSession.getAccountName());
        return false;
    }
        Scanner kb = new Scanner(System.in); 
        String input = ""; 
        String accountInfo = Encryptor.readAccountFile(Encryptor.ACCOUNT_FILE);
        Scanner file = new Scanner(accountInfo);  

        // Get key from file
        SecretKey key = Encryptor.loadKeyFromFile(Encryptor.KEY_FILE);

        try{    
            System.out.println("What is your username?");
            input = kb.nextLine().trim();    
            
            // Check every username in accountInfo.txt
            while(file.hasNextLine()){
                String username = file.next(); 
                String encryptedPassword = file.next(); 
                String email = file.next(); 
                
                // Username matches user's input
                if (input.equals(username)){ 
                    System.out.println("What is your password?");
                    input = kb.nextLine(); 
                    
                    String password = Encryptor.decrypt(encryptedPassword, key); 
                    
                    if (input.equals(password)){
                        System.out.println("Login successful!"); 
                        currentSession = new Account(username, email, true); // Store user data
                        file.close();
                        kb.close();
                        return true; 
                    } else {
                        System.out.println("Password is incorrect");
                        file.close();
                        kb.close();
                        return false; 
                    }
                }
            }  
            
            System.out.println("Account not found");   
        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        } finally {
            file.close();
            kb.close(); 
        }
        return false; 
    } 
    public static Account getCurrentSession() {
        return currentSession;
}

    public static void logout() {
        currentSession = null;
}

public static boolean isLoggedIn() {
    return currentSession != null;
}

}