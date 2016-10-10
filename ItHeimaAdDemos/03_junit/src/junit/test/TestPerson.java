package junit.test;

import com.itheima.junit.Person;

import android.test.AndroidTestCase;

public class TestPerson extends AndroidTestCase{
	
	public void test1(){
		
		Person p = new Person();
		int result = p.add(1, 2);
		
		System.out.println(result);
	}
	
	public void test2(){
		Person p = new Person();
		int result = p.divide(4, 1);
		System.out.println(result);
	}
	
}
