package net.codejava.aws;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertDB {

	
	private static final String INSERT_FILES_SQL = "INSERT INTO files" + "  (name, description,email1, email2,email3,email4,email5) VALUES "
			+ " (?, ?, ?,?,?,?,?);";

	public InsertDB() {
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/s3files?useSSL=false","root","admin");
//			connection = DriverManager.getConnection("jdbc:mysql://database-1.cklaa9bgl9hu.us-east-2.rds.amazonaws.com:3306/employee?useSSL=false","admin","Admin123");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public void insertFileInfo(File file) throws SQLException {
		System.out.println(INSERT_FILES_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_FILES_SQL)) {
			preparedStatement.setString(1, file.getName());
			preparedStatement.setString(2, file.getDescription());
			preparedStatement.setString(3, file.getEmail1());
			preparedStatement.setString(3, file.getEmail2());
			preparedStatement.setString(3, file.getEmail3());
			preparedStatement.setString(3, file.getEmail4());
			preparedStatement.setString(3, file.getEmail5());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	/**
	CREATE TABLE `s3files`.`files` (
			  `id` INT NOT NULL AUTO_INCREMENT,
			  `name` VARCHAR(45) NULL,
			  `description` VARCHAR(45) NULL,
			  `email1` VARCHAR(45) NULL,
			  `email2` VARCHAR(45) NULL,
			  `email3` VARCHAR(45) NULL,
			  `email4` VARCHAR(45) NULL,
			  `email5` VARCHAR(45) NULL,
			  PRIMARY KEY (`id`))
			COMMENT = '		';
**/

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}


}
