package Sanch;

import javax.swing.*;
import java.awt.*;

public class title extends JPanel {

JLabel LabelBrand;

    public title() {
        this.LabelBrand = new JLabel();
        setLayout(new BorderLayout());
        initializeTitleUI();
    }

    private void initializeTitleUI() {
        JPanel TITLE1 = new JPanel();
        LabelBrand.setText("SKYVOYAGE");
        LabelBrand.setFont(new Font("Arial", Font.BOLD, 35));
        LabelBrand.setForeground(new Color(10, 7, 48));
        TITLE1.setBackground(new Color(255, 255, 255));

        TITLE1.setBorder(BorderFactory.createEmptyBorder(50, 10, 15, 10));
        TITLE1.add(LabelBrand);

        add(TITLE1, BorderLayout.NORTH);
    }






}
