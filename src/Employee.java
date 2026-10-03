public class Employee {
    private  int Id;
    private String name;
    private String department;
    private double basesalary;
    //constructor

    public Employee(int id, String name, String department, double basesalary) {
        Id = id;
        this.name = name;
        this.department = department;
        this.basesalary = basesalary;
    }
    //getter abd setter

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public double  getBasesalary() {
        return basesalary;
    }

    public void setBasesalary(double basesalary) {
        this.basesalary = basesalary;
    }
    //toString

    @Override
    public String toString() {
        return "Employee{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", basesalary='" + basesalary + '\'' +
                '}';
    }
}
