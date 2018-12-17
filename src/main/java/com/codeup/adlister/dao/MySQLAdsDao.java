package com.codeup.adlister.dao;

import com.codeup.adlister.Config;
import com.codeup.adlister.models.Ad;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLAdsDao implements Ads {
    private Connection connection = null;

    public MySQLAdsDao(Config config) {
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                config.getUrl(),
                config.getUser(),
                config.getPassword()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database!", e);
        }
    }

    @Override
    public List<Ad> all() {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM ads");
            ResultSet rs = stmt.executeQuery();
            return createAdsFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all ads.", e);
        }
    }

    @Override
    public List<Ad> getUsersAds(long id) {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM ads WHERE user_id = ?");
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            return createAdsFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving user\'s ads.", e);
        }
    }

    @Override
    public Long insert(Ad ad) {
        try {
            String insertQuery = "INSERT INTO ads(user_id, title, description) VALUES (?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, ad.getUserId());
            stmt.setString(2, ad.getTitle());
            stmt.setString(3, ad.getDescription());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating a new ad.", e);
        }
    }

    public Ad EditAd(Ad ad) {
        PreparedStatement stmt = null;
                try {
                    String editQuery = "UPDATE ads SET title = ?, description = ? WHERE id = ?";
                    stmt = connection.prepareStatement(editQuery);
                            stmt.setString(1, ad.getTitle());
                            stmt.setString(2, ad.getDescription());
                            stmt.setLong(3, ad.getId());

                            stmt.executeUpdate();
                            return ad;
                } catch(SQLException e) {
                    throw new RuntimeException("Error in editing ad.", e);
                }
    }

    private Ad extractAd(ResultSet rs) throws SQLException {
        return new Ad(
            rs.getLong("id"),
            rs.getLong("user_id"),
            rs.getString("title"),
            rs.getString("description")
        );
    }

    private List<Ad> createAdsFromResults(ResultSet rs) throws SQLException {
        List<Ad> ads = new ArrayList<>();
        while (rs.next()) {
            ads.add(extractAd(rs));
        }
        return ads;
    }

    @Override
    public List<Ad> searchAds(String searchTerm) {
        List<Ad> ads = new ArrayList<>();

        try {
            String insertQuery = "SELECT * FROM ads WHERE title LIKE ? OR description LIKE ?";
            PreparedStatement stmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, searchTerm);
            stmt.setString(2, searchTerm);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            ads.add(new Ad(
                    rs.getLong("id"),
                    rs.getLong("user_id"),
                    rs.getString("title"),
                    rs.getString("description")
            ));

        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving ads searched for.", e);
        }
        return ads;
    }



//    public Ad findAdd(String title){
//        PreparedStatement stmt = null;
//        try {
//            String prepStat = "SELECT * FROM ads WHERE title = ?";
//            stmt = connection.prepareStatement(prepStat, Statement.RETURN_GENERATED_KEYS);
//            stmt.setString(1, title);
//            ResultSet rs = stmt.executeQuery();
//            rs.next();
//            Ad ad = new Ad(rs.getLong("id"),
//                    rs.getString("title"),
//                    rs.getString("description"));
//            return ad;
//        } catch (SQLException e) {
//            throw new RuntimeException("Error retrieving all ads.", e);
//        }
//    }


    @Override
    public Ad findAdd(String title){
        PreparedStatement stmt = null;
        try {
            String prepStat = "SELECT * FROM ads WHERE title = ?";
            stmt = connection.prepareStatement(prepStat, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, title);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            Ad ad = new Ad(rs.getLong("id"),
                    rs.getString("title"),
                    rs.getString("description"));
            return ad;
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving this ad.", e);
        }
    }
}
