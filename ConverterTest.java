import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * ConverterTest.java
 * Unit tests for Converter.toPostfix() and Converter.toPrefix().
 */
class ConverterTest {

    // ── Postfix Tests ─────────────────────────────────────────────────────────

    @Test
    void postfix_simpleAddition() {
        assertEquals("AB+", Converter.toPostfix("A+B"));
    }

    @Test
    void postfix_precedence_multiplyBeforeAdd() {
        // A + B * C  →  A B C * +
        assertEquals("ABC*+", Converter.toPostfix("A+B*C"));
    }

    @Test
    void postfix_precedence_withSubtraction() {
        // A + B * C - D  →  A B C * + D -
        assertEquals("ABC*+D-", Converter.toPostfix("A+B*C-D"));
    }

    @Test
    void postfix_parenthesesOverridePrecedence() {
        // (A + B) * C  →  A B + C *
        assertEquals("AB+C*", Converter.toPostfix("(A+B)*C"));
    }

    @Test
    void postfix_rightAssociativePower() {
        // A ^ B ^ C  →  A B C ^ ^   (right-to-left)
        assertEquals("ABC^^", Converter.toPostfix("A^B^C"));
    }

    @Test
    void postfix_nestedParentheses() {
        // ((A + B) * (C - D))  →  A B + C D - *
        assertEquals("AB+CD-*", Converter.toPostfix("((A+B)*(C-D))"));
    }

    @Test
    void postfix_singleOperand() {
        assertEquals("A", Converter.toPostfix("A"));
    }

    @Test
    void postfix_complexExpression() {
        // A * B + C / D - E  →  A B * C D / + E -
        assertEquals("AB*CD/+E-", Converter.toPostfix("A*B+C/D-E"));
    }

    // ── Prefix Tests ──────────────────────────────────────────────────────────

    @Test
    void prefix_simpleAddition() {
        assertEquals("+AB", Converter.toPrefix("A+B"));
    }

    @Test
    void prefix_precedence_multiplyBeforeAdd() {
        // A + B * C  →  + A * B C
        assertEquals("+A*BC", Converter.toPrefix("A+B*C"));
    }

    @Test
    void prefix_parenthesesOverridePrecedence() {
        // (A + B) * C  →  * + A B C
        assertEquals("*+ABC", Converter.toPrefix("(A+B)*C"));
    }

    @Test
    void prefix_rightAssociativePower() {
        // A ^ B ^ C  →  ^ A ^ B C
        assertEquals("^A^BC", Converter.toPrefix("A^B^C"));
    }

    @Test
    void prefix_nestedParentheses() {
        // ((A + B) * (C - D))  →  * + A B - C D
        assertEquals("*+AB-CD", Converter.toPrefix("((A+B)*(C-D))"));
    }

    @Test
    void prefix_singleOperand() {
        assertEquals("A", Converter.toPrefix("A"));
    }

    // ── Error Handling ────────────────────────────────────────────────────────

    @Test
    void postfix_mismatchedOpenParen_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> Converter.toPostfix("A+(B*C"));
    }

    @Test
    void postfix_mismatchedCloseParen_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> Converter.toPostfix("A+B)*C"));
    }
}
