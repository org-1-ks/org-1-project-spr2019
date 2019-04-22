package gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;

import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import controller.Element;
import controller.ToDoList;
import model.status.Status;

/**
 * 
 * The screen when the user has not selected an item
 * 
 * @author Krishna Sannasi
 *
 */
public class NoSelection extends JPanel
{

    /**
     * 
     */
    private static final long serialVersionUID = 6611121813221534119L;
    private JList<Object>[] lists;
    private DefaultListModel<Integer> priorityList;
    private DefaultListModel<LocalDate> dueDateList;
    private DefaultListModel<String> taskNameList;
    private DefaultListModel<Status> statusList;
    private DefaultListModel<String> descriptionList;

    @SuppressWarnings("unchecked")
    public NoSelection(Frame frame)
    {
        var title = new JLabel("New List");
        var priority = new JLabel("Priority");
        var dueDate = new JLabel("Due Date");
        var taskName = new JLabel("Task Name");
        var status = new JLabel("Status");
        var description =  new JLabel("Description");
        
        priorityList = new DefaultListModel<Integer>();
        dueDateList = new DefaultListModel<LocalDate>();
        taskNameList = new DefaultListModel<String>();
        statusList = new DefaultListModel<Status>();
        descriptionList = new DefaultListModel<String>();
        
        var labels = new JLabel[] {
                priority,
                dueDate,
                taskName,
                status,
                description
        };

        lists = new JList[] {
            new JList<>(priorityList),
            new JList<>(dueDateList),
            new JList<>(taskNameList),
            new JList<>(statusList),
            new JList<>(descriptionList)
        };
        
        updateList(frame.list);
        
        for(var list: lists) {
            list.addMouseListener(new MouseListener()
            {
                @Override
                public void mouseReleased(MouseEvent e) {}
                
                @Override
                public void mousePressed(MouseEvent e) {}
                
                @Override
                public void mouseExited(MouseEvent e) {}
                
                @Override
                public void mouseEntered(MouseEvent e) {}
                
                @Override
                public void mouseClicked(MouseEvent e)
                {
                    if(e.getClickCount() == 2) {
                        int index = list.getSelectedIndex();
                        for(var l: lists) {
                            l.setSelectedIndex(index);
                        }
                        
                        frame.selectedItem =
                                frame.selectedItem
                                    .withPriority((int) lists[0].getSelectedValue())
                                    .withDueDate((LocalDate) lists[1].getSelectedValue())
                                    .withName((String) lists[2].getSelectedValue())
                                    .withStatus((Status) lists[3].getSelectedValue())
                                    .withDescription((String) lists[4].getSelectedValue());
                        System.out.println("Selected: " + frame.selectedItem.getDueDate());
                        frame.selectItem();
                    } else {
                        for(var l: lists) {
                            l.setSelectedIndices(new int[] {});
                        }
                    }
                }
            });
        }
        
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

    void updateList(ToDoList list) {
        priorityList.clear();
        dueDateList.clear();
        taskNameList.clear();
        statusList.clear();
        descriptionList.clear();
        
        int size = 0;
        for(var e: list.getElements()) {
            priorityList.add(size, e.getPriority());
            dueDateList.add(size, e.getDueDate());
            taskNameList.add(size, e.getName());
            statusList.add(size, e.getStatus());
            descriptionList.add(size, e.getDescription()); 
            size++;
        }
    }
}
