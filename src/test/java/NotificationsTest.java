import com.example.main_sem_proj.controller.NotificationsController;
import com.example.main_sem_proj.model.notifications.MockDisplayComponent;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


public class NotificationsTest {
    private NotificationsController notification;
    private MockDisplayComponent mockDisplayComponent;

    @BeforeEach
    void setUp() {
        notification = new NotificationsController();
        mockDisplayComponent = new MockDisplayComponent();
    }

    @Test
    void testDisplayNotification() {
        notification.displayNotification("Test Message");
        assertTrue(mockDisplayComponent.isPopupDisplayed());
        assertEquals("Test Message", mockDisplayComponent.getNotificationMessage());
    }
//
//    @Test
//    void testHideNotification() {
//        notification.displayNotification("Test Message");
//        assertTrue(mockDisplayComponent.isPopupDisplayed());
//
//        notification.hideNotification(stage);
//        assertFalse(mockDisplayComponent.isPopupDisplayed());
//    }

    @Test
    void testUpdateNotification() {
        notification.updateNotification("New Message");
        assertEquals("New Message", mockDisplayComponent.getNotificationMessage());
    }

}
