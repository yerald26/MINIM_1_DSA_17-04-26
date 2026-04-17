package manager;

import java.util.*;

public class ReversePolishNotationImpl implements ReversePolishNotation {
    @Override
    public double process(String expresion) throws Exception {
        // Comprobamos que no este vacio
        if (expresion == null || expresion.trim().isEmpty()) {
            throw new Exception("La expresión no puede estar vacía");
        }

        // Creamos la Pila
        Stack<Double> pila = new Stack<>();

        // Separamos expresion por espacios
        String[] tokens = expresion.trim().split("\\s+");

        // Recorremos cada elemento haciendo un bucle

        for (int i = 0; i < tokens.length; i++) {

            // Usamos el bucle con un switch para poder realizar la notacion polaca inversa
            String token = tokens[i];

            switch (token) {
                case "+":
                    pila.push(pila.pop() + pila.pop());
                    break;
                case "-":
                    double sustraendo = pila.pop();
                    double minuendo = pila.pop();
                    pila.push(minuendo - sustraendo);
                    break;
                case "*":
                    pila.push(pila.pop() * pila.pop());
                    break;
                case "/":
                    double divisor = pila.pop();
                    if (divisor == 0) throw new Exception("Division por cero no permitida");
                    double dividendo = pila.pop();
                    pila.push(dividendo / divisor);
                    break;
                default:
                    try {
                        pila.push(Double.parseDouble(token));
                    } catch (NumberFormatException e) {
                        throw new Exception("Elemento no valido en la expresión: " + token);
                    }
                    break;
            }
        }
        // Al final, solo debe quedar 1 número en la pila (el resultado final)
        if (pila.size() != 1) {
            throw new Exception("Expresión mal formateada: sobran numeros");
        }

        return pila.pop();
    }
}