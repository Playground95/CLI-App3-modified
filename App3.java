import java.util.Scanner;

public class App3{

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        
        final String CLEAR = "\033[H\033[2J";
        final String COLOR_BLUE_BOLD = "\033[34;1m";
        final String COLOR_RED_BOLD = "\033[31;1m";
        final String COLOR_GREEN_BOLD = "\033[32;1m";
        final String RESET = "\033[0m";

        final String DASHBOARD = "Welcome To Smart Banking";
        final String CREATE_NEW_ACCOUNT = "Create New Account";
        final String DEPOSITS = "Deposits";
        final String WITHDRAWS = "Withdraws";
        final String TRANSFERS = "Transfers";
        final String CHECK_ACCOUNT_BALANCE = "Check Account Balance";
        final String DELETE_ACCOUNT = "Delete an Existing Account";

        String screen = DASHBOARD;
        String[][] userDetails = new String[0][];

        loop:
        do {
            final String APP_TITLE = String.format("%s%s%s",
                                COLOR_BLUE_BOLD, screen, RESET);

            System.out.println(CLEAR);
            System.out.println("\t" + APP_TITLE + "\n");

            switch(screen){
                case DASHBOARD: 
                    System.out.println("\t[1]. Create New Account");
                    System.out.println("\t[2]. Deposits");
                    System.out.println("\t[3]. Withdraws");
                    System.out.println("\t[4]. Transfers");
                    System.out.println("\t[5]. Check Account Balance");
                    System.out.println("\t[6]. Delete an Existing Account");
                    System.out.println("\t[7]. Exit\n");
                    System.out.print("\tEnter an option to continue: ");
                    int option = SCANNER.nextInt();
                    SCANNER.nextLine();

                    switch (option){
                        case 1: screen = CREATE_NEW_ACCOUNT; break;
                        case 2: screen = DEPOSITS; break;
                        case 3: screen = WITHDRAWS; break;
                        case 4: screen = TRANSFERS; break;
                        case 5: screen = CHECK_ACCOUNT_BALANCE; break;
                        case 6: screen = DELETE_ACCOUNT; break;
                        case 7: System.out.println(CLEAR); System.exit(0);
                        default: continue;
                    }
                    break;

                case CREATE_NEW_ACCOUNT:
                    System.out.printf("\tUser ID: SDB-%05d \n", (userDetails.length + 1));

                    boolean valid;
                    String name;
                    double deposite;
                    do{
                        valid = true;
                        System.out.print("\tEnter User Name: ");
                        name = SCANNER.nextLine().strip();
                        if (name.isBlank()){
                            System.out.printf("\t%sName can't be empty%s\n", COLOR_RED_BOLD, RESET);
                            valid = false;
                            continue;
                        }
                        for (int i = 0; i < name.length(); i++) {
                            if (!(Character.isLetter(name.charAt(i)) || 
                                Character.isSpaceChar(name.charAt(i))) ) {
                                System.out.printf("\t%sInvalid Name%s\n", COLOR_RED_BOLD, RESET);
                                valid = false;
                                break;
                            }
                        }
                    }while(!valid);

                    boolean depositeValidity = true;

                    do{
                        depositeValidity = true;
                        System.out.println();
                        System.out.print("\tEnter initial deposite amount: Rs.");
                        deposite = SCANNER.nextDouble();
                        SCANNER.nextLine();
                    
                        if(deposite<5000){
                            depositeValidity = false;
                            System.out.println();
                            System.out.printf("\t%sMinimum deposite amount should be Rs.5000.00%s\n", COLOR_RED_BOLD, RESET);
                            System.out.print("\tDo you want to re-enter (Y/n)?  ");
                            if (SCANNER.nextLine().strip().toUpperCase().equals("Y")) continue;
                            
                            screen = DASHBOARD;
                            continue loop;

                        }

                    }while(!depositeValidity);

                    String[][] newUserDetails = new String[userDetails.length+1][3];

                    for (int i = 0; i < userDetails.length; i++) {
                        newUserDetails[i]=userDetails[i];
                    }

                    newUserDetails[newUserDetails.length-1][0]= String.format("SDB-%05d \n", (userDetails.length + 1));
                    newUserDetails[newUserDetails.length-1][1]= name;
                    newUserDetails[newUserDetails.length-1][2]= String.valueOf(deposite);

                    userDetails = newUserDetails;

                    System.out.println();
                    //System.out.print("\t" + name + " added sucessfully.\n\tDo you want to add new student (Y/n)? ");
                    System.out.printf("\t%s%s added sucessfully!%s\n\tDo you want to create another Account (Y/n)? ",COLOR_GREEN_BOLD, name, RESET);
                    if (SCANNER.nextLine().strip().toUpperCase().equals("Y")) continue;

                    screen = DASHBOARD;
                    break;
            }

        }while(true);
        
    }
}