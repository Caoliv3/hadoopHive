package br.com.oliveira;

import lombok.Data;

import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class ModelProtesto {

    // 0 codigo_retorno
    // 1 Data da Consulta
    // 2 CNPJ
    // 3 Codigo Cartorio CENPROT
    // 4 Nome do Cartorio
    // 5 Endereco
    // 7 Municipio
    // 8 Data de Atualização
    // 9 Quantidade de Protestos
    // 10 Data do Protesto
    // 12 Valor do Protesto
    // 15 Telefone do Cartorio
    // 16 UF
    // 18 Data de Vencimento

    private String codErro = "";
    private String dtConsulta = "";
    private String cnpjConsultado = "";
    private String cnpjRetornado = "";
    private String ufed = "";
    private String dtAtualizacao = "";
    private String dtLegado = "";
    private String codMunicipio = "";
    private String municipio = "";
    private String codCartorio = "";
    private String cartorio = "";
    private String endereco = "";
    private static String numdd = "";
    private static String telefone = "";
    private String fone="";
    private String qtdeProtesto = "";
    private String dtProtesto = "";
    private String dtVencimento = "";
    private String valorProt = "";
    private int valorProtesto = 0;
    public String getCodigoBusca = "";
    public String getTelefone = "";
    private String codCartorioBvs = "";
    private static int ddd = 0;
    private static int numTel = 0;
    private String getddd= "";
    private String getNumTel= "";

    public ModelProtesto(String reg) {

        String[] linha = reg.split("\\|", 99);

        codErro = String.format("%-3s", linha[0]);

        if (!linha[1].equals("")) {
            dtConsulta = formataData(linha[1]);
        }

        cnpjConsultado = String.format("%-18s", linha[2]);
        cnpjRetornado = String.format("%-18s", cnpjRetornado);

        if (!linha[3].equals("")) {
            codCartorio = linha[3];
            codCartorio = String.format("%09d", Integer.parseInt(codCartorio));
        } else {
            codCartorio = String.format("%-9s", codCartorio);
        }

        cartorio = String.format("%-300s", removeAccents(linha[4]));
        endereco = String.format("%-250s", removeAccents(linha[5]));
        municipio = String.format("%-60s", removeAccents(linha[7]));
        dtAtualizacao = String.format("%-10s", linha[8]);
        dtLegado = String.format("%-10s", dtLegado);
        codMunicipio = String.format("%-9s", codMunicipio);

        if (!linha[9].equals("")) {
            qtdeProtesto = linha[9];
            qtdeProtesto = String.format("%09d", Integer.parseInt(qtdeProtesto));
        } else {
            qtdeProtesto = String.format("%-9s", qtdeProtesto);
        }

        if (!linha[10].equals("")) {
            dtProtesto = formataData(linha[10]);
            dtProtesto = String.format("%-10s", dtProtesto);
        } else {
            dtProtesto = String.format("%-10s", dtProtesto);
        }

        if (!linha[12].equals("")) {
            valorProt = linha[12];
            valorProt = String.format("%016d", Long.valueOf(valorProt.replace(".", "")));
            valorProtesto = Integer.parseInt(String.valueOf(valorProt));
        } else {
            valorProt = String.format("%-16s", valorProtesto);
            valorProtesto = Integer.parseInt(String.valueOf(valorProt));
        }

        if (!linha[15].equals("")) {
            telefone = linha[15];
            getTelefone = linha[15];
            try {
                formataTelefone(telefone);
                getCodigoBusca = String.valueOf(numdd) + String.valueOf(numTel);
                getddd = numdd ;
                getNumTel = Integer.toString(numTel);

            } catch (Exception e) {
                System.out.println("Erro na conversao de telefone " + linha[15] + " " + cnpjConsultado + " "
                        + dtProtesto + " " + e.getMessage());
            }
        }else {
            numdd = "";
            telefone = "";
            getCodigoBusca = "";
        }

        ufed = linha[16];

        if (!linha[18].equals("")) {
            dtVencimento = formataData(linha[18]);
            dtVencimento = String.format("%-10s", dtVencimento);
        } else {
            dtVencimento = String.format("%-10s", dtVencimento);
        }



    }

    public static String formataData(String d) {

        SimpleDateFormat formato = new SimpleDateFormat("dd/mm/yyyy");
        try {
            Date tdata = formato.parse(d);
            d = formato.format(tdata);
        } catch (ParseException e) {

            e.printStackTrace();
        }
        return d;
    }

    public static String removeAccents(String str) {

        str = Normalizer.normalize(str, Normalizer.Form.NFD);
        str = ((String) str).replaceAll("[^\\p{ASCII}]", "");
        return str.toUpperCase();
    }

    public static void formataTelefone(String str) {

        String tel = String.valueOf(str.replaceAll("[^0-9]+", ""));

        if (tel.substring(0, 1).equals("0")) {
            numdd = String.format("%02d", Integer.valueOf(tel.substring(1, 3)));
        }else {
            numdd = String.format("%02d", Integer.valueOf(tel.substring(0, 2)));
        }

        if (tel.substring(0, 1).equals("0")) {
            tel = tel.substring(1, tel.length());
        }


//		if (tel.substring(0, 1).equals("9")) {
//		tel = tel.substring(2, tel.length());
//		}else {
        tel = tel.substring(2, tel.length());
//		}


        if (!tel.substring(0, 1).equals("9") && tel.length() < 11) {
            numTel = Integer.valueOf(tel.substring(0, tel.length()));
        } else if (!tel.substring(0, 1).equals("9") && tel.length() > 10) {
            numTel = Integer.valueOf(tel.substring(0, 8));
        } else if (tel.substring(0, 1).equals("9") && tel.length() < 11) {
            numTel = Integer.valueOf(tel.substring(0, tel.length()));
        } else {
            numTel = Integer.valueOf(tel.substring(0, 9));
        }

        telefone = String.format("%09d", numTel);


    }

    @Override
    public String toString() {

        return codErro + dtConsulta + cnpjConsultado + cnpjRetornado + ufed + dtAtualizacao + dtLegado + codMunicipio
                + municipio + codCartorio + cartorio + endereco + numdd + telefone + qtdeProtesto + dtProtesto
                + dtVencimento + valorProtesto + codCartorioBvs;

    }

    public void setcodCartorioBvs(String cod) {

        codCartorioBvs = String.format("%011d", Long.valueOf(cod));
    }
}

