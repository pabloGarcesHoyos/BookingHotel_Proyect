package connect;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class ValidadorEmail {
    public static boolean validarEmail(String email) {
        // Definir el patrón de regex para validar emails
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        // Compilar el patrón de regex
        Pattern pattern = Pattern.compile(regex);
        // Crear un matcher con el patrón y el texto a validar
        Matcher matcher = pattern.matcher(email);
        // Verificar si el texto cumple con el patrón
        return matcher.matches();
    }

    public static void main(String[] args) {
        // Ejemplo de uso
        String email = "ejemplo@correo.com";
        if (validarEmail(email)) {
            System.out.println(email + " es un correo válido.");
        } else {
            System.out.println(email + " no es un correo válido.");
        }
    }
}
