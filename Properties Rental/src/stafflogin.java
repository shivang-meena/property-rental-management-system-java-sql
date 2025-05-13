
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class stafflogin {

    static Landlord landlord = new Landlord();

    public static String Check_landlord_id() {
        int[] attempt = {0};
        while (true) {
            Connection con = GetDatabase.GetDb();
            PreparedStatement pre;
            ResultSet result;
            String lanlord_ID = InputHelper.getString("Enter landlord ID like L1234 ...: ", attempt);
            attempt[0]++;
            lanlord_ID = lanlord_ID.toUpperCase();
            attempt[0]++;
            try {
                pre = con.prepareStatement("select * from user_table where user_id=?");
                pre.setString(1, lanlord_ID);
                result = pre.executeQuery();
                if (result.next()) {

                    return lanlord_ID;
                } else {
                    System.out.println("Not found any landlord ID in record. Please try again.");
                    continue;
                }
            } catch (Exception e) {
                System.out.println("exeption in check landlord id");
                e.printStackTrace();
                Landlord.land_lord_option(lanlord_ID, landlord.password);
            }
        }
    }

    public static void staff_chek_login() {
        int[] attempt = {0};
        while (true) {

            String maintenance_id = InputHelper.getString("Enter maintenance ID like M1, M2, M3, ...: ", attempt);
            attempt[0]++;
            maintenance_id = maintenance_id.toUpperCase();
            attempt[0]++;
            if (maintenance_id == null || maintenance_id.trim().isEmpty()) {
                System.out.println(" ID cannot be null or empty.");
                continue;
            }
            if (maintenance_id.equalsIgnoreCase("exit")) {
                System.out.println("Exiting  process. Goodbye!");
                System.out.println("------------------------------------------------------------\n");
                App.Appp();

            }
            if (maintenance_id.matches("^M\\d+$")) {
                String lanlord_ID = Check_landlord_id();
                if (lanlord_ID == null || lanlord_ID.trim().isEmpty()) {
                    System.out.println(" ID cannot be null or empty.");
                }
                Connection c = GetDatabase.GetDb();
                maintenance_id = maintenance_id.toUpperCase();
                try {
                    PreparedStatement prepare = c.prepareStatement("select * from maintanace_details where maintaince_id=?and landlord_id_assign=?");

                    prepare.setString(1, maintenance_id);
                    prepare.setString(2, lanlord_ID);
                    ResultSet reesult = prepare.executeQuery();
                    if (reesult.next()) {
                        staff.staff_option(maintenance_id, lanlord_ID);
                    } else {
                        System.out.println("Wrong ID or landlord ID. Please try again.");

                    }

                } catch (Exception e) {
                    System.out.println("exeption in maintance id methoed");
                    e.printStackTrace();
                    Landlord.Land_lordslogin();
                }
            } else {

                System.out.println("Invalid ID! Please enter a valid ID like M1, M2, M3, ...");
            }
        }
    }

    public static void view_task(String M_ID, String land_lord_id, String call) {
        String queary = "";
        if (call.equalsIgnoreCase("1")) {
            queary = "select * from tasks where landlord_id=? and assigned_to is null";
        } else if (call.equalsIgnoreCase("2")) {
            queary = "select * from tasks where landlord_id=? and assigned_to is not null";
        }
        Connection con = GetDatabase.GetDb();
        PreparedStatement pre;
        ResultSet result;

        try {
            pre = con.prepareStatement(queary);
            pre.setString(1, land_lord_id);

            result = pre.executeQuery();
            if (!result.next()) {
                System.out.println("You have not Any tasks");
            } else {

                do {

                    String assin_to = result.getString("assigned_to");
                    String assitocondi = (assin_to == null) ? "Not Assigned" : assin_to;
                    String statuse = result.getString("status");
                    String status = (statuse == null) ? "Not Assigned" : statuse;
                    String Status_Detail = result.getString("detail_status");
                    String Status_detail = (Status_Detail == null) ? "Not Assigned" : Status_Detail;
                    System.out.println(
                            "\n========================================="
                            + "\nTask ID         : " + result.getInt("task_id")
                            + "\nDescription     : " + result.getString("description")
                            + "\nStatus          : " + status
                            + "\nAssigned To     : " + assitocondi
                            + "\nAssigned Time   : " + result.getTimestamp("assigned_time")
                            + "\nLandlord ID     : " + result.getString("landlord_id")
                            + "\nRequest ID      : " + result.getString("Request_id")
                            + "\nStatus (Detail) : " + Status_detail
                            + "\n========================================="
                    );

                } while (result.next());

            }

        } catch (Exception e) {
            System.out.println("exeption in view task");
            e.printStackTrace();
            staff.staff_option(staff.M_id, staff.land_lord_id);
        }
    }
// public static void main(String[] args) {
//     assign_reqest_staff("M1", "L8896");
//     assign_reqest_staff("M1", "L8896");
// }

    public static void assign_reqest_staff(String M_id, String Landlord_id) {

        while (true) {

            Connection conee = GetDatabase.GetDb();
            view_task(M_id, Landlord_id, "1");
            int attempt[] = {0};
            System.out.println("You can type 'exit' for back\n");
            String task_id = InputHelper.getString("enter task id ", attempt);
            if (task_id.equalsIgnoreCase("exit")) {
                System.out.println("Returning to main menue...");
                System.out.println("------------------------------------------------------------\n");
                staff.staff_option(M_id, Landlord_id);

            
            String maintenance_id = InputHelper.getString("Enter maintenance ID to aasign like M1, M2, M3, ...: ", attempt);
            if (maintenance_id.matches("^M\\d+$")) {

                Connection c = GetDatabase.GetDb();
                maintenance_id = maintenance_id.toUpperCase();
                try {
                    PreparedStatement prepare = c.prepareStatement("""
                                                      SELECT tasks.task_id, maintanace_details.maintaince_id
                                                      FROM maintanace_details
                                                      JOIN tasks ON maintanace_details.landlord_id_assign = tasks.landlord_id
                                                      WHERE maintanace_details.maintaince_id = ? AND tasks.task_id = ? and assigned_to is null;""" //
                    //
                    //
                    );

                    prepare.setString(1, maintenance_id);
                    prepare.setString(2, task_id);
                    ResultSet reesult = prepare.executeQuery();
                    if (reesult.next()) {
                        String getmaintace_id = reesult.getString("maintanace_details.maintaince_id");
                        String Gettask_id = reesult.getString("tasks.task_id");
                        System.out.println(" sucessfully found " + getmaintace_id + "" + Gettask_id);

                        try {
                            PreparedStatement pree = conee.prepareStatement("UPDATE `tasks` SET `status` = 'pending', `assigned_to` = ? WHERE `task_id` = ? ");
                            pree.setString(1, maintenance_id);
                            pree.setString(2, task_id);
                            int i = pree.executeUpdate();
                            if (i > 0) {
                                System.out.println("Task Assigned Sucessfully");
                                return;
                            } else {
                                System.out.println("Problem occured Server Problem");
                                staff.staff_option(M_id, Landlord_id);
                            }
                        } catch (Exception e) {
                            System.out.println("exeption in under try catch in assign reqest staff");
                            e.printStackTrace();
                            App.Appp();
                        }

                    } else {
                        attempt[0]++;
                        System.out.println(" any staff or task id not found in record");
                        assign_reqest_staff(M_id, Landlord_id);
                    }

                } catch (Exception e) {
                    System.out.println("exeption in maintance id methoed");
                    e.printStackTrace();
                    Landlord.Land_lordslogin();
                } finally {
                    try {
                        conee.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            } else {
                attempt[0]++;
                System.out.println("Invalid ID! Please enter a valid ID like M1, M2, M3, ...");
            }
        }

    }
}
    static int attempteeee[] = {0};

    public static void update_task_status_detail(String M_id, String Landlord_id) {

        while (true) {
            if (attempteeee[0] == 3) {
                System.out.println("You looose your three attempts");
                staff.staff_option(M_id, Landlord_id);
                return;
            }
            int attempt[] = {0};
            view_task(M_id, Landlord_id, "2");
            Connection conee = GetDatabase.GetDb();
            System.out.println("You can type 'exit'  to go back.");
            String task_id = InputHelper.getString("Enter Your Task ID :- ", attempt);

            attempteeee[0]++;

            if (task_id.equalsIgnoreCase("exit")) {
                System.out.println("return to main page.....");
                System.out.println("------------------------------------------------------------\n");
                staff.staff_option(M_id, Landlord_id);

            }
            String status_Detail = InputHelper.getString("enter your status descriptipon\n", attempt);
            if (status_Detail.equalsIgnoreCase("exit")) {
                System.out.println("return to main page.....");
                System.out.println("------------------------------------------------------------\n");
                staff.staff_option(M_id, Landlord_id);

            }

            try {
                Connection con = GetDatabase.GetDb();
                PreparedStatement pree = con.prepareStatement("select * from tasks where landlord_id=? And task_id=? and assigned_to is not null");
                pree.setString(1, Landlord_id);
                pree.setString(2, task_id);
                ResultSet result = pree.executeQuery();
                if (result.next()) {
                    try {
                        PreparedStatement pre = conee.prepareStatement("""
                                                        UPDATE `tasks`
                                                       SET `detail_status` =?
                                                       WHERE `assigned_to`=?;""" //
                        //
                        );
                        pre.setString(1, status_Detail);
                        pre.setString(2, M_id);
                        int i = pre.executeUpdate();
                        if (i > 0) {
                            System.out.println("successfull updated");
                        } else {
                            System.out.println("problem occur");
                            update_task_status_detail(M_id, Landlord_id);
                        }
                    } catch (Exception e) {
                        System.out.println("exeption in update task status details retrnign to main page");
                        e.printStackTrace();
                        App.Appp();

                    }

                } else {

                    System.out.println("You Enter Wrong Task ID");
                    update_task_status_detail(M_id, Landlord_id);
                }
            } catch (Exception e) {
                System.out.println("exeptionin in upeer try catch in update task assign meth0od returning to main page ");
                e.printStackTrace();
                App.Appp();
            }
        }

    }
    static int attempthow[] = {0};

    public static void task_mark_complete(String M_id, String Landlord_id) {

        while (true) {

            if (attempthow[0] == 3) {
                System.out.println("You Loose Your Three Attempts");
                staff.staff_option(M_id, Landlord_id);
                return;
            }
            int attempt[] = {0};
            view_task(M_id, Landlord_id, "2");
            Connection conee = GetDatabase.GetDb();
            System.out.println("You can type 'exit'  to go back.");
            String task_id = InputHelper.getString("Enter task id :- ", attempt);

            attempthow[0]++;
            if (task_id.equalsIgnoreCase("exit")) {
                System.out.println("Exiting  process. Goodbye!");
                System.out.println("------------------------------------------------------------\n");
                staff.staff_option(M_id, Landlord_id);

            }
            String option = InputHelper.getString("1.complete\n2.pending \n", attempt);
            if (option.equalsIgnoreCase("exit")) {
                System.out.println("Exiting  process. Goodbye!");
                System.out.println("------------------------------------------------------------\n");
                staff.staff_option(M_id, Landlord_id);

            }

            if (option.equalsIgnoreCase("1")) {
                option = "complete";
            } else if (option.equalsIgnoreCase("2")) {
                option = "pending";
            } else {
                System.out.println("enter Wrong");
            }

            try {
                Connection con = GetDatabase.GetDb();
                PreparedStatement pree = con.prepareStatement("select * from tasks where landlord_id=? And task_id=? and assigned_to =?");
                pree.setString(1, Landlord_id);
                pree.setString(2, task_id);
                pree.setString(3, M_id);
                ResultSet result = pree.executeQuery();
                if (result.next()) {
                    try {
                        PreparedStatement pre = conee.prepareStatement("""
                                                    UPDATE `tasks`
                                                   SET `status` =?
                                                   WHERE `assigned_to`=?;""" //
                        //
                        );
                        pre.setString(1, "Completed");
                        pre.setString(2, M_id);
                        int i = pre.executeUpdate();
                        if (i > 0) {
                            System.out.println("Succesfully updated");
                        } else {
                            System.out.println("problem occur");
                            update_task_status_detail(M_id, Landlord_id);
                        }
                    } catch (Exception e) {
                        System.out.println("exeption in task mark complete ");
                        e.printStackTrace();
                        App.Appp();

                    }

                } else {
                    System.out.println("you Entered Wrong Task ID");

                }
            } catch (Exception e) {
                System.out.println("exeptionin in upeer try catch in mark task complete method ");
                e.printStackTrace();
                App.Appp();
            }
        }
    }

    public static void communication(String M_id, String Landlord) {
        int attempt[] = {0};
        String box = """
  =========================================
  |       Communication Options           |
  =========================================
  |  1. Send message to landlord          |
  |  2. Send message to tenants           |
  |  3. See landlord's messages           |
  |  4. See tenants' messages             |
  =========================================
  |  Press any number to go back.         |  
  =========================================
        """;
        String option = InputHelper.getString(box + "\n", attempt);
        if (option.equalsIgnoreCase("exit")) {
            System.out.println("return to main page.....");
            System.out.println("------------------------------------------------------------\n");
            staff.staff_option(M_id, Landlord);

        }
        if (option.equalsIgnoreCase("1")) {
            System.out.println(send_messege_landlord(M_id, Landlord));
            communication(M_id, Landlord);
        } else if (option.equalsIgnoreCase("2")) {
            send_messege_Tanents(M_id, Landlord);
            communication(M_id, Landlord);
        } else if (option.equalsIgnoreCase("3")) {
            see_messegges_Landlord(M_id, Landlord);
            communication(M_id, Landlord);

        } else if (option.equalsIgnoreCase("4")) {
            see_Tanents_messeges(M_id, Landlord);
            communication(M_id, Landlord);

        } else {
            staff.staff_option(M_id, Landlord);
        }

    }

    public static String send_messege_landlord(String M_id, String Landlord_id) {
        int[] attempt = {0};
        int[] messageAttempts = {0};
        Connection connection;
        PreparedStatement preparedStatement;

        while (true) {
            System.out.println("You can type 'exit' for back \n");
            String message = InputHelper.getString("Enter your message: ", attempt);

            connection = GetDatabase.GetDb();
            try {
                preparedStatement = connection.prepareStatement(
                        "INSERT INTO communication (staff_id, landlord_id, message_content) VALUES (?, ?, ?);"
                );
                preparedStatement.setString(1, M_id);
                preparedStatement.setString(2, Landlord_id);
                preparedStatement.setString(3, message);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Message sent successfully.");

                    return "Message sent successfully.";
                } else {
                    System.out.println("Message not sent due to an error.");
                    staff.staff_option(M_id, Landlord_id);
                    return null;
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("An exception occurred while sending the message.");
                staff.staff_option(M_id, Landlord_id);
                return null;
            }
        }
    }

    public static void main(String[] args) {
        Get_user_id_without_passs("T4117", "");
    }

    public static String Get_user_id_without_passs(String L_id, String t_id) {
        while (true) {
            Connection cone = GetDatabase.GetDb();
            try {
                PreparedStatement pree = cone.prepareStatement("select * from `properties_details` where tnaents_id=?and landlord_id=?");
                pree.setString(1, t_id);
                pree.setString(2, L_id);
                ResultSet reee = pree.executeQuery();
                if (reee.next()) {
                    return t_id;
                } else {
                    System.out.println("Enter Right Tanents ID");
                    return null;
                }
            } catch (Exception e) {
                System.out.println(" exeption int mehtofd Get_user_id_without_pass");
                e.printStackTrace();
                Landlord.land_lord_option(L_id, t_id);
                return null;
            }
        }
    }
// public static String checckvalidTanentsid(String tanents ,String Landlord){
//     while(true){
//     Connection connection;
//     PreparedStatement preparedStatement;

//     connection=GetDatabase.GetDb();
//     try {
//         preparedStatement=connection.prepareStatement("select * from `properties_details` where landlord_id=? and tnaents_id=?");
//         preparedStatement.setString(1, Landlord);
//         preparedStatement.setString(2, tanents);
//         ResultSet re=  preparedStatement.executeQuery();
//         if (!re.next()) {
//             System.out.println("Enter Right Tanetns Id");
//             continue;
//         }else{
//           return tanents;
//         }
//     } catch (Exception e) {
//         System.out.println("exeption in method cehck valid taent id for staff messee tanegtds ");
//         e.printStackTrace();
//     }}
// }
    public static String send_messege_Tanents(String M_id, String Landlord_id) {

        int[] attempt = {0};
        int[] messageAttempts = {0};
        Connection connection;
        PreparedStatement preparedStatement;

        while (true) {
            System.out.println("You can type 'exit' for back \n");
            String tenantId = InputHelper.getString("Enter your Tenant ID to send a message: ", attempt);
            if (tenantId.equalsIgnoreCase("exit")) {
                communication(M_id, Landlord_id);
            }

            String validatedTenantId = Get_user_id_without_passs(Landlord_id, tenantId);

            if (validatedTenantId == null) {
                System.out.println("No tenant ID found. Please try again.");
                return send_messege_Tanents(M_id, Landlord_id);
            }

            if (!validatedTenantId.matches("^T\\d{4}$")) {
                System.out.println("Invalid tenant ID format. Please use the format T1234.");
                continue;
            }

            if (!validatedTenantId.equalsIgnoreCase(tenantId)) {
                System.out.println("tanet id not found enter again");
                continue;
            }

            String message = InputHelper.getString("Enter your message: ", attempt);

            connection = GetDatabase.GetDb();
            try {
                preparedStatement = connection.prepareStatement(
                        "INSERT INTO communication (tanents_id, staff_id, message_content) VALUES (?, ?,?);"
                );
                preparedStatement.setString(1, validatedTenantId);
                preparedStatement.setString(2, M_id);
                preparedStatement.setString(3, message);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Message sent successfully.");
                    staff.staff_option(M_id, Landlord_id);

                } else {
                    System.out.println("Message not sent due to an error.");
                    staff.staff_option(M_id, Landlord_id);
                    return null;
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("An exception occurred while sending the message.");
                staff.staff_option(M_id, Landlord_id);
                return null;
            }
        }
    }

    public static void see_messegges_Landlord(String M_id, String Landlord_id) {

        Connection con;
        PreparedStatement pre;
        ResultSet re;
                                                               

        try {
            con = GetDatabase.GetDb();
            pre = con.prepareStatement("SELECT * FROM communication WHERE staff_id = ? AND landlord_id = ?");

            pre.setString(1, M_id);
            pre.setString(2, Landlord_id);
            re = pre.executeQuery();

            if (!re.isBeforeFirst()) {
                System.out.println("No messages found for the given LAand lord and  mainrance");
            } else {
                while (re.next()) {
                    String message_id = re.getString("message_id");

                    String landlord_id_get = re.getString("landlord_id");
                    String message_content = re.getString("message_content");
                    String timestamp = re.getString("timestamp");
                    String maintace_id = re.getString("staff_id");

                    System.out.println("_________________________________________");
                    System.out.println("Message ID: " + message_id);
                    System.out.println("Maintaince  (Receiver): " + maintace_id);
                    System.out.println("Landlord ID (Sender): " + landlord_id_get);
                    System.out.println("Message Content: " + message_content);
                    System.out.println("Timestamp: " + timestamp);
                    System.out.println("_________________________________________");

                }

            }
        } catch (Exception e) {
            System.out.println("Exception in see_Tanents_messeges method.");
            e.printStackTrace();
            staff.staff_option(M_id, Landlord_id);
        }

    }

    public static void see_Tanents_messeges(String M_id, String Landlord_id) {
        int[] attempt = {0};
        Connection con;
        PreparedStatement pre;
        ResultSet re;

        while (true) {
            String tenantId = InputHelper.getString("Enter your Tenant ID to view messages: ", attempt);
            if (tenantId.equalsIgnoreCase("exit")) {
                System.out.println("return to main page.....");
                System.out.println("------------------------------------------------------------\n");
                staff.staff_option(M_id, Landlord_id);

            }
            if (tenantId == null || tenantId.isEmpty()) {
                System.out.println("Tenant ID cannot be empty. Please try again.");
                continue;
            }

            if (!tenantId.matches("^T\\d{4}$")) {
                System.out.println("Invalid tenant ID format. Please use the format T1234.");
                continue;
            }

            try {
                con = GetDatabase.GetDb();
                pre = con.prepareStatement("SELECT * FROM communication WHERE tanents_id = ? AND staff_id = ?");
                pre.setString(1, tenantId);
                pre.setString(2, M_id);
                re = pre.executeQuery();

                if (re.next()) {
                    String message_id = re.getString("message_id");
                    String tenant_id = re.getString("tanents_id");

                    String message_content = re.getString("message_content");
                    String timestamp = re.getString("timestamp");
                    String maintace_id = re.getString("staff_id");
                    System.out.println("Message ID: " + message_id + "\n"
                            + " | Staff ID: sender " + maintace_id + "\n"
                            + " | Tanents ID:reciver " + tenant_id + "\n"
                            + " | Message Content: " + message_content + "\n"
                            + " | Timestamp: " + timestamp + "\n");
                    return;
                } else {
                    System.out.println("not found tanents id in db");

                }

            } catch (Exception e) {
                System.out.println("Exception in see_Landlord_messeges method.");
                e.printStackTrace();
                App.Appp();
            }
        }
    }
}
