package cl.kibernumacademy.library.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import cl.kibernumacademy.library.domain.Book;
import cl.kibernumacademy.library.domain.Member;
import cl.kibernumacademy.library.ports.BookRepository;
import cl.kibernumacademy.library.ports.NotificationService;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class LibraryServiceTest {
  
  // Mocks: Objetos simulados. No son reales, no conectan a la base de datos ni envían notificaciones reales
  @Mock
  private BookRepository bookRepository;

  @Mock
  private NotificationService notificationService;

  @InjectMocks
  private LibraryService libraryService;

  private Member commonMember;
  private Book bookWithStock;
  private Book bookOutOfStock;

  @BeforeEach
  void setUp() {
    commonMember = new Member("user123", "Sofia Araya", false);
    bookWithStock = new Book("12345", "Java Moderno", 5);
    bookOutOfStock = new Book("99999", "Cobol Antiguo", 0);
  }

 @Test
 @DisplayName("Debe permitir el prestamo si hay stock y notificar al usuario")
  void testLoanSuccess() {
    // Patron Triple A: Arrange, Act, Assert
    // 1. Arrange (preparar)
    // Le vamos a enseñar al Mock que responder cuando lo llamen
    when(bookRepository.findByIsbn("12345")).thenReturn(Optional.of(bookWithStock));

    //2. ACT (Actuar)
    // Ejecutamos el método "real" quie estamos probando
    libraryService.loanBook("12345", commonMember);

    //3. Assert (Afirmar)
    verify(notificationService).sendNotification("user123", "Préstamo confirmado: Java Moderno");

    // Verificamos que no se haya lanzado ninguna excepcion
    assertDoesNotThrow(() -> libraryService.loanBook("12345", commonMember));
  }


}
