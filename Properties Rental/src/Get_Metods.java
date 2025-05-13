import java.sql.*;

public class Get_Metods {
    static Connection con;
    static PreparedStatement pre;
    static ResultSet result;

    public static void main(String[] args) {
        // // String name= Get_user_name("L8883", "sh1234");
        //  String name= Get_user_Pan_no("L9774", "sh1234");
        // // String name = Get_user_password("L9774", "shivang meena");
        // System.out.println(name);
       System.out.println( Get_user_id_without_pass_for_coomunication("L4571",""));
    }

    public static void tanents_information_lanloird(){
         con=GetDatabase.GetDb();
         try {
           pre= con.prepareStatement("");
         } catch (Exception e) {
           e.printStackTrace();
           System.out.println("exeption occur in Get_methods class tanents information method");
           
         }
    }

    public static String landlord_id_payment(String user_id){
        String user_id1 = null;
        String query = "SELECT * FROM user_table WHERE user_id=?";

        try {

            con = GetDatabase.GetDb();

            pre = con.prepareStatement(query);
            pre.setString(1, user_id);
          

            result = pre.executeQuery();

            if (result.next()) {
                user_id1 = result.getString("user_id");
                  if (user_id1.equals(user_id)) {
                  
                    return  user_id1;

                  }else{
                    System.out.println("not found user id");
                  }
            } 
        } catch (Exception e) {
            System.out.println("Exception in get_user_id method");
            e.printStackTrace();
        } finally {
            try {
                if (result != null) result.close();
                if (pre != null) pre.close();
                if (con != null) con.close(); // This is crucial to avoid connection leaks
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return user_id1;
    }
    // in communication check valid lanlord id or tanents id 
    public static String Get_user_id_without_pass_for_coomunication(String ide,String t_id){
         while (true) { 
            Connection cone=GetDatabase.GetDb();
            try {
                //check database is property aquired or not 
            PreparedStatement pree=  cone.prepareStatement("select * from `properties_details` where tnaents_id=?and landlord_id=?");
            pree.setString(1, ide);
            pree.setString(2, t_id);
             ResultSet reee=pree.executeQuery();
             if (reee.next()) {
                //if exit then return ide 
                 return ide;
             } else {
                //if wriong then print this 
                 System.out.println("Enter Right Tanents ID");
                   return null;
             }
            } catch (Exception e) {
                //print the exeption 
             System.out.println(" exeption int mehtofd Get_user_id_without_pass");
             e.printStackTrace();
              Landlord.land_lord_option(ide, t_id);
              return null;
            }
         }
    }
    public static String Get_user_id(String user_name, String password) {
        String user_id = null;
        //get user id from db  
        String query = "SELECT * FROM user_table WHERE user_name = ? AND password = ?";

        try {

            con = GetDatabase.GetDb();

            pre = con.prepareStatement(query);
            pre.setString(1, user_name);
            pre.setString(2, password);

            result = pre.executeQuery();

            if (result.next()) {
                user_id = result.getString("user_id");

            } 
        } catch (Exception e) {
            System.out.println("Exception in get_user_id method");
            e.printStackTrace();
        }
        finally {
            try {
                if (result != null) result.close();
                if (pre != null) pre.close();
                if (con != null) con.close(); // This is crucial to avoid connection leaks
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return user_id;
    }


    public static String Get_user_name(String user_id, String password) {
        String name = null;
        //get user name  query 
        String query = "SELECT * FROM user_table WHERE user_id = ? AND password = ?";
        try {
            con = GetDatabase.GetDb();
            pre = con.prepareStatement(query);
            pre.setString(1, user_id);
            pre.setString(2, password);
            result = pre.executeQuery();
            
            if (result.next()) {
                name = result.getString("user_name");
                System.out.println("User name found: " + name);
            } 
        } catch (Exception e) {
            System.out.println("Error in Get_user_name: " + e);
        } finally {
            try {
                if (result != null) result.close();
                if (pre != null) pre.close();
                if (con != null) con.close(); // This is crucial to avoid connection leaks
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return name;
    }
    

    public static String Get_user_password(String user_id, String  name) {
        String password = null;
        String query = "Select * from user_table where user_id=? and user_name=?";
        try {
            con = GetDatabase.GetDb();
            pre = con.prepareStatement(query);
            pre.setString(1, user_id);
            pre.setString(2, name);
            result = pre.executeQuery();
            if (result.next()) {
                password = result.getString("password");
            } 

        } catch (Exception e) {
            System.out.println("exeption in get_user_password method");
            System.out.println(e);
            return null;
        } finally {
            try {
                if (result != null) result.close();
                if (pre != null) pre.close();
                if (con != null) con.close(); // This is crucial to avoid connection leaks
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return password;
    }


    public static String Get_user_role(String user_id, String  password) {
        String role = null;
        String query = "Select * from user_table where user_id=? and password=?";
        try {
            con = GetDatabase.GetDb();
            pre = con.prepareStatement(query);
            pre.setString(1, user_id);
            pre.setString(2, password);
            result = pre.executeQuery();
            if (result.next()) {
                role = result.getString("role");

            }

        } catch (Exception e) {
            System.out.println("exeption in get_user_role method");
            System.out.println(e);
            return null;
        } finally {
            try {
                if (result != null) result.close();
                if (pre != null) pre.close();
                if (con != null) con.close(); // This is crucial to avoid connection leaks
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return role;
    }

     public static String Get_user_email(String user_id, String  password) {
        String email = null;
        String query = "Select * from user_table where user_id=? and password=?";
        try {
            con = GetDatabase.GetDb();
            pre = con.prepareStatement(query);
            pre.setString(1, user_id);
            pre.setString(2, password);
            result = pre.executeQuery();
            if (result.next()) {
                email = result.getString("email");

            } 

        } catch (Exception e) {
            System.out.println("exeption in get_user_email method");
            System.out.println(e);
            return null;
        } finally {
            try {
                if (result != null) result.close();
                if (pre != null) pre.close();
                if (con != null) con.close(); // This is crucial to avoid connection leaks
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return email;
    }


    
    public static String Get_user_phone_no(String user_id, String  password) {
        String phone_no = null;
        String query = "Select * from user_table where user_id=? and password=?";
        try {
            con = GetDatabase.GetDb();
            pre = con.prepareStatement(query);
            pre.setString(1, user_id);
            pre.setString(2, password);
            result = pre.executeQuery();
            if (result.next()) {
                phone_no = result.getString("phone_no");

            } 

        } catch (Exception e) {
            System.out.println("exeption in get_user_phone_no method");
            System.out.println(e);
            return null;
        } finally {
            try {
                if (result != null) result.close();
                if (pre != null) pre.close();
                if (con != null) con.close(); // This is crucial to avoid connection leaks
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return phone_no;
    }

    


    public static String Get_user_Address(String user_id, String  password) {
        String Address = null;
        String query = "Select * from user_table where user_id=? and password=?";
        try {
            con = GetDatabase.GetDb();
            pre = con.prepareStatement(query);
            pre.setString(1, user_id);
            pre.setString(2, password);
            result = pre.executeQuery();
            if (result.next()) {
                Address = result.getString("Address");

            } 

        } catch (Exception e) {
            System.out.println("exeption in get_user_Address method");
            System.out.println(e);
            return null;
        } finally {
            try {
                if (result != null) result.close();
                if (pre != null) pre.close();
                if (con != null) con.close(); // This is crucial to avoid connection leaks
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return Address;
    }


    public static String Get_user_Pan_no(String user_id, String  password) {
        String Pan_no = null;
        String query = "Select * from user_table where user_id=? and password=?";
        try {
            con = GetDatabase.GetDb();
            pre = con.prepareStatement(query);
            pre.setString(1, user_id);
            pre.setString(2, password);
            result = pre.executeQuery();
            if (result.next()) {
                Pan_no = result.getString("Pan_no");

            } 

        } catch (Exception e) {
            System.out.println("exeption in get_user_Pan_no method");
            System.out.println(e);
            return null;
        } finally {
            try {
                if (result != null) result.close();
                if (pre != null) pre.close();
                if (con != null) con.close(); // This is crucial to avoid connection leaks
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return Pan_no;
    }

}


 class Set_properties_details {
    static Connection con;
    static PreparedStatement pre;
    static ResultSet result;

   
    public static String Get_property_id(String id){
       Connection con= GetDatabase.GetDb();
       try {
         PreparedStatement pre=   con.prepareStatement("select * From properties_details where property_id=? ");
         pre.setString(1, id);
        ResultSet re= pre.executeQuery();
       while (true) {
        if (re.next()) {
            String Property_id=  re.getString("property_id");
            return Property_id;
          } else {
            
              return "n";
              
          }
       }
       } catch (Exception e) {
        System.out.println("exeption imn get property id set properties details");
        e.printStackTrace();
       } finally {
        try {
            if (result != null) result.close();
            if (pre != null) pre.close();
            if (con != null) con.close(); // This is crucial to avoid connection leaks
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
       return "not found id like this ";

    }
   
    public static void main(String[] args) {
        Get_properties_detailss_landlord("L8896", "Sh1234?");
    }
       // this method is for see properties of landlord 
    public static void Get_properties_detailss_landlord(String ide,String password) {
        String property_id = "";
        String land_lord_id = "";
        String property_addres = "";
        String size = "";
        String rent_amount = "";
        String status = "", tanetns = "";
        String query = "Select * from properties_details where landlord_id =?";
            
        try {
            con = GetDatabase.GetDb();
            pre = con.prepareStatement(query);
            pre.setString(1,ide);
            result = pre.executeQuery();
              
            
            if (!result.next()) {
                //if in database no matched data 
                System.out.println("You have not any property");
            } else {
                
                do {
                    property_id = result.getString("property_id");
                    land_lord_id = result.getString("landlord_id");
                    property_addres = result.getString("property_addres");
                    size = result.getString("size");
                    rent_amount = result.getString("rent_amount");
                    status = result.getString("status");
                    tanetns = result.getString("tnaents_id");
    
                    System.out.println(
                        "Property Details:\n" +
                        "-----------------\n" +
                        "Property ID      : " + property_id + "\n" +
                        "Landlord ID      : " + land_lord_id + "\n" +
                        "Property Address : " + property_addres + "\n" +
                        "Size             : " + size + "\n" +
                        "Rent Amount      : " + rent_amount + "\n" +
                        "Status           : " + status + "\n" +
                        "Tenant ID        : " + tanetns + "\n"
                    );
                    
                } while (result.next());  
            }
    
        } catch (Exception e) {
            System.out.println("Exception in get_properties_detailss_landlord method");
            e.printStackTrace();
    
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
    
    


public static void Get_properties_detailss_Tanents(String statuss, String id) {
    String property_id = "";
    String land_lord_id = "";
    String property_addres = "";
    String size = "";
    String rent_amount = "";
    String tanetn_id, status;

    
    String query = "";
    if (id==null) {
        query = "Select * from properties_details where status=?";
    } else {
         query = "Select * from properties_details where status=? And tnaents_id=?";
    }

    try {
        con = GetDatabase.GetDb();
        pre = con.prepareStatement(query);
        
        if (id==null) {
            pre.setString(1, statuss != null ? statuss : "not_occupied");
        } else {
            pre.setString(1, statuss != null ? statuss : "occupied");
            pre.setString(2, id);
            
        }

        result = pre.executeQuery();

        boolean found = false;
        while (result.next()) {
            found = true;
            property_id = result.getString("property_id");
            land_lord_id = result.getString("landlord_id");
            property_addres = result.getString("property_addres");
            size = result.getString("size");
            rent_amount = result.getString("rent_amount");
            status = result.getString("status");
            tanetn_id = result.getString("tnaents_id");

            System.out.println(
                "Property Details:\n" +
                "-----------------\n" +
                "Property ID      : " + property_id + "\n" +
                "Landlord ID      : " + land_lord_id + "\n" +
                "Property Address : " + property_addres + "\n" +
                "Size             : " + size + "\n" +
                "Rent Amount      : " + rent_amount + "\n" +
                "Status           : " + status + "\n" +
                "Tenant ID        : " + tanetn_id
            );
            
                               
        }

        if (!found) {
            System.out.println("You have not Any property. First acquire property");
        }

    } catch (Exception e) {
        System.out.println("exception in get_user_Pan_no method");
        System.out.println(e);
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


    // public static void  Get_properties_details( ) {
    //     String  property_id="";
    //     String land_lord_id="";
    //     String property_addres="";
    //     String size="";
    //     String rent_amount="";
    //     String status="",tanetn_id;
    //     String query = "Select * from properties_details where tnaents_id is not null";
    //     try {
    //         con = GetDatabase.GetDb();
    //         pre = con.prepareStatement(query);
           
    //         result = pre.executeQuery();
    //         while (result.next()) {
    //             property_id = result.getString("property_id");
    //             land_lord_id=result.getString("landlord_id");
    //             property_addres=result.getString("property_addres");
    //             size=result.getString("size");
    //             rent_amount=result.getString("rent_amount");
    //             status=result.getString("status");
    //              tanetn_id=result.getString("tnaents_id");
               
    //                 System.out.println("property_id :- "+property_id+" land_lord_id:- "+land_lord_id+" property_addres:- "+property_addres+" size:- "+size+" rent_amount:- "+rent_amount+" status:- "+status+" t"+tanetn_id);
                
                
              
              
    //         }

    //     } catch (Exception e) {
    //         System.out.println("exeption in get_user_Pan_no method");
    //         System.out.println(e);
            
    //     }
    //     finally {
    //         try {
    //             if (result != null) result.close();
    //             if (pre != null) pre.close();
    //             if (con != null) con.close(); 
    //         } catch (Exception e) {
    //             e.printStackTrace();
    //         }
    //     }
        
    // }
}
