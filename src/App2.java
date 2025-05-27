import java.io.FileOutputStream;

import models.ScientificInitiationStudent;
import stream.ScientificInitiationStudentOutputStream;

public class App2 {
    public static void main(String[] args) {
        ScientificInitiationStudent[] alunos = {
            new ScientificInitiationStudent("Lucas", 20, "123", "Computer Science", 20, 500.0),
            new ScientificInitiationStudent("Ana", 22, "789", "Biology", 15, 450.0),
        };

        // Enviar dados para a saída padrão
        /* ScientificInitiationStudentOutputStream out = 
            new ScientificInitiationStudentOutputStream(alunos, alunos.length, System.out);
        try {
            out.send();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        } */

        // Enviar dados para um arquivo
        try (FileOutputStream fos = new FileOutputStream("alunos.dat")) {
            ScientificInitiationStudentOutputStream fileOut = new ScientificInitiationStudentOutputStream(alunos, alunos.length, fos);
            fileOut.send();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

    }
}

