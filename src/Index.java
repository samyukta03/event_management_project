import oracle.jdbc.proxy.annotation.Pre;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.*;
import java.lang.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.*;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;


public class Index {

    private static final String dbClassName = "oracle.jdbc.driver.OracleDriver";

    JFrame f = new JFrame();

    JFrame f1 = new JFrame();

    JFrame f2 = new JFrame();

    JFrame f3 = new JFrame();

    JFrame f6 = new JFrame();

    JButton sup = new JButton("SuperAdmin  ");

    JButton register = new JButton("Register  ");

    JButton reg = new JButton("Register");

    JButton get = new JButton("See Database");

    JPasswordField pass = new JPasswordField(10);

    // these are sample events:
    String event[]={"Code n Chaos","Play with Data","Paper Presentation","Workshop on Blockchain+web3",
            "Web on","OSPC","PyDon","IEEE WorkShop","FastestFinger","Connexions","Treasure Hunt"};

    JComboBox cb1 = new JComboBox(event);

    JComboBox cb2 = new JComboBox(event);

    JTextField Name = new JTextField(10);

    JTextField Dept = new JTextField(10);

    JTextField Sem = new JTextField(10);

    JTextField Contact = new JTextField(10);

    JTextField Gmail = new JTextField(10);

    // object of action listener class:
    ListenForButton listen = new ListenForButton();

    // Constructor:
    Index() {
        Index();
    }
    public void Index(){
        f1.setLocationRelativeTo(null);
        JPanel pan = new JPanel();
        pan.setBackground(new Color(0, 255, 255, 50));
        f1.add(pan, BorderLayout.NORTH);
        JPanel pan1 = new JPanel();
        //SuperAdmin Button
        sup.setBorderPainted(false);
        sup.setContentAreaFilled(false);
        sup.addActionListener(listen);
        //Register Button
        register.setBorderPainted(false);
        register.setContentAreaFilled(false);
        register.addActionListener(listen);

        pan1.add(sup, BorderLayout.WEST);
        pan1.add(register, BorderLayout.EAST);
        pan1.setBackground(new Color(0, 255, 255, 50));
        f1.add(pan1, BorderLayout.CENTER);

        f1.setTitle("PRAYATNA'23");
        f1.setResizable(false);
        f1.setVisible(true);
        f1.setSize(700, 360);
        f1.setDefaultLookAndFeelDecorated(true);
        f1.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    //Superadmin - two fns :  one to enter password and event, two to see the database of particular event

    //window to prompt password and select event to get db for super admin
    public void Pass() {
        f6.setSize(430,150);
        f6.setLocationRelativeTo(null);
        f6.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        //ImageIcon logo_b = new ImageIcon("logo_b.jpg");
        // f6.setIconImage(logo_b.getImage());

        JPanel thePanel1 = new JPanel();
        thePanel1.setLayout(new GridLayout(2,3,20,20));

        JLabel lb1 = new JLabel("Enter password:");
        thePanel1.add(lb1);
        thePanel1.add(pass);

        JLabel lb6 = new JLabel("Select Event*:");
        thePanel1.add(lb6);
        thePanel1.add(cb1);

        f6.add(thePanel1,BorderLayout.NORTH);

        JPanel thePanel2 = new JPanel();
        get.addActionListener(listen);
        thePanel2.add(get);
        f6.add(thePanel2,BorderLayout.SOUTH);

        f6.setResizable(false);
        f6.setVisible(true);
    }

    //---------------------------SUPERADMIN_DB DISPLAY-----------------------------
    public void Details(ResultSet rs) {
        f2.setSize(500,430);
        f2.setLocationRelativeTo(null);
        f2.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel Panel2 = new JPanel();
        JTable tb = new JTable();
        JScrollPane src = new JScrollPane(Panel2);
        DefaultTableModel model = new DefaultTableModel(new String[]{"Name", "Department", "Semester", "Contact_No", "Gmail"}, 0);
        // set column names:
        model.addRow(new Object[]{"Name >>","Department >>","Semester >>","Contact >>","Gmail >>"});
        try {
            // for repeatedly showing the rows:

            while(rs.next())
            {
                String a = rs.getString("name");
                String b = rs.getString("department");
                String c = rs.getString("semester");
                int d = rs.getInt("contactno");
                String y = rs.getString("gmail");
                model.addRow(new Object[]{a,b,c,d,y});
            }
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        tb.setModel(model);
        Panel2.add(tb);
        //Panel2.add(src);
        f2.add(Panel2,BorderLayout.CENTER);


        f2.setVisible(true);
        f2.setResizable(false);
    }

    //Registration :

    //window to register for an event
    public void Register() {
        f3.setSize(430,340);
        f3.setLocationRelativeTo(null);
        f3.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        //ImageIcon logo_b = new ImageIcon("logo_b.jpg");
        // f3.setIconImage(logo_b.getImage());

        JPanel thePanel1 = new JPanel();
        thePanel1.setLayout(new GridLayout(7,3,20,20));

        JLabel lb1 = new JLabel("Name*:");
        thePanel1.add(lb1);
        thePanel1.add(Name);

        JLabel lb2 = new JLabel("Dept.*:");
        thePanel1.add(lb2);
        thePanel1.add(Dept);

        JLabel lb3 = new JLabel("Semester*:");
        thePanel1.add(lb3);
        thePanel1.add(Sem);

        JLabel lb4 = new JLabel("Contact No.*:");
        thePanel1.add(lb4);
        thePanel1.add(Contact);

        JLabel lb5 = new JLabel("Gmail*:");
        thePanel1.add(lb5);
        thePanel1.add(Gmail);

        JLabel lb6 = new JLabel("Select Event*:");

        thePanel1.add(lb6);
        thePanel1.add(cb2);

        f3.add(thePanel1,BorderLayout.NORTH);

        JPanel thePanel2 = new JPanel();
        reg.addActionListener(listen);
        thePanel2.add(reg);
        f3.add(thePanel2,BorderLayout.SOUTH);

        f3.setResizable(false);
        f3.setVisible(true);
    }


    //ActionListener for buttons
    private class ListenForButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            // if sup button is pressed:
            if (e.getSource() == sup) {
                Pass();
            }
            // if register button is pressed:
            else if (e.getSource() == register) {
                Register();
            }
            // if reg button is pressed:
            else if (e.getSource() == reg) {
                String name = Name.getText();
                String dept = Dept.getText();
                String sem = Sem.getText();
                String contact = Contact.getText();
                String gmail = Gmail.getText();
                String Department = "CS";
                String Event = null;
                // variable to store the value of selected event:
                String item = (String) cb2.getItemAt(cb2.getSelectedIndex());
                // variables for event details:
                String Date_of_event = null, Time = null, Timing = "Two Hours", venue = "Training and placement cell", prizes = "prizes will be distributed on 25th may 2023 to winners in CT Dept", extra = "> please reach 15min before Event.";

                if (item == "Code in Chaos") {
                    //This is database
                    Department = "CS";
                    //This is a table inside databse above.
                    Event = "codeinchaos";
                    Date_of_event = "22-05-2023";
                    Time = "10:00 AM";
                    venue = "CT Dept.";
                    prizes = "Certificates are awarded to winners.";
                    extra += "\n" + "> Please bring your own systems";
                } else if (item == "Play with Data") {
                    Department = "CS";
                    Event = "playwithdata";
                    Date_of_event = "22-05-2023";
                    Time = "02:00 PM";
                    venue = "CB203";
                    prizes = "Certificates are awarded to winners.";
                } else if (item == "WebOn") {
                    Department = "CS";
                    Event = "webon";
                    Date_of_event = "19-05-2023";
                    Time = "10:00 AM";
                    venue = "CB103";
                    prizes = "Internship opportunity for winner.";
                } else if (item == "OSPC") {
                    Department = "CS";
                    Event = "ospc";
                    Date_of_event = "19-05-2023";
                    Time = "11:00 AM";
                    venue = "CT Dept";
                    prizes = "Certificates are awarded to winners.";
                } else if (item == "PyDon") {
                    Department = "CS";
                    Event = "pydon";
                    Date_of_event = "22-05-2023";
                    Time = "10:00 AM";
                    venue = "CT Dept";
                    prizes = "Certificates are awarded to winners.";
                    extra = "\n" + "> please bring your own components.";
                } else if (item == "Fastest Finger") {
                    Department = "CS";
                    Event = "fastestfinger";
                    Date_of_event = "19-05-2023";
                    Time = "02:00 PM";
                    venue = "CT Dept.";
                    prizes = "Certificates are awarded to winners.";
                } else if (item == "Connexions") {
                    Department = "CS";
                    Event = "connexions";
                    Date_of_event = "23-05-2023";
                    Time = "10:00 AM";
                    venue = "CT Dept.";

                } else if (item == "Treasure Hunt") {
                    Department = "CS";
                    Event = "treasurehunt";
                    Date_of_event = "22-05-2023";
                    Time = "01:00 PM";
                    venue = "CT Dept.";
                    prizes = "Certificate of Participation is awarded..";
                } else if (item == "Workshop on Blockchain+Web3") {
                    Department = "CS";
                    Event = "blockweb3";
                    Date_of_event = "19-05-2023";
                    Time = "09:00 AM";
                    Timing = "5 Hours";
                    venue = "CT Dept.";
                } else if (item == "IEEE Workshop") {
                    Department = "CS";
                    Event = "ieee";
                    Date_of_event = "22-05-2023";
                    Time = "09:00 AM";
                    Timing = "3 Hours";
                    venue = "CT Dept.";
                } else if (item == "Paper Presentation") {
                    Department = "CS";
                    Event = "paperpresentation";
                    Date_of_event = "22-05-2023";
                    Time = "02:00 PM";
                    Timing = "1 Hour";
                    venue = "CT Dept";
                }
                String insertQ = "INSERT INTO " + Event + "(Name, Department, Semester, ContactNo, Gmail) " +
                        "VALUES(?,?,?,?,?)";
                Connection con = null;
                try{
                    Class.forName(dbClassName);
                    con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system",
                            "samyu");
                    PreparedStatement pstmt = con.prepareStatement(insertQ);
                    pstmt.setString(1, name);
                    pstmt.setString(2, dept);
                    pstmt.setString(3, sem);
                    pstmt.setString(4, contact);
                    pstmt.setString(5, gmail);

                    int rowAffected = pstmt.executeUpdate();
                    System.out.println(String.format("Row affected %d", rowAffected));

                    String status = "You have been registered in " + item + "\n";

                    status += ">>> Event Details:" + "\n";

                    status += ">Date : " + Date_of_event + "\n";

                    status += ">Time : " + Time + "\n";

                    status += ">Timing : " + Timing + "\n";

                    status += ">Venue : " + venue + "\n";

                    status += ">Prizes : " + prizes + "\n";

                    status += extra;

                    JOptionPane.showMessageDialog(f,status);
                    f3.dispose();
                }catch (Exception a){
                    System.out.println(a);

                }
            }


            else if(e.getSource() == get) {
                // to check if entered password is wrong:
                if (!Arrays.equals(pass.getPassword(), new char[]{'s','a','m','y','u'}))
                    JOptionPane.showMessageDialog(f,"Wrong Password.","Error",JOptionPane.WARNING_MESSAGE);
                else {
                    String Department = "CS",item = (String)cb1.getItemAt(cb1.getSelectedIndex());
                    //String e1 = "";
                    StringBuffer s1 = new StringBuffer();
                    System.out.println(item);

                    //System.out.println(e1);
                    //String dbClassName = "com.mysql.cj.jdbc.Driver";
                    String dbClassName = "oracle.jdbc.driver.OracleDriver";

                    //String CONNECTION = "jdbc:mysql://127.0.0.1/" + Department;
                    //String connect = "jdbc:oracle:thin:@localhost:1521:xe";

                    Connection con2 = null;

                    java.sql.Statement st = null;

                    try{
                        Class.forName(dbClassName);

                        con2 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system",
                                "samyu");

                        st = con2.createStatement();
                    }
                    catch (Exception ee1) {
                        JOptionPane.showMessageDialog(f,"Error in connection.","Error",JOptionPane.WARNING_MESSAGE);
                    }

                    PreparedStatement s = null;
                    ResultSet rs = null;
                    try {
                        con2 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system",
                                "samyu");

                        if(item.equals("Code in Chaos") ) {
                            //String e1 = new String("codeinchaos");
                            s = con2.prepareStatement("select * from codeinchaos");
                            rs = s.executeQuery();
                            Details(rs);
                        }

                        else if(item.equals("Play with Data")) {
                            String e1 = new String("playwithdata");
                            s = con2.prepareStatement("select * from playwithdata");
                            rs = s.executeQuery();
                            Details(rs);
                        }

                        else if(item.equals("Web On")) {
                            //e1 = "webon";
                            s = con2.prepareStatement("select * from webon ");
                            rs = s.executeQuery();
                            Details(rs);
                        }

                        else if(item.equals("OSPC")) {
                            //e1 = "ospc";
                            s = con2.prepareStatement("select * from ospc");
                            rs = s.executeQuery();
                            Details(rs);
                        }

                        else if(item == "PyDon") {
                            //e1 = "pydon";
                            s = con2.prepareStatement("select * from pydon");
                            rs = s.executeQuery();
                            Details(rs);
                        }

                        else if(item == "Fastest Finger") {
                            //e1 = "fastestfinger";
                            s = con2.prepareStatement("select * from fastestfinger");
                            rs = s.executeQuery();
                            Details(rs);
                        }

                        else if(item == "Connexions") {
                            //e1 = "connexions";
                            s = con2.prepareStatement("select * from connexions");
                            rs = s.executeQuery();
                            Details(rs);
                        }

                        else if(item == "Treasure Hunt") {
                            //e1 = "treasurehunt";
                            s = con2.prepareStatement("select * from treasurehunt");
                            rs = s.executeQuery();
                            Details(rs);
                        }

                        else if(item == "Workshop on Blockchain+web3") {
                            //e1 = "blockweb3";
                            s = con2.prepareStatement("select * from blockweb3");
                            rs = s.executeQuery();
                            Details(rs);
                        }

                        else if(item == "IEEE Workshop") {
                            //e1 = "ieee";
                            s = con2.prepareStatement("select * from ieee");
                            rs = s.executeQuery();
                            Details(rs);
                        }

                        else if(item == "Paper Presentation") {
                            //e1 = "paperpresentation";
                            s = con2.prepareStatement("select * from paperpresentation" );
                            rs = s.executeQuery();
                            Details(rs);
                        }

                    } catch (SQLException e2) {
                        // TODO Auto-generated catch block
                        JOptionPane.showMessageDialog(f,"error in Preparedstatement.","Error",JOptionPane.WARNING_MESSAGE);
                    }
                    // rs stores items of data base as objects:
//                    ResultSet rs = null;
//                    try {
//                        rs = s.executeQuery();
//                        Details(rs);
//                    } catch (SQLException ee) {
//                        JOptionPane.showMessageDialog(f,"Error in statement.","Error",JOptionPane.WARNING_MESSAGE);
//                    }
                }

            }
        }
    }


    public static void main(String[] args) {

        new Index();

        Connection conn=null;
        ResultSet rs=null;

        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system",
                    "samyu");
            if(conn !=null)
                System.out.println("Connected to the database!");
            else
            {
                System.out.println("Failed to make connection!");
            }

        }catch (Exception e){
            System.out.println(e);
        }
    }
}
