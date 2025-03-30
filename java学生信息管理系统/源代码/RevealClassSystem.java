public class RevealClassSystem {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var frame = new RevealClass();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
// 窗口界面
class RevealClass extends JFrame {
    private JPanel displayPanel;
    private static final int DEFAULT_WIDTH = 800;
    private static final int DEFAULT_HEIGHT = 500;
    
    public RevealClass() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        setLocation(screenWidth / 2 - DEFAULT_WIDTH / 2, screenHeight / 2 - DEFAULT_HEIGHT / 2);
        setTitle("学生管理系统");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        displayPanel = new DisplayPanel();
        add(displayPanel);
    }

    public void updateContent(JPanel newPanel) {
    	 if (displayPanel != null) {
    		 remove(displayPanel);
    	 }
    	displayPanel = newPanel;
        add(newPanel);
        revalidate();
        repaint();
    }
}
// 登录页面
class DisplayPanel extends JPanel implements ActionListener {
    private JButton okButton, exitButton;
    private JTextField textField;
    private JPasswordField passwordField;
    private HashMap<String, String> passBooks = new HashMap<>();

    public DisplayPanel() {
    	JPanel inputPanel = new JPanel(new GridLayout(4, 2));
    	inputPanel.add(new JLabel("请输入您的用户名:"));
    	textField = new JTextField(10);
    	inputPanel.add(textField);
    	inputPanel.add(new JLabel("请输入您的密码:"));
    	passwordField = new JPasswordField(10);
    	inputPanel.add(passwordField);
	    add(inputPanel);
	    inputPanel.add(new JLabel(" "));
	    inputPanel.add(new JLabel(" "));
	    okButton = new JButton("登录");
	    exitButton = new JButton("退出");
	    inputPanel.add(okButton);
	    inputPanel.add(exitButton);
	    okButton.addActionListener(this);
	    exitButton.addActionListener(this);
	    loadPasswordBook();
    	}
    private void loadPasswordBook() {
        try (BufferedReader reader = new BufferedReader(new FileReader("PassWordBook.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    passBooks.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "无法加载密码本：" + e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == okButton) {
            String userName = textField.getText();
            String password = new String(passwordField.getPassword());
            if (passBooks.containsKey(userName) && passBooks.get(userName).equals(password)) {
                JOptionPane.showMessageDialog(this, "恭喜你登录成功");
                ((RevealClass) SwingUtilities.getWindowAncestor(this)).updateContent(new StuPanel());// 这里可以切换到新的面板
            } else {
                JOptionPane.showMessageDialog(this, "用户名或密码错误，请重新输入");
                textField.setText(""); // 清空用户名
                passwordField.setText(""); // 清空密码
            }
        } else if (source == exitButton) {
            JOptionPane.showMessageDialog(this, "再见!\n欢迎您再次使用");
            System.exit(0);
        }
    }
}

// 学生信息管理界面
class StuPanel extends JPanel {
    public StuPanel() {
        // 初始化主界面的组件
        setPreferredSize(new Dimension(800, 500));
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        add(new DisplayPanel2());
    }
}
class DisplayPanel2 extends JPanel implements ActionListener {
    private JButton addButton, exchanButton, deleteButton, inquireButton,backButton;
    private JTextField textField;
    public DisplayPanel2() {
        JPanel inputPanel = new JPanel(new GridLayout(10,1));
        addButton = new JButton("添加学生信息");
        exchanButton = new JButton("修改学生信息");
        deleteButton = new JButton("删除学生信息");
        inquireButton = new JButton("查询学生信息");
        backButton = new JButton("返回");
        inputPanel.add(addButton);
        inputPanel.add(new JLabel(" "));
        inputPanel.add(exchanButton);
        inputPanel.add(new JLabel(" "));
        inputPanel.add(deleteButton);
        inputPanel.add(new JLabel(" "));
        inputPanel.add(inquireButton);
        inputPanel.add(new JLabel(" "));
        inputPanel.add(backButton);
        add(inputPanel);

        addButton.addActionListener(this);
        exchanButton.addActionListener(this);
        deleteButton.addActionListener(this);
        inquireButton.addActionListener(this);
        backButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == addButton) {	//添加  
            ((RevealClass) SwingUtilities.getWindowAncestor(this)).updateContent(new addStuPanel());// 这里可以切换到新的面板
        } else if (source == exchanButton) {		//修改
        	((RevealClass) SwingUtilities.getWindowAncestor(this)).updateContent(new excStuPanel());
        }else if (source == deleteButton) {	//删除
        	((RevealClass) SwingUtilities.getWindowAncestor(this)).updateContent(new deleteStuPanel());
		}else if (source == inquireButton) {	//查询
			((RevealClass) SwingUtilities.getWindowAncestor(this)).updateContent(new inqStuPanel());
		}else if (source == backButton) {	//返回
			((RevealClass) SwingUtilities.getWindowAncestor(this)).updateContent(new DisplayPanel());
		}
    }
}
// 添加学生信息页面
class addStuPanel extends JPanel {
    public addStuPanel() {
        // 初始化主界面的组件
        setPreferredSize(new Dimension(800, 500));
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        add(new addStd());
    }
}
class addStd extends JPanel implements ActionListener {
 private JButton okButton, exitButton;
 private JTextField IDField, nameField, sixField, ageField, telephoneField, gradeField, stdClassField;
 private HashMap<String, Student> stdInf = new HashMap<>();

 public addStd() {
     JPanel inputPanel = new JPanel(new GridLayout(10, 2));
     inputPanel.add(new JLabel("学生学号:"));
     IDField = new JTextField(10);
     inputPanel.add(IDField);
     inputPanel.add(new JLabel("学生姓名:"));
     nameField = new JTextField(10);
     inputPanel.add(nameField);
     inputPanel.add(new JLabel("学生性别:"));
     sixField = new JTextField(10);
     inputPanel.add(sixField);
     inputPanel.add(new JLabel("学生年龄:"));
     ageField = new JTextField(10);
     inputPanel.add(ageField);
     inputPanel.add(new JLabel("学生电话:"));
     telephoneField = new JTextField(10);
     inputPanel.add(telephoneField);
     inputPanel.add(new JLabel("学生年纪:"));
     gradeField = new JTextField(10);
     inputPanel.add(gradeField);
     inputPanel.add(new JLabel("学生班级:"));
     stdClassField = new JTextField(10);
     inputPanel.add(stdClassField);
     inputPanel.add(new JLabel(" "));
     inputPanel.add(new JLabel(" "));
     add(inputPanel);

     JPanel buttonPanel = new JPanel();
     okButton = new JButton("确认添加");
     exitButton = new JButton("返回");
     inputPanel.add(okButton);
     inputPanel.add(exitButton);

     okButton.addActionListener(this);
     exitButton.addActionListener(this);

     readMapToStd();
 }

 private void readMapToStd() {
	 try (BufferedReader reader = new BufferedReader(new FileReader("Student.txt"))) {
         String line;
         while ((line = reader.readLine()) != null) {
             String[] parts = line.split(",");
             if (parts.length >= 8) {
             	stdInf.put(parts[0], new Student(parts[1], parts[2], parts[3], Integer.valueOf(parts[4]), parts[5], parts[6], Integer.valueOf(parts[7])));
             }
         }
     } catch (IOException e) {
         e.printStackTrace();
     }
 }
 // 判断是否为空
 private boolean areAllFieldsFilled() {
	    return !IDField.getText().isEmpty()
	            && !nameField.getText().isEmpty()
	            && !sixField.getText().isEmpty()
	            && !ageField.getText().isEmpty()
	            && !telephoneField.getText().isEmpty()
	            && !gradeField.getText().isEmpty()
	            && !stdClassField.getText().isEmpty();
	}
 @Override
 public void actionPerformed(ActionEvent e) {
     Object source = e.getSource();
     if (source == okButton) {
    	 if(areAllFieldsFilled()){
    		 if (stdInf.containsKey(IDField.getText())) {
    			 JOptionPane.showMessageDialog(this, "该学生学号以存在，请您仔细检查");
			}else {
				stdInf.put(IDField.getText(), new Student(IDField.getText(), nameField.getText(), sixField.getText(), Integer.valueOf(ageField.getText()), telephoneField.getText(), gradeField.getText(), Integer.valueOf(stdClassField.getText())));
				writeMapToFile(stdInf, "Student.txt");
				JOptionPane.showMessageDialog(this, "添加成功");
				IDField.setText("");
				nameField.setText("");
				sixField.setText("");
				ageField.setText("");
				telephoneField.setText("");
				gradeField.setText("");
				stdClassField.setText("");
			}
    	 }else {
    		 JOptionPane.showMessageDialog(this, "请您填全信息");
		}
     } else if (source == exitButton) {
    	 ((RevealClass) SwingUtilities.getWindowAncestor(this)).updateContent(new StuPanel());
     }
 	}
 public <K, V>void writeMapToFile(HashMap<K, V> stdInf, String fileName) {
     try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
     	for (Map.Entry<K, V> entry : stdInf.entrySet()) {
             writer.write(entry.getKey() + "," +entry.getValue());
             writer.newLine();
         }
     } catch (IOException e) {
         e.printStackTrace();
     }
 }
}
//修改信息页面
class excStuPanel extends JPanel {
    public excStuPanel() {
        // 初始化主界面的组件
        setPreferredSize(new Dimension(800, 500));
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        add(new excStd());
    }
}
class excStd extends JPanel implements ActionListener {
 private JButton okButton, exitButton, excButton;
 private JTextField IDField, nameField, sixField, ageField, telephoneField, gradeField, stdClassField, judIDField;
 private HashMap<String, Student> stdInf = new HashMap<>();

 
 public excStd() {
     JPanel inputPanel = new JPanel(new GridLayout(11, 2));
     inputPanel.add(new JLabel("请输入学生学号:"));
     judIDField= new JTextField(10);
     inputPanel.add(judIDField);
     inputPanel.add(new JLabel(" "));
     excButton = new JButton("查询");
     inputPanel.add(excButton);
     inputPanel.add(new JLabel("学生学号:"));
     IDField = new JTextField(10);
     inputPanel.add(IDField);
     inputPanel.add(new JLabel("学生姓名:"));
     nameField = new JTextField(10);
     inputPanel.add(nameField);
     inputPanel.add(new JLabel("学生性别:"));
     sixField = new JTextField(10);
     inputPanel.add(sixField);
     inputPanel.add(new JLabel("学生年龄:"));
     ageField = new JTextField(10);
     inputPanel.add(ageField);
     inputPanel.add(new JLabel("学生电话:"));
     telephoneField = new JTextField(10);
     inputPanel.add(telephoneField);
     inputPanel.add(new JLabel("学生年纪:"));
     gradeField = new JTextField(10);
     inputPanel.add(gradeField);
     inputPanel.add(new JLabel("学生班级:"));
     stdClassField = new JTextField(10);
     inputPanel.add(stdClassField);
     inputPanel.add(new JLabel(" "));
     inputPanel.add(new JLabel(" "));
     add(inputPanel);


     okButton = new JButton("确认修改");
     exitButton = new JButton("返回");
     inputPanel.add(okButton);
     inputPanel.add(exitButton);

     okButton.addActionListener(this);
     exitButton.addActionListener(this);
     excButton.addActionListener(this);

     readMapToStd();
 }

 private void readMapToStd() {
	 try (BufferedReader reader = new BufferedReader(new FileReader("Student.txt"))) {
         String line;
         while ((line = reader.readLine()) != null) {
             String[] parts = line.split(",");
             if (parts.length >= 8) {
             	stdInf.put(parts[0], new Student(parts[1], parts[2], parts[3], Integer.valueOf(parts[4]), parts[5], parts[6], Integer.valueOf(parts[7])));
             }
         }
     } catch (IOException e) {
         e.printStackTrace();
     }
 }
 // 判断是否为空
 private boolean areAllFieldsFilled() {
	    return !IDField.getText().isEmpty()
	            && !nameField.getText().isEmpty()
	            && !sixField.getText().isEmpty()
	            && !ageField.getText().isEmpty()
	            && !telephoneField.getText().isEmpty()
	            && !gradeField.getText().isEmpty()
	            && !stdClassField.getText().isEmpty();
 }
 @Override
 public void actionPerformed(ActionEvent e) {
     Object source = e.getSource();
     if (source == excButton) {
    	 if(!judIDField.getText().isEmpty()){
    		 String key = judIDField.getText();
    		 if (stdInf.containsKey(key)) {
    			Student a = stdInf.get(key);
    			IDField.setText(a.getID());
 				nameField.setText(a.getName());
 				sixField.setText(a.getSix());
 				ageField.setText(String.valueOf(a.getAge()));
 				telephoneField.setText(a.getTelephone());
 				gradeField.setText(a.getGrade());
 				stdClassField.setText(String.valueOf(a.getStudentClass()));
			}else {
				JOptionPane.showMessageDialog(this, "该学号学生不存在");
				judIDField.setText("");
			}
    	 }else {
    		 JOptionPane.showMessageDialog(this, "请填学号");
		}
     } else if (source == exitButton) {
    	 ((RevealClass) SwingUtilities.getWindowAncestor(this)).updateContent(new StuPanel());
     }else if (source == okButton) {
		if(areAllFieldsFilled()&&!judIDField.getText().isEmpty()) {
			if (!judIDField.getText().equals(IDField.getText())) {
				stdInf.remove(judIDField.getText());
			}
			stdInf.put(IDField.getText(), new Student(IDField.getText(), nameField.getText(), sixField.getText(), Integer.valueOf(ageField.getText()), telephoneField.getText(), gradeField.getText(), Integer.valueOf(stdClassField.getText())));
			writeMapToFile(stdInf, "Student.txt");
			JOptionPane.showMessageDialog(this, "修改成功");
			judIDField.setText("");
			IDField.setText("");
			nameField.setText("");
			sixField.setText("");
			ageField.setText("");
			telephoneField.setText("");
			gradeField.setText("");
			stdClassField.setText("");
		}else {
			JOptionPane.showMessageDialog(this, "信息未填全");
		}
	}
 }
 public <K, V>void writeMapToFile(HashMap<K, V> stdInf, String fileName) {
     try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
     	for (Map.Entry<K, V> entry : stdInf.entrySet()) {
             writer.write(entry.getKey() + "," +entry.getValue());
             writer.newLine();
         }
     } catch (IOException e) {
         e.printStackTrace();
     }
 }
}
//删除学生信息
class deleteStuPanel extends JPanel {
    public deleteStuPanel() {
        // 初始化主界面的组件
        setPreferredSize(new Dimension(800, 500));
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        add(new deleStd());
    }
}
class deleStd extends JPanel implements ActionListener {
 private JButton okButton, exitButton, excButton;
 private JTextField IDField, nameField, sixField, ageField, telephoneField, gradeField, stdClassField, judIDField;
 private HashMap<String, Student> stdInf = new HashMap<>();

 
 public deleStd() {
     JPanel inputPanel = new JPanel(new GridLayout(6, 4));
     inputPanel.add(new JLabel("请输入学生学号:"));
     judIDField= new JTextField(10);
     inputPanel.add(judIDField);
     inputPanel.add(new JLabel(" "));
     excButton = new JButton("查询");
     inputPanel.add(excButton);
     inputPanel.add(new JLabel("学生学号:"));
     IDField = new JTextField(10);
     inputPanel.add(IDField);
     inputPanel.add(new JLabel("学生姓名:"));
     nameField = new JTextField(10);
     inputPanel.add(nameField);
     inputPanel.add(new JLabel("学生性别:"));
     sixField = new JTextField(10);
     inputPanel.add(sixField);
     inputPanel.add(new JLabel("学生年龄:"));
     ageField = new JTextField(10);
     inputPanel.add(ageField);
     inputPanel.add(new JLabel("学生电话:"));
     telephoneField = new JTextField(10);
     inputPanel.add(telephoneField);
     inputPanel.add(new JLabel("学生年纪:"));
     gradeField = new JTextField(10);
     inputPanel.add(gradeField);
     inputPanel.add(new JLabel("学生班级:"));
     stdClassField = new JTextField(10);
     inputPanel.add(stdClassField);
     inputPanel.add(new JLabel(" "));
     inputPanel.add(new JLabel(" "));
     add(inputPanel);


     okButton = new JButton("确认删除");
     exitButton = new JButton("返回");
     inputPanel.add(okButton);
     inputPanel.add(exitButton);

     okButton.addActionListener(this);
     exitButton.addActionListener(this);
     excButton.addActionListener(this);

     readMapToStd();
 }

 private void readMapToStd() {
	 try (BufferedReader reader = new BufferedReader(new FileReader("Student.txt"))) {
         String line;
         while ((line = reader.readLine()) != null) {
             String[] parts = line.split(",");
             if (parts.length >= 8) {
             	stdInf.put(parts[0], new Student(parts[1], parts[2], parts[3], Integer.valueOf(parts[4]), parts[5], parts[6], Integer.valueOf(parts[7])));
             }
         }
     } catch (IOException e) {
         e.printStackTrace();
     }
 }
 @Override
 public void actionPerformed(ActionEvent e) {
     Object source = e.getSource();
     if (source == excButton) {
    	 if(!judIDField.getText().isEmpty()){
    		 String key = judIDField.getText();
    		 if (stdInf.containsKey(key)) {
    			Student a = stdInf.get(key);
    			IDField.setText(a.getID());
 				nameField.setText(a.getName());
 				sixField.setText(a.getSix());
 				ageField.setText(String.valueOf(a.getAge()));
 				telephoneField.setText(a.getTelephone());
 				gradeField.setText(a.getGrade());
 				stdClassField.setText(String.valueOf(a.getStudentClass()));
			}else {
				JOptionPane.showMessageDialog(this, "该学号学生不存在");
				judIDField.setText("");
			}
    	 }else {
    		 JOptionPane.showMessageDialog(this, "请填学号");
		}
     } else if (source == exitButton) {
    	 ((RevealClass) SwingUtilities.getWindowAncestor(this)).updateContent(new StuPanel());
     }else if (source == okButton) {
			stdInf.remove(judIDField.getText());
			JOptionPane.showMessageDialog(this, "删除成功");
			judIDField.setText("");
			IDField.setText("");
			nameField.setText("");
			sixField.setText("");
			ageField.setText("");
			telephoneField.setText("");
			gradeField.setText("");
			stdClassField.setText("");
	}
 }
 public <K, V>void writeMapToFile(HashMap<K, V> stdInf, String fileName) {
     try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
     	for (Map.Entry<K, V> entry : stdInf.entrySet()) {
             writer.write(entry.getKey() + "," +entry.getValue());
             writer.newLine();
         }
     } catch (IOException e) {
         e.printStackTrace();
     }
 }
}
//查看全部学生信息inquire
class inqStuPanel extends JPanel {
    public inqStuPanel() {
        // 初始化主界面的组件
        setPreferredSize(new Dimension(800, 500));
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        add(new inqStd());
    }
}
class inqStd extends JPanel implements ActionListener {
 private JButton classButton, allButton, exsizButton;
 private HashMap<String, Student> stdInf = new HashMap<>();
 private DefaultListModel<Student> listModel;
 private JList<Student> studentList;

 
 public inqStd() {
     JPanel inputPanel = new JPanel(new GridLayout(1, 3));
     inputPanel.setBorder(new TitledBorder("查询选项"));
     classButton = new JButton("按年级查询");
     allButton = new JButton("全部查询");
     exsizButton = new JButton("返回");
     inputPanel.add(classButton);
     inputPanel.add(allButton);
     inputPanel.add(exsizButton);

     classButton.addActionListener(this);
     allButton.addActionListener(this);
     exsizButton.addActionListener(this);
     add(inputPanel, BorderLayout.NORTH);
     listModel = new DefaultListModel<>();
     studentList = new JList<>(listModel);
     studentList.setCellRenderer(new DefaultListCellRenderer() {
         @Override
         public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
             super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
             if (value instanceof Student) {
                 Student student = (Student) value;
                 setText(student.toStudent());
             }
             return this;
         }
     });
  // 创建滚动面板
     JScrollPane scrollPane = new JScrollPane(studentList);
     scrollPane.setPreferredSize(new Dimension(600, 200));
     add(scrollPane, BorderLayout.CENTER);
     readMapToStd();
 }


 @Override
 public void actionPerformed(ActionEvent e) {
     Object source = e.getSource();
     if (source == classButton) {
    	 listModel.clear();
         String grade = JOptionPane.showInputDialog(this, "请输入年级：");
         if (!grade.isEmpty()) {
             for (Student student : stdInf.values()) {
                 if (student.getGrade().equals(grade)) {
                     listModel.addElement(student);
                 }
             }
         }else {
        	 JOptionPane.showMessageDialog(this, "输入空了哦~");
		}
     } else if (source == exsizButton) {
    	 ((RevealClass) SwingUtilities.getWindowAncestor(this)).updateContent(new StuPanel());
     }else if (source == allButton) {
    	 listModel.clear();
         listModel.addAll(stdInf.values());
	}
 }
 private void readMapToStd() {
	 try (BufferedReader reader = new BufferedReader(new FileReader("Student.txt"))) {
         String line;
         while ((line = reader.readLine()) != null) {
             String[] parts = line.split(",");
             if (parts.length >= 8) {
             	stdInf.put(parts[0], new Student(parts[1], parts[2], parts[3], Integer.valueOf(parts[4]), parts[5], parts[6], Integer.valueOf(parts[7])));
             }
         }
     } catch (IOException e) {
         e.printStackTrace();
     }
 }
 public <K, V>void writeMapToFile(HashMap<K, V> stdInf, String fileName) {
     try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
     	for (Map.Entry<K, V> entry : stdInf.entrySet()) {
             writer.write(entry.getKey() + "," +entry.getValue());
             writer.newLine();
         }
     } catch (IOException e) {
         e.printStackTrace();
     }
 }
}