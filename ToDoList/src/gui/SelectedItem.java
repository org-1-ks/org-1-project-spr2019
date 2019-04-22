package gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;


/**
 * 
 * The screen when the user has selected an item
 * 
 * @author Krishna Sannasi
 *
 */
public class SelectedItem extends JPanel
{

    /**
     * 
     */
    private static final long serialVersionUID = -7309683605288525493L;
    
    public SelectedItem()
    {
        var list = new JList<>();
        var editScreen = makeEditScreen();
        
        list.setBorder(new LineBorder(Color.BLACK));
        
        setLayout(new GridBagLayout());
        
        var c = new GridBagConstraints();
        
        c.fill = GridBagConstraints.BOTH;

        c.gridx = 0;
        c.gridy = 0;

        c.weightx = 2;
        c.weighty = 1;
        
        c.gridwidth = 1;
        c.gridheight = 1;

        add(list, c);
        
        c.gridx++;
        c.weightx = 1;
        
        add(editScreen, c);
        
        setVisible(true);
    }

    private static JPanel makeEditScreen()
    {
        var editScreen = new JPanel();
        var topPanel = new JPanel();

        var nameLabel = new JLabel("Name:");
        var priorityLabel = new JLabel("Priority:");
        var statusLabel = new JLabel("Status:");
        
        var name = new JTextField(40);
        var priority = new JTextField(5);
        var status = new JComboBox<>(new String[] {
                "Not Started",
                "In Progress",
                "Finished"
        });
        var description = new JTextArea();
        description.setBorder(new LineBorder(Color.BLACK));
        
        topPanel.add(priorityLabel);
        topPanel.add(priority);
        topPanel.add(statusLabel);
        topPanel.add(status);
        topPanel.add(nameLabel);
        topPanel.add(name);
        
        editScreen.setLayout(new GridBagLayout());
        
        var c = new GridBagConstraints();
        
        c.fill = GridBagConstraints.BOTH;

        c.gridx = 0;
        c.gridy = 0;

        c.weightx = 1;
        c.weighty = 1;
        
        c.gridwidth = 1;
        c.gridheight = 1;
        
        editScreen.add(topPanel, c);
        
        c.gridx = 0;
        c.gridy = 1;
        c.weighty = 7;
        
        editScreen.add(description, c);
        
        return editScreen;
    }
}
