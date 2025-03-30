public class Student extends person{
	private String ID;	//学号
	private String grade;	//年级
	private int studentClass;	//班级
	public Student() {
		this("", "", "", 0, "", "", 0);
	}
	public Student(String ID, String name, String six, int age, String telephone,String grade, int studentClass) {
		super(name,six,age,telephone);
		this.ID = ID;
		this.grade = grade;
		this.studentClass = studentClass;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		this.ID = iD;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public int getStudentClass() {
		return studentClass;
	}
	public void setStudentClass(int studentClass) {
		this.studentClass = studentClass;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		super.name = name;
	}
	public String getSix() {
		return super.six;
	}
	public void setSix(String six) {
		super.six = six;
	}
	public int getAge() {
		return super.age;
	}
	public void setAge(int age) {
		super.age = age;
	}
	public String getTelephone() {
		return super.telephone;
	}
	public void setTelephone(String telephone) {
		super.telephone = telephone;
	}
	@Override
//	public String toString() {
//		return "Student[ID="+this.ID+",name="+super.name+",six="+super.six+",age="+super.age+",telephone="+super.telephone+",grade="+this.grade+",class="+this.studentClass+"]";
//	}
	public String toString() {
		return this.ID+","+super.name+","+super.six+","+super.age+","+super.telephone+","+this.grade+","+this.studentClass;
	}
	public String toStudent() {
		return "学号："+this.ID+"-姓名："+super.name+"-性别："+super.six+"-年龄"+super.age+"-联系电话"+super.telephone+"-年纪"+this.grade+"-班级"+this.studentClass;
	}
	
}

class person{
	String name;
	String six;
	int age;
	String telephone;
	public person() {
		this("", "", 0, "");
	}
	public person(String name, String six, int age, String telephone) {
		super();
		this.name = name;
		this.six = six;
		this.age = age;
		this.telephone = telephone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSix() {
		return six;
	}
	public void setSix(String six) {
		this.six = six;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	
}