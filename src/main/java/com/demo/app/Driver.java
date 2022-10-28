package com.demo.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Driver {

	public static void main(String[] args) {

		Connection connection = null;
		try {
			connection = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/ford_schema?" + "user=ford&password=ford");
			
			System.out.println("Successful Connection to Mysql ");
			
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM employee");
			
			while (resultSet.next()) {
				Integer id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				Float salary = resultSet.getFloat("salary");
				System.out.println(id + " " + name + " " + salary);

			}
			// get emp by id
			EmployeeDao empDao = new EmployeeDao();
			empDao.getEmployeeByID(1);
			empDao.getEmployeeByID(2);
			empDao.getEmployeeByID(3);
			
			//add employee
			//empDao.addEmployee(new Employee(3, "name 3", 3000.0));
			empDao.getEmployeeByID(3);
			//empDao.addEmployee(new Employee(4, "name 4", 4000.0));

			// delete emp by id
			empDao.deleteEmployeeById(4);
			empDao.getEmployeeByID(4);
			
			// Update emp
			empDao.updateEmployee(new Employee(3, "new name 3", 3500.0));
			empDao.getEmployeeByID(3);
			
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());

		}

	}

}
