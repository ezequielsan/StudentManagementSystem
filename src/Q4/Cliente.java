package Q4;

import java.net.*;
import java.io.*;

public class Cliente {
    public static void main(String[] args) {
        String host = "localhost";
        int porta = 7896;

        try (Socket socket = new Socket(host, porta)) {
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());

            AlunoIC aluno = new AlunoIC("Beatriz Silva", "2023012345", 800.00);
            byte[] dados = aluno.empacotar();

            // Envia tamanho + dados
            out.writeInt(dados.length);
            out.write(dados);

            // Lê resposta do servidor
            int tamanhoResposta = in.readInt();
            byte[] respostaBytes = new byte[tamanhoResposta];
            in.readFully(respostaBytes);
            String resposta = new String(respostaBytes, "UTF-8");

            System.out.println("Servidor respondeu: " + resposta);
        } catch (IOException e) {
            System.out.println("Erro no cliente: " + e.getMessage());
        }
    }
}
