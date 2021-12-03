package Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

/***
 * Gym Booking System Mini-Project created on 15/11/2021
 * @author Steven Oprisan
 * @version 1.0
 */

public class Login extends JFrame implements ActionListener {

    private static JLabel usernameLabel;
    private static JTextField usernameText;
    private static JLabel passwordLabel;
    private static JPasswordField passwordText;
    private static JButton button;
    private static JLabel loggedIn;

    public static void main(String[] args) {

        /**
         * Adding JPanel, displaying the window
         */

        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        panel.add(Box.createVerticalStrut(15));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JFrame frame = new JFrame();
        frame.add(panel);
        frame.setTitle("Login Page");
        frame.setLocation(800,300);
        frame.setLocationRelativeTo(null);
        frame.setSize(400,250);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setFocusable(false);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel.setLayout(null);

        usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(50,30,80,30);
        panel.add(usernameLabel);

        usernameText = new JTextField(20);
        usernameText.setBounds(150,30,180,30);
        panel.add(usernameText);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(50,80,80,30);
        panel.add(passwordLabel);

        passwordText = new JPasswordField();
        passwordText.setBounds(150,80,180,30);
        panel.add(passwordText);

        button = new JButton("Login");
        button.setBounds(50,130,80,30);
        button.addActionListener(new Login());
        panel.add(button);

        loggedIn = new JLabel("");
        loggedIn.setBounds(10,180,250,30);
        panel.add(loggedIn);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String user = usernameText.getText();
        String password = passwordText.getText();

        /*****************************************************
         * Title: Calling a JFrame from Another JFrame in Java Swing
         * Author: Telusko
         * Site owner/sponsor: youtube.com
         * Date: 2015
         * Code version: edited 14 Mar 2015
         * Availability: https://www.youtube.com/watch?v=Yw0p7tTFAoc
         * Modified: Open GymApp (main) GUI after signing in successfully
         * *****************************************************/

        if (user.equals("admin") && password.equals("admin")) {
            JOptionPane.showMessageDialog(null, "Logged in as Admin successfully! ",
                    "Logged In", JOptionPane.INFORMATION_MESSAGE);

            dispose();
            GymApp gymapp = new GymApp();
            gymapp.setVisible(true);

        }


        else if (user.equals("member") && password.equals("member")) {
            JOptionPane.showMessageDialog(null, "Logged in as Member successfully! ",
                    "Logged In", JOptionPane.INFORMATION_MESSAGE);

            dispose();
            GymApp gymapp = new GymApp();
            gymapp.setVisible(true);
        }


        else
            JOptionPane.showMessageDialog(null, "Not logged in... ",
                    "Error Message", JOptionPane.ERROR_MESSAGE);

        setVisible(false);
        System.exit(0);
    }
}
