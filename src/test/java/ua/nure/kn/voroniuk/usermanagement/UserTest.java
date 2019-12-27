package ua.nure.kn.voroniuk.usermanagement;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import junit.framework.TestCase;
import ua.nure.kn.voroniuk.usermanagement.User;

class UserTest extends TestCase {

	//User's age is 19 years old
	public static final int YEAR = 2000;
	public static final int MONTH = 10;
	public static final int DATE = 3;
	public static final int ETALONE_AGE = 19;
	
	//User will be born in this year
	public static final int YEAR2 = 2019;
	public static final int MONTH2 = 10;
	public static final int DATE2 = 9;
	public static final int ETALONE_AGE2 = -1;
	
	//User have a birthday tomorrow 
	public static final int YEAR3 = 1999;
	public static final int MONTH3 = Calendar.MONTH;
	public static final int DATE3 = Calendar.DAY_OF_MONTH + 1;
	public static final int ETALONE_AGE3 = 20;
	
	//User's birthday was in last month
	public static final int YEAR4 = 1995;
	public static final int MONTH4 = Calendar.SEPTEMBER;
	public static final int DATE4 = 15;
	public static final int ETALONE_AGE4 = 24;
	
	//User's birthday will be in the next month
	public static final int YEAR5 = 1999;
	public static final int MONTH5 = Calendar.MONTH + 1;
	public static final int DATE5 = 28;
	public static final int ETALONE_AGE5 = 20;
	
	private User user;

	public void tearDown() throws Exception {
		super.tearDown();
	}

	@BeforeEach
	protected void setUp() throws Exception {
		super.setUp();
		user = new User();
	}

	@Test
	void test() {
		textGetFullName();
		testGetAge1(null);
		testGetAge2(null);
		testGetAge3(null);
		testGetAge4(null);
		testGetAge5(null);
	}
	
	private void textGetFullName() {
		user.setFirstName("Ivan");
		user.setLastName("Petrov");
		assertEquals("Petrov, Ivan", user.getFullName());
	}
	
	private void testGetAge1(Date dateOfBirth){ 
	     Calendar calendar =
	    		 Calendar.getInstance(); calendar.set(YEAR, MONTH, DATE); dateOfBirth =
	    		 calendar.getTime(); user.setDateOfBirth(dateOfBirth);
	    		 assertEquals(ETALONE_AGE, user.getAge()); 
	    		 }
	
	private void testGetAge2(Date dateOfBirth) {
		 Calendar calendar = Calendar.getInstance();
		 calendar.set(YEAR2, MONTH2, DATE2);
		 dateOfBirth = calendar.getTime();
		 user.setDateOfBirth(dateOfBirth);
		 assertEquals(ETALONE_AGE2, user.getAge());
	 }
	
	private void testGetAge3(Date dateOfBirth) {
		 Calendar calendar = Calendar.getInstance();
		 calendar.set(YEAR3, MONTH3, DATE3);
		 dateOfBirth = calendar.getTime();
		 user.setDateOfBirth(dateOfBirth);
		 assertEquals(ETALONE_AGE3, user.getAge());
	}

	private void testGetAge4(Date dateOfBirth) {
		 Calendar calendar = Calendar.getInstance();
		 calendar.set(YEAR4, MONTH4, DATE4);
		 dateOfBirth = calendar.getTime();
		 user.setDateOfBirth(dateOfBirth);
		 assertEquals(ETALONE_AGE4, user.getAge());
	}
	
	private void testGetAge5(Date dateOfBirth) {
		 Calendar calendar = Calendar.getInstance();
		 calendar.set(YEAR5, MONTH5, DATE5);
		 dateOfBirth = calendar.getTime();
		 user.setDateOfBirth(dateOfBirth);
		 assertEquals(ETALONE_AGE5, user.getAge());
	}
	
}
