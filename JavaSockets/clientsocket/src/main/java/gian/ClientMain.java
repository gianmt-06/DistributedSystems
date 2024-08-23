package gian;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import gian.App.Contracts.Responses.ATMResponse;
import gian.App.Presentation.ATMSocketClient;

public class ClientMain {
    public static void main(String[] args) throws IOException {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        ATMSocketClient Atm = new ATMSocketClient();

        System.out.println(sendOptions());
        System.out.print("Opción: ");

        String option = buffer.readLine();
        ATMResponse response = Atm.request(option);

        System.out.println("\nRESPONSE");
        System.out.println("Code: " + response.code());
        System.out.println("Title: " + response.title());
        System.out.println("Message: " + response.message());
    }

    static String sendOptions(){
        return "Bienvenido a tu cajero!" + 
        "\nEscoge la opción de tu preferencia:" +
        "\na.Retiro de cuenta de ahorro" + 
        "\nb.Avance de tarjeta de crédito" + 
        "\nc.Consignación a cuenta de ahorros" +
        "\nd.Saldo";
    }
}
