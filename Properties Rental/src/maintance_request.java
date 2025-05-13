
import java.sql.*;
import java.util.Scanner;

public class maintance_request {

    static Connection con;
    static PreparedStatement pre;
    static ResultSet result;

    public static void main(String[] args) {
        store_maintace_table_db();
    }

    public static String landlord_id() {
        int[] sizeAttempts = {0};
        int attempts = 3;
        String id = "";
        while (attempts > 0) {
            while (true) {
                id = InputHelper.getString("Enter landlord id you send request", sizeAttempts);

                if (id == null) {
                    System.out.println("id cannot be empty. Please enter a valid id.\n");
                    System.out.println("------------------------------------------------------------\n");
                    return landlord_id();
                }
                if (id.equalsIgnoreCase("exit")) {
                    System.out.println("Retrun to main page......");
                    System.out.println("------------------------------------------------------------\n");
                    Tanants.Tanents_option(Tanants.id, Tanants.password);
                    return null;
                }

                if (id.matches("^L\\d{4}$")) {

                    con = GetDatabase.GetDb();
                    try {
                        pre = con.prepareStatement("select * from user_table where user_id=?");
                        pre.setString(1, id);
                        result = pre.executeQuery();
                        if (result.next()) {
                            return id;
                        } else {
                            System.out.println("not valid enter again");
                            return landlord_id();
                        }

                    } catch (Exception e) {
                        System.out.println("exeption in ,landlord method in maintancve");
                        e.printStackTrace();
                        return landlord_id();
                    }

                } else {
                    System.out.println("enter valid id");
                    sizeAttempts[0]++;
                }
            }
        }
        return id;
    }

    public static void chek_valid_detalis_for_request(String tanent_id, String Land_lords_id_pay, String property_id) {
        Connection con = GetDatabase.GetDb();
        String query = "select * from `properties_details` where tnaents_id=? and property_id=?and landlord_id=?";
        try {
            PreparedStatement pre = con.prepareStatement(query);
            pre.setString(1, tanent_id);
            pre.setString(2, property_id);
            pre.setString(3, Land_lords_id_pay);
            ResultSet result = pre.executeQuery();
            if (result.next()) {
                return;
            } else {
                System.out.println("not found any record");
                Tanants.Tanents_option(Tanants.id, Tanants.password);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("try again");
            Tanants.Tanents_option(Tanants.id, Tanants.password);

        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                System.out.println("exeption in payment detail filkl check deatils");
                e.printStackTrace();
            }
        }
    }

    public static String Description_maintace_reqest_tan() {
        int attempt[] = {0};
        String description = InputHelper.getString("enter Description in detail\n", attempt);
        if (description == null) {
            description = InputHelper.getString(" enter again not null value  in descriptiuon\n", attempt);
        }
        if (description.equalsIgnoreCase("exit")) {
            System.out.println("return to main page.....");
            System.out.println("------------------------------------------------------------\n");
            Tanants.Tanents_option(Tanants.id, Tanants.password);
            return null;
        }

        return description;
    }

    public static void store_maintace_table_db() {
        con = GetDatabase.GetDb();
        try {

            Date currentDate = new Date(System.currentTimeMillis());

            String description = Description_maintace_reqest_tan();
            if (description == null || description.trim().isEmpty()) {
                System.out.println("Invalid description, cannot proceed.");
                return;
            }
            if (description.equalsIgnoreCase("exit")) {
                System.out.println("Exiting registration process. Goodbye!");
                System.out.println("------------------------------------------------------------\n");
                Tanants.Tanents_option(Tanants.id, Tanants.password);

            }

            String tenantId = Payment.tenant_id(Tanants.password);
            String propertyId = property_id_maintace();
            String landlord = landlord_id();

            if (tenantId == null || propertyId == null) {
                System.out.println("Invalid tenant_id or property_id.");
                return;
            }

            pre = con.prepareStatement("INSERT INTO maintaiance (tanent_id, property_ids, request_date, about_description,landlord_id) VALUES (?, ?, ?, ?,?)");

            pre.setString(1, tenantId);
            pre.setString(2, propertyId);
            pre.setDate(3, currentDate);
            pre.setString(4, description);
            pre.setString(5, landlord);

            chek_valid_detalis_for_request(tenantId, landlord, propertyId);
            int i = pre.executeUpdate();

            if (i > 0) {

                pre = con.prepareStatement("UPDATE maintaiance SET Request_id = CONCAT('R', Request_id_var) WHERE Request_id_var = LAST_INSERT_ID()");
                int c = pre.executeUpdate();

                if (c > 0) {
                    System.out.println("Successfully sent Request");
                } else {
                    System.out.println("Could not send request. Please try again after some time.");
                    store_maintace_table_db();
                }
                System.out.println("Successfully requested maintenance.");
            } else {
                System.out.println("Problem occurred. Request again.");
            }
        } catch (SQLException e) {
            System.out.println("SQLException in maintenance request store method.");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception in maintenance request store method.");
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public static String property_id_maintace() {
        String propertycheck_id = Payment.property_id();
        String property_id = "";
        con = GetDatabase.GetDb();
        try {
            pre = con.prepareStatement("select * from properties_details where property_id=?");

            pre.setString(1, propertycheck_id);
            result = pre.executeQuery();
            if (result.next()) {
                property_id = result.getString("property_id");
                return property_id;
            } else {
                System.out.println("not found enter again");
                return property_id_maintace();
            }
        } catch (Exception e) {
            System.out.println("this exeption is occur inproperty id maijtace method ");
            e.printStackTrace();
            return property_id_maintace();
        }

    }

    public static String Description_maintace_reqest() {
        int attempt[] = {0};
        String description = InputHelper.getString("enter Description in detail\n", attempt);
        if (description == null) {
            description = InputHelper.getString(" enter again not null value  in descriptiuon\n", attempt);
        }
        if (description.equalsIgnoreCase("exit")) {
            System.out.println("return to main page.....");
            System.out.println("------------------------------------------------------------\n");
            Landlord.Request();
            return null;
        }

        return description;
    }
}

class Request {

    static Connection con;
    static PreparedStatement pre;
    static ResultSet result;

    public static void main(String[] args) {
        view_requesttF("L8896", " ");
    }
         //this if for view requests of tanents sended to landlord 
    public static void view_requesttF(String id, String status) {
        //get db connection form getdatabase class of  getdb method
        con = GetDatabase.GetDb();
        try {

            pre = con.prepareStatement("select * from maintaiance where landlord_id=?and assign_request is null and Request_status=?");
            pre.setString(1, id);
            pre.setString(2, status);
            result = pre.executeQuery();

            if (!result.next()) {
                //if they not found any request then print this 
                System.out.println("NOT Found Any Request or All are Aproved ");
            } else {
                do {
                    String request_id = result.getString("Request_id");

                    String tanent_id = result.getString("tanent_id");
                    String property_id = result.getString("property_ids");
                    String request_date = result.getString("request_date");
                    String request_description = result.getString("about_description");
                    String request_status = result.getString("Request_status");
                    String request_landlord = result.getString("landlord_id");
                    String requestDetails
                            = "Request Details:\n"
                            + "-----------------\n"
                            + "Request ID          : " + request_id + "\n"
                            + "Tenant ID           : " + tanent_id + "\n"
                            + "Property ID         : " + property_id + "\n"
                            + "Request Date        : " + request_date + "\n"
                            + "Request Description : " + request_description + "\n"
                            + "Request Status      : " + request_status + "\n"
                            + "Request Landlord    : " + request_landlord + "\n";
                    //if they have request then print this request 
                    System.out.println(requestDetails);
                } while (result.next());
            }

        } catch (Exception e) {
            e.printStackTrace();
            App.Appp();
        }
    }
      //this mehod for aprroved the tanents request for landlord 
    public static void approved_request_from_landlord(String id, String password) {
        {
            Scanner sc = new Scanner(System.in);
            int attempt[] = {0};
             //if he want to exit then type exit 
            System.out.println("You can type 'exit' for back\n");
            String request_id = InputHelper.getString("Enter your request id (e.g., R1, R2, R3, ...):- \n", attempt);
            request_id = request_id.toUpperCase();
            if (request_id.equalsIgnoreCase("exit")) {
                System.out.println("return to main page.....");
                System.out.println("------------------------------------------------------------\n");
                Landlord.Request();

            }
            //check the valid request id 
            while (!request_id.matches("R\\d+")) {

                System.out.println("Enter a valid request ID (e.g., R1, R2, R3, ...):");
                request_id = sc.nextLine();
            }
          //get db connection form db 
            con = GetDatabase.GetDb();
            try {
                //query for approve tanetns request from landlord
                pre = con.prepareStatement("UPDATE maintaiance SET Request_status = 'approved' WHERE request_id = ?and landlord_id=? and assign_request is null");
                pre.setString(1, request_id);
                pre.setString(2, id);

                int i = pre.executeUpdate();
                if (i > 0) {
                    //if succesfull aprrove then print this 
                    System.out.println("Successfully Approve Reqest.");
                    Landlord.land_lord_option(id, password);
                } else {
                    //if  no request  found then print this 
                    System.out.println("No request found with the given Resqest id ID. Returning to main page...");
                    Landlord.land_lord_option(id, password);
                }
            } catch (SQLException e) {

                System.out.println("Exception occurred while updating request status.");
                e.printStackTrace();
            }
        }

    }
}
