package ADMIN;

import javax.swing.*;
import DATABASE.*;
import Sanch.*;
import java.awt.*;


public class flight extends JFrame {
    JPanel panelmain,paneltable,paneltitel,panelbutton;
    public flight(){
        paneltitel=new title();
        paneltable=new tableAD();

        panelmain = new JPanel();
        panelmain.setLayout(new BorderLayout());
        panelmain.add(paneltitel,BorderLayout.NORTH);
        panelmain.add(paneltable,BorderLayout.CENTER);
        this.add(panelmain);
        this.setTitle("FLIGHTS");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);


    }
    //main method
    public static void main(String[] args) {
        new flight();
    }


}
