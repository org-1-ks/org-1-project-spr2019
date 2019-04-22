package gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import controller.Element;
import model.status.Status;


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
    private static final String NOT_STARTED = "Not Started";
    private static final String FINISHED = "Finished";
    private static final String IN_PROGRESS = "In Progress";

    private static JTextField priority;
    private static JTextField name;
    private static JComboBox<String> status;
    private static JTextArea description;

    private static Element selected;
    
    public SelectedItem(Frame frame)
    {
        var list = new JList<>();
        var editScreen = makeEditScreen(frame);
        
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

    private static JPanel makeEditScreen(Frame frame)
    {
        var editScreen = new JPanel();
        var topPanel = new JPanel();
        var saveCancelPanel = new JPanel();

        var nameLabel = new JLabel("Name:");
        var priorityLabel = new JLabel("Priority:");
        var statusLabel = new JLabel("Status:");
        var save = new JButton("Save");
        var cancel = new JButton("Cancel");
        
        name = new JTextField(40);
        priority = new JTextField(5);
        status = new JComboBox<>(new String[] {
            NOT_STARTED,
            IN_PROGRESS,
            FINISHED
        });
        description = new JTextArea();
        description.setBorder(new LineBorder(Color.BLACK));
        
        save.addActionListener(e -> {
            Status.Type statusType;
            
            switch((String) status.getSelectedItem()) {
                case NOT_STARTED:
                    statusType = Status.Type.NOT_STARTED;
                    break;
                case FINISHED:
                    statusType = Status.Type.FINISHED;
                    break;
                case IN_PROGRESS:
                    statusType = Status.Type.IN_PROGRESS;
                    break;
            }
            
            var newSelected = selected
                .withName(name.getText())
                .withDescription(description.getText())
                .withPriority(Integer.parseInt(priority.getText()))
//                .withStatus(status.getSelectedItem()) // TODO: Finish status
                ;
            frame.list.removeElement(selected);
            frame.list.insertElement(newSelected);
            selected = newSelected;
            
            frame.updateList();
        });
        
        topPanel.add(priorityLabel);
        topPanel.add(priority);
        topPanel.add(statusLabel);
        topPanel.add(status);
        topPanel.add(nameLabel);
        topPanel.add(name);
        
        saveCancelPanel.add(save);
        saveCancelPanel.add(cancel);
        
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
        c.gridy++;
        c.anchor = GridBagConstraints.WEST;
        
        editScreen.add(saveCancelPanel, c);
        
        c.gridy++;
        c.weighty = 7;
        c.anchor = GridBagConstraints.CENTER;
        
        editScreen.add(description, c);
        
        return editScreen;
    }
    
    void use(Element e) {
        selected = e;
        
        name.setText(e.getName());
        description.setText(e.getDescription());
        priority.setText("" + e.getPriority());
        
        Status.Type statusType = null;
        
        if(e.getStatus() != null) {
            statusType = e.getStatus().getType();
        }
        
        if(statusType == null) {
            status.setSelectedItem(NOT_STARTED);
        } else {
            switch(statusType) {
                case NOT_STARTED:
                    status.setSelectedItem(NOT_STARTED);
                    break;
                case FINISHED:
                    status.setSelectedItem(FINISHED);
                    break;
                case IN_PROGRESS:
                    status.setSelectedItem(IN_PROGRESS);
                    break;
            }
        }
    }
}
