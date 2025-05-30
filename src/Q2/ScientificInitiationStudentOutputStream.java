package Q2;

import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import Q1.ScientificInitiationStudent;

public class ScientificInitiationStudentOutputStream extends OutputStream {

    private ScientificInitiationStudent[] studentsSI;
    private int amount;
    private OutputStream outputStream;

    public ScientificInitiationStudentOutputStream(
        ScientificInitiationStudent[] studentsSI, int amount, OutputStream outputStream
        ) {
        this.studentsSI = studentsSI;
        this.amount = amount;
        this.outputStream = outputStream;
    }

    @Override
    public void write(int b) throws IOException {
        outputStream.write(b);
    }

    public void send() throws IOException {
        DataOutputStream dataOut = new DataOutputStream(outputStream);

        for (int i = 0; i < amount; i++) {
            ByteArrayOutputStream temp = new ByteArrayOutputStream();
            DataOutputStream tempOut = new DataOutputStream(temp);

            ScientificInitiationStudent student = studentsSI[i];
            tempOut.writeUTF(student.getName());
            tempOut.writeInt(student.getAge());
            tempOut.writeUTF(student.getStudentId());
            tempOut.writeUTF(student.getMajor()); // course
            tempOut.writeInt(student.getWorkedDays());
            tempOut.writeDouble(student.getScholarshipAmount());

            byte[] studentData = temp.toByteArray();
            dataOut.writeInt(studentData.length);  // envia tamanho
            dataOut.write(studentData);            // envia dados
        }

        dataOut.flush();
    }

    
}
