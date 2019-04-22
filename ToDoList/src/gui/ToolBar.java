package gui;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;

import controller.Sort;

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
            frame.selectItem();
            
            JOptionPane.showMessageDialog(frame, "Item inserted", "Button Test", JOptionPane.INFORMATION_MESSAGE);
        });

        remove.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Item removed", "Button Test", JOptionPane.INFORMATION_MESSAGE);
        });

        print.addActionListener(e -> {
            var fileChooser = new JFileChooser();
            fileChooser.setMultiSelectionEnabled(false);
            fileChooser.showOpenDialog(frame);
            var file = fileChooser.getSelectedFile();
            
            if(file == null) {
                JOptionPane.showMessageDialog(frame, "Please select a file", "Print", JOptionPane.ERROR_MESSAGE);
            } else {
                frame.list.printTo(file);
                JOptionPane.showMessageDialog(frame, "List printed", "Print", JOptionPane.INFORMATION_MESSAGE);
            }

            frame.updateList();
        });

        save.addActionListener(e -> {
            var fileChooser = new JFileChooser();
            fileChooser.setMultiSelectionEnabled(false);
            fileChooser.showOpenDialog(frame);
            var file = fileChooser.getSelectedFile();
            
            if(file == null) {
                JOptionPane.showMessageDialog(frame, "Please select a file", "Save", JOptionPane.ERROR_MESSAGE);
            } else {
                frame.list.saveTo(file);
                JOptionPane.showMessageDialog(frame, "List saved", "Save", JOptionPane.INFORMATION_MESSAGE);
            }

            frame.updateList();
        });

        load.addActionListener(e -> {
            var fileChooser = new JFileChooser();
            fileChooser.setMultiSelectionEnabled(false);
            fileChooser.showOpenDialog(frame);
            var file = fileChooser.getSelectedFile();
            
            if(file == null || !file.exists()) {
                JOptionPane.showMessageDialog(frame, "Please select a file", "Load", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    frame.list.loadFrom(file);
                    JOptionPane.showMessageDialog(frame, "List loaded", "Load", JOptionPane.INFORMATION_MESSAGE);
                } catch(Exception ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage(), "Load", JOptionPane.ERROR_MESSAGE);   
                }
            }
            
            frame.updateList();
        });

        sort.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "List sorted", "Button Test", JOptionPane.INFORMATION_MESSAGE);
            
            if("comboBoxChanged".equals(e.getActionCommand())) {
                String item = (String)sort.getSelectedItem();
                Sort sort;
                
                // TODO: Add sorting mechanisms
                switch(item) {
                    case PRIORITY:
                        sort = Sort.Priority;
                        break;
                    case DUE_DATE:
                        sort = Sort.DueDate;
                        break;
                    case TASK_NAME:
                        sort = Sort.Name;
                        break;
                    default:
                        throw new IllegalStateException(String.format("Unknown Item: %s", item));
                }
                
                frame.list.sortBy(sort);
                frame.updateList();
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
