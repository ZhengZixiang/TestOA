package me.zzx.oa.model;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.Set;

import org.hibernate.Session;

import org.junit.Test;

import me.zzx.oa.util.HibernateUtil;

public class OrgnizationTest {

	@Test
	public void testSaveOrgnization() {
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		
		Orgnization org1 = new Orgnization();
		org1.setName("org1");
		
		Orgnization org2 = new Orgnization();
		org2.setName("org2");
		org1.getChildren().add(org2);
		
		Orgnization org3 = new Orgnization();
		org3.setName("org3");
		org1.getChildren().add(org3);
		
		Orgnization org4 = new Orgnization();
		org4.setName("org4");
		org1.getChildren().add(org4);
		org2.setParent(org1);
		org3.setParent(org1);
		org4.setParent(org1);
		
		session.save(org1);
		
		session.getTransaction().commit();
	}

	@Test
	public void testLoadOrg1() {
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		
		Orgnization org1 = session.load(Orgnization.class, 1);
		System.out.println(org1.getName() + " has children: ");
		Set<Orgnization> set = org1.getChildren();
		for(Iterator<Orgnization> it = set.iterator(); it.hasNext(); ) {
			Orgnization org = it.next();
			System.out.println(org.getName());
		}
		
		session.getTransaction().commit();
	}
	
	@Test
	public void testLoadOrg2() {
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		
		Orgnization org2 = session.load(Orgnization.class, 2);
		System.out.println(org2.getName() + "," + org2.getParent().getName());
		
		session.getTransaction().commit();
	}
	
	@Test
	public void testUpdateOrg1() {
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		Orgnization org1 = new Orgnization();
		org1.setId(1);
		org1.setName("test");
		session.update(org1);
		session.getTransaction().commit();
	}
}
