import java.util.ArrayList;
import java.util.List;

//lab3
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class Main{
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Class group = new Class("Grupa 1", 5);
        Student st1 = new Student("Adam", "Pierwszy", StudentCondition.DOING, 1999, 10);
        Student st2 = new Student("Adam", "Pierwszymat", StudentCondition.DOING, 1999, 0);
        Student st3 = new Student("Tomasz", "Drugi", StudentCondition.ABSENT, 2001, 20);
        Student st4 = new Student("Paweł", "Trzeci", StudentCondition.DOING, 2000, 15);
        Student st5 = new Student("Marcin", "Czwarty", StudentCondition.ILL, 1999, 23.7);
        Student st6 = new Student("Marek", "Piąty", StudentCondition.ABSENT, 1998, 3.6);

        group.addStudent(st1);
        //st1.print();
        group.addStudent(st2);
        //st2.print();
        group.addStudent(st6);
        //st3.print();
        group.addStudent(st4);
        //st4.print();
        group.addStudent(st5);
        //st5.print();
        group.addStudent(st3);
        //st6.print();

        //System.out.println("Dodawanie i odejmowanie punktow");
        group.addPoints(st1, 10.5);
       // st1.print();
        group.removePoints(st1, 5.2);
        //st1.print();

        System.out.println("Test");
        group.test();

        group.summary();

        //System.out.println("Sortowanie po nazwiskach i imionach");
        //group.sortByName();
        //group.summary();

        System.out.println("Sortowanie studentow wedlug punktow");
        group.sortByPoints();
        //group.summary();
        group.max();

        ClassContainer studentsGroup = new ClassContainer();


        studentsGroup.addClass("Grupa 1", 5);
        studentsGroup.addClass("Grupa 2", 7);
        studentsGroup.addClass("Grupa 3", 2);
        studentsGroup.addClass("Grupa 4", 4);

        studentsGroup.removeClass("Grupa 2");

        Class tmp = studentsGroup.getGroup("Grupa 1");
        tmp.addStudent(st1);
        tmp.addStudent(st2);
        tmp.addStudent(st3);

        tmp = studentsGroup.getGroup("Grupa 3");
        tmp.addStudent(st3);

        System.out.println("Wykaz pustych grup studentow");
        List<Class> groupList = new ArrayList<Class>();

        groupList = studentsGroup.findEmpty();

        System.out.println("List of empty groups");
        for(Class cl: groupList) {
            System.out.println(cl.getNameGroup());
        }

        studentsGroup.summary();


        // -- lab 3 --
        //JFrame frame = new JFrame("Students");
        //JLabel label = new JLabel("Students group", JLabel.CENTER);
        /*
            Okno dzieli sie na panele:
            - panel userData - wykaz tabeli studentow w grupie lub grup studentow
            - panel actions - przyciski w zaleznosci od tego czy grupa studentow czy grupy
            * Akcje grupy
                - dodaj grupe
                - usun grupe
                - zmien nazwe grupy, zmien limit grupy
                - otworz grupe ( otwiera sie lista studentow w grupie)

            * Akcje studentow
                - dodaj studenta do grupy
                - usun studenta z grupy
                - zmien informacje o studencie

             Tworzenie warstwy danych i warstwy graficznej
        */
        //JPanel userData = new JPanel();
        //JPanel

        String[] studentColumns = {"ID", "First Name", "Last Name", "Student's Condition", "Year of Birth", "Scores"};
        List<Student> studentsList = group.getStudentList();
        //String[][] data = new String[studentsList.size()][5];
        String studentData[][] = new String[5][6];

        int i = 0;
        for(Student st: studentsList) {
            studentData[i][0] = String.valueOf(i+1);
            studentData[i][1] = st.getFirstName();
            studentData[i][2] = st.getLastName();
            studentData[i][3] = st.getStatusName();
            studentData[i][4] = String.valueOf(st.getDateOfBirth());
            studentData[i][5] = String.valueOf(st.getScores());
            i++;
        }

        String[] groupColumns = { "ID", "Name", "Capacity" };
        String[][] groupData = new String[10][3];
        Class[] groupsTmp = new Class[3];
        groupsTmp[0] = studentsGroup.getGroup("Grupa 1");
        groupsTmp[1] = studentsGroup.getGroup("Grupa 3");
        groupsTmp[2] = studentsGroup.getGroup("Grupa 4");

        for(i = 0; i < 3; i++) {
            groupData[i][0] = String.valueOf(i + 1);
            groupData[i][1] = groupsTmp[i].getNameGroup();
            groupData[i][2] = String.valueOf(groupsTmp[i].getMaxListSize());
        }

        ProgramFrame guiFrame = new ProgramFrame("Students Management System", "Student's Group", groupData, groupColumns);
        guiFrame.initFrame();

        //settings for the panel
        //BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        //panel.setLayout(boxlayout);
        //panel.setBorder(new EmptyBorder( new Insets(45, 70, 45, 70)));

        // Create JTable
//        JTable jt = new JTable(data, columns);
//        JScrollPane sp = new JScrollPane(jt);



        // defines buttons
        //JButton jb1 = new JButton("Button 1");
        //JButton jb2 = new JButton("Button 2");
        //JButton jb3 = new JButton("Button 3");

        //add buttons to the frame
        //panel.add(jb1);
        //panel.add(jb2);
        //panel.add(jb3);

        // add the label nad panel to the frame
        //frame.setLayout(new GridLayout(2, 1));
//        frame.add(sp);
        //frame.add(label);
        //frame.add(panel);

        // Settings for the frame
//        frame.pack();
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(700, 560);
//        frame.setVisible(true);
    }
}