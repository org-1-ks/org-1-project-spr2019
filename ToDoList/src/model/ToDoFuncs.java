package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.*;
import java.io.PrintWriter;

import controller.Element;
import controller.ToDoList;
import controller.Sort;

public class ToDoFuncs implements ToDoList, Serializable {
    private static final long serialVersionUID = -6785270027166806418L;
    
    ArrayList<Element> list = new ArrayList<Element>();
    
    @Override
    public void insertElement(Element e) {
        // TODO Auto-generated method stub
        for(Element a : list) {
            if (e.getName().equals(a.getName()))
                throw new RuntimeException("Name must be unique.");
            if (e.getDescription().equals(a.getDescription()))
                throw new RuntimeException("Description must be unique.");
            
        }
        boolean samePriority = false;
        for(Element a : list) {
            if (e.getPriority() == a.getPriority())
                samePriority = true;
        }
        for(int i = 0; i < list.size(); i++) {
            Element a = list.get(i);
            if (e.getPriority() <= a.getPriority() && samePriority == true)
                list.set(i, a.withPriority(a.getPriority() + 1));
        }
        
        list.add(e);
    }
    
    @Override
    public boolean removeElement(Element e) {
        // TODO Auto-generated method stub
        return list.remove(e);
    }
    
    public void clear() {
        list.clear();
    }
    
    @Override
    public void sortBy(Sort s) {
        // TODO Auto-generated method stub
        switch(s) {
            case Priority:
                // sort the list by comparing the priorities
                Collections.sort(list, (Element a, Element b) -> a.getPriority()
                        - b.getPriority());
                break;
            case DueDate:
                // sort the list by comparing the due dates
                Collections.sort(list, (Element a, Element b) -> a.getDueDate()
                        .compareTo(b.getDueDate()));
                break;
            case Name:
                // sort the list by comparing names
                Collections.sort(list, (Element a, Element b) -> a.getName()
                        .compareTo(b.getName()));
                break;
        }
    }
    
    @Override
    public boolean moveElementToIndex(Element e, int index) {
        // TODO Auto-generated method stub
        if (list.indexOf(e) == index) {
            // Do nothing
        } else if (list.indexOf(e) != -1) {
            list.remove(e);
            list.add(index, e);
        } else {
            return false;
        }
        return true;
    }
    
    @Override
    public Element getElement(String name) {
        // TODO Auto-generated method stub
        int spot = -1;
        for(int i = 0; i < list.size(); i++) {
            String comp = list.get(i).getName();
            if (comp.equals(name)) {
                spot = i;
            }
        }
        if (spot != -1) {
            return list.get(spot);
        } else {
            return null;
        }
        
    }
    
    @Override
    public Element getElement(int priority) {
        // TODO Auto-generated method stub
        int spot = -1;
        for(int i = 0; i < list.size(); i++) {
            int comp = list.get(i).getPriority();
            if (comp == priority) {
                spot = i;
            }
        }
        if (spot != -1) {
            return list.get(spot);
        } else {
            return null;
        }
    }
    
    @Override
    public void printTo(File file) {
        // TODO Auto-generated method stub
        try {
            PrintWriter write = new PrintWriter(new FileWriter(file));
            write.print(
                    "Priority\tDue Date\tTitle\t\tStatus\tStart/End Date\tDescription\n");
            for(int i = 0; i < list.size(); i++) {
                write.print(list.get(i).toString() + "\n");
            }
            write.close();
            
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void saveTo(File file) {
        // Try catch block to handle file exceptions
        try {
            // OOS object, "FileOutputStream(file)" might not be correct, since
            // the param of FileOutputStream is usually a string (filename)...
            ObjectOutputStream os = new ObjectOutputStream(
                    new FileOutputStream(file));
            // load in each object in the array list
            
            os.writeObject(list);
            
            os.close();
        } catch(FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch(IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public void loadFrom(File file) {
        // Try catch block to handle file exceptions
        try {
            // same concern as before with FileInputStream
            ObjectInputStream is = new ObjectInputStream(
                    new FileInputStream(file));
            // load each object into the ArrayList
            
            list = (ArrayList<Element>) is.readObject();
            
            is.close();
        } catch(FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch(IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch(ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    @Override
    public ArrayList<Element> getElements() {
        // TODO Auto-generated method stub
        return list;
    }
    
}
