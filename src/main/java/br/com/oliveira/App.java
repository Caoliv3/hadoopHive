package br.com.oliveira;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App 
{

    public static void main( String[] args )
    {
        String source = args[0];
        String linha = "";

        DaoCartorios dao = new DaoCartorios();
        ReadFile lerArqrobo = new ReadFile(source);

        linha = lerArqrobo.lerLinha();




    }
}
