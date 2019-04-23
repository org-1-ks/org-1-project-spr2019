package gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import controller.Element;
import model.status.Finished;
import model.status.InProgress;
import model.status.NotStarted;
import model.status.Status;

/**
 * 
 * The screen when the user has selected an item
 * 
 * @author Krishna Sannasi
 *
 */
public class SelectedItem extends JPanel {
    private static final long serialVersionUID = -7309683605288525493L;
    private static final String NOT_STARTED = "Not Started";
    private static final String FINISHED = "Finished";
    private static final String IN_PROGRESS = "In Progress";
    
    private static JTextField priority;
    private static JTextField name;
    private static JTextField date;
    private static JTextField auxDate;
    private static JComboBox<String> status;
    private static JTextArea description;
    
    private static Element selected;
    
    public SelectedItem(Frame frame) {
        var topPanel = new JPanel();
        var saveCancelPanel = new JPanel();
        
        var nameLabel = new JLabel("Name:");
        var priorityLabel = new JLabel("Priority:");
        var statusLabel = new JLabel("Status:");
        var dateLabel = new JLabel("Date: ");
        var auxDateLabel = new JLabel("Start/End Date: ");
        var save = new JButton("Save");
        var cancel = new JButton("Cancel");
        
        name = new JTextField(20);
        date = new JTextField(10);
        auxDate = new JTextField(10);
        priority = new JTextField(5);
        status = new JComboBox<>(
                new String[] { NOT_STARTED, IN_PROGRESS, FINISHED });
        description = new JTextArea();
        description.setBorder(new LineBorder(Color.BLACK));
        
        auxDate.setEnabled(false);
        
        status.addActionListener(e -> {
            switch((String) status.getSelectedItem()) {
                default:
                    return; // unreachable
                case NOT_STARTED:
                    auxDate.setEnabled(false);
                    break;
                case FINISHED:
                case IN_PROGRESS:
                    auxDate.setEnabled(true);
                    break;
            }
        });
        
        // Discards changes
        cancel.addActionListener(e -> {
            frame.unselectItem();
        });
        
        // Saves changes
        save.addActionListener(e -> {
            if ("N/A".equals(date.getText())) {
                JOptionPane.showMessageDialog(frame, "Please enter a due date",
                        "Edit Item", JOptionPane.ERROR_MESSAGE);
            } else {
                var numberOfElements = frame.list.getElements().size() + 1;
                
                try {
                    Status statusValue;
                    
                    switch((String) status.getSelectedItem()) {
                        default:
                            return; // unreachable
                        case NOT_STARTED:
                            statusValue = new NotStarted();
                            break;
                        case FINISHED:
                            statusValue = new Finished(
                                    LocalDate.parse(auxDate.getText(),
                                            DateTimeFormatter.ISO_LOCAL_DATE));
                            break;
                        case IN_PROGRESS:
                            statusValue = new InProgress(
                                    LocalDate.parse(auxDate.getText(),
                                            DateTimeFormatter.ISO_LOCAL_DATE));
                            break;
                    }
                    
                    var priorityValue = Integer.parseInt(priority.getText());
                    
                    if (priorityValue < 1 || priorityValue > numberOfElements) {
                        throw new NumberFormatException("Invalid Priority");
                    }
                    
                    var dueDate = LocalDate.parse(date.getText(),
                            DateTimeFormatter.ISO_LOCAL_DATE);
                    var newSelected = selected.withName(name.getText())
                            .withDescription(description.getText())
                            .withPriority(priorityValue).withDueDate(dueDate)
                            .withStatus(statusValue);
                    
                    frame.list.removeElement(selected);
                    frame.list.insertElement(newSelected);
                    selected = newSelected;
                    
                    frame.updateList();
                    frame.unselectItem();
                } catch(DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(frame,
                            "dates must have the format YYYY-MM-DD",
                            "Edit Item", JOptionPane.ERROR_MESSAGE);
                } catch(NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, String.format(
                            "Please enter an integer between 1-%d for the priority",
                            numberOfElements), "Edit Item",
                            JOptionPane.ERROR_MESSAGE);
                } catch(RuntimeException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage(),
                            "Edit Item", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        topPanel.add(priorityLabel);
        topPanel.add(priority);
        topPanel.add(statusLabel);
        topPanel.add(status);
        topPanel.add(auxDateLabel);
        topPanel.add(auxDate);
        topPanel.add(dateLabel);
        topPanel.add(date);
        topPanel.add(nameLabel);
        topPanel.add(name);
        
        saveCancelPanel.add(save);
        saveCancelPanel.add(cancel);
        
        setLayout(new GridBagLayout());
        
        var c = new GridBagConstraints();
        
        c.fill = GridBagConstraints.BOTH;
        
        c.gridx = 0;
        c.gridy = 0;
        
        c.weightx = 1;
        c.weighty = 1;
        
        c.gridwidth = 1;
        c.gridheight = 1;
        
        add(topPanel, c);
        
        c.gridx = 0;
        c.gridy++;
        c.anchor = GridBagConstraints.WEST;
        
        add(saveCancelPanel, c);
        
        c.gridy++;
        c.weighty = 7;
        c.anchor = GridBagConstraints.CENTER;
        
        add(description, c);
        
        setVisible(true);
    }
    
    /**
     * Select which item to edit
     * 
     * @param e
     *              - which element is selected
     */
    void use(Element e) {
        selected = e;
        
        var dateString = "N/A";
        if (e.getDueDate() != null) {
            dateString = e.getDueDate()
                    .format(DateTimeFormatter.ISO_LOCAL_DATE);
        }
        
        name.setText(e.getName());
        description.setText(e.getDescription());
        priority.setText("" + e.getPriority());
        date.setText(dateString);
        
        Status.Type statusType = null;
        
        if (e.getStatus() != null) {
            statusType = e.getStatus().getType();
        }
        
        if (statusType == null) {
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
