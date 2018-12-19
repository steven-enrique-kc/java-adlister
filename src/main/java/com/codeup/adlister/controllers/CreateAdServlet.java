package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.dao.MySQLAdsDao;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "controllers.CreateAdServlet", urlPatterns = "/ads/create")
public class CreateAdServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/login");
            return;
        }
        request.getRequestDispatcher("/WEB-INF/ads/create.jsp")
            .forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
        String username = (String) request.getSession().getAttribute("username");

        User user = DaoFactory.getUsersDao().findByUsername(username);
        User user1 = (User) request.getSession().getAttribute("user");
        List<Ad> userAds1 = DaoFactory.getAdsDao().getUsersAds(user.getId());

        boolean duplicateAdTitle = false;
        List<Ad> ads = DaoFactory.getAdsDao().all();
        for (Ad ad : ads){
            if (ad.getTitle().equals(request.getParameter("title"))){
                duplicateAdTitle = true;
            }
        }

        if (duplicateAdTitle){
            String description = request.getParameter("description");
            request.setAttribute("description", description);
            request.setAttribute("duplicateTitle", true);
            request.getRequestDispatcher("/WEB-INF/ads/create.jsp").forward(request, response);
            return;
        }

        if (request.getParameter("title").equals("")){
            String description = request.getParameter("description");
            request.setAttribute("description", description);
            request.setAttribute("allValues", true);
            request.getRequestDispatcher("/WEB-INF/ads/create.jsp").forward(request, response);
            return;
        }
        if (request.getParameter("description").equals("")){
            String title = request.getParameter("title");
            request.setAttribute("title", title);
            request.setAttribute("allValues", true);
            request.getRequestDispatcher("/WEB-INF/ads/create.jsp").forward(request, response);
            return;
        }
//        Try to make the check where title isnt reused
//        if (DaoFactory.getAdsDao().searchAds(request.getParameter("title")) != null){
//            String description = request.getParameter("description");
//            request.setAttribute("description", description);
//            request.setAttribute("duplicateTitle", true);
//            request.getRequestDispatcher("/WEB-INF/ads/create.jsp").forward(request, response);
//            return;
//        }
        Ad ad = new Ad(
            user.getId(),
            request.getParameter("title"),
            request.getParameter("description")
        );
        DaoFactory.getAdsDao().insert(ad);
        List<Ad> userAds = DaoFactory.getAdsDao().getUsersAds(user.getId());

        request.getSession().setAttribute("userAds", userAds);
        response.sendRedirect("/profile");
    }
}
