//打印工具。20230321
/* 多行換行功能 20230324
 * String轉int方法。20230327
 * 20230327new
 * */
package com.example.java_demo_test.time20230323V04Service;


public class FlyKiteTL {
	
	public static <T> void Println(T i)
	{
		System.out.println(i.toString());
	}

	public static void Println(String i)
	{
		System.out.println(i);
	}
	
	public static void Println(boolean i)
	{
		System.out.println(i);
	}

	public static <T> void Log(T i)
	{
		System.out.println(i.toString());
	}
	
	public static void BigLine()//超高換行
	{
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");	
	}
	
	public static void TestT20230326() {
		Log("這是MyTool的tool");
	}

	//字串轉int。失敗傳回666
	public static int StringToInt(String menuNameTry){
		try{
			return Integer.parseInt(menuNameTry);
		}catch(NumberFormatException e){
			return 666;
		}
	}

	//字串轉int。失敗傳回666
	//另外參數boolean[]，會傳回陣列大小為1的boolean值。false是String轉int成功，true是String轉int失敗。
	public static int StringToInt(String menuNameTry, 
			/*使用Array的參考型別的特性，達成類似CSharp中out跟ref的功能*/
			boolean[] isTry){
		//因為一定要初始化，才能作為參數帶入。所以不打算在方法裡做新建陣列。
		//isTry = new boolean[1];
		try{
			isTry[0] = false;

			return Integer.parseInt(menuNameTry);
		}catch(NumberFormatException e){
			
			isTry[0] = true;
			return 666;
		}
	}
	/*操作方法*/
//	boolean[] isTry = new boolean[1];
//	int obtainMoney = MyTool.StringToInt(scanner.next(), isTry);
//	if(isTry[0] == true) {
//	}else {
//	}

}
