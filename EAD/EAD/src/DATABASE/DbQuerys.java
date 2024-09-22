package DATABASE;


import Sanch.menue;
import com.mysql.jdbc.*;
import java.sql.*;

public class DbQuerys extends DatabaseOperationsContacts {
    private PreparedStatement pStatement;

    private Connection connection;
    private ResultSet result;

    public DbQuerys() throws SQLException {
    }


    //----------------------------------------------------Check login---------------------------------------------------
    public String[] checkLogin(String email, String password) throws SQLException {
        this.connection = connect();
        try {
            String query = "SELECT * FROM Customer WHERE C_Email =? AND  C_Password = ?";

            pStatement = connection.prepareStatement(query);
            pStatement.setString(1, email);
            pStatement.setString(2, password);
            result = pStatement.executeQuery();
            System.out.println("result" + result);
            if (result.next()) {
                String C_ID = result.getString("C_ID");
                System.out.println("Login success" + C_ID);

                System.out.println("Login success");

                return new String[]{"succes", C_ID};

            } else {
                System.out.println("Login failed");

                return new String[]{"Login failed", "fff"};
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connection.close();
        return new String[]{"Login failed", "fff"};
    }

    //--------------------------------------------------INSERT CUSTOMER------------------------------------------------
    public String saveUser(String name, String email, String password) throws SQLException {
        this.connection = connect();
        try {
            String query = "INSERT INTO Customer (C_Name, C_Email, C_Password) VALUES (?, ?, ?)";
            pStatement = connection.prepareStatement(query);
            pStatement.setString(1, name);
            pStatement.setString(2, email);
            pStatement.setString(3, password);
            int r1 = pStatement.executeUpdate();

//            if (pStatement.executeUpdate() > 0) {
                System.out.println("User saved successfully");
                return "success";
//            } else {
//                System.out.println("User not saved");
//                return "failed";
//            }

        } catch (SQLException e) {
            e.printStackTrace();
            return "CAN'T CONNECT TO DATABASE";
        }
    }


    //--------------------------------------------------booking------------------------------------------------
    public String Booking(String flight, String classNAME, String sheat, String C_ID) throws SQLException {
        this.connection = connect();
        try {
            String query = "INSERT INTO Seat (F_ID, S_Class, S_NO,C_ID,Avalability) VALUES (?, ?, ?,?,?)";
            pStatement = connection.prepareStatement(query);
            pStatement.setString(1, flight);
            pStatement.setString(2, classNAME);
            pStatement.setString(3, sheat);
            pStatement.setString(4, C_ID);
            pStatement.setString(5, "booked");
            pStatement.executeUpdate();
            return "success";
        } catch (SQLException e) {
            e.printStackTrace();
            return "CAN'T CONNECT TO DATABASE";
        }
    }


    //--------------------------------------------------------GET DATA FOR PROFILE-----------------------------------

    public String[] SHOWDETAILS(String C_ID) throws SQLException {
        this.connection = connect();
        try {
            String state[] = new String[8];

            String query = "SELECT * FROM Customer WHERE C_ID = ?";

            pStatement = connection.prepareStatement(query);
            pStatement.setString(1, C_ID);
            result = pStatement.executeQuery();
            if (result.next()) {
                state[0] = result.getString("C_Name");
                state[1] = result.getString("C_Email");
                state[2] = result.getString("C_Password");
                state[3] = result.getString("C_Country");
                state[4] = result.getString("C_Address");
                state[5] = result.getString("C_Gender");
                state[6] = result.getString("C_Passport");
                state[7] = result.getString("C_ID");
                return state;

            } else {
                System.out.println("Login failed");
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connection.close();
        return null;
    }

    //--------------------------------------------------------UPDATE PROFILE-----------------------------------


    public String UPDATEPROFILE(String C_ID, String C_Name, String C_Email, String C_Country, String C_Passport, String C_Address, String C_Gender) throws SQLException {
        this.connection = connect();
        try {
            System.out.println("try");
            String query = "UPDATE Customer SET C_Name = ?, C_Email = ?, C_Country = ?, C_Passport = ?, C_Address = ?, C_Gender = ? WHERE C_ID = ?";
            pStatement = connection.prepareStatement(query);
            pStatement.setString(1, C_Name);
            pStatement.setString(2, C_Email);
            pStatement.setString(3, C_Country);
            pStatement.setString(4, C_Passport);
            pStatement.setString(5, C_Address);
            pStatement.setString(6, C_Gender);
            System.out.println("try1");
            pStatement.setString(7, C_ID);
            System.out.println("try2");
            pStatement.executeUpdate();
            System.out.println("try3");
            if (pStatement.executeUpdate() > 0) {
                System.out.println("Profile Updated successfully");
                return "success";
            } else {
                System.out.println("Profile not Updated");
                return "failed";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "CAN'T CONNECT TO DATABASE";
        }
    }


    //-----------------------------------------------------history------------------------------------------------


    //delete booking
    public String deleteBooking(String C_ID, String F_ID, String S_NO) throws SQLException {
        this.connection = connect();
        try {
            String query = "UPDATE Seat SET Avalability = NULL, C_ID = NULL WHERE C_ID = ? AND F_ID = ? AND S_NO = ?";


            pStatement = connection.prepareStatement(query);
            pStatement.setString(1, C_ID);
            pStatement.setString(2, F_ID);
            pStatement.setString(3, S_NO);
            pStatement.executeUpdate();
            System.out.println("try");

            if (pStatement.executeUpdate() >= 0) {
                System.out.println("Booking deleted successfully");
                return "success";
            } else {
                System.out.println("Booking not deleted");
                return "failed";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "CAN'T CONNECT TO DATABASE";
        }
    }


    //------------------------------------LOAD DATA FOR COMBOBOX------------------------------------------------

    public String[] loaddata() throws SQLException {
        this.connection = connect();
        try {
            String state[] = new String[25];
            String query = "SELECT * FROM Airport";
            pStatement = connection.prepareStatement(query);
            result = pStatement.executeQuery();
            System.out.println("query executed");
            if (result.next()) {
                int i = 0;
                System.out.println("success");
                while (result.next()) {
                    System.out.println(i);

                    state[i] = result.getString("A_Name");
                    i++;
                }
                return state;
            } else {
                System.out.println("fail");
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}



















