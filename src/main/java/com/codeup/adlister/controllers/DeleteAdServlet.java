package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "DeleteAdServlet", urlPatterns = "/deletead")
public class DeleteAdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
//        User user = (User) request.getSession().getAttribute("user");
        Ad ad = DaoFactory.getAdsDao().findAdd(title);
//        List<Ad> userAds = DaoFactory.getAdsDao().getUsersAds(user.getId());
//        int indexOfDeletedAd = userAds.indexOf(ad);
//        userAds.remove(indexOfDeletedAd);
//        request.getSession().setAttribute("userAds", userAds);

        Ad deleteAd = DaoFactory.getAdsDao().deleteAd(ad);

        response.sendRedirect("/ads");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
