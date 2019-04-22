package model;

import java.time.LocalDate;

import controller.Element;
import model.status.Status;

public class ElementFuncs implements Element{
	int priority=0;
	String name=null;
	String description=null;
	LocalDate dueDate=null;
	Status status=null;
	public ElementFuncs(int priority, String name, String description,
			LocalDate date, Status status) {
		this.priority=priority;
		this.name=name;
		this.description=description;
		dueDate=date;
		this.status=status;
	}
	@Override
	public int getPriority() {
		// TODO Auto-generated method stub
		return priority;
	}

	@Override
	public LocalDate getDueDate() {
		// TODO Auto-generated method stub
		return dueDate;
	}

	@Override
	public Status getStatus() {
		// TODO Auto-generated method stub
		return status;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return description;
	}

	@Override
	public Element withPriority(int priority) {
		// TODO Auto-generated method stub
		ElementFuncs newItem = new ElementFuncs(priority, name, description, dueDate, status);
		return newItem; //not at all sure what is going on
	}

	@Override
	public Element withDueDate(LocalDate date) {
		// TODO Auto-generated method stub
		ElementFuncs newItem = new ElementFuncs(priority, name, description, date, status);
		return newItem; //what are these supposed to do
	}

	@Override
	public Element withStatus(Status status) {
		// TODO Auto-generated method stub
		ElementFuncs newItem = new ElementFuncs(priority, name, description, dueDate, status);
		return newItem; //please explain
	}

	@Override
	public Element withName(String name) {
		// TODO Auto-generated method stub
		ElementFuncs newItem = new ElementFuncs(priority, name, description, dueDate, status);
		return newItem; //I am confused
	}

	@Override
	public Element withDescription(String description) {
		// TODO Auto-generated method stub
		ElementFuncs newItem = new ElementFuncs(priority, name, description, dueDate, status);
		return newItem; //There must be a reason
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		if (this.getStatusDate() != null)
			return this.getPriority() + "\t" + this.getDueDate() + "\t" + this.getName() + "\t" + 
					this.getStatus() + "\t" + this.getStatusDate() + "\t" + this.getDescription() ;	
		else {
			return this.getPriority() + "\t" + this.getDueDate() + "\t" + this.getName() + "\t" + 
					this.getStatus() + "\tN/A\t" + this.getDescription() ;
		}

	}
	
}
