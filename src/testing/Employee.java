package testing;

public class Employee implements Comparable<Employee> {
    private final Integer id;
    private String name;
    private int salary;

    public Employee(int id, String name, int salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public int compareTo(Employee employee) {
        return id.compareTo(employee.id);
    }
}
