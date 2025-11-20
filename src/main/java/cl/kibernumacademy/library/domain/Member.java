package cl.kibernumacademy.library.domain;

/**
 * Representa un miembro de la tienda.
 *
 * Como `record`, esta clase:
 *  - es inmutable
 *  - tiene constructor canónico generado
 *  - expone métodos de acceso: id(), name(), isVip()
 *  - tiene equals(), hashCode() y toString() generados
 */
public record Member(String id, String name, boolean isVip) {
    // Aquí se pueden añadir métodos de comportamiento si se necesita,
    // pero los campos y los métodos básicos ya los proporciona el record.
}