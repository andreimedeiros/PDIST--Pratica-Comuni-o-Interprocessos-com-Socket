package com.gugawag.so.ipc;

import java.net.*;
import java.io.*;
import java.util.Date;

public class DateServer {
    public static void main(String[] args) {
        try {
            ServerSocket sock = new ServerSocket(6013);

            System.out.println("=== Servidor iniciado ===\n");

            // escutando por conexões
            while (true) {
                // Aceita a conexão do cliente
                Socket client = sock.accept();
                // Se chegou aqui, foi porque algum cliente se comunicou
                System.out.println("Servidor recebeu comunicação do ip: " + client.getInetAddress() + "-" + client.getPort());

                // Obtém os fluxos de entrada e saída do cliente
                PrintWriter pout = new PrintWriter(client.getOutputStream(), true);
                InputStream in = client.getInputStream();
                BufferedReader bin = new BufferedReader(new InputStreamReader(in));

                // Escreve a data atual no socket
                pout.println(new Date().toString() + "-Boa noite, Andrei de Medeiros Lucena!");

                String line = bin.readLine();
                System.out.println("O cliente me disse:" + line);

                // fecha o socket após a comunicação com o cliente
                client.close();
            }
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
    }
}
