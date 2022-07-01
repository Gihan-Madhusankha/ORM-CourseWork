package util;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * @author : Gihan Madhusankha
 * 2022-06-30 8:17 PM
 **/

public class NotificationUtil {
    public void showNotification(String iconName, String title, String text){
        Image image = new Image("view/asserts/image/"+iconName+".png");
        Notifications notifications = Notifications.create();
        notifications.graphic(new ImageView(image));
        notifications.title(title);
        notifications.text(text);
        notifications.hideAfter(Duration.seconds(5));
        notifications.darkStyle();
        notifications.show();
    }
}
