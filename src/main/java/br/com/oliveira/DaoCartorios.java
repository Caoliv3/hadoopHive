package br.com.oliveira;

import java.sql.*;
import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;

public class DaoCartorios {

    private static String driverName = "org.apache.hive.jdbc.HiveDriver";
    private static Logger log = Logger.getLogger(App.class);
//    private static final String SQL_SELECT= "select * from cartoriosBvs";
//    private static final String SQL_INSERT= "insert into protestoRobo values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static Connection con;
    Statement stmt = null;
    private int codigoBvs = 0;

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
            stmt = con.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public  int selecionaCartorio(String ddd , String fone) {

        try {
            ResultSet rs = stmt.executeQuery("select * from cartoriosBvs where ddd = " + ddd + " and foneCeprot = " + fone);
            while (rs.next()) {
                codigoBvs = rs.getInt(6);
                System.out.println(rs.getString(1) + " " + rs.getInt(2) + " " + rs.getInt(3) + " " + rs.getString(4) + " " + rs.getString(5) + " "  + rs.getInt(6));

            }
        }catch (SQLException e){
            System.out.println("Erro ao selecionar registros " +e.getMessage());
        }
        return  codigoBvs;
    }

    public void Insert(ModelProtesto b) {

         try {
            stmt.executeUpdate("insert into table protestorobo values (" + b.getCodErro() + "," + b.getCnpjConsultado() + "," +
                                                                     b.getCnpjRetornado() + "," + b.getDtProtesto() + "," +
                                                                     b.getDtVencimento() + "," + b.getValorProtesto() + "," +
                                                                     b.getCodCartorioBvs() + "," + b.getUfed() + "," +
                                                                     b.getDtConsulta() + "," + b.getDtAtualizacao() + "," +
                                                                     b.getDtLegado() + "," + b.getCodMunicipio() + "," +
                                                                     b.getMunicipio() + "," + b.getCodCartorio() + "," +
                                                                     b.getCartorio() + "," + b.getEndereco() + "," +
                                                                     b.getGetddd() + "," + b.getGetTelefone() + "," +
                                                                     b.getQtdeProtesto() +
                    ")");

        } catch (SQLException e) {
            System.out.println("Erro ao insert os dados" + e.getMessage());
        }
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
