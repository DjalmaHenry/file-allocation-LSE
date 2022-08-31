import java.util.Scanner;

public class Aplicacao {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Particao particao = new Particao();
        int op;
        String info;
        while (true) {
            System.out.println("MENU:");
            System.out.println("1 - Adicionar uma informação.");
            System.out.println("2 - Remover uma informação.");
            System.out.println("3 - Consultar uma informação.");
            System.out.println("4 - Exibir todas informações.");
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
                    particao.inserirValor(info);
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
                    particao.exibirValor(info);
                    break;
                case 4:
                    particao.exibirValores();
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
}