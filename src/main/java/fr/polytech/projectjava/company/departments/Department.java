package fr.polytech.projectjava.company.departments;

import fr.polytech.projectjava.company.Company;
import fr.polytech.projectjava.company.staff.Employee;
import fr.polytech.projectjava.company.staff.Person;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Represent a department
 * <p>
 * Created by Thomas Couchoud (MrCraftCod - zerderr@gmail.com) on 23/03/2017.
 *
 * @author Thomas Couchoud
 * @since 2017-03-23
 */
public abstract class Department<B extends Person & Serializable, E extends Employee & Serializable> implements Serializable
{
	private static final long serialVersionUID = 3644405617796041285L;
	private SimpleObjectProperty<B> leader;
	protected Company company;
	private int ID;
	private SimpleStringProperty name;
	private ObservableList<E> employees = FXCollections.observableArrayList();
	protected static int NEXT_ID = 0;
	
	/**
	 * Construct a department of a company with its name.
	 *
	 * @param company The company the department is in.
	 * @param name    The name of the department.
	 */
	public Department(Company company, String name, B leader)
	{
		this.ID = NEXT_ID++;
		this.company = company;
		this.name = new SimpleStringProperty(name);
		this.leader = new SimpleObjectProperty<>(leader);
	}
	
	/**
	 * Add an employee to the department.
	 *
	 * @param employee The employee to add.
	 */
	public void addEmployee(E employee)
	{
		if(!employees.contains(employee))
		{
			employees.add(employee);
			company.addEmployee(employee);
		}
	}
	
	@Override
	public boolean equals(Object obj)
	{
		return obj instanceof Department && ID == ((Department) obj).getID();
	}
	
	@Override
	public String toString()
	{
		return "[Department " + getName() + "\nLeader: " + getLeader() + "\nWorkers: \t" + getEmployees() + "]";
	}
	
	/**
	 * Remove an employee from the department.
	 *
	 * @param employee The employee to remove.
	 */
	public void removeEmployee(E employee)
	{
		this.employees.remove(employee);
		company.removeEmployee(employee);
	}
	
	/**
	 * Used to know if the department have this employee.
	 *
	 * @param employee The employee to check.
	 *
	 * @return true if in the department, false else.
	 */
	public boolean hasEmployee(E employee)
	{
		return getEmployees().contains(employee);
	}
	
	/**
	 * Get the employees of the department.
	 *
	 * @return A list of the employees.
	 */
	public ObservableList<E> getEmployees()
	{
		return employees;
	}
	
	/**
	 * Get the unique ID of the department.
	 *
	 * @return Its ID.
	 */
	public int getID()
	{
		return ID;
	}
	
	/**
	 * Get the name of the department.
	 *
	 * @return Its name.
	 */
	public String getName()
	{
		return nameProperty().get();
	}
	
	private SimpleStringProperty nameProperty()
	{
		return name;
	}
	
	public B getLeader()
	{
		return leaderProperty().get();
	}
	
	private SimpleObjectProperty<B> leaderProperty()
	{
		return leader;
	}
	
	protected void setLeader(B leader)
	{
		this.leader.set(leader);
	}
	
	private void writeObject(ObjectOutputStream oos) throws IOException
	{
		oos.writeInt(getID());
		oos.writeObject(getName());
		oos.writeObject(getLeader());
		oos.writeObject(company);
		oos.writeInt(getEmployees().size());
		for(Employee employee : getEmployees())
			oos.writeObject(employee);
	}
	
	private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException
	{
		ID = ois.readInt();
		name = new SimpleStringProperty((String) ois.readObject());
		leader = new SimpleObjectProperty<>((B) ois.readObject());
		company = (Company) ois.readObject();
		
		employees = FXCollections.observableArrayList();
		int empCount = ois.readInt();
		for(int i = 0; i < empCount; i++)
			employees.add((E) ois.readObject());
	}
}
