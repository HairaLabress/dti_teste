import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Lembrete {
    public static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private String nome;
    private LocalDate data;
//metodo construtor
    public Lembrete(String nome, LocalDate data) {
        this.nome = nome;
        this.data = data;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getData() {
        return data;
    }
//verificação para data futura
    public static LocalDate lerDataValida(Scanner scanner, String mensagem) {
        LocalDate data = null;
        boolean dataValida = false;
        do {
            System.out.print(mensagem);
            String dataStr = scanner.nextLine().trim();
        try {
            data = LocalDate.parse(dataStr, FORMATO_DATA);
            if (data.isBefore(LocalDate.now())) {
                System.out.println("Data inválida. Digite uma data futura.");
            } else {
                dataValida = true;
            }
        } catch (DateTimeParseException e) {
            System.out.println("Data inválida. Digite no formato dd/MM/yyyy.");
        }
        } while (!dataValida);
            return data;
    }

    public static String lerStringNaoVazia(Scanner scanner, String mensagem) {
        String valor;

        do {
            System.out.print(mensagem);
            valor = scanner.nextLine().trim();
        } while (valor.isEmpty());

        return valor;
    }
}
