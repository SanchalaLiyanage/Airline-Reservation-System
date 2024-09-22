package Sanch;

import DATABASE.DatabaseOperationsContacts;
import DATABASE.DbQuerys;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import ADMIN.*;


public class tableAD extends JPanel {

    private JTable table;
    private JPanel panelbutton;
   // private JLabel  ;
    private PreparedStatement pStatement;

    private Connection connection;
    public tableAD() {


        setLayout(new BorderLayout());
        // Create a DefaultTableModel
        DefaultTableModel model = new DefaultTableModel();

        // Set column names
        model.setColumnIdentifiers(new Object[]{"F_ID"," F_Name", "F_AIRLINE", "ARRIVAL","ARRIVAL TIME","DEPARTURE","DEPARTURE TIME"});

        // Retrieve data from the database
        fetchDataFromDatabase(model);

        // Create the JTable with the DefaultTableModel
        table = new JTable(model);
        // Add the table to a JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);



        //--------------------------------------------------------------------button and combobox------------------------------------------------



        panelbutton = new JPanel();
        panelbutton.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 10));



        JButton button1 = new JButton("Add NEW JOURNEY");
        JButton button2 = new JButton("Delete");


        //ADD PADDINGS BUTTONS
        button1.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        button2.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        //CHENGE THE FONT SIZE
        button1.setFont(new Font("Arial", Font.BOLD, 15));
        button2.setFont(new Font("Arial", Font.BOLD, 15));








        panelbutton.add(button1);
        panelbutton.add(button2);

        //ADD PADDINGS

        panelbutton.setBorder(BorderFactory.createEmptyBorder(68, 10, 100, 10));




        //--------------------------------------------------------------------button action------------------------------------------------

        //button 1 action

        button1.addActionListener(e -> {
            new ADD();
        });








        //button 2 action
        button2.addActionListener(e -> {




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
                System.out.println("Selected Flight Number: " + flightno);
                try {
                    DatabaseOperationsContacts db = new DatabaseOperationsContacts();
                    this.connection = db.connect();
                    System.out.println("Connected to the database");
                } catch (SQLException e1) {
                    throw new RuntimeException(e1);
                }
                try {
                    String query1= "DELETE FROM Seat WHERE F_ID = ?";
                    String query2 = "DELETE FROM Flight WHERE F_ID = ?";
                    pStatement = connection.prepareStatement(query1);
                    pStatement.setString(1, flightno);
                    pStatement.executeUpdate();
                    pStatement = connection.prepareStatement(query2);
                    pStatement.setString(1, flightno);
                    pStatement.executeUpdate();
                    System.out.println("got the statement");
                    JOptionPane.showMessageDialog(this, "Flight deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    // Close the resources

                    pStatement.close(); // Close PreparedStatement
                    connection.close();
                    new flight();


                    connection.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
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
    private void fetchDataFromDatabase(DefaultTableModel model) {


        try {
            DatabaseOperationsContacts db = new DatabaseOperationsContacts();
            this.connection = db.connect();
            System.out.println("Connected to the database");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            String query = "SELECT * FROM Flight ";
            PreparedStatement pStatement = connection.prepareStatement(query);

            ResultSet rs = pStatement.executeQuery();
            System.out.println("Statement created successfully");

            // Assuming 'model' is an instance of DefaultTableModel or similar
            while (rs.next()) {
                String ID, NAME, F_AIRLINE, ARRIVAL_TIME, DEPARTURE_TIME,ARRIVAL, DEPARTURE;
                ID = rs.getString("F_ID");
                NAME = rs.getString("F_Name");
                F_AIRLINE = rs.getString("F_AIRLINE");
                ARRIVAL = rs.getString("ARRIVAL");
                ARRIVAL_TIME = rs.getString("ARRIVALtime");
                DEPARTURE = rs.getString("DEPARTURE");
                DEPARTURE_TIME = rs.getString("DEPARTUREtime");
                model.addRow(new Object[]{ID, NAME, F_AIRLINE, ARRIVAL, ARRIVAL_TIME, DEPARTURE,DEPARTURE_TIME});
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
