import java.util.Stack;

public class Resolver {
        public  double evaluarExpresion(String expresion) {
        // Eliminar espacios en blanco de la expresión
        // Crear una pila para operadores y otra para operandos
        Stack<Double> operandos = new Stack<>();
        Stack<Character> operadores = new Stack<>();

        // Definir la precedencia de los operadores
        // Puedes ajustar estas prioridades según tus necesidades
        final int PRIORIDAD_SUMA_RESTA = 1;
        final int PRIORIDAD_MULTIPLICACION_DIVISION = 2;

        // Dividir la expresión en tokens (números, operadores y paréntesis)
        String[] tokens = expresion.split("(?<=[-+*/()])|(?=[-+*/()])");

        System.out.println(tokens);

        for (String token : tokens) {
            if (token.isEmpty()) {
                // Ignorar tokens vacíos
                continue;
            } else if (esNumero(token)) {
                // Si el token es un número, apílalo en operandos
                operandos.push(Double.parseDouble(token));
            } else if (token.charAt(0) == '(') {
                // Si el token es un paréntesis izquierdo, apílalo en operadores
                operadores.push('(');
            } else if (token.charAt(0) == ')') {
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

        // El resultado final debe estar en la cima de la pila de operandos
        if (!operandos.isEmpty()) {
            return operandos.peek();
        } else {
            // La expresión estaba vacía o mal formada
            throw new IllegalArgumentException("Expresión no válida");
        }
    }

    // Función auxiliar para determinar si un token es un número
    private static boolean esNumero(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Función auxiliar para obtener la prioridad de un operador
    private static int obtenerPrioridad(char operador) {
        switch (operador) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return 0; // Para otros caracteres o paréntesis
        }
    }

    // Función auxiliar para evaluar un operador y realizar la operación correspondiente
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
     


}
