import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.sql.*;
import java.util.Vector;

public class SuperadministratorPage extends JFrame implements ActionListener{
    int idSuperadministrator;
    JTable table1;
    JTable table2;
    JPanel panel = new JPanel();
    JPanel p2 = new JPanel();
    JLabel label = new JLabel("Super-administrator Page");
    JButton datePers = new JButton("Date personale");
    JButton editDate = new JButton("Modificare Date");
    JButton vizUtiliz = new JButton("Viz. Utilizatori");
    JButton logOut = new JButton("Logout");
    JButton editare = new JButton("Editare");
    JButton cursuriBTN = new JButton("Cursuri");
    JButton cautareBtn1 = new JButton("Cauta");
    JButton cautareBtn2 = new JButton("Cauta");
    JButton asignareProfBTN = new JButton("Asignare Profesor");
    JButton asignareBTN = new JButton("Asignare");
    JButton backBTN = new JButton("Back");
    JButton backBTN2 = new JButton("Back");
    JRadioButton studentRBTN = new JRadioButton("Student");
    JRadioButton profesorRBTN = new JRadioButton("Profesor");
    JRadioButton adminRBTN = new JRadioButton("Administrator");
    JTextField numeTxt = new JTextField();
    JTextField prenumeTxt = new JTextField();
    JTextField materieTxt = new JTextField();
    JCheckBox cursBox = new JCheckBox("Curs");
    JCheckBox seminarBox = new JCheckBox("Seminar");
    JCheckBox laboratorBox = new JCheckBox("Laborator");
    JTextField numeCursTxt = new JTextField();
    JTextField numeStudentTxt = new JTextField();
    JTextField prenumeStudentTxt = new JTextField();
    JButton inscriereBTN1 = new JButton("Inscriere student");
    JButton inscriereBTN2 = new JButton("Inscriere");
    JFrame f = new JFrame();
    JFrame f2 = new JFrame();
    Border border = BorderFactory.createLineBorder(Color.lightGray);
    Connection connection;
    boolean curs = false, seminar = false, laborator = false;
    int idMaterie;
    public SuperadministratorPage(int idSuperadministrator, Connection connection) {

        this.connection = connection;

        this.idSuperadministrator = idSuperadministrator;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 550);
        this.setTitle("Pagina unui Super-administrator");

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);

        label.setBounds((this.getWidth() - 250)/2, 10, 250, 30);
        label.setFont(new Font("Calibri", Font.BOLD, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label);

        this.add(panel, BorderLayout.CENTER);
        datePers.setBounds(180, 80, 150, 30);
        datePers.setBorder(border);
        datePers.addActionListener(this);
        panel.add(datePers);

        cursuriBTN.setBounds(180, 130, 150, 30);
        cursuriBTN.setBorder(border);
        cursuriBTN.addActionListener(this);
        panel.add(cursuriBTN);

        vizUtiliz.setBounds(180, 180, 150, 30);
        vizUtiliz.setBorder(border);
        vizUtiliz.addActionListener(this);
        panel.add(vizUtiliz);

        editDate.setBounds(180, 230, 150, 30);
        editDate.setBorder(border);
        editDate.addActionListener(this);
        panel.add(editDate);

        asignareProfBTN.setBounds(180, 280, 150, 30);
        asignareProfBTN.setBorder(border);
        asignareProfBTN.addActionListener(this);
        panel.add(asignareProfBTN);

        inscriereBTN1.setBounds(180, 330, 150, 30);
        inscriereBTN1.setBorder(border);
        inscriereBTN1.addActionListener(this);
        panel.add(inscriereBTN1);

        logOut.setBounds((this.getWidth()-80)/2, 475, 80, 30);
        logOut.setBorder(border);
        logOut.addActionListener(this);
        panel.add(logOut);

        panel.setBackground(Color.lightGray);
        this.setContentPane(panel);
        this.setLayout(null);
        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == logOut)
        {
            this.dispose();
            LoginPage  login = new LoginPage(connection);
        }
        if(e.getSource() == backBTN)
        {
            f.dispose();
            SuperadministratorPage admin = new SuperadministratorPage(idSuperadministrator, connection);
        }
        if(e.getSource() == backBTN2)
        {
            f2.dispose();
            SuperadministratorPage admin = new SuperadministratorPage(idSuperadministrator, connection);
        }
        if(e.getSource() == asignareProfBTN)
        {
            this.dispose();
            asignareProfesor();
        }
        if(e.getSource() == inscriereBTN1)
        {
            this.dispose();
            inscriereCursuri();
        }
        if(e.getSource() == inscriereBTN2)
        {
            Statement selectStatement1 = null;
            ResultSet rs1 = null;
            Statement selectStatement2 = null;
            ResultSet rs2 = null;
            Statement selectStatement3 = null;
            ResultSet rs3 = null;
            Statement selectStatement4 = null;
            ResultSet rs4 = null;
            Statement selectStatement5 = null;
            ResultSet rs5 = null;
            try
            {
                selectStatement1 = connection.createStatement();
                selectStatement1.execute("SELECT * FROM materie WHERE nume_materie = \"" + numeCursTxt.getText() + "\";");
                rs1 = selectStatement1.getResultSet();
                selectStatement2 = connection.createStatement();
                selectStatement2.execute("SELECT * FROM structura_materie WHERE nume_materie = \"" + numeCursTxt.getText() + "\";");
                rs2 = selectStatement2.getResultSet();
                if (rs1.next() && rs2.next())
                {
                    selectStatement3 = connection.createStatement();
                    selectStatement3.execute("SELECT * FROM utilizator WHERE nume = \"" + numeStudentTxt.getText() + "\" and prenume = \"" + prenumeStudentTxt.getText() + "\" ;");
                    rs3 = selectStatement3.getResultSet();
                    if(rs3.next())
                    {
                        selectStatement4 = connection.createStatement();
                        selectStatement4.execute("SELECT * FROM inscriere_la_activitati WHERE idStudent = " + rs3.getInt("id") + " and idMaterie = " + rs1.getInt("idMaterie") + ";");
                        rs4 = selectStatement4.getResultSet();
                        if (rs4.next())
                        {
                            Error e2 = new Error("Studentul e deja inscris la curs!");
                        }
                        else
                        {
                            selectStatement5 = connection.createStatement();
                            selectStatement5.execute("SELECT idStructura FROM structura_materie WHERE nume_materie = \"" + numeCursTxt.getText() + "\" and tip_activitate = \"" + "curs" + "\" and nr_studenti = (SELECT MIN(nr_studenti) FROM structura_materie WHERE nume_materie = \"" + numeCursTxt.getText() + "\" and tip_activitate = \"" + "curs" + "\");");
                            rs5 = selectStatement5.getResultSet();
                            if (rs5.next()) {
                                CallableStatement call1 = null;
                                call1 = connection.prepareCall("call inscriere_studenti(?,?,?)");
                                call1.setInt(1, rs3.getInt("id"));
                                call1.setInt(2, rs1.getInt("idMaterie"));
                                call1.setInt(3, rs5.getInt("idStructura"));
                                call1.execute();
                            }
                            selectStatement5 = connection.createStatement();
                            selectStatement5.execute("SELECT idStructura FROM structura_materie WHERE nume_materie = \"" + numeCursTxt.getText() + "\" and tip_activitate = \"" + "seminar" + "\" and nr_studenti = (SELECT MIN(nr_studenti) FROM structura_materie WHERE nume_materie = \"" + numeCursTxt.getText() + "\" and tip_activitate = \"" + "seminar" + "\");");
                            rs5 = selectStatement5.getResultSet();
                            if (rs5.next()) {
                                CallableStatement call1 = null;
                                call1 = connection.prepareCall("call inscriere_studenti(?,?,?)");
                                call1.setInt(1, rs3.getInt("id"));
                                call1.setInt(2, rs1.getInt("idMaterie"));
                                call1.setInt(3, rs5.getInt("idStructura"));
                                call1.execute();
                            }
                            selectStatement5 = connection.createStatement();
                            selectStatement5.execute("SELECT idStructura FROM structura_materie WHERE nume_materie = \"" + numeCursTxt.getText() + "\" and tip_activitate = \"" + "laborator" + "\" and nr_studenti = (SELECT MIN(nr_studenti) FROM structura_materie WHERE nume_materie = \"" + numeCursTxt.getText() + "\" and tip_activitate = \"" + "laborator" + "\");");
                            rs5 = selectStatement5.getResultSet();
                            if (rs5.next()) {
                                CallableStatement call1 = null;
                                call1 = connection.prepareCall("call inscriere_studenti(?,?,?)");
                                call1.setInt(1, rs3.getInt("id"));
                                call1.setInt(2, rs1.getInt("idMaterie"));
                                call1.setInt(3, rs5.getInt("idStructura"));
                                call1.execute();
                            }
                            Succes s1 = new Succes("Student inscris cu succes!");
                        }
                    }
                    else
                    {
                        Error e1 = new Error("Student inexistent!");
                    }
                }
                else
                {
                    Error e1 = new Error("Materie inexistenta!");
                }
            } catch (SQLException sqlex) {
                System.err.println("An SQL Exception occured. Details are provided below:");
                sqlex.printStackTrace(System.err);
            }
        }
        if(e.getSource() == asignareBTN)
        {
            Statement selectStatement1 = null;
            ResultSet rs1 = null;
            Statement selectStatement2 = null;
            ResultSet rs2 = null;
            Statement selectStatement3 = null;
            ResultSet rs3 = null;
            try {
                selectStatement1 = connection.createStatement();
                selectStatement2 = connection.createStatement();
                selectStatement1.execute("SELECT * FROM profesor WHERE idProfesor = ANY (SELECT id FROM utilizator WHERE nume=\"" + numeTxt.getText() + "\" and prenume=\"" + prenumeTxt.getText() + "\");");
                rs1 = selectStatement1.getResultSet();
                selectStatement2.execute("SELECT * FROM materie WHERE nume_materie =\"" + materieTxt.getText() + "\";");
                rs2 = selectStatement2.getResultSet();
                if (rs1.next())
                {
                    if(rs2.next())
                    {
                        selectStatement3 = connection.createStatement();
                        selectStatement3.execute("SELECT * FROM distributie_materie WHERE idMaterie = " + rs2.getInt("idMaterie") + " and idProfesor = " + rs1.getInt("idProfesor") + ";");
                        rs3 = selectStatement3.getResultSet();
                        if(rs3.next())
                        {
                            Error e3 = new Error("Profesor deja asignat!");
                        }
                        else
                        {
                            if (cursBox.isSelected())
                                curs = true;
                            if (seminarBox.isSelected())
                                seminar = true;
                            if (laboratorBox.isSelected())
                                laborator = true;
                            if(curs == false && seminar == false && laborator == false)
                            {
                                Error e4 = new Error("Selectati tipul de materie!");
                            }
                            else
                            {
                                CallableStatement call1 = null;
                                call1 = connection.prepareCall("call asignare_profesor(?,?,?,?,?,?)");
                                call1.setInt(1, rs1.getInt("idProfesor"));
                                call1.setInt(2, rs2.getInt("idMaterie"));
                                call1.setBoolean(3, curs);
                                call1.setBoolean(4, seminar);
                                call1.setBoolean(5, laborator);
                                call1.setString(6, materieTxt.getText());
                                call1.execute();
                                curs = false;
                                seminar = false;
                                laborator = false;
                                Succes s1 = new Succes("Profesor asignat cu succes");
                            }
                        }
                    }
                    else
                    {
                        Error e2 = new Error("Materie inexistenta!");
                    }
                }
                else
                {
                    Error e1 = new Error("Profesor inexistent!");
                }
            } catch (SQLException sqlex) {
                System.err.println("An SQL Exception occured. Details are provided below:");
                sqlex.printStackTrace(System.err);
            }

        }
        if(e.getSource() == datePers)
        {
            this.dispose();
            DatePersonale date = new DatePersonale(idSuperadministrator, connection, "superadministrator","superadministrator", idSuperadministrator);
        }
        if(e.getSource() == cursuriBTN){
            this.dispose();
            cautareCursuri();
        }
        if(e.getSource() == vizUtiliz){
            this.dispose();
            vizualizareUtilizatori();
        }
        if(e.getSource() == editDate)
        {
            this.dispose();
            editareDate();
        }
        if (e.getSource() == cautareBtn1)
        {
            int idCautare = 0;
            Statement selectStatement1 = null;
            ResultSet rs1 = null;
            if(studentRBTN.isSelected())
            {
                try {
                    selectStatement1 = connection.createStatement();
                    selectStatement1.execute("SELECT * FROM student WHERE idStudent = ANY (SELECT id FROM utilizator WHERE nume=\"" + numeTxt.getText() + "\" and prenume=\"" + prenumeTxt.getText() + "\")");
                    rs1 = selectStatement1.getResultSet();
                    if (rs1.next())
                    {
                        idCautare = rs1.getInt("idStudent");
                    }
                    else
                    {
                        Error e1 = new Error("Student inexistent!");
                    }
                } catch (SQLException sqlex) {
                    System.err.println("An SQL Exception occured. Details are provided below:");
                    sqlex.printStackTrace(System.err);
                }
            }
            else if(profesorRBTN.isSelected())
            {
                try {
                    selectStatement1 = connection.createStatement();
                    selectStatement1.execute("SELECT * FROM profesor WHERE idProfesor = ANY (SELECT id FROM utilizator WHERE nume=\"" + numeTxt.getText() + "\" and prenume=\"" + prenumeTxt.getText() + "\")");
                    rs1 = selectStatement1.getResultSet();
                    if (rs1.next())
                    {
                        idCautare = rs1.getInt("idProfesor");
                    }
                    else
                    {
                        Error e2 = new Error("Profesor inexistent!");
                    }
                } catch (SQLException sqlex) {
                    System.err.println("An SQL Exception occured. Details are provided below:");
                    sqlex.printStackTrace(System.err);
                }
            }
            else if(adminRBTN.isSelected())
            {
                try {
                    selectStatement1 = connection.createStatement();
                    selectStatement1.execute("SELECT id FROM utilizator WHERE nume=\"" + numeTxt.getText() + "\" and prenume=\"" + prenumeTxt.getText() + "\"");
                    rs1 = selectStatement1.getResultSet();
                    if (rs1.next())
                    {
                        idCautare = rs1.getInt("id");
                    }
                    else
                    {
                        Error e3 = new Error("Administrator inexistent!");
                    }
                } catch (SQLException sqlex) {
                    System.err.println("An SQL Exception occured. Details are provided below:");
                    sqlex.printStackTrace(System.err);
                }
            }
            else
            {
                Error e4 = new Error("Selectati tipul de utilizator!");
            }
            if(idCautare != 0)
            {
                f.dispose();
                try {
                    selectStatement1 = connection.createStatement();
                    selectStatement1.execute("SELECT * FROM utilizator WHERE id = " + idCautare);
                    rs1 = selectStatement1.getResultSet();
                    if (rs1.next())
                    {
                        if(studentRBTN.isSelected())
                        {
                            DatePersonale student = new DatePersonale(idCautare, connection,"student","superadministrator", idSuperadministrator);
                        }
                        else if(profesorRBTN.isSelected())
                        {
                            DatePersonale profesor = new DatePersonale(idCautare, connection,"profesor","superadministrator", idSuperadministrator);
                        }
                        else if(adminRBTN.isSelected())
                        {
                            DatePersonale admin = new DatePersonale(idCautare, connection,"administrator","superadministrator", idSuperadministrator);
                        }
                    }
                } catch (SQLException sqlex) {
                    System.err.println("An SQL Exception occured. Details are provided below:");
                    sqlex.printStackTrace(System.err);
                }
            }
        }
        if (e.getSource() == cautareBtn2)
        {
            Statement selectStatement1 = null;
            ResultSet rs1 = null;
            try {
                selectStatement1 = connection.createStatement();
                selectStatement1.execute("SELECT * FROM materie WHERE nume_materie =\"" + materieTxt.getText() + "\";");
                rs1 = selectStatement1.getResultSet();
                if (rs1.next()) {
                    f.dispose();
                    idMaterie = rs1.getInt("idMaterie");
                    cursuri(rs1.getString("nume_materie"), rs1.getString("descriere"), rs1.getString("data_inceput"), rs1.getString("data_sfarsit"));
                }
                else
                {
                    Error e1 = new Error("Materie inexistenta");
                }
            } catch (SQLException sqlex) {
                System.err.println("An SQL Exception occured. Details are provided below:");
                sqlex.printStackTrace(System.err);
            }
        }
        if(e.getSource() == editare)
        {
            Statement selectStatement1 = null;
            ResultSet rs1 = null;
            if(studentRBTN.isSelected())
            {
                try
                {
                    selectStatement1 = connection.createStatement();
                    selectStatement1.execute("SELECT * FROM student WHERE idStudent = ANY (SELECT id FROM utilizator WHERE nume=\"" + numeTxt.getText() + "\" and prenume=\"" + prenumeTxt.getText() + "\")");
                    rs1 = selectStatement1.getResultSet();
                    if (rs1.next())
                    {
                        f.dispose();
                        ModificareDate mod = new ModificareDate(rs1.getInt("idStudent"), connection, "student", "superadministrator", idSuperadministrator);
                    }
                    else
                    {
                        Error e1 = new Error("Student inexistent!");
                    }
                } catch (SQLException sqlex) {
                    System.err.println("An SQL Exception occured. Details are provided below:");
                    sqlex.printStackTrace(System.err);
                }
            }
            else if(profesorRBTN.isSelected())
            {
                try
                {
                    selectStatement1 = connection.createStatement();
                    selectStatement1.execute("SELECT * FROM profesor WHERE idProfesor = ANY (SELECT id FROM utilizator WHERE nume=\"" + numeTxt.getText() + "\" and prenume=\"" + prenumeTxt.getText() + "\")");
                    rs1 = selectStatement1.getResultSet();
                    if (rs1.next())
                    {
                        f.dispose();
                        ModificareDate mod = new ModificareDate(rs1.getInt("idProfesor"), connection, "profesor", "superadministrator", idSuperadministrator);
                    }
                    else
                    {
                        Error e2 = new Error("Profesor inexistent!");
                    }
                } catch (SQLException sqlex) {
                    System.err.println("An SQL Exception occured. Details are provided below:");
                    sqlex.printStackTrace(System.err);
                }
            }
            else if(adminRBTN.isSelected())
            {
                try
                {
                    selectStatement1 = connection.createStatement();
                    selectStatement1.execute("SELECT id FROM utilizator WHERE nume=\"" + numeTxt.getText() + "\" and prenume=\"" + prenumeTxt.getText() + "\"");
                    rs1 = selectStatement1.getResultSet();
                    if (rs1.next())
                    {
                        f.dispose();
                        ModificareDate mod = new ModificareDate(rs1.getInt("id"), connection, "administrator", "superadministrator", idSuperadministrator);
                    }
                    else
                    {
                        Error e3 = new Error("Administrator inexistent!");
                    }
                } catch (SQLException sqlex) {
                    System.err.println("An SQL Exception occured. Details are provided below:");
                    sqlex.printStackTrace(System.err);
                }
            }
            else
            {
                Error e4 = new Error("Selectati tipul de utilizator!");
            }
        }
    }
    public void vizualizareUtilizatori()
    {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Utilizator");
        JLabel numeLbl = new JLabel("");
        JLabel prenumeLbl = new JLabel("");

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(450, 350);
        f.setTitle("Vizualizare utilizatori");

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - f.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - f.getHeight()) / 2);
        f.setLocation(x, y);

        label.setBounds((f.getWidth() - 200)/2, 10, 200, 30);
        label.setFont(new Font("Calibri", Font.BOLD, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label);

        numeLbl.setText("Introduceti numele:");
        numeLbl.setBounds(50,60,150,30);
        panel.add(numeLbl);

        numeTxt.setBounds(200, 60, 200, 30);
        numeTxt.setBorder(border);
        panel.add(numeTxt);

        prenumeLbl.setText("Introduceti prenumele:");
        prenumeLbl.setBounds(50,95,150,30);
        panel.add(prenumeLbl);

        prenumeTxt.setBounds(200, 95, 200, 30);
        prenumeTxt.setBorder(border);
        panel.add(prenumeTxt);

        studentRBTN.setBounds(50,130,100,30);
        studentRBTN.setBackground(Color.lightGray);
        profesorRBTN.setBounds(180,130,100,30);
        profesorRBTN.setBackground(Color.lightGray);
        adminRBTN.setBounds(300,130,100,30);
        adminRBTN.setBackground(Color.lightGray);

        ButtonGroup grup = new ButtonGroup();
        grup.add(studentRBTN);
        grup.add(profesorRBTN);
        grup.add(adminRBTN);

        studentRBTN.addActionListener(this);
        profesorRBTN.addActionListener(this);
        adminRBTN.addActionListener(this);

        panel.add(studentRBTN);
        panel.add(profesorRBTN);
        panel.add(adminRBTN);

        cautareBtn1.setBounds((f.getWidth() - 80)/2, 200, 80, 30);
        cautareBtn1.setBorder(border);
        panel.add(cautareBtn1);
        cautareBtn1.addActionListener(this);

        backBTN.setBounds(10, 275, 80, 30);
        backBTN.setBorder(border);
        panel.add(backBTN);
        backBTN.addActionListener(this);

        panel.setBackground(Color.lightGray);
        f.setContentPane(panel);
        f.setLayout(null);
        f.setVisible(true);
    }
    public void editareDate()
    {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Utilizator");
        JLabel numeLbl = new JLabel("");
        JLabel prenumeLbl = new JLabel("");

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(450, 350);
        f.setTitle("Editare date utilizatori");

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - f.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - f.getHeight()) / 2);
        f.setLocation(x, y);

        label.setBounds((f.getWidth() - 200)/2, 10, 200, 30);
        label.setFont(new Font("Calibri", Font.BOLD, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label);

        numeLbl.setText("Introduceti numele:");
        numeLbl.setBounds(50,60,150,30);
        panel.add(numeLbl);

        numeTxt.setBounds(200, 60, 200, 30);
        numeTxt.setBorder(border);
        panel.add(numeTxt);

        prenumeLbl.setText("Introduceti prenumele:");
        prenumeLbl.setBounds(50,95,150,30);
        panel.add(prenumeLbl);

        prenumeTxt.setBounds(200, 95, 200, 30);
        prenumeTxt.setBorder(border);
        panel.add(prenumeTxt);

        studentRBTN.setBounds(50,130,100,30);
        studentRBTN.setBackground(Color.lightGray);
        profesorRBTN.setBounds(180,130,100,30);
        profesorRBTN.setBackground(Color.lightGray);
        adminRBTN.setBounds(300,130,100,30);
        adminRBTN.setBackground(Color.lightGray);

        ButtonGroup grup = new ButtonGroup();
        grup.add(studentRBTN);
        grup.add(profesorRBTN);
        grup.add(adminRBTN);

        studentRBTN.addActionListener(this);
        profesorRBTN.addActionListener(this);
        adminRBTN.addActionListener(this);


        panel.add(studentRBTN);
        panel.add(profesorRBTN);
        panel.add(adminRBTN);

        editare.setBounds((f.getWidth() - 80)/2, 200, 80, 30);
        editare.setBorder(border);
        panel.add(editare);
        editare.addActionListener(this);

        backBTN.setBounds(10, 275, 80, 30);
        backBTN.setBorder(border);
        panel.add(backBTN);
        backBTN.addActionListener(this);

        panel.setBackground(Color.lightGray);
        f.setContentPane(panel);
        f.setLayout(null);
        f.setVisible(true);
    }
    public void cautareCursuri()
    {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Materie");
        JLabel materieLbl = new JLabel("");

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(450, 350);
        f.setTitle("Cautare cursuri");

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - f.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - f.getHeight()) / 2);
        f.setLocation(x, y);

        label.setBounds((f.getWidth() - 200)/2, 10, 200, 30);
        label.setFont(new Font("Calibri", Font.BOLD, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label);

        materieLbl.setText("Introduceti materia:");
        materieLbl.setBounds(50,60,150,30);
        panel.add(materieLbl);

        materieTxt.setBounds(200, 60, 200, 30);
        materieTxt.setBorder(border);
        panel.add(materieTxt);

        cautareBtn2.setBounds((f.getWidth() - 80)/2, 150, 80, 30);
        cautareBtn2.setBorder(border);
        panel.add(cautareBtn2);
        cautareBtn2.addActionListener(this);

        backBTN.setBounds(10, 275, 80, 30);
        backBTN.setBorder(border);
        panel.add(backBTN);
        backBTN.addActionListener(this);

        panel.setBackground(Color.lightGray);
        f.setContentPane(panel);
        f.setLayout(null);
        f.setVisible(true);
    }
    public void cursuri(String nume, String descriere, String dataInceput, String dataSfarsit)
    {

        f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f2.setSize(500, 550);
        f2.setTitle("Vizualizare cursuri");

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - f2.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - f2.getHeight()) / 2);
        f2.setLocation(x, y);

        JLabel label = new JLabel("Vizualizare Curs");
        label.setBounds((f2.getWidth() - 250)/2, 10, 250, 30);
        label.setFont(new Font("Calibri", Font.BOLD, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        p2.add(label);

        JLabel numeMaterie = new JLabel("Nume materie: " + nume);
        numeMaterie.setBounds(50, 40, 250, 30);
        p2.add(numeMaterie);

        JLabel descriereMat = new JLabel("Descriere: " + descriere);
        descriereMat.setBounds(50, 70, 500, 30);
        p2.add(descriereMat);

        JLabel dataInceputMat = new JLabel("Data la care incepe cursul: " + dataInceput);
        dataInceputMat.setBounds(50, 100, 250, 30);
        p2.add(dataInceputMat);

        JLabel dataSfarsitMat = new JLabel("Data la care se incheie cursul: " + dataSfarsit);
        dataSfarsitMat.setBounds(50, 130, 250, 30);
        p2.add(dataSfarsitMat);

        JLabel profi = new JLabel("Profesorii asignati acestei materii:");
        profi.setBounds((f2.getWidth() - 260) / 2, 180, 260, 30);
        profi.setFont(new Font("Calibri", Font.BOLD, 18));
        profi.setHorizontalAlignment(SwingConstants.CENTER);
        profi.setVerticalAlignment(SwingConstants.CENTER);
        p2.add(profi);

        JLabel studenti = new JLabel("Studentii inscrisi:");
        studenti.setBounds((f2.getWidth() - 200) / 2, 330, 200, 30);
        studenti.setFont(new Font("Calibri", Font.BOLD, 18));
        studenti.setHorizontalAlignment(SwingConstants.CENTER);
        studenti.setVerticalAlignment(SwingConstants.CENTER);
        p2.add(studenti);

        Statement selectStatement1 = null;
        Statement selectStatement2 = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        Statement selectStatement3 = null;
        Statement selectStatement4 = null;
        ResultSet rs3 = null;
        ResultSet rs4 = null;
        Vector columnNames1 = new Vector();
        Vector data1 = new Vector();
        Vector columnNames2 = new Vector();
        Vector data2 = new Vector();
        try {
            selectStatement1 = connection.createStatement();
            selectStatement1.execute("SELECT distinct(idProfesor) FROM distributie_materie WHERE idMaterie = " + idMaterie + ";");
            rs1 = selectStatement1.getResultSet();
            columnNames1.addElement("Nume");
            columnNames1.addElement("Prenume");
            while (rs1.next()) {
                Vector row = new Vector();
                selectStatement2 = connection.createStatement();
                selectStatement2.execute("SELECT nume, prenume FROM utilizator WHERE id = " + rs1.getInt("idProfesor"));
                rs2 = selectStatement2.getResultSet();
                if(rs2.next())
                {
                    row.addElement(rs2.getObject(1));
                    row.addElement(rs2.getObject(2));
                    data1.addElement(row);
                }
            }
            selectStatement3 = connection.createStatement();
            selectStatement3.execute("SELECT distinct(idStudent) FROM inscriere_la_activitati WHERE idMaterie = " + idMaterie + ";");
            rs3 = selectStatement3.getResultSet();
            columnNames2.addElement("Nume");
            columnNames2.addElement("Prenume");
            while (rs3.next()) {
                Vector row = new Vector();
                selectStatement4 = connection.createStatement();
                selectStatement4.execute("SELECT nume, prenume FROM utilizator WHERE id = " + rs3.getInt("idStudent"));
                rs4 = selectStatement4.getResultSet();
                if(rs4.next())
                {
                    row.addElement(rs4.getObject(1));
                    row.addElement(rs4.getObject(2));
                    data2.addElement(row);
                }
            }
            Rezultat1(data1, columnNames1, data2, columnNames2);
        } catch (SQLException sqlex) {
            System.err.println("An SQL Exception occured. Details are provided below:");
            sqlex.printStackTrace(System.err);
        }

        backBTN2.setBounds(10, 475, 80, 30);
        backBTN2.setBorder(border);
        p2.add(backBTN2);
        backBTN2.addActionListener(this);

        p2.setBackground(Color.lightGray);
        f2.setContentPane(p2);
        f2.setLayout(null);
        f2.setVisible(true);
    }
    public void Rezultat1(Vector data1, Vector columnNames1, Vector data2, Vector columnNames2) {

        table1 = new JTable(data1, columnNames1);
        table1.setBorder(border);

        JScrollPane sp1 = new JScrollPane(table1);
        sp1.setBounds((f2.getWidth() - 400)/2, 210, 400, 100);
        sp1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        sp1.setBorder(border);

        table2 = new JTable(data2, columnNames2);
        table2.setBorder(border);

        JScrollPane sp2 = new JScrollPane(table2);
        sp2.setBounds((f2.getWidth() - 400)/2, 360, 400, 100);
        sp2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        sp2.setBorder(border);

        p2.add(sp1);
        p2.add(sp2);
    }
    public void asignareProfesor()
    {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Asignare Profesor la curs");
        JLabel numeLbl = new JLabel();
        JLabel prenumeLbl = new JLabel();
        JLabel materieLbl = new JLabel();

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(450, 350);
        f.setTitle("Asignare profesor");

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - f.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - f.getHeight()) / 2);
        f.setLocation(x, y);

        label.setBounds((f.getWidth() - 250)/2, 10, 250, 30);
        label.setFont(new Font("Calibri", Font.BOLD, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label);

        numeLbl.setText("Introduceti numele:");
        numeLbl.setBounds(50,60,150,30);
        panel.add(numeLbl);

        numeTxt.setBounds(200, 60, 200, 30);
        numeTxt.setBorder(border);
        panel.add(numeTxt);

        prenumeLbl.setText("Introduceti prenumele:");
        prenumeLbl.setBounds(50,95,150,30);
        panel.add(prenumeLbl);

        prenumeTxt.setBounds(200, 95, 200, 30);
        prenumeTxt.setBorder(border);
        panel.add(prenumeTxt);

        materieLbl.setText("Introduceti materia:");
        materieLbl.setBounds(50,130,150,30);
        panel.add(materieLbl);

        materieTxt.setBounds(200, 130, 200, 30);
        materieTxt.setBorder(border);
        panel.add(materieTxt);

        cursBox.setBounds(80, 170, 100, 30);
        cursBox.setBackground(Color.lightGray);
        panel.add(cursBox);
        seminarBox.setBounds(180, 170, 100, 30);
        seminarBox.setBackground(Color.lightGray);
        panel.add(seminarBox);
        laboratorBox.setBounds(280, 170, 100, 30);
        laboratorBox.setBackground(Color.lightGray);
        panel.add(laboratorBox);

        asignareBTN.setBounds((f.getWidth() - 80)/2, 230, 80, 30);
        asignareBTN.setBorder(border);
        panel.add(asignareBTN);
        asignareBTN.addActionListener(this);

        backBTN.setBounds(10, 275, 80, 30);
        backBTN.setBorder(border);
        panel.add(backBTN);
        backBTN.addActionListener(this);

        panel.setBackground(Color.lightGray);
        f.setContentPane(panel);
        f.setLayout(null);
        f.setVisible(true);
    }
    public void inscriereCursuri()
    {
        JLabel label = new JLabel("Inscriere la activitati");
        JLabel numeStudentlbl = new JLabel("Nume student:");
        JLabel prenumeStudentlbl = new JLabel("Prenume student:");
        JLabel numeCurslbl = new JLabel("Nume materie:");
        JPanel p1 = new JPanel();

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(450, 350);
        f.setTitle("Inscriere la activitati");

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - f.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - f.getHeight()) / 2);
        f.setLocation(x, y);

        label.setBounds((f.getWidth() - 200)/2, 10, 200, 30);
        label.setFont(new Font("Calibri", Font.BOLD, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        p1.add(label);

        numeStudentlbl.setBounds(40, 50, 120, 30);
        p1.add(numeStudentlbl);

        prenumeStudentlbl.setBounds(40, 85, 120, 30);
        p1.add(prenumeStudentlbl);

        numeCurslbl.setBounds(40, 120, 100, 30);
        p1.add(numeCurslbl);

        numeStudentTxt.setBounds(170, 50, 200, 30);
        numeStudentTxt.setBorder(border);
        p1.add(numeStudentTxt);

        prenumeStudentTxt.setBounds(170, 85, 200, 30);
        prenumeStudentTxt.setBorder(border);
        p1.add(prenumeStudentTxt);

        numeCursTxt.setBounds(170, 120, 200, 30);
        numeCursTxt.setBorder(border);
        p1.add(numeCursTxt);

        inscriereBTN2.setBounds((f.getWidth() - 100) / 2, 200, 100, 30);
        inscriereBTN2.setBorder(border);
        inscriereBTN2.addActionListener(this);
        p1.add(inscriereBTN2);

        backBTN.setBounds(10, 275, 80, 30);
        backBTN.setBorder(border);
        backBTN.addActionListener(this);
        p1.add(backBTN);

        p1.setBackground(Color.lightGray);
        f.setContentPane(p1);
        f.setLayout(null);
        f.setVisible(true);
    }
}
