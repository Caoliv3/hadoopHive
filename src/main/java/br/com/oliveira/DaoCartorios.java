package br.com.oliveira;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;

public class DaoCartorios {

    private static String driverName = "org.apache.hive.jdbc.HiveDriver";
    private static Logger log = Logger.getLogger(App.class);

    public DaoCartorios() {

        BasicConfigurator.configure();
        log.info("This is Logger Info");

        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // get connection
        Connection con = null;
        try {
//          con = DriverManager.getConnection("jdbc:hive2://localhost:10000/default", "", "");
            con = DriverManager.getConnection("jdbc:hive2://localhost:10000/mydb","ubuntu", "ubuntu");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
