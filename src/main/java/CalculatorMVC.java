import GUI.Controller;
import GUI.View;
import datamodels.Model;

public class CalculatorMVC {
    public static void main(String[] args) {
        Model model = new Model();
        View view = new View(model);
        view.setVisible(true);
        Controller controller = new Controller(view,model);
    }
}
