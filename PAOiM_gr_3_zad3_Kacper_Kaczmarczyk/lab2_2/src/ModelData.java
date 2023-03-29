import java.util.List;
import java.util.ArrayList;

public class ModelData {
    private List<String> studentColumns;
    private List<Student> studentsInGroup;

    private List<String> groupColumns;
    private ClassContainer studentGroups;

    public ModelData()
    {
        this.groupColumns = new ArrayList<String>();
        this.groupColumns.add("Group Name");
        this.groupColumns.add("Size Group");
        this.groupColumns.add("Group Status(%)");
        this.studentGroups = new ClassContainer();

        this.studentColumns = new ArrayList<String>();
        this.studentColumns.add("First Name");
        this.studentColumns.add("Last Name");
        this.studentColumns.add("Status");
        this.studentColumns.add("Year of Birth");
        this.studentColumns.add("Scores");
        this.studentsInGroup = new ArrayList<Student>();

    }


    public List<String> getStudentColumns() {
        return studentColumns;
    }

    public void setStudentColumns(List<String> studentColumns) {
        this.studentColumns = studentColumns;
    }

    public List<Student> getStudentsInGroup() {
        return studentsInGroup;
    }

    public void addStudentToGroup(Student student)
    {
        this.studentsInGroup.add(student);
    }

    public void addGroup(Class group) {
        this.studentGroups.addClass(group.getNameGroup(), group.getMaxListSize());
    }

    public void setStudentsInGroup(List<Student> studentsInGroup) {
        this.studentsInGroup = studentsInGroup;
    }

    public List<String> getGroupColumns() {
        return groupColumns;
    }

    public void setGroupColumns(List<String> groupColumns) {
        this.groupColumns = groupColumns;
    }

    public List<Class> getGroups() {
        List<Class> groups = new ArrayList<Class>(this.studentGroups.getGroup().values());
        return groups;
    }

    public void init()
    {

        studentGroups.addClass("Grupa 1", 5);
        studentGroups.addClass("Grupa 2", 7);
        studentGroups.addClass("Grupa 3", 2);

        Student st1 = new Student("Adam", "Pierwszy", StudentCondition.DOING, 1999, 10);
        Student st2 = new Student("Adam", "Pierwszymat", StudentCondition.DOING, 1999, 0);
        Student st3 = new Student("Tomasz", "Drugi", StudentCondition.ABSENT, 2001, 20);
        Student st4 = new Student("Paweł", "Trzeci", StudentCondition.DOING, 2000, 15);
        Student st5 = new Student("Marcin", "Czwarty", StudentCondition.ILL, 1999, 23.7);
        Student st6 = new Student("Marek", "Piąty", StudentCondition.ABSENT, 1998, 3.6);

        Class tmp = studentGroups.getGroup("Grupa 1");
        tmp.addStudent(st1);
        tmp.addStudent(st2);
        tmp.addStudent(st3);

        tmp = studentGroups.getGroup("Grupa 3");
        tmp.addStudent(st4);

        tmp = studentGroups.getGroup("Grupa 2");
        tmp.addStudent(st5);
        tmp.addStudent(st6);


    }

}
