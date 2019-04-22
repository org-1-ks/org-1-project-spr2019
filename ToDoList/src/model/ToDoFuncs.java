package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
	ArrayList<Element> list = new ArrayList<Element>();
	@Override
	public void insertElement(Element e) {
		// TODO Auto-generated method stub
		list.add(e);
	}

	@Override
	public boolean removeElement(Element e) {
		// TODO Auto-generated method stub
		return list.remove(e);
	}

	@Override
	public void sortBy(Sort s) {
		// TODO Auto-generated method stub
		switch(s) {		
		case Priority:
			for (int i = 0; i < list.size(); i++)
			{
				if (list.get(i).getPriority() == i)
					//sort into i slot
			}
			//Collections.sort(list, sortByPriority) ; where sortByPriority is a class that implements comparator between priorities
			break;
		case DueDate:
			for (int i = 0; i < list.size(); i++)
			{
				Collections.sort(list.get(i).getDueDate()) ;
			}
			break;
		case Name:
			for (int i = 0; i < list.size(); i++)
			{
				Collections.sort(list.get(i).getName()) ;
				//depending on how the list is accessed
			}
			break;
		}
	}

	@Override
	public boolean moveElementToIndex(Element e, int index) {
		// TODO Auto-generated method stub
		if(list.indexOf(e)==index) {
			// Do nothing
		}
		else if(list.indexOf(e)!=-1) {
			list.remove(e);
			list.add(index,e);
		}
		else {
			return false;
		}
		return true;
	}

	@Override
	public Element getElement(String name) {
		// TODO Auto-generated method stub
		int spot = -1;
		for(int i=0; i<list.size(); i++) {
			String comp = list.get(i).getName();
			if(comp.equals(name)) {
				spot=i;
			}
		}
		if(spot!=-1) {
			return list.get(spot);
		}
		else {
			return null;
		}
		 
	}

	@Override
	public Element getElement(int priority) {
		// TODO Auto-generated method stub
		int spot = -1;
		for(int i=0; i<list.size();i++) {
			int comp = list.get(i).getPriority();
			if(comp==priority) {
				spot = i;
			}
		}
		if(spot!=-1) {
			return list.get(spot);
		}
		else {
			return null;
		}
	}

	@Override
	public void printTo(File file) {
		// TODO Auto-generated method stub
		try {
			PrintWriter write = new PrintWriter(FileWriter(file)) ;
			write.print("Priority\tDue Date\tTitle\tStatus\tDescription\n") ;
			for (int i = 0; i < list.size(); i++)
			{
				write.print(list.get(i).toString) ;
			}
			write.close() ;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	}

	@Override
	public void saveTo(File file) {
		//  Try catch block to handle file exceptions
		try {
			// OOS object, "FileOutputStream(file)" might not be correct, since
			// the param of FileOutputStream is usually a string (filename)...
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file));
			// load in each object in the array list

			os.writeObject(list);

			os.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void loadFrom(File file) {
		// Try catch block to handle file exceptions
		try {
			// same concern as before with FileInputStream
			ObjectInputStream is = new ObjectInputStream(new FileInputStream(file));
			// load each object into the ArrayList
			// NEEDS IMPLEMENTATION...
			
			list = (ArrayList<Element>) os.readObject();

			// if I wanted to read in a singular object I would do something like this...
			// Element temp = (Element) is.readObject();
			// However there is going to be multiple objects, I am not sure how to read
			// all of them in properly. 
			is.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//shouldn't be hard, reverse of saveto
	}

	@Override
	public ArrayList<Element> getElements() {
		// TODO Auto-generated method stub
		return list;
	}

}
