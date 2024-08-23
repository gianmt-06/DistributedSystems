package gian.App.Presentation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

import gian.App.Contracts.ATM.CardAdvanceRequest;
import gian.App.Contracts.ATM.ConsignmentToAccountRequest;
import gian.App.Contracts.ATM.GetBalanceRequest;
import gian.App.Contracts.ATM.GetMoneyRequest;
import gian.App.Contracts.Responses.ATMResponse;
import gian.App.Infrastructure.Socket.JavaClientSocket.JavaClientSocket;
import gian.App.Infrastructure.Socket.Server.Client;
import gian.App.Infrastructure.Socket.Server.SocketProcess;

public class ATMSocketClient {
    BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    SocketProcess client;

    private boolean connect() {
        JavaClientSocket javaClientSocket = new JavaClientSocket(1802, "127.0.0.1");
        Socket clientSocket = javaClientSocket.get();

        if (clientSocket == null) {
            System.out.println("ClientSocket is null");
            return false;
        }

        this.client = new Client(clientSocket);

        if (!client.bind()) {
            System.out.println("Client bind failed");
            return false;
        }

        return true;
    }

    private void close() {
        if (!client.close()) {
            System.out.println("Client close failed");
            return;
        }

        System.out.println("Java Client Socket closed");
    }

    public ATMResponse request(String option){
        ArrayList<Object> dataRequest = new ArrayList<Object>();
        ArrayList<Object> dataResponse = new ArrayList<Object>();

        try { 
            if (!this.connect()) throw new Error("Error al conectar");
            Object request;

            System.out.print("Ingrese el id de su cuenta: ");
            int idCard = Integer.parseInt(buffer.readLine());
    
            System.out.print("\nIngrese su contraseña: ");
            String password = buffer.readLine();

            switch (option) {
                case "a":
                    int money;
                    System.out.print("Ingrese el dinero a retirar: ");
                    money = Integer.parseInt(buffer.readLine());

                    request = new GetMoneyRequest(idCard, password, money);
                    break;
                
                case "b":
                    System.out.print("Ingrese el dinero del avance: ");
                    money = Integer.parseInt(buffer.readLine());

                    request = new CardAdvanceRequest(idCard, password, money);
                    break;
                
                case "c":
                    System.out.print("Ingrese el número de cuenta a la cual va a consignar: ");
                    int account = Integer.parseInt(buffer.readLine());

                    System.out.print("Ingrese el dinero del avance: ");
                    money = Integer.parseInt(buffer.readLine());

                    request = new ConsignmentToAccountRequest(idCard, password, account, money);
                    break;

                case "d":
                    request = new GetBalanceRequest(idCard, password);
                    break;
                default:
                    throw new Error("Opción inválida");
            }

            dataRequest.add(option);
            dataRequest.add(request);
            dataRequest.add(0);

            this.client.request(dataRequest);

            dataResponse = (ArrayList<Object>) client.listen();
            
            this.close();
        
            return (ATMResponse) dataResponse.get(0);
        } catch (Exception e) {
            return new ATMResponse("500", "Error", e.getMessage());
        }
    }

}
