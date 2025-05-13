
public class App {

    public static void main(String[] args) {
        // print this 
        System.out.println("=================================================");
        System.out.println("|                                               |");
        System.out.println("|       WELCOME TO PROPERTIES RENTAL SYSTEM     |");
        System.out.println("|                                               |");
        System.out.println("=================================================");
        System.out.println("|             Manage Properties Easily          |");
        System.out.println("|     Connect Landlords, Tenants, and Staff     |");
        System.out.println("|                                               |");
        System.out.println("|                                               |");
        System.out.println("=================================================");
        // call this method 
        Appp();
    }

    public static void Appp() {

        int attempte[] = {0};
        while (attempte[0] <= 3) {
          //print this options
            System.out.println("======================================");
            System.out.println("|          MAIN MENU                 |");
            System.out.println("======================================");
            System.out.println("|  1. Landlord                       |");
            System.out.println("|  2. Tenants                        |");
            System.out.println("|  3. Staff                          |");
            System.out.println("|  4. Logout                         |");
            System.out.println("======================================");   
            System.out.println("|  Please select an option (1-4):    |");
            System.out.println("======================================");
            System.out.println("|  Note: You can type 'exit'         |");
            System.out.println("|  anywhere to go back.    x         |");
            System.out.println("======================================");
            //get input from inputhelper class and get string method 
            String option = InputHelper.getString("\n", attempte);

            // ++ the attempts
            attempte[0]++;
            //attempt check are left or not 
            if (attempte[0] == 3) {
                System.out.println("You have reached  the maximum number of attempts. Please try again later.");
                logoutProcess();
                break;

            }
            //switch case for option 
            switch (option) {
                case "1" ->
                //if 1 then call this method  
                    Landlord.welcome_L(option);
                case "2" ->
                //if 2 then call this method 
                    Tanants.welcome_Ta(option);
                case "3" ->
                //if 3 then call this method 
                    staff.welocme_S();
                case "4" -> {
                    // if 4 then call  this method and exit the program
                    logoutProcess();
                    return;
                }
                default -> {
                        // if another option invalid option then execute this
                    System.out.println("Invalid option. Please enter a valid choice (1, 2, 3, or 4).");
                }
            }
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public static void logoutProcess() {
//start logout process
        System.out.println("Logging out... Please wait.");
        System.setProperty("file.encoding", "UTF-8");
        for (int i = 5; i > 0; i--) {
            System.out.print(".");
            try {
                // call sleep method  for wait 
                Thread.sleep(500);

            } catch (InterruptedException e) {
                System.out.println("An error occurred while waiting.");
                e.printStackTrace();
            }
        }

        System.out.print("Logging out now. Goodbye!");
        System.exit(0);

    }

}
