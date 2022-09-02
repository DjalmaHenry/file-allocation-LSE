import java.util.ArrayList;
import java.util.Scanner;

public class Aplicacao {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Particao particao = new Particao();
        ArrayList<String> buffer = new ArrayList<String>();
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
                    info = in.next();
                    in.nextLine();
                    buffer.add(info);
                    particao.inserirValor(buffer.get(buffer.size() - 1));
                    break;
                case 2:
                    System.out.println("Informe algo para remover do armazenamento:");
                    System.out.print("-> ");
                    info = in.next();
                    in.nextLine();
                    particao.removerValor(info);
                    break;
                case 3:
                    System.out.println("Informe algo para exibir:");
                    System.out.print("-> ");
                    info = in.next();
                    in.nextLine();
                    boolean result = buscaBuffer(info, buffer);
                    if (!result) {
                        particao.exibirValor(info);
                    }
                    break;
                case 4:
                    particao.exibirValores();
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
}