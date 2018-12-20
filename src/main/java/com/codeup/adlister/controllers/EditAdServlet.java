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

@WebServlet(name = "controllers.EditAdServlet", urlPatterns = "/editad")
public class EditAdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        Ad ad = DaoFactory.getAdsDao().findAdd(title);
        request.setAttribute("Ad", ad);
//        User user = (User) request.getSession().getAttribute("user");
//        List<Ad> userAds = DaoFactory.getAdsDao().getUsersAds(user.getId());
//        if (request.getParameter("title").equals("")){
//            String description = request.getParameter("description");
//            request.setAttribute("description", description);
//            request.setAttribute("allValues", true);
//            request.getRequestDispatcher("/WEB-INF/ads/editad.jsp").forward(request, response);
//            return;
//        }
//        if (request.getParameter("description").equals("")){
//            String title = request.getParameter("title");
//            request.setAttribute("title", title);
//            request.setAttribute("allValues", true);
//            request.getRequestDispatcher("/WEB-INF/ads/editad.jsp").forward(request, response);
//            return;
//        }
//        Try to make the check where title isnt reused
//        if (DaoFactory.getAdsDao().findAdd(request.getParameter("title")) != null){
//            String description = request.getParameter("description");
//            request.setAttribute("description", description);
//            request.setAttribute("duplicateTitle", true);
//            request.getRequestDispatcher("/WEB-INF/ads/create.jsp").forward(request, response);
//            return;
//        }
//        String addLookingFor = request.getParameter("title");
//        Ad ad = new Ad(
//                user.getId(),
//                request.getParameter("title"),
//                request.getParameter("description")
//        );
//        DaoFactory.getAdsDao().editAd(ad);
//        response.sendRedirect("/profile");
        request.getRequestDispatcher("/WEB-INF/ads/editad.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String title = request.getParameter("title");
//        System.out.println(title);
//        Ad ad = DaoFactory.getAdsDao().findAdd(title);
//        request.setAttribute("Ad", ad);
        request.getRequestDispatcher("/WEB-INF/ads/editad.jsp").forward(request, response);
    }
}
