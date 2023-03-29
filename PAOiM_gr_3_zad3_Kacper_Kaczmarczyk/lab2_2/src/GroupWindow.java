import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GroupWindow extends JFrame {
    private JLabel mainLabel;
    private JPanel groupButtons;
    private JButton addGroup;
    private JButton deleteGroup;
    private JButton editGroup;
    private JTable groupTable;
    private JPanel container;
    private JPanel groupForm;
    private JLabel groupNameLabel;
    private JTextField groupNameInput;
    private JTextField sizeGroupInput;
    private JLabel sizeGroupLabel;
    private JScrollPane GroupScrollPane;
    private JTable studentTable;
    private JScrollPane studentScrollPane;
    private JPanel studentForm;
    private JLabel firstNameLabel;
    private JTextField firstNameInput;
    private JTextField lastNameInput;
    private JComboBox studentConditioInput;
    private JTextField scoresInput;
    private JLabel lastNameLabel;
    private JLabel studentConditionLabel;
    private JLabel scoresLabel;
    private JPanel groupStudentButtons;
    private JButton addStudent;
    private JButton deleteStudentButton;
    private JButton editStudent;
    private JTextField yearOfBirthInput;
    private JLabel yearOfBirthLabel;
    private JFrame frame = new JFrame();
    private Class selectedGroup;
    private int groupIndex;
    private Student selectedStudent;
    private int studentIndex;

    public GroupWindow(String title)
    {
        super(title);
        ModelData modelData = new ModelData();
        modelData.init();
        List<Class> groups = modelData.getGroups();
        GroupTableModel groupTableModel = new GroupTableModel(modelData.getGroupColumns(), groups);
        StudentTableModel studentTableModel = new StudentTableModel(modelData.getStudentColumns(), modelData.getStudentsInGroup());
        groupTable.setModel(groupTableModel);

        studentTable.setModel(studentTableModel);
        this.setContentPane(container);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        addGroup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(groupNameInput.getText().equals("") || sizeGroupInput.getText().equals(""))
                    JOptionPane.showMessageDialog(frame, "All fields must be filled");
                else
                {
                    Class newGroup = new Class();
                    newGroup.setNameGroup(groupNameInput.getText());
                    newGroup.setMaxListSize(Integer.valueOf(sizeGroupInput.getText()));
                    groups.add(newGroup);
                    groupNameInput.setText("");
                    sizeGroupInput.setText("");
                    groupTableModel.fireTableDataChanged();
                    if(groupTable.getSelectedRow() != -1)
                    {
                        groupTable.clearSelection();
                    }
                }

            }
        });



        groupTable.getSelectionModel().addListSelectionListener(e -> {
            fillGroupForm();
        });
        deleteGroup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        editGroup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        addStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //if(firstNameInput.getText().equals("") || lastNameInput.getText().equals("") || studentConditioInput.getToolTipText().equals("") || scoresInput.getText().equals(""))
                if(firstNameInput.getText().equals("") || lastNameInput.getText().equals("") || yearOfBirthInput.getText().equals("") || scoresInput.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(frame, "All fields must be filled");
                }
                else
                {
                    Student newStudent = new Student();
                    newStudent.setFirstName(firstNameInput.getText());
                    newStudent.setLastName(lastNameInput.getText());
                    newStudent.setStatus(StudentCondition.DOING);
//                    switch(studentConditioInput.getToolTipText()) {
//                        case "DOING":
//                            newStudent.setStatus(StudentCondition.DOING);
//                            break;
//                        case "ILL":
//                            newStudent.setStatus(StudentCondition.ILL);
//                            break;
//                        case "ABSENT":
//                            newStudent.setStatus(StudentCondition.ABSENT);
//                            break;
//                    }
                    newStudent.setDateOfBirth(Integer.valueOf(yearOfBirthInput.getText()));
                    newStudent.setScores(Double.valueOf(scoresInput.getText()));
                    modelData.addStudentToGroup(newStudent);
                    firstNameInput.setText("");
                    lastNameInput.setText("");
                    scoresInput.setText("");
                    yearOfBirthInput.setText("");


                    studentTableModel.fireTableDataChanged();
                }
            }
        });
        editStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        deleteStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }


    public void fillGroupForm()
    {
        groupNameInput.setText(groupTable.getValueAt(groupTable.getSelectedRow(), 0).toString());
        sizeGroupInput.setText(groupTable.getValueAt(groupTable.getSelectedRow(), 1).toString());

    }

    public void setData(Class data) {
        groupNameInput.setText(data.getNameGroup());
        sizeGroupInput.setText(String.valueOf(data.getMaxListSize()));
    }

    public void getData(Class data) {
        data.setNameGroup(groupNameInput.getText());
    }

    public boolean isModified(Class data) {
        if (groupNameInput.getText() != null ? !groupNameInput.getText().equals(data.getNameGroup()) : data.getNameGroup() != null)
            return true;
        return false;
    }

    public static void main(String[] args)
    {

        GroupWindow window = new GroupWindow("Student's Group");
        window.setVisible(true);
    }
}
