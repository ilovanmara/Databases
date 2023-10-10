import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.border.Border;
public class DatePersonale extends JFrame implements ActionListener{
    JPanel panel = new JPanel();
    JLabel label = new JLabel("Date Personale", SwingConstants.CENTER);
    JButton backBtn = new JButton("BACK");
    Border border = BorderFactory.createLineBorder(Color.lightGray);
    Connection connection;
    String utilizator;
    String utilizatorRevenire;
    int id;
    int idRevenire;
    public DatePersonale(int id, Connection connection, String utilizator, String utilizatorRevenire, int idRevenire){

        this.utilizator = utilizator;
        this.id = id;
        this.idRevenire = idRevenire;
        this.connection = connection;
        this.utilizatorRevenire = utilizatorRevenire;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize((int)dimension.getWidth(), (int)dimension.getHeight());
        this.setSize(500, 550);

        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);

        panel.setBackground(Color.lightGray);

        backBtn.setBounds(10, 475, 80, 30);
        backBtn.setBorder(border);
        backBtn.addActionListener(this);
        panel.add(backBtn);

        label.setBounds((this.getWidth() - 200)/2, 10, 200, 30);
        label.setFont(new Font("Calibri", Font.BOLD, 20));
        panel.add(label);

        Statement selectStatement1 = null;
        Statement selectStatement2 = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        try {
            selectStatement1 = connection.createStatement();
            selectStatement1.execute("SELECT * FROM utilizator WHERE id =\"" +  id + "\";");
            rs1 = selectStatement1.getResultSet();

            if (rs1.next())
            {
                JLabel numeProf = new JLabel("Nume: " + rs1.getString("nume"));
                numeProf.setBounds(50, 50, 200, 30);
                panel.add(numeProf);

                JLabel prenumeProf = new JLabel("Prenume: " + rs1.getString("prenume"));
                prenumeProf.setBounds(50, 85, 200, 30);
                panel.add(prenumeProf);

                JLabel cnp = new JLabel("CNP: " + rs1.getString("CNP"));
                cnp.setBounds(50, 120, 200, 30);
                panel.add(cnp);

                JLabel adresaEmail = new JLabel("Email: " + rs1.getString("email"));
                adresaEmail.setBounds(50, 155, 300, 30);
                panel.add(adresaEmail);

                JLabel telefon = new JLabel("Telefon: " + rs1.getString("nr_telefon"));
                telefon.setBounds(50, 190, 200, 30);
                panel.add(telefon);

                JLabel contIBAN = new JLabel("IBAN: " + rs1.getString("IBAN"));
                contIBAN.setBounds(50, 225, 200, 30);
                panel.add(contIBAN);

                JLabel adresaUtiliz = new JLabel("Adresa: " + rs1.getString("adresa"));
                adresaUtiliz.setBounds(50, 260, 300, 30);
                panel.add(adresaUtiliz);

                JLabel nrContract = new JLabel("Numarul de contract: " + rs1.getString("nr_contract"));
                nrContract.setBounds(50, 295, 200, 30);
                panel.add(nrContract);
            }

            if(utilizator.equals("student"))
            {
                selectStatement2 = connection.createStatement();
                selectStatement2.execute("SELECT an_studiu FROM student WHERE idStudent =\"" +  id + "\";");
                rs2 = selectStatement2.getResultSet();
                if(rs2.next())
                {
                    JLabel anStudiu = new JLabel("Anul de studiu: " + rs2.getString("an_studiu"));
                    anStudiu.setBounds(50, 330, 100, 30);
                    panel.add(anStudiu);
                }
                this.setTitle("Student");
            }
            if(utilizator.equals("profesor"))
            {
                selectStatement2 = connection.createStatement();
                selectStatement2.execute("SELECT departament FROM profesor WHERE idProfesor =\"" +  id + "\";");
                rs2 = selectStatement2.getResultSet();
                if(rs2.next())
                {
                    JLabel anStudiu = new JLabel("Departamentul: " + rs2.getString("departament"));
                    anStudiu.setBounds(50, 330, 300, 30);
                    panel.add(anStudiu);
                }
                this.setTitle("Profesor");
            }
            if(utilizator.equals("administrator"))
            {
                this.setTitle("Administrator");
            }
            if(utilizator.equals("superadministrator"))
            {
                this.setTitle("Superadministrator");
            }
        }
        catch(SQLException sqlex) {
            System.err.println("An SQL Exception occured. Details are provided below:");
            sqlex.printStackTrace(System.err);
        }

        this.add(panel, BorderLayout.CENTER);
        this.setContentPane(panel);
        this.setLayout(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == backBtn)
        {
            this.setVisible(false);
            if(utilizatorRevenire.equals("student"))
            {
                StudentPage student = new StudentPage(idRevenire, connection);
            }
            else if(utilizatorRevenire.equals("profesor"))
            {
                TeacherPage prof = new TeacherPage(idRevenire, connection);
            }
            else if(utilizatorRevenire.equals("administrator"))
            {
                AdministratorPage administrator = new AdministratorPage(idRevenire, connection);
            }
            else if(utilizatorRevenire.equals("superadministrator"))
            {
                SuperadministratorPage stud = new SuperadministratorPage(idRevenire, connection);
            }
        }
    }
}
