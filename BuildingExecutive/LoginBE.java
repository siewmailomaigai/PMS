/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BuildingExecutive;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Hong Shen
 */
public class LoginBE {
    public static String filename;
    public static String checkId;
    public LoginBE(){
        
    }
    
    public LoginBE(String filename) {
        LoginBE.filename = filename;
    }
    
    public boolean validateCredentials(String id,String password){
        try (Scanner scan = new Scanner(new FileReader(filename))) {           
            while (scan.hasNextLine()) {
                String line= scan.nextLine();   
                String[] parts = line.split("\\|");
                String storedId=parts[0];
                String storedPassword=parts[2];
                if(id.equals(storedId)&&password.equals(storedPassword)){
                    return true;
                }
            }
        }catch (IOException e) {
         e.printStackTrace();  
        }
        return false;
    }
    
    public void LoginBE(){
        Scanner scan=new Scanner(System.in);
        String checkPassword;
        do{
            System.out.println("ID: ");
            checkId=scan.nextLine();
            System.out.println("Password: ");
            checkPassword=scan.nextLine();
            if(validateCredentials(checkId, checkPassword)){
                System.out.println("Login successful!");
            }else{
                System.out.println("Wrong ID or password");
            }
        }while(this.validateCredentials(checkId, checkPassword)==false);
    }
}
