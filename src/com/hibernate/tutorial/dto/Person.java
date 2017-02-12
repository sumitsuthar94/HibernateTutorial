package com.hibernate.tutorial.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "Person")
public class Person {

	@Id
	@GeneratedValue
	@Column(name = "person_id")
	private int id;
	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "salary")
	@Transient
	private int salary;

	@Column(name = "join_date")
	@Temporal(TemporalType.DATE)
	private Date joinDate;
	
	@ElementCollection
	@Embedded
	@JoinTable(name="person_address",joinColumns=@JoinColumn(name="person_id"))
	/*@GenericGenerator(name = "sequence-gen", strategy = "sequence")
	@CollectionId(columns = { @Column(name="address_id") }, generator = "sequence-gen", type = @Type(type="long"))
	*/
	private Collection<Address> listOfAddress = new ArrayList<>();

//	@Column(name = "home_address")
//	@Embedded
//	@AttributeOverrides({ @AttributeOverride(name = "city", column = @Column(name = "home_city_name")),
//			@AttributeOverride(name = "state", column = @Column(name = "home_state_name")),
//			@AttributeOverride(name = "pincode", column = @Column(name = "home_pincode")) })
//	private Address homeAddress;
//	@Embedded
//	private Address officeAddress;
//	
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the salary
	 */
	public int getSalary() {
		return salary;
	}

	/**
	 * @param salary
	 *            the salary to set
	 */
	public void setSalary(int salary) {
		this.salary = salary;
	}

	/**
	 * @return the joinDate
	 */
	public Date getJoinDate() {
		return joinDate;
	}

	/**
	 * @param joinDate
	 *            the joinDate to set
	 */
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	/**
	 * @return the listOfAddress
	 */
	public Collection<Address> getListOfAddress() {
		return listOfAddress;
	}

	/**
	 * @param listOfAddress the listOfAddress to set
	 */
	public void setListOfAddress(Collection<Address> listOfAddress) {
		this.listOfAddress = listOfAddress;
	}

	
	
	

//	/**
//	 * @return the homeAddress
//	 */
//	public Address getHomeAddress() {
//		return homeAddress;
//	}
//
//	/**
//	 * @param homeAddress
//	 *            the homeAddress to set
//	 */
//	public void setHomeAddress(Address homeAddress) {
//		this.homeAddress = homeAddress;
//	}
//
//	/**
//	 * @return the officeAddress
//	 */
//	public Address getOfficeAddress() {
//		return officeAddress;
//	}
//
//	/**
//	 * @param officeAddress the officeAddress to set
//	 */
//	public void setOfficeAddress(Address officeAddress) {
//		this.officeAddress = officeAddress;
//	}

}
