package br.com.oliveira;

import java.sql.*;

import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;

public class DaoCartorios {

    private static String driverName = "org.apache.hive.jdbc.HiveDriver";
    private static Logger log = Logger.getLogger(App.class);
    private static final String SQL_SELECT= "select * from cartoriosBvs where dd=? and foneCeprot=?";
    private static Connection con;
    java.sql.Statement sql = null;
    private String codigoBvs ="";

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
            con = DriverManager.getConnection("jdbc:hive2://localhost:10000/mydb","ubuntu", "ubuntu");
            sql = con.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public  String selecionaCartorio(String ddd , String fone) {

        try {
            PreparedStatement sqlSelect = con.prepareStatement(SQL_SELECT);
            sqlSelect.setString(1, ddd);
            sqlSelect.setString(2,fone);
            ResultSet rs = sqlSelect.executeQuery();
            if (rs.next()) {
                codigoBvs = rs.getString("codigoCartorio");
            }
        }catch (SQLException e){
            System.out.println("Erro ao selecionar registros " +e.getMessage());
        }
        return  codigoBvs;
    }

    public void close(){

        try {
            if (con != null) {
                con.close();
            }
        }catch (SQLException e){
            System.out.println("Erro no fechamento do banco " + e.getMessage());
        }
    }
}
