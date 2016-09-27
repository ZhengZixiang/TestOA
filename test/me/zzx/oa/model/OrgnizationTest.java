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
		
		Organization org1 = new Organization();
		org1.setName("org1");
		
		Organization org2 = new Organization();
		org2.setName("org2");
		org1.getChildren().add(org2);
		
		Organization org3 = new Organization();
		org3.setName("org3");
		org1.getChildren().add(org3);
		
		Organization org4 = new Organization();
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
		
		Organization org1 = session.load(Organization.class, 1);
		System.out.println(org1.getName() + " has children: ");
		Set<Organization> set = org1.getChildren();
		for(Iterator<Organization> it = set.iterator(); it.hasNext(); ) {
			Organization org = it.next();
			System.out.println(org.getName());
		}
		
		session.getTransaction().commit();
	}
	
	@Test
	public void testLoadOrg2() {
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		
		Organization org2 = session.load(Organization.class, 2);
		System.out.println(org2.getName() + "," + org2.getParent().getName());
		
		session.getTransaction().commit();
	}
	
	@Test
	public void testUpdateOrg1() {
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		Organization org1 = new Organization();
		org1.setId(1);
		org1.setName("test");
		session.update(org1);
		session.getTransaction().commit();
	}
}
