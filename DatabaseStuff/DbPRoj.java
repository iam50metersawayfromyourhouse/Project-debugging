package DatabaseStuff;
import java.sql.*;
import java.util.Scanner;

public class DbPRoj {
    boolean success = false;
    public void logIn(String emailEnteredByUser, String passwordEnteredByUser){
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/epicdatabase", "root", "karlsonManxd123")) {
            Statement stat = connection.createStatement();
            ResultSet rs = stat.executeQuery("SELECT * FROM LOGININFO");
            while (rs.next()){
                String email = rs.getString("email");
                String password = rs.getString("password");
                if (emailEnteredByUser.equals(email) && passwordEnteredByUser.equals(password)){
                    System.out.println("Successfully logged into account");
                    success = true;
                } else{
                    System.out.println("Wrong email or password");
                }
            }
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void signUp(String userEmail, String userPassword){
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/epicdatabase", "root", "karlsonManxd123")) {
            Statement stat = connection.createStatement();
            stat.executeUpdate("insert into LOGININFO value('"+ userEmail +"', '"+ userPassword +"')");
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws SQLException {
        DbPRoj db = new DbPRoj();
        Scanner userInput = new Scanner(System.in);
        boolean enteredCorrectAnswer = false;
        while (!enteredCorrectAnswer) {
            System.out.println("Login or Sign Up? [l/s] : ");
            String c = userInput.nextLine();
            switch (c) {
                case "l":
                    enteredCorrectAnswer = true;
                    System.out.println("Enter email:");
                    String em = userInput.nextLine();
                    System.out.println("Enter password");
                    String pass = userInput.nextLine();
                    db.logIn(em, pass);
                    break;
                case "s":
                    enteredCorrectAnswer = true;
                    boolean wrongPassword = true;
                    System.out.println("Enter new email address:");
                    String email = userInput.nextLine();
                    System.out.println("Enter password");
                    String password = userInput.nextLine();
                    while (wrongPassword) {
                        System.out.println("Confirm password");
                        String Cpassword = userInput.nextLine();
                        if (Cpassword.equals(password)) {
                            wrongPassword = false;
                        }
                    }
                    db.signUp(email, password);
                    break;
            }
        }
    }
}
