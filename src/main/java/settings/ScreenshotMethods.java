package settings;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

abstract public class ScreenshotMethods {
    private static Document document;
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
    private static final String reportsFolder="TestResults";
    private static String testResultFolder, screenshotNameWithPath;

    public static void createTestFolder(String scenarioName){
        File reportFolderFile = new File(reportsFolder);
        if (!reportFolderFile.exists()){
            reportFolderFile.mkdir();
        }

        testResultFolder = reportsFolder+"/"+ scenarioName + " " + getTimeStampWithoutYear();
        File testResultFile = new File(testResultFolder);
        if (!testResultFile.exists()){
            testResultFile.mkdir();
        }
    }

    public static void screenshotSetup() throws Exception{
        document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(testResultFolder+"/"+ getTimeStampWithoutYear()+".pdf"));
        document.open();
    }

    public static void screenshotTearDown(){
        try {
            document.close();
        }catch (Exception e){
            System.out.println("No images attached to pdf.");
        }

    }

    public static String getTimeStampWithoutYear(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-hh-mm-ss");
        Date date = new Date();
        String dateString = simpleDateFormat.format(date);
        return dateString;
    }

    public static void takeScreenshot(WebDriver driver) throws Exception{
        System.out.println("Taking screenshot");
        //Create screenshot
        File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        screenshotNameWithPath = testResultFolder+"/"+ getTimeStampWithoutYear()+".png";
        FileUtils.copyFile(source, new File(screenshotNameWithPath));

        //Add screenshot to pdf
        Image screenshot = Image.getInstance(screenshotNameWithPath);
        screenshot.scalePercent(100f,70f);
        screenshot.scaleToFit(500,600);
        document.add(new Paragraph("MY SCENARIO TITLE",catFont));
        document.add(new Paragraph(" "));
        document.add(new Paragraph("My test"));
        document.add(screenshot);
        document.newPage();

        //Delete screenshot
        Files.deleteIfExists(Paths.get(System.getProperty("user.dir") +"/"+screenshotNameWithPath));
    }

    public static void addLogger(String my_log) throws DocumentException {
        document.add(new Paragraph(my_log));
    }
}
