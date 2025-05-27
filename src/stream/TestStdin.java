package stream;

import java.io.IOException;
import models.ScientificInitiationStudent;

public class TestStdin {
    public static void main(String[] args) {
        try {
            ScientificInitiationStudentInputStream input = new ScientificInitiationStudentInputStream(System.in);
            ScientificInitiationStudent aluno = input.readStudent();
            System.out.println(aluno);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// Para executar este código, você pode usar o seguinte comando no terminal:
// javac -d bin src/**/*.java para compilar tudo e gerar os arquivos .class na pasta bin.
// Depois, execute o programa com:
// java -cp bin stream.TestStdin < alunos.dat

