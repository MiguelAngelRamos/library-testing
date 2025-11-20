package cl.kibernumacademy.library.domain;

// Uso de 'record' para reducir el boilerplate (getters, toString, equals automáticos)
public record Book(String isbn, String title, int totalCopies) {
    // Podemos agregar lógica ligera si es necesario
}