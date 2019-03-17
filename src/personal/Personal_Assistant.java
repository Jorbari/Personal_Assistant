package personal;

import java.sql.*;
import java.util.*;

public class Personal_Assistant {

    Scanner sc = new Scanner(System.in);

    private final void welcome() {

        try {
            String user = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/contact", user, password);

            Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("select * from appointment");

            while (rs.next()) {
                if (rs.isLast()) {
                    System.out.println(" Reminder!!! You have a meeting on: " + rs.getString(2));
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        System.out.println("\nWelcome to Personal Assistant Software");
        System.out.println("Enter an Option to continue \n\n");
        System.out.println("1:      Contact details");
        System.out.println("2:      Meetings/appointments");

        int option = sc.nextInt();

        switch (option) {
            case 1:
                System.out.println("You have selected Contact \n");
                contact();
                break;
            case 2:
                System.out.println("You have Meeting/Appointment  \n");
                meeting();
                break;
            default:
                System.out.println("Pick an option");
                welcome();
        };

    }

    void contact() {
        class transfer extends Contact {
        }
        new transfer();
    }

    void meeting() {
        class transfer extends Appointment {
        }
        new transfer();
    }

    public static void main(String[] args) {
        Personal_Assistant pa = new Personal_Assistant();
        pa.welcome();
    }

}
