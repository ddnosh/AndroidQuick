package com.androidwind.androidquick.demo.features.design_patterns.builder;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class Student {

    private String name;
    private int age;
    private boolean sex;

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
}
