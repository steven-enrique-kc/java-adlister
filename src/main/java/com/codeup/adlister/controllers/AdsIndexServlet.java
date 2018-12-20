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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        String id = req.getParameter("oldId");
        String oldTitle = req.getParameter("oldName");
        Ad prevAd = DaoFactory.getAdsDao().findAdd(oldTitle);
        Ad newAd = new Ad(prevAd.getId(), prevAd.getUserId(), title,description);
        Ad ad = DaoFactory.getAdsDao().editAd(newAd);
        List<Ad> userAds = DaoFactory.getAdsDao().getUsersAds(ad.getUserId());
        userAds.add(ad);
        req.getSession().setAttribute("userAds", userAds);
        resp.sendRedirect("/ads");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.removeAttribute("thisAds");
        request.setAttribute("ads", DaoFactory.getAdsDao().all());
        request.getRequestDispatcher("/WEB-INF/ads/index.jsp").forward(request, response);
    }
}
