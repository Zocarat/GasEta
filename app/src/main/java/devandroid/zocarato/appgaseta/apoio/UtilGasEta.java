package devandroid.zocarato.appgaseta.apoio;

public class UtilGasEta {

    public void metodoNaoEstatico() {
    }

    public static void metodoEstatico() {    }

    public static String mensagem() {

        return "Qualquer mensagem";
    }

    public static double preco() {

        return 9.99;
    }

    public static String calcularMelhorOpcao(double gasolina, double etanol){
        //preço da gasolina 5,12
        // preço do etanol 3,99

        double precoIdeal = gasolina * 0.70;
        String menssagemDeRetorno;


        if (etanol <= precoIdeal){
            menssagemDeRetorno = "Abasteça com etanol";
        }
        else {
            menssagemDeRetorno = "Abasteça com gasolina";
        }
        return menssagemDeRetorno;

    }

}
