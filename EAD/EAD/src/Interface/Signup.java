package Interface;

import DATABASE.DbQuerys;
import Sanch.menue;

import java.awt.*;
import javax.swing.*;



public class Signup extends JFrame {



    JPanel panelmain,panelleft,panelRight,PanelRightTop,PanelRightBottom,PanelRightCenter;

    // constructor
   public Signup() {


//add panel
        panelmain=new JPanel();
        panelleft=new JPanel();
        panelRight=new JPanel();
        PanelRightTop=new JPanel();
        PanelRightBottom=new JPanel();
        PanelRightCenter=new JPanel();



        //panelreight

        //-----------------------------------------right panel Top part---------------------------------------------------


        // Set layout for panelRightTop
        PanelRightTop.setLayout(new FlowLayout(FlowLayout.CENTER));
        // Add components to panelRightTop
        JLabel titleLabel = new JLabel("<html><center><br><br><br>Register As A User</center></html>");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        PanelRightTop.add(titleLabel);







//-----------------------------------------right panel middle part---------------------------------------------------

        JLabel usernameLabel = new JLabel("Name");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 17));
        JTextField usernameField = new JTextField(20);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(new Font("Arial", Font.BOLD, 17));
        JTextField emailField = new JTextField(20);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 17));
        JPasswordField passwordField = new JPasswordField(20);

        JLabel confirmPasswordLabel = new JLabel("Confirm Password");
        confirmPasswordLabel.setFont(new Font("Arial", Font.BOLD, 17));
        JPasswordField confirmPasswordField = new JPasswordField(20);




        // Use GridBagLayout for panelRight


        PanelRightCenter.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15); // Add some padding

        // Add components to panelRight with constraints

        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 0;

        PanelRightCenter.add(usernameLabel, gbc);
        gbc.gridy = 1;
        PanelRightCenter.add(emailLabel, gbc);
        gbc.gridy = 2;
        PanelRightCenter.add(passwordLabel, gbc);
        gbc.gridy = 3;
        PanelRightCenter.add(confirmPasswordLabel, gbc);
        gbc.gridy = 4;



        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 1;
        gbc.gridy = 0;

        PanelRightCenter.add(usernameField, gbc);
        gbc.gridy = 1;
        PanelRightCenter.add(emailField, gbc);
        gbc.gridy = 3;
        PanelRightCenter.add(passwordField, gbc);
        gbc.gridy = 2;
        PanelRightCenter.add(confirmPasswordField, gbc);
        gbc.gridy = 4;





        //-----------------------------------------right panel Bottom part---------------------------------------------------


        PanelRightBottom.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 20));
        JButton loginButton = new JButton("Login");
        JButton signupButton = new JButton("SignUp");
        customizeButton(loginButton);
        customizeButton(signupButton);
        PanelRightBottom.add(loginButton);
        PanelRightBottom.add(signupButton);


        //Button design


        //difine frame layout
        this.setLayout(new BorderLayout());









//difine grid layout
        panelmain.setLayout(new GridLayout(1,2));
        panelRight.setLayout(new GridLayout(3,1));
//add lay out color
//        panelleft.setBackground(Color.BLACK);
//        panelRight.setBackground(Color.WHITE);
//            PanelRightTop.setBackground(Color.gray);
//            PanelRightBottom.setBackground(Color.green);
//            PanelRightCenter.setBackground(Color.blue);

        Color c1 = new Color(10, 7, 48);
        panelleft.setBackground(c1);
        panelRight.setBackground(Color.WHITE);
        PanelRightTop.setBackground(c1);
        PanelRightBottom.setBackground(c1);
        PanelRightCenter.setBackground(c1);

        //add all lable to white color
        titleLabel.setForeground(Color.WHITE);
        usernameLabel.setForeground(Color.WHITE);
        passwordLabel.setForeground(Color.WHITE);
        usernameField.setBackground(Color.WHITE);
        passwordField.setBackground(Color.WHITE);
        confirmPasswordField.setBackground(Color.WHITE);
        emailField.setBackground(Color.WHITE);
        confirmPasswordLabel.setForeground(Color.WHITE);
        emailLabel.setForeground(Color.WHITE);




//add panel to frame
        this.add(panelmain,BorderLayout.CENTER);

        panelmain.add(panelleft,BorderLayout.WEST);
        panelmain.add(panelRight,BorderLayout.EAST);

        panelRight.add(PanelRightTop,BorderLayout.NORTH);
        panelRight.add(PanelRightCenter,BorderLayout.CENTER);
        panelRight.add(PanelRightBottom,BorderLayout.SOUTH);

        //ADD IMAGE TO LEFT PANEL
        ImageIcon image = new ImageIcon("C:/Users/User/Desktop/ead/EAD/EAD/src/Sanch/bg.jpg");
        JLabel label = new JLabel(image);
        panelleft.add(label);

        //-----------------------------------------------------LOGGING BUTTON-------------------------------------------------------------------------------------

       loginButton.addActionListener(e -> {
           System.out.println("Login button clicked");
            new Logging();
        });



        //----------------------------------------------VALIDATION and SIGNUPBUTTON-----------------------------------------------------------------------------------


        signupButton.addActionListener(e -> {
            String username = usernameField.getText();
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());

            if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty())
            {
                JOptionPane.showMessageDialog(this, "All fields are required", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else if (!password.equals(confirmPassword))
            {
                JOptionPane.showMessageDialog(this, "Passwords do not match", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                // Save to database
                try {
                    // Save to database
                    DbQuerys dbQuerys = new DbQuerys();

                    String state=dbQuerys.saveUser(username, email, password);
                    if (state.equals("success")) {
                        System.out.println("User saved successfully");
                        JOptionPane.showMessageDialog(this, "User saved successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                       // new menue();
                        new Logging();
                        this.dispose();
                    }
                    else {
                        System.out.println("User not saved");
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "An error occurred", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });









        setSize(1080, 720);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }




    private void customizeButton(JButton button) {
        // Set custom font
        button.setFont(new Font("Arial", Font.BOLD, 16));

        // Set custom foreground color
        button.setForeground(Color.BLACK);
        button.setBackground(Color.LIGHT_GRAY);

        // Set padding for the button text
        button.setMargin(new Insets(8, 20, 8, 24));

        // Set rounded border
        int borderRadius = 15;
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(borderRadius, borderRadius, borderRadius, borderRadius)
        ));

        // Add more designs or effects if needed

        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    // main method
    public static void main(String argvs[])
    {
//create frame

        new Signup();
    }
}





