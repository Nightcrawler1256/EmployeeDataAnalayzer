
import java.util.List;
public class EmployeeDataAnalyazer {
    public static void main(String[] args) {
        EmployeeManager employeeManager= new EmployeeManager();

        employeeManager.addEmployee(new Employee(1, " emp1 ", "Engineering",75000.0));
        employeeManager.addEmployee(new Employee(2, "emp2", "Marketing",
        55000.0));
        employeeManager.addEmployee(new Employee(3, "emp3", "Sales", 45000.0));
        employeeManager.addEmployee(new Employee(4, "emp4", "Engineering",80000.0));
        employeeManager.addEmployee(new Employee(4, "emp5", "HR", 60000.0));

        Thread processor1= new EmployeeProcessor(employeeManager,1);
        Thread processor2= new EmployeeProcessor(employeeManager,2);
        Thread processor3= new EmployeeProcessor(employeeManager,5);


        processor1.start();
        processor2.start();
        processor3.start();

        try {
            {
                processor1.join();
                processor2.join();
                processor3.join();
            }
        } catch (InterruptedException e) {
            System.out.println("Error waiting for thread completion: "+e.getMessage());
        }

        System.out.println("\nEmployee with salary >=70000");
        List<Employee> highEarners=employeeManager.filterEmployeesBySalary(70000.0);
        highEarners.forEach(System.out::println);

        System.out.println("\nEmployee sorted by salary:");
        List<Employee> sortedEmployees=employeeManager.sortEmployeeBySalary();

        sortedEmployees.forEach(System.out::println);

    }
}
