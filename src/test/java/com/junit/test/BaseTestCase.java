package com.junit.test;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
/**
 * 
 * @author tangkuo
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring-mvc.xml"})
public abstract class BaseTestCase {

	@BeforeClass
	public static void beforeClass() {
	}
	
	@Before
	public void init() {
		System.out.println("\n\nstart test --> ");
	}

	@After
	public void destory() {
		System.out.println("end test --> \n\n");
	}
	
}
