import java.util.Scanner;

/**
 * Main.java
 * Entry point for the Infix → Prefix / Postfix Converter.
 * Runs an interactive loop, letting the user enter expressions
 * and see both conversions with a step-by-step trace.
 */
public class Main {

    // ── ANSI colour codes (ignored on terminals that don't support them) ──────
    private static final String RESET  = "\u001B[0m";
    private static final String BOLD   = "\u001B[1m";
    private static final String CYAN   = "\u001B[36m";
    private static final String GREEN  = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String RED    = "\u001B[31m";

    public static void main(String[] args) {
        printBanner();

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.print(CYAN + "\nEnter infix expression (or 'q' to quit): " + RESET);
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit")) {
                System.out.println(YELLOW + "\nGoodbye!\n" + RESET);
                running = false;
                continue;
            }

            if (input.isEmpty()) {
                System.out.println(RED + "⚠  Please enter an expression." + RESET);
                continue;
            }

            try {
                String postfix = Converter.toPostfix(input);
                String prefix  = Converter.toPrefix(input);

                System.out.println();
                System.out.println(BOLD + "┌─────────────────────────────────────┐" + RESET);
                System.out.printf (BOLD + "│  %-37s│%n" + RESET, "Results");
                System.out.println(BOLD + "├─────────────────────────────────────┤" + RESET);
                System.out.printf ("│  Infix   : %-26s│%n", input);
                System.out.printf (GREEN  + "│  Postfix : %-26s│%n" + RESET, postfix);
                System.out.printf (YELLOW + "│  Prefix  : %-26s│%n" + RESET, prefix);
                System.out.println(BOLD + "└─────────────────────────────────────┘" + RESET);

            } catch (IllegalArgumentException e) {
                System.out.println(RED + "✗ Error: " + e.getMessage() + RESET);
            }
        }

        scanner.close();
    }

    private static void printBanner() {
        System.out.println(CYAN + BOLD);
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║   Infix → Prefix & Postfix Converter ║");
        System.out.println("║   Shunting-Yard Algorithm  •  Java   ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.println(RESET);
        System.out.println("  Supported operators: + - * / ^");
        System.out.println("  Parentheses:         ( )");
        System.out.println("  Operands:            letters or digits");
    }
}
