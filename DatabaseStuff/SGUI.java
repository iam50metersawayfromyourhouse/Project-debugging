package DatabaseStuff;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.util.Random;


public class SGUI implements ActionListener {
    JPanel panel = new JPanel();
    JFrame frame = new JFrame("Epic GUI");
    JLabel emailLabel = new JLabel("Email");
    JTextField emailField = new JTextField(10);
    JLabel passLabel = new JLabel("Password");
    JPasswordField passField = new JPasswordField(60);
    JLabel CpassLabel = new JLabel("Confirm Password");
    JPasswordField CpassField = new JPasswordField(60);
    JButton sub = new JButton("Submit");
    JLabel logInLabel = new JLabel("Already have an account? Log In!");
    JButton log = new JButton("Log in");
    JLabel success = new JLabel("");
    boolean alreadyExists = false;
    public void Signup(){
        frame.setSize(650, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);

        JLabel signUp = new JLabel("Sign Up");
        signUp.setFont(new Font("Helvetica", Font.PLAIN, 20));
        signUp.setBounds(10, 10, 125, 25);
        panel.add(signUp);

        emailLabel.setBounds(10, 50, 80,25);
        emailLabel.setFont(new Font("Helvetica", Font.PLAIN, 16));
        panel.add(emailLabel);

        emailField.setBounds(100, 50 , 165, 25);
        panel.add(emailField);

        passLabel.setBounds(10, 100, 80,25);
        passLabel.setFont(new Font("Helvetica", Font.PLAIN, 16));
        panel.add(passLabel);

        passField.setBounds(100, 100 , 165, 25);
        panel.add(passField);

        CpassLabel.setBounds(10, 150, 300,25);
        CpassLabel.setFont(new Font("Helvetica", Font.PLAIN, 16));
        panel.add(CpassLabel);

        CpassField.setBounds(150, 150 , 165, 25);
        panel.add(CpassField);

        emailField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (emailField.getText().length() >= 64 ) {
                    e.consume();
                }
            }
        });

        passField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (String.valueOf(passField.getPassword()).length() >= 24) {
                    e.consume();
                }
            }
        });

        sub.setBounds(10, 190, 80, 25);
        sub.addActionListener(this);
        panel.add(sub);

        logInLabel.setBounds(20, 230, 300, 30);
        panel.add(logInLabel);

        log.setBounds(25, 260, 150, 25);
        log.addActionListener(this);
        panel.add(log);

        success.setBounds(110, 190, 500, 30);
        panel.add(success);

        frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sub){
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/epicdatabase", "root", "karlsonManxd123")) {
                Statement stat = connection.createStatement();
                ResultSet rs = stat.executeQuery("SELECT * FROM LOGININFO");
                while (rs.next()){
                    if (emailField.getText().equals(rs.getString("email"))) {
                        alreadyExists = true;
                    }
                }
            }
            // Handle any errors that may have occurred.
            catch (SQLException ex) {
                ex.printStackTrace();
            }
            String[] answers = {"Hmmm maybe you entered a short password (has to be longer than 5)", "Maybe its because you entered an invalid email", "Hmm i have a feeling it might be because someone already used that email", "Hey it might be because you entered an incorrect password at 'Confirm password'"};
            DbPRoj db = new DbPRoj();
            if(emailField.getText().equals("") || String.valueOf(passField.getPassword()).equals("") || !String.valueOf(passField.getPassword()).equals(String.valueOf(CpassField.getPassword())) || alreadyExists || !emailField.getText().contains("@") || String.valueOf(passField.getPassword()).length() < 5) {
                success.setText(answers[new Random().nextInt(answers.length)]);
                success.setForeground(Color.RED);
            } else{
                db.signUp(emailField.getText(), String.valueOf(passField.getPassword()));
                success.setText("Successfully Signed Up!");
                success.setForeground(Color.GREEN);
            }
        }
        else if (e.getSource() == log){
            LGUI lGui = new LGUI();
            lGui.logIn();
            frame.dispose();
        }
    }
//    public static void main(String[] args) {
//        SGUI sGui = new SGUI();
//        sGui.Signup();
//    }
}
