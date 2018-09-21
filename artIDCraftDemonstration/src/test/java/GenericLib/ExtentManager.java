package GenericLib;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
	private static ExtentReports instance;
	static String screenshotExtension,screenshotPath;
	static File folder;
	
	
	static int iFlag=0;

	public static synchronized ExtentReports getInstance() {
		if (instance == null) {
			System.out.println(System.getProperty("user.dir"));
			
			SimpleDateFormat sdfDateReport = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
			Date now = new Date();
			screenshotExtension=sdfDateReport.format(now) +"_"+System.currentTimeMillis();
			if(iFlag == 0)
			{
				folder = new File(System.getProperty("user.dir") +"/ReportGenerator/CraftDemonstration__"+screenshotExtension);
				
				screenshotPath="NBCAutomation__"+screenshotExtension;
				if(!folder.exists())
				{
					folder.mkdir();
					iFlag=1;
					
				}
			}
			instance=new ExtentReports(folder+"/CraftDemonstration_"+ sdfDateReport.format(now) +".html",true);
			instance.assignProject("CraftDemonstration");
		
			
		}
		
		return instance;
	}
}
