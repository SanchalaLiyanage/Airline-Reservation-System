package Interface;
import DATABASE.DbQuerys;
import Sanch.menue;
import Sanch.table;
import Sanch.title;
import Sanch.menue;

import java.awt.*;
import java.sql.SQLException;
import javax.swing.*;

public class booking extends JFrame{


       private JPanel titleBar,menueBar,panelbooking,panelbutton,paneltop,paneltable ;
    private JTable table;


    public booking() throws HeadlessException {
          // this("Contact List Application");
       }

    public booking(String C_ID) throws HeadlessException {
         //  super(title);
           titleBar = new title();
           menueBar= new menue(C_ID);
           //paneltable = new table();




           //add panelbooking
                panelbooking = new JPanel();
                paneltop = new JPanel();
                paneltable = new JPanel();
                panelbutton = new JPanel();
        panelbooking.setLayout(new BorderLayout());
        panelbooking.add(paneltop,BorderLayout.NORTH);


        //add lables and 2comboboxes as form
        JLabel lblFROM,lblTO;
        JComboBox cmbFROM,cmbTO;
        JButton btnSEARCH;

        lblFROM = new JLabel("FROM");
        lblTO = new JLabel("TO");
        cmbFROM = new JComboBox();
        cmbTO = new JComboBox();
        btnSEARCH = new JButton("SEARCH");

        //difine the size of combobox
        cmbFROM.setPreferredSize(new Dimension(200,25));
        cmbTO.setPreferredSize(new Dimension(200,25));

        //ADD ITEMS TO COMBOBOX AS ARRY
        String items[] = new String[10];
        try {
            items = new DbQuerys().loaddata();
            for (int i = 0; i < items.length; i++) {
                cmbFROM.addItem(items[i]);
                cmbTO.addItem(items[i]);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }






        //add all the components to gridbag layout
        paneltop.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 150, 15, 150);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 0;

        paneltop.add(lblFROM, gbc);
        gbc.gridy = 1;
        paneltop.add(lblTO, gbc);

        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 1;
        gbc.gridy = 0;

        paneltop.add(cmbFROM, gbc);
        gbc.gridy = 1;
        paneltop.add(cmbTO, gbc);

        gbc.anchor=GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 2;
        paneltop.add(btnSEARCH, gbc);











              IntializeUI();

              //ADD ACTION LISTENER TO BUTTON
                btnSEARCH.addActionListener(e -> {

                    //VALIDATION
                    if(cmbFROM.getSelectedItem().equals(cmbTO.getSelectedItem()))
                    {
                        JOptionPane.showMessageDialog(null,"FROM and TO cannot be same");
                    }
                    else {
                        String FROM = (String) cmbFROM.getSelectedItem();
                        String TO = (String) cmbTO.getSelectedItem();


                        paneltable=new table(C_ID,FROM,TO);

                        panelbooking.add(paneltable,BorderLayout.CENTER);
                        panelbooking.revalidate();
                    }

                });














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
        container.add(panelbooking,BorderLayout.CENTER);

        //ADD TOP PANEL TO PANELBOOKING
        panelbooking.add(paneltop,BorderLayout.NORTH);

        //ADD TABLE PANEL TO PANELBOOKING
       // JPanel paneltable1 = new TEST.table();








        //SET SIZE AND VISIBILITY
        setSize(1080,720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

    }

    public static void main(String argvs[])
    {
//create frame

        new booking();
    }
}
