package gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class NoSelection extends JPanel
{

    /**
     * 
     */
    private static final long serialVersionUID = 6611121813221534119L;
    
    public NoSelection()
    {
        var title = new JLabel("New List");
        var priority = new JLabel("Priority");
        var dueDate = new JLabel("Due Date");
        var taskName = new JLabel("Task Name");
        var status = new JLabel("Status");
        var description =  new JLabel("Description");
        
        var priorityList = new JList<>();
        var dueDateList = new JList<>();
        var taskNameList = new JList<>();
        var statusList = new JList<>();
        var descriptionList = new JList<>();

        var labels = new JLabel[] {
                priority,
                dueDate,
                taskName,
                status,
                description
        };

        var lists = new JComponent[] {
                priorityList,
                dueDateList,
                taskNameList,
                statusList,
                descriptionList
        };

        Border border = new LineBorder(Color.BLACK);
        for(var list: lists) {
            list.setBackground(Color.WHITE);
            list.setBorder(border);
        }
        
        setLayout(new GridBagLayout());
        
        var c = new GridBagConstraints();
        
        c.fill = GridBagConstraints.BOTH;

        c.gridx = 2;
        c.gridy = 0;

        c.weightx = 1;
        c.weighty = 1;
        
        c.gridwidth = 1;
        c.gridheight = 1;
        
        c.weightx = 1;
        c.weighty = 1;

        c.anchor = GridBagConstraints.CENTER;
        
        add(title, c);
        
        c.gridy++;
        c.gridx = 0;
        
        for(int i = 0; i < labels.length; i++) {
            var label = labels[i];
            var list = lists[i];
            
            if("Task Name".equals(label.getText()) || "Description".equals(label.getText())) {
                c.weightx = 3;
            } else {
                c.weightx = 1;
            }
            
            c.gridx = i;
            c.gridy = 1;
            c.weighty = 1;

            add(label, c);
            
            c.gridy = 2;
            c.weighty = 20;
            
            add(list, c);
        }
        
        
        setVisible(true);
    }
}
