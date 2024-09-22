package ADMIN;

import DATABASE.*;
import Interface.Logging;
import Sanch.*;
import java.awt.*;
import javax.swing.*;

public class adminloging extends JFrame {



    JPanel panelmain,panelleft,panelRight,PanelRightTop,PanelRightBottom,PanelRightCenter;

    // constructor
    public adminloging() {


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
        JLabel titleLabel = new JLabel("<html><center><br><br><br>ADMIN LOGIN</center></html>");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 45));
        PanelRightTop.add(titleLabel);







//-----------------------------------------right panel middle part---------------------------------------------------

        JLabel usernameLabel = new JLabel("Email");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 17));
        JTextField usernameField = new JTextField(20); // Set preferred width for text field
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 17));
        JPasswordField passwordField = new JPasswordField(20); // Set preferred width for password field




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
        PanelRightCenter.add(passwordLabel, gbc);
        gbc.gridy = 2;


        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 1;
        gbc.gridy = 0;

        PanelRightCenter.add(usernameField, gbc);
        gbc.gridy = 1;
        PanelRightCenter.add(passwordField, gbc);
        gbc.gridy = 2;




        //-----------------------------------------right panel Bottom part---------------------------------------------------


        PanelRightBottom.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 20));
        JButton loginButton = new JButton("Login");
        JButton signupButton = new JButton("BACK");
        customizeButton(loginButton);
         customizeButton(signupButton);
        PanelRightBottom.add(loginButton);
         PanelRightBottom.add(signupButton);


        //Button design


        //difine frame layout
        this.setLayout(new BorderLayout());

//ADD IMAGE TO LEFT PANEL
        ImageIcon image = new ImageIcon("EAD/EAD/src/Sanch/bg.jpg");
        JLabel label = new JLabel(image);
        panelleft.add(label);







//difine grid layout
        panelmain.setLayout(new GridLayout(1,2));
        panelRight.setLayout(new GridLayout(3,1));



        Color c1 = new Color(0, 33, 115);
        panelleft.setBackground(new Color(31, 46, 80));
        panelRight.setBackground(new Color(31, 46, 80));
        PanelRightTop.setBackground(new Color(31, 46, 80));
        PanelRightBottom.setBackground(new Color(31, 46, 80));
        PanelRightCenter.setBackground(new Color(31, 46, 80));

        //add all lable to white color
        titleLabel.setForeground(Color.WHITE);
        usernameLabel.setForeground(Color.WHITE);
        passwordLabel.setForeground(Color.WHITE);
        usernameField.setBackground(Color.WHITE);
        passwordField.setBackground(Color.WHITE);




//add panel to frame
        this.add(panelmain,BorderLayout.CENTER);

        panelmain.add(panelleft,BorderLayout.WEST);
        panelmain.add(panelRight,BorderLayout.EAST);

        panelRight.add(PanelRightTop,BorderLayout.NORTH);
        panelRight.add(PanelRightCenter,BorderLayout.CENTER);
        panelRight.add(PanelRightBottom,BorderLayout.SOUTH);


        loginButton.addActionListener(e -> {
            System.out.println("Login button clicked");
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (username == null || username.isEmpty() || password == null || password.isEmpty())
            {
                JOptionPane.showMessageDialog(this, "Please enter username", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            else{
                try {
                    String state[] =  new DbQuerys().checkADMINLogin(username, password);
                    String state1 = state[0];
                    String state2 = state[1];

                    if (state1.equals("success")) {
                        System.out.println("Login success GO NEXT PAGE");
                        // new profile(state2);
                        //CLOSE THE LOGIN WINDOW
                        new flight();
                        this.dispose();
                    } else {
                        System.out.println("Login failed");
                        JOptionPane.showMessageDialog(this, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (Exception e1) {
                    e1.printStackTrace();
                }

            }

        });

        loginButton.addActionListener(e -> {
            new flight();
            this.dispose();
        });

        signupButton.addActionListener(e -> {
            new Logging();
            this.dispose();
        });





        setVisible(true);
        setSize(1080, 720);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);




    }




    private void customizeButton(JButton button) {
        // Set custom font
        button.setFont(new Font("Arial", Font.BOLD, 16));

        // Set custom foreground color
        button.setForeground(Color.BLACK);
        button.setBackground(Color.WHITE);

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
//        JFrame frame = new Logging();
//        frame.setSize(1080, 720);
//        frame.setVisible(true);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        new adminloging();
    }
}





