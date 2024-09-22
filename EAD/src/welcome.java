import javax.swing.*;
import DATABASE.*;
import Sanch.*;
import java.awt.*;
import ADMIN.*;
import Interface.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class welcome extends JFrame {
    JPanel panelmain, panelRight, PanelRightTop, PanelRightCenter;

    // constructor
    public welcome() {

        // add panel
        panelmain = new JPanel();
        panelRight = new JPanel();
        PanelRightTop = new JPanel();
        PanelRightCenter = new JPanel();

        JButton btnAdmin = new JButton(" Login as Admin");
        JButton btnUserLOGGING = new JButton("Login as User");
        JButton btnUserSIGNUP = new JButton(" Register As a User ");
        customizeButton(btnAdmin);
        customizeButton(btnUserLOGGING);
        customizeButton(btnUserSIGNUP);

        // panelright

        //-----------------------------------------right panel Top part---------------------------------------------------

        // Set layout for panelRightTop
        PanelRightTop.setLayout(new FlowLayout(FlowLayout.CENTER));
        // Add components to panelRightTop
        JLabel titleLabel = new JLabel("<html><center>Welcome to SkyVoyage</center></html>");
        titleLabel.setFont(new Font("Arial", Font.ITALIC, 55));
        PanelRightTop.add(titleLabel);

        //-----------------------------------------right panel middle part---------------------------------------------------

        // Use GridBagLayout for panelRight
        PanelRightCenter.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(100, 15, 15, 15); // Add some padding

        // Add components to panelRight with constraints
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 0;
        PanelRightCenter.add(btnAdmin, gbc);

        gbc.gridy = 1;
        PanelRightCenter.add(btnUserSIGNUP, gbc);
        gbc.gridy = 2;
        PanelRightCenter.add(btnUserLOGGING, gbc);

        PanelRightCenter.setBackground(new Color(31, 46, 80));

        this.setLayout(new BorderLayout());

        // difine grid layout
        panelmain.setLayout(new GridLayout(1, 2));
        panelRight.setLayout(new FlowLayout(FlowLayout.CENTER)); // Centering the contents of panelRight

        panelRight.setBackground(new Color(31, 46, 80));
        PanelRightTop.setBackground(new Color(31, 46, 80));

        // add all label to white color
        titleLabel.setForeground(Color.WHITE);

        // add panel to frame
        this.add(PanelRightTop, BorderLayout.NORTH);
        this.add(panelmain, BorderLayout.CENTER);

        panelmain.add(panelRight, BorderLayout.EAST);
        panelRight.add(PanelRightCenter);

        // ADD BUTTON ACTION
        btnAdmin.addActionListener(e -> {
            new adminloging();
            dispose();
        });

        btnUserLOGGING.addActionListener(e -> {
            new Logging();
            dispose();
        });

        btnUserSIGNUP.addActionListener(e -> {
            new Signup();
            dispose();
        });

        setTitle("welcome");
        setSize(1080, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void customizeButton(JButton button) {
        // Set custom font
        button.setFont(new Font("Arial", Font.BOLD, 16));

        // Set custom foreground color
        button.setForeground(Color.BLACK);
        button.setBackground(Color.LIGHT_GRAY);

        // Set padding for the button text
        button.setMargin(new Insets(50, 20, 8, 24));

        // Set rounded border
        int borderRadius = 15;
        button.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(borderRadius, borderRadius, borderRadius, borderRadius)));

        // Add more designs or effects if needed
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    // main method
    public static void main(String[] args) {
        new welcome();
    }
}
