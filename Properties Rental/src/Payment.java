import java.sql.*;
import java.util.*;


public class Payment {
  static InputValidator inpute=new InputValidator();
  static InputHelper inputhelp=new InputHelper();
    
    static Scanner input=new Scanner(System.in);

    public static String Land_lords_id_pay() {
        System.out.println("Enter land lord id for payment:");
        String land_lord_id = "";
 int[] sizeAttempts = { 0 };
        while (true) {
          
            land_lord_id = "";
           
            land_lord_id=InputHelper.getString("Enter your land lord id for payment\n",sizeAttempts);
             if (land_lord_id==null) {
                 System.out.println("id cannot be null. Please enter a valid id.");
                 System.out.println("------------------------------------------------------------\n");
                      return   Land_lords_id_pay();
              } 

            if (land_lord_id == null || land_lord_id.trim().isEmpty()) {
                System.out.println("Name cannot be null or empty. Please enter a valid name.");
                System.out.println("------------------------------------------------------------");
                continue;
            }
            if (land_lord_id.equalsIgnoreCase("exit")) {
                System.out.println("Returning to main page.....");
                System.out.println("------------------------------------------------------------\n");
                Tanants.Tanents_option(Tanants.id, Tanants.password);
                return null; 
            }
          String check_id= Get_Metods.landlord_id_payment(land_lord_id);
          if (check_id==null) {
           
          } else if(check_id!=null)
          {
            if (land_lord_id.matches("^L\\d{4}$")) { 
                if (check_id.equals(land_lord_id)) {
                   return land_lord_id; 
                  } else {
                    
                   System.out.println("not found any id enter again");
                   Land_lords_id_pay();
                  }
              
           } else {
               System.out.println("Invalid ID format. The ID must start with 'L' followed by exactly 4 digits.");
           }
          }
        }
    }

   
   public static String payment_ammount(){
    int[] sizeAttempts = { 0 };
  while (true) {
    String payment_ammount="";
   
   payment_ammount=InputHelper.getString("Enter your payment_ammount \n",sizeAttempts);
   sizeAttempts[0]++;
     if (payment_ammount==null||payment_ammount.isEmpty()) {
        System.out.println(" cannot be null OR Empty. Please enter a valid name.");
        System.out.println("------------------------------------------------------------\n");
        continue;
     }
    


    
    if (payment_ammount.matches("^-?\\d+$")) {
      
         if (payment_ammount.equalsIgnoreCase("exit")) {
            System.out.println("Returning to main page.....");
            System.out.println("------------------------------------------------------------\n");
            Tanants.Tanents_option(Tanants.id, Tanants.password);
            return null; 
        }
      
        if (payment_ammount.isEmpty()) {
            System.out.println("Payment  amount cannot be empty. Please enter a valid amount.");
            System.out.println("------------------------------------------------------------\n");
              
        }else if (!payment_ammount.matches("^[1-9][0-9]*$")) {
            System.out.println("The payment Ammount does Not Start With Zero");
           
        } 
        else if (!payment_ammount.matches("^\\d+$")) {
            System.out.println("Invalid input. payment ammount must be a positive integer (e.g., 1000). Please try again.");
            System.out.println("------------------------------------------------------------\n");
            
             
        } else if (Long.parseLong(payment_ammount) > Long.parseLong("1000")) {
              System.out.println("Rent amount successfully validated: " + payment_ammount);
            System.out.println("------------------------------------------------------------\n");
            return payment_ammount;
        }else {
            
          System.out.println("payment ammount is not valid");
         
          continue;
        }
        
    } else {
        System.out.println("emter valid price");
           continue;
    }
    if (sizeAttempts[0]==3) {
        System.out.println("You loose your three Attempts");
        Tanants.Tanents_option(Tanants.id, Tanants.password);
    }
  }
    
   }

   public static String tenant_id(String passworde) {
    
    try {
        

       
       
       
         int[] sizeAttemptss = { 0 };
         String password = "";
         password=InputHelper.getString("Enter Your password for Payment \n",sizeAttemptss);
         if (password==null) {
             System.out.println("password cannot be null. Please enter a valid password.");
             System.out.println("------------------------------------------------------------\n");
                   return   tenant_id(passworde);
          }
           
           if (password.equalsIgnoreCase("exit")) {
             System.out.println("Returning to main page.....");
             System.out.println("------------------------------------------------------------\n");
             Tanants.Tanents_option(Tanants.id, password);
             return null;}
        
        // String name =Get_Metods.Get_user_name(Tanants.id, password);
        
       
    //    String get_password= Get_Metods.Get_user_password(Tanants.id, name);

        
        if (password.equals(Tanants.password)) {
             
            return Tanants.id;
           
        
        } else {
            System.out.println("Wrong password Enter Again . ");
            return tenant_id(passworde);
            
        }
    } catch (Exception e) {
        
        System.out.println("Exception occurred in tenant_id method.");
        e.printStackTrace();
        tenant_id(passworde);
         return tenant_id(passworde);
    }
    
}

public static String payment_method() {
    int[] sizeAttempts = { 0 };
    String payment_method = ""; 
    String validPaymentMethods[] = {"1", "2", "3"};
    
    while (sizeAttempts[0] < 3) {
        payment_method = InputHelper.getString("Choose  your payment method: \n1. Cash\n2. UPI\n3. Check\n",sizeAttempts);
        
        if (payment_method == null) {
            System.out.println("Input cannot be null. Please enter a valid name.");
            System.out.println("------------------------------------------------------------");
            continue;
        }
        if (payment_method.equalsIgnoreCase("exit")) {
            System.out.println("Exiting registration process. Goodbye!");
            System.out.println("------------------------------------------------------------\n");
           Tanants.Tanents_option(Tanants.id, Tanants.password);
          
              break;
        }
        

    
        if (payment_method.equals("1")) {
            return "Cash";
        } else if (payment_method.equals("2")) {
            return "UPI";
        } else if (payment_method.equals("3")) {
            return "Check";
        } else {
            System.out.println("Invalid input. Please enter a valid payment method.");
            sizeAttempts[0]++;
        }
    }
    
    System.out.println("You have exceeded the maximum attempts. Returning null.");
    System.out.println("returning  to login page");
    return null;
}



// public static String  rent_status(){
 

//      while (true) {
//         int[] sizeAttempts = { 0 };
//     String status = ""; // Read input from the user
//     status=InputHelper.getString("Enter rent status \n 1.paid \n 2.unpaid", sizeAttempts);
//     if (status==null) {
//         System.out.println("status  cannot be null. Please enter a valid status.");
//         System.out.println("------------------------------------------------------------\n");
//               return  rent_status();
//      }
//      if (status.matches("^(1|2)$")) {
//         if (status.equalsIgnoreCase("1")) {
//             return "paid";

//         } else if(status.equalsIgnoreCase("2")){
//             return "unpaid";
//         }
//      } else {
//         System.out.println("not valdi  enter again");g
//      }
//      }
    
// }

public static String property_id(){
   
    int attempt[]={0};
    while (true) {
      String property_id=InputHelper.getString("enter property id p1 p2 p3",attempt);
      if (property_id.equalsIgnoreCase("exit")) {
        System.out.println("return to main page.....");
        System.out.println("------------------------------------------------------------\n");
        Tanants.Tanents_option(Tanants.id, Tanants.password);
        return null; 
    }
       if (property_id.matches("P\\d+")) {
          return property_id;
       } else {
          System.out.println("no valid enter again");
          return property_id();
       }
    }
  }

  public static void main(String[] args) {
    fill_payment_details_DB("Sh1234?");
  }
  
     public static void chek_valid_detalis(String tanent_id,  String Land_lords_id_pay, String property_id){
        Connection con= GetDatabase.GetDb();
        String query="select * from `properties_details` where tnaents_id=? and property_id=?and landlord_id=?";
        try {
            PreparedStatement pre= con.prepareStatement(query);
            pre.setString(1, tanent_id);
            pre.setString(2, property_id);
            pre.setString(3, Land_lords_id_pay);
            ResultSet result= pre.executeQuery();
            if (result.next()) {
                System.out.println("yes");
                return;
            } else {
                System.out.println("you write wrong details");
                Tanants.Tanents_option(Tanants.id, Tanants.password);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("try again");
            Tanants.Tanents_option(Tanants.id, Tanants.password);

        }
        finally {
            try {
                if (con != null) con.close();
            } catch (Exception e) {
                System.out.println("exeption in payment detail filkl check deatils");
                e.printStackTrace();
            }
        }
         
     }
    public static void  fill_payment_details_DB(String password){
    Connection con= GetDatabase.GetDb();
    String query ="INSERT INTO `payment_table`  VALUES (NULL, ?, CURRENT_DATE, ?, ?, ?, ?, ?);";
    try {
        String tanent_id, Land_lords_id_pay, property_id;
       PreparedStatement pre= con.prepareStatement(query);
       pre.setString(1,tanent_id=tenant_id(password));
   
       pre.setString(2, payment_ammount());
       
       pre.setString(3, payment_method());
       pre.setString(4, Land_lords_id_pay=Land_lords_id_pay());
       pre.setString(5, "paid");
       pre.setString(6, property_id= maintance_request.property_id_maintace());
            chek_valid_detalis(tanent_id, Land_lords_id_pay, property_id);
         int i= pre.executeUpdate();
        
         if (i>0) {
            System.out.println("succesfully payment done");
         } else {
            System.out.println("failed");
         }
                              
                      
                      
    } catch (Exception e) {
        System.out.println("exeption in payment detail filkl");
       e.printStackTrace();
       System.out.println("try again");
        Landlord.Land_lordslogin();

    }
    finally {
        try {
           
            if (con != null) con.close(); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   
    }

}


 class Get_methods_property_details {
  
    static Connection con;
    static PreparedStatement pre;
    static ResultSet result;
   

static  Landlord landlord=new Landlord();
         //these for track rent sattus for landlord 
   public static String Get_rent_status_for_landlord(String Landlord_id){
    String rent_status;
     con=GetDatabase.GetDb();
    try {
        System.out.println("__________________________________________________________");
        System.out.println( "       Transaction of :- "+Landlord_id+"                   ");
        System.out.println("----------------------------------------------------------");
        pre= con.prepareStatement("select * from payment_table where payment_to=?");
        pre.setString(1, Landlord_id);
       result=   pre.executeQuery();
       if (!result.next()) {
            // if they have no transaction
               System.out.println("You have No Transaction");
         Landlord.land_lord_option(Landlord_id, landlord.password);
        return  null;
       } else {
            do {
                rent_status=result.getString("rent_status");
                String property_id=result.getString("property_id");
                String tanents_id=result.getString("tanent_id");
                String payment_amut=result.getString("payment_ammount");
                String payment_date=result.getString("payment_date");
                String returnBoth = 
                "Property Details:\n" +
                "-----------------\n" +
                "Property ID      : " + property_id + "\n" +
                "Rent Status      : " + rent_status + "\n" +
                "Tenant ID        : " + tanents_id + "\n" +
                "Payment Amount   : " + payment_amut + "\n" +
                "Payment Date     : " + payment_date + "\n";
            // if they have transaction then print 
                System.out.println(returnBoth);
                
            } while (result.next());
       }
       return null;
       
    } catch (Exception e) {
        e.printStackTrace();
         Landlord.land_lord_option(Landlord_id,landlord. password);
         return Get_rent_status_for_landlord(Landlord_id);
    } finally {
        try {
            //in last close the connectionn
            if (result != null) result.close();
            if (pre != null) pre.close();
            if (con != null) con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
   }

public static void main(String[] args) {
   Get_rent_status_for_landlord("L8896");
}
   public static String Get_rent_status_for_Tenatns(String tanent_id){
    String rent_status;
     con=GetDatabase.GetDb();
    try {
        
        pre= con.prepareStatement("select * from payment_table where tanent_id=? ");
        pre.setString(1, tanent_id);
       result=   pre.executeQuery();
       if (!result.next()) {
        System.out.println("You have No Transaction");
        Tanants.Tanents_option(tanent_id, Tanants.password);
         return null;
             
            
             
       } else {
       do {
         rent_status=result.getString("rent_status");
        String property_id=result.getString("property_id");
        String tanents_id=result.getString("tanent_id");
        String Lanlord_id =result.getString("payment_to");
        String returnBoth = 
    "Property Payment Details:\n" +
    "-------------------------\n" +
    "Property ID      : " + property_id + "\n" +
    "Rent Status      : " + rent_status + "\n" +
    "Tenant ID        : " + tanents_id + "\n" +
    "Payment Date     : " + result.getString("payment_date") + "\n" +
    "Payment Amount   : " + result.getString("payment_ammount") + "\n"+ 
    "Payment to       : " + Lanlord_id + "\n" ;

       System.out.println(returnBoth);
       } while (result.next());
       
       }
       return  null;
    } catch (Exception e) {
        e.printStackTrace();
         Landlord.land_lord_option(tanent_id, Tanants.password);
         return Get_rent_status_for_landlord(tanent_id);
    } finally {
        try {
            if (result != null) result.close();
            if (pre != null) pre.close();
            if (con != null) con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
   }
    
}