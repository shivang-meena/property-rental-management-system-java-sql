
import java.sql.*;
import java.util.*;

public class fill_details_DB {

    static int role;

    static InputValidator checckvalid = new InputValidator();

//if choose registration then come this method 
    public static void start_db_store(String option) {
        //then call this method 
        store_into_db.store_db(option);
    }
    //    static String full_name;
    // public static String set_user_name() {

    //     System.out.println("enter your first name");
    //     String first_name = input.nextLine();
    //     System.out.println("enter your last name ");
    //     String last_name = input.nextLine();
    //     while (true) {
    //         if (first_name.matches("[a-zA-Z]+$") && last_name.matches("[a-zA-Z]+$")) {
    //             System.out.println("Full name is :- " + first_name + " " + "" + last_name);
    //             full_name=first_name + " " + "" + last_name;
    //             return full_name;
    //         } else {
    //             System.out.println("Invalid input! Only alphabetic characters are allowed.");
    //             System.out.println("enter again valid first name");
    //             first_name = input.nextLine();
    //             System.out.println("enter again valid last name ");
    //             last_name = input.nextLine();
    //         }
    //     }
    // }
    static String user_id_reg;

    public static String set_user_id(String i) {
        Random random = new Random();

        while (true) {
            int user_idint = 1000 + random.nextInt(9000);
            if (i.equalsIgnoreCase("tanents")) {
                System.out.println("your user id is :- " + "T" + user_idint);
                user_id_reg = "T" + user_idint;
                return user_id_reg;
            } else if (i.equalsIgnoreCase("landlord")) {
                System.out.println("your user id is :- " + "L" + user_idint);
                user_id_reg = "L" + user_idint;
                return user_id_reg;
            }
        }

    }

    // private static boolean isPasswordInDatabase(String password) {
    //     Connection con = GetDatabase.GetDb();
    //     try {
    //         PreparedStatement pre = con.prepareStatement("SELECT 1 FROM user_table WHERE password = ?");
    //         pre.setString(1, password);
    //         ResultSet re = pre.executeQuery();
    //         return re.next();
    //     } catch (Exception e) {
    //         System.out.println("Exception while checking password in database:");
    //         e.printStackTrace();
    //         return false; 
    //     } finally {
    //         try {
    //             if (con != null) con.close();
    //         } catch (Exception ex) {
    //             ex.printStackTrace();
    //         }
    //     }}
    // public static String set_password() {
    //     String password;
    //     while (true) {
    //         System.out.println("Enter your password:");
    //         System.out.println("The first 2 characters should be alphabetic, and the next 4 numeric digits.");
    //         password = input.nextLine();
    //         if (!password.matches("^[a-zA-Z]{2}\\d{4}$")) {
    //             System.out.println("Invalid password! Follow the format: 2 alphabetic characters + 4 numeric digits.");
    //         } else {
    //             String unsafePassword = Get_Metods.Get_user_password(user_id_reg, full_name);
    //             if (password.equals(unsafePassword)) {
    //                 System.out.println("Password is unsafe. Please enter a safer password.");
    //             } else if (isPasswordInDatabase(password)) {
    //                 System.out.println("Password already exists in the system. Please choose a different password.");
    //             } else {
    //                 System.out.println("Password successfully registered.");
    //                 System.out.println("Your password: " + password);
    //                 return password;
    //             }
    //         }
    //     }
    // }
    // public static String set_role() {
    //     while (true) {
    //         try {
    //             System.out.println("Enter your role for Registration :");
    //             System.out.println("1. Tenants");
    //             System.out.println("2. Landlord");
    //             System.out.println("3. Maintenance");
    //             role = input.nextInt();
    //             input.nextLine();
    //             switch (role) {
    //                 case 1:
    //                     return "Tenants";
    //                 case 2:
    //                     return "Landlord";
    //                 case 3:
    //                     return "Maintenance";
    //                 default:
    //                     System.out.println("Invalid input. Please enter a number between 1 and 3.");
    //             }
    //         } catch (InputMismatchException e) {
    //             System.out.println("Invalid input. Please enter a valid number.");
    //             input.nextLine();
    //         }
    //     }
    // }
    // public static String set_adress() {
    //     System.out.println("enter your address");
    //     String address = input.nextLine();
    //     return address;
    // }
    // public static String set_email(int i) {
    //     String email;
    //     if (i == 1) {
    //         System.out.println("Enter your email in the format: xyz.tenant@gmail.com");
    //         while (true) {
    //             email = input.nextLine().trim();
    //             if (email.matches("^[a-zA-Z]+\\.tenant@gmail\\.com$")) {
    //                 System.out.println("your email :- " + email);
    //                 return email;
    //             } else {
    //                 System.out.println("Invalid email format. Please use 'xyz.tenant@gmail.com'. Try again:");
    //             }
    //         }
    //     } else if (i == 2) {
    //         System.out.println("Enter your email in the format: xyz.landlord@gmail.com");
    //         while (true) {
    //             email = input.nextLine().trim();
    //             if (email.matches("^[a-zA-Z]+\\.landlord@gmail\\.com$")) {
    //                 System.out.println("your email :- " + email);
    //                 return email;
    //             } else {
    //                 System.out.println("Invalid email format. Please use 'xyz.landlord@gmail.com'. Try again:");
    //             }
    //         }
    //     } else if (i == 3) {
    //         System.out.println("Enter your email in the format: xyz.staff@gmail.com");
    //         while (true) {
    //             email = input.nextLine().trim();
    //             if (email.matches("^[a-zA-Z]+\\.staff@gmail\\.com$")) {
    //                 System.out.println("your email :- " + email);
    //                 return email;
    //             } else {
    //                 System.out.println("Invalid email format. Please use 'xyz.staff@gmail.com'. Try again:");
    //             }
    //         }
    //     } else {
    //         System.out.println("Invalid role selected.");
    //         return null;
    //     }
    // }
    // public static String set_phone_no() {
    //     System.out.println("Enter phone no");
    //     String phoneNumber = input.nextLine();
    //     while (true) {
    //         if (phoneNumber.matches("[6789]\\d{9}")) {
    //             System.out.println("your phoone Number :- " + "+91 " + phoneNumber);
    //             return "+91 " + phoneNumber;
    //         } else {
    //             System.out.println(
    //                     "Invalid phone number Must start with 6, 7, 8, or 9 and contain 10 digits and not alpabaticall.");
    //             System.out.print("Enter phone number again: ");
    //             phoneNumber = input.nextLine();
    //         }
    //     }
    // }
    // public static String set_pan_no() {
    //     System.out.println("enter youur pan no.");
    //     String panNumber = input.nextLine();
    //     panNumber = panNumber.toUpperCase();
    //     while (true) {
    //         if (panNumber.matches("^[A-Za-z]{5}\\d{4}[A-Za-z]{1}$")) {
    //             System.out.println("Valid PAN number: " + panNumber);
    //             return panNumber;
    //         } else {
    //             System.out.println("Invalid PAN number! The format should be:  (5 letters, 4 digits, 1 letter).");
    //             System.out.print("Enter a valid PAN number: ");
    //             panNumber = input.nextLine();
    //         }
    //     }
    // }
}

class store_into_db {

    static InputValidator checckvalid = new InputValidator();
    static String id;
    static String password;

    //this methid is for store user details in database 
    public static void store_db(String option) {
        //this query for registration for any user 
        String query = "insert into user_table values(?,?,?,?,?,?,?,?) ";
        Connection con = GetDatabase.GetDb();
        try {
            String role;
            String ide;
            String passworde;
            //then set the value to sql
            PreparedStatement pre = con.prepareStatement(query);
            pre.setString(4, role = checckvalid.getvalidRole(option));
            pre.setString(1, ide = fill_details_DB.set_user_id(role));
            pre.setString(2, checckvalid.getvalidName());
            pre.setString(3, passworde = checckvalid.validPassword());
            pre.setString(5, checckvalid.getEmail());
            pre.setString(6, checckvalid.validPhoneNumber());
            pre.setString(7, checckvalid.getValidAddress());
            pre.setString(8, checckvalid.validPanNumber());
            int inn = pre.executeUpdate();

            if (inn > 0) {

                System.out.println("Succesfully Registered");
                login(option, ide, passworde);

            } else {
                System.out.println("failed any technical error");
                App.Appp();
            }
        } catch (Exception e) {
            System.out.println("exeption in store_into_db class ");
            System.out.println(e);
        } finally {
            try {

                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void login(String option, String id, String password) {
        if (option.equalsIgnoreCase("1")) {
            Landlord.land_lord_option(id, password);
        } else if (option.equalsIgnoreCase("2")) {
            Tanants.Tanents_option(id, password);
        }
    }

}

class property_detail_fill_db {

    static String id;
    static String password;
    static Scanner input = new Scanner(System.in);

    // public static String status_property() {
    //     System.out.println("Enter status: 1 for occupied, 2 for not occupied");
    //     String statuss = input.nextLine();
    //     while (true) {
    //         if (statuss.equals("1")) {
    //             return "occupied"; 
    //         } else if (statuss.equals("2")) {
    //             return "not_occupied"; 
    //         } else {
    //             System.out.println("Invalid value. Please enter '1' for occupied or '2' for not occupied.");
    //             statuss = input.nextLine();  
    //         }
    //     }
    // }
    public static String getValidStatusProperty() {
        Scanner scanner = new Scanner(System.in);
        int[] statusAttempts = {0};

        while (true) {
            String status = InputHelper.getString("Enter property status: 1 for Occupied, 2 for Not Occupied: ", statusAttempts);
            System.out.println("------------------------------------------------------------\n");

            if (status != null) {
                status = status.trim();

                if (status.equals("1")) {
                    System.out.println("Status successfully validated as 'Occupied'.");
                    System.out.println("------------------------------------------------------------\n");
                    return "Occupied";
                } else if (status.equals("2")) {
                    System.out.println("Status successfully validated as 'Not Occupied'.");
                    System.out.println("------------------------------------------------------------\n");
                    return "Not Occupied";
                } else {
                    System.out.println("Invalid input. Please enter '1' for Occupied or '2' for Not Occupied.");
                    System.out.println("------------------------------------------------------------\n");
                    statusAttempts[0]++;
                }
            }

            if (statusAttempts[0] >= 3) {
                int menuAttempts = 0;

                while (true) {
                    System.out.println("Too many invalid status attempts.");
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
                            return null;
                        }
                        continue;
                    }

                    switch (choice) {
                        case 1:
                            statusAttempts[0] = 0;
                            break;
                        case 2:
                            System.out.println("Returning to main menu...");
                            System.out.println("------------------------------------------------------------\n");
                            return null;
                        default:
                            System.out.println("Invalid choice. Please enter 1 to retry or 2 to exit.");
                            System.out.println("------------------------------------------------------------\n");
                            menuAttempts++;

                            if (menuAttempts >= 3) {
                                System.out.println("Too many invalid menu choices. Returning to main menu...");
                                System.out.println("------------------------------------------------------------\n");
                                return null;
                            }
                            continue;
                    }

                    break;
                }
            }
        }
    }
    //----------------------------------------------------------------------------------------------------------------------------------

// public static String Property_id() {
//     while (true) {
//         System.out.println("Enter property id like this P1, P2, P3:");
//         String property_id = input.nextLine();
//         property_id = property_id.toUpperCase();
//         String check_id = Set_properties_details.Get_property_id(property_id);
//         System.out.println(check_id);
//         if (!property_id.matches("^P\\d+$")) {
//             System.out.println("Invalid format. Please enter the property id in the correct format (e.g., P1, P2, P3).");
//             continue;
//         }
//         if (check_id.equalsIgnoreCase(property_id)) {
//             System.out.println("Duplicate property id. Please enter a unique property id.");
//             continue;
//         }
//         System.out.println("Success");
//         return property_id;
//     }
// }
    public static String tanents_id() {
        int attempts = 3;

        while (attempts > 0) {
            while (true) {
                System.out.println("Enter your tnaents id:");
                id = input.nextLine().trim();

                System.out.println("Enter your password:");
                password = input.nextLine().trim();

                String name = Get_Metods.Get_user_name(id, password);
                String user_id = Get_Metods.Get_user_id(name, password);
                String password_me = Get_Metods.Get_user_password(id, name);
                if (id.matches("^T\\d{4}$")) {
                    if (id.equalsIgnoreCase(user_id) && password.equalsIgnoreCase(password_me)) {
                        return id;
                    } else {
                        attempts--;
                        System.out.println("Wrong ID or Password. Attempts left: " + attempts);
                        return tanents_id();
                    }
                } else {
                    System.out.println("not valid enter agin");
                    return tanents_id();
                }
            }

        }

        System.out.println("Too many failed  attempts. Returning to main menu.");
        Landlord.Land_lordslogin();
        return null;
    }

//-----------------------------------------------------------------------------------------------------------------------------------//
    //check valid property id//
//------------------------------------------------------------------------------------------------------------------------------------//
    public static String getValidPropertyId() {
        Scanner scanner = new Scanner(System.in);
        int[] propertyIdAttempts = {0};

        while (true) {
            String propertyId = InputHelper.getString("Enter property ID (e.g., P1, P2, P3): ", propertyIdAttempts);
            System.out.println("------------------------------------------------------------\n");

            if (propertyId != null) {
                propertyId = propertyId.trim().toUpperCase();
                propertyId = propertyId.toUpperCase();
                if (propertyId.equalsIgnoreCase("exit")) {
                    System.out.println("Returning to  menu...");
                    System.out.println("------------------------------------------------------------\n");
                    Landlord.property_Manage(id, password);

                }

                if (!propertyId.matches("^P\\d+$")) {
                    System.out.println("Invalid format. Property ID must start with 'P' followed by digits (e.g., P1, P2, P3).");
                    System.out.println("------------------------------------------------------------\n");

                } else {
                    String existingPropertyId = Set_properties_details.Get_property_id(propertyId);

                    if (existingPropertyId != null && existingPropertyId.equalsIgnoreCase(propertyId)) {
                        System.out.println("property id is already exist Please enter a unique property ID.");
                        System.out.println("------------------------------------------------------------\n");

                    } else {
                        System.out.println("Property ID successfully validated.");
                        System.out.println("------------------------------------------------------------\n");
                        return propertyId;
                    }
                }
                propertyIdAttempts[0]++;
            }

            if (propertyIdAttempts[0] >= 3) {
                int menuAttempts = 0;

                while (true) {
                    System.out.println("Too many invalid property ID attempts.");
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
                            return null;
                        }
                        continue;
                    }

                    switch (choice) {
                        case 1:
                            propertyIdAttempts[0] = 0;
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
//-----------------------------------------------------------------------------------------------------------------------------------//
//-----------------------------------------------------------------------------------------------------------------------------------//
//-----------------------------------------------------------------------------------------------------------------------------------//
    //                                                                      check valid landlord id registration

    public static String getValidLandlordId() {
        Scanner scanner = new Scanner(System.in);
        int[] landlordIdAttempts = {0};

        while (true) {

            String id = InputHelper.getString("Enter your landlord ID (e.g., L1234): ", landlordIdAttempts);
            System.out.println("------------------------------------------------------------\n");
            id = id.toUpperCase();

            String password = InputHelper.getString("Enter your password: ", landlordIdAttempts);
            System.out.println("------------------------------------------------------------\n");

            if (id != null && password != null) {
                id = id.toUpperCase();

                if (!id.matches("^L\\d{4}$")) {
                    System.out.println("Invalid format. Landlord ID must be in the format 'L1234'.");
                    System.out.println("------------------------------------------------------------\n");
                    landlordIdAttempts[0]++;
                } else {

                    String name = Get_Metods.Get_user_name(id, password);
                    String userId = Get_Metods.Get_user_id(name, password);
                    String userPassword = Get_Metods.Get_user_password(id, name);

                    if (id.equals(userId) && password.equals(userPassword)) {
                        System.out.println("Authentication successful.");
                        System.out.println("------------------------------------------------------------\n");

                        return id;
                    } else {
                        System.out.println("Invalid landlord ID or password. Please try again.");
                        System.out.println("------------------------------------------------------------\n");
                        landlordIdAttempts[0]++;
                    }
                }
            }

            if (landlordIdAttempts[0] >= 3) {
                int menuAttempts = 0;

                while (true) {
                    System.out.println("Too many invalid attempts.");
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
                            return null;
                        }
                        continue;
                    }

                    switch (choice) {
                        case 1:
                            landlordIdAttempts[0] = 0;
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

//    public static String property_address(){
//     System.out.println("enter your property address ");
//     String address=input.nextLine();
//     return address;
//    }
// ------------------------------------------------------------------------------------------------------------------------------------//
//-----------------------------------------------------check valid address or not//
    public static String getValidPropertyAddress() {
        Scanner scanner = new Scanner(System.in);
        int[] addressAttempts = {0};

        while (true) {
            String address = InputHelper.getString("Enter your property address: ", addressAttempts);
            System.out.println("------------------------------------------------------------\n");

            if (address != null) {
                address = address.trim();
                if (address.equalsIgnoreCase("exit")) {
                    System.out.println("Returning to  menu...");
                    System.out.println("------------------------------------------------------------\n");
                    Landlord.property_Manage(id, password);

                }
                if (address.equalsIgnoreCase("exit")) {
                    System.out.println("returning to main menu");
                    System.out.println("------------------------------------------------------------\n");
                    Landlord.property_Manage(id, password);

                }
                if (address.isEmpty()) {
                    System.out.println("Address cannot be empty. Please enter a valid address.");
                    System.out.println("------------------------------------------------------------\n");

                } else if (address.length() < 5 || address.length() > 100) {
                    System.out.println("Address must be between 5 and 100 characters. Please try again.");
                    System.out.println("------------------------------------------------------------\n");

                } else if (!address.matches("^[a-zA-Z0-9,\\\\.]+( [a-zA-Z0-9,\\\\.]+)*$")) {
                    System.out.println("Address can only contain letters, numbers,one  spaces, commas, periods, and hyphens.");
                    System.out.println("------------------------------------------------------------\n");

                } else {
                    System.out.println("Property address successfully validated.");
                    System.out.println("------------------------------------------------------------\n");
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

//    public static String property_size(){
//     System.out.println("Enter property size in dimensions like '12*12' (length*width in feet):");
//     String size = input.nextLine();
//     if (size.matches("^\\d+\\*\\d+$")) {
//         return size;
//     } else {
//         System.out.println("Invalid format. Please enter the size in the correct format (e.g., 12*12).");
//         return property_size(); 
//     }
//    }
//--------------------------------------------------------------------------------------------------------------------------------------//
//---------------------------------------------------------------------check valid  property size or not-------------------------------
//--------------------------------------------------------------------------------------------------------------------------------------//
    public static String getValidPropertySize() {
        Scanner scanner = new Scanner(System.in);
        int[] sizeAttempts = {0};

        while (true) {
            String size = InputHelper.getString("Enter property size in dimensions (e.g., '12*12' in feet): ", sizeAttempts);
            System.out.println("------------------------------------------------------------\n");

            if (size != null) {
                size = size.trim();

                if (size.equalsIgnoreCase("exit")) {
                    System.out.println("returning to main menu....");
                    System.out.println("------------------------------------------------------------\n");
                    Landlord.property_Manage(id, Landlord.password);

                }
                if (size.isEmpty()) {
                    System.out.println("Size cannot be empty. Please enter a valid size.");
                    System.out.println("------------------------------------------------------------\n");

                } else if (!size.matches("^\\d+\\*\\d+$")) {
                    System.out.println("Invalid format. Please enter the size in the correct format (e.g., '12*12').");
                    System.out.println("------------------------------------------------------------\n");

                } else {
                    System.out.println("Property size successfully validated.");
                    System.out.println("------------------------------------------------------------\n");
                    return size;
                }
                sizeAttempts[0]++;
            }

            if (sizeAttempts[0] >= 3) {
                int menuAttempts = 0;

                while (true) {
                    System.out.println("Too many invalid size attempts.");
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
                            return null;
                        }
                        continue;
                    }

                    switch (choice) {
                        case 1:
                            sizeAttempts[0] = 0;
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

//    public static String rent_amount(){
//     String rent;
//     System.out.println("Enter the rent amount (only integers allowed):- ");
//     while (true) {
//         rent =input.nextLine();
//         if (rent.matches("^\\d+$")) {
//             System.out.println("property  rent :- " + rent);
//             return rent;
//         } else {
//             System.out.println("Invalid input. Please enter a valid rent amount (e.g., 1000):");
//         }
//    }
// }
//--------------------------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------check ammount validation for property rent-----------------------------------------------------------//
//--------------------------------------------------------------------------------------------------------------------------------------//
    public static String getValidRentAmount() {
        Scanner scanner = new Scanner(System.in);

        int[] rentAttempts = {0};
        while (true) {

            String rent = InputHelper.getString("Enter the rent amount (only integers allowed): ", rentAttempts);
            System.out.println("------------------------------------------------------------\n");
            rentAttempts[0]++;

            if (rent != null) {
                rent = rent.trim();
                if (rent.equalsIgnoreCase("exit")) {
                    System.out.println("returning to main menu");
                    System.out.println("------------------------------------------------------------\n");
                    Landlord.property_Manage(id, password);

                }
                int ammount;

                if (rent.isEmpty()) {
                    System.out.println("Rent amount cannot be empty. Please enter a valid amount.");
                    System.out.println("------------------------------------------------------------\n");

                } else if (!rent.matches("^[1-9][0-9]*$")) {
                    System.out.println("The Rent Ammount does Not Start With Zero");

                } else if (!rent.matches("^\\d+$")) {
                    System.out.println("Invalid input. Rent must be a positive integer (e.g., 1000). Please try again.");
                    System.out.println("------------------------------------------------------------\n");

                } else if (Integer.parseInt(rent) > Integer.parseInt("1000")) {
                    System.out.println("Rent amount successfully validated: " + rent);
                    System.out.println("------------------------------------------------------------\n");
                    return rent;
                } else {

                    System.out.println("rent ammount is not valid");

                    continue;
                }

            }

            if (rentAttempts[0] >= 3) {
                int menuAttempts = 0;

                while (true) {
                    System.out.println("Too many invalid rent amount attempts.");
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
                            return null;
                        }
                        continue;
                    }

                    switch (choice) {
                        case 1:
                            rentAttempts[0] = 0;
                            break;
                        case 2:
                            System.out.println("Returning to main menu...");
                            System.out.println("------------------------------------------------------------\n");
                            Landlord.land_lord_option(id, password);
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

    ///-------------------------------------------------------------------------------------------------------------------------------------//

        //if landlord wants to see which proerty aquire the tanents then
     public static void view_tanents_information_landlord(String id) {
        Connection con = GetDatabase.GetDb();
        try {
            PreparedStatement pre = con.prepareStatement("SELECT * FROM properties_details WHERE landlord_id = ? AND tnaents_id IS NOT NULL");
            pre.setString(1, id);
            ResultSet re = pre.executeQuery();

            if (!re.next()) {
                // if landlord properties not aqure any tanents 
                System.out.println("you have no tanents");
            } else {
                do {
                    String property_id = re.getString("property_id");
                    String landlord_id = re.getString("landlord_id");
                    String property_addres = re.getString("property_addres");
                    String size = re.getString("size");
                    String rent_amount = re.getString("rent_amount");
                    String status = re.getString("status");
                    String tenants_id = re.getString("tnaents_id");
                    //if they have tanetns then print the details
                    System.out.println(
                        "Property Details:\n" +
                        "-----------------\n" +
                        "Property ID      : " + property_id + "\n" +
                        "Landlord ID      : " + landlord_id + "\n" +
                        "Property Address : " + property_addres + "\n" +
                        "Size             : " + size + "\n" +
                        "Rent Amount      : " + rent_amount + "\n" +
                        "Status           : " + status + "\n" +
                        "Tenant ID        : " + tenants_id + "\n"
                );
                
                } while (re.next());
            }
        } catch (Exception e) {
            System.out.println("exeption in view tanents information landlor dmethod");
            e.printStackTrace();
            Landlord.Land_lordslogin();

        }
    }

    public static void main(String[] args) {

    }

    //these method is for add properties for landlord 
    public static void property_table_store_db(String landlor_id, String passworde) {
        String property, address, size, ammount;
        String query = "insert into properties_details values(?,?,?,?,?,?,?)";
        Connection con = GetDatabase.GetDb();
        try {
            System.out.println(landlor_id + " lond lor d idsanfjfajsdbfksjd");
            PreparedStatement pre = con.prepareStatement(query);
            pre.setString(1, property = getValidPropertyId());
            pre.setString(2, landlor_id);
            pre.setString(3, address = getValidPropertyAddress());
            pre.setString(4, size = getValidPropertySize());
            pre.setString(5, ammount = getValidRentAmount());
            pre.setString(6, "not_occupied");
            pre.setString(7, null);
//check if any value is null then go to back
            if (property == null || landlor_id == null || address == null || size == null || ammount == null) {
                System.out.println("you enter any null value");
                Landlord.land_lord_option(landlor_id, passworde);

            }

            int inn = pre.executeUpdate();
            if (inn > 0) {
//if property succesfully added 
                System.out.println("Succesfully Registered");
                Landlord.land_lord_option(landlor_id, passworde);

            } else {
                //if any probelm was occur 
                System.out.println("failed any technical error");
                System.out.println("regter again");
            }
        } catch (Exception e) {
            System.out.println("exeption in store_into_db class ");
            e.printStackTrace();
            App.Appp();
        } finally {
            try {
                //close the connection
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void acquire_property(String id) {
        int attempt[] = {0};
        Connection conee = GetDatabase.GetDb();

        while (true) {
            System.out.println("You can type 'exit' for back \n");
            String property_id = InputHelper.getString("Enter property ID (e.g., P1, P2, P3): ", attempt);
            System.out.println("------------------------------------------------------------\n");
            if (property_id.equalsIgnoreCase("exit")) {
                System.out.println("Returning to main page...");
                System.out.println("------------------------------------------------------------\n");
                Tanants.aquire_property();
                break;
            }
            if (property_id != null) {
                property_id = property_id.trim().toUpperCase();

                if (!property_id.matches("^P\\d+$")) {
                    System.out.println("Invalid format. Property ID must start with 'P' followed by digits (e.g., P1, P2, P3).");
                    System.out.println("------------------------------------------------------------\n");
                    continue;
                }

                try {

                    PreparedStatement pree = conee.prepareStatement(
                            "UPDATE `properties_details` "+ "SET `status` = ?, `tnaents_id` = ? "+ "WHERE `property_id` = ? AND `status` = 'not_occupied';"
                    );
                    pree.setString(1, "occupied");
                    pree.setString(2, id);
                    pree.setString(3, property_id);

                    int rowsUpdated = pree.executeUpdate();
                    if (rowsUpdated > 0) {
                        System.out.println("Successfully occupied the property!");
                        break;
                    } else {
                        System.out.println("Property not available or already occupied. Please try again.");
                    }
                } catch (Exception e) {
                    System.out.println("Exception in acquire_property method.");
                    e.printStackTrace();
                    Tanants.Tanents_option(id, password);
                    break;
                }
            }
        }
    }

}
