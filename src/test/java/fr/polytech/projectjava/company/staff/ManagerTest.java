package fr.polytech.projectjava.company.staff;

import com.sun.javaws.exceptions.InvalidArgumentException;
import fr.polytech.projectjava.company.Company;
import fr.polytech.projectjava.company.departments.StandardDepartment;
import org.junit.Before;
import org.junit.Test;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Created by Thomas Couchoud (MrCraftCod - zerderr@gmail.com) on 23/03/2017.
 *
 * @author Thomas Couchoud
 * @since 2017-03-23
 */
public class ManagerTest
{
	private Manager manager1;
	private Manager manager2;

	@Before
	public void setUp() throws InvalidArgumentException
	{
		manager1 = new Manager("A", "B");
		manager2 = new Manager("A", "B");
		new StandardDepartment(new Company("A", new Boss("A", "B")), "RND", manager1);
	}

	@Test
	public void isManaging() throws Exception
	{
		assertTrue(manager1.isManaging());
		assertFalse(manager2.isManaging());
	}
}