import java.sql.*;
import java.time.LocalDate;




public class maintance {

 
    static  Landlord landlord=new Landlord();
 

    public static String maintenance_id() {
        int[] attempt = {0};
        
            
               
           
        while (true) {

 String maintenance_id = InputHelper.getString("Enter maintenance ID like M1, M2, M3, ...: ",attempt);
 maintenance_id=maintenance_id.toUpperCase();
         if (maintenance_id == null || maintenance_id.trim().isEmpty()) {
            System.out.println("Maintenance ID cannot be null or empty.");
           
        }
        if (maintenance_id.equalsIgnoreCase("exit")) {
            System.out.println("return to main page.....");
            System.out.println("------------------------------------------------------------\n");
             Landlord.add_view_maintance();return null ;
           
           
        }
        if (maintenance_id.matches("^M\\d+$")) {

            Connection c=GetDatabase.GetDb();
            maintenance_id = maintenance_id.toUpperCase();
            try {
              PreparedStatement prepare=c.prepareStatement("select * from maintanace_details where maintaince_id=?");
              prepare.setString(1,maintenance_id );
              ResultSet reesult=prepare.executeQuery();
              if (!reesult.next()) {
                return maintenance_id;
              } else {
                System.out.println("already exist in db");
              }

            } catch (Exception e) {
             System.out.println("exeption in maintance id methoed");
             e.printStackTrace();
             Landlord.Land_lordslogin();
            }} else {
                System.out.println("Invalid ID! Please enter a valid ID like M1, M2, M3, ...");
            }
        }
    
            
  
           
            
    
          
        }
    

       
    public static String property_id_maintace_table(){
        int attempt[]={0};
        String property_id_check;
        String property_id="";
        Connection cone=GetDatabase.GetDb();
         ResultSet resulte;
         PreparedStatement pree;
          while (true) {
            property_id=InputHelper.getString("enter property id:- \n",attempt);
            property_id=property_id.toUpperCase();
            if (property_id.equalsIgnoreCase("exit")) {
              System.out.println("return to main page.....");
              System.out.println("------------------------------------------------------------\n");
             Landlord.add_view_maintance();
              
          }
          
            if (property_id.matches("^P\\d+$")) {
                try {
                    pree= cone.prepareStatement("select * from `properties_details` where property_id=?and landlord_id=?");
                      pree.setString(1, property_id);
                      pree.setString(2,landlord. id);

                     resulte= pree.executeQuery();
                     if (resulte.next()) {
                         property_id_check= resulte.getString("property_id");
                         if (property_id.equals(property_id_check)) {
                             return property_id;
                             
                         } else {
                             System.out.println("not found any property id");
                             return property_id_maintace_table();
                         }
                     } else {
                         System.out.println("not found any property id");
                         return property_id_maintace_table();
                     }
                    } catch (Exception e) {
                     System.out.println("exeption in  property idmaintace table method");
                     e.printStackTrace();
                     return property_id_maintace_table();
                    }
            } else {
                System.out.println("en ter right format");
                return property_id_maintace_table();
            }
          }
          
          
           
    }

    public static void main(String[] args) {
    store_maintace_table_db("L8896");
    }
    //if landlord want to add maintaince staff for any property 
    public static void store_maintace_table_db(String landlord_id){
         Connection con;
         PreparedStatement pre;
         
        String query="INSERT INTO `maintanace_details`VALUES(?,?,?,?,?);";
        // get database connecton 
        con=GetDatabase.GetDb();
        InputValidator inputvalid=new InputValidator();
         LocalDate localDate = LocalDate.now();
         Date sqlDate = Date.valueOf(localDate);
             
        try {
            pre=con.prepareStatement(query);
            pre.setString(1, maintenance_id());
            pre.setString(2, inputvalid.getvalidName());
            pre.setDate(3,sqlDate);
            pre.setString(4,landlord_id );
            pre.setString(5, property_id_maintace_table());
             int i=pre.executeUpdate();
            if (i>0) {
                //if succesfully added then print this 
                System.out.println("sucesfully updated ");
            } else {
                //if occur in problem 
                System.out.println("problem occur returning mainmpage");
                Landlord.Land_lordslogin();
            }
        } catch (Exception e) {
            
            System.out.println("exception maintenance class store method ");
            e.printStackTrace();
            Landlord.Land_lordslogin();
        }
    }

         //if the lanlord want to see his maintaince 
    public static void view_maintaince(String id){
        Connection con;
        PreparedStatement pre;
        ResultSet result;
        try {
            //get database connection
          con=  GetDatabase.GetDb();
          //this query for see maintaince details
        pre=  con.prepareStatement("SELECT * FROM `maintanace_details` where `landlord_id_assign`=? ;");
        pre.setString(1, id);
        result=pre.executeQuery();
      if (!result.next()) {
        //if they have no maintaince staff 
              System.out.println("you have not maintace staff");
             } else {
            do { 
                 String maintaince_id=  result.getString("maintaince_id");
              String name=  result.getString("name");
              String join_date=  result.getString("join_date");
              String  landlord_id_assign=  result.getString("landlord_id_assign");
              String property_id=  result.getString("property_id");
              String maintenanceDetails = 
    "Maintenance Staff Details:\n" +
    "---------------------------\n" +
    "Maintenance ID    : " + maintaince_id + "\n" +
    "Name              : " + name + "\n" +
    "Join Date         : " + join_date + "\n" +
    "Landlord Assigned : " + landlord_id_assign + "\n" +
    "Property ID       : " + property_id + "\n";
    //print the maintaince details 
System.out.println(maintenanceDetails);
      
            } while (result.next());
             
       
      }

        } catch (Exception e) {
           System.out.println("exeption in view  maintnace mthod");
           e.printStackTrace();
           Landlord.Land_lordslogin();
        }

    }
        
   // check valid request id 
         public static String check_task_db(String request_id) {
          Connection con = null;
          PreparedStatement pre = null;
          ResultSet result = null;
      
     
          if (!request_id.matches("^R\\d+$")) {
              System.out.println("Enter the correct format for Request ID (e.g., R1, R2, R100, etc.)");
              return "nexist";
          }
      
          try {
              con = GetDatabase.GetDb();
              //query for check valid request id 
              pre = con.prepareStatement("SELECT * FROM tasks WHERE Request_id = ?");
              pre.setString(1, request_id);
              result = pre.executeQuery();
      
             
              if (result.next()) {
                //if exist then return exist 
                  return "exist";
              } else {
                //if not found then return this
                  return "not exist";
              }
      
          } catch (Exception e) {
              System.out.println("Exception in check_task_db method");
              e.printStackTrace();
              return "error";
          } finally {
              try {
                //close the connection 
                  if (result != null) result.close();
                  if (pre != null) pre.close();
                  if (con != null) con.close();
              } catch (Exception e) {
                  e.printStackTrace();
              }
          }
      }
         // if landlord wants to create task of tanetns reqest
      public static void assign_request(String id) {
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet result = null;
     int attempt[]={0};
     //print all the request list of landlord they approved 
        System.out.println("----------------------------------------------request list--------------------------------------");
        Request.view_requesttF(id,"approved");
     
       System.out.println("You can type 'exit' for back \n ");
       //get input of request id 
        String request_id = InputHelper.getString("Enter request id for assigning to a task\n",attempt);
    
      request_id=request_id.toUpperCase();
      //if they enter exit then go to back page 
        if (request_id.equalsIgnoreCase("exit")) {
            System.out.println("Returning to the main page...");
            System.out.println("------------------------------------------------------------\n");
            Landlord.land_lord_option(id, request_id);
            return;
        }
// check  request id has in db or not 
        String check = check_task_db(request_id);
        //in chek if exi then return the request already created task
        if (check.equalsIgnoreCase("exist")) {
            System.out.println("For This Request ID Task is already Created");
            assign_request(id); 
            return;
        }
          
        try {
            //get database connection 
            con = GetDatabase.GetDb();
            //check valid request or landord id
            pre = con.prepareStatement("SELECT * FROM maintaiance WHERE Request_id = ? AND Request_status = 'approved' AND landlord_id = ? AND assign_request IS NULL");
            pre.setString(1, request_id);
            pre.setString(2, id);
            result = pre.executeQuery();
    
            if (result.next()) {
          //pass descriptin for reqest
                String description = result.getString("about_description");
    
           //create task for insert query    
                PreparedStatement pree = con.prepareStatement("INSERT INTO `tasks` (`description`, `landlord_id`, `Request_id`) VALUES (?, ?, ?)");
                pree.setString(1, description);
                pree.setString(2, id);
                pree.setString(3, request_id);
                int i = pree.executeUpdate();
    
                if (i > 0) {
                    //if suucesfully created task 
                    System.out.println("Successfully created task.");
                    Landlord.land_lord_option(id, landlord.password);
                } else {
                    //if any errror occured 
                    System.out.println("Error occurred while creating the task. Returning to the main page...");
                    Landlord.land_lord_option(id, landlord.password);
                }
            } else {
                //if reqest id id not valid 
                System.out.println("Request not found or not approved. Please check the request ID.");
                assign_request(id);  
            }
    
        } catch (Exception e) {
            System.out.println("Exception in assign_request method");
            e.printStackTrace();
            Landlord.Land_lordslogin();
        } finally {
            try {
                // close the connection 
                if (result != null) result.close();
                if (pre != null) pre.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}
