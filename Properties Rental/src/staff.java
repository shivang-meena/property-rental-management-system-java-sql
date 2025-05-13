
public class staff {
    static String M_id,land_lord_id;
    public static void main(String[] args) {
        welocme_S();
    }
    public static void welocme_S(){
        int attempt[]={0};
      while (true) { 
        String menu = """
                      =========================================
                      |        Maintenance Staff Menu         |
                      =========================================
                      |  1. Login as Maintenance Staff        |
                      |  2. Back                              |
                      =========================================
                      |  Please select an option (1-2):       |
                      =========================================""";
        String option=InputHelper.getString(menu+"\n",attempt);
       
        attempt[0]++;
        if (option==null) {
            System.out.println("this cannot be null. Please enter a valid option.");
            System.out.println("------------------------------------------------------------\n");
            
            welocme_S();
        }
        if (option.equalsIgnoreCase("exit")) {
            System.out.println("Exiting  process. Goodbye!");
            System.out.println("------------------------------------------------------------\n");
            App.Appp();
           
        }
        if (option.matches("^\\d+$")) {
            switch (option) {
                case "1":
                    login_staff();
                    break;
                case "2":
                    App.Appp();
                    break;
                default:
                    System.out.println("not valid ");
                    
                    break;
            }
        } else {
            System.out.println("not valid ");
            
            
        }
      }
    }

    public static void login_staff() {
   stafflogin.staff_chek_login();
         
        
    }

    public static void staff_option(String M_id,String land_lord_id) {
        M_id=M_id;
        land_lord_id=land_lord_id;
        int attempt[] = {0};


        while (true) {
            String menu = """
                          =========================================
                          |      Maintenance Staff Options        |
                          =========================================
                          |  1. View Assigned Maintenance Tasks   |
                          |  2. Update Maintenance Task Status    |
                          |  3. Mark Task as Complete             |
                          |  4. Communicate with Tenant and Landlord |
                          |  5. Logout                            |
                          =========================================
                          |  Please select an option (1-5):       |
                          =========================================""";
            String option=InputHelper.getString(menu+"\n",attempt);
           attempt[0]++;
            if (option==null) {
                System.out.println("this cannot be null. Please enter a valid option.");
                System.out.println("------------------------------------------------------------\n");
                
               staff_option(M_id, land_lord_id);
            }
            switch ( option) {
                case "1":
                    stafflogin.assign_reqest_staff(M_id, land_lord_id);
                    attempt[0] = 0;
                    main_menue();
                    break;
                    case"2":
                    stafflogin.update_task_status_detail(M_id, land_lord_id);
                    main_menue();
                    attempt[0] = 0;
                    break;
                    case"3":

                    stafflogin.task_mark_complete(M_id, land_lord_id);
                    main_menue();
                    attempt[0] = 0;
                    break;
                    case"4":
                    stafflogin.communication(M_id, land_lord_id);
                    main_menue();
                    attempt[0] = 0;
                    case"5":
                    App.Appp();
                    break;
                default:
                    System.out.println("Choose A valid Option");
                    
            }
}
    }
    public static void main_menue(){
        int attempt[]={0};
     
       while (true) { 
        String menu = """
                      =========================================
                      |      Maintenance Staff Options        |
                      =========================================
                      |  1. Back                              |
                      |  2. Logout                            |
                      =========================================
                      |  Please select an option (1-2):       |
                      =========================================""";
        String option=InputHelper.getString(menu+"\n",attempt);
        attempt[0]++;
        if (option.matches("^\\d+$")) {
          
          switch (option) {
            case "1":
            staff_option(M_id,land_lord_id);
              break;
          case "2":
          App.Appp();
            default:
            System.out.println("Not valid option");
              break;
          }
        } else {
        System.out.println("Not valid option");
        
        }
       }
    }
}