package com.hibernate.tutorial.dao;

import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.hibernate.tutorial.dto.Address;
import com.hibernate.tutorial.dto.Person;

public class ManagePerson {

	private static SessionFactory factory;
	public static void main(String[] args) {
		try{
		factory = new Configuration().configure().buildSessionFactory();
		}
		catch(Throwable ex){
			System.out.println("Failed to create factory:"+ex);
			throw new ExceptionInInitializerError(ex);
		}
		ManagePerson mp = new ManagePerson();
		mp.addPerson("sumit","suthar",5623);
		mp.addPerson("foo","bar",5632);
		mp.getData();
	}
	public Integer addPerson(String fname,String lname,int salary){
		Session session  = factory.openSession();
		Transaction tx = null;
		Integer personId = null;
		try{
			tx = session.beginTransaction();
			Person person  = new Person();
			person.setFirstName(fname);
			person.setLastName(lname);
			person.setSalary(salary);
			person.setJoinDate(new Date());
			
			Address adr1 = new Address();
			adr1.setCity("udaipur");
			adr1.setState("Rajasthan");
			adr1.setPincode("5656");
			
			Address adr2 = new Address();
			adr2.setCity("Jaipur");
			adr2.setState("Rajasthan");
			adr2.setPincode("3323");
			
			person.getListOfAddress().add(adr1);
			person.getListOfAddress().add(adr2);
			
			//person.setHomeAddress(adr1);
			//person.setOfficeAddress(adr2);
			session.save(person);
			tx.commit();
			
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
		return personId;
	}
	public void getData(){
		Session session = factory.openSession();
		Transaction tx = null;
		try{
		tx = session.beginTransaction();
		Person person = session.get(Person.class, 1);
	//	Address adr = person.getHomeAddress();
		System.out.println(person.getFirstName()+" "+person.getLastName()+" "+person.getSalary()+" "+person.getJoinDate());
		
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
