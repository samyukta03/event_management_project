import java.awt.*;
import java.io.*;
import java.util.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.lang.*;
public class indexpage extends JFrame {
    JFrame f = new JFrame();
    JFrame f1 = new JFrame(); //frame for home window to display login types
    JFrame f2 = new JFrame();
    JFrame f3 = new JFrame();//frame to display register screen for student
    JFrame f6 = new JFrame();  //frame to display the tables from the db to super admin
    JFrame f7 = new JFrame();
    JFrame f8 = new JFrame();
    JFrame f9 = new JFrame();
    JFrame f10 = new JFrame();
    JFrame f11 = new JFrame();
    JFrame f12 = new JFrame();
    JFrame f13 = new JFrame();
    JFrame f14= new JFrame();
    JFrame f15 = new JFrame();
    JFrame f16 = new JFrame();
    JFrame f17 = new JFrame();
    //login buttons for super admin and student
    JButton sup = new JButton("SuperAdmin  ");
    JButton register = new JButton("Register  ");
    //button for admin to view db or student to register for event
    JButton reg = new JButton("Register");
    JButton get = new JButton("See Database");
    JPasswordField pass = new JPasswordField(10);
    // these are sample events:
    String event[]={"Code n Chaos","Play with Data","Paper Presentation","Workshop on Blockchain+web3",
            "Web on","OSPC","PyDon","IEEE WorkShop","FastestFinger","Connexions","Treasure Hunt"};
    JComboBox cb1 = new JComboBox(event); //for admin
    JComboBox cb2 = new JComboBox(event); //for student

    //text fields in student reg form
    JTextField Name = new JTextField(10);
    JTextField Dept = new JTextField(10);
    JTextField Sem = new JTextField(10);
    JTextField Contact = new JTextField(10);
    JTextField Gmail = new JTextField(10);

    // object of action listner class:
    ListenForButton listen = new ListenForButton();

    //constructor for index page class:
    public indexpage(){
        index();
    }
    public void index(){
        f1.setLocationRelativeTo(null);
//        window icon
//         Icon logo_b = new ImageIcon(getClass().getResource("logo_b.jpg"));;
//        window logo
         Icon logo_a = new ImageIcon(getClass().getResource("logo_b.jpg"));
         Icon prayatna = new ImageIcon(getClass().getResource("praytna.png"));
        JPanel pan = new JPanel();
        JButton back = new JButton(logo_a);
        pan.add(back);
        back.setRolloverIcon(prayatna);
        pan.setBackground(new Color(0, 0, 255, 50));
        f1.add(pan, BorderLayout.NORTH);
        JPanel pan1 = new JPanel();

        // icons for buttons:
        Icon students_s = new ImageIcon(getClass().getResource("student_s.jpg"));
        Icon event_s = new ImageIcon(getClass().getResource("event_s.jpg"));
        Icon admin_s = new ImageIcon(getClass().getResource("admin_s.jpg"));

        //TODO: add a frame or image box saying the event name

        // Superadmin Button: sup is the button that opens the super admin login window
        sup.setBorderPainted(true);
        sup.setContentAreaFilled(true);
        sup.setIcon(admin_s);
        sup.setToolTipText("Click here to login as Superadmin and to see DataBase");
        sup.addActionListener(listen);

        // register Button: the button that opens the registration window
        register.setBorderPainted(true);
        register.setContentAreaFilled(true);
        register.setIcon(students_s);
        register.setToolTipText("Click here to register as Participant and see details");
        register.addActionListener(listen);

        pan1.add(sup, BorderLayout.WEST);
        pan1.add(register, BorderLayout.EAST);
        pan1.setBackground(new Color(0, 0, 255, 50));
        f1.add(pan1, BorderLayout.CENTER);
        f1.setTitle("PRAYATNA'23");
        f1.setResizable(true);
        f1.setVisible(true);
        f1.setSize(700, 360);
        f1.add(pan1,BorderLayout.CENTER);
        f1.setDefaultLookAndFeelDecorated(true);
        f1.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    //Superadmin - two fns :  one to enter password and event, two to see the database of particular event
    public void admindb(){
        f6.setSize(700,360);
        f6.setLocationRelativeTo(null);
        f6.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

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

        f6.setResizable(true);
        f6.setVisible(true);

    }
    public void studregister(){
        f3.setSize(700,360);
        f3.setLocationRelativeTo(null);
        f3.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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
        reg.addActionListener(listen); //reg is the button that a student clicks to register
        thePanel2.add(reg);
        f3.add(thePanel2,BorderLayout.SOUTH);

        f3.setResizable(true);
        f3.setVisible(true);
    }
        public String [][] Details(ResultSet rs) throws SQLException {
            String[][] disp = new String[5][20];
            int i = 0;
            while (rs.next()) {
                String a = rs.getString(1);//name
                disp[i][0] = a;
                String b = rs.getString(2); //dept
                disp[i][1] = b;
                String c = rs.getString(3); //sem
                disp[i][2] = c;
                String d = rs.getString(4); //contact no
                disp[i][3] = d;
                String y = rs.getString(5); //gmail
                disp[i][4] = y;
                i++;
            }
            return disp;
        }
    public void display(String[][] dis){
        JFrame Dframe = new JFrame();
        Dframe.setSize(700,360);
        Dframe.setLocationRelativeTo(null);
        Dframe.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        String[] colNames = {"Name","Department","Semester","Contact","Gmail"};
        JTable t2;
        t2 = new JTable(dis, colNames);
        t2.setBounds(30,40,200,300);
        JScrollPane sp = new JScrollPane(t2);
        Dframe.add(sp);
        Dframe.setVisible(true);
        Dframe.setResizable(true);
    }
    public class ListenForButton implements  ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == sup) {
                admindb();
            }
            else if(e.getSource() == register) {
                studregister();
            }
            else if(e.getSource()==reg){
                String name = Name.getText();
                String dept = Dept.getText(); //dept of the student registering
                String sem = Sem.getText();
                String contact = Contact.getText();
                String gmail = Gmail.getText();
                String Department = "CS"; //the dept that organises the events
                String Event = null;
                String item = (String) cb2.getItemAt(cb2.getSelectedIndex());
//                System.out.println(item);
                String Date = null,Time = null,Timing = "2 Hours",venue = "CT Dept";
                String prizes = "Prizes will be distributed at CB103 on 25th May 2023";
                String extra = "Please reach 15min before Event.";
                if (item == "Code n Chaos") {
                    //This is database
                    Department = "CS";
                    //This is a table inside databse above.
                    Event = "codeinchaos";
                    Date = "22-05-2023";
                    Time = "10:00 AM";
                    venue = "CT Dept.";
                    prizes = "Certificates are awarded to winners.";
                    extra += "\n" + "> Please bring your own systems";
                } else if (item == "Play with Data") {
                    Department = "CS";
                    Event = "playwithdata";
                    Date = "22-05-2023";
                    Time = "02:00 PM";
                    venue = "CB203";
                    prizes = "Certificates are awarded to winners.";
                } else if (item.equals("Web on")) {
                    Department = "CS";
                    Event = "webon";
                    Date = "19-05-2023";
                    Time = "10:00 AM";
                    venue = "CB103";
                    prizes = "Internship opportunity for winner.";
                } else if (item == "OSPC") {
                    Department = "CS";
                    Event = "ospc";
                    Date = "19-05-2023";
                    Time = "11:00 AM";
                    venue = "CT Dept";
                    prizes = "Certificates are awarded to winners.";
                } else if (item == "PyDon") {
                    Department = "CS";
                    Event = "pydon";
                    Date = "22-05-2023";
                    Time = "10:00 AM";
                    venue = "CT Dept";
                    prizes = "Certificates are awarded to winners.";
                    extra = "\n" + "->Please bring your own components.";
                } else if (item == "FastestFinger") {
                    Department = "CS";
                    Event = "fastestfinger";
                    Date = "19-05-2023";
                    Time = "02:00 PM";
                    venue = "CT Dept.";
                    prizes = "Certificates are awarded to winners.";
                } else if (item == "Connexions") {
                    Department = "CS";
                    Event = "connexions";
                    Date = "23-05-2023";
                    Time = "10:00 AM";
                    venue = "CT Dept.";

                } else if (item == "Treasure Hunt") {
                    Department = "CS";
                    Event = "treasurehunt";
                    Date = "22-05-2023";
                    Time = "01:00 PM";
                    venue = "CT Dept.";
                    prizes = "Certificate of Participation is awarded..";
                } else if (item == "Workshop on Blockchain+web3") {
                    Department = "CS";
                    Event = "blockweb3";
                    Date = "19-05-2023";
                    Time = "09:00 AM";
                    Timing = "5 Hours";
                    venue = "CT Dept.";
                } else if (item == "IEEE WorkShop") {
                    Department = "CS";
                    Event = "ieee";
                    Date = "22-05-2023";
                    Time = "09:00 AM";
                    Timing = "3 Hours";
                    venue = "CT Dept.";
                } else if (item == "Paper Presentation") {
                    Department = "CS";
                    Event = "paperpresentation";
                    Date = "22-05-2023";
                    Time = "02:00 PM";
                    Timing = "1 Hour";
                    venue = "CT Dept";
                }
                String insertQ = "INSERT INTO " + Event + "(name, department, semester, contactno, gmail) " +
                        "VALUES(?,?,?,?,?)";
                Connection conn = null;
                try{
                    conn = DriverManager.getConnection(
                            "jdbc:oracle:thin:@localhost:1521:XE", "system", "samyu");
                    PreparedStatement stmnt = conn.prepareStatement(insertQ);
                    stmnt.setString(1,name);
                    stmnt.setString(2,dept);
                    stmnt.setString(3,sem);
                    stmnt.setString(4,contact);
                    stmnt.setString(5,gmail);
                    int row = stmnt.executeUpdate();
                    System.out.println(String.format("Details added %d", row));
                    String status = "You have been registered in " + item + "\n";
                    status += ">>> Event Details:" + "\n";
                    status += ">Date : " + Date + "\n";
                    status += ">Time : " + Time + "\n";
                    status += ">Timing : " + Timing + "\n";
                    status += ">Venue : " + venue + "\n";
                    status += ">Prizes : " + prizes + "\n";
                    status += extra;
                    JOptionPane.showMessageDialog(f,status);
                    f3.dispose();
                }catch (SQLException sqle){
                    System.out.println(sqle);
                    JOptionPane.showMessageDialog(f,"Something is going wrong.","Error",JOptionPane.WARNING_MESSAGE);
                }
            } //reg button activites get over here

            //checking if the superadmin enters crct password to view DB
            else if(e.getSource() == get) {
                // to check if entered password is wrong:
                if (!Arrays.equals(pass.getPassword(), new char[]{'s', 'a', 'm', 'y', 'u'}))
                    JOptionPane.showMessageDialog(f, "Wrong Password.", "Error", JOptionPane.WARNING_MESSAGE);
                else {
                    String Department = "CS";
                    String item = (String)cb1.getItemAt(cb1.getSelectedIndex());
//                    System.out.println(item);
                    String dbClassName = "oracle.jdbc.driver.OracleDriver";
                    Connection con2 = null;
                    PreparedStatement st = null;
                    ResultSet rs = null;
                    try {
                        con2 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system",
                                "samyu");


                        if(item.equals("Code n Chaos") ) {
                            String cc = "select * from codeinchaos";
                            st = con2.prepareStatement(cc);
                            rs = st.executeQuery();
                            String[][] a1 = Details(rs);
                            display(a1);
                        }

                        else if(item.equals("Play with Data")) {
                            String pwd = "select * from playwithdata";
                            st = con2.prepareStatement(pwd);
                            rs = st.executeQuery();
                            String[][] a2 =  Details(rs);
                            display(a2);
                        }

                        else if(item.equals("Web On")) {
                            String web = "select * from webon ";
                            st = con2.prepareStatement(web);
                            String[][] a3 = Details(rs);
                            display(a3);
                        }

                        else if(item.equals("OSPC")) {
                            String ospc = "select * from ospc";
                            st = con2.prepareStatement(ospc);
                            rs = st.executeQuery();
                            String[][] a4 = Details(rs);
                            display(a4);
                        }

                        else if(item == "PyDon") {
                            String py = "select * from pydon";
                            st = con2.prepareStatement(py);
                            rs = st.executeQuery();
                            String[][] a5 = Details(rs);
                            display(a5);
                        }

                        else if(item == "Fastest Finger") {
                            String ff = "select * from fastestfinger";
                            st = con2.prepareStatement(ff);
                            rs = st.executeQuery();
                            String[][] a6 = Details(rs);
                            display(a6);
                        }

                        else if(item == "Connexions") {
                            String con ="select * from connexions";
                            st = con2.prepareStatement(con);
                            rs = st.executeQuery();
                            String[][] a7 = Details(rs);
                            display(a7);
                        }

                        else if(item == "Treasure Hunt") {
                            String th = "select * from treasurehunt";
                            st = con2.prepareStatement(th);
                            rs = st.executeQuery();
                            String[][] a8 = Details(rs);
                            display(a8);
                        }

                        else if(item == "Workshop on Blockchain+web3") {
                            String web3 = "select * from blockweb3";
                            st = con2.prepareStatement(web3);
                            rs = st.executeQuery();
                            String[][] a9 = Details(rs);
                            display(a9);
                        }

                        else if(item == "IEEE Workshop") {
                            String ieee ="select * from ieee";
                            st = con2.prepareStatement(ieee);
                            rs = st.executeQuery();
                            String[][] a10 = Details(rs);
                            display(a10);
                        }

                        else if(item == "Paper Presentation") {
                            String paper = "select * from paperpresentation" ;
                            st = con2.prepareStatement(paper);
                            rs = st.executeQuery();
                            String[][] a11 = Details(rs);
                            display(a11);
                        }

                    } catch (SQLException e2) {
                        // TODO Auto-generated catch block
                        JOptionPane.showMessageDialog(f,"error in Preparedstatement.","Error",JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        new indexpage();
    }
}

