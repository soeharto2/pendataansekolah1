package pendataansekolah1;

import javax.swing.SwingUtilities;
import view.MainFrame;

public class Pendataansekolah1 {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new MainFrame();
        });

    }

}