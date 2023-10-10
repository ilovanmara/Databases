import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.DayOfWeek;
import java.util.Vector;
import java.time.LocalDate;

public class TeacherPage extends JFrame implements ActionListener {
    int idProfesor;
    JPanel panel = new JPanel();
    JPanel p2 = new JPanel();
    JPanel p1 = new JPanel();
    JButton logOut = new JButton("Logout");
    JLabel label = new JLabel("Teacher Page");
    JButton datePers = new JButton("Date personale");
    JButton vizualizareCursuri = new JButton("Vizualizare Cursuri");
    JButton backBTN1 = new JButton("Back");
    JButton backBTN2 = new JButton("Back");
    JButton editareBTN = new JButton("Editare Curs");
    JButton editareBTN2 = new JButton("Editare");
    JButton orarBTN = new JButton("Orar");
    JButton activitatiBTN = new JButton("Activitati curente");
    JButton editare3BTN = new JButton("Editare Note");
    JButton catalogBTN = new JButton("Catalog Online");
    JRadioButton cursRBTN = new JRadioButton("curs");
    JRadioButton seminarRBTN = new JRadioButton("seminar");
    JRadioButton laboratorRBTN = new JRadioButton("laborator");
    JTextField procentajTxt = new JTextField();
    JTextField nrMaxStudentiTxt = new JTextField();
    JTextField ziTxt = new JTextField();
    JTextField oraTxt = new JTextField();
    JTextField durataTxt = new JTextField();
    JLabel procentajLbl = new JLabel("Procentaj:");
    JLabel nrMaxStudentiLbl = new JLabel("Nr. max studenti:");
    JLabel ziLbl = new JLabel("Zi:");
    JLabel oraLbl = new JLabel("Ora:");
    JLabel durataLbl = new JLabel("Durata:");
    String numeCurs;
    JFrame f1 = new JFrame();
    JFrame f2 = new JFrame();
    JTable table;
    Border border = BorderFactory.createLineBorder(Color.lightGray);
    Connection connection;
    int idStructura;
    JTextField notaLabTxt = new JTextField();
    JTextField notaSeminarTxt = new JTextField();
    JTextField notaCursTxt = new JTextField();
    JLabel notaLabLbl = new JLabel("Nota Laborator:");
    JLabel notaSeminarLbl = new JLabel("Nota Seminar:");
    JLabel notaCursLbl = new JLabel("NotaCurs:");
    JButton editare4BTN = new JButton("Editare");
    JButton predareBTN1 = new JButton("Predare activitate");
    JButton predareBTN2 = new JButton("Predare activitate");
    JTextField numeActivitateTxt = new JTextField();
    String tipActivitate;
    int idMaterie;
    int idStudent;
    int pcurs;
    int pseminar;
    int plab;
    public TeacherPage(int idProfesor, Connection connection) {

        this.connection = connection;

        this.idProfesor = idProfesor;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 550);
        this.setTitle("Pagina unui profesor");

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);

        label.setBounds((this.getWidth() - 200) / 2, 10, 200, 30);
        label.setFont(new Font("Calibri", Font.BOLD, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label);

        this.add(panel, BorderLayout.CENTER);

        datePers.setBounds(180, 80, 150, 30);
        datePers.setBorder(border);
        datePers.addActionListener(this);
        panel.add(datePers);

        vizualizareCursuri.setBounds(180, 130, 150, 30);
        vizualizareCursuri.setBorder(border);
        panel.add(vizualizareCursuri);
        vizualizareCursuri.addActionListener(this);

        catalogBTN.setBounds(180, 180, 150, 30);
        catalogBTN.setBorder(border);
        panel.add(catalogBTN);
        catalogBTN.addActionListener(this);

        activitatiBTN.setBounds(180, 230, 150, 30);
        activitatiBTN.setBorder(border);
        panel.add(activitatiBTN);
        activitatiBTN.addActionListener(this);

        orarBTN.setBounds(180, 280, 150, 30);
        orarBTN.setBorder(border);
        panel.add(orarBTN);
        orarBTN.addActionListener(this);

        predareBTN1.setBounds(180, 330, 150, 30);
        predareBTN1.setBorder(border);
        panel.add(predareBTN1);
        predareBTN1.addActionListener(this);

        logOut.setBounds((this.getWidth() - 80) / 2, 475, 80, 30);
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
        if (e.getSource() == datePers) {
            this.setVisible(false);
            DatePersonale date = new DatePersonale(idProfesor, connection, "profesor", "profesor", idProfesor);
        }
        if (e.getSource() == logOut) {
            this.setVisible(false);
            LoginPage login = new LoginPage(connection);
        }
        if (e.getSource() == backBTN1) {
            f1.setVisible(false);
            new TeacherPage(idProfesor, connection);
        }
        if (e.getSource() == backBTN2) {
            f2.setVisible(false);
            new TeacherPage(idProfesor, connection);
        }
        if (e.getSource() == vizualizareCursuri) {
            this.setVisible(false);
            cursuri();
        }
        if(e.getSource() == predareBTN1)
        {
            this.setVisible(false);
            predareGrup();
        }
        if(e.getSource() == predareBTN2)
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
                    selectStatement2.execute("SELECT * FROM distributie_materie WHERE idMaterie = " + rs1.getInt("idMaterie") + " and idProfesor = " + idProfesor + ";");
                    rs2 = selectStatement2.getResultSet();
                    if (rs2.next()) {
                        selectStatement3 = connection.createStatement();
                        selectStatement3.execute("SELECT * FROM grup_studiu WHERE nume_materie = \"" + numeActivitateTxt.getText() + "\";");
                        rs3 = selectStatement3.getResultSet();
                        if(rs3.next())
                        {
                            selectStatement4 = connection.createStatement();
                            selectStatement4.execute("SELECT * FROM predare_grup_studiu WHERE idGrup = " + rs3.getInt("idGrup") + ";");
                            rs4 = selectStatement4.getResultSet();
                            if(rs4.next())
                            {
                                Error e5 = new Error("Cineva preda deja la activitate!");
                            }
                            else {
                                CallableStatement call1 = null;
                                call1 = connection.prepareCall("call predare_grup(?,?)");
                                call1.setInt(1, idProfesor);
                                call1.setString(2, numeActivitateTxt.getText());
                                call1.execute();
                                Succes s1 = new Succes("Activitatea va fi predata de dvs!");
                            }
                        }
                        else {
                            Error e1 = new Error("Activitatea de grup nu exista!");
                        }
                    }
                    else
                    {
                        Error e2 = new Error("Nu predati acest tip curs!");
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
        if (e.getSource() == editareBTN) {
            if(numeCurs != null)
            {
                f1.setVisible(false);
                editareCurs();
            }
            else {
                Error e1 = new Error("Selectati un curs!");
            }
        }
        if (e.getSource() == editareBTN2) {
            try {
                CallableStatement call1 = null;
                call1 = connection.prepareCall("call updatare_structura_materie(?,?,?,?,?,?,?)");
                call1.setInt(1, idProfesor);
                call1.setInt(2, idStructura);
                call1.setInt(3, Integer.valueOf(procentajTxt.getText()));
                call1.setInt(4, Integer.valueOf(nrMaxStudentiTxt.getText()));
                call1.setString(5, ziTxt.getText());
                call1.setString(6, oraTxt.getText());
                call1.setInt(7, Integer.valueOf(durataTxt.getText()));
                call1.execute();
            } catch (SQLException sqlex) {
                System.err.println("An SQL Exception occured. Details are provided below:");
                sqlex.printStackTrace(System.err);
            }
            Succes s2 = new Succes("Date editate cu succes!");
        }
        if (e.getSource() == cursRBTN) {

            p2.remove(procentajLbl);
            p2.remove(procentajTxt);
            p2.remove(nrMaxStudentiLbl);
            p2.remove(nrMaxStudentiTxt);
            p2.remove(ziLbl);
            p2.remove(ziTxt);
            p2.remove(oraLbl);
            p2.remove(oraTxt);
            p2.remove(durataLbl);
            p2.remove(durataTxt);
            editareBTN2.removeActionListener(this);
            p2.remove(editareBTN2);

            Statement selectStatement1 = null;
            Statement selectStatement2 = null;
            Statement selectStatement3 = null;
            ResultSet rs1 = null;
            ResultSet rs2 = null;
            ResultSet rs3 = null;
            try {
                selectStatement1 = connection.createStatement();
                selectStatement1.execute("SELECT idMaterie FROM materie WHERE nume_materie = \"" + numeCurs + "\";");
                rs1 = selectStatement1.getResultSet();
                if (rs1.next()) {
                    selectStatement2 = connection.createStatement();
                    selectStatement2.execute("SELECT idStructura from distributie_materie where idProfesor = " + idProfesor + " and idMaterie = " + rs1.getInt("idMaterie") + ";");
                    rs2 = selectStatement2.getResultSet();
                    while (rs2.next()) {
                        selectStatement3 = connection.createStatement();
                        selectStatement3.execute("SELECT * from structura_materie where idStructura = " + rs2.getInt("idStructura") + " and tip_activitate = \"" + "curs" + "\";");
                        rs3 = selectStatement3.getResultSet();
                        if (rs3.next()) {

                            idStructura = rs3.getInt("idStructura");
                            procentajLbl.setBounds(50, 100, 200, 30);
                            p2.add(procentajLbl);
                            procentajTxt.setBounds(250, 100, 200, 30);
                            procentajTxt.setText(String.valueOf(rs3.getInt("procentaj")));
                            procentajTxt.setBorder(border);
                            p2.add(procentajTxt);

                            nrMaxStudentiLbl.setBounds(50, 135, 200, 30);
                            p2.add(nrMaxStudentiLbl);
                            nrMaxStudentiTxt.setBounds(250, 135, 200, 30);
                            nrMaxStudentiTxt.setText(String.valueOf(rs3.getInt("nr_max_studenti")));
                            nrMaxStudentiTxt.setBorder(border);
                            p2.add(nrMaxStudentiTxt);

                            ziLbl.setBounds(50, 170, 200, 30);
                            p2.add(ziLbl);
                            ziTxt.setBounds(250, 170, 200, 30);
                            ziTxt.setText(rs3.getString("zi"));
                            ziTxt.setBorder(border);
                            p2.add(ziTxt);

                            oraLbl.setBounds(50, 205, 200, 30);
                            p2.add(oraLbl);
                            oraTxt.setBounds(250, 205, 200, 30);
                            oraTxt.setText(rs3.getString("ora"));
                            oraTxt.setBorder(border);
                            p2.add(oraTxt);

                            durataLbl.setBounds(50, 240, 200, 30);
                            p2.add(durataLbl);
                            durataTxt.setBounds(250, 240, 200, 30);
                            durataTxt.setText(String.valueOf(rs3.getInt("durata")));
                            durataTxt.setBorder(border);
                            p2.add(durataTxt);

                            editareBTN2.setBounds((f2.getWidth() - 80) / 2, 300, 80, 30);
                            editareBTN2.setBorder(border);
                            p2.add(editareBTN2);
                            editareBTN2.addActionListener(this);

                            break;
                        }
                    }
                }

            } catch (SQLException sqlex) {
                System.err.println("An SQL Exception occured. Details are provided below:");
                sqlex.printStackTrace(System.err);
            }

            f2.setContentPane(p2);
        }
        if (e.getSource() == seminarRBTN) {

            p2.remove(procentajLbl);
            p2.remove(procentajTxt);
            p2.remove(nrMaxStudentiLbl);
            p2.remove(nrMaxStudentiTxt);
            p2.remove(ziLbl);
            p2.remove(ziTxt);
            p2.remove(oraLbl);
            p2.remove(oraTxt);
            p2.remove(durataLbl);
            p2.remove(durataTxt);
            editareBTN2.removeActionListener(this);
            p2.remove(editareBTN2);

            Statement selectStatement1 = null;
            Statement selectStatement2 = null;
            Statement selectStatement3 = null;
            ResultSet rs1 = null;
            ResultSet rs2 = null;
            ResultSet rs3 = null;
            try {
                selectStatement1 = connection.createStatement();
                selectStatement1.execute("SELECT idMaterie FROM materie WHERE nume_materie = \"" + numeCurs + "\";");
                rs1 = selectStatement1.getResultSet();
                if (rs1.next()) {
                    selectStatement2 = connection.createStatement();
                    selectStatement2.execute("SELECT idStructura from distributie_materie where idProfesor = " + idProfesor + " and idMaterie = " + rs1.getInt("idMaterie") + ";");
                    rs2 = selectStatement2.getResultSet();
                    while (rs2.next()) {
                        selectStatement3 = connection.createStatement();
                        selectStatement3.execute("SELECT * from structura_materie where idStructura = " + rs2.getInt("idStructura") + " and tip_activitate = \"" + "seminar" + "\";");
                        rs3 = selectStatement3.getResultSet();
                        if (rs3.next()) {

                            idStructura = rs3.getInt("idStructura");
                            procentajLbl.setBounds(50, 100, 200, 30);
                            p2.add(procentajLbl);
                            procentajTxt.setBounds(250, 100, 200, 30);
                            procentajTxt.setText(String.valueOf(rs3.getInt("procentaj")));
                            procentajTxt.setBorder(border);
                            p2.add(procentajTxt);

                            nrMaxStudentiLbl.setBounds(50, 135, 200, 30);
                            p2.add(nrMaxStudentiLbl);
                            nrMaxStudentiTxt.setBounds(250, 135, 200, 30);
                            nrMaxStudentiTxt.setText(String.valueOf(rs3.getInt("nr_max_studenti")));
                            nrMaxStudentiTxt.setBorder(border);
                            p2.add(nrMaxStudentiTxt);

                            ziLbl.setBounds(50, 170, 200, 30);
                            p2.add(ziLbl);
                            ziTxt.setBounds(250, 170, 200, 30);
                            ziTxt.setText(rs3.getString("zi"));
                            ziTxt.setBorder(border);
                            p2.add(ziTxt);

                            oraLbl.setBounds(50, 205, 200, 30);
                            p2.add(oraLbl);
                            oraTxt.setBounds(250, 205, 200, 30);
                            oraTxt.setText(rs3.getString("ora"));
                            oraTxt.setBorder(border);
                            p2.add(oraTxt);

                            durataLbl.setBounds(50, 240, 200, 30);
                            p2.add(durataLbl);
                            durataTxt.setBounds(250, 240, 200, 30);
                            durataTxt.setText(String.valueOf(rs3.getInt("durata")));
                            durataTxt.setBorder(border);
                            p2.add(durataTxt);

                            editareBTN2.setBounds((f2.getWidth() - 80) / 2, 300, 80, 30);
                            editareBTN2.setBorder(border);
                            p2.add(editareBTN2);
                            editareBTN2.addActionListener(this);

                            break;
                        }
                    }
                }

            } catch (SQLException sqlex) {
                System.err.println("An SQL Exception occured. Details are provided below:");
                sqlex.printStackTrace(System.err);
            }

            f2.setContentPane(p2);
        }
        if (e.getSource() == laboratorRBTN) {
            p2.remove(procentajLbl);
            p2.remove(procentajTxt);
            p2.remove(nrMaxStudentiLbl);
            p2.remove(nrMaxStudentiTxt);
            p2.remove(ziLbl);
            p2.remove(ziTxt);
            p2.remove(oraLbl);
            p2.remove(oraTxt);
            p2.remove(durataLbl);
            p2.remove(durataTxt);
            editareBTN2.removeActionListener(this);
            p2.remove(editareBTN2);

            Statement selectStatement1 = null;
            Statement selectStatement2 = null;
            Statement selectStatement3 = null;
            ResultSet rs1 = null;
            ResultSet rs2 = null;
            ResultSet rs3 = null;
            try {
                selectStatement1 = connection.createStatement();
                selectStatement1.execute("SELECT idMaterie FROM materie WHERE nume_materie = \"" + numeCurs + "\";");
                rs1 = selectStatement1.getResultSet();
                if (rs1.next()) {
                    selectStatement2 = connection.createStatement();
                    selectStatement2.execute("SELECT idStructura from distributie_materie where idProfesor = " + idProfesor + " and idMaterie = " + rs1.getInt("idMaterie") + ";");
                    rs2 = selectStatement2.getResultSet();
                    while (rs2.next()) {
                        selectStatement3 = connection.createStatement();
                        selectStatement3.execute("SELECT * from structura_materie where idStructura = " + rs2.getInt("idStructura") + " and tip_activitate = \"" + "laborator" + "\";");
                        rs3 = selectStatement3.getResultSet();
                        if (rs3.next()) {

                            idStructura = rs3.getInt("idStructura");
                            procentajLbl.setBounds(50, 100, 200, 30);
                            p2.add(procentajLbl);
                            procentajTxt.setBounds(250, 100, 200, 30);
                            procentajTxt.setText(String.valueOf(rs3.getInt("procentaj")));
                            procentajTxt.setBorder(border);
                            p2.add(procentajTxt);

                            nrMaxStudentiLbl.setBounds(50, 135, 200, 30);
                            p2.add(nrMaxStudentiLbl);
                            nrMaxStudentiTxt.setBounds(250, 135, 200, 30);
                            nrMaxStudentiTxt.setText(String.valueOf(rs3.getInt("nr_max_studenti")));
                            nrMaxStudentiTxt.setBorder(border);
                            p2.add(nrMaxStudentiTxt);

                            ziLbl.setBounds(50, 170, 200, 30);
                            p2.add(ziLbl);
                            ziTxt.setBounds(250, 170, 200, 30);
                            ziTxt.setText(rs3.getString("zi"));
                            ziTxt.setBorder(border);
                            p2.add(ziTxt);

                            oraLbl.setBounds(50, 205, 200, 30);
                            p2.add(oraLbl);
                            oraTxt.setBounds(250, 205, 200, 30);
                            oraTxt.setText(rs3.getString("ora"));
                            oraTxt.setBorder(border);
                            p2.add(oraTxt);

                            durataLbl.setBounds(50, 240, 200, 30);
                            p2.add(durataLbl);
                            durataTxt.setBounds(250, 240, 200, 30);
                            durataTxt.setText(String.valueOf(rs3.getInt("durata")));
                            durataTxt.setBorder(border);
                            p2.add(durataTxt);

                            editareBTN2.setBounds((f2.getWidth() - 80) / 2, 300, 80, 30);
                            editareBTN2.setBorder(border);
                            p2.add(editareBTN2);
                            editareBTN2.addActionListener(this);

                            break;
                        }
                    }
                }

            } catch (SQLException sqlex) {
                System.err.println("An SQL Exception occured. Details are provided below:");
                sqlex.printStackTrace(System.err);
            }

            f2.setContentPane(p2);
        }
        if (e.getSource() == catalogBTN) {
            this.setVisible(false);
            studenti();
        }
        if (e.getSource() == orarBTN) {
            this.setVisible(false);
            orarActivitati();
        }
        if (e.getSource() == editare3BTN) {
            p2.remove(notaCursLbl);
            p2.remove(notaCursTxt);
            p2.remove(notaLabLbl);
            p2.remove(notaLabTxt);
            p2.remove(notaSeminarLbl);
            p2.remove(notaSeminarTxt);

            Statement selectStatement1 = null;
            Statement selectStatement2 = null;
            Statement selectStatement3 = null;
            ResultSet rs1 = null;
            ResultSet rs2 = null;
            ResultSet rs3 = null;
            try {
                selectStatement1 = connection.createStatement();
                selectStatement1.execute("SELECT * FROM catalog WHERE idStudent = " + idStudent + " and nume_materie = \"" + numeCurs + "\";");
                rs1 = selectStatement1.getResultSet();
                int ok = 0;
                while (rs1.next()) {
                    f1.setVisible(false);
                    adaugareNote();
                    ok = 1;
                    idMaterie = rs1.getInt("idMaterie");
                    selectStatement2 = connection.createStatement();
                    selectStatement2.execute("SELECT * FROM inscriere_la_activitati WHERE idStudent = \"" + idStudent + "\"and idMaterie = " + idMaterie + ";");
                    rs2 = selectStatement2.getResultSet();
                    while (rs2.next()) {
                        selectStatement3 = connection.createStatement();
                        selectStatement3.execute("SELECT * FROM structura_materie WHERE idStructura = " + rs2.getInt("idStructura") + ";");
                        rs3 = selectStatement3.getResultSet();
                        if(rs3.next())
                        {
                            if(rs3.getString("tip_activitate").equals(tipActivitate))
                            {
                                if (tipActivitate.equals("curs")) {
                                    notaCursLbl.setBounds(50, 100, 200, 30);
                                    p2.add(notaCursLbl);
                                    notaCursTxt.setBounds(250, 100, 200, 30);
                                    notaCursTxt.setText(String.valueOf(rs1.getFloat("nota_curs")));
                                    notaCursTxt.setBorder(border);
                                    p2.add(notaCursTxt);
                                }
                                if (tipActivitate.equals("laborator")) {
                                    notaLabLbl.setBounds(50, 100, 200, 30);
                                    p2.add(notaLabLbl);
                                    notaLabTxt.setBounds(250, 100, 200, 30);
                                    notaLabTxt.setText(String.valueOf(rs1.getFloat("nota_lab")));
                                    notaLabTxt.setBorder(border);
                                    p2.add(notaLabTxt);
                                }
                                if (tipActivitate.equals("seminar")) {
                                    notaSeminarLbl.setBounds(50, 100, 200, 30);
                                    p2.add(notaSeminarLbl);
                                    notaSeminarTxt.setBounds(250, 100, 200, 30);
                                    notaSeminarTxt.setText(String.valueOf(rs1.getFloat("nota_seminar")));
                                    notaSeminarTxt.setBorder(border);
                                    p2.add(notaSeminarTxt);
                                }
                                editare4BTN.setBounds((f2.getWidth() - 80) / 2, 300, 80, 30);
                                editare4BTN.setBorder(border);
                                p2.add(editare4BTN);
                                editare4BTN.addActionListener(this);
                            }
                        }
                    }
                }
                if(ok == 0)
                {
                    Error e1 = new Error("Selectati un student!");
                }
            } catch (SQLException sqlex) {
                System.err.println("An SQL Exception occured. Details are provided below:");
                sqlex.printStackTrace(System.err);
            }
            f2.setContentPane(p2);
        }
        if (e.getSource() == editare4BTN) {
            Statement selectStatement1 = null;
            Statement selectStatement2 = null;
            ResultSet rs1 = null;
            ResultSet rs2 = null;
            try {
                CallableStatement call1 = null;
                call1 = connection.prepareCall("call updatare_catalog(?,?,?,?)");
                call1.setInt(1, idStudent);
                call1.setInt(2, idMaterie);
                if (tipActivitate.equals("curs")) {
                    call1.setFloat(3, Float.valueOf(notaCursTxt.getText()));
                    call1.setString(4, "curs");
                }
                if (tipActivitate.equals("laborator")) {
                    call1.setFloat(3, Float.valueOf(notaLabTxt.getText()));
                    call1.setString(4, "laborator");
                }
                if (tipActivitate.equals("seminar")) {
                    call1.setFloat(3, Float.valueOf(notaSeminarTxt.getText()));
                    call1.setString(4, "seminar");
                }
                call1.execute();

                selectStatement1 = connection.createStatement();
                selectStatement1.execute("SELECT * FROM inscriere_la_activitati WHERE idStudent = " + idStudent + " and idMaterie = " + idMaterie + ";");
                rs1 = selectStatement1.getResultSet();
                while(rs1.next())
                {
                    selectStatement2 = connection.createStatement();
                    selectStatement2.execute("SELECT * FROM structura_materie WHERE idStructura = " + rs1.getInt("idStructura") + ";");
                    rs2 = selectStatement2.getResultSet();
                    if(rs2.next())
                    {
                        if(rs2.getString("tip_activitate").equals("curs"))
                        {
                            pcurs = rs2.getInt("procentaj");
                        }
                        if(rs2.getString("tip_activitate").equals("seminar"))
                        {
                            pseminar = rs2.getInt("procentaj");
                        }
                        if(rs2.getString("tip_activitate").equals("laborator"))
                        {
                            plab = rs2.getInt("procentaj");
                        }
                    }
                }
                CallableStatement call2 = null;
                call2 = connection.prepareCall("call notafinala(?,?,?,?,?)");
                call2.setInt(1, idStudent);
                call2.setInt(2, idMaterie);
                call2.setInt(3, pcurs);
                call2.setInt(4, pseminar);
                call2.setInt(5, plab);
                call2.execute();

            } catch (SQLException sqlex) {
                System.err.println("An SQL Exception occured. Details are provided below:");
                sqlex.printStackTrace(System.err);
            }
            Succes s1 = new Succes("Nota a fost editata cu succes!");
        }
        if(e.getSource() == activitatiBTN)
        {
            this.setVisible(false);
            activitatiViit();
        }
    }
    public void cursuri() {
        Statement selectStatement1 = null;
        Statement selectStatement2 = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;

        Vector columnNames = new Vector();
        Vector data = new Vector();

        try {
            selectStatement1 = connection.createStatement();
            selectStatement1.execute("SELECT distinct idMaterie FROM distributie_materie WHERE idProfesor = " + idProfesor + ";");
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
            Rezultat1(data, columnNames,"Vizualizare Cursuri");
        } catch (SQLException sqlex) {
            System.err.println("An SQL Exception occured. Details are provided below:");
            sqlex.printStackTrace(System.err);
        }
    }

    public void Rezultat1(Vector data, Vector columnNames, String s) {

        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setSize(500, 550);
        f1.setTitle("Cursuri");
        p1.setBackground(Color.lightGray);

        JLabel label = new JLabel(s);
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
                    if(s.equals("Vizualizare Cursuri"))
                        numeCurs = table.getModel().getValueAt(row, column).toString();
                    else {
                        idStudent = Integer.valueOf(table.getModel().getValueAt(row, column).toString());
                        numeCurs = table.getModel().getValueAt(row, 3).toString();
                        tipActivitate = table.getModel().getValueAt(row, 4).toString();
                    }
                }
            }
        });

        if(s.equals("Vizualizare Cursuri")) {
            editareBTN.setBounds((f1.getWidth() - 80) / 2, 300, 80, 30);
            editareBTN.setBorder(border);
            p1.add(editareBTN);
            editareBTN.addActionListener(this);
        }else if(s.equals("Vizualizare Studenti")){
            editare3BTN.setBounds((f1.getWidth() - 80) / 2, 300, 80, 30);
            editare3BTN.setBorder(border);
            p1.add(editare3BTN);
            editare3BTN.addActionListener(this);
        }
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds((f1.getWidth() - 400)/2, 50, 400, 200);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        sp.setBorder(border);
        f1.setContentPane(p1);
        f1.add(sp);
        f1.setLayout(null);
        f1.setVisible(true);
    }
    public void adaugareNote(){
        f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f2.setSize(500, 550);
        f2.setTitle("Catalog");
        p2.setBackground(Color.lightGray);

        JLabel label = new JLabel("Editare Note");
        label.setBounds((this.getWidth() - 200) / 2, 10, 200, 30);
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

        f2.setContentPane(p2);
        f2.setLayout(null);
        f2.setVisible(true);
    }
    public void editareCurs()
    {
        f2 = new JFrame();
        f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f2.setSize(500, 550);
        f2.setTitle("Cursuri");
        p2.setBackground(Color.lightGray);

        JLabel label = new JLabel("Editare Cursuri");
        label.setBounds((f1.getWidth() - 200) / 2, 10, 200, 30);
        label.setFont(new Font("Calibri", Font.BOLD, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        p2.add(label);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - f2.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - f2.getHeight()) / 2);
        f2.setLocation(x, y);

        cursRBTN.setBounds(50,50,100,30);
        cursRBTN.setBackground(Color.lightGray);
        seminarRBTN.setBounds(180,50,100,30);
        seminarRBTN.setBackground(Color.lightGray);
        laboratorRBTN.setBounds(310,50,100,30);
        laboratorRBTN.setBackground(Color.lightGray);

        ButtonGroup grup = new ButtonGroup();
        grup.add(cursRBTN);
        grup.add(seminarRBTN);
        grup.add(laboratorRBTN);

        p2.add(cursRBTN);
        p2.add(seminarRBTN);
        p2.add(laboratorRBTN);

        cursRBTN.addActionListener(this);
        seminarRBTN.addActionListener(this);
        laboratorRBTN.addActionListener(this);

        backBTN2.setBounds(10, 475, 80, 30);
        backBTN2.setBorder(border);
        p2.add(backBTN2);
        backBTN2.addActionListener(this);

        f2.setContentPane(p2);
        f2.setLayout(null);
        f2.setVisible(true);
    }
    public void studenti() {
        Statement selectStatement1 = null;
        Statement selectStatement2 = null;
        Statement selectStatement3 = null;
        Statement selectStatement4 = null;

        ResultSet rs1 = null;
        ResultSet rs2 = null;
        ResultSet rs3 = null;
        ResultSet rs4 = null;

        Vector columnNames = new Vector(5);
        Vector data = new Vector();
        try {
            selectStatement1 = connection.createStatement();
            selectStatement1.execute("SELECT * FROM distributie_materie WHERE idProfesor = " + idProfesor + ";");
            rs1 = selectStatement1.getResultSet();
            columnNames.addElement("id");
            columnNames.addElement("Nume");
            columnNames.addElement("Prenume");
            columnNames.addElement("Materie");
            columnNames.addElement("Tip activitate");
            while(rs1.next()) {
                selectStatement2 = connection.createStatement();
                selectStatement2.execute("SELECT * FROM inscriere_la_activitati WHERE idStructura = " + rs1.getInt("idStructura") + ";");
                rs2 = selectStatement2.getResultSet();
                while (rs2.next()) {
                    Vector row = new Vector();
                    selectStatement3 = connection.createStatement();
                    selectStatement3.execute("SELECT nume, prenume FROM utilizator WHERE id = " + rs2.getInt("idStudent") + ";");
                    rs3 = selectStatement3.getResultSet();
                    if (rs3.next()) {
                        row.addElement(rs2.getObject("idStudent"));
                        row.addElement(rs3.getObject("nume"));
                        row.addElement(rs3.getObject("prenume"));;
                        selectStatement4 = connection.createStatement();
                        selectStatement4.execute("SELECT * FROM structura_materie WHERE idStructura = " + rs1.getInt("idStructura") + ";");
                        rs4 = selectStatement4.getResultSet();
                        if(rs4.next()) {
                            row.addElement(rs4.getObject("nume_materie"));
                            row.addElement(rs4.getObject("tip_activitate"));
                            data.addElement(row);
                        }
                    }
                }
            }
            Rezultat1(data, columnNames, "Vizualizare Studenti");
        } catch (SQLException sqlex) {
            System.err.println("An SQL Exception occured. Details are provided below:");
            sqlex.printStackTrace(System.err);
        }
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
        ziCurenta = "luni";
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
                    selectStatement2.execute("SELECT * FROM distributie_materie WHERE idProfesor = " + idProfesor + " and idStructura =" + rs1.getInt("idStructura"));
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
                            selectStatement2.execute("SELECT * FROM distributie_materie WHERE idProfesor = " + idProfesor + " and idStructura =" + rs1.getInt("idStructura"));
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
                            selectStatement2.execute("SELECT * FROM predare_grup_studiu WHERE idProfesor = " + idProfesor + " and idGrup =" + rs1.getInt("idGrup"));
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
    public void predareGrup()
    {
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setSize(450, 350);
        f1.setTitle("Predare Activitati Grup");
        p1.setBackground(Color.lightGray);

        JLabel label = new JLabel("Predare activitati de grup");
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

        predareBTN2.setBounds((f1.getWidth() - 100) / 2, 150, 120, 30);
        predareBTN2.setBorder(border);
        predareBTN2.addActionListener(this);
        p1.add(predareBTN2);

        backBTN1.setBounds(10, 275, 80, 30);
        backBTN1.setBorder(border);
        p1.add(backBTN1);
        backBTN1.addActionListener(this);

        f1.setContentPane(p1);
        f1.setLayout(null);
        f1.setVisible(true);
    }
}