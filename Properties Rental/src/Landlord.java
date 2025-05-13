import java.sql.*;

public class Landlord {

    static String id;
    static String password;

   

    

   
    //then come to this mthod  for login or registration
    public static void welcome_L(String option) {
        //welcome to lanlord class 
        System.out.println("welcome Land_lords ");
         //check the attempts 
        int[] sizeAttempts = {0};

        while (true) {
            // then show this options 
            String menu = """
            ===========================================
            |    Welcome to the Lanlord System        |
            ===========================================
            |   Please choose an option:              |
            |-----------------------------------------|
            |   1. Login                              |
            |   2. Sign up/Registration               |
            ===========================================
            """;
            String i;
            //get input
            i = InputHelper.getString(menu+"\n", sizeAttempts);
            // attempts 
            sizeAttempts[0]++;
            //check if exit this will exit to main page 
            if (i.equalsIgnoreCase("exit")) {
                System.out.println("Exiting registration process. Goodbye!");
                System.out.println("------------------------------------------------------------\n");
                App.Appp();
                
            }
            //check null 
            if (i == null) {
                System.out.println("option cannot be empty. Please enter a valid option.");
                System.out.println("------------------------------------------------------------\n");
                welcome_L(option);
               
            }
            
            if (i.equals("1")) {
                Land_lordslogin();
                break;
            } else if (i.equals("2")) {
                System.out.println("enter your details");
                fill_details_DB.start_db_store(option);
                property_Manage(id,password);

            } else {
                
                System.out.println("Wrong input enter again");
               

            }
        }

    }
public static void main(String[] args) {
    // login();
    App.Appp();
}

               //in this metod check valid details for login
             public static void Land_lordslogin() {
          int attempt[]={0};
          int passwordAttempts[]={0};
          while (true) {
        System.out.print("Enter User ID: ");
        String ide = InputHelper.getString("Enter you Landlord_ID:- ", attempt);
        ide=ide.toUpperCase();
        attempt[0]++;
        //check null
        if (ide == null) { 
            System.out.println("this cannot be empty. Please enter a valid integer.\n");
            System.out.println("------------------------------------------------------------\n");
            continue;
           
        }
        //check regex correction 
        if (!ide.matches("^L\\d{4}$")) {
             System.out.println("ID is not valid Format");
 continue;
        }
  //if he enter exit then go back 
        if (ide.equalsIgnoreCase("exit")) {
            System.out.println("Exiting registration process. Goodbye!");
            System.out.println("------------------------------------------------------------\n");
            App.Appp();
            
        }

        try (Connection connection = GetDatabase.GetDb()) {
        
            String checkUserIdQuery = "SELECT password FROM user_table WHERE user_id = ?";
            try (PreparedStatement checkUserIdStmt = connection.prepareStatement(checkUserIdQuery)) {
                checkUserIdStmt.setString(1, ide);
                ResultSet resultSet = checkUserIdStmt.executeQuery();

                if (resultSet.next()) {
                    attempt[0]=0;
                    while (true) { 
                        
                    
                    String passworde = InputHelper.getString("Enter Password: ", passwordAttempts);
                    passwordAttempts[0]++;
                    
                    if (passworde == null) {
                        System.out.println("this cannot be empty. Please enter a valid integer.");
                        System.out.println("------------------------------------------------------------\n");
                       continue;
                        
                    }
                    if (passworde.equalsIgnoreCase("exit")) {
                        System.out.println("Exiting registration process. Goodbye!");
                        System.out.println("------------------------------------------------------------\n");
                        App.Appp();
                        
                    }
                    String correctPassword = resultSet.getString("password");
                    //check password correct 
                    if (correctPassword.equals(passworde)) {
                        String name = Get_Metods.Get_user_name(ide, passworde);
                     System.out.println("Welcome "+name);
                        //if password is right then call this method 
                        land_lord_option(ide, passworde);
                        
                    } else {
                        System.out.println("Password is wrong.");
                    }}
                } else {
                    System.out.println("ID is wrong.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }}
    }
    
    // public static void Land_lordslogin() {
    //     int[] sizeAttempts = {0};
    //     int attempts = 3;

    //     while (attempts > 0) {
    //         while (true) {
    //             id = "";
    //             id = InputHelper.getString("Enter your Lanlord ID:- ", sizeAttempts);
    //             if (id.equalsIgnoreCase("exit")) {
    //                 System.out.println("Exiting registration process. Goodbye!");
    //                 System.out.println("------------------------------------------------------------\n");
    //                 App.Appp();
                    
    //             }
    //             if (id == null) {
    //                 System.out.println("this cannot be empty. Please enter a valid integer.\n");
    //                 System.out.println("------------------------------------------------------------\n");
    //                 Land_lordslogin();
    //                 sizeAttempts[0]++;
    //             }
    //             String name = Get_Metods.Get_user_name(id, password);
    //             String user_id = Get_Metods.Get_user_id(name, password);
    //             String password_me = Get_Metods.Get_user_password(id, name);
    //             Get_Metods.Get_user_password(user_id, name);

    //             password = "";
    //             password = InputHelper.getString("Enter your password:- ", sizeAttempts);
    //             if (password.equalsIgnoreCase("exit")) {
    //                 System.out.println("Exiting registration process. Goodbye!");
    //                 System.out.println("------------------------------------------------------------\n");
    //                 App.Appp();
                    
    //             }
    //             sizeAttempts[0]++;
    //             if (password == null) {
    //                 System.out.println("this cannot be empty. Please enter a valid integer.");
    //                 System.out.println("------------------------------------------------------------\n");
    //                 Land_lordslogin();
                    
    //             }

               

    //             if (id.matches("^L\\d{4}$")) {
                   
    //                 if (!password.equals(password_me)) {
    //                     System.out.println("Wrong Password.");
    //                 } 
    //                 if (id.equals(user_id) && password.equals(password_me)) {
                       
    //                    land_lord_option(id, password);
    //                     break;

    //                 } else {
                   
    //                     System.out.println("Enter right ID and Password");
                     
    //                 }
    //             } else {
    //                 System.out.println("Enter in Valid Format");
                    
    //             }

    //         }

    //     }

    //     System.out.println("Too many Attempts Return  to mainn page");
    //     App.Appp();
    // }

    // show the option of lanlord 

    public static void land_lord_option(String ide,String passwoede) {
        id=ide;
        password=passwoede;
            int[] sizeAttempts = {0};
        while (true) {
            String landlordMenu = """
                ================================================
                |             Welcome to Landlord Menu          |
                ================================================
                | Please choose an option:                      |
                |-----------------------------------------------|
                | 1. Manage Properties                          |
                | 2. Track Rent Status                          |
                | 3. View Tenants Information                   |
                | 4. Request Related Query                      |
                | 5. Add/View Maintenance Staff                 |
                | 6. Create task for Tanents Request            |
                | 7. Message Related Query                      |
                | 8. Logout                                     |
                ================================================
                """;
            //    6. Assign Maintenance Staff to Tenant Request
           

            String option = "";
           //get in put with the help of input helper classs
            option = InputHelper.getString(landlordMenu+"\n", sizeAttempts);
            sizeAttempts[0]++;
             if (option == null) {
                System.out.println("this cannot be null. Please enter a valid option.");
                System.out.println("------------------------------------------------------------\n");
                
                App.Appp();
            }
            //if print exit then back to main meneu
            if (option.equalsIgnoreCase("exit")) {
                System.out.println("Returning to main page.....");
                System.out.println("------------------------------------------------------------\n");
                App.Appp();
                
            }
           
            if (option.matches("^\\d+$")) {
                switch (option) {
                    case "1":
                    sizeAttempts[0]=0;
                    //if he 1 option choose then call  property manage method
                        property_Manage(id,password);
                        break;
                    case "2":
                    sizeAttempts[0]=0;
                    //if he 2 option choose then call this method
                        Get_methods_property_details.Get_rent_status_for_landlord(id);
                        main_menue();
                        break;
                    case "3":
                    sizeAttempts[0]=0;
                    //if he 3 option choose then call this method
                        property_detail_fill_db.view_tanents_information_landlord(id);
                        main_menue();
                        break;
                    case "4":
                    
                    sizeAttempts[0]=0;
                    //if he 4 option choose then call this method
                        Request();
                        main_menue();
                    case "5":
                    sizeAttempts[0]=0;
                    //if he 5 option choose then call this method
                        add_view_maintance();
                        main_menue();
                        break;
                    case "6":
                    sizeAttempts[0]=0;
                    //if he 6 option choose then call this method
                        maintance.assign_request(id);
                        main_menue();
                        break;
                    case "7":
                    sizeAttempts[0]=0;
                    //if he 7 option choose then call this method
                        communication.communication_with_Tanents(id, password);
                        main_menue();
                        break;
                    case "8":
                    sizeAttempts[0]=0;
                    //if he 8 option for logout landdlord 
                        App.Appp();
                        break;
                    default:
                    
                        break;
                }
            } else {
                //ask again 
                System.out.println("Not Valid Option Enter a valid option");
                
                
            }
           
        }
    }
        //if landlord want any request related query 
    public static void Request() {
        //this for attempts 
        int[] sizeAttempts = {0};
        //then show this option
        String requestMenu = """
            ====================================
            |          Request Menu            |
            ====================================
            | Please choose an option:         |
            |----------------------------------|
            | 1. View Requests                 |
            | 2. Approve a Request             |
            | 3. Go Back                       |
            ====================================
            """;
            //then get input  of the option
        String option = InputHelper.getString(requestMenu+"\n", sizeAttempts);
        sizeAttempts[0]++;
        // if he want to go back then type exit
        if (option.equalsIgnoreCase("exit")) {
            System.out.println("return to main page.....");
            System.out.println("------------------------------------------------------------\n");
            Landlord.land_lord_option(id, password);
            
        }

        if (option.equals("1")) {
            //if he choose 1 option then call this method
            Request.view_requesttF(id,"not approved");
            main_menue();
        } else if (option.equals("2")) {
            //if he choose 2 option then call this methods
            //first print the request list 
            Request.view_requesttF(id,"not approved");
            //then call this methiod and ask for request id 
            Request.approved_request_from_landlord(id, password);
        } else if (option.equals("3")) {
            //if he choose 3 option then call this method
            land_lord_option(id, password);
        } else {
            System.out.println("Not vaid option");
          land_lord_option(id, password);
        }

    }
       // if we  choose property manage option
    public static void property_Manage(String ide,String password) {
        int[] sizeAttempts = {0};
        //then show these option
        String propertyMenu = """
            ========================================
            |          Property Management          |
            ========================================
            | Please choose an option:             |
            |--------------------------------------|
            | 1. View Property Details             |
            | 2. Add a New Property                |
            | 3. Go Back to Main Menu              |
            ========================================
            """;
        String option = "";
        option = InputHelper.getString(propertyMenu+"\n", sizeAttempts);
        if (option == null) {
            System.out.println("cannot be null. Please enter a valid number.");
            System.out.println("------------------------------------------------------------\n");
            App.Appp();
        }
        if (option.equalsIgnoreCase("exit")) {
            System.out.println("return to main page.....");
            System.out.println("------------------------------------------------------------\n");
            Landlord.land_lord_option(id, password);
            
        }
        if (option.matches("^\\d+$")) {

            switch (option) {
                case "1":
                //if we choose 1 option then call this method
                    Set_properties_details.Get_properties_detailss_landlord(ide,password);
                    break;
                case "2":
                //if we choose 2 option then call this method
                    property_detail_fill_db.property_table_store_db(ide,password);
                    break;
                    case"3":
                    //if we choose 3 option then call this method
                    land_lord_option(id, password);
                    break;
                default:
                //other wise
                    System.out.println("not valid ");
                    land_lord_option(id, password);
                    break;
            }
        } else {
            property_Manage(id,password);
        }

       land_lord_option(id, password);

    }

    public static void main_menue() {
        int attempt[]={0};

 while (true) { 
    String exitMenu = """
        ============================
        |         Exit Menu         |
        ============================
        | Please choose an option: |
        |--------------------------|
        | 1. Go Back               |
        | 2. Logout                |
        ============================
        """;
  String option=InputHelper.getString(exitMenu+"\n",attempt);
  attempt[0]++;
  if (option.equalsIgnoreCase("exit")) {
    System.out.println("return to main page.....");
    System.out.println("------------------------------------------------------------\n");
   Landlord.land_lord_option(id, password);
    
}
  if (option.matches("^\\d+$")) {
    
    switch (option) {
      case "1":
land_lord_option(id, password);
        break;
    case "2":
    App.Appp();
      default:
      System.out.println("Not valid option");
        break;
    }
  } else {
  System.out.println("Notvalid option");
  }
 }

    }
       //this method for add or view maintaince staff 
    public static void add_view_maintance() {
        int attempt[] = {0};
        //then show this option 
        String maintenanceStaffMenu = """
        ==========================================
        |         Maintenance Staff Menu         |
        ==========================================
        | Please choose an option:              |
        |---------------------------------------|
        | 1. View Maintenance Staff             |
        | 2. Add Maintenance Staff              |
        | 3. Back to Main Menu                  |
        ==========================================
        """;
        //then enter the option 
        String option = InputHelper.getString(maintenanceStaffMenu+"\n", attempt);
        //check null
        if (option == null) {
            System.out.println("cannot be null. Please enter a valid number.");
            add_view_maintance();
        }
        //check  if exit then call the mehotd 
        if (option.equalsIgnoreCase("exit")) {
            System.out.println("return to main page.....");
            System.out.println("------------------------------------------------------------\n");
            Landlord.land_lord_option(id, password);
            
        }
        if (option.equalsIgnoreCase("1")) {
            //if 1 then call the mehtod 
            maintance.view_maintaince(id);
        } else if (option.equalsIgnoreCase("2")) {
            //if 2 then call this method 
            maintance.store_maintace_table_db(id);
        }else if (option.equalsIgnoreCase("3")) {
            //if 3 this  call this method 
           land_lord_option(id, password);
        } else {
            System.out.println("Not valid option");
            add_view_maintance();
        }

    }

}
