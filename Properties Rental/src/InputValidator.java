import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidator {
    private final Scanner scanner;

    public InputValidator() {
        this.scanner = new Scanner(System.in);
    }

        // ===========================================================================================================================
        // ======================================================-pan number vlaidati-==================================================
        // ===========================================================================================================================
    
   //check valid pan no 
        public String validPanNumber() {
        int[] panAttempts = { 0 };
        int menuAttempts = 0; 
    
           //check the valid patter for pan number 
        Pattern panPattern = Pattern.compile("^[A-Z]{5}\\d{4}[A-Z]{1}$");
    
        while (true) {
            //ask for pan number 
            String panNumber = InputHelper.getString("Enter PAN number : ",panAttempts);
            System.out.println("------------------------------------------------------------\n");
            panNumber=panNumber.toUpperCase();
            //check if not null 
            if (panNumber != null) {
    
                 //if exit then go to main page 
                if (panNumber.equalsIgnoreCase("exit")) {
                    System.out.println("Exiting registration process. Goodbye!");
                    System.out.println("------------------------------------------------------------\n");
                    App.Appp();
                    return null; 
                }
    
                   //mathcer class matches the pattern compare the input 
                Matcher matcher = panPattern.matcher(panNumber);
                if (matcher.matches()) {
                    //get db connection 
                    Connection c=GetDatabase.GetDb();
                    //pan nnumer to upeercase
                    panNumber = panNumber.toUpperCase();
                    try {
                        // check pan number already in db or not 
                      PreparedStatement prepare=   c.prepareStatement("select * from user_table where Pan_no=?");
                      prepare.setString(1,panNumber );
                      ResultSet reesult=prepare.executeQuery();
                      if (!reesult.next()) {
                        return panNumber;
                      } else {
                         //
                        System.out.println("Pan number is already aquired. enter unique pannumber");
                        panAttempts[0]++;
                        continue;
                      }
        
                    } catch (Exception e) {
                     System.out.println("exeption in maintance id methoed");
                     e.printStackTrace();
                     Landlord.Land_lordslogin();
                    }
                }
    
              
                panAttempts[0]++;
            // if wrong then suggest this for pan number 
                System.out.println("\nInvalid PAN number. Ensure it meets the following criteria:");
                System.out.println("1. PAN number should be in the format XXXXX9999X.");
                System.out.println("   - The first 5 characters should be uppercase letters.");
                System.out.println("   - The next 4 characters should be digits.");
                System.out.println("   - The last character should be an uppercase letter.\n");
                System.out.println("Example: ABCDE1234F\n");
                System.out.println("------------------------------------------------------------\n");
            }
    
           
            if (panAttempts[0] >= 3) {
                System.out.println("You've entered an incorrect PAN number 3 times.");
                System.out.println("------------------------------------------------------------\n");
    
                while (true) {
                    System.out.println("Please choose an option:");
                    System.out.println("1. Try Again");
                    System.out.println("2. Back to Main Menu");
                    System.out.print("Enter your choice: ");
    
                    String choice = scanner.nextLine().trim();
                    System.out.println("------------------------------------------------------------\n");
    
                    if ("1".equals(choice)) {
                        panAttempts[0] = 0; 
                        menuAttempts = 0; 
                        break; 
                    } else if ("2".equals(choice)) {
                        System.out.println("Returning to the main menu...");
                        System.out.println("------------------------------------------------------------\n");
                        App.Appp();
                        return null; 
                    } else {
                        System.out.println("Invalid choice...");
                        System.out.println("------------------------------------------------------------\n");
                        menuAttempts++;
    
                        if (menuAttempts >= 3) {
                            System.out.println("Too many invalid choices. Returning to the main menu...");
                            System.out.println("------------------------------------------------------------\n");
                            App.Appp();
                            return null; 
                        }
                    }
                }
            }
        }
    }
    
    // ===========================================================================================================================
    // ======================================================-Phone no vlaidati-==================================================
    // ===========================================================================================================================

    public String validPhoneNumber() {
        int[] phoneAttempts = { 0 }; 
        int menuAttempts = 0; 
    

        Pattern phonePattern = Pattern.compile("^(\\+\\d{1,4}\\s?)?\\(?\\d{1,4}\\)?[-\\s]?\\d{1,4}[-\\s]?\\d{1,4}[-\\s]?\\d{1,4}$");
    
        while (true) {
            String phoneNumber = InputHelper.getString("Enter phone number : ",phoneAttempts);
            phoneAttempts[0]++;
            System.out.println("------------------------------------------------------------\n");
    
            if (phoneNumber != null) {
    
                
                if (phoneNumber.equalsIgnoreCase("exit")) {
                    System.out.println("Exiting registration process.");
                    System.out.println("------------------------------------------------------------\n");
                    App.Appp();
                    return null; 
                }
             if (!phoneNumber.matches("^(\\+91[\\-\\s]?)?[6-9][0-9]{9}$")) {
              System.out.println("Phone nummber are start with only 6,7,8 and 9");
                continue;
             }
            
                if (phoneNumber.matches("^\\d{10}$")) {
                    phoneNumber = "+91" + phoneNumber;
                }
    
                
                Matcher matcher = phonePattern.matcher(phoneNumber);
                if (matcher.matches()) {
                    Connection c=GetDatabase.GetDb();
                    phoneNumber = phoneNumber.toLowerCase();
                    try {
                      PreparedStatement prepare=   c.prepareStatement("select * from user_table where phone_no=?");
                      prepare.setString(1,phoneNumber );
                      ResultSet reesult=prepare.executeQuery();
                      if (!reesult.next()) {
                        return phoneNumber;
                      } else {
                        System.out.println("phone is already aquired. enter unique phone number");
                       
                        continue;
                      }
        
                    } catch (Exception e) {
                     System.out.println("exeption in maintance id methoed");
                     e.printStackTrace();
                     Landlord.Land_lordslogin();
                    }
                }
    
                
                phoneAttempts[0]++;
                System.out.println("\nInvalid phone number. Ensure it meets the following criteria:");
                System.out.println("enter only 10 digit number ");
                System.out.println("------------------------------------------------------------\n");
            }
    
          
            if (phoneAttempts[0] >= 3) {
                System.out.println("You've entered an incorrect phone number 3 times.");
                System.out.println("------------------------------------------------------------\n");
    
                while (true) {
                    System.out.println("Please choose an option:");
                    System.out.println("1. Try Again");
                    System.out.println("2. Back to Main Menu");
                    System.out.print("Enter your choice: ");
    
                    String choice = scanner.nextLine().trim();
                    System.out.println("------------------------------------------------------------\n");
    
                    if ("1".equals(choice)) {
                        phoneAttempts[0] = 0;
                        menuAttempts = 0; 
                        break; 
                    } else if ("2".equals(choice)) {
                        System.out.println("Returning to the main menu...");
                        System.out.println("------------------------------------------------------------\n");
                        App.Appp();
                        return null; 
                    } else {
                        System.out.println("Invalid choice...");
                        System.out.println("------------------------------------------------------------\n");
                        menuAttempts++;
    
                        if (menuAttempts >= 3) {
                            System.out.println("Too many invalid choices. Returning to the main menu...");
                            System.out.println("------------------------------------------------------------\n");
                            App.Appp();
                            return null; 
                        }
                    }
                }
            }
        }
    }
    
           
    // ===========================================================================================================================
    // ======================================================-ROLE-VALIDATION-====================================================
    // ===========================================================================================================================

    public String getvalidRole(String role) {
        int[] roleAttempts = { 0 }; 
        int menuAttempts = 0; 
  
        while (true) {
            
             //check roll is not null
            if (role != null) {

                System.out.println("------------------------------------------------------------\n");

               // if chose option 1 then landlord registration
                if (role.equalsIgnoreCase("1") ) {
                    return "Landlord"; 
                }

                // if chose option 2 for tanents      
                if (role.equalsIgnoreCase("2") ) {
                    return "Tanents"; 
                }  
               
               
                roleAttempts[0]++;
                //else invalid role then print this 
                System.out.println("\nInvalid role.'.");
                System.out.println("------------------------------------------------------------\n");
            }
            //check attempts 
            if (roleAttempts[0] >= 3) {
                while (true) {
                    //if three attempts are completed then ask try again or back 
                    System.out.println("You have entered an incorrect role 3 times.");
                    System.out.println("1. Try Again");
                    System.out.println("2. Back to Menu");
                    System.out.print("Enter your choice: ");

                    int choice;

                    try {
                        choice = Integer.parseInt(scanner.nextLine().trim());

                    } catch (NumberFormatException e) {
                        System.out.println("------------------------------------------------------------\n");
                        System.out.println("Invalid choice. Please enter a valid number (1 or 2).");
                        System.out.println("------------------------------------------------------------\n");

                        menuAttempts++;
                        if (menuAttempts >= 3) {
                            //if attemps is grater than 3 then go to main page 
                            System.out.println("Too many invalid choices. Returning to main menu...");
                            System.out.println("------------------------------------------------------------\n");
                            App.Appp();
                            return null; 
                        }
                        continue;
                    }
                    //this switch case for chcek try again or back 
                    switch (choice) {
                        case 1: 
                            roleAttempts[0] = 0; 
                            menuAttempts = 0; 
                            break;
                        case 2: 
                        //if to then go to main page 
                            System.out.println("Returning to menu...");
                            System.out.println("------------------------------------------------------------\n");
                            App.Appp();
                            return null; 
                        default: 
                            System.out.println("Invalid choice....");
                            System.out.println("------------------------------------------------------------\n");
                                     //  menue for the back in third attempts 
                            menuAttempts++;
                            if (menuAttempts >= 3) {
                                System.out.println("Too many invalid choices. Returning to main menu...");
                                System.out.println("------------------------------------------------------------\n");
                                App.Appp();
                                return null;
                            }
                            continue;
                    }

                    break; 
                }
            }
        }
    }

    // ===========================================================================================================================
    // ========================================================-EMAIL-VALIDATION-=================================================
    // ===========================================================================================================================

    public String getEmail() {
        String email;
        int[] emailAttempts = { 0 };
        int menuAttempts = 0; 

        while (true) {
            email = InputHelper.getString("Enter your email : ",emailAttempts);
            System.out.println("------------------------------------------------------------\n");
            if (email != null) {

                
                if (email.equalsIgnoreCase("exit")) {
                    System.out.println("Exiting registration process. Goodbye!");
                    System.out.println("------------------------------------------------------------\n");
                    App.Appp();
                    return null; 
                }
                

              
                if (email.matches("^[a-z0-9._%+-]+@(yahoo|gmail)\\.(com|org)$")) {
                    Connection c=GetDatabase.GetDb();
                    email = email.toLowerCase();
                    try {
                      PreparedStatement prepare=   c.prepareStatement("select * from user_table where email=?");
                      prepare.setString(1,email );
                      ResultSet reesult=prepare.executeQuery();
                      if (!reesult.next()) {
                        return email;
                      } else {
                        System.out.println("email is already aquired. enter unique email");
                        emailAttempts[0]++;
                        continue;
                      }
        
                    } catch (Exception e) {
                     System.out.println("exeption in maintance id methoed");
                     e.printStackTrace();
                     Landlord.Land_lordslogin();
                    }
                }

              
                emailAttempts[0]++;
                System.out.println("\n------------------------------------------------------------");

                System.out.println("Invalid email format. Please ensure it matches the format:");
                System.out.println("1. Must contain only lowercase letters, numbers, and special characters (. _ % + -).");
                System.out.println("Example: enter a valid email like keshavvishwkarma1234(@gmail.com/@yahoo.com)");
                System.out.println("------------------------------------------------------------\n");

            }
           
            if (emailAttempts[0] >= 3) {
                System.out.println("You've entered an incorrect email 3 times.");
                System.out.println("------------------------------------------------------------\n");
                while (true) {

                    System.out.println("Please choose an option:");
                    System.out.println("1. Try Again");
                    System.out.println("2. Back to Main Menu");
                    System.out.print("Enter your choice: ");

                    String choice = scanner.nextLine().trim();
                    System.out.println("------------------------------------------------------------\n");

                    if ("1".equals(choice)) {
                        emailAttempts[0] = 0; 
                        menuAttempts = 0; 
                        break; 
                    } else if ("2".equals(choice)) {
                        System.out.println("Returning to the main menu...");
                        System.out.println("------------------------------------------------------------\n");
                        App.Appp();
                        return null; 
                    } else {
                        System.out.println("Invalid choice...");
                        System.out.println("------------------------------------------------------------\n");
                        menuAttempts++;

                        if (menuAttempts >= 3) {
                            System.out.println("Too many invalid choices. Returning to the main menu...");
                            App.Appp();
                            System.out.println("------------------------------------------------------------\n");
                            return null; 
                        }
                    }
                }
            }
        }
    }

    // ===========================================================================================================================
    // ========================================================-NAME-VALIDATION-==================================================
    // ===========================================================================================================================

    public String getvalidName() {
        int[] nameAttempts = { 0 };

        while (true) {
            String name = InputHelper.getString("Enter your  Full name : ",nameAttempts);
            System.out.println("------------------------------------------------------------\n");
           
            if (name != null) {
                

                if (name.isEmpty()) {
                    System.out.println("Name cannot be empty. Please enter a valid name.");
                    System.out.println("------------------------------------------------------------\n");

                } else if (!name.matches("^[a-zA-Z]+\\s[a-zA-Z]+$")) {
                    System.out.println("Name can only contain alphabets and one  space. Please try again.");
                    System.out.println("OR Enter your Full Name");
                    System.out.println("------------------------------------------------------------\n");

                } else if (name.length() < 3 || name.length() > 40) {
                    System.out.println("Name must be between 3 and 40 characters. Please try again.");
                    System.out.println("------------------------------------------------------------\n");

                } else {
                    return name; 
                }
                nameAttempts[0]++;
            }
         
            if (nameAttempts[0] >= 3) {
                int menuAttempts = 0;

                while (true) {
                    System.out.println("Too many invalid name attempts.");
                    System.out.println("1. Try Again");
                    System.out.println("2. Back to Menu");
                    System.out.print("Enter your choice: ");

                    int choice;

                    try {
                        choice = Integer.parseInt(scanner.nextLine().trim());
                        System.out.println("------------------------------------------------------------\n");

                    } catch (NumberFormatException e) {
                        System.out.println("Invalid choice. Please enter 1 or 2.");
                        System.out.println("------------------------------------------------------------\n");

                        menuAttempts++;

                       

                        if (menuAttempts >= 3) {
                            System.out.println("Too many invalid menu choices. Returning to main menu...");
                            System.out.println("------------------------------------------------------------\n");
                               App.Appp();
                            return null;
                        }
                        continue;
                    }

                    switch (choice) {
                        case 1: 
                            nameAttempts[0] = 0;
                            break;
                        case 2: 
                            System.out.println("Returning to main menu...");
                            System.out.println("------------------------------------------------------------\n");
                            App.Appp();
                            return null; 
                        default: 
                            System.out.println("Invalid choice. Please enter 1 to retry or 2 to exit.");
                            System.out.println("------------------------------------------------------------\n");
                            menuAttempts++;

                            if (menuAttempts >= 3) {
                                System.out.println("Too many invalid menu choices. Returning to main menu...");
                                System.out.println("------------------------------------------------------------\n");
                                App.Appp();
                                return null; 
                            }
                            continue;
                    }

                    break;
                }
            }
        }
    }

    // ===========================================================================================================================
    // ========================================================-PASSWORD-VALIDATION-==============================================
    // ===========================================================================================================================
// public static void main(String[] args) {
//     InputValidator input=new InputValidator();
//     System.out.println(input.validPassword());
// }
    public String validPassword() {
        int[] passwordAttempts = { 0 }; 
        int menuAttempts = 0; 

       
        Pattern passwordPattern = Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&#])[A-Za-z\\d@$!%*?&#]{6,10}$");
            
        while (true) {
            String password = InputHelper.getString("Enter password : ",passwordAttempts);
            System.out.println("------------------------------------------------------------\n");
            if (password != null) {

                
                if (password.equalsIgnoreCase("exit")) {
                    System.out.println("Exiting registration process. Goodbye!");
                    System.out.println("------------------------------------------------------------\n");
                    App.Appp();
                    return null; 
                }
                
                Matcher matcher = passwordPattern.matcher(password);
                if (matcher.matches()) {
                    return  password;
                   
                }
                

               
                passwordAttempts[0]++;
                System.out.println("\nInvalid password. Ensure it meets the following criteria:");
                System.out.println("1. Contains at least (one uppercase and one lowercase).");
                System.out.println("2. Contains at least one number.");
                System.out.println("3. Contains at least one special character (@, $, !, %, *, ?, &, #).");
                System.out.println("4. Length must be between 6 and 10 characters.\n");
                System.out.println("------------------------------------------------------------\n");
            }
           
            if (passwordAttempts[0] >= 3) {
                System.out.println("You've entered an incorrect password 3 times.");
                System.out.println("------------------------------------------------------------\n");
                while (true) {
                    System.out.println("Please choose an option:");
                    System.out.println("1. Try Again");
                    System.out.println("2. Back to Main Menu");
                    System.out.print("Enter your choice: ");

                    String choice = scanner.nextLine().trim();
                    System.out.println("------------------------------------------------------------\n");

                    if ("1".equals(choice)) {
                        passwordAttempts[0] = 0;
                        menuAttempts = 0; 
                        break; 
                    } else if ("2".equals(choice)) {
                        System.out.println("Returning to the main menu...");
                        System.out.println("------------------------------------------------------------\n");
                        App.Appp();
                        return null; 
                    } else {
                        System.out.println("Invalid choice...");
                        System.out.println("------------------------------------------------------------\n");
                        menuAttempts++;

                        if (menuAttempts >= 3) {
                            System.out.println("Too many invalid choices. Returning to the main menu...");
                            System.out.println("------------------------------------------------------------\n");
                            App.Appp();
                            return null; 
                        }
                    }
                }
            }
        }
    }



// ===========================================================================================================================
    // =====================================================-address -========================================================
    // ===========================================================================================================================

public static void main(String[] args) {
   InputValidator in=new InputValidator();
   System.out.println(in.getValidAddress());
}
public String getValidAddress() {
   
    int[] addressAttempts = { 0 }; 

    while (true) {
        String address = InputHelper.getString("Enter your address: ",addressAttempts);
        System.out.println("------------------------------------------------------------\n");

        
        if (address != null) {
            if (address.equalsIgnoreCase("exit")) {
                System.out.println("Exiting registration process. Goodbye!");
                System.out.println("------------------------------------------------------------\n");
                App.Appp();
                return null; 
            }


            if (address.isEmpty()) {
                System.out.println("Address cannot be empty. Please enter a valid address.");
                System.out.println("------------------------------------------------------------\n");

            } 
         
            else if (!address.matches("^[a-zA-Z0-9,\\\\.]+( [a-zA-Z0-9,\\\\.]+)*$")) {
                System.out.println("Address can only contain letters, numbers,one  spaces, commas, and periods. Please try again.");
                System.out.println("------------------------------------------------------------\n");

            } 
           
            else if (address.length() < 10 || address.length() > 200) {
                System.out.println("Address must be between 10 and 200 characters. Please try again.");
                System.out.println("------------------------------------------------------------\n");

            } else {
                return address; 
            }

            addressAttempts[0]++;
        }

       
        if (addressAttempts[0] >= 3) {
            int menuAttempts = 0;

            while (true) {
                System.out.println("Too many invalid address attempts.");
                System.out.println("1. Try Again");
                System.out.println("2. Back to Menu");
                System.out.print("Enter your choice: ");

                int choice;

                try {
                    choice = Integer.parseInt(scanner.nextLine().trim());
                    System.out.println("------------------------------------------------------------\n");

                } catch (NumberFormatException e) {
                    System.out.println("Invalid choice. Please enter 1 or 2.");
                    System.out.println("------------------------------------------------------------\n");

                    menuAttempts++;

                   
                    if (menuAttempts >= 3) {
                        System.out.println("Too many invalid menu choices. Returning to main menu...");
                        System.out.println("------------------------------------------------------------\n");
                        App.Appp();
                        return null; 
                    }
                    continue;
                }

                switch (choice) {
                    case 1: 
                        addressAttempts[0] = 0;
                        break;
                    case 2:
                        System.out.println("Returning to main menu...");
                        System.out.println("------------------------------------------------------------\n");
                        App.Appp();
                        return null; 
                    default: 
                        System.out.println("Invalid choice. Please enter 1 to retry or 2 to exit.");
                        System.out.println("------------------------------------------------------------\n");
                        menuAttempts++;

                        if (menuAttempts >= 3) {
                            System.out.println("Too many invalid menu choices. Returning to main menu...");
                            System.out.println("------------------------------------------------------------\n");
                          App.Appp();
                            return null; 
                        }
                        continue;
                }

                break; 
            }
        }
    }
}
}