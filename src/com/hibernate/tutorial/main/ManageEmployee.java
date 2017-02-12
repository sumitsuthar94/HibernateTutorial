package com.hibernate.tutorial.main;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.hibernate.tutorial.entity.Address;
import com.hibernate.tutorial.entity.Employee;

public class ManageEmployee {

	private static SessionFactory factory;

	public static void main(String[] args) {

		try {
			Configuration cfg = new Configuration();
			cfg.configure("hibernate.cfg.xml");
			factory = cfg.buildSessionFactory();
		} catch (Throwable ex) {
			System.out.println("Failed to create session factory object " + ex);
			throw new ExceptionInInitializerError(ex);
		}
		ManageEmployee me = new ManageEmployee();
		 Integer empId1 = me.addEmployee("sumit", "suthar", 2323000);
		// Integer empId2 = me.addEmployee("foo", "bar", 523223);

		Address adr1 = me.addAddress("udaipur", "Rajasthan", 313002);
		System.out.println("adddres: "+adr1);
		Integer empId3 = me.addEmployeeAddress("foo", "bar", 2323323, adr1);

		// me.listEmployees();
		// me.update(empId1, 565565);
		// me.listEmployees();
		// me.delete(empId2);
		me.listEmployees();
		me.listAddress();
	}

	public Integer addEmployee(String fname, String lname, int salary) {
		Session session = factory.openSession();
		Transaction tx = null;
		Integer employeeId = null;
		try {
			tx = session.beginTransaction();
			Employee employee = new Employee(fname, lname, salary);
			employeeId = (Integer) session.save(employee);
			tx.commit();
		} catch (HibernateException he) {
			if (tx != null) {
				tx.rollback();
			}
			he.printStackTrace();
		} finally {
			session.close();
		}
		return employeeId;
	}

	public Integer addEmployeeAddress(String fname, String lname, int salary, Address address) {
		Session session = factory.openSession();
		Transaction tx = null;
		Integer employeeId = null;
		try {
			tx = session.beginTransaction();
			Employee employee = new Employee(fname, lname, salary, address);
			employeeId = (Integer) session.save(employee);
			tx.commit();
		} catch (HibernateException he) {
			if (tx != null) {
				tx.rollback();
			}
			he.printStackTrace();
		} finally {
			session.close();
		}
		return employeeId;
	}

	public void listEmployees() {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List employees = session.createQuery("From Employee").list();
			for (Iterator it = employees.iterator(); it.hasNext();) {
				Employee employee = (Employee) it.next();
				Address adr = employee.getAddress();
				System.out.println(adr);
				System.out.println(employee.getId() + " " + employee.getFirstName() + " " + employee.getLastName() + " "
						+ employee.getSalary());
			}
			tx.commit();
		} catch (HibernateException he) {
			if (tx != null) {
				tx.rollback();
			}
			he.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void update(Integer id, int salary) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Employee employee = session.get(Employee.class, id);
			employee.setSalary(salary);
			session.update(employee);
			tx.commit();
		} catch (HibernateException he) {
			if (tx != null) {
				tx.rollback();
			}
			he.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void delete(Integer id) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Employee employee = session.get(Employee.class, id);
			session.delete(employee);
			tx.commit();
		} catch (HibernateException he) {
			if (tx != null) {
				tx.rollback();
			}
			he.printStackTrace();
		} finally {
			session.close();
		}
	}

	public Address addAddress(String city, String state, int zipcode) {
		Session session = factory.openSession();
		Transaction tx = null;
		Integer AddressId = null;
		Address adr = null;
		try {
			tx = session.beginTransaction();
			adr = new Address(city, state, zipcode);
			AddressId = (Integer) session.save(adr);
			tx.commit();
		} catch (HibernateException he) {
			if (tx != null) {
				tx.rollback();
			}
			he.printStackTrace();
		} finally {
			session.close();
		}
		return adr;
	}
	
	public void listAddress(){
		Session session  = factory.openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			List adr = session.createQuery("From Address").list();
			for(Iterator it = adr.iterator();it.hasNext();){
				Address  add = (Address)it.next();
				System.out.println(add.getCity()+" "+add.getState()+" "+add.getZipcode());
			}
		}
		catch(HibernateException he){
			if(tx!=null){
				tx.rollback();
			}
			he.printStackTrace();
		}
		finally{
			session.close();
		}
	}

}
