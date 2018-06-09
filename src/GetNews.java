import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.swing.*;
import java.sql.*;
import java.util.List;


public class GetNews {
    Connection con;
    Statement stmt;
    String title,detail,imgSrc;
    WebDriver webDriver;
    List<WebElement> titles;
    public GetNews(){
        connect();
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.get("https://www.bbc.com");
    }
    public void connect(){


        try {
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/localnews","root","");
            stmt=con.createStatement();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database connection couldn't be made!");
        }

    }
    public void getFeed(int i){

        titles= webDriver.findElements(By.className("title-link__title-text"));
        List<WebElement> details=webDriver.findElements(By.className("dove-item__summary"));
        List<WebElement> images=webDriver.findElements(By.className("js-image-replace"));


        //title=titles.get(i).getText();detail=details.get(i).getText();imgSrc=images.get(i).getText();

    }

    public void update() {
        titles= webDriver.findElements(By.className("media__link"));
        List<WebElement> details=webDriver.findElements(By.className("media__summary"));
        List<WebElement> images=webDriver.findElements(By.className("image-replace"));
        System.out.println(images.size()+"d"+titles.size());
        int i=1;
        while(i<6){
            try {

                details.get(i).getText().replace("\'","");
                images.get(i).getText().replace("\'","");

                System.out.println(details.get(i).getText());
                //System.out.println(images.get(i).getText());

                System.out.println(images.get(i).getAttribute("src"));
                PreparedStatement prstmt=con.prepareStatement("UPDATE news set title=?,details=?,src=? WHERE id=?");
                prstmt.setString(1,titles.get(i+4).getText().replace("\'",""));
                prstmt.setString(2,details.get(i).getText().replace("\'",""));
                prstmt.setString(3,images.get(i).getAttribute("src").replace("\'",""));
                prstmt.setString(4,String.valueOf(i));
                prstmt.executeUpdate();
               // stmt.execute("update news set title=" + titles.get(i).getText().replace('\'','\'').replace(',',' ')+ " ,details="+details.get(i).getText().replace("\'","").replace(",","")+ ",src="+images.get(i).getText().replace("\'","").replace(",","")+" where id='" + i + "';");


            } catch (Exception e) {
                e.printStackTrace();
            }
            i++;
        }


//        System.out.println(titles.size());

//        while (i < 6) {
//            getFeed(1);
//            try {
//                //stmt.execute("update news set title='" + title + "' where itemidno='" + i + "';");
//                System.out.println(title);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            //i++;
//        }
    }

}
