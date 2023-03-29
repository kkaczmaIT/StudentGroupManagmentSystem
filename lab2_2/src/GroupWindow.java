import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
    private JComboBox studentConditionInput;
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
    private JTextField _filter_textbox_;
    private JLabel searchInputLabel;
    private JFrame frame = new JFrame();
    private Class selectedGroup;
    private int groupSelectedIndex;
    private Student selectedStudent;
    private int studentSelectedIndex;
    private GroupTableModel groupTableModel;
    private StudentTableModel studentTableModel;
    private ModelData modelData;
    private TableRowSorter<GroupTableModel> groupSorter;
    private TableRowSorter<StudentTableModel> studentSorter;
    public GroupWindow(String title)
    {
        super(title);
        groupSelectedIndex = -1;
        studentSelectedIndex = -1;
        modelData = new ModelData();
        modelData.init();
        List<Class> groups = modelData.getGroups();
        //List<Student> studentsInGroup = modelData.getStudentsInGroup();
        groupTableModel = new GroupTableModel(modelData.getGroupColumns(), groups);
        studentTableModel = new StudentTableModel(modelData.getStudentColumns(), modelData.getStudentsInGroup());//studentsInGroup);
        groupTable.setModel(groupTableModel);
        studentTable.setModel(studentTableModel);
        groupSorter = new TableRowSorter<GroupTableModel>(groupTableModel);
        groupTable.setRowSorter(groupSorter);

        studentConditionInput.setSelectedIndex(1);
        //studentConditioInput.addActionListener(this);
        //studentConditioInput.setModel(new DefaultComboBoxModel<>(StudentCondition.values()));




        this.setContentPane(container);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        _filter_textbox_.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    nameFilter();
                }
            }
        });
        addGroup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(selectedGroup != null && selectedGroup.getNameGroup().equals(groupNameInput.getText()))
                {
                    JOptionPane.showMessageDialog(frame, "Group exists");
                }
                else
                {
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
                        modelData.addGroup(newGroup);
                        groupTableModel.fireTableDataChanged();
                    }
                }


            }
        });
        deleteGroup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(groupSelectedIndex == -1)
                {
                    JOptionPane.showMessageDialog(frame, "Group must be selected");
                }
                else if(groupSelectedIndex != -1)
                {
                    Class group = groups.get(groupSelectedIndex);

                    modelData.clearStudentsListInGroup();
                    refreshStudentTable();
                    groups.remove(groupSelectedIndex);
                    modelData.removeGroup(group.getNameGroup());
                    groupTableModel.fireTableDataChanged();
                    groupNameInput.setText("");
                    sizeGroupInput.setText("");
                    groupSelectedIndex = -1;
                }
            }
        });
        editGroup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(groupNameInput.getText().equals("") || sizeGroupInput.getText().equals(""))
                    JOptionPane.showMessageDialog(frame, "All fields must be filled");
                else
                {
                    selectedGroup.setNameGroup(groupNameInput.getText());
                    selectedGroup.setMaxListSize(Integer.valueOf(sizeGroupInput.getText()));
                    groupNameInput.setText("");
                    sizeGroupInput.setText("");

                    groups.set(groupSelectedIndex, selectedGroup);
                    //refreshGroupTable(modelData, groups);
                    groupTableModel.fireTableDataChanged();
                    groupSelectedIndex = -1;
                }

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
                    if(selectedGroup != null)
                    {
                        if(selectedGroup.getStudentList().size() < selectedGroup.getMaxListSize())
                        {
                            Student newStudent = new Student();
                            newStudent.setFirstName(firstNameInput.getText());
                            newStudent.setLastName(lastNameInput.getText());
                            newStudent.setStatus(StudentCondition.DOING);
                    switch((String)studentConditionInput.getSelectedItem()) {
                        case "DOING":
                            newStudent.setStatus(StudentCondition.DOING);
                            break;
                        case "ILL":
                            newStudent.setStatus(StudentCondition.ILL);
                            break;
                        case "ABSENT":
                            newStudent.setStatus(StudentCondition.ABSENT);
                            break;
                    }
                            newStudent.setDateOfBirth(Integer.valueOf(yearOfBirthInput.getText()));
                            newStudent.setScores(Double.valueOf(scoresInput.getText()));
                            modelData.addStudentToGroup(newStudent);
                            firstNameInput.setText("");
                            lastNameInput.setText("");
                            scoresInput.setText("");
                            yearOfBirthInput.setText("");

                            studentTableModel.fireTableDataChanged();
                            groupTableModel.fireTableDataChanged();
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(frame, "Group is full");
                        }

                    }
                    else
                    {
                        JOptionPane.showMessageDialog(frame, "Group is not selected");
                    }

                }
            }
        });
        editStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(firstNameInput.getText().equals("") || lastNameInput.getText().equals("") || yearOfBirthInput.getText().equals("") || scoresInput.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(frame, "All fields must be filled");
                }
                else {
                    if (selectedGroup != null && selectedStudent != null) {
                        Student editStudent = new Student();
                        editStudent.setFirstName(firstNameInput.getText());
                        editStudent.setLastName(lastNameInput.getText());
                        editStudent.setStatus(StudentCondition.DOING);
                    switch((String)studentConditionInput.getSelectedItem()) {
                        case "DOING":
                            editStudent.setStatus(StudentCondition.DOING);
                            break;
                        case "ILL":
                            editStudent.setStatus(StudentCondition.ILL);
                            break;
                        case "ABSENT":
                            editStudent.setStatus(StudentCondition.ABSENT);
                            break;
                    }
                        editStudent.setDateOfBirth(Integer.valueOf(yearOfBirthInput.getText()));
                        editStudent.setScores(Double.valueOf(scoresInput.getText()));
                        modelData.updateStudent(studentSelectedIndex, editStudent);
                        firstNameInput.setText("");
                        lastNameInput.setText("");
                        scoresInput.setText("");
                        yearOfBirthInput.setText("");

                        studentTableModel.fireTableDataChanged();
                        refreshStudentTable();

                    } else {
                        JOptionPane.showMessageDialog(frame, "Group or student is not selected");
                    }
                }
            }
        });
        deleteStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(studentSelectedIndex == -1)
                {
                    JOptionPane.showMessageDialog(frame, "Student must be selected");
                }
                else if(studentSelectedIndex != -1)
                {
                    Student student = modelData.getStudentsInGroup().get(studentSelectedIndex);
                    modelData.getStudentsInGroup().remove(studentSelectedIndex);
                    refreshStudentTable();
                    groupTableModel.fireTableDataChanged();
                    firstNameInput.setText("");
                    lastNameInput.setText("");
                    scoresInput.setText("");
                    yearOfBirthInput.setText("");
                    studentSelectedIndex = -1;
                }
            }
        });

        groupTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!groupTable.getSelectionModel().isSelectionEmpty()) {
                    groupSelectedIndex = groupTable.convertRowIndexToModel(groupTable.getSelectedRow());
                    selectedGroup = groups.get(groupSelectedIndex);
                    modelData.setStudentsInGroup(selectedGroup.getStudentList());
                    refreshStudentTable();
                    if(selectedGroup != null) {
                        setDataGroup(selectedGroup);
                    }
                }
            }
        });

        studentTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!studentTable.getSelectionModel().isSelectionEmpty()) {
                    studentSelectedIndex = studentTable.convertRowIndexToModel(studentTable.getSelectedRow());
                    selectedStudent = modelData.getStudentsInGroup().get(studentSelectedIndex);
                    if(selectedStudent != null) {
                        setDataStudent(selectedStudent);
                    }
                }
            }
        });

    }

    //Search by name
    private void nameFilter() {
        RowFilter<StudentTableModel, Object> rowFilter = null;
        try {
            rowFilter = RowFilter.regexFilter(_filter_textbox_.getText(), 0);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        studentSorter.setRowFilter(rowFilter);
    }


    public void setDataGroup(Class data) {
        groupNameInput.setText(data.getNameGroup());
        sizeGroupInput.setText(String.valueOf(data.getMaxListSize()));
    }

    public void setDataStudent(Student data)
    {
        firstNameInput.setText(data.getFirstName());
        lastNameInput.setText(data.getLastName());
        scoresInput.setText(String.valueOf(data.getScores()));
        yearOfBirthInput.setText(String.valueOf(data.getDateOfBirth()));
    }

    public void refreshStudentTable()
    {
        studentTable.setRowSorter(null);
        if(!modelData.getStudentsInGroup().isEmpty())
        {
            studentTableModel = new StudentTableModel(modelData.getStudentColumns(), modelData.getStudentsInGroup());
            studentTable.setModel(studentTableModel);
            studentSorter = new TableRowSorter<StudentTableModel>(studentTableModel);
            studentTable.setRowSorter(studentSorter);
        }
        else
        {
            studentTableModel = new StudentTableModel(modelData.getStudentColumns(), modelData.getStudentsInGroup());
            studentTable.setModel(studentTableModel);
        }

    }

    public void refreshGroupTable(ModelData modelData, List<Class> groups)
    {
        GroupTableModel groupTableModel = new GroupTableModel(modelData.getGroupColumns(), groups);
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
