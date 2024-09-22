package Sanch;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import DATABASE.*;


public class table extends JPanel {

    private JTable table;
    private JPanel panelbutton;
    private JLabel  lbclass,lblseatno,lblflightno;
    private PreparedStatement pStatement;

    private  Connection connection;
    public table(String C_ID, String DEPARTURE, String ARRIVAL) {


        setLayout(new BorderLayout());
        // Create a DefaultTableModel
        DefaultTableModel model = new DefaultTableModel();

        // Set column names
        model.setColumnIdentifiers(new Object[]{"F_ID"," F_Name", "F_AIRLINE", "ARRIVAL TIME","DEPARTURE TIME"});

        // Retrieve data from the database
        fetchDataFromDatabase(model, DEPARTURE, ARRIVAL);

        // Create the JTable with the DefaultTableModel
        table = new JTable(model);
        // Add the table to a JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);



        //--------------------------------------------------------------------button and combobox------------------------------------------------



        panelbutton = new JPanel();
        panelbutton.setLayout(new FlowLayout());


        JButton button = new JButton("Book");

        lbclass = new JLabel("Class");
        lblseatno = new JLabel("Seat No");
        lblflightno = new JLabel("Flight No");

        JTextField textflightno = new JTextField(10);

        JComboBox<String> comboBoxSheat = new JComboBox<>();
        String[] items = {"1", "2", "3", "4", "5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20"};
        for (int i = 0; i < items.length; i++) {
            comboBoxSheat.addItem(items[i]);
        }
        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.addItem("Economy");
        comboBox.addItem("Business");

        //add button and combobox to panel
       // panelbutton.add(lblflightno);
       // panelbutton.add(textflightno);
        panelbutton.add(lbclass);
        panelbutton.add(comboBox);

        panelbutton.add(lblseatno);

        panelbutton.add(comboBoxSheat);
        panelbutton.add(button);

        //ADD PADDINGS

        panelbutton.setBorder(BorderFactory.createEmptyBorder(8, 10, 100, 10));




        //--------------------------------------------------------------------button action------------------------------------------------

        button.addActionListener(e -> {

            String seatno = (String) comboBoxSheat.getSelectedItem();
            String classs = (String) comboBox.getSelectedItem();




            //get the selected question number from mouse click from row AND VALIDATE IF ANY ROW IS SELECTED

            String flightno;
            int row=-1;
            row = table.getSelectedRow();

            if (row == -1)
            {
                JOptionPane.showMessageDialog(null, "Please select a Flight Number");
            }
            else
            {
                flightno = (String) table.getValueAt(row, 0);
                System.out.println(flightno);
                // new VOTESHEET(U_ID,QNO);

                if (seatno.equals("") || classs.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please fill all the fields", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        String state = new DbQuerys().Booking(flightno, classs, seatno, C_ID);
                        if (state.equals("success")) {
                            System.out.println("Booking success");
                            JOptionPane.showMessageDialog(this, "Booking success", "Success", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            System.out.println("Booking failed");
                            JOptionPane.showMessageDialog(this, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }

            }

        });




















                //-------------------------------------------------add components to the frame------------------------------------------------



        // Add the JScrollPane to the frame
        add(scrollPane,BorderLayout.CENTER);
        add(panelbutton,BorderLayout.SOUTH);
        // Display the frame
        setVisible(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    }




    // add data to the table
    private void fetchDataFromDatabase(DefaultTableModel model, String DEPARTURE, String ARRIVAL) {
        System.out.println(DEPARTURE+" "+ARRIVAL);

        try {
            DatabaseOperationsContacts db = new DatabaseOperationsContacts();
            this.connection = db.connect();
            System.out.println("Connected to the database");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            String query = "SELECT * FROM Flight WHERE DEPARTURE = ? AND ARRIVAL = ?";
            PreparedStatement pStatement = connection.prepareStatement(query);
            pStatement.setString(1, DEPARTURE);
            pStatement.setString(2, ARRIVAL);
            ResultSet rs = pStatement.executeQuery();
            System.out.println("Statement created successfully");

            // Assuming 'model' is an instance of DefaultTableModel or similar
            while (rs.next()) {
                String ID, NAME, F_AIRLINE, ARRIVAL_TIME, DEPARTURE_TIME;
                ID = rs.getString("F_ID");
                NAME = rs.getString("F_Name");
                F_AIRLINE = rs.getString("F_AIRLINE");
                ARRIVAL_TIME = rs.getString("ARRIVALtime");
                DEPARTURE_TIME = rs.getString("DEPARTUREtime");
                model.addRow(new Object[]{ID, NAME, F_AIRLINE, ARRIVAL_TIME, DEPARTURE_TIME});
                System.out.println("Data added to the model");
            }

            // Close the resources
            rs.close();
            pStatement.close(); // Close PreparedStatement
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }






}
