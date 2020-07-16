package util;

import java.sql.*;

public class ConnectionUtil {

		
		private static String DRIVER = "com.mysql.cj.jdbc.Driver";
		private static String URL = "jdbc:mysql://localhost:3306/test?characterEncoding=UTF-8&serverTimezone=UTC";
		private static String NAME = "root";
		private static String PASSWORD = "123456";
	 
	 
		public static Connection getConnection() {
			Connection connection = null;
			try {
				// ��������
				Class.forName(DRIVER);
				// �������ݿ�
				connection = DriverManager.getConnection(URL, NAME, PASSWORD);
				return connection;
			} catch (Exception e) {
				return null;
			}
		}
	 
	 
		// �ر���
		public static void closeConnection(Connection connection) {
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	 
	 
		public static void closeStatement(Statement statement) {
			try {
				statement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	 
	 
		public static void closePreparedStatement(PreparedStatement pStatement) {
			try {
				pStatement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	 
	 
		public static void closeResultSet(ResultSet rs) {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public static void main(String[] args) {
			System.out.println(getConnection());
		}
	 
	 
	}



