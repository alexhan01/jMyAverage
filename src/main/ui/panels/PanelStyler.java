package ui.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public abstract class PanelStyler extends JPanel implements ActionListener {

    // MODIFIES: j
    // EFFECTS: initializes j with given components
    public void initializeUIPane(JPanel j, Component[] components) {
        j.setLayout(new BoxLayout(j, 1));
        for (Component c : components) {
            j.add(c);
        }
    }

    // EFFECTS: creates button based on given string
    public JButton createButton(String str) {
        JButton button = new JButton(str);
        button.setActionCommand(str);
        button.addActionListener(this);
        return button;
    }

    @Override
    public abstract void actionPerformed(ActionEvent e);
}
