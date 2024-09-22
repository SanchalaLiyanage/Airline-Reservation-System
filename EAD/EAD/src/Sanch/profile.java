package Sanch;

import DATABASE.DbQuerys;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import Interface.*;

import Interface.Logging;


public class profile extends JFrame {

    private JPanel titleBar,menueBar,panelProfile,panelbutton ;


    public profile() throws HeadlessException {
        //this("Contact List Application");
    }

    public profile(String UID) throws HeadlessException {
       // super(title);
        titleBar = new title();
        menueBar= new menue(UID);
        setBackground(Color.WHITE);




        //add panelprofile
        panelProfile = new JPanel();

        //add lables and textfields
        JLabel lblNAME,lblEMAIL,lb,lblCOUNTRY,lblpassport,lblgender,lblADDRESS;
        JTextField txtNAME,txtEMAIL,txtCOUNTRY,txtpassport,txtADDRESS;
        JRadioButton r1,r2;

        lblNAME = new JLabel("NAME");
        lblEMAIL = new JLabel("EMAIL");
        lblCOUNTRY = new JLabel("COUNTRY");
        lblpassport = new JLabel("PASSPORT");
        lblgender = new JLabel("GENGER");
        lblADDRESS = new JLabel("ADDRESS");


        txtNAME = new JTextField(20);
        txtEMAIL = new JTextField(20);
        txtCOUNTRY = new JTextField(20);
        txtpassport = new JTextField(20);
        r1 = new JRadioButton("Male");
        r2 = new JRadioButton("Female");
        txtADDRESS = new JTextField(20);


        //add gridbag layout to panelprofile
        panelProfile.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 1, 15, 80);


        //add lables and textfields to panelprofile
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 0;

        panelProfile.add(lblNAME, gbc);
        gbc.gridy = 1;
        panelProfile.add(lblADDRESS, gbc);
        gbc.gridy = 2;
        panelProfile.add(lblCOUNTRY, gbc);
        gbc.gridy = 3;
        panelProfile.add(lblpassport, gbc);
        gbc.gridy = 4;
        panelProfile.add(lblgender, gbc);
        gbc.gridy = 6;
        panelProfile.add(lblEMAIL, gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 1;
        gbc.gridy = 0;
        panelProfile.add(txtNAME, gbc);
        gbc.gridy = 1;
        panelProfile.add(txtADDRESS, gbc);
        gbc.gridy = 2;
        panelProfile.add(txtCOUNTRY, gbc);
        gbc.gridy = 3;
        panelProfile.add(txtpassport, gbc);
        gbc.gridy = 4;
        panelProfile.add(r1, gbc);
        gbc.gridy = 5;
        panelProfile.add(r2, gbc);
        gbc.gridy = 6;
        panelProfile.add(txtEMAIL, gbc);


        //ADD BUTTON UPDATEAND DELETE
        JButton btnUpdate = new JButton("UPDATE");
        JButton btnLOGOUT = new JButton("LOGOUT");

        panelbutton = new JPanel();
        panelbutton.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 20));
        //ADD PADDING TO PANELBUTTON
       // panelbutton.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 10));
        customizeButton(btnUpdate);
        customizeButton(btnLOGOUT);
        panelbutton.add(btnLOGOUT);
        panelbutton.add(btnUpdate);





        //set background color
        Color c1 = new Color(31, 46, 80);
        panelProfile.setBackground(c1);
        panelbutton.setBackground(c1);

        //add white color to lablesand textfields
        lblNAME.setForeground(Color.WHITE);
        lblEMAIL.setForeground(Color.WHITE);
        lblCOUNTRY.setForeground(Color.WHITE);
        lblpassport.setForeground(Color.WHITE);
        lblgender.setForeground(Color.WHITE);
        lblADDRESS.setForeground(Color.WHITE);
        txtNAME.setBackground(Color.WHITE);
        txtEMAIL.setBackground(Color.WHITE);
        txtCOUNTRY.setBackground(Color.WHITE);
        txtpassport.setBackground(Color.WHITE);
        r1.setBackground(Color.WHITE);
        r2.setBackground(Color.WHITE);
        txtADDRESS.setBackground(Color.WHITE);

        // show details

        String[] details = showDetails(UID);
        txtNAME.setText(details[0]);
        txtEMAIL.setText(details[1]);
        txtCOUNTRY.setText(details[3]);
        txtpassport.setText(details[6]);
        txtADDRESS.setText(details[4]);
        if(details[5]=="Male")
        {
            r1.setSelected(true);
        }
        else
        {
            r2.setSelected(true);
        }





        IntializeUI();



        //action listener for update button
        btnUpdate.addActionListener(e -> {
            String UNAME,UEMAIL,UCOUNTRY,UPASSPORT,ADDRESS,GENDER;

            UNAME=txtNAME.getText();
            UEMAIL= txtEMAIL.getText();
            UCOUNTRY=txtCOUNTRY.getText();
            UPASSPORT=txtpassport.getText();
            ADDRESS=txtADDRESS.getText();
            if(r1.isSelected())
            {
                GENDER="Male";
            }
            else
            {
                GENDER="Female";
            }

            String state;
            try {
                state = new DbQuerys().UPDATEPROFILE(UID,UNAME,UEMAIL,UCOUNTRY,UPASSPORT,ADDRESS,GENDER);

                if (state.equals("success")) {
                    JOptionPane.showMessageDialog(null, "Profile Updated Successfully");
                    new profile(UID);
                    this.dispose();
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Profile Not Updated");
                }



            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }


        });



        //action listener for logout button
        btnLOGOUT.addActionListener(e -> {
            new Logging();
            this.dispose();
        });




        setSize(1080, 720);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);









    }


    private String[] showDetails(String UID) {
        try {
            String state[] =  new DbQuerys().SHOWDETAILS(UID);
            return state;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }




//add title and menu
    private void IntializeUI() {

        //CREATE CONTAINER

        Container container = getContentPane();
        container.setLayout(new BorderLayout());



// ADD  TITLE PANEL TO CONTAINER
        container.add(titleBar,BorderLayout.NORTH);

        //ADD MENUE PANEL TO CONTAINER
        container.add(menueBar,BorderLayout.WEST);

        //ADD PROFILE PANEL TO CONTAINER
        container.add(panelProfile,BorderLayout.CENTER);

        //ADD BUTTON PANEL TO CONTAINER
        container.add(panelbutton,BorderLayout.SOUTH);




        //SET SIZE AND VISIBILITY
        setSize(1080,720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);


    }

    private void customizeButton(JButton button) {
        // Set custom font
        button.setFont(new Font("Arial", Font.BOLD, 16));

        // Set custom foreground color
        button.setForeground(Color.white);
        button.setBackground(Color.GRAY);

        // Set border with rounded corners
        int borderRadius = 20;
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 2),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        button.setBorder(new RoundedCornerBorder(Color.BLACK, borderRadius));

        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.LIGHT_GRAY);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.WHITE);
            }
        });

        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    //create frame
    public static void main(String argvs[])
    {
        new profile();
    }


}
