package br.com.wilson.camel.lambda01;

public class LambdaExample {
    public static void main(String[] args) {
        // Interface funcional para verificar se um número é positivo
        VerificarNumero verificar = n -> n > 0;

        System.out.println(verificar.checar(5));   // Saída: true
        System.out.println(verificar.checar(-3));  // Saída: false
    }
}

@FunctionalInterface
interface VerificarNumero {
    boolean checar(int n);  // Método único da interface funcional
}
