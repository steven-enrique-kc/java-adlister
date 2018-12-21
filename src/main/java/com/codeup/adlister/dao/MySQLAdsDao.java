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

//    generates all ads from database
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
//    finds add based on a users id
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

//    inserts add into database
    @Override
    public Long insert(Ad ad) {
        try {
            String insertQuery = "INSERT INTO ads(user_id, title, description) VALUES (?, ?, ?);" +
                    "";
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

//    deletes add from the database
    public Ad deleteAd(Ad ad) {
        PreparedStatement stmt = null;
        try {
            String editQuery = "DELETE ads_categories.*, ads.* FROM ads_categories LEFT JOIN ads ON ads_categories.ad_id = ads.id WHERE ads_categories.ad_id = ?";
            stmt = connection.prepareStatement(editQuery);
            System.out.println(ad.getId());
            stmt.setLong(1, ad.getId());
            System.out.println(stmt);
            stmt.executeUpdate();
            return ad;
        } catch (SQLException e) {
            throw new RuntimeException("Error in deleting ad.", e);
        }
    }

//    edits add already in the database
    public Ad editAd(Ad ad) {

        PreparedStatement stmt = null;
        try {
            String editQuery = "UPDATE ads SET title = ?, description = ? WHERE id = ?";
            stmt = connection.prepareStatement(editQuery);
            stmt.setString(1, ad.getTitle());
            stmt.setString(2, ad.getDescription());
            stmt.setLong(3, ad.getId());

            stmt.executeUpdate();
            return ad;
        } catch (SQLException e) {
            throw new RuntimeException("Error in editing ad.", e);
        }
    }

//    extracts add from the database, for use inside function
    private Ad extractAd(ResultSet rs) throws SQLException {
        return new Ad(
                rs.getLong("id"),
                rs.getLong("user_id"),
                rs.getString("title"),
                rs.getString("description")
        );
    }

//    create the ad inside a function
    private List<Ad> createAdsFromResults(ResultSet rs) throws SQLException {
        List<Ad> ads = new ArrayList<>();
        while (rs.next()) {
            ads.add(extractAd(rs));
        }
        return ads;
    }

//    search for an add in the database
    @Override
    public List<Ad> searchAds(String searchTerm) {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM ads WHERE title LIKE ? OR description LIKE ? OR " +
                    "id in (SELECT ad_id from ads_categories where category_id in (SELECT id from categories where category like ?))");
//            SELECT ads.*, categories.* FROM ads, categories WHERE ads.title LIKE ? OR ads.description LIKE ? OR categories.category LIKE ?
            stmt.setString(1, '%' + searchTerm + '%');
            stmt.setString(2, '%' + searchTerm + '%');
            stmt.setString(3, '%' + searchTerm + '%');
            ResultSet rs = stmt.executeQuery();
            return createAdsFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving requested ads.", e);
        }
    }

//find an add based on a given title
    @Override
    public Ad findAdd(String title) {
        PreparedStatement stmt = null;
        try {
            String prepStat = "SELECT * FROM ads WHERE title = ?";
            stmt = connection.prepareStatement(prepStat, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, title);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            Ad ad = new Ad(rs.getLong("id"),
                    rs.getLong("user_id"),
                    rs.getString("title"),
                    rs.getString("description"));
            return ad;
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving this ad.", e);
        }
    }

//    selects the id of an ad
    public int findThisAdd(String title) {
        int answer = 0;
        PreparedStatement stmt = null;
        try {
            String prepStat = "SELECT id FROM ads WHERE title = ?";
            stmt = connection.prepareStatement(prepStat, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, title);
            ResultSet rs = stmt.executeQuery();
            System.out.println(rs);
            rs.next();
            answer = rs.getInt("id");
            return answer;
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving this ad.", e);
        }
    }

//    gets the catagories applicable for a given ad
    public List<String> getCatagories(Ad ad) {
        List<String> result = new ArrayList<>();
        PreparedStatement stmt = null;
        try {
            String insertQuery = "SELECT * FROM categories WHERE id IN ( SELECT category_id FROM ads_categories WHERE ad_id = ?)";
            stmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, ad.getId());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result.add(rs.getString("category"));
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving user\'s ads.", e);
        }
    }

//    adds cataqgories for a given ad
    public List<Integer> categories(List<Integer> categories, Ad ad) {
        List<Integer> result = new ArrayList<>();
        MySQLAdsDao dao = new MySQLAdsDao(new Config());
        try {
            for (int i = 0; i < categories.size(); i++) {
                String insertQuery = "INSERT INTO ads_categories(ad_id, category_id) VALUES (?, ?)";
                PreparedStatement stmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
                stmt.setLong(1, dao.findThisAdd(ad.getTitle()));
                stmt.setInt(2, categories.get(i));
                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();
                while (rs.next()) {
                    result.add(rs.getInt(1));
                }
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Error creating a new ad.", e);
        }
    }


    public List<Integer> editCategories(List<Integer> categories, Ad ad) {
        List<Integer> result = new ArrayList<>();
        MySQLAdsDao dao = new MySQLAdsDao(new Config());
        try {
            for (int i = 0; i < categories.size(); i++) {
                String insertQuery = "DELETE FROM ads_categories WHERE ad_id = ? and category_id = ?";
                PreparedStatement stmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
                stmt.setLong(1, dao.findThisAdd(ad.getTitle()));
                stmt.setInt(2, categories.get(i));
                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();
                while (rs.next()) {
                    result.add(rs.getInt(1));
                }
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Error creating a new ad.", e);
        }
    }

//    adds a link to a picture for a given ad
    public List<Integer> addPicture(String Link, Ad ad) {
        List<Integer> result = new ArrayList<>();
        try {
            String insertQuery = "INSERT INTO pictures(ad_id, link) VALUES (?, ?)";
            PreparedStatement stmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, ad.getId());
            stmt.setString(2, Link);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            while (rs.next()) {
                result.add(rs.getInt(1));
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Error creating a new ad.", e);
        }
    }

//    retrives the picture for a given ad
    public List<String> getPicture(Ad ad) {
        List<String> result = new ArrayList<>();
        try {
            String insertQuery = "SELECT link from pictures where ad_id = ?";
            PreparedStatement stmt = connection.prepareStatement(insertQuery);
            stmt.setLong(1, ad.getId());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result.add(rs.getString(1));
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Error getting picture.", e);
        }

    }

}
