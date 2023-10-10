import javax.swing.*;
import java.awt.*;

public class Error {
    String eroare;
    public Error(String eroare){
        JFrame err = new JFrame();
        JPanel perr = new JPanel();
        JLabel lerr = new JLabel(eroare);
        err.setSize(350, 150);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - err.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - err.getHeight()) / 2);
        err.setLocation(x, y);
        err.setTitle("Error");

        lerr.setBounds((err.getWidth() - 280)/ 2,45, 280, 30);
        lerr.setFont(new Font("Calibri", Font.BOLD, 20));
        lerr.setHorizontalAlignment(SwingConstants.CENTER);
        lerr.setVerticalAlignment(SwingConstants.CENTER);
        perr.add(lerr);

        perr.setBackground(Color.red);
        err.setContentPane(perr);
        err.setLayout(null);
        err.setVisible(true);

    }
}
