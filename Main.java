import java.util.Scanner;


public class Main {
	public static void main(String[]args) throws Exception {
		Scanner kb = new Scanner(System.in);
		String input = "";

		System.out.println("Please log in(l) or create an account(c)");
		input = kb.nextLine().trim().toLowerCase();
		while(true)
		{
			if (input.equals("l")) {
				LogIn.tryToLogIn();
				break;
			} else if (input.equals("c")) {
				CreateAccount.signUp();
				break;
			} else {
				System.out.println("Invalid input, please try again");
				input = kb.nextLine();
			}

		}
		kb.close();


	}
}
