package Q4;

import java.io.*;

public class AlunoIC {
    public String nome;
    public String matricula;
    public double bolsa;

    public AlunoIC(String nome, String matricula, double bolsa) {
        this.nome = nome;
        this.matricula = matricula;
        this.bolsa = bolsa;
    }

    public byte[] empacotar() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(baos);

        out.writeUTF(nome);
        out.writeUTF(matricula);
        out.writeDouble(bolsa);

        return baos.toByteArray();
    }

    public static AlunoIC desempacotar(byte[] dados) throws IOException {
        DataInputStream in = new DataInputStream(new ByteArrayInputStream(dados));

        String nome = in.readUTF();
        String matricula = in.readUTF();
        double bolsa = in.readDouble();

        return new AlunoIC(nome, matricula, bolsa);
    }
}
