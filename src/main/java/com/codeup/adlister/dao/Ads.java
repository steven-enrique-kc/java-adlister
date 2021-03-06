package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;

import java.util.List;

public interface Ads {
    // get a list of all the ads
    List<Ad> all();
    // insert a new ad and return the new ad's id
    Long insert(Ad ad);
    // search for ads matching search term
    List<Ad> searchAds(String searchTerm);

    Ad findAdd(String title);
    //Retrieve the user's ads
    List<Ad> getUsersAds(long id);

    Ad editAd(Ad ad);

    List<Integer> categories(List<Integer> categories, Ad ad);

    List<String> getCatagories(Ad ad);

    Ad deleteAd(Ad ad);
    List<Integer> addPicture(String Link, Ad ad);

    List<String> getPicture(Ad ad);

    List<Integer> editCategories(List<Integer> categories, Ad ad);
}
