public class PasswordStrength {

	public static int startScan(String p) {
		//Conditions
		boolean hasUpperCase = false;
		boolean hasLowerCase = false;
		boolean hasSpecialChar = false;
		boolean hasNum = false;

		String specialChar = "!@#$%^&*()_+{}|:<>?[]~";  //Common Special Characters
		int s = 0; //Strength Value

		//Password Length
		if (p.length() < 8) {
			System.out.println("Passwords is Invalid!");
		} else {
			for (int i = 0; i < p.length(); i++) { //Loop thru all char of password
				char currentChar = p.charAt(i);
				//Check if password has an instance of a Upper Case Letter
				if (!hasUpperCase && Character.isUpperCase(currentChar)) {
					hasUpperCase = true;
					s++;
				}
				//Check if password has an instance of a Lower Case Letter
				if (!hasLowerCase && Character.isLowerCase(currentChar)) {
					hasLowerCase = true;
					s++;
				}
				//Check if password has an instance of a Number
				if (!hasNum &&  Character.isDigit(currentChar) ) {
					hasNum = true;
					s+=2;
				}
				//Check if password has an instance of a Special Character
				if (!hasSpecialChar) {
					for (int c = 0; c < specialChar.length(); c++) {
						char currentSpChar = specialChar.charAt(c);
						if (currentChar == currentSpChar) {
							hasSpecialChar = true;
							s += 2;
						}
					}
				}
			} //Close main loop

			//Bonus Points
			//Has multiple positive conditions
			if (hasNum && hasLowerCase && hasUpperCase) {
				s+=2; //Give bonus if it has multiple conditions
			}

			//Password length is greater than 15
			if (p.length() > 15) {
				s+=2;
			}

			//Subtract Points
			//Password is common
			if(isCommonPassword(p)) {
				s -= 2;
			}

			//Has a repetative number
			if(isRepeating(p)) {
				s-=2;
			}
			//Stop Score from being negative
			s = Math.max(0,s);

		}
		return s;
	}

	//Returns a boolean, if password is a common password, return true
	public static boolean isCommonPassword(String p) {
		String passCopy = p.trim().toLowerCase();
		String [] common = {"abcde", "password", "admin", "Qwerty"};
		for(String i: common) {
			if (passCopy.equals(i)) {
				return true;
			}
		}
		return false;
	}

	//Returns boolean, if  i - 1 and i - 2 are the same character as the current character, return true
	public static boolean isRepeating(String p) {
		for (int i = 2; i < p.length(); i++ )
			if ((p.charAt(i) == p.charAt(i - 1)) && (p.charAt(i) == p.charAt(i - 2))) {
				return true;
			}
		return false;
	}

	//Returns a String that gives description of score
	public static String getStrengthDescription(int score) {
		if (score >= 8) return "Strong";
		if (score < 8 && score >= 5) return "Medium";
		if (score < 5) 	return "Weak";
		return "Erorr";


	}
}