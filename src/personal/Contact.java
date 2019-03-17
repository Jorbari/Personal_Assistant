package personal;

import java.sql.*;
import java.util.*;


public class Contact {

    Scanner sc = new Scanner(System.in);

    protected Contact() {
        choice();
    }

    /*
    
    Name, address, phone_number and email_address
    
    
     */
    protected final void choice() {
        System.out.println("Enter one of the submenu below: \n ");
        System.out.println("1:      Add new record");
        System.out.println("2:      Delete record");
        System.out.println("3:      Edit record");
        System.out.println("4:      Search record");
        
        
        int option;
        option = sc.nextInt();

        switch (option) {
            case 1:
                Add_new_record();
                break;
            case 2:
                Delete_record();
                break;
            case 3:
                Edit_record();
                break;
            case 4:
                Search_record();
                break;
            default:
                System.out.println("Choose an option to continue");
                choice();
        }
    }

    Connection con;
    Statement st;
    ResultSet rs;
    PreparedStatement ps;
    String user = "root";
    String password = "";

    void Add_new_record() {

        try {

            System.out.println("Add new record \n");

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/contact", user, password);

            System.out.println("Input name");
            String name = sc.next();

            System.out.println("Input address");
            String address = sc.next();

            System.out.println("Input email");
            String email = sc.next();

            System.out.println("Input Phone_number");
            String number = sc.next();

            String add = "INSERT INTO record(name,address,number,email) VALUES(?,?,?,?)";
            ps = con.prepareStatement(add);

//            Adding to the table
            ps.setString(1, name);
            ps.setString(2, address);
            ps.setString(3, number);
            ps.setString(4, email);
//            Executing the query
            int execute = ps.executeUpdate();

            System.out.println("\n" + execute + " new record successfully added \n");

        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }

        System.out.println("Would you like to continue Y/N Press 'C' to exit ");
        String user = sc.next();
        if (user.equalsIgnoreCase("y")) {
            Add_new_record();
        } else if (user.equalsIgnoreCase("c")) {
            System.exit(0);
        } else {
            choice();
        }
    }

    void Delete_record() {

        try {

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/contact", user, password);

            st = con.createStatement();
            rs = st.executeQuery("select * from record");

            System.out.println("List of records \n");
            System.out.println("Select the record to delete by inserting the id number of the record");
            while (rs.next()) {
                System.out.println("Id: " + rs.getInt(1) + "\t" + "Name: " + rs.getString(2) + "\t"
                        + "Phone_number: " + rs.getString(4) + "\t             " + "Address: " + rs.getString(3) + "\t            " + "Email: " + rs.getString(5));
            }

            int number = sc.nextInt();

            String url = "delete from record where id=" + number;
            int delete = st.executeUpdate(url);

            System.out.println(delete + " record successfully deleted");

        } catch (Exception e) {
            System.out.println(e);
        }
        
        System.out.println("Would you like to continue Y/N Press 'C' to exit ");
        String user = sc.next();
        if (user.equalsIgnoreCase("y")) {
            Delete_record();
        } else if (user.equalsIgnoreCase("c")) {
            System.exit(0);
        } else {
            choice();
        }

    }

    void Edit_record() {

        try {

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/contact", user, password);

            st = con.createStatement();
            rs = st.executeQuery("select * from record");

            System.out.println("List of records \n");
            System.out.println("Select the record to edit by inserting the id number of the record");
            while (rs.next()) {
                System.out.println("Id: " + rs.getInt(1) + "\t" + "Name: " + rs.getString(2) + "\t"
                        + "Phone_number: " + rs.getString(4) + "\t             " + "Address: " + rs.getString(3) + "\t            " + "Email: " + rs.getString(5));
            }

//            values to hold the query 
            int origin, num;

//            Variables representing each number in the switch case below
            String target = "";

            System.out.println("Insert the ID number");
            origin = sc.nextInt();

            System.out.println("Choose the item you want to update/edit");
            System.out.println("Name: 2");
            System.out.println("Address: 3");
            System.out.println("Number: 4");
            System.out.println("Email: 5");
            num = sc.nextInt();

//            Creating a switch statement to navigate to the target
            switch (num) {
                case 2:
                    target = "name";
                    break;
                case 3:
                    target = "address";
                    break;
                case 4:
                    target = "number";
                    break;
                case 5:
                    target = "email";
                    break;
                default:
                    System.out.println("You didnt pick a number");
                    Edit_record();

            }

            System.out.println("Enter the new edit");

                String msg = sc.next();

//            Creating a preparestatement to execute the query
                ps = con.prepareStatement("UPDATE record SET " + target + " = ? WHERE id = ?");

//            mapping the values to the preparestatement
                ps.setString(1, msg);
                ps.setInt(2, origin);

                int edit = ps.executeUpdate();
                System.out.println(edit + " record edited");
//            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
        
        System.out.println("Would you like to continue Y/N Press 'C' to exit ");
        String user = sc.next();
        if (user.equalsIgnoreCase("y")) {
            Edit_record();
        } else if (user.equalsIgnoreCase("c")) {
            System.exit(0);
        } else {
            choice();
        }

    }

    void Search_record() {

        System.out.println("Enter the ID number 0f the record");
        int id = sc.nextInt();

        try {
            int count=0;
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/contact", user, password);

            st = con.createStatement();
            rs = st.executeQuery("select * from record where id = " + id);

            
            while (rs.next()) {
                count++;
                System.out.println("records found " + count + "\n");
                System.out.println("Id: " + rs.getInt(1) + "\t" + "Name: " + rs.getString(2) + "\t"
                        + "Phone_number: " + rs.getString(4) + "\t             " + "Address: " + rs.getString(3) + "\t            " + "Email: " + rs.getString(5));
            }
            if(count == 0){
                System.out.println("0 records found");
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
        
        System.out.println("Would you like to continue Y/N Press 'C' to exit ");
        String user = sc.next();
        if (user.equalsIgnoreCase("y")) {
            Search_record();
        } else if (user.equalsIgnoreCase("c")) {
            System.exit(0);
        } else {
            choice();
        }
    }

}
