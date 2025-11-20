package cl.kibernumacademy.library.services;

import cl.kibernumacademy.library.domain.Book;
import cl.kibernumacademy.library.domain.Member;
import cl.kibernumacademy.library.exceptions.LoanException;
import cl.kibernumacademy.library.ports.BookRepository;
import cl.kibernumacademy.library.ports.NotificationService;

public class LibraryService {

    private final BookRepository bookRepository;
    private final NotificationService notificationService;

    // Inyección de dependencias por constructor (Best Practice para Testing)
    public LibraryService(BookRepository bookRepository, NotificationService notificationService) {
        this.bookRepository = bookRepository;
        this.notificationService = notificationService;
    }

    public void loanBook(String isbn, Member member) {
        // 1. Buscar libro en repositorio (dependencia externa)
        Book book = bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new LoanException("Libro no encontrado: " + isbn));

        // 2. Lógica de Negocio: Verificar Stock
        if (book.totalCopies() <= 0) {
            throw new LoanException("No hay stock disponible para el libro: " + book.title());
        }

        // 3. Lógica de Negocio: Regla VIP (Solo para demostración de tests parametrizados luego)
        // Supongamos que hay una regla compleja aquí...
        
        // 4. Efecto Secundario: Notificar al usuario (dependencia externa)
        notificationService.sendNotification(member.id(), "Préstamo confirmado: " + book.title());
        
     
    }
}