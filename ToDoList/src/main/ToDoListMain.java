package main;

import gui.Frame;
import model.ElementFuncs;
import model.ToDoFuncs;
import model.status.NotStarted;

public class ToDoListMain
{
    public static void main(String[] args)
    {
        var element = new ElementFuncs(-1, "enter name here", "enter description here", null, new NotStarted());
        var list = new ToDoFuncs();
        
        var frame = new Frame(list, element);

        // show the frame
        frame.setVisible(true);
    }
}
