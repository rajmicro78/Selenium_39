package src.com.pack.base;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;

public class Start {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> xmlFile = new ArrayList<String>();
		String path = new File("brandsdal.xml").getAbsolutePath();
		//String path1 = new File("D:\\CP-Script\\NSelenium4").getAbsolutePath();
		//System.out.println(path);
		xmlFile.add(path);
		//xmlFile.add(path1);
		//xmlFile.add("C:\\testngtest.xml");
		//	xmlFile.add("C:\\Users\\rajeev.singh\\workspace\\Cocopanda\\cpse.xml");
		//we can even more than one testng.xml files to xmlFile array to run
		//xmlFile.add("c:testng.xml");
		TestNG tng = new TestNG();
		tng.setTestSuites(xmlFile);
		//tng.setTestSuites("C:\\Users\\rajeev.singh\\workspace\\Cocopanda\\testng.xml");
		tng.run();

	}

}
