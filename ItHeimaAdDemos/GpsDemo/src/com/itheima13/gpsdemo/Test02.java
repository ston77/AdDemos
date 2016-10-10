package com.itheima13.gpsdemo;

public class Test02 {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		ModifyOffset mo = ModifyOffset.getInstance(Test02.class
				.getClassLoader().getResourceAsStream("axisoffset.dat"));
		PointDouble pt = new PointDouble(113.9163568f, 22.57919685f);
		PointDouble newpt = mo.s2c(pt);
		System.out.println(">>>>" + newpt);
	}

}
