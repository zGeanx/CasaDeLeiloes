
import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            cadastroVIEW cadastroView = new cadastroVIEW();
            cadastroView.setVisible(true);
            cadastroView.setLocationRelativeTo(null); // Centraliza
        });
    }
}