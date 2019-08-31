package com.cruddemo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="employee")
public class Employee {

	// define fields

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;

	@Column(name="email")
	private String email;

	@Column(name="date")
	private Date date;

	@Column(name="employee")
	private String employee;

	@Column(name="affliate")
	private String affliate;


	// define constructors

	public Employee() {

	}

	public Employee(String firstName, String lastName, String email, Date date, String employee, String affliate) {
		System.out.println("This is inside the users");
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.date = date;
		this.employee = employee;
		this.affliate = affliate;
	}

	// define getter/setter

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getEmployee() {
		return employee;
	}

	public void setEmployee(String employee) {
		employee = employee;
	}

	public String getAffliate() {
		return affliate;
	}

	public void setAffliate(String affliate) {
		affliate = affliate;
	}

	// define tostring

	@Override
	public String toString() {
		System.out.println(id);
		return "employee{" +
				"id=" + id +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", email='" + email + '\'' +
				", date=" + date +
				", isEmployee=" + employee +
				", isAffliate=" + affliate +
				'}';
	}

}











