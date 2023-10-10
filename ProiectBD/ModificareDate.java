import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.border.Border;
public class ModificareDate extends JFrame implements ActionListener{
    JPanel panel = new JPanel();
    JLabel label = new JLabel("Date Personale", SwingConstants.CENTER);
    JButton backBtn = new JButton("BACK");
    JButton edit = new JButton("Editare");
    JLabel nume = new JLabel("Nume:");
    JLabel prenume = new JLabel("Prenume:");
    JLabel cnp = new JLabel("CNP:");
    JLabel email = new JLabel("Email:");
    JLabel telefon = new JLabel("Telefon:");
    JLabel IBAN = new JLabel("IBAN:");
    JLabel adresa = new JLabel("Adresa:");
    JLabel nrContract = new JLabel("Numarul de contract:");
    JTextField numeTxt = new JTextField();
    JTextField prenumeTxt = new JTextField();
    JTextField cnpTxt = new JTextField();
    JTextField emailTxt = new JTextField();
    JTextField telefonTxt = new JTextField();
    JTextField IBANTxt = new JTextField();
    JTextField adresaTxt = new JTextField();
    JTextField nrContractTxt = new JTextField();
    JTextField anStudiuTxt = new JTextField();
    JTextField departamentTxt = new JTextField();
    JTextField nrOreMinTxt = new JTextField();
    JTextField nrOreMaxTxt = new JTextField();
    Border border = BorderFactory.createLineBorder(Color.gray);
    Connection connection;
    String utilizator;
    String utilizatorRevenire;
    int id;
    int idRevenire;
    public ModificareDate(int id, Connection connection, String utilizator, String utilizatorRevenire, int idRevenire){

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

        nume.setBounds(50, 50, 100, 30);
        panel.add(nume);
        numeTxt.setBounds(200, 50, 200, 30);
        numeTxt.setBorder(border);
        panel.add(numeTxt);

        prenume.setBounds(50, 85, 100, 30);
        panel.add(prenume);
        prenumeTxt.setBounds(200, 85, 200, 30);
        prenumeTxt.setBorder(border);
        panel.add(prenumeTxt);

        cnp.setBounds(50, 120, 100, 30);
        panel.add(cnp);
        cnpTxt.setBounds(200, 120, 200, 30);
        cnpTxt.setBorder(border);
        panel.add(cnpTxt);

        email.setBounds(50, 155, 100, 30);
        panel.add(email);
        emailTxt.setBounds(200, 155, 200, 30);
        emailTxt.setBorder(border);
        panel.add(emailTxt);

        telefon.setBounds(50, 190, 100, 30);
        panel.add(telefon);
        telefonTxt.setBounds(200, 190, 200, 30);
        telefonTxt.setBorder(border);
        panel.add(telefonTxt);

        IBAN.setBounds(50, 225, 100, 30);
        panel.add(IBAN);
        IBANTxt.setBounds(200, 225, 200, 30);
        IBANTxt.setBorder(border);
        panel.add(IBANTxt);

        adresa.setBounds(50, 260, 100, 30);
        panel.add(adresa);
        adresaTxt.setBounds(200, 260, 200, 30);
        adresaTxt.setBorder(border);
        panel.add(adresaTxt);

        nrContract.setBounds(50, 295, 100, 30);
        panel.add(nrContract);
        nrContractTxt.setBounds(200, 295, 200, 30);
        nrContractTxt.setBorder(border);
        panel.add(nrContractTxt);

        edit.setBounds(180, 445, 150, 30);
        edit.setBorder(border);
        edit.addActionListener(this);
        panel.add(edit);

        panel.setBackground(Color.gray);

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
                numeTxt.setText(rs1.getString("nume"));
                prenumeTxt.setText(rs1.getString("prenume"));
                cnpTxt.setText(rs1.getString("CNP"));
                emailTxt.setText(rs1.getString("email"));
                telefonTxt.setText(rs1.getString("nr_telefon"));
                IBANTxt.setText(rs1.getString("IBAN"));
                adresaTxt.setText(rs1.getString("adresa"));
                nrContractTxt.setText(String.valueOf(rs1.getInt("nr_contract")));
            }
            if(utilizator.equals("student"))
            {
                selectStatement2 = connection.createStatement();
                selectStatement2.execute("SELECT * FROM student WHERE idStudent =\"" +  id + "\";");
                rs2 = selectStatement2.getResultSet();
                if(rs2.next())
                {
                    JLabel anStudiu = new JLabel("Anul de studiu:");
                    anStudiu.setBounds(50, 330, 100, 30);
                    panel.add(anStudiu);
                    anStudiuTxt.setText(String.valueOf(rs2.getInt("an_studiu")));
                    anStudiuTxt.setBounds(200, 330, 200, 30);
                    anStudiuTxt.setBorder(border);
                    panel.add(anStudiuTxt);
                }
                this.setTitle("Student");
            }
            if(utilizator.equals("profesor"))
            {
                selectStatement2 = connection.createStatement();
                selectStatement2.execute("SELECT * FROM profesor WHERE idProfesor =\"" +  id + "\";");
                rs2 = selectStatement2.getResultSet();
                if(rs2.next())
                {
                    JLabel departament = new JLabel("Departamentul:");
                    departament.setBounds(50, 330, 100, 30);
                    panel.add(departament);
                    departamentTxt.setText(rs2.getString("departament"));
                    departamentTxt.setBounds(200, 330, 200, 30);
                    departamentTxt.setBorder(border);
                    panel.add(departamentTxt);

                    JLabel nrOreMin = new JLabel("Nr. minim de ore:");
                    nrOreMin.setBounds(50, 365, 100, 30);
                    panel.add(nrOreMin);
                    nrOreMinTxt.setText(String.valueOf(rs2.getInt("nr_min_ore")));
                    nrOreMinTxt.setBounds(200, 365, 200, 30);
                    nrOreMinTxt.setBorder(border);
                    panel.add(nrOreMinTxt);

                    JLabel nrOreMax = new JLabel("Nr. minim de ore:");
                    nrOreMax.setBounds(50, 400, 100, 30);
                    panel.add(nrOreMax);
                    nrOreMaxTxt.setText(String.valueOf(rs2.getInt("nr_max_ore")));
                    nrOreMaxTxt.setBounds(200, 400, 200, 30);
                    nrOreMaxTxt.setBorder(border);
                    panel.add(nrOreMaxTxt);
                }
                this.setTitle("Profesor");
            }
            if(utilizator.equals("administrator"))
            {
                this.setTitle("Administrator");
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
            if(utilizatorRevenire.equals("administrator"))
            {
                AdministratorPage administrator = new AdministratorPage(idRevenire, connection);
            }
            else if(utilizatorRevenire.equals("superadministrator"))
            {
                SuperadministratorPage stud = new SuperadministratorPage(idRevenire, connection);
            }
        }
        if(e.getSource() == edit)
        {
            Succes s = new Succes("Date actualizate cu succes");
            try {
                CallableStatement call1 = null;
                call1 = connection.prepareCall("call update_utilizator(?,?,?,?,?,?,?,?,?)");
                call1.setInt(1, id);
                call1.setString(2,cnpTxt.getText());
                call1.setString(3,numeTxt.getText());
                call1.setString(4,prenumeTxt.getText());
                call1.setString(5,adresaTxt.getText());
                call1.setString(6,telefonTxt.getText());
                call1.setString(7,emailTxt.getText());
                call1.setString(8,IBANTxt.getText());
                call1.setInt(9, Integer.valueOf(nrContractTxt.getText()));
                call1.execute();
            }
            catch(SQLException sqlex) {
                System.err.println("An SQL Exception occured. Details are provided below:");
                sqlex.printStackTrace(System.err);
            }
            if(utilizator.equals("student"))
            {
                try {
                    CallableStatement call2 = null;
                    call2 = connection.prepareCall("call update_student(?,?)");
                    call2.setInt(1, id);
                    call2.setInt(2, Integer.valueOf(anStudiuTxt.getText()));
                    call2.execute();
                }
                catch(SQLException sqlex) {
                    System.err.println("An SQL Exception occured. Details are provided below:");
                    sqlex.printStackTrace(System.err);
                }
            }
            if(utilizator.equals("profesor"))
            {
                try {
                    CallableStatement call2 = null;
                    call2 = connection.prepareCall("call update_profesor(?,?,?,?)");
                    call2.setInt(1, id);
                    call2.setString(2, departamentTxt.getText());
                    call2.setInt(3, Integer.valueOf(nrOreMinTxt.getText()));
                    call2.setInt(4, Integer.valueOf(nrOreMaxTxt.getText()));
                    call2.execute();
                }
                catch(SQLException sqlex) {
                    System.err.println("An SQL Exception occured. Details are provided below:");
                    sqlex.printStackTrace(System.err);
                }
            }
        }
    }
}
