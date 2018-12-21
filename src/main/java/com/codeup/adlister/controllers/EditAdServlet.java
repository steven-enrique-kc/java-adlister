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
        List<String> categories = DaoFactory.getAdsDao().getCatagories(ad);
        System.out.println(categories);
        request.setAttribute("categories",categories);
        request.setAttribute("Ad", ad);
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
