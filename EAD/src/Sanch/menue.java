package Sanch;

import javax.swing.*;
import java.awt.*;
import Sanch.*;
import Interface.*;


public class menue extends JPanel {




    JPanel panelMenue;

    // constructor
   public menue(String C_ID) {

       //add panel

       this.panelMenue = new JPanel();
       setLayout(new BorderLayout());
       initializeTitleUI(C_ID);
   }



    private void initializeTitleUI(String C_ID) {
        //panelMenue

        this.add(panelMenue, BorderLayout.WEST);
        panelMenue.setLayout(new GridLayout(5, 1));
        panelMenue.setBorder(BorderFactory.createEmptyBorder(50, 10, 10, 10));
        //add SMALL IMAGE TOP OF PANEL
//        JLabel image = new JLabel(new ImageIcon("src/Sanchi/bg.jpg"));
//        image.setSize(10, 10);
        JButton btn1 = new JButton("HOME");
        JButton btn2 = new JButton("BOOKING");
        JButton btn3 = new JButton("VIEW HISTORY");
        JButton btn4 = new JButton("LOGOUT");



//        panelMenue.add(image);
        panelMenue.add(btn1);
        panelMenue.add(btn2);
        panelMenue.add(btn3);
        panelMenue.add(btn4);

        Color c1 = new Color(10, 7, 48);
        panelMenue.setBackground(c1);


        btn1.setBackground(c1);
        btn1.setForeground(Color.WHITE);
        btn2.setBackground(c1);
        btn2.setForeground(Color.lightGray);
        btn3.setBackground(c1);
        btn3.setForeground(Color.WHITE);
        btn4.setBackground(c1);
        btn4.setForeground(Color.lightGray);


        // add action listener to buttons
        btn1.addActionListener(e -> {

            System.out.println("Home button clicked");
            new profile(C_ID);


        });
        btn2.addActionListener(e -> {
            System.out.println("Booking button clicked");
            new booking(C_ID);


        });
        btn3.addActionListener(e -> {
            System.out.println("View History button clicked");
            new BOOKINGHISTORY(C_ID);
        });
        btn4.addActionListener(e -> {
            new Logging();
        });














//
//       setSize(1080, 720);
//       setVisible(true);
//       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }







//    // main method
//    public static void main(String argvs[])
//    {
////create frame
//
//        new menue();
//
//
//    }

}
