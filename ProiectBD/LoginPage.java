import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LoginPage extends JFrame implements ActionListener {
    JPanel p = new JPanel();
    JLabel login = new JLabel("Login page", SwingConstants.CENTER);
    JLabel emailLbl = new JLabel("E-mail:");
    JLabel passwordLbl = new JLabel("Parola:");
    JTextField emailTxt = new JTextField();
    JPasswordField passwordTxt = new JPasswordField();
    JButton loginBtn = new JButton("Login");
    JLabel signIn = new JLabel("First time? Create an account", SwingConstants.CENTER);
    JButton signInBtn = new JButton("Sign in");
    Border border = BorderFactory.createLineBorder(Color.lightGray);
    Connection connection;
    public LoginPage(Connection connection){

        this.connection = connection;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(450, 350);
        this.setTitle("Pagina de logare");

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);

        login.setBounds((this.getWidth() - 200)/2, 10, 200, 30);
        login.setFont(new Font("Calibri", Font.BOLD, 20));
        login.setHorizontalAlignment(SwingConstants.CENTER);
        p.add(login);

        emailLbl.setBounds(50,60,50,30);
        p.add(emailLbl);

        passwordLbl.setBounds(50,100,50,30);
        p.add(passwordLbl);

        emailTxt.setBounds(130, 60, 250, 30);
        emailTxt.setBorder(border);
        p.add(emailTxt);

        passwordTxt.setBounds(130, 100, 250, 30);
        passwordTxt.setBorder(border);
        p.add(passwordTxt);

        loginBtn.setBounds((this.getWidth() - 80)/2, 140, 80, 30);
        loginBtn.setBorder(border);
        //loginBtn.setBackground(Color.);
        p.add(loginBtn);
        loginBtn.addActionListener(this);

        signIn.setBounds((this.getWidth() - 200)/2, 200, 200, 30);
        signIn.setFont(new Font("Calibri", Font.BOLD, 15));
        p.add(signIn);

        signInBtn.setBounds((this.getWidth() - 80)/2, 240, 80, 30);
        signInBtn.setBorder(border);
        p.add(signInBtn);
        signInBtn.addActionListener(this);

        p.setBackground(Color.lightGray);
        this.setContentPane(p);
        this.setLayout(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signInBtn)
        {
            this.setVisible(false);
            SignInPage sgn = new SignInPage(connection);
        }
        else if (e.getSource() == loginBtn)
        {
            if(selectFromTextBox(emailTxt, passwordTxt) == -2)
            {
                Error erroare = new Error("E-mail invalid!");
            }
            else if(selectFromTextBox(emailTxt, passwordTxt) == -1)
            {
                Error erroare = new Error("Parola invalida!");
            }
            else if(selectFromTextBox(emailTxt, passwordTxt) == 1)
            {
                this.setVisible(false);
                Statement selectStatement1 = null;
                ResultSet rs1 = null;
                try {
                    selectStatement1 = connection.createStatement();
                    selectStatement1.execute("SELECT id FROM utilizator WHERE email =\"" + emailTxt.getText() + "\";");
                    rs1 = selectStatement1.getResultSet();
                    if(rs1.next())
                    {
                        StudentPage student = new StudentPage(rs1.getInt("id"), connection);
                    }
                }
                catch(SQLException sqlex) {
                    System.err.println("An SQL Exception occured. Details are provided below:");
                    sqlex.printStackTrace(System.err);
                }
            }
            else if(selectFromTextBox(emailTxt, passwordTxt) == 2)
            {
                this.setVisible(false);
                Statement selectStatement1 = null;
                ResultSet rs1 = null;
                try {
                    selectStatement1 = connection.createStatement();
                    selectStatement1.execute("SELECT id FROM utilizator WHERE email =\"" + emailTxt.getText() + "\";");
                    rs1 = selectStatement1.getResultSet();
                    if(rs1.next())
                    {
                        TeacherPage profesor = new TeacherPage(rs1.getInt("id"), connection);
                    }
                }
                catch(SQLException sqlex) {
                    System.err.println("An SQL Exception occured. Details are provided below:");
                    sqlex.printStackTrace(System.err);
                }
            }
            else if(selectFromTextBox(emailTxt, passwordTxt) == 3)
            {
                this.setVisible(false);
                Statement selectStatement1 = null;
                ResultSet rs1 = null;
                try {
                    selectStatement1 = connection.createStatement();
                    selectStatement1.execute("SELECT id FROM utilizator WHERE email =\"" + emailTxt.getText() + "\";");
                    rs1 = selectStatement1.getResultSet();
                    if(rs1.next())
                    {
                        AdministratorPage administrator = new AdministratorPage(rs1.getInt("id"), connection);
                    }
                }
                catch(SQLException sqlex) {
                    System.err.println("An SQL Exception occured. Details are provided below:");
                    sqlex.printStackTrace(System.err);
                }
            }
            else if(selectFromTextBox(emailTxt, passwordTxt) == 4)
            {
                this.setVisible(false);
                Statement selectStatement1 = null;
                ResultSet rs1 = null;
                try {
                    selectStatement1 = connection.createStatement();
                    selectStatement1.execute("SELECT id FROM utilizator WHERE email =\"" + emailTxt.getText() + "\";");
                    rs1 = selectStatement1.getResultSet();
                    if(rs1.next())
                    {
                        SuperadministratorPage superadmin = new SuperadministratorPage(rs1.getInt("id"), connection);
                    }
                }
                catch(SQLException sqlex) {
                    System.err.println("An SQL Exception occured. Details are provided below:");
                    sqlex.printStackTrace(System.err);
                }
            }
        }
    }

    public int selectFromTextBox(JTextField emailX, JTextField parolaX)
    {
        int ok = -2;
        Statement selectStatement1 = null, selectStatement2 = null, selectStatement3 = null;
        ResultSet rs1 = null, rs2 = null, rs3 = null;
        try {
            selectStatement1 = connection.createStatement();
            selectStatement2 = connection.createStatement();
            selectStatement3 = connection.createStatement();
            selectStatement1.execute("SELECT id FROM utilizator WHERE email =\"" + emailX.getText() + "\";");
            rs1 = selectStatement1.getResultSet();
            selectStatement2.execute("SELECT id FROM utilizator WHERE parola =\"" + parolaX.getText() + "\";");
            rs2 = selectStatement2.getResultSet();
            if(rs1.next())
            {
                ok = -1;
                if(rs2.next() && rs1.getInt("id") == rs2.getInt("id"))
                {
                    ok = 0;
                    selectStatement3.execute("SELECT idStudent FROM student WHERE idStudent = \"" + rs1.getInt("id") +"\";");
                    rs3 = selectStatement3.getResultSet();
                    if(rs3.next())
                        ok = 1;
                    else
                    {
                        selectStatement3.execute("SELECT idProfesor FROM profesor WHERE idProfesor = \"" + rs1.getInt("id") +"\";");
                        rs3 = selectStatement3.getResultSet();
                        if(rs3.next())
                            ok = 2;
                        else
                        {
                            selectStatement3.execute("SELECT idAdmin FROM administrator WHERE idAdmin = \"" + rs1.getInt("id") +"\";");
                            rs3 = selectStatement3.getResultSet();
                            if(rs3.next())
                                ok = 3;
                            else
                            {
                                selectStatement3.execute("SELECT idSuperAdmin FROM superadministrator WHERE idSuperAdmin = \"" + rs1.getInt("id") +"\";");
                                rs3 = selectStatement3.getResultSet();
                                if(rs3.next())
                                    ok = 4;
                            }
                        }
                    }
                }
            }
        }
        catch(SQLException sqlex) {
            System.err.println("An SQL Exception occured. Details are provided below:");
            sqlex.printStackTrace(System.err);
        }
        return ok;
    }
}
