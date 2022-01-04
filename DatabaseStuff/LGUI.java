package DatabaseStuff;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LGUI implements ActionListener {
    JPanel panel = new JPanel();
    JFrame frame = new JFrame("Epic GUI");
    JLabel emailLabel = new JLabel("Email");
    JTextField emailField = new JTextField(10);
    JLabel passLabel = new JLabel("Password");
    JPasswordField passField = new JPasswordField(60);
    JButton sub = new JButton("Submit");
    JLabel signUpLabel = new JLabel("Dont have an account? Sign up!");
    JButton sign = new JButton("Sign up");
    JLabel success = new JLabel("");
    public void logIn(){
        frame.setSize(650, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);

        JLabel log = new JLabel("Log In");
        log.setFont(new Font("Helvetica", Font.PLAIN, 20));
        log.setBounds(10, 10, 80, 25);
        panel.add(log);

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

        sub.setBounds(10, 140, 80, 25);
        sub.addActionListener(this);
        panel.add(sub);

        signUpLabel.setBounds(20, 220, 300, 30);
        panel.add(signUpLabel);

        sign.setBounds(25, 250, 150, 25); // nice
        sign.addActionListener(this);
        panel.add(sign);

        success.setBounds(20, 180, 500, 30);
        panel.add(success);

        frame.setVisible(true);
    }
//    public static void main(String[] args) {
//        LGUI GUI = new LGUI();
//        GUI.logIn();
//    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sub){
            DbPRoj db = new DbPRoj();
            db.logIn(emailField.getText(), String.valueOf(passField.getPassword()));
            if (db.success){
                success.setText("Successfully logged in!");
                success.setForeground(Color.GREEN);
                new _2FAGUI().TwoFactorAuthentication();
                new _2FA().send_email(emailField.getText());
            } else{
                success.setText("Wrong email or password!!");
                success.setForeground(Color.RED);
            }
        } else if (e.getSource() == sign){
            SGUI sGui = new SGUI();
            sGui.Signup();
            frame.dispose();
        }
    }
}



// epic