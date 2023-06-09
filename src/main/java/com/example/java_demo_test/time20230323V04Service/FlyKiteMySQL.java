//MySQL便利工具。20230409
/* 
 * T20230411V001(new)
 * 完成自訂class新增資料庫的method。
 * 
 * T20230414V001(new)
 * 
 * T20230416(new)
 * 輸出失敗訊息或成功訊息。
 * */
package com.example.java_demo_test.time20230323V04Service;

import com.example.java_demo_test.controller.FlyKiteTL;
import javax.persistence.Table;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.util.StringUtils;

import javax.persistence.Entity;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Column;

public class FlyKiteMySQL<T extends Entity> {
	
	private static String url = "jdbc:mysql://localhost:3301/java_demo_test";

	private static String username = "root";
	
    private static String password = "root";

	//T20230411V001(new)
	public static <T> String AddSave(T x) {
		
		if(isSamePKDataInTable(x)) {
			FlyKiteTL.Log("新增資料重複，請確認後重新操作");
			return "新增資料重複，請確認後重新操作";	
		}
		
		PreparedStatement pstmt = null;
		
		Table tableAnnotation = x.getClass().getAnnotation(Table.class);
				
	     Field[] fields = x.getClass().getDeclaredFields();
	        
	        // 準備 SQL 語句
	        String sql = AddDataMySQL(fields, tableAnnotation);
	        
		    try (Connection conn = 
		    		DriverManager.getConnection(url, username, password)) {
		    	
	            pstmt = conn.prepareStatement(sql);
	            
	            int nowNumber = 1;
	            
		        for (Field field : fields) {
		            Column column = field.getAnnotation(Column.class);
		            if (column != null) {
		                String columnName = column.name();
		                field.setAccessible(true);//開啟私用變數的存取。
		                          FlyKiteTL.Log(columnName);
							try {
								Object columnValue = field.get(x);;
								if (columnValue != null) { // 防止空指针异常
									
									isAbnormalValue(columnValue, pstmt,
											nowNumber);
									
									nowNumber++;//下一個資料
								}
							} catch (IllegalArgumentException | IllegalAccessException e) {
							}
		            }
		        }
		        
	          // 執行 SQL 語句
	          pstmt.executeUpdate();
	          
              if (pstmt != null) {
                  pstmt.close();
              }
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }finally{
	            // 關閉資源
	            try {
	                if (pstmt != null) {
	                    pstmt.close();
	                }
	            } catch (SQLException e) {
	                System.out.println("Error: " + e.getMessage());
	            }
	        }
		    
		    return "新增資料成功";
	}

	public static <T> String ReviseSave(T x) {
		
		if(isSamePKDataInTable(x)) {
		}else {
			FlyKiteTL.Log("執行修改的資料不存在，請確認後重新操作");
			return "執行修改的資料不存在，請確認後重新操作";	
		}
		
		PreparedStatement pstmt = null;
		
		Table tableAnnotation = x.getClass().getAnnotation(Table.class);
				
	     Field[] fields = x.getClass().getDeclaredFields();
	        
	        
		    try (Connection conn = 
		    		DriverManager.getConnection(url, username, password)) {
		    	
		    	 Statement statement = conn.createStatement();
		    	 
		    	 // 獲取 @Table(name = "shop_name") 的 name 屬性值
		    	 String tableName = tableAnnotation.name(); 
		      		//資料庫語法
		    	 String sql = "SHOW KEYS " +
		                     "FROM " + tableName/*<<<*/;
		      ResultSet resultSet = statement.executeQuery(sql);
		      //主鍵名稱
		      String pk_column_name = "";
		      while (resultSet.next()) {
		    	  pk_column_name = resultSet.getString("Column_name");//注意在資料庫的資料型別
		        }
		      
		      resultSet.close();
		      
		      // 準備 SQL 語句
		      sql = ReviseDataMySQL(fields, tableName, pk_column_name);
		      
	            pstmt = conn.prepareStatement(sql);
	            
	            int nowNumber = 1;
	            
	            /*這個地方跟ReviseDataMySQL()使用一樣的fields，所以順序方面都是相同的，
	             * 不會有SQL 語句 資料對應錯誤的問題。
	             * */
		        for (Field field : fields) {
		            Column column = field.getAnnotation(Column.class);
		            if (column != null) {
		                String columnName = column.name();
		                
		                
		                field.setAccessible(true);//開啟私用變數的存取。
		                          FlyKiteTL.Log(columnName);
							try {
								Object columnValue = field.get(x);;
								
									
									if (columnValue != null) { // 防止空指针异常
										
										/*
										 * 想法:其實好像不用跳過主鍵，因為主鍵跟新資料的主鍵是相同的。
										 * 根本不需要做其他多餘的判斷。
										 * 這部分跟新增資料不同
										 * */
										if(pk_column_name.equals(columnName)) {
											isAbnormalValue(columnValue, pstmt,
													fields.length/*因為主鍵資料在最後面*/);
											FlyKiteTL.Log("主鍵");
											continue;//nowNumber++不需要
										}else {
										
											isAbnormalValue(columnValue, pstmt,
													nowNumber);
											nowNumber++;//下一個資料
										}
										
								}
							} catch (IllegalArgumentException | IllegalAccessException e) {
							}
		            }
		        }
		        
	          // 執行 SQL 語句
	          pstmt.executeUpdate();
	          
	          
              if (pstmt != null) {
                  pstmt.close();
              }
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }finally{
	            // 關閉資源
	            try {
	                if (pstmt != null) {
	                    pstmt.close();
	                }
	            } catch (SQLException e) {
	                System.out.println("Error: " + e.getMessage());
	            }
	        }
		    
		    return "修改資料成功";
	}
	

	
	private static String AddDataMySQL(Field[] fields, Table tableAnnotation)
	{
		String tableName = tableAnnotation.name(); // 獲取 @Table(name = "shop_name") 的 name 屬性值

	     String strV001 = "";//dataName
	     //問號數量代表個對應的資料值
	     String strV002 = "";//dataPosition
		
	     boolean isStart = true;
	     
	        for (Field field : fields) {
	            Column column = field.getAnnotation(Column.class);
	            if (column != null) {
	                String columnName = column.name();
	                if(isStart) {//在第一次不加入", "。	
	                	isStart = false;
	                }else {
	                	strV001 += ", ";
	                	strV002 += ", ";
	                }
	                
	                strV001 += columnName;
	                strV002 += "?";
	            }
	        }
	        
	        // 準備 SQL 語句
	        String sql = "INSERT INTO " + tableName/*<<<*//*資料庫tables名稱*/ 
	        		+ " (" + strV001 + /*<<<*/")"
 				+ " VALUES (" + strV002/*<<<*/ + ")";/**/
		
		return sql;
	}
	
	/* 只找尋主鍵。
	 * */
	private static String ReviseDataMySQL(Field[] fields, String _tableName, 
			String pkKey)
	{
		if(fields.length <= 1) {
			return "只有主鍵資料，無法變更。";
		}
		
	     String strV001 = "";//dataName
	     
	        for (int i = 0; i < fields.length; i++) {
	            Column column = fields[i].getAnnotation(Column.class);
	            if (column != null) {
	                //是否為主鍵。
	            	if(pkKey.equals(column.name())) {
	            		continue;
	            	}
	            	
	            	//strV001為null的話代表第一次加入字串，所以直接略過", "
	            	if(StringUtils.hasText(strV001)) {
	            		strV001 += ", " + column.name() + " = ?";
	            	}else {
	            		strV001 += column.name() + " = ?";
	            	}
	            }
	        }
	        
	        // 準備 SQL 語句
	        String sql = "UPDATE " + _tableName/*<<<*//*資料庫tables名稱*/
	        		+ " SET " + strV001 /*<<<*/
	        		+ " WHERE " + pkKey + /*主鍵名稱*/" = ?";
            
	        /*
	         *          String sql = "UPDATE course_data SET 
	         *          name = ?, week = ?, start_time = ?, over_time = ?, credit = ? 
	         *          WHERE course_num = ?";
	         * */
		return sql;
	}

	/*是否判斷資料庫型別的變數，有資料庫型別以外的變數，會直接顯示錯誤。並終止程序運作
	 * 資料庫型別變數:
	 * int
	 * Integer
	 * String
	 * */
	private static void isAbnormalValue(Object columnValue, 
			PreparedStatement _pstmt, int nowNumber) throws SQLException {
			if (columnValue instanceof String) {
				FlyKiteTL.Log("String");
				_pstmt.setString(nowNumber, columnValue.toString());
			} else if (columnValue instanceof Integer) {
				FlyKiteTL.Log("Integer");
				_pstmt.setInt(nowNumber, (Integer)columnValue);
			}else {
				FlyKiteTL.Log("變數 :　" + columnValue.toString()+ 
						" 型別　：　");
				//0//這裡為不同型別暴錯誤
				throw new RuntimeException("偵測到例外型別，停止這次運作");
			}
		}
	
	//T20230411V002(new)\
	//取得全部資料List<Class>返回。
//	public static <T> List<T> findAll(JpaRepository<T, String> jpaRepositoryList){
		public static <T> List<T> 
		findAll(Class<T> clazz) throws Exception{
				
			ResultSet pstmt = null;
			
			List<T> nowData = new ArrayList<T>();
			
        	T tV002= clazz.newInstance();
			
		//這個功能暫時閒置
		//jpaRepositoryList;//這個是Dao腳本。
		
		Table tableAnnotation = tV002.getClass().getAnnotation(Table.class);
				
	     Field[] fields = tV002.getClass().getDeclaredFields();
	     
	     String tableName = tableAnnotation.name(); // 獲取 @Table(name = "shop_name") 的 name 屬性值
	     
	        try(Connection conn = DriverManager.getConnection(url, username, password)) {
	        	Statement stmt = conn.createStatement(
	        			ResultSet.TYPE_SCROLL_INSENSITIVE, 
	        			ResultSet.CONCUR_UPDATABLE);
	        		        	
	            pstmt = stmt.executeQuery(
	            		"SELECT * FROM " + tableName);
	            
	            /*初始化List長度*/
	            int rowCount = 0;

	            while (pstmt.next()) {
	                rowCount++;
	             }
	            
	             System.out.println("共檢索到 " + rowCount + " 條數據。\n");
	             
	             //這裡報錯。
	             pstmt.beforeFirst();
	             
		            while (pstmt.next()) {
		             
		            	/*棄:
		            	 * 雖然可以變成T型別，但是獲得的資料還是Object類型。
		            	 */
		            	//直接用強行別把object轉成T。*/
//		            	T tV001= (T)new Object();

		            	T tV001= clazz.newInstance();
		            	
		            	
		            	int nowNumber = 1;
			            
				        for (Field field : fields) {
				            Column column = field.getAnnotation(Column.class);
				            if (column != null) {
				                String columnName = column.name();
				                field.setAccessible(true);//開啟私用變數的存取。
									try {
										
										if (field.getType().toString().equals("int")) {
											int nowDataV001 = pstmt.
													getInt(columnName/*注意這裡使用的是在資料庫裡資料名稱*/);
											FlyKiteTL.Log(nowDataV001);
											Field xField = tV002.getClass().getDeclaredField(
													field.getName()/*變數名稱*/);
											xField.setAccessible(true);
											
											xField.set(tV001, nowDataV001);

										} else if (field.getType().toString().equals("class java.lang.String")) {
											String nowDataV001 = pstmt.
													getString(columnName/*注意這裡使用的是在資料庫裡資料名稱*/);
											FlyKiteTL.Log(nowDataV001);
											Field xField = tV002.getClass().getDeclaredField(
													field.getName()/*變數名稱*/);
											xField.setAccessible(true);
											
											xField.set(tV001, nowDataV001);
										}else {
					                          FlyKiteTL.Log(field.getName()+ ",\n型別:" + 
					                        		  field.getType().toString());
											//這裡為不同型別暴錯誤
											throw new RuntimeException("偵測到例外型別" +
											field.getType().toString() + "，停止這次運作");
										}
											nowNumber++;//下一個資料
									} catch (IllegalArgumentException e) {
									}
				            }
				        }
				        FlyKiteTL.Log("");
				        nowData.add(tV001);
		            }
	             
		      } catch (SQLException e) {
			        e.printStackTrace();
		      }
		
		return nowData;
	}
	/*
	 * T20230414V001(new)
	 * 判斷是否有相同主鍵資料在資料庫中。
	 * true為有相同主鍵資料在資料庫。
	 * */
	public static <T> boolean isSamePKDataInTable(T x){
		
		PreparedStatement pstmt = null;
				
		Table tableAnnotation = x.getClass().getAnnotation(Table.class);
				
	     Field[] fields = x.getClass().getDeclaredFields();
	     
	     String tableName;
	     try {
	    	 // 獲取 @Table(name = "shop_name") 的 name 屬性值
	    	 tableName = tableAnnotation.name(); 
	     }finally{
		     FlyKiteTL.Log("\n傳入型別錯誤。Entity資料缺少@Table");
	     }
		
		try {
		      Connection connection = DriverManager.getConnection(url, username, password);
		      
		      Statement statement = connection.createStatement();
		      String query = "SHOW KEYS " +//取得主鍵名稱
		                     "FROM " + tableName;
		      String mainKeyName = null;
		      ResultSet resultSet = statement.executeQuery(query);
		      while (resultSet.next()) {
		          mainKeyName = resultSet.getString("Column_name");//注意在資料庫的資料型別
		        }
		      
		      if(!StringUtils.hasText(mainKeyName) && mainKeyName.equals("")) {
		    	  FlyKiteTL.Log("主鍵無資料");
		    	  return true;
		      }
		      
		      //=====================================
		      //取得主鍵的類型。
		      /*用這個決定目前資料的類型。
		       * INT:4
		       * VARCHAR:12
		       * 錯誤資料型別或取得失敗:0
		       * */
		      int nowData = 0;
		      
		      pstmt = connection.prepareStatement(
		    		  "SELECT * FROM "+ tableName
		    		  );
		      
		      resultSet = pstmt.executeQuery();
		      
		      ResultSetMetaData dbmd = resultSet.getMetaData();
		      
		      int columnCount = dbmd.getColumnCount();
		      
		      for(int i = 1; i <= columnCount; i++) {
		    	  
		    	  String databaseName = dbmd.getColumnName(i);
		    	  if(!mainKeyName.equals(databaseName)) {continue;}
		    	  
		    	  String databaseTypeV002 = dbmd.getColumnTypeName(i);/*獲得資料庫中Columns第2個資料類型*/
		    	  
		    	  //判斷要存入的型別。使用代號的方式。
		    	  nowData = dbmd.getColumnType(i);
		    	  
		    	  break;
		      }
		      		  
		      //目前主鍵資料值
		      Object columnValue = null;
		      	            
		        for (Field field : fields) {
		            Column column = field.getAnnotation(Column.class);
		            if (column != null &&
		            		(mainKeyName.equals(column.name()))) {
		            	
		                field.setAccessible(true);//開啟私用變數的存取。
		                          
							try {
								columnValue = field.get(x);
						        if (columnValue == null) { // 防止空指针异常
						        	//在class找不到相同名稱的資料名稱
						        	throw new RuntimeException(""
						        			+ "在class找不到相同名稱的資料名稱");
						        }
						        	break;
							} catch (IllegalArgumentException | IllegalAccessException e) {
							}
		            }
		        }
		        
			      Connection conn = DriverManager.getConnection(url, username, password);
			        Statement stmt = conn.createStatement();
			        ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName);
			        
				      
			      while(rs.next()) {
			    	  if(nowData == 4) {
			    		  FlyKiteTL.Log(rs.getInt(mainKeyName));
			    		  if(((Integer)columnValue).equals(
			    				  rs.getInt(mainKeyName)))
			    		  {
			    			  FlyKiteTL.Log("有相同資料值。");
			    			  return true;
			    		  }
			    	  }else if(nowData == 12) {
			    		  FlyKiteTL.Log(rs.getString(mainKeyName/*mainKeyName*/));
			    		  if(columnValue.toString().equals(
			    				  rs.getString(mainKeyName)))
			    		  {
			    			  FlyKiteTL.Log("有相同資料值。");
			    			  return true;
			    		  }
			    	  }
			      }
			      FlyKiteTL.Log("無重複資料值可進行新增");
		      
		        resultSet.close();
		        statement.close();
		        connection.close();
		        
		      } catch (SQLException e) {
		        e.printStackTrace();
				return true;
		      }
		return false;
	}
	
	/*true為有相同主鍵資料在資料庫。，*/
	public static <T> void testMySQL(T x){
		
		try {
	      Connection connection = DriverManager.getConnection(url, username, password);
	      
	      DatabaseMetaData metaData = connection.getMetaData();
	      ResultSet pkResultSet = metaData.getPrimaryKeys(null, "%", "test_table");
	      
	      
	      while (pkResultSet.next()) {
	          
	          FlyKiteTL.Log(pkResultSet.getString("TYPE_NAME"));
	          
	      }
		} catch (SQLException e) {
		    e.printStackTrace();
		}	
		}
		
	
	public static <T> void 
	deleteData(T x){
			
		PreparedStatement pstmt = null;
		
		Table tableAnnotation = x.getClass().getAnnotation(Table.class);
				
	     Field[] fields = x.getClass().getDeclaredFields();
	     
	     String tableName;
	     try {
	    	 // 獲取 @Table(name = "shop_name") 的 name 屬性值
	    	 tableName = tableAnnotation.name(); 
	     }finally{
		     FlyKiteTL.Log("\n傳入型別錯誤。Entity資料缺少@Table");
	     }
		
		try {
		      Connection connection = DriverManager.getConnection(url, username, password);
		      
		      Statement statement = connection.createStatement();
		      String query = "SHOW KEYS " +//取得主鍵名稱
		                     "FROM " + tableName;
		      String mainKeyName = null;
		      ResultSet resultSet = statement.executeQuery(query);
		      while (resultSet.next()) {
		          mainKeyName = resultSet.getString("Column_name");//注意在資料庫的資料型別
		        }
		      
		      if(!StringUtils.hasText(mainKeyName) && mainKeyName.equals("")) {
		    	  FlyKiteTL.Log("無主鍵名稱");
		    	  return;
		      }
		      
		      //=====================================
		      //取得主鍵的類型。
		      /*用這個決定目前資料的類型。
		       * INT:4
		       * VARCHAR:12
		       * 錯誤資料型別或取得失敗:0
		       * */
		      int nowData = 0;
		      
		      pstmt = connection.prepareStatement(
		    		  "SELECT * FROM "+ tableName
		    		  );
		      
		      resultSet = pstmt.executeQuery();
		      
		      ResultSetMetaData dbmd = resultSet.getMetaData();
		      
		      int columnCount = dbmd.getColumnCount();
		      
		      for(int i = 1; i <= columnCount; i++) {
		    	  
		    	  String databaseName = dbmd.getColumnName(i);
		    	  if(!mainKeyName.equals(databaseName)) {continue;}
		    	  
		    	  String databaseTypeV002 = dbmd.getColumnTypeName(i);/*獲得資料庫中Columns第2個資料類型*/
		    	  
		    	  //判斷要存入的型別。使用代號的方式。
		    	  nowData = dbmd.getColumnType(i);
		    	  
		    	  break;
		      }
		      		  
		      //目前主鍵資料值
		      Object columnValue = null;
		      	            
		        for (Field field : fields) {
		            Column column = field.getAnnotation(Column.class);
		            if (column != null &&
		            		(mainKeyName.equals(column.name()))) {
		            	
		                field.setAccessible(true);//開啟私用變數的存取。
		                          
							try {
								columnValue = field.get(x);
						        if (columnValue == null) { // 防止空指针异常
						        	//在class找不到相同名稱的資料名稱
						        	throw new RuntimeException(""
						        			+ "在class找不到相同名稱的資料名稱");
						        }
						        	break;
							} catch (IllegalArgumentException | IllegalAccessException e) {
							}
		            }
		        }
		        
			      Connection conn = DriverManager.getConnection(url, username, password);
			        Statement stmt = conn.createStatement();
			        ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName);
			        
			        String hasmainKeyData = "";
			        
			      while(rs.next()) {
			    	  if(nowData == 4) {
			    		  FlyKiteTL.Log("number:" + rs.getInt(mainKeyName));
			    		  if(((Integer)columnValue).equals(
			    				  rs.getInt(mainKeyName)))
			    		  {
			    			  hasmainKeyData = columnValue.toString();
			    			  FlyKiteTL.Log("有相同資料值。");
			    			  break;
			    		  }
			    	  }else if(nowData == 12) {
			    		  FlyKiteTL.Log("number:" + 
			    	  rs.getString(mainKeyName/*mainKeyName*/));
			    		  if(columnValue.toString().equals(
			    				  rs.getString(mainKeyName)))
			    		  {
			    			  hasmainKeyData = columnValue.toString();
			    			  FlyKiteTL.Log("有相同資料值。");
			    			  break;
			    		  }
			    	  }
			      }
			      
			      if(!StringUtils.hasText(hasmainKeyData)) {
			    	  FlyKiteTL.Log("主鍵名稱:" + mainKeyName + 
			    			  "資料為" + hasmainKeyData);
			    	  FlyKiteTL.Log("無重複資料值刪除資料失敗");
			    	  return;
			      }
			      
			      FlyKiteTL.Log(tableName);
			      FlyKiteTL.Log(mainKeyName);
			      FlyKiteTL.Log(hasmainKeyData);

			      String sql = "DELETE FROM " + tableName + " WHERE " + mainKeyName + 
			    		  " = " + hasmainKeyData;
			      
			       // 執行刪除語句
		            int rowsAffected = stmt.executeUpdate(sql);
		            System.out.println("刪除成功，影響了 " + rowsAffected + " 行資料。");
			      
		        resultSet.close();
		        statement.close();
		        connection.close();
		        
		      } catch (SQLException e) {
		        e.printStackTrace();
		    	  FlyKiteTL.Log("錯誤");
				return;
		      }
    	}
}
