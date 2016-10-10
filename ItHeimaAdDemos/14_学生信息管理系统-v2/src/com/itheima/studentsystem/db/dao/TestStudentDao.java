package com.itheima.studentsystem.db.dao;

import android.test.AndroidTestCase;

public class TestStudentDao extends AndroidTestCase{

	public void test1(){
		
		StudentDao sdao = new StudentDao(getContext());
		
		for (int i = 0; i < 2000; i++) {
			
			sdao.insert("lss"+i, "female");
		}
	}
	public void test2(){
		
		StudentDao sdao = new StudentDao(getContext());
		
		sdao.delete("zs");
	}
	
	public void test3(){
		
		StudentDao sdao = new StudentDao(getContext());
		
		sdao.update("zs", "male");
	}
	public void test4(){
		
		StudentDao sdao = new StudentDao(getContext());
		
		String sex = sdao.findOne("zs");
		System.out.println("sex : " + sex);
	}
}
