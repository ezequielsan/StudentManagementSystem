package Q2;

import java.io.*;

import Q3.ScientificInitiationStudentInputStream;
import Q1.ScientificInitiationStudent;

public class TestFile {
    public static void main(String[] args) {
        try (FileInputStream fileIn = new FileInputStream("alunos.dat")) {
            ScientificInitiationStudentInputStream input = new ScientificInitiationStudentInputStream(fileIn);

            while (true) {
                ScientificInitiationStudent aluno = input.readStudent();
                System.out.println(aluno);
            }

        } catch (EOFException e) {
            System.out.println("Fim do arquivo.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}