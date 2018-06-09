import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BBC {

    public static void main(String [] args) {
        GetNews getNews=new GetNews();
        getNews.update();
    }
}
