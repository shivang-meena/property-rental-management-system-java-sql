import java.sql.*;
import java.util.Scanner;

public class Tanants {
    static String  id;
    static String password ;
  

    static  Scanner input=new Scanner(System.in);
    
public static  void welcome_Ta(String option){
  int attempt[]={0};
       System.out.println("welcome Tanants");
      

       while (true) { 
        String menu =
         """
         ======================================
         |          MAIN MENU                 |
         ======================================
         |  1. Login                          |
         |  2. Sign up/Registration           |
         |                                    |  
         ======================================
         |  Please select an option (1-3):    |
         ======================================""";
          String i=InputHelper.getString(menu+"\n",attempt);
          attempt[0]++;
          if (i.equalsIgnoreCase("exit")) {
            System.out.println("Exiting registration process. Goodbye!");
            System.out.println("------------------------------------------------------------\n");
            App.Appp();
            
        }
        if (i.equals("1")) {
        Tanantslogin();
        break;
       } else if(i.equals("2")){
         System.out.println("Enter your details to Regestration");
      fill_details_DB.start_db_store(option);
       } else{
        System.out.println("Invalid option");
       
       }
       }
    
       
}
public static void main(String[] args) {
  Tanantslogin();
}
public static void Tanantslogin() {
  int[] attempt = {0};
  int[] passwordAttempts = {0};

  while (true) {
      String  ide= InputHelper.getString("Enter your Tenant ID:- ", attempt);
      attempt[0]++;
    
      if (ide == null) {
          System.out.println("This cannot be empty. Please enter a valid ID.\n");
          System.out.println("------------------------------------------------------------\n");
          continue;
      }

      if (!ide.matches("^T\\d{4}$")) {
          System.out.println("ID is not in valid format like T1234.\n");
          continue;
      }

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
                
                
                  while (true) { 
                    String correctPassword = resultSet.getString("password");
                    String passworde = InputHelper.getString("Enter Password: ", passwordAttempts);
                    passwordAttempts[0]++;
                    
  
                    if (passworde == null) {
                        System.out.println("This cannot be empty. Please enter a valid password.");
                        System.out.println("------------------------------------------------------------\n");
                        continue;
                    }
  
                    if (passworde.equalsIgnoreCase("exit")) {
                        System.out.println("Exiting process. Goodbye!");
                        System.out.println("------------------------------------------------------------\n");
                        App.Appp();
                    }
  
                    if (correctPassword.equals(passworde)) {
                        attempt[0] = 0;
                        Tanents_option(ide, passworde);
                    } else {
                        System.out.println("Password is wrong.");
                    }
                  }
              } else {
                  System.out.println("ID is wrong.");
              }
          }
      } catch (SQLException e) {
          System.out.println("Database error: " + e.getMessage());
      }
  }
}

// public static void Tanantslogin() {
   
//     int attempts[] = {0};

//     while (true) {
        

      
//         while (true) {
         
//         id = InputHelper.getString("Enter your ID:- ",attempts);

        
//         password =InputHelper.getString("Enter your password:- ",attempts);
//           attempts[0]++;
//         if (id == null || password == null) {
//             System.out.println("ID or password cannot be null. Please enter a valid ID and password.");
//             System.out.println("------------------------------------------------------------\n");}
        
//         System.out.println("Attempting to fetch user data...");
//         String name = Get_Metods.Get_user_name(id, password);
//         String user_id = Get_Metods.Get_user_id(name, password);
//         String password_me = Get_Metods.Get_user_password(id, name);
//           if (id.matches("^T\\d{4}$")) {
//             if (!id.equals(user_id)) {
//               System.out.println("Wrong ID");
//           }
//           if (!password.equals(password_me)) {
//               System.out.println("Wrong Password.");
//           } 
//               if (id.equalsIgnoreCase(user_id) && password.equalsIgnoreCase(password_me)) {
//              Tanents_option(id, password);
//               }
//          else {
          
//           System.out.println("Attempts left: " + attempts);
//       }
//           } else {
//             System.out.println("Not in valid format. Please enter a valid ID Like T1234,...");
//           }
//         }
          
         
//     }

   
// }

public static void Tanents_option(String ide,String passworde){
  id=ide;
  password=passworde;
  int[] sizeAttempts = { 0 };
  System.out.println("      Welcome Tenants Menu     ");
 

String option="";

 
while (true) {
  String menu = """
                =========================================
                |         Property Management Menu      |
                =========================================
                |  1. View Property Details              |
                |  2. Pay Rent                           |
                |  3. Request Maintenance                |
                |  4. Track Rent Status                  |
                |  5. Communicate with Landlord          |
                |  6. Acquire Property                   |
                |  7. Logout                             |
                =========================================
                |  Please select an option (1-7):       |
                =========================================""";
  option=InputHelper.getString(menu+"\n",sizeAttempts);
  sizeAttempts[0]++;
  if (option==null) {
   System.out.println(" cannot be null. Please enter a valid option.");
   System.out.println("------------------------------------------------------------\n");
      Tanents_option(id, password);
}
if (option.equalsIgnoreCase("exit")) {
  System.out.println("Exiting registration process. Goodbye!");
  System.out.println("------------------------------------------------------------\n");
  App.Appp();
  
}

  if (option != null && option.matches("^\\d+$")) {
    switch (option) {
      case "1":
       property_detail();
       main_menue();
        break;
    case "2":
        Payment.fill_payment_details_DB(password);
       main_menue();
      break;
      case"3":

      maintance_request.store_maintace_table_db();
      main_menue();
        break;
        case"4":
       Get_methods_property_details.Get_rent_status_for_Tenatns(id);
         main_menue();
                      
         case "5":
         communication.communication_with_landlord(id, password);
         main_menue();
         break;
         case "6":
        
         aquire_property();
      
main_menue();
         break;
         case "7":
         App.Appp();
         break;
        default:
        System.out.println("Invalid Option");
    
    }
  } else {

    System.out.println("Invalid Option");
    

  }
 }
              

}
public static void aquire_property(){
 int attempt[]={0};
 String menu =
  """
  =========================================
  |         Property Management Menu      |
  =========================================
  |  1. See properties Details            |
  |  2. Acquire Any Property              |
  |  3. For back                          |
  =========================================
  |  Please select an option (1-7):       |
  =========================================""";
  String option=InputHelper.getString(menu+"\n",attempt);
  if (option==null) {
    if (option.equalsIgnoreCase("exit")) {
      System.out.println("Returning to main page...");
      System.out.println("------------------------------------------------------------\n");
     Tanents_option(id, password);
     
  }
    System.out.println(" cannot be null. Please enter a valid option.");
    System.out.println("------------------------------------------------------------\n");
            aquire_property();}
            switch (option) {
                case  "1":
                String status="not_occupied";
                Set_properties_details.Get_properties_detailss_Tanents(status,null);
                    main_menue();
                    break;
                    case "2":
                     status="not_occupied";
                    Set_properties_details.Get_properties_detailss_Tanents(status,null);
                   property_detail_fill_db.acquire_property(id);
                    main_menue();
                   break;
                default:
                Tanents_option(id, password);
                    break;
            }
  property_detail_fill_db.acquire_property(id);

}

public static void main_menue(){
  int attempt[]={0};

 while (true) { 
  String menu =
   """
   =========================================
   |          Navigation Menu              |
   =========================================
   |  1. Back to Previous Menu              |
   |  2. Logout                            |
   =========================================
   |  Please select an option (1-2):       |
   =========================================""";
  String option=InputHelper.getString(menu+"\n",attempt);
  attempt[0]++;
  if (option.matches("^\\d+$")) {
    
    switch (option) {
      case "1":
    Tanents_option(id, password);
        break;
    case "2":
    App.Appp();
      default:
      System.out.println("not valid option");
        break;
    }
  } else {
  System.out.println("not valid option");
  }
 }

}
public static void property_detail(){
  int attempt[]={0};
  String status;
  String menu = """
                =========================================
                |         Property View Options         |
                =========================================
                |  1. See Not Occupied Properties       |
                |  2. See Your Occupied Property        |
                |  3. Back                              |
                =========================================
                |  Please select an option (1-3):       |
                =========================================""";
  
  String option=InputHelper.getString(menu+"\n",attempt);
  if (option.equalsIgnoreCase("exit")) {
    System.out.println("return to main page.....");
    System.out.println("------------------------------------------------------------\n");
    Tanents_option(id, password);
    
}
  switch (option) {
  case "1":
  status="not_occupied";
    Set_properties_details.Get_properties_detailss_Tanents(status,null);
    main_menue();
    break;
  case "2": 
  status="occupied";
  Set_properties_details.Get_properties_detailss_Tanents(status,id);
  main_menue();
  break;
  default:
    break;
}
  
  
}



}

