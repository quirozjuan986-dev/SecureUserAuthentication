# SecureAccountManager
This program is a way for me to better understand encryption methods as AES, understand password strength, and file based credential log in. 

# Overview #
1) Creates an account
2) Validate strength of password
3) Encrypt said password using AES
4) Store credentials in a file

# Tech Used #
1) Java
2) javax.crypto (AES encryption)
3) File Writter and Reader
4) Buffered Reading
5) Base 64 Encoding
6) Object Oriented Programming fundamentals

# Password Strength Algorithm # 
  Evaluted by checking ..
  * Password length
  * Upper and Lower characters
  * Numbers
  * Special Characters
  * Repeatative Characters
  * Common password detection
  Assign a strength with a description
  * Strong
  * Medium
  * Weak
  * Very Weak

# Project Structure #
Main.java <- Program entry point
Account.java <- Account Object Structure
CreateAccount.java <- Creates an Account
LogIn.java <- Log in authentication 
PasswordStrength <- Determine strength of password
Encryptor.java <-  Manages keys, encrypts password, reads account info

# Requirements to run code #
Create 2 txt files -> go to Encryptor.java and change redirectory

# Future Improvements #
1) Improve user interface
2) Add hashing password storage
3) Add analytical tools

# Author #
Juan Quiroz - beanDip986
quirozjuan986@gmail.com


