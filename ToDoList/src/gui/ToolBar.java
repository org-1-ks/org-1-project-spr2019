package gui;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;

/**
 * 
 * The tool bar that is shared across screens
 * 
 * @author Krishna Sannasi
 *
 */
public class ToolBar extends JToolBar
{

    /**
     * 
     */
    private static final long serialVersionUID = -6297787221312734786L;
    
    private JButton insert, remove, print, save, load;
    private JComboBox<String> sort;

    public ToolBar(Frame frame)
    {
        final String PRIORITY = "Priority";
        final String DUE_DATE = "Due Date";
        final String TASK_NAME = "Task Name";
        
//        super(JToolBar.VERTICAL);
        
        insert = new JButton("Insert Task");
        remove = new JButton("Remove Task");
        print = new JButton("Print");
        save = new JButton("Save");
        load = new JButton("Load");
        var sortLabel = new JLabel("Sort By");
        sort = new JComboBox<>(new String[] {
                PRIORITY,
                DUE_DATE,
                TASK_NAME
        });

        insert.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Item inserted", "Button Test", JOptionPane.INFORMATION_MESSAGE);
        });

        remove.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Item removed", "Button Test", JOptionPane.INFORMATION_MESSAGE);
        });

        print.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "List printed", "Button Test", JOptionPane.INFORMATION_MESSAGE);
        });

        save.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "List saved", "Button Test", JOptionPane.INFORMATION_MESSAGE);
        });

        load.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "List loaded", "Button Test", JOptionPane.INFORMATION_MESSAGE);
        });

        sort.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "List sorted", "Button Test", JOptionPane.INFORMATION_MESSAGE);
            
            if("comboBoxChanged".equals(e.getActionCommand())) {
                String item = (String)sort.getSelectedItem();
                
                // TODO: Add sorting mechanisms
                switch(item) {
                    case PRIORITY:
                        break;
                    case DUE_DATE:
                        break;
                    case TASK_NAME:
                        break;
                    default:
                        throw new IllegalStateException(String.format("Unknown Item: %s", item));
                }
            }
        });

        add(insert);        
        add(remove);
        add(sortLabel);
        add(sort);
        add(print);
        add(save);
        add(load);
        
        setFloatable(false);
        setVisible(true);

        var size = sort.getSize();
        size.height = 25;
        size.width = 200;
        
        sort.setPreferredSize(size);
        sort.setMaximumSize(size);
    }
}
