package JDBC;
import java.sql.*;
import java.util.Scanner;

public class EmployeeManagementSystem {

    
    static final String URL = "jdbc:mysql://localhost:3306/employee_management";
    static final String USER = "root";  
    static final String PASSWORD = "Aditya007!";  
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        
        
        do {
            System.out.println("Employee Management System");
            System.out.println("1. Add Employee");
            System.out.println("2. Add Multiple Employees");
            System.out.println("3. Update Employee Details");
            System.out.println("4. Delete Employee");
            System.out.println("5. Display Employee by ID");
            System.out.println("6. Display Employees by Department");
            System.out.println("7. Display Employees by Gender");
            System.out.println("8. Display Employees by Year of Birth");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    addMultipleEmployees();
                    break;
                case 3:
                    updateEmployee();
                    break;
                case 4:
                    deleteEmployee();
                    break;
                case 5:
                    displayEmployeeById();
                    break;
                case 6:
                    displayEmployeesByDept();
                    break;
                case 7:
                    displayEmployeesByGender();
                    break;
                case 8:
                    displayEmployeesByYear();
                    break;
                case 9:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 9);
        
        scanner.close();
    }
    
    
    public static void addEmployee() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Employee ID: ");
        int eno = scanner.nextInt();
        scanner.nextLine();  
        System.out.println("Enter Employee Name: ");
        String ename = scanner.nextLine();
        System.out.println("Enter Employee Salary: ");
        double salary = scanner.nextDouble();
        scanner.nextLine();  
        System.out.println("Enter Employee Department: ");
        String dept = scanner.nextLine();
        System.out.println("Enter Employee Gender (M/F): ");
        String gender = scanner.nextLine();
        System.out.println("Enter Employee Date of Birth (yyyy-mm-dd): ");
        String dob = scanner.nextLine();
        
        String sql = "INSERT INTO employeee (eno, ename, salary, dept, gender, dob) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, eno);
            statement.setString(2, ename);
            statement.setDouble(3, salary);
            statement.setString(4, dept);
            statement.setString(5, gender);
            statement.setDate(6, Date.valueOf(dob));
            
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Employee added successfully!");
            } else {
                System.out.println("Failed to add employee.");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public static void addMultipleEmployees() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of employees to add: ");
        int numEmployees = scanner.nextInt();
        
        String sql = "INSERT INTO employeee (eno, ename, salary, dept, gender, dob) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            for (int i = 0; i < numEmployees; i++) {
                System.out.println("Enter details for Employee " + (i + 1));
                System.out.print("Enter Employee ID: ");
                int eno = scanner.nextInt();
                scanner.nextLine();  
                System.out.print("Enter Employee Name: ");
                String ename = scanner.nextLine();
                System.out.print("Enter Employee Salary: ");
                double salary = scanner.nextDouble();
                scanner.nextLine();  
                System.out.print("Enter Employee Department: ");
                String dept = scanner.nextLine();
                System.out.print("Enter Employee Gender (M/F): ");
                String gender = scanner.nextLine();
                System.out.print("Enter Employee Date of Birth (yyyy-mm-dd): ");
                String dob = scanner.nextLine();
                
                statement.setInt(1, eno);
                statement.setString(2, ename);
                statement.setDouble(3, salary);
                statement.setString(4, dept);
                statement.setString(5, gender);
                statement.setDate(6, Date.valueOf(dob));
                
                statement.addBatch();  
            }
            
            int[] result = statement.executeBatch();  
            System.out.println("Batch update executed, " + result.length + " employees added.");
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public static void updateEmployee() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Employee ID to update: ");
        int eno = scanner.nextInt();
        scanner.nextLine();  
        System.out.print("Enter new name: ");
        String ename = scanner.nextLine();
        System.out.print("Enter new salary: ");
        double salary = scanner.nextDouble();
        scanner.nextLine();  
        System.out.print("Enter new department: ");
        String dept = scanner.nextLine();
        
        String sql = "UPDATE employeee SET ename = ?, salary = ?, dept = ? WHERE eno = ?";
        
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setString(1, ename);
            statement.setDouble(2, salary);
            statement.setString(3, dept);
            statement.setInt(4, eno);
            
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Employee details updated successfully!");
            } else {
                System.out.println("Employee not found.");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public static void deleteEmployee() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Employee ID to delete: ");
        int eno = scanner.nextInt();
        
        String sql = "DELETE FROM employeee WHERE eno = ?";
        
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, eno);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Employee deleted successfully!");
            } else {
                System.out.println("Employee not found.");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public static void displayEmployeeById() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Employee ID to display: ");
        int eno = scanner.nextInt();
        
        String sql = "SELECT * FROM employeee WHERE eno = ?";
        
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, eno);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                System.out.println("Employee ID: " + resultSet.getInt("eno"));
                System.out.println("Name: " + resultSet.getString("ename"));
                System.out.println("Salary: " + resultSet.getDouble("salary"));
                System.out.println("Department: " + resultSet.getString("dept"));
                System.out.println("Gender: " + resultSet.getString("gender"));
                System.out.println("Date of Birth: " + resultSet.getDate("dob"));
            } else {
                System.out.println("Employee not found.");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public static void displayEmployeesByDept() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Department to display employees: ");
        String dept = scanner.nextLine();
        
        String sql = "SELECT * FROM employeee WHERE dept = ?";
        
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setString(1, dept);
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                System.out.println("Employee ID: " + resultSet.getInt("eno"));
                System.out.println("Name: " + resultSet.getString("ename"));
                System.out.println("Salary: " + resultSet.getDouble("salary"));
                System.out.println("Department: " + resultSet.getString("dept"));
                System.out.println("Gender: " + resultSet.getString("gender"));
                System.out.println("Date of Birth: " + resultSet.getDate("dob"));
                System.out.println("------------------------------");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public static void displayEmployeesByGender() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Gender (M/F) to display employees: ");
        String gender = scanner.nextLine();
        
        String sql = "SELECT * FROM employeee WHERE gender = ?";
        
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setString(1, gender);
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                System.out.println("Employee ID: " + resultSet.getInt("eno"));
                System.out.println("Name: " + resultSet.getString("ename"));
                System.out.println("Salary: " + resultSet.getDouble("salary"));
                System.out.println("Department: " + resultSet.getString("dept"));
                System.out.println("Gender: " + resultSet.getString("gender"));
                System.out.println("Date of Birth: " + resultSet.getDate("dob"));
                System.out.println("------------------------------");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public static void displayEmployeesByYear() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Year to display employees born after that year: ");
        int year = scanner.nextInt();
        
        String sql = "SELECT * FROM employeee WHERE YEAR(dob) > ?";
        
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, year);
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                System.out.println("Employee ID: " + resultSet.getInt("eno"));
                System.out.println("Name: " + resultSet.getString("ename"));
                System.out.println("Salary: " + resultSet.getDouble("salary"));
                System.out.println("Department: " + resultSet.getString("dept"));
                System.out.println("Gender: " + resultSet.getString("gender"));
                System.out.println("Date of Birth: " + resultSet.getDate("dob"));
                System.out.println("------------------------------");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
