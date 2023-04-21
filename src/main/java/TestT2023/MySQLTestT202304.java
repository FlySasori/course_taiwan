package TestT2023;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

import aj.org.objectweb.asm.Type;

/*
 * 怎麼new Generics 的作法。
 * */
public class MySQLTestT202304 <T extends Entity> {
		
	public static <T> List<T> findAll(Class<T> clazz) throws Exception{
		
		List<T> tList = new ArrayList<T>();
    	//直接用強行別把object轉成T。
    	T tV001= clazz.newInstance();
    	    	
    	tList.add(tV001);
    	tList.add(tV001);
    	tList.add(tV001);

    	return tList;
	}
}
