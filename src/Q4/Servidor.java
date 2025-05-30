package Q4;

import java.net.*;
import java.io.*;

public class Servidor {
    public static void main(String[] args) {
        int porta = 7896;
        try (ServerSocket serverSocket = new ServerSocket(porta)) {
            System.out.println("Servidor aguardando conexões na porta " + porta + "...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Cliente conectado!");

                new Thread(() -> {
                    try {
                        DataInputStream in = new DataInputStream(socket.getInputStream());
                        DataOutputStream out = new DataOutputStream(socket.getOutputStream());

                        int tamanho = in.readInt(); // tamanho da mensagem
                        byte[] dados = new byte[tamanho];
                        in.readFully(dados);

                        AlunoIC aluno = AlunoIC.desempacotar(dados);
                        System.out.println("Recebido do cliente:");
                        System.out.println("  Nome: " + aluno.nome);
                        System.out.println("  Matrícula: " + aluno.matricula);
                        System.out.println("  Bolsa: R$ " + aluno.bolsa);

                        // Enviar resposta
                        String resposta = "Recebido com sucesso, " + aluno.nome + "!";
                        byte[] respostaBytes = resposta.getBytes("UTF-8");

                        out.writeInt(respostaBytes.length);
                        out.write(respostaBytes);

                        socket.close();
                    } catch (IOException e) {
                        System.out.println("Erro no servidor: " + e.getMessage());
                    }
                }).start();
            }

        } catch (IOException e) {
            System.out.println("Erro ao iniciar servidor: " + e.getMessage());
        }
    }
}
