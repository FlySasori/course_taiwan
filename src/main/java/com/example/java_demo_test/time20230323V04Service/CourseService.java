package com.example.java_demo_test.time20230323V04Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.java_demo_test.controller.FlyKiteTL;
import com.example.java_demo_test.reposopory.CourseReposopory;
import com.example.java_demo_test.time20230323V04Entity.CourseEntity;
import com.example.java_demo_test.time20230323V04Entity.PersonInfoT20230330;
import com.example.java_demo_test.time20230323V04Entity.StudentEntily;
import com.example.java_demo_test.time20230323V04Entity.Student_and_courseEntity;
import com.example.java_demo_test.time20230323V04Interface.CourseInterface;
import com.example.java_demo_test.vo.CourseRequest;
import com.example.java_demo_test.vo.CourseResponse;
import com.example.java_demo_test.vo.StudentRequest;
import com.example.java_demo_test.vo.StudentResponse;

@Service
public class CourseService implements CourseInterface{

	@Autowired
	CourseReposopory courseReposopory;
	
	@Override
	public CourseResponse addCourse(CourseRequest courseRequest) {
		
		String strMassage = GetIsMassage(courseRequest);
		if(strMassage != "") {
			return new CourseResponse(strMassage);
		}
		
		CourseEntity courseEntity = new CourseEntity(
				courseRequest.getCourse_num(),
				courseRequest.getName(),
				courseRequest.getWeek(),
				courseRequest.getStart_time(),
				courseRequest.getOver_time(),
				courseRequest.getCredit());
		
		
		CourseResponse courseResponse = new CourseResponse("新增課程成功。");
		
		return new CourseResponse(
				FlyKiteMySQL.AddSave(courseEntity));/*//point2:
				再調用FlyKiteMySQL.AddSave，會自動輸出是否資料重複String訊息，
				或新增成功訊息。
				注意:
				因為課程代碼是主鍵，所以調用方法時自動判斷。
				＝＝＝＝＝＝＝＝(日本語)＝＝＝＝＝＝＝＝
				FlyKiteMySQL.AddSaveを再度呼び出すと、
				データが重複しているかどうかの文字列メッセージが自動的に出力されます。
				また、追加が成功した場合はメッセージが表示されます。
				注意：
				課程コードは主キーであるため、メソッドを呼び出す際に自動的に判定されます。
				*/
		
	}

	/* 
	 * 1.
	 * 所有數值的正確性判斷。
	 * 2.
	 * 輸出""的話，代表所有判斷無錯誤，可使用
	 //＝＝＝＝＝＝＝＝(日本語)＝＝＝＝＝＝＝＝
	 * １．
	 * すべての数値の正確性判断。
	 * ２．
	 * 「""」と出力された場合、すべての判断が正確であり、使用できます。
	 * */
	private String GetIsMassage(CourseRequest _courseRequest) {
		//課程代碼可否使用判斷。
		 //＝＝＝＝＝＝＝＝(日本語)＝＝＝＝＝＝＝＝
		//課程コードは判断に使用できますか。
		if(/*小於等於0判斷*/
				_courseRequest.getCourse_num() <= 0){
			return "課程代號不可等於小於0。";
		}//課程代碼重複判斷(略)point2:
		
		
		//課程名稱可否使用判斷。
		if(!StringUtils.hasText(_courseRequest.getName())/*null判斷*/) {
			return "課程名稱不可為空。";
		}
		
		//星期可否使用判斷。
		if(_courseRequest.getWeek() <= 0 || _courseRequest.getWeek() > 7
				/*小於等於0判斷 和 大於7*/) {
			return "上課星期需要在1到7的數值。";
		}
		//開始時間可否使用判斷。
		String strMassage = GeTimeStringError(_courseRequest.getStart_time(),
				"getStart_time()");
		if(strMassage != "") {
			return strMassage;
		}
		
		//結束時間可否使用判斷。
		strMassage = GeTimeStringError(_courseRequest.getOver_time(),
				"getOver_time()");
		if(strMassage != "") {
			return strMassage;
		}else if(_courseRequest.getStart_time().
				equals(_courseRequest.getOver_time())
				/*結束時間不可和開始時間相同*/) {
			return "結束時間不可和開始時間相同。";
		}
		//學分可否使用判斷。
		if(_courseRequest.getCredit() < 1 || 
				_courseRequest.getCredit() >= 3
				/*學分需要在1到3的數值*/) {
			return "學分需要在1到3的數值。";
		}
		
		return "";
	}
	
	/*輸出""的話，代表時間判斷無錯誤，可使用*/
	private String GeTimeStringError(String _time, String isTiemName) {
		
		if(!StringUtils.hasText(_time)/*null判斷*/) {
			return isTiemName + "不可為空。";
		}else if(_time.length() != 4
				/*開始時間為4個字符*/) {
			return isTiemName + "需要為4個字符。";
		}else if(_time.matches(".*[^\\d].*")
				/*開始時間只能為數字*/) {
			return isTiemName + "只能為數字。";
		}else if(Integer.parseInt(_time.
				substring(0, 2)) >= 24 
				/* 開始時間小時位數不能等於大於24小時。*/
				) {
			return isTiemName + "小時位數不能等於大於24小時。";
		}else if(Integer.parseInt(_time.
				substring(2, 4)) > 59
				/* 開始時間分鐘位數不能等於大於60分鐘*/
				) {
			return isTiemName + "分鐘位數不能等於大於60分鐘。";
		}
		return "";
	}

	//不考慮重複的資料，就算全部的資料重複也會修執行完成。省去判斷每個資料是否相同的判斷。
	@Override
	public CourseResponse reviseCourse(CourseRequest courseRequest) {
		
		String strMassage = GetIsMassage(courseRequest);
		if(strMassage != "") {
			return new CourseResponse(strMassage);
		}
		
		CourseEntity courseEntity = new CourseEntity(
				courseRequest.getCourse_num(),
				courseRequest.getName(),
				courseRequest.getWeek(),
				courseRequest.getStart_time(),
				courseRequest.getOver_time(),
				courseRequest.getCredit());
		
		if(!FlyKiteMySQL.isSamePKDataInTable(courseEntity)) {
			return new CourseResponse("資料庫中無此課程代碼資料");
		}
		
		return new CourseResponse(
				FlyKiteMySQL.ReviseSave(courseEntity)
				);
	}

	@Override
	public CourseResponse findCourse_num(CourseRequest courseRequest) {
		
		List<CourseEntity> courseEntityList = new ArrayList<>();
		
		try {
			courseEntityList = 
					FlyKiteMySQL.findAll(CourseEntity.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		
		
		return findCourse_numHasGetCourseEntityList(courseRequest, 
				courseEntityList);
	}

	//回傳CourseEntity資料庫資料
	private CourseResponse findCourse_numHasGetCourseEntityList
	(CourseRequest courseRequest, List<CourseEntity> courseEntityListV001) {
//		courseEntityListV001 = new ArrayList<>();
		
		if(courseRequest.getCourse_num() <= 0) {
			return new CourseResponse("課程代號不可等於小於0。");
		}
		
		
		for(int i = 0; i < courseEntityListV001.size(); i++) {
			CourseEntity courseEntityV001 = courseEntityListV001.get(i);
			//找尋相同課程代碼。
			if(courseRequest.getCourse_num() ==  
					courseEntityV001.getCourse_num()) {
				return new CourseResponse(courseRequest.getCourse_num(),
						courseEntityV001.getName(),
						courseEntityV001.getWeek(),
						courseEntityV001.getStart_time(),
						courseEntityV001.getOver_time(),
						courseEntityV001.getCredit());
			}
		}
		
		return new CourseResponse("無資料");
	}
	
	//找尋符合課程名稱之課程
	@Override//注意課程名稱可能會有重複。
	public CourseResponse findCourseName(CourseRequest courseRequest) {
		List<CourseEntity> courseEntitys = new ArrayList<>();
		
		if(!StringUtils.hasText(courseRequest.getName())/*null判斷*/) {
			return new 
					CourseResponse(courseRequest.getName() + "不可為空。");
		}
		
		try {
			CourseEntity courseEntityV001 = new CourseEntity();
			courseEntitys = FlyKiteMySQL.findAll(CourseEntity.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		
		List<CourseEntity> courseEntityListV01 = new ArrayList<>();
		
		for(int i = 0; i < courseEntitys.size(); i++) {
			CourseEntity courseEntityV001 = courseEntitys.get(i);
			//找尋相同課程代碼。
			if(courseRequest.getName().equals(
					courseEntityV001.getName())){
				courseEntityListV01.add(courseEntityV001);
			}
		}
		
		if(courseEntityListV01.size() == 0) {
			return new CourseResponse("無資料");	
		}
		
		return new CourseResponse(courseEntityListV01);
		
		}
	
	
	
	@Override
	public CourseResponse courseSelection(CourseRequest courseRequest) {
		
		//學生資料的學號跟名稱在這裏面
		CourseResponse courseResponseV001 = 
				FindStudent_num(courseRequest);
		
		//如果Message()不是null的話，代表獲取失敗。找不到課程代碼。
		if(StringUtils.hasText(courseResponseV001.getMessage())) {
			return courseResponseV001;
		}
		
		
		//CourseEntity資料庫
		List<CourseEntity> courseEntityListV001 = new ArrayList<>();
		
		try {
			courseEntityListV001 = 
					FlyKiteMySQL.findAll(CourseEntity.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		
		CourseResponse courseResponseV002= 
				findCourse_numHasGetCourseEntityList(
						courseRequest, courseEntityListV001);
		
		//如果Message()不是null的話，代表獲取失敗。找不到課程代碼。
		if(StringUtils.hasText(courseResponseV002.getMessage())) {
			return courseResponseV002;
		}
		
		List<Student_and_courseEntity> student_and_courseList = new ArrayList<>();
		try {
			student_and_courseList = 
					FlyKiteMySQL.findAll(Student_and_courseEntity.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//相同課程判斷
		for(Student_and_courseEntity student_and_course : student_and_courseList) {
			/*找到自己的學號*/
			if(courseResponseV001.getStudent_num() != 
					student_and_course.getStudent_num()) {
				continue;//跳過其他人的學號
			}
			
			/*用自己的課程代碼判斷之前有沒有選過同樣課程代號的課程*/
			if(courseRequest.getCourse_num() == 
					student_and_course.getCourse_num()
					) {
				//刪除此課程
				FlyKiteMySQL.deleteData(student_and_course);
				return new CourseResponse(courseRequest.getCourse_num() + 
						"為已選過課程，執行退選");
			}
		}
		
		//課程滿人判斷。
		int nowDataCount = 0;
		
		for(Student_and_courseEntity student_and_courseV001 : 
			student_and_courseList) {
			
			/*用自己的課程代碼判斷之前有沒有選過同樣課程代號的課程*/
			if(courseRequest.getCourse_num() != 
					student_and_courseV001.getCourse_num()
					) {
				continue;
			}
			
			nowDataCount++;
			
			if(nowDataCount >= 3) {
				return new CourseResponse(courseRequest.getCourse_num() + 
						"已滿人");
			}
		}
		
		//學生學分上限總和10分判斷
		CourseResponse courseResponseV003 = 
				isStudentCreditMax(courseEntityListV001, 
						student_and_courseList, 
						courseResponseV002.getCredit(),
						courseRequest.getStudent_num());
				//不是null的話加選失敗。
		if(courseResponseV003 != null) {
			return courseResponseV003;
		}
		
		//課程時間是否和已選課程重疊判斷。
		courseResponseV003 = isStudentCourseTimeUser(
				courseEntityListV001, student_and_courseList, 
				courseRequest.getStudent_num(), 
				courseResponseV002);
		
		//不是null的話加選失敗。
		if(courseResponseV003 != null) {
			return courseResponseV003;
		}
		
		//匯入資料庫。
		Student_and_courseEntity student_and_courseEntity = 
				new Student_and_courseEntity(courseRequest.getStudent_num(), 
						courseRequest.getCourse_num());
		
		FlyKiteMySQL.AddSave(student_and_courseEntity);
		
		return new CourseResponse("新增成功");
	}
	
	//回傳StudentEntily資料庫資料
	private CourseResponse FindStudent_num
	(CourseRequest courseRequest) {
		
		if(courseRequest.getStudent_num() <= 0) {
			return new CourseResponse("學號不可等於小於0。");
		}
		
		List<StudentEntily> studentEntilyList = new ArrayList<>();
		
		try {
			studentEntilyList = 
					FlyKiteMySQL.findAll(StudentEntily.class);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		for(int i = 0; i < studentEntilyList.size(); i++) {
			StudentEntily studentEntilyV001 = studentEntilyList.get(i);
			//找尋相同課程代碼。
			if(courseRequest.getStudent_num() ==  
					studentEntilyV001.getStudent_num()) {
				return new CourseResponse(studentEntilyV001.getStudent_num(),
						studentEntilyV001.getName());
			}
		}
		
		return new CourseResponse("無學生資料");	
		}
		
	//學生學分上限總和10分判斷
	//可以加選回傳null。
	private CourseResponse isStudentCreditMax(
			List<CourseEntity> courseEntityList, 
			List<Student_and_courseEntity> student_and_courseList, 
			int thisCourseCredit, 
			int thisStudent_num) {
		
		final int maxCredit = 10;
		
		int creditCount = thisCourseCredit;
		
		for(int i = 0; i < student_and_courseList.size(); i++) {
			
			if(student_and_courseList.get(i).getStudent_num() != 
					thisStudent_num) {
				continue;
			}
			
			for(int j = 0; j < courseEntityList.size(); j++) {
				
				if(student_and_courseList.get(i).getCourse_num() != 
						courseEntityList.get(j).getCourse_num()) {
					continue;
				}
				
				creditCount += courseEntityList.get(j).getCredit();
				
				if(maxCredit < creditCount) {
					return new CourseResponse("加選後,學分超過上限。無法加選。");
				}
			}
		}
		return null;
	}
	
	
	//時間覆蓋判斷
	private CourseResponse isStudentCourseTimeUser(
			List<CourseEntity> courseEntityListV01, 
			List<Student_and_courseEntity> student_and_courseListV01,
			int newStudent_numV01, 
			CourseResponse newcourseResponseV01) {
		
		FlyKiteTL.Log("===============時間覆蓋判斷===============");
		
		for(int i = 0; i < student_and_courseListV01.size(); i++) {
			
			if(student_and_courseListV01.get(i).getStudent_num() != 
					newStudent_numV01) {
				continue;
			}
			
			for(CourseEntity courseEntityV001 : courseEntityListV01) {
				
				if(courseEntityV001.getCourse_num() != 
						student_and_courseListV01.
						get(i).getCourse_num()) {
					continue;
				}
				
				//=====================星期判斷==========================
				if(courseEntityV001.getWeek() != 
						newcourseResponseV01.getWeek()) {
					FlyKiteTL.Log(
							courseEntityV001.getCourse_num() + "不同星期");
					/*昨天跟明天需要另外判斷*/
					
					int countWeek = newcourseResponseV01.getWeek() - 1;
					
					//如果大於7，回到星期1
					if(countWeek > 7) {countWeek = 1;}
					//昨天
					if(countWeek == courseEntityV001.getWeek()) {
						
								int nowStart_time = 
								Integer.parseInt(courseEntityV001.getStart_time().
										substring(0, 4));
						int nowOver_time = 
								Integer.parseInt(courseEntityV001.getOver_time().
										substring(0, 4));
						int newStart_time = 
								Integer.parseInt(newcourseResponseV01.getStart_time().
										substring(0, 4));
						
						if(IsStudentCourseTimeCount2Day(nowStart_time, nowOver_time, 
								newStart_time) 

								== true) {
							return new CourseResponse(
									"課程代號:" + courseEntityV001.getCourse_num() + " "
											+ "時間重疊。");
						}
						break;
					}
					
					countWeek = newcourseResponseV01.getWeek() + 1;
					
					//如果大於7，回到星期1
					if(countWeek < 1) {countWeek = 7;}
					
					//明天
					if(countWeek == courseEntityV001.getWeek()) {
						
						int newStart_time = 
								Integer.parseInt(newcourseResponseV01.getStart_time().
										substring(0, 4));
						int newOver_time = 
								Integer.parseInt(newcourseResponseV01.getOver_time().
										substring(0, 4));
						int nowStart_time = 
								Integer.parseInt(courseEntityV001.getStart_time().
										substring(0, 4));
						
						if(IsStudentCourseTimeCount2Day(newStart_time, newOver_time, 
								nowStart_time) 
								== true) {
							return new CourseResponse(
									"課程代號:" + courseEntityV001.getCourse_num() + " "
											+ "時間重疊。");
						}
						break;
					}
					
					break;
				}
				
				int newStart_time = 
				Integer.parseInt(newcourseResponseV01.getStart_time().
						substring(0, 4));
				
				int newOver_time = 
				Integer.parseInt(newcourseResponseV01.getOver_time().
						substring(0, 4));
				
				int nowStart_time = 
				Integer.parseInt(courseEntityV001.getStart_time().
						substring(0, 4));
				
				int nowOver_time = 
				Integer.parseInt(courseEntityV001.getOver_time().
						substring(0, 4));
				
				
				if(newStart_time >= nowStart_time) {
					
					if(isStudentCourseTimeCount(
							newStart_time,
							newOver_time,
							nowStart_time,
							nowOver_time) 
							== true) {
						
						return new CourseResponse(
								"課程代號:" + courseEntityV001.getCourse_num() + " "
								+ "時間重疊。");
					}
				}else {
					if(isStudentCourseTimeCount(
							nowStart_time,
							nowOver_time,
							newStart_time,
							newOver_time) 
							== true) {
						return new CourseResponse(
								"課程代號:" + courseEntityV001.getCourse_num() + " "
								+ "時間重疊。");
					}
				}
				
				FlyKiteTL.Log(courseEntityV001.getCourse_num() + "不重複時間");
				
				break;
			}
		}
		
		return null;
	}
	
	//時間覆蓋判斷計算
	private boolean isStudentCourseTimeCount(
			int newCourseStartTime, int newCourseOverTime,
			int hasCourseStart_time, int hasCourseOver_time){
		/*
		 * 判斷原始時間startTime
			是否大於原始時間overTime
		 * */
		if(hasCourseStart_time > hasCourseOver_time) {
			return true;
		}
		
		/*
		 * 這樣代表新增時間start在
			原始時間start和over之間，
			所以必定重疊。
		 * */
		if(hasCourseOver_time >= newCourseStartTime) {
			return true;
		}

		/*
		 * 這樣代表新增時間start在
			原始時間start和over之外，
			除非隔天也一起做計算。不然這樣就是最終判斷結果。

			return 不重疊
		 * */
		return false;
		}
	
	//時間覆蓋判斷計算
	private boolean IsStudentCourseTimeCount2Day(
			int dayAStartTime, int dayAOverTime,
			int dayBStartTime) {
		
		//如果新增時間的startTime大於overTime，就表示時間有到隔天
		if(dayAOverTime > dayAStartTime) {
			/*不重疊*/
			return false;
		}else {}
		
		if(dayBStartTime > dayAOverTime) {
			return false;/*不重疊*/
		}
		
		return true;
	}
}
