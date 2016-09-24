package me.zzx.oa.manager;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import me.zzx.oa.manager.impl.OrgManagerImpl;
import me.zzx.oa.model.Orgnization;

public class OrgManagerTest extends OrgManagerImpl {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testAddOrg() {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		OrgManager manager = context.getBean("orgManager", OrgManager.class);
		Orgnization org = new Orgnization();
		org.setName("²âÊÔ»ú¹¹");
		org.setDescription("test");
		manager.addOrg(org, 0);
		((ConfigurableApplicationContext) context).close();
	}
}
