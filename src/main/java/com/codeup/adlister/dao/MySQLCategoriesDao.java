//package com.codeup.adlister.dao;
//
//import com.codeup.adlister.Config;
//import com.codeup.adlister.models.Category;
//import com.mysql.cj.jdbc.Driver;
//
//import java.sql.*;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.util.List;
//
//public class MySQLCategoriesDao implements Categories {
//	private Connection connection = null;
//
//	public MySQLCategoriesDao(Config config) {
//		try {
//			DriverManager.registerDriver(new Driver());
//			connection = DriverManager.getConnection(
//					config.getUrl(),
//					config.getUser(),
//					config.getPassword()
//			);
//		} catch (SQLException e) {
//			throw new RuntimeException("Error connecting to the database!", e);
//		}
//	}

//	@Override
//	public List<Category> searchCategory(String searchTerm) {
//		PreparedStatement stmt = null;
//		try {
//			stmt = connection.prepareStatement("SELECT * FROM ads_ WHERE title LIKE ? OR description LIKE ? IN ");
//			stmt.setString(1, '%' + searchTerm + '%');
//			stmt.setString(2, '%' + searchTerm + '%');
//			ResultSet rs = stmt.executeQuery();
//			return createAdsFromResults(rs);
//		} catch (SQLException e) {
//			throw new RuntimeException("Error retrieving requested ads.", e);
//		}
//	}
//}
