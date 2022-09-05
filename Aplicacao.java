import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Aplicacao {

    public static void main(String[] args) throws ClassNotFoundException, IOException {
        Scanner in = new Scanner(System.in);
        ArrayList<String> buffer = new ArrayList<String>();
        ArrayList<InfoParticao> infoParticao = new ArrayList<InfoParticao>();
        ArrayList<Arquivo> particao = new ArrayList<Arquivo>();
        int op;
        String info;
        while (true) {
            System.out.println("MENU:");
            System.out.println("1 - Adicionar uma informação.");
            System.out.println("2 - Consultar uma informação.");
            System.out.println("3 - Limpar buffer.");
            System.out.println("0 - Sair do programa.");
            System.out.println("Informe a opção desejada:");
            System.out.print("-> ");
            op = in.nextInt();
            switch (op) {
                case 1:
                    System.out.println("Informe algo para armazenar:");
                    System.out.print("-> ");
                    // capturando informacao
                    info = in.next();
                    in.nextLine();

                    // adicionando informacao ao buffer
                    buffer.add(info);

                    // convertendo informacao para bytes
                    byte[] bytes = stringToByte(info);

                    // salvando localizacao da informacao na particao
                    InfoParticao inf = new InfoParticao(particao.size(), info, bytes.length);
                    infoParticao.add(inf);

                    // quebrando a informacao para salvar particionado
                    int n = bytes.length;
                    byte[] b1 = new byte[(n + 1) / 2];
                    byte[] b2 = new byte[n - b1.length];
                    for (int i = 0; i < n; i++) {
                        if (i < b1.length) {
                            b1[i] = bytes[i];
                        } else {
                            b2[i - b1.length] = bytes[i];
                        }
                    }
                    Arquivo arq1 = new Arquivo(particao.size() + 1, info, b1);
                    Arquivo arq2 = new Arquivo(-1, info, b2);

                    // guardando arquivo particionado
                    particao.add(particao.size(), arq1);
                    particao.add(particao.size(), arq2);

                    break;
                case 2:
                    System.out.println("Informe algo para exibir:");
                    System.out.print("-> ");
                    info = in.next();
                    in.nextLine();
                    boolean result = buscaBuffer(info, buffer);
                    if (!result) {
                        InfoParticao infoArq = buscaInicioParticao(info, infoParticao);
                        if (infoArq == null) {
                            System.out.println("Arquivo não encontrado na partição!");
                            break;
                        }
                        byte[] bytesArquivo = montaArquivo(infoArq.getInicio(), particao, infoArq.getTamanho());
                        String arquivo = byteToString(bytesArquivo);
                        System.out.println("Info: " + arquivo);
                    }
                    break;
                case 31
                :
                    System.out.println("Buffer limpo!");
                    buffer.clear();
                    break;
                case 0:
                    System.out.println("Adeus!");
                    System.exit(0);
                    break;
                default:
                    System.err.println("Opção inválida");
            }
        }
    }

    public static boolean buscaBuffer(String info, ArrayList<String> buffer) {
        for (int i = 0; i < buffer.size(); i++) {
            if (buffer.get(i).compareTo(info) == 0) {
                System.out.println("Achou no buffer!");
                System.out.println("Info: " + buffer.get(i));
                return true;
            }
        }

        return false;
    }

    private static byte[] stringToByte(String info) throws IOException {
        Object obj = (Object) info;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(obj);
        objectOutputStream.flush();
        objectOutputStream.close();
        byteArrayOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }

    private static String byteToString(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        Object object = objectInputStream.readObject();
        objectInputStream.close();
        String info = (String) object;
        return info;
    }

    private static InfoParticao buscaInicioParticao(String info, ArrayList<InfoParticao> infoParticao) {
        for (int i = 0; i < infoParticao.size(); i++) {
            if (infoParticao.get(i).getNome().compareTo(info) == 0) {
                return infoParticao.get(i);
            }
        }
        return null;
    }

    private static byte[] montaArquivo(int inicio, ArrayList<Arquivo> particao, int tamanho) {
        Arquivo atual = particao.get(inicio);
        byte[] montagem1 = atual.getInfo();
        byte[] montagem2 = particao.get(atual.getProx()).getInfo();
        byte[] byteArquivo = new byte[tamanho];
        for (int i = 0; i < tamanho; i++) {
            if (i < montagem1.length) {
                byteArquivo[i] = montagem1[i];
            } else {
                byteArquivo[i] = montagem2[i - montagem1.length];
            }
        }
        return byteArquivo;
    }
}
