package com.demo.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeeDao {

	Connection connection = null;

	public EmployeeDao() {
		super();
		try {
			connection = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/ford_schema?" + "user=ford&password=ford");

			System.out.println("Successful Connection to Mysql ");
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());

		}
	}

	public EmployeeDao(Connection connection) {
		super();
		this.connection = connection;
	}

	// fetch emp by id and display
	void getEmployeeByID(Integer id) {
		try {
			Statement statement = connection.createStatement();

			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employee WHERE id = ?");
			preparedStatement.setInt(1, id);
			// System.out.println(preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				System.out.println("id:" + resultSet.getInt(1) + " name:" + resultSet.getString(2) + " salary:"
						+ resultSet.getFloat(3));
			} else {
				System.out.println("Record does not exists for id :" + id);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// fetch emp by id and display
	void addEmployee(Employee employee) {
		try {
			Statement statement = connection.createStatement();

			PreparedStatement preparedStatement = connection
					.prepareStatement("INSERT INTO employee(id,name,salary ) VALUES(?,?,?)");
			preparedStatement.setInt(1, employee.getId());
			preparedStatement.setString(2, employee.getName());
			preparedStatement.setDouble(3, employee.getSalary());

			// System.out.println(preparedStatement);
			Integer count = preparedStatement.executeUpdate();
			if (count == 1) {
				System.out.println("Employee inserted successfully.");
			} else {
				System.out.println("Could not insert Employee");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	void deleteEmployeeById(Integer deleteId) {
		try {
			Statement statement = connection.createStatement();

			PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM employee WHERE id = ?");
			preparedStatement.setInt(1, deleteId);

			// System.out.println(preparedStatement);
			Integer count = preparedStatement.executeUpdate();
			if (count == 1) {
				System.out.println("Employee Deleted successfully.");
			} else {
				System.out.println("Could not Delete Employee");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void updateEmployee(Employee updateEmployee) {
		try {
			Statement statement = connection.createStatement();

			PreparedStatement preparedStatement = connection
					.prepareStatement("UPDATE employee SET name = ? , salary = ? WHERE id =?");
			preparedStatement.setString(1, updateEmployee.getName());
			preparedStatement.setDouble(2, updateEmployee.getSalary());
			preparedStatement.setInt(3, updateEmployee.getId());
			

			// System.out.println(preparedStatement);
			Integer count = preparedStatement.executeUpdate();
			if (count == 1) {
				System.out.println("Employee Updated successfully.");
			} else {
				System.out.println("Could not Update Employee");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
