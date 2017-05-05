package fr.polytech.projectjava;

import fr.polytech.projectjava.company.Company;
import fr.polytech.projectjava.company.departments.StandardDepartment;
import fr.polytech.projectjava.company.staff.Boss;
import fr.polytech.projectjava.company.staff.Employee;
import fr.polytech.projectjava.company.staff.Manager;
import fr.polytech.projectjava.jfx.main.MainApplication;
import javafx.application.Application;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Thomas Couchoud (MrCraftCod - zerderr@gmail.com) on 23/03/2017.
 *
 * @author Thomas Couchoud
 * @since 2017-03-23
 */
public class Main
{
	/**
	 * Temporary main method.
	 *
	 * @param args Program's arguments.
	 */
	public static void main(String[] args)
	{
		Company comp = new Company("TheCompany", new Boss("Robert", "LeBoss"));
		comp.addDepartment(new StandardDepartment(comp, "IT Dpt", new Manager("Victor", "AManager")));
		comp.addDepartment(new StandardDepartment(comp, "MC Dpt", new Manager("Maxence", "AManager")));
		comp.getDepartment(0).ifPresent(dpt -> dpt.addEmployee(new Employee("A", "AEmployee")));
		comp.getDepartment(0).ifPresent(dpt -> dpt.addEmployee(new Employee("B", "AEmployee")));
		comp.getDepartment(0).ifPresent(dpt -> dpt.addEmployee(new Employee("C", "AEmployee")));
		comp.getDepartment(1).ifPresent(dpt -> dpt.addEmployee(new Employee("D", "AEmployee")));
		comp.getDepartment(1).ifPresent(dpt -> dpt.addEmployee(new Employee("E", "AEmployee")));
		comp.getDepartment(1).ifPresent(dpt -> dpt.addEmployee(new Employee("F", "AEmployee")));
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(".", "tCompany.pjv"))))
		{
			oos.writeObject(comp);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		Application.launch(MainApplication.class, args);
	}
}
