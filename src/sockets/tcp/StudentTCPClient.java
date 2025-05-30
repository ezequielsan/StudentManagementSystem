package sockets.tcp;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import Q2.ScientificInitiationStudentOutputStream;
import Q1.ScientificInitiationStudent;

public class StudentTCPClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 7896)) {
            OutputStream out = socket.getOutputStream();

            ScientificInitiationStudent[] alunos = {
                new ScientificInitiationStudent("Lucas", 20, "123", "Computer Science", 20, 500.0),
                new ScientificInitiationStudent("Ana", 22, "789", "Biology", 15, 450.0),
                new ScientificInitiationStudent("Carlos", 21, "456", "Physics", 18, 600.0),
            };

            ScientificInitiationStudentOutputStream customOut = 
                new ScientificInitiationStudentOutputStream(alunos, alunos.length, out);
            customOut.send();

            System.out.println("Dados enviados ao servidor.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
