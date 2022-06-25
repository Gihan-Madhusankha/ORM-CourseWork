package util;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.TextField;

import java.util.LinkedHashMap;
import java.util.regex.Pattern;

/**
 * @author : Gihan Madhusankha
 * 2022-06-25 5:18 PM
 **/

public class ValidateUtil {

    public static Object validate(LinkedHashMap<TextField, Pattern> map, JFXButton btnReg) {
        for (TextField textField : map.keySet()) {
            Pattern pattern = map.get(textField);

            if (!pattern.matcher(textField.getText()).matches()) {
                addError(textField, btnReg);
                return textField;
            } else {
                removeError(textField);
            }
        }
        btnReg.setDisable(false);
        return true;
    }

    private static void removeError(TextField textField) {
        textField.getParent().setStyle("-fx-border-color: GREEN");
    }

    private static void addError(TextField textField, JFXButton btnReg) {
        if (textField.getText().length() > 0) {
            textField.getParent().setStyle("-fx-border-color: RED");
        }
        btnReg.setDisable(true);
    }

    public static void setBorders(TextField... textFields) {
        for (TextField textField : textFields) {
            textField.getParent().setStyle("-fx-border-color: rgba(76, 73, 73, 0.83)");
        }
    }
}
