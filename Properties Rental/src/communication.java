import java.sql.*;


public class communication {

    public static void communication_with_landlord(String id,String password){
      int attempt[]={0};
      int attempte[]={0};
      if (attempte[0]==3) {
        System.out.println("You loose your three attempts");
        Tanants.Tanents_option(id, password);
      }
      String message = """
                       ==========================================c          x
                       |         Message-related Query         |
                       ==========================================
                       | Please choose an option:              |
                       |---------------------------------------|
                       | 1. Send message to landlord           |
                       | 2. View messages                      |
                       | 3. For navigating back                |
                       ==========================================""";
      String option=InputHelper.getString(message+"\n",attempt);
   
    if (option.equalsIgnoreCase("exit")) {
        System.out.println("return to main page.....");
        System.out.println("------------------------------------------------------------\n");
         Tanants.Tanents_option(id, password);
        
    }
      if (option.equalsIgnoreCase("1")) {
      System.out.println(  sendMessageToLandlord(id, password));
      }  else if(option.equalsIgnoreCase("2")){
            see_Tanents_messeges(id);
      }else if(option.equalsIgnoreCase("3")){
        Tanants.Tanents_option(id, password);
      }else{
        Tanants.Tanents_option(id, password);
      }
        
    }
static String id;
static String password;
//if landlord want coomunicate with tanents then come here 
    public static void communication_with_Tanents(String id,String password){
        id=id;
        password=password;
        //attempt for meesege 
        int attempt[]={0};
        
        //then showing  this option 
        String message = 
        """
        =========================================
        |         Message-related Query         |
        =========================================
        | Please choose an option:              |
        |---------------------------------------|
        | 1. Send message to tenants            |
        | 2. View messages                      |
        | 3. Go back                            |
        =========================================""";
          
        String option=InputHelper.getString(message+"\n",attempt);
        //if enter exit then go to option method 
        if (option.equalsIgnoreCase("exit")) {
            System.out.println("return to main page.....");
            System.out.println("------------------------------------------------------------\n");
           Landlord.land_lord_option(id, password);
            
        }
       
        if (option.equalsIgnoreCase("1")) {
             //if choose 1  then call this method 
        System.out.println(  send_messege_Tanents(id, password));
        }  else if(option.equalsIgnoreCase("2")){
            //if choose 2 then call this method 
              see_Landlord_messeges(id);
        }else if(option.equalsIgnoreCase("3")){
            //if chosse 3 call this method 
          Landlord.land_lord_option(id, password);
        }else{
            //if he enterr other thing then go back
            System.out.println("Invalid option. Please enter a valid choice (1, 2, or 3).");
         Landlord.land_lord_option(id, password);
        }
          
      }
public static void main(String[] args) {
    sendMessageToLandlord(id, password);
}

    public static String sendMessageToLandlord(String tenantId, String tenantPassword) {
      int[] attempt = {0};
      int[] messageAttempts = {0};
      if (messageAttempts[0]==3) {
        System.out.println("you loose your three attempts");
        communication_with_landlord(id, password);
      }
      Connection connection;
      PreparedStatement preparedStatement;
      System.out.println("------------------------------------------------------------\n");
      while (true) { messageAttempts[0]++;
        if (messageAttempts[0]==4) {
            System.out.println("you loose your three attempts");
            communication_with_landlord(id, password);
        }
          String landlordId = InputHelper.getString("Enter your landlord ID:- ",attempt);
           messageAttempts[0]++;
           if (landlordId.equalsIgnoreCase("exit")) {
            System.out.println("Returning to main page...");
            System.out.println("------------------------------------------------------------\n");
            communication_with_landlord(id, password);
            return null; 
        }
        if (!landlordId.matches("^L\\d{4}$")) {
            System.out.println("Invalid landlord ID format. Please use the format L1234.");
            continue;
        }
             String property_id = InputHelper.getString("Enter your property ID:- ",attempt);
             if (property_id.equalsIgnoreCase("exit")) {
                System.out.println("Returning to main page...");
                System.out.println("------------------------------------------------------------\n");
                communication_with_landlord(id, password);
                return null; 
            }
            attempt[0]++;
          if (landlordId == null||property_id==null) {
              System.out.println("Lanlord Or property id is null .");
              return sendMessageToLandlord(tenantId, tenantPassword);
          } 
         
  
        
        
  
          String message = InputHelper.getString("Enter your message:- ",attempt);
          
          if (message.equalsIgnoreCase("exit")) {
            System.out.println("Exiting registration process. Goodbye!");
            System.out.println("------------------------------------------------------------\n");
            App.Appp();
            return null; 
        }
          connection = GetDatabase.GetDb();
          try {
              preparedStatement = connection.prepareStatement(
                  "INSERT INTO communication (tanents_id, landlord_id, message_content) VALUES (?, ?, ?);"
              );
              preparedStatement.setString(1, tenantId);
              preparedStatement.setString(2, landlordId);
              preparedStatement.setString(3, message);
               Payment.chek_valid_detalis(tenantId, landlordId, property_id);
              int rowsAffected = preparedStatement.executeUpdate();
              if (rowsAffected > 0) {
                  System.out.println("Message sent successfully.");
                  Tanants.Tanents_option(id, password);
                  return "Message sent successfully.";
              } else {
                  System.out.println("Message not sent due to server  error Try again .");
                  Tanants.Tanents_option(id, password);
                  return null;
              }
          } catch (Exception e) {
              e.printStackTrace();
              System.out.println("An exception occurred while sending the message.");
              Tanants.Tanents_option(id, password);
              return null;
          } 
     
      }
     
  }

//if landlord want to send meesege to tanetns then come here 
  public static String send_messege_Tanents(String landlordId, String password) {
    int[] attempt = {0};
    int[] messageAttempts = {0};
    Connection connection;
    PreparedStatement preparedStatement;
    System.out.println("------------------------------------------------------------\n");
    while (true) {
        //ask for tantettn id for send meesege 
        String tenantId = InputHelper.getString("Enter your Tenant ID to send a message: ",attempt);
        tenantId = tenantId.toUpperCase();
        if (tenantId.equalsIgnoreCase("exit")) {
            System.out.println("return to main page.....");
            System.out.println("------------------------------------------------------------\n");
           communication_with_Tanents(id, password);
            
        }
        String validatedTenantId = Get_Metods.Get_user_id_without_pass_for_coomunication(tenantId,landlordId);
        //check not null 
        if (validatedTenantId == null) {
            System.out.println("No tenant ID found. Please try again.");
            return send_messege_Tanents(landlordId, password);
        }
        //check exit
        if (validatedTenantId.equalsIgnoreCase("exit")) {
            System.out.println("return to main page.....");
            System.out.println("------------------------------------------------------------\n");
           communication.communication_with_Tanents(id, password);
            
        }
       
   //check valid regex 
        if (!validatedTenantId.matches("^T\\d{4}$")) {
            System.out.println("Invalid tenant ID format. Please use the format T1234.");
            continue;
        }
          //ask for meesege for send to tanents 
        String message = InputHelper.getString("Enter your message: ",attempt);
        //check null
          if (message==null||message.isEmpty()) {
            System.out.println("Message cannot be empty. Please try again.");
            continue;
            
          }
          //then get db second time 
        connection = GetDatabase.GetDb();
        try { 
            // this query for send meesseges to tanents db in coomunicatuin table
            preparedStatement = connection.prepareStatement(
                "INSERT INTO communication (tanents_id, landlord_id, message_content) VALUES (?, ?, ?);"
            );
            preparedStatement.setString(1, validatedTenantId);
            preparedStatement.setString(2, landlordId);
            preparedStatement.setString(3, message);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                //if meesseges send sucessfully then print this
                System.out.println("Message sent successfully.");
               
                communication_with_Tanents(id, password);
                return "Message sent successfully.";
            } else {
                //if meesseges not  send sucessfully then print this
                System.out.println("Message not sent due to an error.");
                Landlord.land_lord_option(id, password);
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An exception occurred while sending the message.");
            Tanants.Tanents_option(id, password);
            return null;
        }
    }
}



public static void see_Tanents_messeges(String tanetns_id) {
  int[] attempt = {0};
  Connection con;
  PreparedStatement pre;
  ResultSet re;
int []attempte={0};
  while (true) {
    attempte[0]++;
    if (attempte[0]==3) {
        System.out.println("You loose your three attempts");
        communication_with_landlord(id, password);
    }
      String landlord_id = InputHelper.getString("Enter your landlord ID to view messages: ",attempt);
     attempt[0]++;
      if (landlord_id.equalsIgnoreCase("exit")) {
        System.out.println("return to main page.....");
        System.out.println("------------------------------------------------------------\n");
         communication_with_landlord(id, password);
        
    }
      if (landlord_id == null || landlord_id.isEmpty()) {
        System.out.println("Tenant ID cannot be empty. Please try again.");
       continue;
    }
    
      if (!landlord_id.matches("^L\\d{4}$")) {
          System.out.println("Invalid landlord ID format. Please use the format L1234.");
          continue;
      }

      try {
          con = GetDatabase.GetDb();
          pre = con.prepareStatement("SELECT * FROM communication WHERE tanents_id = ? AND landlord_id = ?");

          pre.setString(1, tanetns_id);
          pre.setString(2, landlord_id);
          re = pre.executeQuery();

          if (!re.isBeforeFirst()) {
              System.out.println("No messages found for the given tenant and landlord IDs.");
          } else {
              while (re.next()) {
                  String message_id = re.getString("message_id");
                  String tenant_id = re.getString("tanents_id");
                  String landlord_id_get = re.getString("landlord_id");
                  String message_content = re.getString("message_content");
                  String timestamp = re.getString("timestamp");

                  System.out.println(
        "Message Details:\n" +
        "-----------------\n" +
        "Message ID       : " + message_id + "\n" +
        "Sender (Tenant)  : " + tenant_id + "\n" +
        "Receiver (Landlord): " + landlord_id_get + "\n" +
        "Message Content  : " + message_content + "\n" +
        "Timestamp        : " + timestamp + "\n"
);

              }
              communication_with_landlord(id, password);

          }  
      } catch (Exception e) {
          System.out.println("Exception in see_Tanents_messeges method.");
       e.printStackTrace();
          Tanants.Tanents_option(id, password);
      }
  }
}



   //if landord wants to see the meesseges 
public static void see_Landlord_messeges(String landlord_id) {
    int[] attempt = {0};
    Connection con;
    PreparedStatement pre;
    ResultSet re;

    while (true) {
        //then enter the tanent id who wants to see messeges 
    String tenantId = InputHelper.getString("Enter Tenant ID to view Tenant messages: ",attempt);
    // if he enter exit for back
    if (tenantId.equalsIgnoreCase("exit")) {
        System.out.println("return to main page.....");
        System.out.println("------------------------------------------------------------\n");
       communication_with_Tanents(id, password);
        
    }
    //check null
    if (tenantId == null || tenantId.isEmpty()) {
        System.out.println("Tenant ID cannot be empty. Please try again.");
       continue;
    }
//cehck crrecto format 
    if (!tenantId.matches("^T\\d{4}$")) {
        System.out.println("Invalid tenant ID format. Please use the format T1234.");
        continue;
    }

    try {
        con = GetDatabase.GetDb();
        //queary for see messeges of landlord 
        pre = con.prepareStatement("SELECT * FROM communication WHERE tanents_id = ? AND landlord_id = ?");
        pre.setString(1, tenantId);
        pre.setString(2, landlord_id);
        re = pre.executeQuery();

      
            
        
            if (!re.next()) {
                //if no messeges found in db then print this 
                System.out.println("No messages found for the given tenant and landlord IDs.");
            }else{
                do { 
                    String message_id = re.getString("message_id");
                String tenant_id = re.getString("tanents_id");
                String landlord_idget = re.getString("landlord_id");
                String message_content = re.getString("message_content");
                String timestamp = re.getString("timestamp");
System.out.println("_________________________________________");
//print meeseges in this format 
                System.out.println("Message ID: " + message_id);
                System.out.println("Tenant ID (Receiver): " + tenant_id);
                System.out.println("Landlord ID (Sender): " + landlord_idget);
                System.out.println("Message Content: " + message_content);
                System.out.println("Timestamp: " + timestamp);
System.out.println("_________________________________________");
                } while (re.next());
                //after they print then call this method 
                communication_with_Tanents(id, password);
            }
    } catch (Exception e) {
        System.out.println("Exception in see_Landlord_messeges method.");
        e.printStackTrace();
        Landlord.land_lord_option(id, password);
    }}
}


 

}
