import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermissions;


public class Encryptor{
  //Variables
    final static String KEY_FILE = "secret.key";
    final static String ACCOUNT_FILE = "account.txt";  
  
    //Key methods
    // Generates key
    public static SecretKey generateKey() throws Exception{
        KeyGenerator keyGen = KeyGenerator.getInstance("AES"); //Gets for encryption (AES)
        keyGen.init(128);                                      //Initate key generator
        return keyGen.generateKey();                           //Generate key
    }
    
    //Saves the key to a File
    public static void saveKeytoFile(String filename, SecretKey key) throws Exception {
        //Encode key to Base64 string
        String encodedKey = Base64.getEncoder().encodeToString(key.getEncoded()); 

        //Write key into file (auto-close writer)
        try (FileWriter w = new FileWriter(filename)) {
            w.write(encodedKey);
        }

        //Attempt to set restrictive permissions; ignore if filesystem doesn't support POSIX
        try {
            Files.setPosixFilePermissions(Paths.get(filename), PosixFilePermissions.fromString("rw-------"));
        } catch (UnsupportedOperationException | IOException ex) {
            // On Windows or unsupported FS, POSIX permissions are not available — ignore
        }
    }
    
    //Loads key from file
    public static SecretKey loadKeyFromFile(String filename) throws Exception{
    try{ 
        //Creates StringBuilder to store encoded key - Allows String to be mutable 
        StringBuilder encodedKey = new StringBuilder(); 
        //Grabs each line of the key that is in the file - Stops when null(Read thru everything)
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))){
            String line; 
            while((line = reader.readLine()) != null){
                encodedKey.append(line); 
            }
        }
        if (encodedKey.length() == 0) {
            throw new IOException("Key file is empty");
        }
        //Decode entire key - allows for reuse later on
        byte[] decodedKey = Base64.getDecoder().decode(encodedKey.toString());         
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
    } catch (IOException e){
        System.out.println("Key not found, creating one....");  
        SecretKey newKey = generateKey(); 
        System.out.println("Generated successfully");
        saveKeytoFile(filename, newKey);
        System.out.println("Saved key to file");
        return newKey;
    }
}
//Encryption/Decryption
    //Encrypt password and reuses kay
    public static String encrypt(String p, SecretKey key) throws Exception{ //Takes in key and password
        Cipher eCipher = Cipher.getInstance("AES");                         //Gets tool for encryption with AES
        eCipher.init(Cipher.ENCRYPT_MODE,key);                              //set itself to encrypt mode and gets the key
        byte[] encryptedBytes = eCipher.doFinal(p.getBytes());              //encryps and converts String into random bytes
        return Base64.getEncoder().encodeToString(encryptedBytes);          //coverts bytes into random String
    }
     
    //Decrypt password
    public static String decrypt(String cipherText, SecretKey key) throws Exception{
        Cipher dCipher = Cipher.getInstance("AES"); 
        dCipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedBytes = dCipher.doFinal(Base64.getDecoder().decode(cipherText)); 

        return new String(decryptedBytes); 
    }

    //Write encrypted account into file
    public static void writeToFile(String filename, String username, String pass, String email)throws IOException{
        //Create fileWrite - have it write onto given file
        try (FileWriter w = new FileWriter(filename, true)) { 
            w.write((username + " " + pass + " " + email) + System.lineSeparator()); 
        }
    }

//Return account info - encrypted
      public static String readAccountFile(String filename) throws Exception{
        //Creates StringBuilder to store encoded key - Allows String to be mutable 
        String accountInfo = "";  

        //Grabs each line of the key that is in the file - Stops when null(Read thru everything)
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))){
            String line; 
            while((line = reader.readLine()) != null){
                accountInfo += line + " "; 
            }
        }
        
        //return    
        return accountInfo;
    }


}
