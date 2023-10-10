import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

public class StudentPage extends JFrame implements ActionListener{
    int idStudent;
    JFrame f1 = new JFrame();
    JFrame f2 = new JFrame();
    JPanel p1 = new JPanel();
    JPanel p = new JPanel();
    JPanel p2 = new JPanel();
    JScrollPane sp;
    JTextField numeCursTxt = new JTextField();
    JButton inscriereBTN2 = new JButton("Inscriere");
    JButton renuntareBTN2 = new JButton("Renuntare");
    JButton renuntareBTN1 = new JButton("Renuntare Curs");
    JButton vizualizareCursBTN = new JButton("Vizualizare Cursuri");
    JButton sugestiiBTN1 = new JButton("Sugestii ore grup");
    JButton sugestiiBTN2 = new JButton("Sugestii participanti grup");
    JButton sugestiiBTN3 = new JButton("Sugestii participanti");
    JTable table;
    JButton logOut = new JButton("Logout");
    JLabel studentPage = new JLabel("Student Page");
    JButton datePers = new JButton("Date personale");
    JButton inscriereCursBTN = new JButton("Incriere Curs");
    JButton backBTN1 = new JButton("Back");
    JButton backBTN2 = new JButton("Back");
    JButton orarBtn = new JButton("Orar");
    JButton noteBtn= new JButton("Vizualizare Note");
    JButton activitatiBTN = new JButton("Activitati curente");
    JButton creareActivitateBTN = new JButton("Creare activitate de grup");
    JButton inscriereActivitateBTN = new JButton("Inscriere activitate de grup");
    JButton renuntareActivitateBTN = new JButton("Renuntare activitate de grup");
    JButton viualizareStudentiGrupBTN = new JButton("Vizualizare studenti participanti");
    JButton viualizareMesajeBTN = new JButton("Vizualizare mesaje");
    JTextField numeActivitateTxt = new JTextField();
    JTextField nrMinPTxt = new JTextField();
    JTextField dataActivitateTxt = new JTextField("YYYY-MM-DD");
    JTextField oraActivitateTxt = new JTextField("00:00");
    JTextField durataActivitateTxt = new JTextField();
    JButton creareActivitateBTN2 = new JButton("Creare");
    JButton inscriereActivitateBTN2 = new JButton("Inscriere");
    JButton renuntareActivitateBTN2 = new JButton("Renuntare");
    JButton vizualizareGrupBTN = new JButton("Grupuri de studiu");
    JTextField mesajGrup = new JTextField();
    JButton mesajGrupBTN = new JButton("Trimitere mesaj");
    Border border = BorderFactory.createLineBorder(Color.lightGray);
    Connection connection;
    String numeActivitateGrup;
    int idGrup;
    String numeStd;
    public StudentPage(int idStudent, Connection connection) {

        this.connection = connection;

        this.idStudent = idStudent;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 550);
        this.setTitle("Pagina unui student");

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);

        studentPage.setBounds((this.getWidth() - 200)/2, 10, 200, 30);
        studentPage.setFont(new Font("Calibri", Font.BOLD, 20));
        studentPage.setHorizontalAlignment(SwingConstants.CENTER);
        p.add(studentPage);

        datePers.setBounds(50, 80, 180, 30);
        datePers.setBorder(border);
        datePers.addActionListener(this);
        p.add(datePers);

        inscriereCursBTN.setBounds(50, 130, 180, 30);
        inscriereCursBTN.setBorder(border);
        inscriereCursBTN.addActionListener(this);
        p.add(inscriereCursBTN);

        renuntareBTN1.setBounds(50, 180, 180, 30);
        renuntareBTN1.setBorder(border);
        renuntareBTN1.addActionListener(this);
        p.add(renuntareBTN1);

        activitatiBTN.setBounds(50, 230, 180, 30);
        activitatiBTN.setBorder(border);
        activitatiBTN.addActionListener(this);
        p.add(activitatiBTN);

        orarBtn.setBounds(50, 280, 180, 30);
        orarBtn.setBorder(border);
        orarBtn.addActionListener(this);
        p.add(orarBtn);

        sugestiiBTN1.setBounds(50, 330, 180, 30);
        sugestiiBTN1.setBorder(border);
        sugestiiBTN1.addActionListener(this);
        p.add(sugestiiBTN1);

        vizualizareCursBTN.setBounds((this.getWidth() - 180) / 2, 380, 180, 30);
        vizualizareCursBTN.setBorder(border);
        vizualizareCursBTN.addActionListener(this);
        p.add(vizualizareCursBTN);

        noteBtn.setBounds(250, 80, 180, 30);
        noteBtn.setBorder(border);
        noteBtn.addActionListener(this);
        p.add(noteBtn);

        creareActivitateBTN.setBounds(250, 130, 180, 30);
        creareActivitateBTN.setBorder(border);
        creareActivitateBTN.addActionListener(this);
        p.add(creareActivitateBTN);

        inscriereActivitateBTN.setBounds(250, 180, 180, 30);
        inscriereActivitateBTN.setBorder(border);
        inscriereActivitateBTN.addActionListener(this);
        p.add(inscriereActivitateBTN);

        renuntareActivitateBTN.setBounds(250, 230, 180, 30);
        renuntareActivitateBTN.setBorder(border);
        renuntareActivitateBTN.addActionListener(this);
        p.add(renuntareActivitateBTN);

        vizualizareGrupBTN.setBounds(250, 280, 180, 30);
        vizualizareGrupBTN.setBorder(border);
        vizualizareGrupBTN.addActionListener(this);
        p.add(vizualizareGrupBTN);

        sugestiiBTN2.setBounds(250, 330, 180, 30);
        sugestiiBTN2.setBorder(border);
        sugestiiBTN2.addActionListener(this);
        p.add(sugestiiBTN2);

        logOut.setBounds((this.getWidth()-80)/2, 475, 80, 30);
        logOut.setBorder(border);
        logOut.addActionListener(this);
        p.add(logOut);

        p.setBackground(Color.lightGray);
        this.setContentPane(p);
        this.setLayout(null);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == datePers)
        {
            this.setVisible(false);
            DatePersonale date = new DatePersonale(idStudent, connection, "student", "student", idStudent);
        }
        if(e.getSource() == logOut)
        {
            this.setVisible(false);
            LoginPage  login = new LoginPage(connection);
        }
        if(e.getSource() == inscriereCursBTN)
        {
            this.setVisible(false);
            inscriereCursuri();
        }
        if(e.getSource() == renuntareBTN1)
        {
            this.setVisible(false);
            renuntareCursuri();
        }
        if(e.getSource() == backBTN1)
        {
            f1.setVisible(false);
            StudentPage s = new StudentPage(idStudent, connection);
        }
        if(e.getSource() == orarBtn)
        {
            this.setVisible(false);
            orarActivitati();
        }
        if(e.getSource() == noteBtn)
        {
            this.setVisible(false);
            vizualizareNote();
        }
        if(e.getSource() == creareActivitateBTN)
        {
            this.setVisible(false);
            creareActivitate();
        }
        if(e.getSource() == inscriereActivitateBTN)
        {
            this.setVisible(false);
            inscriereActivitate();
        }
        if(e.getSource() == renuntareActivitateBTN)
        {
            this.setVisible(false);
            renuntareActivitate();
        }
        if(e.getSource() == vizualizareCursBTN)
        {
            this.setVisible(false);
            vizualizareCursuri();
        }
        if(e.getSource() == sugestiiBTN1)
        {
            this.setVisible(false);
            sugestiiOre();
        }
        if(e.getSource() == sugestiiBTN2)
        {
            this.setVisible(false);
            sugestiiParticipanti();
        }
        if(e.getSource() == sugestiiBTN3)
        {
            f1.setVisible(false);
            participantiGrup();
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
                    selectStatement3.execute("SELECT * FROM inscriere_la_activitati WHERE idStudent = " + idStudent + " and idMaterie = " + rs1.getInt("idMaterie")+ ";");
                    rs3 = selectStatement3.getResultSet();
                    if(rs3.next())
                    {
                        Error e2 = new Error("Sunteti deja inscris la curs!");
                    }
                    else
                    {
                        selectStatement4 = connection.createStatement();
                        selectStatement4.execute("SELECT idStructura FROM structura_materie WHERE nume_materie = \"" + numeCursTxt.getText() + "\" and tip_activitate = \"" + "curs" + "\" and nr_studenti = (SELECT MIN(nr_studenti) FROM structura_materie WHERE nume_materie = \"" + numeCursTxt.getText() + "\" and tip_activitate = \"" + "curs" + "\");");
                        rs4 = selectStatement4.getResultSet();
                        if(rs4.next())
                        {
                            CallableStatement call1 = null;
                            call1 = connection.prepareCall("call inscriere_studenti(?,?,?)");
                            call1.setInt(1, idStudent);
                            call1.setInt(2, rs1.getInt("idMaterie"));
                            call1.setInt(3, rs4.getInt("idStructura"));
                            call1.execute();
                        }
                        selectStatement4 = connection.createStatement();
                        selectStatement4.execute("SELECT idStructura FROM structura_materie WHERE nume_materie = \"" + numeCursTxt.getText() + "\" and tip_activitate = \"" + "seminar" + "\" and nr_studenti = (SELECT MIN(nr_studenti) FROM structura_materie WHERE nume_materie = \"" + numeCursTxt.getText() + "\" and tip_activitate = \"" + "seminar" + "\");");
                        rs4 = selectStatement4.getResultSet();
                        if(rs4.next())
                        {
                            CallableStatement call1 = null;
                            call1 = connection.prepareCall("call inscriere_studenti(?,?,?)");
                            call1.setInt(1, idStudent);
                            call1.setInt(2, rs1.getInt("idMaterie"));
                            call1.setInt(3, rs4.getInt("idStructura"));
                            call1.execute();
                        }
                        selectStatement4 = connection.createStatement();
                        selectStatement4.execute("SELECT idStructura FROM structura_materie WHERE nume_materie = \"" + numeCursTxt.getText() + "\" and tip_activitate = \"" + "laborator" + "\" and nr_studenti = (SELECT MIN(nr_studenti) FROM structura_materie WHERE nume_materie = \"" + numeCursTxt.getText() + "\" and tip_activitate = \"" + "laborator" + "\");");
                        rs4 = selectStatement4.getResultSet();
                        if(rs4.next())
                        {
                            CallableStatement call1 = null;
                            call1 = connection.prepareCall("call inscriere_studenti(?,?,?)");
                            call1.setInt(1, idStudent);
                            call1.setInt(2, rs1.getInt("idMaterie"));
                            call1.setInt(3, rs4.getInt("idStructura"));
                            call1.execute();
                        }
                        Succes s1 = new Succes("V-ati inscris cu succes!");
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
        if(e.getSource() == renuntareBTN2)
        {
            Statement selectStatement1 = null;
            ResultSet rs1 = null;
            Statement selectStatement2 = null;
            ResultSet rs2 = null;
            Statement selectStatement3 = null;
            ResultSet rs3 = null;
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
                    selectStatement3.execute("SELECT * FROM inscriere_la_activitati WHERE idStudent = " + idStudent + " and idMaterie = " + rs1.getInt("idMaterie")+ ";");
                    rs3 = selectStatement3.getResultSet();
                    int ok = 0;
                    while(rs3.next())
                    {
                        CallableStatement call1 = null;
                        call1 = connection.prepareCall("call reactualizare_structura(?,?)");
                        call1.setInt(1, idStudent);
                        call1.setInt(2, rs3.getInt("idStructura"));
                        call1.execute();
                        ok = 1;
                    }
                    if(ok == 0)
                    {
                        Error e2 = new Error("Nu sunteti inscris la acest curs!");
                    }
                    else
                    {
                        CallableStatement call1 = null;
                        call1 = connection.prepareCall("call renuntare_studenti(?,?)");
                        call1.setInt(1, idStudent);
                        call1.setInt(2, rs1.getInt("idMaterie"));
                        call1.execute();
                        Succes s1 = new Succes("Ati renuntat la acest curs!");
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
        if(e.getSource() == creareActivitateBTN2)
        {
            Statement selectStatement1 = null;
            ResultSet rs1 = null;
            Statement selectStatement2 = null;
            ResultSet rs2 = null;
            Statement selectStatement3 = null;
            ResultSet rs3 = null;
            try
            {
                selectStatement1 = connection.createStatement();
                selectStatement1.execute("SELECT * FROM materie WHERE nume_materie = \"" + numeActivitateTxt.getText() + "\";");
                rs1 = selectStatement1.getResultSet();
                if(rs1.next())
                {
                    selectStatement2 = connection.createStatement();
                    selectStatement2.execute("SELECT * FROM inscriere_la_activitati WHERE idMaterie = " + rs1.getInt("idMaterie") + " and idStudent = " + idStudent + ";");
                    rs2 = selectStatement2.getResultSet();
                    if (rs2.next()) {
                        selectStatement3 = connection.createStatement();
                        selectStatement3.execute("SELECT * FROM grup_studiu WHERE nume_materie = \"" + numeActivitateTxt.getText() + "\";");
                        rs3 = selectStatement3.getResultSet();
                        if(rs3.next()) {
                            Error e1 = new Error("Activitatea de grup exista deja!");
                        }
                        else {
                            CallableStatement call1 = null;
                            call1 = connection.prepareCall("call creare_activitate(?,?,?,?,?,?)");
                            call1.setInt(1, idStudent);
                            call1.setString(2, numeActivitateTxt.getText());
                            call1.setInt(3, Integer.valueOf(nrMinPTxt.getText()));
                            call1.setString(4, dataActivitateTxt.getText());
                            call1.setString(5, oraActivitateTxt.getText());
                            call1.setInt(6, Integer.valueOf(durataActivitateTxt.getText()));
                            call1.execute();
                            Succes s1 = new Succes("Ati creat activitatea cu succes!");
                        }
                    }
                    else
                    {
                        Error e2 = new Error("Nu urmati acest curs!");
                    }
                }
                else
                {
                    Error e2 = new Error("Materie inexistenta!");
                }
            } catch (SQLException sqlex) {
                System.err.println("An SQL Exception occured. Details are provided below:");
                sqlex.printStackTrace(System.err);
            }
        }
        if(e.getSource() == inscriereActivitateBTN2)
        {
            Statement selectStatement1 = null;
            ResultSet rs1 = null;
            Statement selectStatement2 = null;
            ResultSet rs2 = null;
            Statement selectStatement3 = null;
            ResultSet rs3 = null;
            Statement selectStatement4 = null;
            ResultSet rs4 = null;
            try
            {
                selectStatement1 = connection.createStatement();
                selectStatement1.execute("SELECT * FROM materie WHERE nume_materie = \"" + numeActivitateTxt.getText() + "\";");
                rs1 = selectStatement1.getResultSet();
                if(rs1.next())
                {
                    selectStatement2 = connection.createStatement();
                    selectStatement2.execute("SELECT * FROM inscriere_la_activitati WHERE idMaterie = " + rs1.getInt("idMaterie") + " and idStudent = " + idStudent + ";");
                    rs2 = selectStatement2.getResultSet();
                    if (rs2.next()) {
                        selectStatement3 = connection.createStatement();
                        selectStatement3.execute("SELECT * FROM grup_studiu WHERE nume_materie = \"" + numeActivitateTxt.getText() + "\";");
                        rs3 = selectStatement3.getResultSet();
                        if(rs3.next())
                        {
                            selectStatement4 = connection.createStatement();
                            selectStatement4.execute("SELECT * FROM inscriere_grup_studiu WHERE idStudent = " + idStudent + " and idGrup = " + rs3.getInt("idGrup") + ";");
                            rs4 = selectStatement4.getResultSet();
                            if(rs4.next())
                            {
                                Error e5 = new Error("Sunteti deja inscris la activitate!");
                            }
                            else {
                                CallableStatement call1 = null;
                                call1 = connection.prepareCall("call inscriere_activitate_grup(?,?)");
                                call1.setInt(1, idStudent);
                                call1.setString(2, numeActivitateTxt.getText());
                                call1.execute();
                                Succes s1 = new Succes("V-ati inscris cu succes!");
                            }
                        }
                        else {
                            Error e1 = new Error("Activitatea de grup nu exista!");
                        }
                    }
                    else
                    {
                        Error e2 = new Error("Nu urmati acest curs!");
                    }
                }
                else
                {
                    Error e2 = new Error("Materie inexistenta!");
                }
            } catch (SQLException sqlex) {
                System.err.println("An SQL Exception occured. Details are provided below:");
                sqlex.printStackTrace(System.err);
            }
        }
        if(e.getSource() == renuntareActivitateBTN2)
        {
            Statement selectStatement1 = null;
            ResultSet rs1 = null;
            Statement selectStatement2 = null;
            ResultSet rs2 = null;
            Statement selectStatement3 = null;
            ResultSet rs3 = null;
            Statement selectStatement4 = null;
            ResultSet rs4 = null;
            try
            {
                selectStatement1 = connection.createStatement();
                selectStatement1.execute("SELECT * FROM materie WHERE nume_materie = \"" + numeActivitateTxt.getText() + "\";");
                rs1 = selectStatement1.getResultSet();
                if(rs1.next())
                {
                    selectStatement2 = connection.createStatement();
                    selectStatement2.execute("SELECT * FROM inscriere_la_activitati WHERE idMaterie = " + rs1.getInt("idMaterie") + " and idStudent = " + idStudent + ";");
                    rs2 = selectStatement2.getResultSet();
                    if (rs2.next()) {
                        selectStatement3 = connection.createStatement();
                        selectStatement3.execute("SELECT * FROM grup_studiu WHERE nume_materie = \"" + numeActivitateTxt.getText() + "\";");
                        rs3 = selectStatement3.getResultSet();
                        if(rs3.next())
                        {
                            selectStatement4 = connection.createStatement();
                            selectStatement4.execute("SELECT * FROM inscriere_grup_studiu WHERE idStudent = " + idStudent + " and idGrup = " + rs3.getInt("idGrup") + ";");
                            rs4 = selectStatement4.getResultSet();
                            if(rs4.next())
                            {
                                CallableStatement call1 = null;
                                call1 = connection.prepareCall("call renuntare_activitate_grup(?,?)");
                                call1.setInt(1, idStudent);
                                call1.setString(2, numeActivitateTxt.getText());
                                call1.execute();
                                Succes s1 = new Succes("Ati renuntat la activitate!");
                            }
                            else {
                                Error e5 = new Error("Nu sunteti inscris la activitate!");
                            }
                        }
                        else {
                            Error e1 = new Error("Activitatea de grup nu exista!");
                        }
                    }
                    else
                    {
                        Error e2 = new Error("Nu urmati acest curs!");
                    }
                }
                else
                {
                    Error e2 = new Error("Materie inexistenta!");
                }
            } catch (SQLException sqlex) {
                System.err.println("An SQL Exception occured. Details are provided below:");
                sqlex.printStackTrace(System.err);
            }
        }
        if(e.getSource() == activitatiBTN){
            this.setVisible(false);
            activitatiViit();
        }
        if(e.getSource() == vizualizareGrupBTN){
            this.setVisible(false);
            grupuri();
        }
        if(e.getSource() == viualizareStudentiGrupBTN){
            f1.setVisible(false);
            studentiGrup();
        }
        if(e.getSource() == backBTN2){
            f2.setVisible(false);
            StudentPage s = new StudentPage(idStudent, connection);
        }
        if(e.getSource() == viualizareMesajeBTN){
            f1.setVisible(false);
            mesajeGrup();
        }
        if(e.getSource() == mesajGrupBTN)
        {
            p2.remove(sp);
            Statement selectStatement1 = null;
            Statement selectStatement2 = null;
            Statement selectStatement3= null;
            ResultSet rs1 = null;
            ResultSet rs2 = null;
            ResultSet rs3 = null;
            try
            {
                selectStatement1 = connection.createStatement();
                selectStatement1.execute("SELECT * FROM grup_studiu WHERE nume_materie = \"" + numeActivitateGrup + "\";");
                rs1 = selectStatement1.getResultSet();
                if(rs1.next())
                {
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();
                    CallableStatement call1 = null;
                    call1 = connection.prepareCall("call trimitere_mesaj(?,?,?,?)");
                    call1.setInt(1, idStudent);
                    call1.setString(2, now.toString());
                    call1.setInt(3, rs1.getInt("idGrup"));
                    call1.setString(4, mesajGrup.getText());
                    call1.execute();
                }
            } catch (SQLException sqlex) {
                System.err.println("An SQL Exception occured. Details are provided below:");
                sqlex.printStackTrace(System.err);
            }

            Vector columnNames = new Vector();
            Vector data = new Vector();


            columnNames.addElement("Nume");
            columnNames.addElement("Prenume");
            columnNames.addElement("Mesaj");
            columnNames.addElement("Data mesaj");

            try {
                selectStatement1 = connection.createStatement();
                selectStatement1.execute("SELECT distinct * FROM grup_studiu WHERE nume_materie = \"" + numeActivitateGrup + "\";");
                rs1 = selectStatement1.getResultSet();
                if(rs1.next())
                {
                    selectStatement2 = connection.createStatement();
                    selectStatement2.execute("SELECT * FROM mesaje_grup WHERE idGrup = " + rs1.getInt("idGrup") + ";");
                    rs2 = selectStatement2.getResultSet();
                    while (rs2.next()) {
                        Vector row = new Vector();
                        selectStatement3 = connection.createStatement();
                        selectStatement3.execute("SELECT nume, prenume FROM utilizator WHERE id = " + rs2.getInt("idStudent"));
                        rs3 = selectStatement3.getResultSet();
                        if (rs3.next()) {
                            row.addElement(rs3.getObject("nume"));
                            row.addElement(rs3.getObject("prenume"));
                            row.addElement(rs2.getString("mesaj"));
                            row.addElement(rs2.getString("data_mesaj"));
                            data.addElement(row);
                        }
                    }
                }
            } catch (SQLException sqlex) {
                System.err.println("An SQL Exception occured. Details are provided below:");
                sqlex.printStackTrace(System.err);
            }

            table = new JTable(data, columnNames);
            table.setBorder(border);

            table.getColumnModel().getColumn(0).setPreferredWidth(100);
            table.getColumnModel().getColumn(1).setPreferredWidth(100);
            table.getColumnModel().getColumn(2).setPreferredWidth(400);
            table.getColumnModel().getColumn(3).setPreferredWidth(100);

            table.getTableHeader().setResizingAllowed(false);
            table.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 16));

            sp = new JScrollPane(table);
            sp.setBounds((f2.getWidth() - 800)/2, 50, 800, 300);
            sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            sp.setBorder(border);
            p2.add(sp);
            mesajGrup.setText("");
        }
    }
    public void inscriereCursuri()
    {
        JLabel label = new JLabel("Inscriere la activitati");
        JLabel numeCurslbl = new JLabel("Nume materie:");

        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setSize(450, 350);
        f1.setTitle("Inscriere la activitati");

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - f1.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - f1.getHeight()) / 2);
        f1.setLocation(x, y);

        label.setBounds((f1.getWidth() - 200)/2, 10, 200, 30);
        label.setFont(new Font("Calibri", Font.BOLD, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        p1.add(label);

        numeCurslbl.setBounds(50, 80, 100, 30);
        p1.add(numeCurslbl);

        numeCursTxt.setBounds(170, 80, 200, 30);
        numeCursTxt.setBorder(border);
        p1.add(numeCursTxt);

        inscriereBTN2.setBounds((f1.getWidth() - 100) / 2, 150, 100, 30);
        inscriereBTN2.setBorder(border);
        inscriereBTN2.addActionListener(this);
        p1.add(inscriereBTN2);

        backBTN1.setBounds(10, 275, 80, 30);
        backBTN1.setBorder(border);
        backBTN1.addActionListener(this);
        p1.add(backBTN1);

        p1.setBackground(Color.lightGray);
        f1.setContentPane(p1);
        f1.setLayout(null);
        f1.setVisible(true);
    }
    public void renuntareCursuri()
    {
        JLabel label = new JLabel("Renuntare la activitati");
        JLabel numeCurslbl = new JLabel("Nume materie:");

        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setSize(450, 350);
        f1.setTitle("Renuntare la activitati");

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - f1.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - f1.getHeight()) / 2);
        f1.setLocation(x, y);

        label.setBounds((f1.getWidth() - 200)/2, 10, 200, 30);
        label.setFont(new Font("Calibri", Font.BOLD, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        p1.add(label);

        numeCurslbl.setBounds(50, 80, 100, 30);
        p1.add(numeCurslbl);

        numeCursTxt.setBounds(170, 80, 200, 30);
        numeCursTxt.setBorder(border);
        p1.add(numeCursTxt);

        renuntareBTN2.setBounds((f1.getWidth() - 100) / 2, 150, 100, 30);
        renuntareBTN2.setBorder(border);
        renuntareBTN2.addActionListener(this);
        p1.add(renuntareBTN2);

        backBTN1.setBounds(10, 275, 80, 30);
        backBTN1.setBorder(border);
        backBTN1.addActionListener(this);
        p1.add(backBTN1);

        p1.setBackground(Color.lightGray);
        f1.setContentPane(p1);
        f1.setLayout(null);
        f1.setVisible(true);
    }
    public void orarActivitati()
    {
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setSize(1000, 650);
        f1.setTitle("Orar");
        p1.setBackground(Color.lightGray);

        JLabel label = new JLabel("Orar Activitati");
        label.setBounds((f1.getWidth() - 200) / 2, 10, 200, 30);
        label.setFont(new Font("Calibri", Font.BOLD, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        p1.add(label);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - f1.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - f1.getHeight()) / 2);
        f1.setLocation(x, y);

        Statement selectStatement1 = null;
        Statement selectStatement2 = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;

        Vector columnNames = new Vector(6);
        Vector data = new Vector();
        Vector ore = new Vector(6);
        Vector zile = new Vector(5);

        columnNames.addElement("Ora");
        columnNames.addElement("Luni");
        columnNames.addElement("Marti");
        columnNames.addElement("Miercuri");
        columnNames.addElement("Joi");
        columnNames.addElement("Vineri");

        ore.addElement("08:00");
        ore.addElement("10:00");
        ore.addElement("12:00");
        ore.addElement("14:00");
        ore.addElement("16:00");
        ore.addElement("18:00");

        zile.addElement("luni");
        zile.addElement("marti");
        zile.addElement("miercuri");
        zile.addElement("joi");
        zile.addElement("vineri");

        for(int i = 0; i < 6; i++)
        {
            Vector row = new Vector();
            row.addElement(ore.get(i));
            try
            {
                for(int j = 0; j < 5; j++)
                {
                    selectStatement1 = connection.createStatement();
                    selectStatement1.execute("SELECT * FROM structura_materie WHERE ora = \"" + ore.get(i).toString() + "\";");
                    rs1 = selectStatement1.getResultSet();
                    int ok = 0;
                    while (rs1.next())
                    {
                        if(rs1.getString("zi").equals(zile.get(j).toString())) {
                            selectStatement2 = connection.createStatement();
                            selectStatement2.execute("SELECT * FROM inscriere_la_activitati WHERE idStudent = " + idStudent + " and idStructura =" + rs1.getInt("idStructura"));
                            rs2 = selectStatement2.getResultSet();
                            if (rs2.next()) {
                                row.addElement(rs1.getString("nume_materie") + " / " + rs1.getString("tip_activitate"));
                                ok = 1;
                            }
                        }
                    }
                    selectStatement1 = connection.createStatement();
                    selectStatement1.execute("SELECT * FROM grup_studiu WHERE ora = \"" + ore.get(i).toString() + "\";");
                    rs1 = selectStatement1.getResultSet();
                    while(rs1.next())
                    {
                        LocalDate date = LocalDate.parse(rs1.getString("data_desfasurare"));
                        DayOfWeek day_of_week = date.getDayOfWeek();
                        String ziCurenta = "zi";
                        if (day_of_week.getValue() == 1) {
                            ziCurenta = "luni";
                        }
                        if (day_of_week.getValue() == 2) {
                            ziCurenta = "marti";
                        }
                        if (day_of_week.getValue() == 3) {
                            ziCurenta = "miercuri";
                        }
                        if (day_of_week.getValue() == 4) {
                            ziCurenta = "joi";
                        }
                        if (day_of_week.getValue() == 5) {
                            ziCurenta = "vineri";
                        }
                        if(ziCurenta.equals(zile.get(j).toString())) {
                            selectStatement2 = connection.createStatement();
                            selectStatement2.execute("SELECT * FROM inscriere_grup_studiu WHERE idStudent = " + idStudent + " and idGrup =" + rs1.getInt("idGrup"));
                            rs2 = selectStatement2.getResultSet();
                            if (rs2.next()) {
                                row.addElement(rs1.getString("nume_materie") + "/" + rs1.getString("data_desfasurare"));
                                ok = 1;
                            }
                        }
                    }
                    if(ok == 0) {
                        row.addElement(null);
                    }
                }
            } catch (SQLException sqlex)
            {
                System.err.println("An SQL Exception occured. Details are provided below:");
                sqlex.printStackTrace(System.err);
            }
            data.addElement(row);
        }

        table = new JTable(data, columnNames);
        table.setBorder(border);
        table.setRowHeight(80);
        table.setFont(new Font("Calibri", 0, 20));
        table.getTableHeader().setResizingAllowed(false);
        table.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 20));

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds((f1.getWidth() - 900)/2, 50, 900, 511);
        sp.setBorder(border);
        p1.add(sp);

        backBTN1.setBounds(10, 575, 80, 30);
        backBTN1.setBorder(border);
        p1.add(backBTN1);
        backBTN1.addActionListener(this);

        f1.setContentPane(p1);
        f1.setLayout(null);
        f1.setVisible(true);
    }
    public void vizualizareNote()
    {
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setSize(850, 400);
        f1.setTitle("Catalog");
        p1.setBackground(Color.lightGray);

        JLabel label = new JLabel("Vizualizare note");
        label.setBounds((f1.getWidth() - 200) / 2, 10, 200, 30);
        label.setFont(new Font("Calibri", Font.BOLD, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        p1.add(label);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - f1.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - f1.getHeight()) / 2);
        f1.setLocation(x, y);

        Statement selectStatement1 = null;
        Statement selectStatement2 = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;

        Vector columnNames = new Vector(5);
        Vector data = new Vector(5);
        columnNames.addElement("MATERIE");
        columnNames.addElement("NOTA CURS");
        columnNames.addElement("NOTA SEMINAR");
        columnNames.addElement("NOTA LABORATOR");
        columnNames.addElement("NOTA FINALA");
        try
        {
            selectStatement1 = connection.createStatement();
            selectStatement1.execute("SELECT * FROM catalog WHERE idStudent = " + idStudent + ";");
            rs1 = selectStatement1.getResultSet();
            int ok = 0;
            while (rs1.next())
            {
                Vector row = new Vector();
                row.addElement(rs1.getString("nume_materie"));
                row.addElement(rs1.getFloat("nota_curs"));
                row.addElement(rs1.getFloat("nota_seminar"));
                row.addElement(rs1.getFloat("nota_lab"));
                row.addElement(rs1.getFloat("nota_finala"));
                data.addElement(row);
            }
        } catch (SQLException sqlex)
        {
            System.err.println("An SQL Exception occured. Details are provided below:");
            sqlex.printStackTrace(System.err);
        }

        table = new JTable(data, columnNames);
        table.setBorder(border);
        table.setRowHeight(40);
        table.setFont(new Font("Calibri", 0, 20));
        table.getTableHeader().setResizingAllowed(false);
        table.getTableHeader().setFont(new Font("Calibri", 0, 20));

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds((f1.getWidth() - 800)/2, 50, 800, 250);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        sp.setBorder(border);
        p1.add(sp);

        backBTN1.setBounds(10, 325, 80, 30);
        backBTN1.setBorder(border);
        p1.add(backBTN1);
        backBTN1.addActionListener(this);

        f1.setContentPane(p1);
        f1.setLayout(null);
        f1.setVisible(true);
    }
    public void creareActivitate()
    {
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setSize(450, 350);
        f1.setTitle("Creare Activitati Grup");
        p1.setBackground(Color.lightGray);

        JLabel label = new JLabel("Creare activitati de grup");
        label.setBounds((f1.getWidth() - 250) / 2, 10, 250, 30);
        label.setFont(new Font("Calibri", Font.BOLD, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        p1.add(label);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - f1.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - f1.getHeight()) / 2);
        f1.setLocation(x, y);

        JLabel numeMaterieLbl = new JLabel("Nume materie:");
        numeMaterieLbl.setBounds(40, 50, 100, 30);
        p1.add(numeMaterieLbl);

        numeActivitateTxt.setBounds(170, 50, 200, 30);
        numeActivitateTxt.setBorder(border);
        p1.add(numeActivitateTxt);

        JLabel nrMinPLbl = new JLabel("Nr. min. participanti:");
        nrMinPLbl.setBounds(40, 85, 120, 30);
        p1.add(nrMinPLbl);

        nrMinPTxt.setBounds(170, 85, 200, 30);
        nrMinPTxt.setBorder(border);
        p1.add(nrMinPTxt);

        JLabel dataLbl = new JLabel("Data:");
        dataLbl.setBounds(40, 120, 100, 30);
        p1.add(dataLbl);

        dataActivitateTxt.setBounds(170, 120, 200, 30);
        dataActivitateTxt.setBorder(border);
        p1.add(dataActivitateTxt);

        JLabel oraLbl = new JLabel("Ora:");
        oraLbl.setBounds(40, 155, 100, 30);
        p1.add(oraLbl);

        oraActivitateTxt.setBounds(170, 155, 200, 30);
        oraActivitateTxt.setBorder(border);
        p1.add(oraActivitateTxt);

        JLabel durataLbl = new JLabel("Durata:");
        durataLbl.setBounds(40, 190, 100, 30);
        p1.add(durataLbl);

        durataActivitateTxt.setBounds(170, 190, 200, 30);
        durataActivitateTxt.setBorder(border);
        p1.add(durataActivitateTxt);

        creareActivitateBTN2.setBounds((f1.getWidth() - 100) / 2, 230, 100, 30);
        creareActivitateBTN2.setBorder(border);
        creareActivitateBTN2.addActionListener(this);
        p1.add(creareActivitateBTN2);

        backBTN1.setBounds(10, 275, 80, 30);
        backBTN1.setBorder(border);
        p1.add(backBTN1);
        backBTN1.addActionListener(this);

        f1.setContentPane(p1);
        f1.setLayout(null);
        f1.setVisible(true);
    }
    public void inscriereActivitate()
    {
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setSize(450, 350);
        f1.setTitle("Inscriere Activitati Grup");
        p1.setBackground(Color.lightGray);

        JLabel label = new JLabel("Inscriere activitati de grup");
        label.setBounds((f1.getWidth() - 250) / 2, 10, 250, 30);
        label.setFont(new Font("Calibri", Font.BOLD, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        p1.add(label);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - f1.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - f1.getHeight()) / 2);
        f1.setLocation(x, y);

        JLabel numeMaterieLbl = new JLabel("Nume materie:");
        numeMaterieLbl.setBounds(50, 80, 100, 30);
        p1.add(numeMaterieLbl);

        numeActivitateTxt.setBounds(170, 80, 200, 30);
        numeActivitateTxt.setBorder(border);
        p1.add(numeActivitateTxt);

        inscriereActivitateBTN2.setBounds((f1.getWidth() - 100) / 2, 150, 100, 30);
        inscriereActivitateBTN2.setBorder(border);
        inscriereActivitateBTN2.addActionListener(this);
        p1.add(inscriereActivitateBTN2);

        backBTN1.setBounds(10, 275, 80, 30);
        backBTN1.setBorder(border);
        p1.add(backBTN1);
        backBTN1.addActionListener(this);

        f1.setContentPane(p1);
        f1.setLayout(null);
        f1.setVisible(true);
    }
    public void renuntareActivitate()
    {
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setSize(450, 350);
        f1.setTitle("Renuntare Activitati Grup");
        p1.setBackground(Color.lightGray);

        JLabel label = new JLabel("Renuntare activitati de grup");
        label.setBounds((f1.getWidth() - 250) / 2, 10, 250, 30);
        label.setFont(new Font("Calibri", Font.BOLD, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        p1.add(label);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - f1.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - f1.getHeight()) / 2);
        f1.setLocation(x, y);

        JLabel numeMaterieLbl = new JLabel("Nume materie:");
        numeMaterieLbl.setBounds(50, 80, 100, 30);
        p1.add(numeMaterieLbl);

        numeActivitateTxt.setBounds(170, 80, 200, 30);
        numeActivitateTxt.setBorder(border);
        p1.add(numeActivitateTxt);

        renuntareActivitateBTN2.setBounds((f1.getWidth() - 100) / 2, 150, 100, 30);
        renuntareActivitateBTN2.setBorder(border);
        renuntareActivitateBTN2.addActionListener(this);
        p1.add(renuntareActivitateBTN2);

        backBTN1.setBounds(10, 275, 80, 30);
        backBTN1.setBorder(border);
        p1.add(backBTN1);
        backBTN1.addActionListener(this);

        f1.setContentPane(p1);
        f1.setLayout(null);
        f1.setVisible(true);
    }
    public void activitatiViit(){
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setSize(500, 550);
        f1.setTitle("Activitatile de astazi");
        p1.setBackground(Color.lightGray);

        JLabel label = new JLabel("Activitatile de azi:");
        label.setBounds((f1.getWidth() - 200) / 2, 10, 200, 30);
        label.setFont(new Font("Calibri", Font.BOLD, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        p1.add(label);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - f1.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - f1.getHeight()) / 2);
        f1.setLocation(x, y);

        Statement selectStatement1 = null;
        ResultSet rs1 = null;

        Statement selectStatement2 = null;
        ResultSet rs2 = null;

        Vector columnNames = new Vector(6);
        Vector data = new Vector();
        Vector ore = new Vector();

        columnNames.addElement("Ore");
        columnNames.addElement("Astazi");

        ore.addElement("08:00");
        ore.addElement("10:00");
        ore.addElement("12:00");
        ore.addElement("14:00");
        ore.addElement("16:00");
        ore.addElement("18:00");

        LocalDate date = LocalDate.now();
        DayOfWeek day_of_week = date.getDayOfWeek();
        String ziCurenta = "zi";
        if (day_of_week.getValue() == 1) {
            ziCurenta = "luni";
        }
        if (day_of_week.getValue() == 2) {
            ziCurenta = "marti";
        }
        if (day_of_week.getValue() == 3) {
            ziCurenta = "miercuri";
        }
        if (day_of_week.getValue() == 4) {
            ziCurenta = "joi";
        }
        if (day_of_week.getValue() == 5) {
            ziCurenta = "vineri";
        }
        for (int i = 0; i < 6; i++) {
            Vector row = new Vector();
            row.addElement(ore.get(i));
            try {
                selectStatement1 = connection.createStatement();
                selectStatement1.execute("SELECT * FROM structura_materie WHERE  ora = \"" + ore.get(i).toString() + "\";");
                rs1 = selectStatement1.getResultSet();
                int ok = 0;
                while (rs1.next()) {
                    selectStatement2 = connection.createStatement();
                    selectStatement2.execute("SELECT * FROM inscriere_la_activitati WHERE idStudent = " + idStudent + " and idStructura =" + rs1.getInt("idStructura"));
                    rs2 = selectStatement2.getResultSet();
                    if (rs2.next()) {
                        if (rs1.getString("zi").equals(ziCurenta)) {
                            row.addElement(rs1.getString("nume_materie") + " / " + rs1.getString("tip_activitate"));
                            ok = 1;
                        }
                    }
                }
                if (ok == 0)
                    row.addElement(null);
            } catch (SQLException sqlex) {
                System.err.println("An SQL Exception occured. Details are provided below:");
                sqlex.printStackTrace(System.err);
            }
            data.addElement(row);
        }

        table = new JTable(data, columnNames);
        table.setBorder(border);
        table.setRowHeight(61);
        table.setFont(new Font("Calibri", 0, 20));
        table.getTableHeader().setResizingAllowed(false);
        table.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 20));

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds((f1.getWidth() - 400)/2, 50, 400, 397);
        sp.setBorder(border);
        p1.add(sp);

        backBTN1.setBounds(10, 470, 80, 30);
        backBTN1.setBorder(border);
        p1.add(backBTN1);
        backBTN1.addActionListener(this);

        f1.setContentPane(p1);
        f1.setLayout(null);
        f1.setVisible(true);
    }
    public void grupuri() {
        Statement selectStatement1 = null;
        Statement selectStatement2 = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;

        Vector columnNames = new Vector();
        Vector data = new Vector();

        try {
            selectStatement1 = connection.createStatement();
            selectStatement1.execute("SELECT distinct * FROM inscriere_grup_studiu WHERE idStudent = " + idStudent + ";");
            rs1 = selectStatement1.getResultSet();
            columnNames.addElement("Nume activitate grup");
            while (rs1.next()) {
                Vector row = new Vector();
                selectStatement2 = connection.createStatement();
                selectStatement2.execute("SELECT nume_materie FROM grup_studiu WHERE idGrup = " + rs1.getInt("idGrup"));
                rs2 = selectStatement2.getResultSet();
                if(rs2.next())
                {
                    row.addElement(rs2.getObject("nume_materie"));
                    data.addElement(row);
                }
            }
            Rezultat1(data, columnNames);
        } catch (SQLException sqlex) {
            System.err.println("An SQL Exception occured. Details are provided below:");
            sqlex.printStackTrace(System.err);
        }
    }
    public void Rezultat1(Vector data, Vector columnNames) {

        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setSize(500, 550);
        f1.setTitle("Grup de studiu");
        p1.setBackground(Color.lightGray);

        JLabel label = new JLabel("Vizualizarea activitatii de grup");
        label.setBounds((this.getWidth() - 200) / 2, 10, 200, 30);
        label.setFont(new Font("Calibri", Font.BOLD, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        p1.add(label);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - f1.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - f1.getHeight()) / 2);
        f1.setLocation(x, y);

        backBTN1.setBounds(10, 475, 80, 30);
        backBTN1.setBorder(border);
        p1.add(backBTN1);
        backBTN1.addActionListener(this);

        table = new JTable(data, columnNames);
        table.setBorder(border);

        ListSelectionModel model;
        model = table.getSelectionModel();
        model.addListSelectionListener(new ListSelectionListener()
        {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!model.isSelectionEmpty())
                {
                    int column = 0;
                    int row = table.getSelectedRow();
                    numeActivitateGrup = table.getModel().getValueAt(row, column).toString();
                }
            }
        });

        viualizareStudentiGrupBTN.setBounds((f1.getWidth() - 150) / 2, 300, 200, 30);
        viualizareStudentiGrupBTN.setBorder(border);
        p1.add(viualizareStudentiGrupBTN);
        viualizareStudentiGrupBTN.addActionListener(this);

        viualizareMesajeBTN.setBounds((f1.getWidth() - 150) / 2, 350, 200, 30);
        viualizareMesajeBTN.setBorder(border);
        p1.add(viualizareMesajeBTN);
        viualizareMesajeBTN.addActionListener(this);

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds((f1.getWidth() - 400)/2, 50, 400, 200);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        sp.setBorder(border);
        f1.setContentPane(p1);
        f1.add(sp);
        f1.setLayout(null);
        f1.setVisible(true);
    }
    public void studentiGrup() {
        Statement selectStatement1 = null;
        Statement selectStatement2 = null;
        Statement selectStatement3 = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        ResultSet rs3 = null;

        Vector columnNames = new Vector();
        Vector data = new Vector();

        try {
            selectStatement1 = connection.createStatement();
            selectStatement1.execute("SELECT distinct * FROM grup_studiu WHERE nume_materie = \"" + numeActivitateGrup + "\";");
            rs1 = selectStatement1.getResultSet();
            if(rs1.next())
                idGrup = rs1.getInt("idGrup");
            selectStatement2 = connection.createStatement();
            selectStatement2.execute("SELECT distinct * FROM inscriere_grup_studiu WHERE idGrup = " + idGrup + ";");
            rs2 = selectStatement2.getResultSet();
            columnNames.addElement("Nume");
            columnNames.addElement("Prenume");
            while (rs2.next()) {
                Vector row = new Vector();
                selectStatement3 = connection.createStatement();
                selectStatement3.execute("SELECT nume, prenume FROM utilizator WHERE id = " + rs2.getInt("idStudent"));
                rs3 = selectStatement3.getResultSet();
                if(rs3.next())
                {
                    row.addElement(rs3.getObject("nume"));
                    row.addElement(rs3.getObject("prenume"));
                    data.addElement(row);
                }
            }
            Rezultat2(data, columnNames);
        } catch (SQLException sqlex) {
            System.err.println("An SQL Exception occured. Details are provided below:");
            sqlex.printStackTrace(System.err);
        }
    }
    public void Rezultat2(Vector data, Vector columnNames) {

        f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f2.setSize(500, 550);
        f2.setTitle("Grup de studiu");
        p2.setBackground(Color.lightGray);

        JLabel label = new JLabel("Vizualizare Participanti");
        label.setBounds((f2.getWidth() - 200) / 2, 10, 200, 30);
        label.setFont(new Font("Calibri", Font.BOLD, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        p2.add(label);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - f2.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - f2.getHeight()) / 2);
        f2.setLocation(x, y);

        backBTN2.setBounds(10, 475, 80, 30);
        backBTN2.setBorder(border);
        p2.add(backBTN2);
        backBTN2.addActionListener(this);

        table = new JTable(data, columnNames);
        table.setBorder(border);

        ListSelectionModel model;
        model = table.getSelectionModel();
        model.addListSelectionListener(new ListSelectionListener()
        {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!model.isSelectionEmpty())
                {
                    int column = 0;
                    int row = table.getSelectedRow();
                    numeStd = table.getModel().getValueAt(row, column).toString();
                }
            }
        });
        table.getTableHeader().setResizingAllowed(false);
        table.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 18));
        table.setFont(new Font("Calibri", Font.BOLD, 16));
        table.setRowHeight(25);

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds((f2.getWidth() - 400)/2, 50, 400, 300);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        sp.setBorder(border);
        p2.add(sp);

        f2.setContentPane(p2);
        f2.setLayout(null);
        f2.setVisible(true);
    }
    public void mesajeGrup() {
        Statement selectStatement1 = null;
        Statement selectStatement2 = null;
        Statement selectStatement3= null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        ResultSet rs3 = null;

        Vector columnNames = new Vector();
        Vector data = new Vector();


        columnNames.addElement("Nume");
        columnNames.addElement("Prenume");
        columnNames.addElement("Mesaj");
        columnNames.addElement("Data mesaj");

        try {
            selectStatement1 = connection.createStatement();
            selectStatement1.execute("SELECT distinct * FROM grup_studiu WHERE nume_materie = \"" + numeActivitateGrup + "\";");
            rs1 = selectStatement1.getResultSet();
            if(rs1.next())
            {
                selectStatement2 = connection.createStatement();
                selectStatement2.execute("SELECT * FROM mesaje_grup WHERE idGrup = " + rs1.getInt("idGrup") + ";");
                rs2 = selectStatement2.getResultSet();
                while (rs2.next()) {
                    Vector row = new Vector();
                    selectStatement3 = connection.createStatement();
                    selectStatement3.execute("SELECT nume, prenume FROM utilizator WHERE id = " + rs2.getInt("idStudent"));
                    rs3 = selectStatement3.getResultSet();
                    if (rs3.next()) {
                        row.addElement(rs3.getObject("nume"));
                        row.addElement(rs3.getObject("prenume"));
                        row.addElement(rs2.getString("mesaj"));
                        row.addElement(rs2.getString("data_mesaj"));
                        data.addElement(row);
                    }
                }
            }
            Rezultat5(data, columnNames);
        } catch (SQLException sqlex) {
            System.err.println("An SQL Exception occured. Details are provided below:");
            sqlex.printStackTrace(System.err);
        }
    }
    public void Rezultat5(Vector data, Vector columnNames) {

        f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f2.setSize(900, 500);
        f2.setTitle("Grup de studiu");
        p2.setBackground(Color.lightGray);

        JLabel label = new JLabel("Vizualizare Mesaje");
        label.setBounds((f2.getWidth() - 200) / 2, 10, 200, 30);
        label.setFont(new Font("Calibri", Font.BOLD, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        p2.add(label);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - f2.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - f2.getHeight()) / 2);
        f2.setLocation(x, y);

        backBTN2.setBounds(10, 425, 80, 30);
        backBTN2.setBorder(border);
        p2.add(backBTN2);
        backBTN2.addActionListener(this);

        table = new JTable(data, columnNames);
        table.setBorder(border);

        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(400);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);

        table.getTableHeader().setResizingAllowed(false);
        table.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 16));

        sp = new JScrollPane(table);
        sp.setBounds((f2.getWidth() - 800)/2, 50, 800, 300);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        sp.setBorder(border);
        p2.add(sp);

        mesajGrup.setBounds(270, 380, 300,30);
        mesajGrup.setBorder(border);
        p2.add(mesajGrup);

        mesajGrupBTN.setBounds(570, 380, 120, 30);
        mesajGrupBTN.setBorder(border);
        p2.add(mesajGrupBTN);
        mesajGrupBTN.addActionListener(this);

        f2.setContentPane(p2);
        f2.setLayout(null);
        f2.setVisible(true);
    }
    public void vizualizareCursuri() {

        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setSize(500, 350);
        f1.setTitle("Cursuri");
        p1.setBackground(Color.lightGray);

        JLabel label = new JLabel("Vizualizare cursuri!");
        label.setBounds((this.getWidth() - 200) / 2, 10, 200, 30);
        label.setFont(new Font("Calibri", Font.BOLD, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        p1.add(label);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - f1.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - f1.getHeight()) / 2);
        f1.setLocation(x, y);

        backBTN1.setBounds(10, 275, 80, 30);
        backBTN1.setBorder(border);
        p1.add(backBTN1);
        backBTN1.addActionListener(this);

        Statement selectStatement1 = null;
        Statement selectStatement2 = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;

        Vector columnNames = new Vector();
        Vector data = new Vector();

        try {
            selectStatement1 = connection.createStatement();
            selectStatement1.execute("SELECT distinct idMaterie FROM inscriere_la_activitati WHERE idStudent = " + idStudent + ";");
            rs1 = selectStatement1.getResultSet();
            columnNames.addElement("Nume materie");
            while (rs1.next()) {
                Vector row = new Vector();
                selectStatement2 = connection.createStatement();
                selectStatement2.execute("SELECT nume_materie FROM materie WHERE idMaterie = " + rs1.getInt("idMaterie"));
                rs2 = selectStatement2.getResultSet();
                if(rs2.next())
                {
                    row.addElement(rs2.getObject(1));
                    data.addElement(row);
                }
            }
        } catch (SQLException sqlex) {
            System.err.println("An SQL Exception occured. Details are provided below:");
            sqlex.printStackTrace(System.err);
        }

        table = new JTable(data, columnNames);
        table.setBorder(border);
        table.getTableHeader().setResizingAllowed(false);
        table.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 20));
        table.setRowHeight(30);
        table.setFont(new Font("Calibri", 0, 20));

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds((f1.getWidth() - 400)/2, 50, 400, 200);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        sp.setBorder(border);
        p1.add(sp);

        f1.setContentPane(p1);
        f1.setLayout(null);
        f1.setVisible(true);
    }
    public void sugestiiOre()
    {
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setSize(1000, 650);
        f1.setTitle("Sugestii ore");
        p1.setBackground(Color.lightGray);

        JLabel label = new JLabel("Sugestii ore pentru activitati de grup");
        label.setBounds((f1.getWidth() - 300) / 2, 10, 300, 30);
        label.setFont(new Font("Calibri", Font.BOLD, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        p1.add(label);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - f1.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - f1.getHeight()) / 2);
        f1.setLocation(x, y);

        Statement selectStatement1 = null;
        Statement selectStatement2 = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;

        Vector columnNames = new Vector(6);
        Vector data = new Vector();
        Vector ore = new Vector(6);
        Vector zile = new Vector(5);

        columnNames.addElement("Ora");
        columnNames.addElement("Luni");
        columnNames.addElement("Marti");
        columnNames.addElement("Miercuri");
        columnNames.addElement("Joi");
        columnNames.addElement("Vineri");

        ore.addElement("08:00");
        ore.addElement("10:00");
        ore.addElement("12:00");
        ore.addElement("14:00");
        ore.addElement("16:00");
        ore.addElement("18:00");

        zile.addElement("luni");
        zile.addElement("marti");
        zile.addElement("miercuri");
        zile.addElement("joi");
        zile.addElement("vineri");

        table = new JTable(data, columnNames);

        for(int i = 0; i < 6; i++)
        {
            Vector row = new Vector();
            row.addElement(ore.get(i));
            try
            {
                for(int j = 0; j < 5; j++)
                {
                    selectStatement1 = connection.createStatement();
                    selectStatement1.execute("SELECT * FROM structura_materie WHERE ora = \"" + ore.get(i).toString() + "\";");
                    rs1 = selectStatement1.getResultSet();
                    int ok = 0;
                    while (rs1.next())
                    {
                        if(rs1.getString("zi").equals(zile.get(j).toString())) {
                            selectStatement2 = connection.createStatement();
                            selectStatement2.execute("SELECT * FROM inscriere_la_activitati WHERE idStudent = " + idStudent + " and idStructura =" + rs1.getInt("idStructura"));
                            rs2 = selectStatement2.getResultSet();
                            if (rs2.next()) {
                                row.addElement(null);
                                ok = 1;
                            }
                        }
                    }
                    selectStatement1 = connection.createStatement();
                    selectStatement1.execute("SELECT * FROM grup_studiu WHERE ora = \"" + ore.get(i).toString() + "\";");
                    rs1 = selectStatement1.getResultSet();
                    while(rs1.next())
                    {
                        LocalDate date = LocalDate.parse(rs1.getString("data_desfasurare"));
                        DayOfWeek day_of_week = date.getDayOfWeek();
                        String ziCurenta = "zi";
                        if (day_of_week.getValue() == 1) {
                            ziCurenta = "luni";
                        }
                        if (day_of_week.getValue() == 2) {
                            ziCurenta = "marti";
                        }
                        if (day_of_week.getValue() == 3) {
                            ziCurenta = "miercuri";
                        }
                        if (day_of_week.getValue() == 4) {
                            ziCurenta = "joi";
                        }
                        if (day_of_week.getValue() == 5) {
                            ziCurenta = "vineri";
                        }
                        if(ziCurenta.equals(zile.get(j).toString())) {
                            selectStatement2 = connection.createStatement();
                            selectStatement2.execute("SELECT * FROM inscriere_grup_studiu WHERE idStudent = " + idStudent + " and idGrup =" + rs1.getInt("idGrup"));
                            rs2 = selectStatement2.getResultSet();
                            if (rs2.next()) {
                                row.addElement(null);
                                ok = 1;
                            }
                        }
                    }
                    if(ok == 0) {
                        row.addElement("LIBER");
                    }
                }
            } catch (SQLException sqlex)
            {
                System.err.println("An SQL Exception occured. Details are provided below:");
                sqlex.printStackTrace(System.err);
            }
            data.addElement(row);
        }

        table.setBorder(border);
        table.setRowHeight(80);
        table.setFont(new Font("Calibri", 0, 20));
        table.getTableHeader().setResizingAllowed(false);
        table.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 20));

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds((f1.getWidth() - 900)/2, 50, 900, 511);
        sp.setBorder(border);
        p1.add(sp);

        backBTN1.setBounds(10, 575, 80, 30);
        backBTN1.setBorder(border);
        p1.add(backBTN1);
        backBTN1.addActionListener(this);

        f1.setContentPane(p1);
        f1.setLayout(null);
        f1.setVisible(true);
    }
    public void sugestiiParticipanti() {
        Statement selectStatement1 = null;
        Statement selectStatement2 = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;

        Vector columnNames = new Vector();
        Vector data = new Vector();

        try {
            selectStatement1 = connection.createStatement();
            selectStatement1.execute("SELECT distinct * FROM inscriere_grup_studiu WHERE idStudent = " + idStudent + ";");
            rs1 = selectStatement1.getResultSet();
            columnNames.addElement("Nume activitate grup");
            while (rs1.next()) {
                Vector row = new Vector();
                selectStatement2 = connection.createStatement();
                selectStatement2.execute("SELECT nume_materie FROM grup_studiu WHERE idGrup = " + rs1.getInt("idGrup"));
                rs2 = selectStatement2.getResultSet();
                if(rs2.next())
                {
                    row.addElement(rs2.getObject("nume_materie"));
                    data.addElement(row);
                }
            }
            Rezultat3(data, columnNames);
        } catch (SQLException sqlex) {
            System.err.println("An SQL Exception occured. Details are provided below:");
            sqlex.printStackTrace(System.err);
        }
    }
    public void Rezultat3(Vector data, Vector columnNames) {

        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setSize(500, 550);
        f1.setTitle("Grup de studiu");
        p1.setBackground(Color.lightGray);

        JLabel label = new JLabel("Activitati de grup");
        label.setBounds((this.getWidth() - 200) / 2, 10, 200, 30);
        label.setFont(new Font("Calibri", Font.BOLD, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        p1.add(label);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - f1.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - f1.getHeight()) / 2);
        f1.setLocation(x, y);

        backBTN1.setBounds(10, 475, 80, 30);
        backBTN1.setBorder(border);
        p1.add(backBTN1);
        backBTN1.addActionListener(this);

        table = new JTable(data, columnNames);
        table.setBorder(border);

        ListSelectionModel model;
        model = table.getSelectionModel();
        model.addListSelectionListener(new ListSelectionListener()
        {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!model.isSelectionEmpty())
                {
                    int column = 0;
                    int row = table.getSelectedRow();
                    numeActivitateGrup = table.getModel().getValueAt(row, column).toString();
                }
            }
        });

        sugestiiBTN3.setBounds((f1.getWidth() - 150) / 2, 300, 150, 30);
        sugestiiBTN3.setBorder(border);
        p1.add(sugestiiBTN3);
        sugestiiBTN3.addActionListener(this);

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds((f1.getWidth() - 400)/2, 50, 400, 200);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        sp.setBorder(border);

        f1.setContentPane(p1);
        f1.add(sp);
        f1.setLayout(null);
        f1.setVisible(true);
    }
    public void participantiGrup() {
        Statement selectStatement1 = null;
        Statement selectStatement2 = null;
        Statement selectStatement3 = null;
        Statement selectStatement4 = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        ResultSet rs3 = null;
        ResultSet rs4 = null;

        Vector columnNames = new Vector();
        Vector data = new Vector();

        try {
            selectStatement1 = connection.createStatement();
            selectStatement1.execute("SELECT distinct * FROM materie WHERE nume_materie = \"" + numeActivitateGrup + "\";");
            rs1 = selectStatement1.getResultSet();
            if(rs1.next()) {
                selectStatement2 = connection.createStatement();
                selectStatement2.execute("SELECT distinct idStudent FROM inscriere_la_activitati WHERE idMaterie = " + rs1.getInt("idMaterie") + ";");
                rs2 = selectStatement2.getResultSet();
                columnNames.addElement("Nume");
                columnNames.addElement("Prenume");
                while (rs2.next()) {
                    Vector row = new Vector();
                    selectStatement3 = connection.createStatement();
                    selectStatement3.execute("SELECT nume, prenume FROM utilizator WHERE id = " + rs2.getInt("idStudent"));
                    rs3 = selectStatement3.getResultSet();
                    if (rs3.next()) {
                        selectStatement4 = connection.createStatement();
                        selectStatement4.execute("SELECT * FROM inscriere_grup_studiu WHERE idStudent = " + rs2.getInt("idStudent") + " and idGrup = (SELECT idGrup FROM grup_studiu WHERE nume_materie = \"" + numeActivitateGrup + "\");");
                        rs4 = selectStatement4.getResultSet();
                        if (!rs4.next()) {
                            row.addElement(rs3.getObject("nume"));
                            row.addElement(rs3.getObject("prenume"));
                            data.addElement(row);
                        }
                    }
                }
            }
            Rezultat4(data, columnNames);
        } catch (SQLException sqlex) {
            System.err.println("An SQL Exception occured. Details are provided below:");
            sqlex.printStackTrace(System.err);
        }
    }
    public void Rezultat4(Vector data, Vector columnNames) {

        f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f2.setSize(500, 550);
        f2.setTitle("Grup de studiu");
        p2.setBackground(Color.lightGray);

        JLabel label = new JLabel("Sugestii Participanti");
        label.setBounds((f2.getWidth() - 200) / 2, 10, 200, 30);
        label.setFont(new Font("Calibri", Font.BOLD, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        p2.add(label);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - f2.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - f2.getHeight()) / 2);
        f2.setLocation(x, y);

        backBTN2.setBounds(10, 475, 80, 30);
        backBTN2.setBorder(border);
        p2.add(backBTN2);
        backBTN2.addActionListener(this);

        table = new JTable(data, columnNames);
        table.setBorder(border);

        ListSelectionModel model;
        model = table.getSelectionModel();
        model.addListSelectionListener(new ListSelectionListener()
        {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!model.isSelectionEmpty())
                {
                    int column = 0;
                    int row = table.getSelectedRow();
                    numeStd = table.getModel().getValueAt(row, column).toString();
                }
            }
        });

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds((f2.getWidth() - 400)/2, 50, 400, 200);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        sp.setBorder(border);
        f2.setContentPane(p2);
        f2.add(sp);
        f2.setLayout(null);
        f2.setVisible(true);
    }
}