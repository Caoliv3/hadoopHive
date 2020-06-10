package br.com.oliveira;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class ReadFile {

    private String origem;
    private String linha = "";
    private BufferedReader buffer = null;

    public ReadFile(String o) {

        origem = o;

        try {
            buffer = new BufferedReader(new InputStreamReader( new FileInputStream(origem), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de origem nao encontrada!\n" + e.getMessage());
        }
    }

    public String lerLinha() {

        try {
            linha = buffer.readLine();
        } catch (IOException e) {
            System.out.println("Erro nao leitura do arquivo!\n" + e.getMessage());
            e.printStackTrace();
        }
        return linha;
    }

    public void close() {
        try {
            if (buffer != null) {
                buffer.close();
            }
        } catch (IOException e) {
            System.out.println("Erro no close do arquivo!\n" + e.getMessage());
        }
    }
}