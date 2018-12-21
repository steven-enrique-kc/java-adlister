package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "controllers.AdsIndexServlet", urlPatterns = "/ads")
public class AdsIndexServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        grabs both the adited ad title and description and their old versions
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        String oldTitle = req.getParameter("oldName");
//        finds old version and new version of ad, then edits them
        Ad prevAd = DaoFactory.getAdsDao().findAdd(oldTitle);
        Ad newAd = new Ad(prevAd.getId(), prevAd.getUserId(), title,description);
        Ad ad = DaoFactory.getAdsDao().editAd(newAd);
//        List<String> categories = DaoFactory.getAdsDao().getCatagories(ad);
//        if (req.getParameter("1").equals("1")) {
//            categories.add(1);
//        }
//        if (req.getParameter("2").equals("1")) {
//            categories.add(2);
//        }
//        if (req.getParameter("3").equals("1")) {
//            categories.add(3);
//        }
//        if (req.getParameter("4").equals("1")) {
//            categories.add(4);
//        }
//        if (req.getParameter("5").equals("1")) {
//            categories.add(5);
//        }
//        if (req.getParameter("6").equals("1")) {
//            categories.add(6);
//        }
//        grabs the updated ads assigned to a particular user, and reassignes the variable to display for a user
        List<Ad> userAds = DaoFactory.getAdsDao().getUsersAds(ad.getUserId());
        userAds.add(ad);
        req.getSession().setAttribute("userAds", userAds);
        resp.sendRedirect("/ads");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        removes the attributes so theat they can be reassigned when the user clicks a new ad
//        and sets attribute to show all the adds
        request.removeAttribute("thisAds");
        request.setAttribute("ads", DaoFactory.getAdsDao().all());
        request.getRequestDispatcher("/WEB-INF/ads/index.jsp").forward(request, response);
    }
}
