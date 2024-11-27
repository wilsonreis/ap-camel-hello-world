package br.com.wilson.camel.variados;

public class CamelCaseConverter {

    public static String toCamelCase(String texto) {
        StringBuilder camelCase = new StringBuilder();
        boolean nextUpperCase = false;

        for (char c : texto.toCharArray()) {
            if (Character.isWhitespace(c)) {
                nextUpperCase = true;
            } else {
                if (nextUpperCase || camelCase.length() == 0) {
                    camelCase.append(Character.toUpperCase(c));
                } else {
                    camelCase.append(c);
                }
                nextUpperCase = false;
            }
        }

        return camelCase.toString();
    }

    public static void main(String[] args) {
        String texto = "hello world";
        String textoCamelCase = toCamelCase(texto);
        System.out.println(textoCamelCase); // Imprime: HelloWorld
    }
}
