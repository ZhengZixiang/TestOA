package me.zzx.oa.manager;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import me.zzx.oa.manager.impl.InitSystemDatasImpl;

public class InitSystemDatasTest extends InitSystemDatasImpl {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testAddOrUpdateInitDatas() {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String []{"beans.xml", "activiti-context.xml"});
		InitSystemDatas init = context.getBean("initSystemDatas", InitSystemDatas.class);
		init.addOrUpdateInitDatas("init_datas.xml");
		((ConfigurableApplicationContext) context).close();
	}
}
