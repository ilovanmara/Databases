import javax.swing.*;
import java.awt.*;

public class Succes {
    public Succes(String succes){
        JFrame succ = new JFrame();
        JPanel psucc = new JPanel();
        JLabel lsucc = new JLabel(succes);
        succ.setSize(350, 150);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - succ.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - succ.getHeight()) / 2);
        succ.setLocation(x, y);
        succ.setTitle("Succes");

        lsucc.setBounds((succ.getWidth() - 280)/ 2,45, 280, 30);
        lsucc.setFont(new Font("Calibri", Font.BOLD, 20));
        lsucc.setHorizontalAlignment(SwingConstants.CENTER);
        lsucc.setVerticalAlignment(SwingConstants.CENTER);
        psucc.add(lsucc);

        psucc.setBackground(Color.green);
        succ.setContentPane(psucc);
        succ.setLayout(null);
        succ.setVisible(true);
    }
}
