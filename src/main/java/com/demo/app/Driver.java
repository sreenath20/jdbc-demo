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

		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());

		}

	}

}
