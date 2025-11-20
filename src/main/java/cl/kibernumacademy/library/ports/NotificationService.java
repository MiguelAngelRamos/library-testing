package cl.kibernumacademy.library.ports;


public interface NotificationService {
    void sendNotification(String userId, String message);
}