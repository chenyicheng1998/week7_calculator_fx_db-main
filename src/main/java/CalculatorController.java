
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CalculatorController {

    @FXML private TextField number1Field;
    @FXML private TextField number2Field;
    @FXML private Label resultLabel;

    // ── Addition ──────────────────────────────────────────────────────────────
    @FXML
    private void onAddClick() {
        try {
            double num1 = Double.parseDouble(number1Field.getText());
            double num2 = Double.parseDouble(number2Field.getText());
            double result = num1 + num2;
            resultLabel.setText("Sum: " + result);
            ResultService.saveResult("Addition", num1, num2, result);
        } catch (NumberFormatException e) {
            resultLabel.setText("Please enter valid numbers!");
        }
    }

    // ── Multiplication ────────────────────────────────────────────────────────
    @FXML
    private void onMultiplyClick() {
        try {
            double num1 = Double.parseDouble(number1Field.getText());
            double num2 = Double.parseDouble(number2Field.getText());
            double result = num1 * num2;
            resultLabel.setText("Product: " + result);
            ResultService.saveResult("Multiplication", num1, num2, result);
        } catch (NumberFormatException e) {
            resultLabel.setText("Please enter valid numbers!");
        }
    }

    // ── Subtraction ───────────────────────────────────────────────────────────
    @FXML
    private void onSubtractClick() {
        try {
            double num1 = Double.parseDouble(number1Field.getText());
            double num2 = Double.parseDouble(number2Field.getText());
            double result = num1 - num2;
            resultLabel.setText("Subtraction: " + result);
            ResultService.saveResult("Subtraction", num1, num2, result);
        } catch (NumberFormatException e) {
            resultLabel.setText("Please enter valid numbers!");
        }
    }

    // ── Division ──────────────────────────────────────────────────────────────
    @FXML
    private void onDivideClick() {
        try {
            double num1 = Double.parseDouble(number1Field.getText());
            double num2 = Double.parseDouble(number2Field.getText());
            if (num2 == 0) {
                resultLabel.setText("Error: Division by zero!");
                return;
            }
            double result = num1 / num2;
            resultLabel.setText("Division: " + result);
            ResultService.saveResult("Division", num1, num2, result);
        } catch (NumberFormatException e) {
            resultLabel.setText("Please enter valid numbers!");
        }
    }
}

