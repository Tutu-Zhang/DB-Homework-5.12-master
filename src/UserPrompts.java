import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.ArrayList;
import java.util.Scanner;

public class UserPrompts {
    private static Scanner scanner;

    public UserPrompts() {
        scanner = new Scanner(System.in);
    }
    public Credentials GetCredentials() {
        return new Credentials(PromptUsername(), PromptPassword());
    }

    private String PromptUsername() {
        String username = "";

        while (username.isEmpty()) {
            System.out.println("Please enter your username:");
            username = scanner.nextLine();

            if (username.isEmpty()) {
                System.out.println("Username was empty. Try again.");
            }
        }

        return username;
    }

    private String PromptPassword() {
        String password = "";

        while (password.isEmpty()) {
            System.out.println("Please enter your password:");
            password = scanner.nextLine();

            if (password.isEmpty()) {
                System.out.println("Password was empty. Try again.");
            }
        }

        User.setAdminPassword(password);
        return password;
    }

    public void Search() {
        System.out.println("Search For Instructors!");
        boolean keepOnSearching = true;
        String input = "";

        while (keepOnSearching) {
            System.out.println("Enter name (-h for help):");
            input = scanner.nextLine();
            keepOnSearching = CheckInput(input);
            ArrayList<Instructor> results = DBHelper.SearchInstructor(input);
            results.forEach(i -> {
                System.out.println(String.format("id: %d, name: %s", i.getId(), i.getName()));
            });

            if(!results.isEmpty()){
                System.out.println("Enter the ID you want");
                String GivenID;
                GivenID = scanner.nextLine();
                keepOnSearching = CheckInput(GivenID);
                ArrayList<Instructor> IDresults = DBHelper.SearchInstructorID(GivenID);
                ArrayList<InstructorInfo> InfoResult = DBHelper.SearchInstructorInfo(GivenID);

                if(IDresults.isEmpty()){
                    System.out.println("No such Instructor");
                }
                else {
                    if (InfoResult.isEmpty()) {
                        System.out.println("This Instructor has taught no course");
                    } else {
                        InfoResult.forEach(i -> {
                            System.out.println(String.format("id: %d, name: %s, department: %s, course_id: %s, title: %s, sec_id: %s, semester: %s, year: %s", i.getId(), i.getName(), i.getDepartName(), i.getCourse_id(), i.getTitle(), i.getSec_id(), i.getSemester(), i.getYear()));
                        });
                        System.out.println(String.format("This instructor has %d courses he/she taught", InfoResult.size()));
                    }
                }
            }
        }
    }

    public boolean CheckInput(String input) {
        if (input.equals("-q") || input.equals("--quit")) {
            return false;
        } else if (input.equals("-h") || input.equals("--help")) {
            ShowHelp();
        }
        return true;
    }

    private void ShowHelp() {
        System.out.println("How to use this program:");
        System.out.println("-h, --help \tBrings you this this menu (obviously).");
        System.out.println("-q, --quit \tExits the program.");
        System.out.println("Searching: Search for a name that contains what you enter.");
        System.out.println("Example: Searching for 'John Smith' you can enter 'john' or 'John' or 'JOHN' or anything that matches.");
        System.out.println("To get all instructors, just leave the field blank and press 'ENTER'");
        System.out.println("Enjoy!!");
    }
}
