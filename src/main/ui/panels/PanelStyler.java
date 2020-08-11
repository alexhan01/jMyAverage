package ui.panels;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;

// Represents a PanelStyler that consolidates repetitive styling methods
public abstract class PanelStyler extends JPanel implements ActionListener, ListSelectionListener {

    // MODIFIES: j
    // EFFECTS: styles j
    public void listStyler(JList j) {
        j.setSelectionMode(0);
        j.setSelectedIndex(0);
        j.setVisibleRowCount(7);
        j.addListSelectionListener(this);
    }

    // REQUIRES: 0 <= i <= 3
    // MODIFIES: j
    // EFFECTS: initializes j with given components
    public void initializeUIPane(JPanel j, Component[] components, int i) {
        j.setLayout(new BoxLayout(j, i));
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

    @Override
    public abstract void valueChanged(ListSelectionEvent e);
}
