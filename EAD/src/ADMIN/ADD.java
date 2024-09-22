package ADMIN;

import DATABASE.*;
import Sanch.*;
import java.awt.*;
import javax.swing.*;

public class ADD extends JFrame {

    JPanel panelmain,paneltitle,panelSubtitle,Panelgrid,PanelBottom,panelCenter;

    // constructor
    public ADD() {
        panelmain=new JPanel();
        paneltitle=new title();
        panelSubtitle=new JPanel();
        Panelgrid=new JPanel();
        PanelBottom=new JPanel();
        panelCenter=new JPanel();
        JLabel nameLabel;
        JLabel airlineLabel;
        JLabel departureLabel;
        JLabel arrivalLabel;
        JLabel departureTimeLabel;
        JLabel arrivalTimeLabel;

        JTextField nameField,airlineField,departureTimeField ,arrivalTimeField,arrivalField,departureField;


        setTitle("Flight Information");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(7, 2)); // Adjust rows and columns as needed

        nameLabel = new JLabel("Flight Name:");
        airlineLabel = new JLabel("Airline:");
        departureLabel = new JLabel("Departure:");
        arrivalLabel = new JLabel("Arrival:");
        departureTimeLabel = new JLabel("Departure Time:");
        arrivalTimeLabel = new JLabel("Arrival Time:");

        nameLabel.setFont(new Font("Arial", Font.BOLD, 17));
        airlineLabel.setFont(new Font("Arial", Font.BOLD, 17));
        departureLabel.setFont(new Font("Arial", Font.BOLD, 17));
        arrivalLabel.setFont(new Font("Arial", Font.BOLD, 17));
        departureTimeLabel.setFont(new Font("Arial", Font.BOLD, 17));
        arrivalTimeLabel.setFont(new Font("Arial", Font.BOLD, 17));


        nameLabel.setForeground(Color.WHITE);
        airlineLabel.setForeground(Color.WHITE);
        departureLabel.setForeground(Color.WHITE);
        arrivalLabel.setForeground(Color.WHITE);
        departureTimeLabel.setForeground(Color.WHITE);
        arrivalTimeLabel.setForeground(Color.WHITE);


        nameField = new JTextField(20);
        airlineField = new JTextField(20);
        departureField = new JTextField(20);
        arrivalField = new JTextField(20);
        departureTimeField = new JTextField(20);
        arrivalTimeField = new JTextField(20);

        JButton insertButton = new JButton("Insert");
        JButton backButton = new JButton("Back");




        panelmain.setLayout(new BorderLayout());
        panelmain.add(paneltitle,BorderLayout.NORTH);
        panelmain.add(panelCenter,BorderLayout.CENTER);

        panelCenter.setLayout(new BorderLayout());
        panelCenter.add(panelSubtitle,BorderLayout.NORTH);
        panelCenter.add(Panelgrid,BorderLayout.CENTER);
        panelCenter.add(PanelBottom,BorderLayout.SOUTH);

// penel grid

        Panelgrid.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15); // Add some padding

        // Add components to panelRight with constraints

        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 0;

        Panelgrid.add(nameLabel, gbc);
        gbc.gridy = 1;
        Panelgrid.add(airlineLabel, gbc);
        gbc.gridy = 2;
        Panelgrid.add(departureLabel, gbc);
        gbc.gridy = 3;
        Panelgrid.add(departureTimeLabel, gbc);
        gbc.gridy = 4;
        Panelgrid.add(arrivalLabel, gbc);
        gbc.gridy = 5;
        Panelgrid.add(arrivalTimeLabel, gbc);



        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 1;
        gbc.gridy = 0;

        Panelgrid.add(nameField, gbc);
        gbc.gridy = 1;
        Panelgrid.add(airlineField, gbc);
        gbc.gridy = 2;
        Panelgrid.add(departureField, gbc);
        gbc.gridy = 3;
        Panelgrid.add(departureTimeField, gbc);
        gbc.gridy = 4;
        Panelgrid.add(arrivalField, gbc);
        gbc.gridy = 5;
        Panelgrid.add(arrivalTimeField, gbc);


        //panel bottom
        PanelBottom.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 20));
        PanelBottom.add(insertButton);
        PanelBottom.add(backButton);
        //Button design
        //add padding to button
        insertButton.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        insertButton.setBackground(Color.BLACK);
        insertButton.setForeground(Color.WHITE);
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
//        PanelBottom.setBorder(BorderFactory.createEmptyBorder(8, 10, 100, 10));


        //add panel color

        PanelBottom.setBackground(new Color(31, 46, 80));
        Panelgrid.setBackground(new Color(31, 46, 80));
        panelCenter.setBackground(new Color(31, 46, 80));
        panelmain.setBackground(new Color(31, 46, 80));
        paneltitle.setBackground(new Color(31, 46, 80));
        panelSubtitle.setBackground(new Color(31, 46, 80));



        this.setLayout(new BorderLayout());
        this.add(panelmain,BorderLayout.CENTER);
        this.setTitle("FLIGHTS");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);



        //button action


        backButton.addActionListener(e -> {

            new flight();
            this.dispose();
        });













        insertButton.addActionListener(e -> {
            String name = nameField.getText();
            String airline = airlineField.getText();
            String departure = departureField.getText();
            String arrival = arrivalField.getText();
            String departureTime = departureTimeField.getText();
            String arrivalTime = arrivalTimeField.getText();

            if (name.isEmpty() || airline.isEmpty() || departure.isEmpty() || arrival.isEmpty() || departureTime.isEmpty() || arrivalTime.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill all the fields");
            } else {
                try {
                    new DbQuerys().insertFlight(name, airline, departure, arrival, departureTime, arrivalTime);
                    JOptionPane.showMessageDialog(null, "Flight added successfully");
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });



    }


    //main method
    public static void main(String[] args) {
        new ADD();
    }
}
