package model;

import java.io.File;
import java.util.ArrayList;

import controller.Element;
import controller.ToDoList;
import controller.Sort;

public class ToDoFuncs implements ToDoList {
	ArrayList<Element> list = new ArrayList<Element>();
	@Override
	public void insertElement(Element e) {
		// TODO Auto-generated method stub
		list.add(e);
	}

	@Override
	public boolean removeElement(Element e) {
		// TODO Auto-generated method stub
		list.remove(e);
		return true;
	}

	@Override
	public void sortBy(Sort s) {
		// TODO Auto-generated method stub
		switch(s) {
		case Priority:
			//list.sort(Priority);
			break;
		case DueDate:
			//list.sort(DueDate);
			break;
		case Name:
			//list.sort(Name);
			break;
		}
	}

	@Override
	public boolean moveElementToIndex(Element e, int index) {
		// TODO Auto-generated method stub
		if(list.indexOf(e)==index) {
		}
		else if(list.indexOf(e)!=-1) {
			list.remove(e);
			list.add(index,e);
		}
		else {
			list.add(index,e);
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
		//not sure how to handle printing to a printer device
	}

	@Override
	public void saveTo(File file) {
		// TODO Auto-generated method stub
		//gotta look this up again to figure it out
	}

	@Override
	public void loadFrom(File file) {
		// TODO Auto-generated method stub
		//shouldn't be hard, reverse of saveto
	}

	@Override
	public ArrayList<Element> getElements() {
		// TODO Auto-generated method stub
		return list;
	}

}
