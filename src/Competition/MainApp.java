package Competition;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) throws FileNotFoundException {
    CompetitorList football = new CompetitorList();
        football.readFile("list.csv");

        Scanner sc = new Scanner(System.in);
        boolean keepEntering = true;

        while (keepEntering) {
            System.out.println("Enter a competitor number: ");
            int cID = sc.nextInt();
            Competitor c = football.findById(cID);
            if (c != null) {
                System.out.println(c.getShortDetails());
            } else {
                System.out.println("Invalid competitor ID, please try again.");
            }

            System.out.println("Do you want to enter another competitor ID? (yes/no)");
            String answer = sc.next();
            if (!answer.equalsIgnoreCase("yes")) {
                keepEntering = false;
            }
        }
        sc.close();
}
}
