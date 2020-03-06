## BuilderFragment
<pre>
    @Override
    public void clickItem(int position) {
        switch (position) {
            case 0:
                Student student1 = new Student.StudentBuilder().setName("张三").setAge(18).setSex(true).build();
                showOutput(student1.toString());
                break;
            case 1:
                Student student2 = Student.newInstance().setName("李四").setAge(20).setSex(false).build();
                showOutput(student2.toString());
                break;
            case 2:
                Student student3 = new Student().setAge(22).setName("王五").setSex(true);
                showOutput(student3.toString());
                break;
        }
    }

    public class Student {

    private String name;
    private int age;
    private boolean sex;

    public Student() {
    }

    public Student(StudentBuilder studentBuilder) {
        this.name = studentBuilder.name;
        this.age = studentBuilder.age;
        this.sex = studentBuilder.sex;
    }

    public static StudentBuilder newInstance() {
        return new StudentBuilder();
    }

    public static class StudentBuilder {

        private String name;

        private int age;

        private boolean sex;

        public StudentBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public StudentBuilder setAge(int age) {
            this.age = age;
            return this;
        }

        public StudentBuilder setSex(boolean sex) {
            this.sex = sex;
            return this;
        }

        public Student build() {
            return new Student(this);
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }

    public Student setName(String name) {
        this.name = name;
        return this;
    }

    public Student setAge(int age) {
        this.age = age;
        return this;
    }

    public Student setSex(boolean sex) {
        this.sex = sex;
        return this;
    }
}
</pre>