package com.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.xml.XmlSuite;
import com.aventstack.extentreports.ExtentReports;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import src.com.pack.base.SendMail;

public class ExtentTestNGIReporterListener implements IReporter {
    private static final String OUTPUT_FOLDER = "test-output/";
    private static final String FILE_NAME = "Brandsdal.html";

    private ExtentReports extent;
    public static String extentfilename;

    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List <ISuite>  suites, String outputDirectory) {
        init();

        for (ISuite suite : suites) {
          //  Map result = suite.getResults();
            Map<String,ISuiteResult> result = suite.getResults();

            for (ISuiteResult r : result.values()) {
                ITestContext context = r.getTestContext();

                buildTestNodes(context.getFailedTests(), Status.FAIL);
                buildTestNodes(context.getSkippedTests(), Status.SKIP);
                buildTestNodes(context.getPassedTests(), Status.PASS);

            }
        }

        for (String s : Reporter.getOutput()) {
            extent.setTestRunnerOutput(s);
        }

        extent.flush();
       
    }

    private void init() {
    	 extentfilename= getDateAsString()+FILE_NAME;
    	//String filename= FILE_NAME;
    	System.out.println(extentfilename);
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(OUTPUT_FOLDER + extentfilename);
        htmlReporter.config().setDocumentTitle("Detail Compiled Report");
        htmlReporter.config().setReportName("Brandsdal Framework Report");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        //htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
       // htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setReportUsesManualConfiguration(false);
        
    }

   
    
    private void buildTestNodes(IResultMap tests, Status status) {
        ExtentTest test;

        if (tests.size() > 0) {
            for (ITestResult result : tests.getAllResults()) {
                test = extent.createTest(result.getMethod().getMethodName());

                for (String group : result.getMethod().getGroups())
                    test.assignCategory(group);

                if (result.getThrowable() != null) {
                    test.log(status, result.getThrowable());
                } else {
                    test.log(status, "Test " + status.toString().toLowerCase() + "ed");
                }

                test.getModel().setStartTime(getTime(result.getStartMillis()));
                test.getModel().setEndTime(getTime(result.getEndMillis()));
            }
        }
       
    }

    private String getDateAsString() {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-");
		Date date = new Date();
		return dateFormat.format(date);
		
	}
    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
      
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }
}