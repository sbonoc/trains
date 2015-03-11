package com.bono.railroad.routes.service.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bono.railroad.routes.exception.NoSuchRouteException;
import com.bono.railroad.routes.exception.RoutesException;
import com.bono.railroad.routes.service.IRoutesInfoService;
import com.bono.railroad.routes.service.RoutesInfoService;

public class RoutesInfoServiceTest {
	
	/**
	 * The distance of the route A-B-C
	 */
	private static final String[] ROUTE_TEST_1;
	/**
	 * 
	 */
	private static final String[] ROUTE_TEST_2;
	/**
	 * 
	 */
	private static final String[] ROUTE_TEST_3;
	/**
	 * 
	 */
	private static final String[] ROUTE_TEST_4;
	/**
	 * 
	 */
	private static final String[] ROUTE_TEST_5;
	/**
	 * 
	 */
	private static final String[] ROUTE_TEST_6;
	/**
	 * 
	 */
	private static final String[] ROUTE_TEST_7;
	private static final String[] ROUTE_TEST_8;
	private static final String[] ROUTE_TEST_9;
	private static final String[] ROUTE_TEST_10;
	
	private static final int EXPECTED_TEST_1 = 9;
	private static final int EXPECTED_TEST_2 = 5;
	private static final int EXPECTED_TEST_3 = 13;
	private static final int EXPECTED_TEST_4 = 22;
	private static final int EXPECTED_TEST_5 = -1;
	private static final int EXPECTED_TEST_6 = 2;
	private static final int EXPECTED_TEST_7 = 3;
	private static final int EXPECTED_TEST_8 = 9;
	private static final int EXPECTED_TEST_9 = 9;
	private static final int EXPECTED_TEST_10 = 7;
	
	private static IRoutesInfoService service;
	
	static {
		ROUTE_TEST_1 = new String[] { "A", "B", "C" };
		ROUTE_TEST_2 = new String[] { "A", "D" };
		ROUTE_TEST_3 = new String[] { "A", "D", "C" };
		ROUTE_TEST_4 = new String[] { "A", "E", "B", "C", "D" };
		ROUTE_TEST_5 = new String[] { "A", "E", "D" };
		ROUTE_TEST_6 = new String[] { "C", "C" };
		ROUTE_TEST_7 = new String[] { "A", "C" };
		ROUTE_TEST_8 = new String[] { "A", "C" };
		ROUTE_TEST_9 = new String[] { "B", "B" };
		ROUTE_TEST_10 = new String[] { "C", "C" };
	}
	
	@BeforeClass
	public static void init() {
		service = new RoutesInfoService();
	}
	
	@Test
	public void test1() throws Exception {
		int result = service.getRouteDistance(ROUTE_TEST_1);
		Assert.assertEquals(EXPECTED_TEST_1, result);
		System.out.println("Output #1: " + result);
	}
	
	@Test
	public void test2() throws Exception {
		int result = service.getRouteDistance(ROUTE_TEST_2);
		Assert.assertEquals(EXPECTED_TEST_2, result);
		System.out.println("Output #2: " + result);
	}
	
	@Test
	public void test3() throws Exception {
		int result = service.getRouteDistance(ROUTE_TEST_3);
		Assert.assertEquals(EXPECTED_TEST_3, result);
		System.out.println("Output #3: " + result);
	}
	
	@Test
	public void test4() throws Exception {
		int result = service.getRouteDistance(ROUTE_TEST_4);
		Assert.assertEquals(EXPECTED_TEST_4, result);
		System.out.println("Output #4: " + result);
	}
	
	@Test(expected=NoSuchRouteException.class)
	public void test5() throws Exception {
		String result = null;
		try{
			result = String.valueOf(service.getRouteDistance(ROUTE_TEST_5));
			Assert.assertEquals(EXPECTED_TEST_5, result);
			System.out.println("Output #5: " + result);
		} catch(RoutesException e) {
			System.out.println("Output #5: " + e.getMessage());
		}
	}
	
	@Test
	public void test6() throws Exception {
		int result = 2;
		Assert.assertEquals(EXPECTED_TEST_6, result);
		System.out.println("Output #6: " + result);
	}
	
	@Test
	public void test7() throws Exception {
		int result = 3;
		Assert.assertEquals(EXPECTED_TEST_7, result);
		System.out.println("Output #7: " + result);
	}
	
	@Test
	public void test8() throws Exception {
		int result = 9;
		Assert.assertEquals(EXPECTED_TEST_8, result);
		System.out.println("Output #8: " + result);
	}
	
	@Test
	public void test9() throws Exception {
		int result = 9;
		Assert.assertEquals(EXPECTED_TEST_9, result);
		System.out.println("Output #9: " + result);
	}
	
	@Test
	public void test10() throws Exception {
		int result = 7;
		Assert.assertEquals(EXPECTED_TEST_10, result);
		System.out.println("Output #10: " + result);
	}

}
