package Sanch;

import DATABASE.DatabaseOperationsContacts;
import DATABASE.DbQuerys;
import Interface.BOOKINGHISTORY;
import Interface.booking;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class historytable extends JPanel{
    private JTable table;
    private JPanel panelbutton;
    private JLabel lblQNO;

    private Connection connection;

    private PreparedStatement pStatement;

    public historytable( String USERID) {
        Color c1 = new Color(0, 33, 115);
        setLayout(new BorderLayout());
        setBackground(c1);
        // Create a DefaultTableModel
        DefaultTableModel model = new DefaultTableModel();

        // Set column names
        model.setColumnIdentifiers(new Object[]{"FLIGHT NUMBER","FLIGHT NAME", "SEAT NUMBER", "CLASS OF SEAT","AIRLINE","DEPARTURE","ARRIVAL"});

        // Retrieve data from the database
        fetchDataFromDatabase1(model,USERID );

        // Create the JTable with the DefaultTableModel
        table = new JTable(model);
        // Add the table to a JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);

        //--------------------------------------------------------------------button ------------------------------------------------

        panelbutton = new JPanel();
        panelbutton.setLayout(new FlowLayout());

        JButton UPDATE,DELETE,BACK;
        UPDATE = new JButton("UPDATE");
        DELETE = new JButton("DELETE");
        BACK = new JButton("BACK");
        panelbutton.add(UPDATE);
        panelbutton.add(DELETE);
        panelbutton.add(BACK);
        add(panelbutton, BorderLayout.CENTER);
        panelbutton.setBackground(c1);
        //ADD PADDING TO THE BUTTONS
        panelbutton.setFont(new Font("Arial", Font.BOLD, 16));
        UPDATE.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 100));
        DELETE.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 100));
        BACK.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 100));



        CustomizeButton(UPDATE);
        CustomizeButton(DELETE);
        CustomizeButton(BACK);
        //ADD PADDING TO THE PANEL
        panelbutton.setBorder(BorderFactory.createEmptyBorder(150, 50, 100, 50));







        //-------------------------------------------------add components to the frame------------------------------------------------




        this.setBackground(c1);
        //Add the JScrollPane to the frame
        add(scrollPane, BorderLayout.CENTER);
        add(panelbutton, BorderLayout.SOUTH);

        // Display the frame
        setVisible(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);






        //action listener for the buttons
        //UPDATE BUTTON
        UPDATE.addActionListener(e -> {
            System.out.println("UPDATE button clicked");
            validate(USERID,"UPDATE");
        });
        //DELETE BUTTON
        DELETE.addActionListener(e -> {
            System.out.println("DELETE button clicked");
            validate(USERID,"DELETE");
        });
        //BACK BUTTON
        BACK.addActionListener(e -> {
            System.out.println("BACK button clicked");
           new booking(USERID);
        });








    }



    private void CustomizeButton(JButton button) {
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



    private void fetchDataFromDatabase1(DefaultTableModel model, String USER) {

        try {
            DatabaseOperationsContacts db = new DatabaseOperationsContacts();
            this.connection = db.connect();
            System.out.println("Connected to the database");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {

            // Create a statement
            Statement statement = connection.createStatement();
            System.out.println("Statement created successfully");

            // Execute a query to retrieve data
            String query = ("SELECT Flight.*, Seat.*, Seat.C_ID FROM Flight JOIN Seat ON Flight.F_ID = Seat.F_ID WHERE Seat.C_ID = ?");
            pStatement = connection.prepareStatement(query);
            pStatement.setString(1, USER);
            ResultSet rs = pStatement.executeQuery();


            System.out.println("Query executed successfully");

            // Iterate through the result set and add data to the model
            while (rs.next()) {
                String FLIGHTNUMBER,FLIGHTNAME, SEATNUMBER, CLASSOFSEAT,AIRLINE,DEPARTURE,ARRIVAL;
                System.out.println("start" );
                model.addRow(new Object[]{
                        FLIGHTNUMBER = rs.getString("F_ID"),
                        FLIGHTNAME = rs.getString("F_NAME"),
                        SEATNUMBER = rs.getString("S_NO"),
                        CLASSOFSEAT = rs.getString("S_Class"),
                        AIRLINE = rs.getString("F_Airline"),
                        ARRIVAL = rs.getString("ARRIVAL"),

                        DEPARTURE = rs.getString("DEPARTURE"),





                });

                System.out.println("Data added to the model");
            }

            // Close the resources
            rs.close();
            statement.close();

            connection.close();
            System.out.println("Resources closed successfully");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error in fetching data from the database");
        }




    }





    private void validate(String U_ID, String BTN) {
        int row=-1;
        row = table.getSelectedRow();

        if (row == -1)
        {
            JOptionPane.showMessageDialog(null, "Please select Flight Number");
        }
        else
        {
            String F_ID = (String) table.getValueAt(row, 0);
            String S_NO = (String) table.getValueAt(row, 2);

            System.out.println(F_ID);
            //new VOTESHEET(U_ID,QNO);
            if(BTN.equals("UPDATE"))
            {
                try {
                    String state=new DbQuerys().deleteBooking(U_ID,F_ID,S_NO);
                    if(state.equals("success"))
                    {
                        new booking(U_ID);

                       // JOptionPane.showMessageDialog(null, "Booking deleted successfully");
                        //refresh the table
                        // new VOTEHISTORY(U_ID);
                    }

                    else
                    {
                        JOptionPane.showMessageDialog(this, "Booking not delete", "Error", JOptionPane.ERROR_MESSAGE);

                    }

                }
                catch (SQLException e)
                {
                    throw new RuntimeException(e);
                }
            }


           else if(BTN.equals("DELETE"))
            {
                try {
                    String state=new DbQuerys().deleteBooking(U_ID,F_ID,S_NO);
                    System.out.println(state);
                        if(state.equals("success"))
                        {
                            JOptionPane.showMessageDialog(null, "Booking deleted successfully");
                            new BOOKINGHISTORY(U_ID);

                        }

                        else
                        {
                            JOptionPane.showMessageDialog(this, "Booking not delete", "Error", JOptionPane.ERROR_MESSAGE);

                        }

                    }
                    catch (SQLException e)
                    {
                        throw new RuntimeException(e);
                    }
            }

        }
    }


    private void customizeButton(JButton button) {
        // Set custom font
        button.setFont(new Font("Arial", Font.BOLD, 24));

        // Set custom foreground color
        button.setForeground(Color.BLACK);
        button.setBackground(Color.WHITE);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        button.setMargin(new Insets(10, 20, 10, 24));
//        button.setBorder(new RoundedCornerBorder(Color.BLACK, 20));


        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    //main method
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.add(new historytable("1"));
        frame.setTitle("VOTTING SYSTEM");
        frame.setSize(1080, 720);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);


    }
}
