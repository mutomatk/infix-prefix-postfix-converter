import java.util.Stack;

/**
 * Converter.java
 * Converts infix expressions to Postfix and Prefix notation
 * using the Shunting-Yard Algorithm.
 */
public class Converter {

    // ── Helpers ──────────────────────────────────────────────────────────────

    /** Returns operator precedence (higher = evaluated first). */
    private static int precedence(char op) {
        switch (op) {
            case '+': case '-': return 1;
            case '*': case '/': return 2;
            case '^':           return 3;
            default:            return -1;
        }
    }

    /** True for standard left-associative operators; ^ is right-associative. */
    private static boolean isLeftAssociative(char op) {
        return op != '^';
    }

    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }

    private static boolean isOperand(char c) {
        return Character.isLetterOrDigit(c);
    }

    // ── Infix → Postfix (Shunting-Yard) ──────────────────────────────────────

    /**
     * Converts an infix expression string to postfix notation.
     *
     * @param infix the infix expression (e.g. "A+B*C")
     * @return postfix expression string (e.g. "ABC*+")
     * @throws IllegalArgumentException on mismatched parentheses
     */
    public static String toPostfix(String infix) {
        StringBuilder output = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < infix.length(); i++) {
            char token = infix.charAt(i);

            if (token == ' ') continue; // skip whitespace

            if (isOperand(token)) {
                output.append(token);

            } else if (isOperator(token)) {
                while (!stack.isEmpty()
                        && isOperator(stack.peek())
                        && (precedence(stack.peek()) > precedence(token)
                            || (precedence(stack.peek()) == precedence(token)
                                && isLeftAssociative(token)))) {
                    output.append(stack.pop());
                }
                stack.push(token);

            } else if (token == '(') {
                stack.push(token);

            } else if (token == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    output.append(stack.pop());
                }
                if (stack.isEmpty()) {
                    throw new IllegalArgumentException("Mismatched parentheses: extra ')'");
                }
                stack.pop(); // discard '('
            }
        }

        while (!stack.isEmpty()) {
            char top = stack.pop();
            if (top == '(') {
                throw new IllegalArgumentException("Mismatched parentheses: extra '('");
            }
            output.append(top);
        }

        return output.toString();
    }

    // ── Infix → Prefix ────────────────────────────────────────────────────────

    /**
     * Converts an infix expression string to prefix notation.
     * Strategy: reverse → swap parens → postfix → reverse result.
     *
     * @param infix the infix expression (e.g. "A+B*C")
     * @return prefix expression string (e.g. "+A*BC")
     */
    public static String toPrefix(String infix) {
        // Step 1: reverse the expression
        String reversed = new StringBuilder(infix).reverse().toString();

        // Step 2: swap '(' and ')'
        StringBuilder swapped = new StringBuilder();
        for (char c : reversed.toCharArray()) {
            if      (c == '(') swapped.append(')');
            else if (c == ')') swapped.append('(');
            else               swapped.append(c);
        }

        // Step 3: apply postfix algorithm on the modified string
        String postfixOfReversed = toPostfix(swapped.toString());

        // Step 4: reverse the postfix result → prefix
        return new StringBuilder(postfixOfReversed).reverse().toString();
    }
}
