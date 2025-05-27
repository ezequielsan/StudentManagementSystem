package stream;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import models.ScientificInitiationStudent;

public class ScientificInitiationStudentInputStream extends InputStream {

    private InputStream source;

    public ScientificInitiationStudentInputStream(InputStream source) {
        this.source = source;
    }

    @Override
    public int read() throws IOException {
        return source.read();
    }

    public ScientificInitiationStudent readStudent() throws IOException {
        DataInputStream dataIn = new DataInputStream(source);

        int length = dataIn.readInt(); // número de bytes
        byte[] data = new byte[length];
        dataIn.readFully(data); // lê os dados

        DataInputStream objIn = new DataInputStream(new ByteArrayInputStream(data));

        String name = objIn.readUTF();
        int age = objIn.readInt();
        String studentId = objIn.readUTF();
        String course = objIn.readUTF();
        int workedDays = objIn.readInt();
        double scholarshipAmount = objIn.readDouble();

        return new ScientificInitiationStudent(name, age, studentId, course, workedDays, scholarshipAmount);
    }
    
}
