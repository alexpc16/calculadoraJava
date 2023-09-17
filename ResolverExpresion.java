import java.util.Stack;

public class ResolverExpresion {
    public static double evaluar(String cadena) {

        Stack<Double> operandos = new Stack<>();
        Stack<Character> operadores = new Stack<>();

        String[] tokens = cadena.split("(?<=[-+*/^()])|(?=[-+*/^()])");

        imprimir(tokens);

        for (String token : tokens) {

            System.out.println(token + "op");

            if (token.isEmpty()) {
                // Ignorar tokens vacíos
                continue;
            } else if (esNumeroDouble(token)) {
                // Si el token es un número, apílalo en operandos
                char signo;
                System.out.println(operadores);
                if (!operadores.isEmpty()) {
                    signo = operadores.peek();
                    System.out.println(signo + "fff");

                } else {
                    signo = '+';
                }
                System.out.println(signo);
                if (signo == '-') {
                    operandos.push((-1) * Double.parseDouble(token));
                    operadores.pop();
                    operadores.push('+');
                } else {
                    operandos.push(Double.parseDouble(token));

                }

            } else if (token.charAt(0) == '+' || token.charAt(0) == '*' || token.charAt(0) == '/'
                    || token.charAt(0) == '^' ) {
                operadores.push(token.charAt(0));
            }

            else if (token.charAt(0) == '(') {
                // Si el token es un paréntesis izquierdo, apílalo en operadores
                operadores.push('(');
            } else if (token.charAt(0) == '-') {
                // Si el token es un paréntesis izquierdo, apílalo en operadores
                operadores.push('-');
            }
            else if (token.charAt(0) == '`') {
                // Si el token es un paréntesis derecho, desapila operadores hasta encontrar '('
                while (!operadores.isEmpty() && operadores.peek() != '(') {
                    evaluarOperador(operandos, operadores);
                }
                // Retira el '(' de la pila de operadores
                if (!operadores.isEmpty() && operadores.peek() == '(') {
                    operadores.pop();
                }
            }
            else if (token.charAt(0) == ')') {
                // Si el token es un paréntesis derecho, desapila operadores hasta encontrar '('
                while (!operadores.isEmpty() && operadores.peek() != '(') {
                    evaluarOperador(operandos, operadores);
                }
                // Retira el '(' de la pila de operadores
                if (!operadores.isEmpty() && operadores.peek() == '(') {
                    operadores.pop();
                }
            } else {
                // El token es un operador (+, -, *, /)
                while (!operadores.isEmpty() && obtenerPrioridad(operadores.peek()) >= obtenerPrioridad(token.charAt(0))) {
                    evaluarOperador(operandos, operadores);
                }
                operadores.push(token.charAt(0));
            }
            

        }

         // Procesar los operadores restantes en la pila de operadores
        while (!operadores.isEmpty()) {
            evaluarOperador(operandos, operadores);
        }

                if (!operandos.isEmpty()) {
            return operandos.peek();
        } else {
            // La expresión estaba vacía o mal formada
            throw new IllegalArgumentException("Expresión no válida");
        }

    }

    public static void imprimir(String[] arreglo) {
        for (String string : arreglo) {
            System.out.print(string + " ");
        }
        System.out.println();
    }
        private static int obtenerPrioridad(char operador) {
        switch (operador) {
            case '+':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return 0; // Para otros caracteres o paréntesis
        }
    }

       private static void evaluarOperador(Stack<Double> operandos, Stack<Character> operadores) {
        if (operandos.size() < 2 || operadores.isEmpty()) {
            throw new IllegalArgumentException("Expresión no válida");
        }
        double b = operandos.pop();
        double a = operandos.pop();
        char operador = operadores.pop();
        switch (operador) {
            case '+':
                operandos.push(a + b);
                break;
            case '-':
                operandos.push(a - b);
                break;
            case '*':
                operandos.push(a * b);
                break;
            case '/':
                if (b == 0) {
                    throw new ArithmeticException("División por cero");
                }
                operandos.push(a / b);
                break;
            default:
                throw new IllegalArgumentException("Operador no válido");
        }
    }

    public static boolean esNumeroDouble(String cadena) {
        try {
            // Intenta convertir la cadena en un double
            Double.parseDouble(cadena);
            return true; // La conversión fue exitosa, es un número double válido
        } catch (NumberFormatException e) {
            return false; // La conversión falló, no es un número double válido
        }
    }

    public static void main(String[] args) {
        System.out.println(evaluar("11-5)"));

    }

}
