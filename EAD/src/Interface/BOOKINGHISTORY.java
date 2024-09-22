package Interface;
 import DATABASE.*;
    import Sanch.*;
    import java.awt.*;
    import javax.swing.*;

public class BOOKINGHISTORY extends JFrame {
  //  JPanel panelmain,panelmenue,paneltitel,panelbutton;

    private JPanel titleBar, panelhistory, panelmenue,panelmain;
    public  BOOKINGHISTORY() throws HeadlessException {
        this("Contact List Application");
    }

    public  BOOKINGHISTORY(String USER_ID) throws HeadlessException {
        //super(title);
       // USER_ID="1";
        titleBar = new title();
        panelhistory = new historytable( USER_ID);
        panelmenue=new menue(USER_ID);

        Color c1 = new Color(0, 33, 115);
       // panelimg = new img();
        panelmain = new JPanel();
        panelmain.setLayout(new BorderLayout());
        panelmain.setBackground(c1);
        panelmain.add(panelhistory, BorderLayout.CENTER);


        // setBackground(Color.BLACK);

        panelhistory.setBackground(c1);
        add(titleBar, BorderLayout.NORTH);
        add(panelmain, BorderLayout.CENTER);
        add(panelmenue, BorderLayout.WEST);

        // add(panelimg, BorderLayout.WEST);


        setTitle("QUESTIONS");
        setSize(1080, 720);
        //setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);


    }


    public static void main(String[] args) {
        new  BOOKINGHISTORY();

    }

}
