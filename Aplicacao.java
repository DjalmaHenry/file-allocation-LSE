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
        // Particao particao = new Particao();
        ArrayList<String> buffer = new ArrayList<String>();
        ArrayList<InfoParticao> infoParticao = new ArrayList<InfoParticao>();
        ArrayList<Arquivo> particao = new ArrayList<Arquivo>();
        int op;
        String info;
        while (true) {
            System.out.println("MENU:");
            System.out.println("1 - Adicionar uma informação.");
            System.out.println("2 - Remover uma informação.");
            System.out.println("3 - Consultar uma informação.");
            System.out.println("4 - Exibir todas informações.");
            System.out.println("5 - Limpar buffer.");
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

                    // salvando localizacao da informacao na particao
                    InfoParticao inf = new InfoParticao(0, info);
                    infoParticao.add(inf);

                    // convertendo informacao para bytes
                    byte[] bytes = stringToByte(info);

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
                    Arquivo arq1 = new Arquivo(4, info, b1);
                    Arquivo arq2 = new Arquivo(-1, info, b2);

                    // guardando arquivo particionado
                    particao.set(0, arq1);
                    particao.set(4, arq2);
                    break;
                case 2:
                    System.out.println("Informe algo para remover do armazenamento:");
                    System.out.print("-> ");
                    info = in.next();
                    in.nextLine();
                    // particao.removerValor(info);
                    break;
                case 3:
                    System.out.println("Informe algo para exibir:");
                    System.out.print("-> ");
                    info = in.next();
                    in.nextLine();
                    boolean result = buscaBuffer(info, buffer);
                    if (!result) {
                        int inicio = buscaInicioParticao(info, infoParticao);
                        byte[] bytesArquivo = montaArquivo(inicio);
                        String arquivo = byteToString(bytesArquivo);
                    }
                    break;
                case 4:
                    // particao.exibirValores();
                    break;
                case 5:
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

    private static int buscaInicioParticao(String info, ArrayList<InfoParticao> infoParticao) {
        for (int i = 0; i < infoParticao.size(); i++) {
            if (infoParticao.get(i).getNome().compareTo(info) == 0) {
                return infoParticao.get(i).getInicio();
            }
        }
        return -1;
    }
}
