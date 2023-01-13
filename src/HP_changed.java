import java.awt.*;
import java.util.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.lang.*;
import java.util.List;

public class HP_changed extends JFrame {
    JFrame f = new JFrame();
    JFrame f1 = new JFrame("Welcome "); //frame for home window to display login types

    JFrame f2 = new JFrame("Details of Registered students");
    JFrame f3 = new JFrame("Register for an EVent");//frame to display register screen for student
    JFrame f4 = new JFrame("Add Event"); //add event frame for super admin
    JFrame f6 = new JFrame("Admin ");  //frame to display the tables from the db to super admin

    //login buttons for super admin and student
    JButton sup = new JButton("SuperAdmin  ");
    JButton register = new JButton("Register  ");
    //button for admin to view db or student to register for event
    JButton reg = new JButton("Register");
    JButton get = new JButton("See Database");
    JButton adde = new JButton("Add Event");

    JButton addevent = new JButton("Add Event to DB");
    JPasswordField pass = new JPasswordField(10);



    ArrayList<String> listofevents = new ArrayList<String>() {
        {
            // these are sample events:
            add("Code in Chaos");add("Play With Data");add("Paper Presentation");
            add("Workshop:Blockchain+Web3");add("Web on");add("OSPC");
            add("PyDon");add("WorkShop: IEEE ");add("Fastestfinger");
            add("Connexions");add("Treasure Hunt");
            //TODO: Make comment the below line
            //add("abc"); add("Hackathon");add("Pixelate");
        }
    };

//    String eventlist[]={"Code n Chaos","Play with Data","Paper Presentation","Workshop on Blockchain+web3",
//            "Web on","OSPC","PyDon","IEEE WorkShop","FastestFinger","Connexions","Treasure Hunt"};


    JComboBox cb1 = new JComboBox(listofevents.toArray()); //for admin
    JComboBox cb2 = new JComboBox(listofevents.toArray()); //for student
    //text fields in student reg form
    JTextField Name = new JTextField(10);
    JTextField Dept = new JTextField(10);
    JTextField Sem = new JTextField(10);
    JTextField Contact = new JTextField(10);
    JTextField Gmail = new JTextField(10);

    JTextField Deptt = new JTextField(10);
    JTextField Eventname = new JTextField(10);
    JTextField DOE = new JTextField(10);
    JTextField Time = new JTextField(10);
    JTextField Timing = new JTextField(10);
    JTextField Venue = new JTextField(10);
    JTextField Prizes = new JTextField(10);
    JTextField Extra  = new JTextField(10);

    // object of action listner class:
    ListenForButton listen = new ListenForButton();

    //constructor for index page class:
    public HP_changed(){
        index();
    }
    public void index(){
        f1.setLocationRelativeTo(null);
//        window icon
//         Icon logo_b = new ImageIcon(getClass().getResource("logo_b.jpg"));;
//        window logo
        Icon logo_a = new ImageIcon(getClass().getResource("logo_b.jpg"));
        Icon prayatna = new ImageIcon(getClass().getResource("prayatna.jpg"));
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

        adde.addActionListener(listen);
        thePanel2.add(adde);

        f6.add(thePanel2,BorderLayout.SOUTH);
        f6.setResizable(true);
        f6.setVisible(true);

    }
    public void addEvent(){

        f4.setSize(700,400);
        f4.setLocationRelativeTo(null);
        f4.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel thePanel4 = new JPanel();
        thePanel4.setLayout(new GridLayout(8,2,20,20));

        JLabel ae1 = new JLabel("Department:");
        thePanel4.add(ae1);
        thePanel4.add(Deptt);

        JLabel ae2 = new JLabel("Event Name:");
        thePanel4.add(ae2);
        thePanel4.add(Eventname);

        JLabel ae3 = new JLabel("Date of event:");
        thePanel4.add(ae3);
        thePanel4.add(DOE);

        JLabel ae4 = new JLabel("Time:");
        thePanel4.add(ae4);
        thePanel4.add(Time);

        JLabel ae5 = new JLabel("Timing:");
        thePanel4.add(ae5);
        thePanel4.add(Timing);

        JLabel ae6 = new JLabel("Venue:");
        thePanel4.add(ae6);
        thePanel4.add(Venue);

        JLabel ae7 = new JLabel("Prizes:");
        thePanel4.add(ae7);
        thePanel4.add(Prizes);

        JLabel ae8 = new JLabel("Extras:");
        thePanel4.add(ae8);
        thePanel4.add(Extra);

        f4.add(thePanel4,BorderLayout.NORTH);

        JPanel thePanel24 = new JPanel();
        addevent.addActionListener(listen); //addevent button to add event details to db
        thePanel24.add(addevent);
        f4.add(thePanel24,BorderLayout.SOUTH);

        f4.setResizable(true);
        f4.setVisible(true);
    }
    public void studregister(){
        f3.setSize(700,400);
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
    /*    public void Details(ResultSet rs){
            f2.setSize(700,360);
            f2.setLocationRelativeTo(null);
            f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f2.setLayout(new BorderLayout());
            String[] columnNames = new String[]{"Name", "Department", "Semester", "Contact_No", "Gmail"};

            JPanel Panel2 = new JPanel();
            JTable tb = new JTable();
            JScrollPane src = new JScrollPane(Panel2);

            DefaultTableModel model = new DefaultTableModel();
            model.setColumnIdentifiers(columnNames);
            tb.setModel(model);
            tb.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            tb.setFillsViewportHeight(true);
            src.setHorizontalScrollBarPolicy(
                    JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            src.setVerticalScrollBarPolicy(
                    JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            model.addRow(new Object[]{"Name ","Department ","Semester ","Contact ","Gmail "});
            try {
                // for repeatedly showing the rows:

                while(rs.next())
                {
                    String a = rs.getString(1); //name
                    String b = rs.getString(2); //dept
                    String c = rs.getString(3); //sem
                    String d = rs.getString(4); //contact no
                    String y = rs.getString(5); //gmail
                    model.addRow(new Object[]{a,b,c,d,y});
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }

            Panel2.add(tb);
    //        Panel2.add(src);
            f2.add(Panel2,BorderLayout.CENTER);
            f2.setSize(400,400);
            f2.setVisible(true);
            f2.setResizable(true);
        }
     */
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
        JFrame Dframe = new JFrame("Event Registration Details");
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
                int contact = Integer.parseInt(Contact.getText());
                String gmail = Gmail.getText();
                String Department = "CS"; //the dept that organises the events
                String Event = null;
                String item = (String) cb2.getItemAt(cb2.getSelectedIndex());
//                System.out.println(item);
//                String Date = null,Time = null,Timing = "2 Hours",venue = "CT Dept";
//                String prizes = "Prizes will be distributed at CB103 on 25th May 2023";
//                String extra = "Please reach 15min before Event.";
                String Date = null, Time = null, Timing = null, venue=null, prizes=null,extra=null;
//               System.out.println(item);
                Connection conn = null;
                ResultSet rs = null;
                try{
                    conn = DriverManager.getConnection(
                            "jdbc:oracle:thin:@localhost:1521:orcl", "system", "Sam03sam");
                    String selectQ="SELECT * FROM events WHERE event LIKE '"+item+"'";
                    PreparedStatement stmnt = conn.prepareStatement(selectQ);
                    rs =stmnt.executeQuery();
                    while (rs.next()){
                        Event = rs.getString(2);
                        Date = rs.getString(3);
                        Time = rs.getString(4);
                        Timing = rs.getString(5);
                        venue = rs.getString(6);
                        prizes = rs.getString(7);
                        extra = rs.getString(8);
                    }
                    System.out.println(Date);
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
                }catch (SQLException e2){
                    System.out.println(e2);
                    JOptionPane.showMessageDialog(f,e2.getMessage(),"Error",JOptionPane.WARNING_MESSAGE);
                }
                if(item.equalsIgnoreCase("Workshop: Blockchain+Web3")==false || item.equalsIgnoreCase("Workshop: IEEE")==false ){
                Event=item.toLowerCase();
                Event=Event.replaceAll(" ","");}
                else if(item.equalsIgnoreCase("Workshop: Blockchain+Web3")){
                    Event="blockweb3";
                }
                else if(item.equalsIgnoreCase("Workshop: IEEE")){
                    Event="ieee";
                }
                System.out.println(Event);
                String insertQ = "INSERT INTO " + Event + "(name, department, semester, contactno, gmail) " +
                        "VALUES(?,?,?,?,?)";
                try{
                    conn = DriverManager.getConnection(
                            "jdbc:oracle:thin:@localhost:1521:orcl", "system", "Sam03sam");
                    PreparedStatement stmnt = conn.prepareStatement(insertQ);
                    stmnt.setString(1,name);
                    stmnt.setString(2,dept);
                    stmnt.setString(3,sem);
                    stmnt.setInt(4,contact);
                    stmnt.setString(5,gmail);
                    int row = stmnt.executeUpdate();
                    System.out.println(String.format("Details added %d", row));

                }catch (SQLException sqle){
                    System.out.println(sqle);
                    JOptionPane.showMessageDialog(f,sqle.getMessage(),"Error",JOptionPane.WARNING_MESSAGE);
                }
            } //reg button activites get over here

            //checking if the superadmin enters crct password to view DB
            else if(e.getSource() == get) {
                // to check if entered password is wrong:
                if (!Arrays.equals(pass.getPassword(), new char[]{'S','a','m','0','3','s','a','m'}))
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
                        con2 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system",
                                "Sam03sam");


                        if(item.equals("Code in Chaos") ) {
                            String cc = "select * from codeinchaos";
                            st = con2.prepareStatement(cc);
                            rs = st.executeQuery();
                            String[][] a1 = Details(rs);
                            display(a1);
                        }

                        else if(item.equals("Play With Data")) {
                            String pwd = "select * from playwithdata";
                            st = con2.prepareStatement(pwd);
                            rs = st.executeQuery();
                            String[][] a2 =  Details(rs);
                            display(a2);
                        }

                        else if(item.equals("Web on")) {
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

                        else if(item == "Fastestfinger") {
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

                        else if(item == "Workshop:Blockchain+Web3") {
                            String web3 = "select * from blockweb3";
                            st = con2.prepareStatement(web3);
                            rs = st.executeQuery();
                            String[][] a9 = Details(rs);
                            display(a9);
                        }

                        else if(item == "IEEE WorkShop") {
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
//                        else if(item == "Hackathon") {
//                            String hk = "select * from hackathon" ;
//                            st = con2.prepareStatement(hk);
//                            rs = st.executeQuery();
//                            String[][] a12 = Details(rs);
//                            display(a12);
//                        }
//                        else if(item == "Pixelate") {
//                            String px = "select * from pixelate" ;
//                            st = con2.prepareStatement(px);
//                            rs = st.executeQuery();
//                            String[][] a13 = Details(rs);
//                            display(a13);
//                        }
//                        else if(item == "abc") {
//                            String abc = "select * from abc" ;
//                            st = con2.prepareStatement(abc);
//                            rs = st.executeQuery();
//                            String[][] a14 = Details(rs);
//                            display(a14);
//                        }
                        else{
                            String a = item;
                            String q1 = "Select * from "+a;
                            st = con2.prepareStatement(q1);
                            rs = st.executeQuery();
                            String[][] a15 = Details(rs);
                            display(a15);
                        }

                    } catch (SQLException e2) {
                        JOptionPane.showMessageDialog(f,"error in Preparedstatement.","Error",JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
            else if(e.getSource() == adde){
                //if the password is right then open the add event frame
                if (!Arrays.equals(pass.getPassword(), new char[]{'S','a','m','0','3','s','a','m'}))
                    JOptionPane.showMessageDialog(f, "Wrong Password.", "Error", JOptionPane.WARNING_MESSAGE);
                else {
                    addEvent();
                }
            }
            //if admin need to add an event, events details are obtained inserted into event table and a tbale for that
            //particular event is also created with columns like name
            else if(e.getSource()==addevent) {
                String dept = Deptt.getText(); //the dept that organises the events
                String event = Eventname.getText();
                String doe = DOE.getText();
                String time = Time.getText();
                String timing = Timing.getText();
                String venue = Venue.getText();
                String prizes = Prizes.getText();
                String extra = Extra.getText();
                listofevents.add(event);
                for(int i = 0; i < listofevents.size(); i++) {
                    System.out.print(" "+listofevents.get(i));
                }
                //TODO: uncomment the below line
                cb2 = new JComboBox(listofevents.toArray());
                cb1 = new JComboBox(listofevents.toArray());
                String insertQ = "INSERT INTO events (dept, event, doe, time, timing,venue,prizes,extra) " +
                        "VALUES(?,?,?,?,?,?,?,?)";
                Connection conn = null;
                try {
                    conn = DriverManager.getConnection(
                            "jdbc:oracle:thin:@localhost:1521:orcl", "system", "Sam03sam");
                    PreparedStatement stmnt = conn.prepareStatement(insertQ);
                    stmnt.setString(1, dept);
                    stmnt.setString(2, event);
                    stmnt.setString(3, doe);
                    stmnt.setString(4, time);
                    stmnt.setString(5, timing);
                    stmnt.setString(6, venue);
                    stmnt.setString(7, prizes);
                    stmnt.setString(8, extra);
                    int row = stmnt.executeUpdate();
                    System.out.println(String.format("Details added %d", row));
                    String status = event + " Added \n";
                    status += ">>> Event Details:" + "\n";
                    status += ">Date : " + doe + "\n";
                    status += ">Time : " + time + "\n";
                    status += ">Timing : " + timing + "\n";
                    status += ">Venue : " + venue + "\n";
                    status += ">Prizes : " + prizes + "\n";
                    status += extra;
                    JOptionPane.showMessageDialog(f, status);
                    f4.dispose();
                } catch (SQLException sqle) {
                    System.out.println(sqle);
                    JOptionPane.showMessageDialog(f, "Something is going wrong.", "Error", JOptionPane.WARNING_MESSAGE);
                }
                event = event.toLowerCase();
                event = event.replaceAll(" ", "");

                String createQ = "CREATE TABLE " + event + " (name varchar(20) not null," +
                        " department varchar(20) not null,"+ " semester varchar(10) not null," + " contactno varchar(10) not null unique," + " gmail varchar(20) not null)" ;
                try{
                    conn = DriverManager.getConnection(
                            "jdbc:oracle:thin:@localhost:1521:orcl", "system", "Sam03sam");
                    Statement stmnt = conn.createStatement();
                    int res = stmnt.executeUpdate(createQ);
                    if(res!=0){
                        System.out.println("Table created ");
                    }
                    JOptionPane.showMessageDialog(f, "Event Table created", "Message", JOptionPane.PLAIN_MESSAGE);

                }catch (Exception sqle1){
                    sqle1.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        new HP_changed();
    }
}


