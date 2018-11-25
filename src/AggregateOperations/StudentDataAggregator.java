package AggregateOperations;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StudentDataAggregator {
    public static void main(String[] args) {
        Student galina = new Student("Galina", 95, "Philology", Student.Gender.FEMALE);
        Student anton = new Student("Anton", 90, "CS", Student.Gender.MALE);
        Student jack = new Student("Jack", 82, "Philology", Student.Gender.MALE);
        Student mike = new Student("Mike", 60, "Philology", Student.Gender.MALE);
        Student jane = new Student("Jane", 65, "CS", Student.Gender.FEMALE);

        Student[] stds = new Student[]{galina, anton, jack, mike, jane};
        Stream<Student> students = Arrays.stream(stds);

        System.out.println(
                getTheNumberOfStudentsByGenderForEachDepartment(students)
        );
    }

    /**
     * SOLUTION PART 4
     * Some Fun with Aggregate Operations (PART 3)
     */
    public static Map<String, Map<Student.Gender, Long>> getTheNumberOfStudentsByGenderForEachDepartment(Stream<Student> students) {
        return students.collect(
                Collectors.groupingBy(Student::getDepartment,
                        Collectors.groupingBy(Student::getGender,
                                Collectors.counting())));
    }


    /**
     * SOLUTION PART 3
     * Some Fun with Aggregate Operations (PART 3)
     */
    public static Map<String, List<String>> getStudentNamesByDepartment(Stream<Student> students) {
        return students.collect(
                Collectors.groupingBy(Student::getDepartment,
                        Collectors.mapping(Student::getName, Collectors.toList())));
    }

    /**
     * SOLUTION PART 1
     * Some Fun with Aggregate Operations (PART 1)
     */
    public static Map<String, Double> getAverageGradeByDepartment(Stream<Student> students) {
        Set<Student> studentSet = students.collect(Collectors.toCollection(HashSet::new));
        Map<String, Double> acc = getDeptAccumulatedGrade(studentSet);
        Map<String, Long> counter = getDeptCountOfStudents(studentSet);

        Map<String, Double> result = acc.entrySet().stream().collect(Collectors.toMap(
                e -> e.getKey(),
                e -> e.getValue() / counter.get(e.getKey())
        ));
        return result;
//        Solution from codewars:
//        return students.collect(Collectors.groupingBy(AggregateOperations.Student::getDepartment, Collectors.averagingDouble(AggregateOperations.Student::getGrade)));
    }

    public static Map<String, Double> getDeptAccumulatedGrade(Set<Student> students) {
        return students.stream().collect(Collectors.toMap(
                Student::getDepartment,
                s -> s.getGrade(),
                (oldValue, newValue) -> (oldValue += newValue),
                HashMap::new
        ));
    }

    /**
     * SOLUTION PART 2
     * Some Fun with Aggregate Operations (PART 2)
     */ // just use Stream not Set
    public static Map<String, Long> getDeptCountOfStudents(Set<Student> students) {
        return students.stream().collect(Collectors.toMap(
                Student::getDepartment,
                s -> 1L,
                (oldValue, newValue) -> oldValue += newValue,
                HashMap::new
        ));

//        Solution from code wars:
//        return s.collect(Collectors.groupingBy(AggregateOperations.Student::getDepartment, Collectors.counting()));
    }

}

/**
 * Java 8 has introduced a sexy new Stream API which makes it possible to solve some data related problems in just a few lines of code. Let's try it out!
 * <p>
 * You have a AggregateOperations.Student class (see the class declaration below).
 * <p>
 * You have an array of students and you want to get some aggregate data.
 * <p>
 * THE TASK: get the average grade for every department.
 * <p>
 * Implement the method using Java 8 Stream API.
 * <p>
 * Have Fun!
 * <p>
 * AggregateOperations.Student class declaration:
 */

class Student {
    private String name;
    private double grade;
    private String department;
    private Gender gender;

    public static final double PASSING_GRADE = 70.0;

    public enum Gender {
        MALE, FEMALE
    }

    public Student(String name, double grade, String department, Gender gender) {
        this.name = name;
        this.grade = grade;
        this.department = department;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "AggregateOperations.Student{" +
                "name='" + name + '\'' +
                ", grade=" + grade +
                ", department='" + department + '\'' +
                ", gender=" + gender +
                '}';
    }
}