import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class SignInPage extends JFrame implements ActionListener{
    JPanel p = new JPanel();
    JLabel signIn = new JLabel("Register", SwingConstants.CENTER);
    JLabel CNP = new JLabel("CNP:");
    JLabel email = new JLabel("E-Mail:");
    JLabel parola = new JLabel("Parola:");
    JLabel nume  = new JLabel("Nume:");
    JLabel prenume = new JLabel("Prenume:");
    JLabel adresa = new JLabel("Adresa:");
    JLabel nrTelefon = new JLabel("Numar de telefon:");
    JLabel IBan = new JLabel("Cont IBAN:");
    JTextField CNPTxt = new JTextField();
    JTextField emailTxt = new JTextField();
    JPasswordField parolaTxt = new JPasswordField();
    JTextField numeTxt  = new JTextField();
    JTextField prenumeTxt = new JTextField();
    JTextField adresaTxt = new JTextField();
    JTextField nrTelefonTxt = new JTextField();
    JTextField IBanTxt = new JTextField();
    JRadioButton student = new JRadioButton("Student");
    JRadioButton profesor = new JRadioButton("Profesor");
    JRadioButton administrator = new JRadioButton("Administrator");
    Border border = BorderFactory.createLineBorder(Color.lightGray);
    JLabel campDiferit = new JLabel();
    JButton signInBtn = new JButton("Sign In");
    JButton backBtn = new JButton("BACK");
    JTextField campDiferitTxt = new JTextField();
    Connection connection;
    int id = 0;
    public SignInPage(Connection connection){

        this.connection = connection;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 550);
        this.setTitle("Pagina de inregistrare");

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);

        signIn.setBounds((this.getWidth() - 200)/2, 10, 200, 30);
        signIn.setFont(new Font("Calibri", Font.BOLD, 20));
        signIn.setHorizontalAlignment(SwingConstants.CENTER);
        p.add(signIn);

        nume.setBounds(50, 50, 120, 30);
        p.add(nume);
        numeTxt.setBounds(200, 50, 250, 30);
        numeTxt.setBorder(border);
        p.add(numeTxt);

        prenume.setBounds(50, 85, 120, 30);
        p.add(prenume);
        prenumeTxt.setBounds(200, 85, 250, 30);
        prenumeTxt.setBorder(border);
        p.add(prenumeTxt);

        CNP.setBounds(50, 120, 120, 30);
        p.add(CNP);
        CNPTxt.setBounds(200, 120, 250, 30);
        CNPTxt.setBorder(border);
        p.add(CNPTxt);

        adresa.setBounds(50, 155, 120, 30);
        p.add(adresa);
        adresaTxt.setBounds(200, 155, 250, 30);
        adresaTxt.setBorder(border);
        p.add(adresaTxt);

        nrTelefon.setBounds(50, 190, 120, 30);
        p.add(nrTelefon);
        nrTelefonTxt.setBounds(200, 190, 250, 30);
        nrTelefonTxt.setBorder(border);
        p.add(nrTelefonTxt);

        IBan.setBounds(50, 225, 120, 30);
        p.add(IBan);
        IBanTxt.setBounds(200, 225, 250, 30);
        IBanTxt.setBorder(border);
        p.add(IBanTxt);

        email.setBounds(50, 260, 120, 30);
        p.add(email);
        emailTxt.setBounds(200, 260, 250, 30);
        emailTxt.setBorder(border);
        p.add(emailTxt);

        parola.setBounds(50, 295, 120, 30);
        p.add(parola);
        parolaTxt.setBounds(200, 295, 250, 30);
        parolaTxt.setBorder(border);
        p.add(parolaTxt);

        student.setBounds(50,330,100,30);
        student.setBackground(Color.lightGray);
        profesor.setBounds(180,330,100,30);
        profesor.setBackground(Color.lightGray);
        administrator.setBounds(310,330,100,30);
        administrator.setBackground(Color.lightGray);

        ButtonGroup grup = new ButtonGroup();
        grup.add(student);
        grup.add(profesor);
        grup.add(administrator);

        student.addActionListener(this);
        profesor.addActionListener(this);
        administrator.addActionListener(this);

        campDiferit.setBounds(50, 365, 120, 30);
        campDiferitTxt.setBounds(200, 365, 250, 30);
        campDiferitTxt.setBorder(border);

        signInBtn.setBounds((this.getWidth() - 80)/2, 425, 80, 30);
        signInBtn.setBorder(border);
        signInBtn.addActionListener(this);

        backBtn.setBounds(10, 475, 80, 30);
        backBtn.setBorder(border);
        backBtn.addActionListener(this);
        p.add(backBtn);

        p.add(student);
        p.add(profesor);
        p.add(administrator);

        p.setBackground(Color.lightGray);
        this.setContentPane(p);
        this.setLayout(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String numeEt;
        if(e.getSource() == backBtn)
        {
            this.setVisible(false);
            LoginPage login = new LoginPage(connection);
        }
        else if(e.getSource() == signInBtn)
        {
            callProcedureUtilizator();

            Statement selectStatement = null;
            ResultSet rs = null;
            try {
                selectStatement = connection.createStatement();
                selectStatement.execute("SELECT id FROM utilizator WHERE email =\"" + emailTxt.getText() + "\";");
                rs = selectStatement.getResultSet();
                if(rs.next())
                    id = rs.getInt(1);
            }
            catch(SQLException sqlex) {
                System.err.println("An SQL Exception occured. Details are provided below:");
                sqlex.printStackTrace(System.err);
            }

            if(student.isSelected())
            {
                this.setVisible(false);
                try {
                    CallableStatement call1 = null;
                    call1 = connection.prepareCall("call inserare_student(?,?)");
                    call1.setInt(1, id);
                    call1.setInt(2, Integer.valueOf(campDiferitTxt.getText()));
                    call1.execute();
                }
                catch(SQLException sqlex) {
                    System.err.println("An SQL Exception occured. Details are provided below:");
                    sqlex.printStackTrace(System.err);
                }
                StudentPage student = new StudentPage(id, connection);
            }
            if(profesor.isSelected())
            {
                this.setVisible(false);
                try {
                    CallableStatement call1 = null;
                    call1 = connection.prepareCall("call inserare_profesor(?,?)");
                    call1.setInt(1, id);
                    call1.setString(2, campDiferitTxt.getText());
                    call1.execute();
                }
                catch(SQLException sqlex) {
                    System.err.println("An SQL Exception occured. Details are provided below:");
                    sqlex.printStackTrace(System.err);
                }
                TeacherPage profesor = new TeacherPage(id, connection);
            }
            if(administrator.isSelected())
            {
                this.setVisible(false);
                try {
                    CallableStatement call1 = null;
                    call1 = connection.prepareCall("call inserare_administrator(?)");
                    call1.setInt(1, id);
                    call1.execute();
                }
                catch(SQLException sqlex) {
                    System.err.println("An SQL Exception occured. Details are provided below:");
                    sqlex.printStackTrace(System.err);
                }
                AdministratorPage administrator = new AdministratorPage(id, connection);
            }
        }
        else if (e.getSource() == student)
        {
            p.remove(campDiferit);
            p.remove(campDiferitTxt);
            numeEt = "An Studiu:";
            campDiferit.setText(numeEt);
            p.add(campDiferit);
            p.add(campDiferitTxt);
            p.add(signInBtn);
        }
        else if (e.getSource() == profesor)
        {
            p.remove(campDiferit);
            p.remove(campDiferitTxt);
            numeEt = "Departament";
            campDiferit.setText(numeEt);
            p.add(campDiferit);
            p.add(campDiferitTxt);
            p.add(signInBtn);
        }
        else if(e.getSource() == administrator)
        {
            p.remove(campDiferit);
            p.remove(campDiferitTxt);
            p.add(signInBtn);
        }
        this.setContentPane(p);
    }

    public void callProcedureUtilizator()
    {
        try {
            CallableStatement call1 = null;
            call1 = connection.prepareCall("call inserare_utilizator(?,?,?,?,?,?,?,?)");
            call1.setString(1,CNPTxt.getText());
            call1.setString(2,numeTxt.getText());
            call1.setString(3,prenumeTxt.getText());
            call1.setString(4,adresaTxt.getText());
            call1.setString(5,nrTelefonTxt.getText());
            call1.setString(6,emailTxt.getText());
            call1.setString(7,IBanTxt.getText());
            call1.setString(8,parolaTxt.getText());
            call1.execute();
        }
        catch(SQLException sqlex) {
            System.err.println("An SQL Exception occured. Details are provided below:");
            sqlex.printStackTrace(System.err);
        }
    }
}
