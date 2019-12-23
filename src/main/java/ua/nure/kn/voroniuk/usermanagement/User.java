package ua.nure.kn.voroniuk.usermanagement;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class User implements Serializable {

	private static final long serialVersionUID = -138019132080436045L;
	private Long id;
	private String firstName;
	private String lastName;
	private Date dateOfBirth;

	public User(Long id, String firstName, String lastName, Date date) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = date;
	}

	public User(String firstName, String lastName, Date date) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = date;
	}

	public User() {

	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getFullName() {
		return new StringBuilder().append(getLastName()).append(", ").append(getFirstName()).toString();
	}

	public Object getAge() {
		Calendar calendar = Calendar.getInstance();

		calendar.setTime(new Date());
		int currentMonth = calendar.get(Calendar.MONTH);
		int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
		int currentYear = calendar.get(Calendar.YEAR);

		calendar.setTime(getDateOfBirth());
		int birthYear = calendar.get(Calendar.YEAR);
		int birthMonth = calendar.get(Calendar.MONTH);
		int birthDay = calendar.get(Calendar.DATE);

		int userAge = currentYear - birthYear;

		if (userAge == 0) {
			if (currentMonth < birthMonth) {
				return --userAge;
			}
		} else if (currentMonth == birthMonth && currentDay++ == birthDay) {
			return --userAge;
		} else if (--currentMonth == birthMonth || currentMonth++ == birthMonth) {
			return userAge;
		}
		return userAge;
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (this.getId() == null && (((User) obj).getId() == null)) {
			return true;
		}
		return this.getId().equals(((User) obj).getId());
	}

	public int hashCode() {
		if (this.getId() == null) {
			return 0;
		}
		return this.getId().hashCode();
	}

}