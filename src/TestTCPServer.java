

import java.net.*;

import Q3.ScientificInitiationStudentInputStream;

import java.io.*;
import Q1.ScientificInitiationStudent;

public class TestTCPServer {
    public static void main(String[] args) {
        try {
            System.out.println("Servidor de Alunos de Iniciação Científica iniciado");
            int serverPort = 7896;
            ServerSocket listenSocket = new ServerSocket(serverPort);

            while (true) {
                Socket clientSocket = listenSocket.accept();
                System.out.println("Cliente conectado: " + clientSocket.getInetAddress());
                new StudentConnection(clientSocket);
            }
        } catch (IOException e) {
            System.out.println("Listen socket: " + e.getMessage());
        }
    }
}

class StudentConnection extends Thread {
    private Socket clientSocket;

    public StudentConnection(Socket aClientSocket) {
        this.clientSocket = aClientSocket;
        this.start();
    }

    public void run() {
        try {
            ScientificInitiationStudentInputStream in =
                new ScientificInitiationStudentInputStream(clientSocket.getInputStream());

            while (true) {
                ScientificInitiationStudent aluno = in.readStudent();
                System.out.println("AlunoIC recebido:");
                System.out.println("  Nome: " + aluno.getName());
                System.out.println("  Idade: " + aluno.getAge());
                System.out.println("  Matrícula: " + aluno.getStudentId());
                //                System.out.println("  Curso: " + aluno.getCourse());
                System.out.println("  Dias trabalhados: " + aluno.getWorkedDays());
                System.out.println("  Bolsa: R$ " + aluno.getScholarshipAmount());
                System.out.println("  Pagamento total: R$ " + aluno.calculatePayment());
            }

        } catch (EOFException e) {
            System.out.println("Conexão encerrada pelo cliente.");
        } catch (IOException e) {
            System.out.println("Erro na leitura: " + e.getMessage());
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
