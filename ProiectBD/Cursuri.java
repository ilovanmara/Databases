import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Cursuri extends JFrame implements ActionListener{
    //int idMaterie;
    JPanel panel = new JPanel();
    JLabel label = new JLabel("Materie:");

    Cursuri(String nume, String descriere, String dataInceput, String dataSfarsit){
        //this.idMaterie = idMaterie;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 550);
        this.setTitle("Pagina unui administrator");

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);

        label.setBounds((this.getWidth() - 200)/2, 10, 200, 30);
        label.setFont(new Font("Calibri", Font.BOLD, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label);

        JLabel numeMaterie = new JLabel("Nume materie: " + nume);
        numeMaterie.setBounds(50, 50, 250, 100);
        panel.add(numeMaterie);

        JLabel descriereMat = new JLabel("Prenume: " + descriere);
        descriereMat.setBounds(50, 85, 500, 100);
        panel.add(descriereMat);

        JLabel dataInceputMat = new JLabel("Data la care incepe cursul: " + dataInceput);
        dataInceputMat.setBounds(50, 120, 250, 100);
        panel.add(dataInceputMat);

        JLabel dataSfarsitMat = new JLabel("Data la care se incheie cursul: " + dataSfarsit);
        dataSfarsitMat.setBounds(50, 155, 250, 100);
        panel.add(dataSfarsitMat);

        panel.setBackground(Color.lightGray);
        this.setContentPane(panel);
        this.setLayout(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}


