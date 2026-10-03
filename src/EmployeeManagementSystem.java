import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class EmployeeManagementSystem {

    private static final Map<Integer, Employee> employees = new HashMap<>();

    private static final Scanner scanner = new Scanner(System.in);


    // FR1: EMPLOYEE MANAGEMENT


    public static void addEmployee() {

        try {

            // Employee ID
            System.out.print("Enter Employee ID: ");
            String idInput = scanner.nextLine();

            if (idInput.trim().isEmpty()) {
                System.out.println(
                        "Employee registration failed: ID cannot be empty."
                );
                return;
            }

            int id;

            try {
                id = Integer.parseInt(idInput);
            } catch (NumberFormatException e) {
                System.out.println(
                        "Employee registration failed: Please enter a valid numeric ID."
                );
                return;
            }

            // ID must be positive
            if (id <= 0) {
                System.out.println(
                        "Employee registration failed: ID must be positive."
                );
                return;
            }

            // Duplicate ID
            if (employees.containsKey(id)) {
                System.out.println(
                        "Employee registration failed: ID already exists."
                );
                return;
            }

            // Employee Name
            System.out.print("Enter Employee Name: ");
            String name = scanner.nextLine();

            if (name.trim().isEmpty()) {
                System.out.println(
                        "Employee registration failed: Name cannot be empty."
                );
                return;
            }

            // Name should contain letters and spaces
            if (!name.matches("[a-zA-Z ]+")) {
                System.out.println(
                        "Employee registration failed: Name must contain letters only."
                );
                return;
            }

            // Department
            System.out.print("Enter Department: ");
            String department = scanner.nextLine();

            if (department.trim().isEmpty()) {
                System.out.println(
                        "Employee registration failed: Department cannot be empty."
                );
                return;
            }

            // Base Salary
            System.out.print("Enter Base Salary: ");
            String salaryInput = scanner.nextLine();

            double baseSalary;

            try {
                baseSalary = Double.parseDouble(salaryInput);
            } catch (NumberFormatException e) {
                System.out.println(
                        "Employee registration failed: Please enter a valid basesalary."
                );
                return;
            }

            // Salary validation
            if (baseSalary < 0) {
                System.out.println(
                        "Employee registration failed: baseSalary cannot be negative."
                );
                return;
            }

            // Create employee
            Employee employee =
                    new Employee(id, name, department, baseSalary);

            employees.put(id, employee);

            System.out.println(
                    "Employee registered successfully."
            );

        } catch (Exception e) {

            System.out.println(
                    "Employee registration failed: Unexpected error occurred."
            );
        }
    }



    // FR1: REMOVE EMPLOYEE

    public static void removeEmployee() {

        try {

            System.out.print("Enter Employee ID to remove: ");

            String idInput = scanner.nextLine();

            if (idInput.trim().isEmpty()) {
                System.out.println(
                        "Employee removal failed: ID cannot be empty."
                );
                return;
            }

            int id;

            try {
                id = Integer.parseInt(idInput);
            } catch (NumberFormatException e) {

                System.out.println(
                        "Employee removal failed: Please enter a valid numeric ID."
                );
                return;
            }

            if (employees.containsKey(id)) {

                employees.remove(id);

                System.out.println(
                        "Employee removed successfully."
                );

            } else {

                System.out.println(
                        "Employee removal failed: Employee ID does not exist."
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "Employee removal failed: Unexpected error occurred."
            );
        }
    }



    // FR2: PROCESS PAYMENT


    public static void processPayment() {

        try {

            System.out.print("Enter Employee ID: ");

            String idInput = scanner.nextLine();

            if (idInput.trim().isEmpty()) {

                System.out.println(
                        "Payment processing failed: ID cannot be empty."
                );
                return;
            }

            int id;

            try {

                id = Integer.parseInt(idInput);

            } catch (NumberFormatException e) {

                System.out.println(
                        "Payment processing failed: Please enter a valid numeric ID."
                );
                return;
            }

            // Check employee
            if (!employees.containsKey(id)) {

                System.out.println(
                        "Payment processing failed: Employee ID does not exist."
                );
                return;
            }

            Employee employee = employees.get(id);

            // Hours worked
            System.out.print("Enter Hours Worked: ");

            String hoursInput = scanner.nextLine();

            double hours;

            try {

                hours = Double.parseDouble(hoursInput);

            } catch (NumberFormatException e) {

                System.out.println(
                        "Payment processing failed: Please enter valid hours."
                );
                return;
            }

            if (hours < 0) {

                System.out.println(
                        "Payment processing failed: Hours cannot be negative."
                );
                return;
            }

            // Overtime
            System.out.print("Enter Overtime Hours: ");

            String overtimeInput = scanner.nextLine();

            double overtimeHours;

            try {

                overtimeHours =
                        Double.parseDouble(overtimeInput);

            } catch (NumberFormatException e) {

                System.out.println(
                        "Payment processing failed: Please enter valid overtime hours."
                );
                return;
            }

            if (overtimeHours < 0) {

                System.out.println(
                        "Payment processing failed: Overtime hours cannot be negative."
                );
                return;
            }

            // PAYMENT CALCULATION


            double baseSalary = employee.getBasesalary();

            // Standard working hours = 160
            double hourlyRate = baseSalary / 160;

            // Overtime is paid at 1.5 times hourly rate
            double overtimePay =
                    overtimeHours * hourlyRate * 1.5;

            // Gross pay before bonus
            double grossPay =
                    baseSalary + overtimePay;

            // Management bonus
            double bonus = 0;

            if (employee.getDepartment().equalsIgnoreCase("Management")) {

                bonus = grossPay * 0.10;
            }

            grossPay = grossPay + bonus;

            // Tax
            double tax;

            if (grossPay >= 5000) {

                tax = grossPay * 0.25;

            } else {

                tax = grossPay * 0.15;
            }

            // Net payment
            double netPay =
                    grossPay - tax;

            // DISPLAY RESULT


            System.out.println();
            System.out.println("========== PAYMENT RESULT ==========");
            System.out.println("Employee ID: " + employee.getId());
            System.out.println("Employee Name: " + employee.getName());
            System.out.println("Department: " + employee.getDepartment());
            System.out.println("Base Salary: " + baseSalary);
            System.out.println("Overtime Pay: " + overtimePay);
            System.out.println("Bonus: " + bonus);
            System.out.println("Gross Pay: " + grossPay);
            System.out.println("Tax: " + tax);
            System.out.println("Net Payment: " + netPay);
            System.out.println("Payment processed successfully.");
            System.out.println("====================================");

        } catch (Exception e) {

            System.out.println(
                    "Payment processing failed: Unexpected error occurred."
            );
        }
    }



    // FR3: PAYROLL REPORT


    public static void generateReport() {

        try {

            if (employees.isEmpty()) {

                System.out.println(
                        "Payroll report failed: No employees to report."
                );
                return;
            }

            System.out.println();
            System.out.println("========== EMPLOYEE REPORT ==========");

            double totalPayroll = 0;
            double highestPayment = 0;
            String topEarner = "";

            for (Employee employee : employees.values()) {

                double baseSalary =
                        employee.getBasesalary();

                totalPayroll += baseSalary;

                if (baseSalary > highestPayment) {

                    highestPayment = baseSalary;

                    topEarner = employee.getName();
                }

                System.out.println(
                        "ID: " + employee.getId()
                                + " | Name: " + employee.getName()
                                + " | Department: " + employee.getDepartment()
                                + " | Salary: " + baseSalary
                );
            }

            double averageSalary =
                    totalPayroll / employees.size();

            System.out.println("-------------------------------------");
            System.out.println("Total Payroll: " + totalPayroll);
            System.out.println("Average Salary: " + averageSalary);
            System.out.println("Top Earner: " + topEarner);
            System.out.println("Highest Salary: " + highestPayment);
            System.out.println("=====================================");

        } catch (Exception e) {

            System.out.println(
                    "Payroll report failed: Unexpected error occurred."
            );
        }
    }



    // FR3: COUNT EMPLOYEES


    public static void employeeCount() {

        System.out.println(
                "Current number of employees: "
                        + employees.size()
        );
    }



    // MAIN MENU


    public static void main(String[] args) {

        while (true) {

            System.out.println();
            System.out.println("====================================");
            System.out.println("     EMPLOYEE MANAGEMENT SYSTEM");
            System.out.println("====================================");
            System.out.println("1. Add Employee");
            System.out.println("2. Remove Employee");
            System.out.println("3. Process Payment");
            System.out.println("4. Generate Payroll Report");
            System.out.println("5. Count Employees");
            System.out.println("6. Exit");
            System.out.println("====================================");

            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            try {

                switch (choice) {

                    case "1":
                        addEmployee();
                        break;

                    case "2":
                        removeEmployee();
                        break;

                    case "3":
                        processPayment();
                        break;

                    case "4":
                        generateReport();
                        break;

                    case "5":
                        employeeCount();
                        break;

                    case "6":
                        System.out.println(
                                "Thank you for using the Employee Management System."
                        );
                        scanner.close();
                        return;

                    default:
                        System.out.println(
                                "Invalid choice. Please select a number from 1 to 6."
                        );
                }

            } catch (Exception e) {

                System.out.println(
                        "System error: Please try again."
                );
            }
        }
    }
}