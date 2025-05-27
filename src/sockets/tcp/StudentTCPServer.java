package sockets.tcp;

import java.net.*;
import java.io.*;

public class StudentTCPServer {
    public static void main(String args[]) {
        try {
            System.out.println("Servidor de Alunos Iniciação Científica iniciado");
            int serverPort = 7896;
            ServerSocket listenSocket = new ServerSocket(serverPort);
            while (true) {
                Socket clientSocket = listenSocket.accept();
                System.out.println("Cliente conectado: " + clientSocket.getInetAddress());
                new StudentConnection(clientSocket);
            }
        } catch (IOException e) {
            System.out.println("Listen socket:" + e.getMessage());
        }
    }
}

class StudentConnection extends Thread {
    DataInputStream in;
    Socket clientSocket;

    public StudentConnection(Socket aClientSocket) {
        try {
            clientSocket = aClientSocket;
            in = new DataInputStream(clientSocket.getInputStream());
            this.start();
        } catch (IOException e) {
            System.out.println("Connection:" + e.getMessage());
        }
    }

    public void run() {
        try {
            while (true) {
                // Lê o tamanho do próximo objeto
                int tamanho = in.readInt();
                byte[] dados = new byte[tamanho];
                in.readFully(dados);

                // Lê os dados do AlunoIC
                DataInputStream dadosIn = new DataInputStream(new ByteArrayInputStream(dados));
                String nome = dadosIn.readUTF();
                String matricula = dadosIn.readUTF();
                double bolsa = dadosIn.readDouble();

                System.out.println("AlunoIC recebido:");
                System.out.println("  Nome: " + nome);
                System.out.println("  Matrícula: " + matricula);
                System.out.println("  Bolsa: R$ " + bolsa);
            }
        } catch (EOFException e) {
            System.out.println("Conexão encerrada pelo cliente.");
        } catch (IOException e) {
            System.out.println("Erro na leitura:" + e.getMessage());
        } finally {
            try {
                clientSocket.close();
                System.out.println("Conexão finalizada.");
            } catch (IOException e) {
                System.out.println("Erro ao fechar socket: " + e.getMessage());
            }
        }
    }
}
