import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;



public class Agenda {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Lembrete> lembretes = new ArrayList<>();

        while (true) {
            System.out.println("=== AGENDA ===");
            System.out.println("1. Adicionar lembrete");
            System.out.println("2. Excluir lembrete");
            System.out.println("3. Listar lembretes");
            System.out.println("4. Sair");

            int opcao = lerInteiro(scanner, "Digite uma opção: ");

            switch (opcao) {
                case 1:
                    adicionarLembrete(scanner, lembretes);
                    break;
                case 2:
                    excluirLembrete(scanner, lembretes);
                    break;
                case 3:
                    listarLembretes(lembretes);
                    break;
                case 4:
                    System.out.println("Saindo...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
    }
    //vriar lembrete
    private static void adicionarLembrete(Scanner scanner, List<Lembrete> lembretes) {
        String nome = lerStringNaoVazia(scanner, "Digite o nome do lembrete: ");
        LocalDate data = Lembrete.lerDataValida(scanner, "Digite a data do lembrete (no formato dd/mm/aaaa): ");
        lembretes.add(new Lembrete(nome, data));
        System.out.println("Lembrete adicionado!");
    }
    //excluir lembrete
    private static void excluirLembrete(Scanner scanner, List<Lembrete> lembretes) {
        if (lembretes.isEmpty()) {
            System.out.println("Não há lembretes para excluir!");
            return;
        }
    //listar lembretes
        listarLembretes(lembretes);
        int indice = lerInteiro(scanner, "Digite o índice do lembrete a ser excluído: ");

        if (indice < 0 || indice >= lembretes.size()) {
            System.out.println("Índice inválido!");
            return;
        }

        lembretes.remove(indice);
        System.out.println("Lembrete excluído!");
    }
    //caso vazio
    private static void listarLembretes(List<Lembrete> lembretes) {
        if (lembretes.isEmpty()) {
            System.out.println("Não há lembretes para listar!");
            return;
        }

        Collections.sort(lembretes, Comparator.comparing(Lembrete::getData));

        System.out.println("=== LISTA DE LEMBRETES ===");

        for (int i = 0; i < lembretes.size(); i++) {
            Lembrete lembrete = lembretes.get(i);
            System.out.printf("%d. %s (%s)%n", i, lembrete.getNome(), lembrete.getData().format(Lembrete.FORMATO_DATA));
        }
    }
    //caso vazio
    private static String lerStringNaoVazia(Scanner scanner, String mensagem) {
        String valor;

        do {
            System.out.print(mensagem);
            valor = scanner.nextLine().trim();
        }while (valor.isEmpty());
            return valor;
    }

    private static int lerInteiro(Scanner scanner, String mensagem) {
        int valor;

        while (true) {
        System.out.print(mensagem);

            try {
            valor = scanner.nextInt();
            scanner.nextLine(); // consumir o \n deixado pelo nextInt()
            return valor;
            } catch (InputMismatchException e) {
            scanner.nextLine(); // consumir a entrada inválida
            System.out.println("Valor inválido! Digite um número inteiro.");
            }
        }
    }
//verificar formato da data
    private static LocalDate lerData(Scanner scanner, String mensagem) {
        LocalDate data;

        while (true) {
            String textoData = lerStringNaoVazia(scanner, mensagem);

            try {
            data = LocalDate.parse(textoData, Lembrete.FORMATO_DATA);
            return data;
            } catch (DateTimeParseException e) {
            System.out.println("Data inválida! Digite uma data no formato dd/mm/aaaa.");
            }
        }
    }
}