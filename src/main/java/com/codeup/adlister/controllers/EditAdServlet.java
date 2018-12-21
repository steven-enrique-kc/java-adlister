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

@WebServlet(name = "controllers.EditAdServlet", urlPatterns = "/editad")
public class EditAdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        finds the add the user is trying to update and the associated catagories
        String title = request.getParameter("title");
        Ad ad = DaoFactory.getAdsDao().findAdd(title);
        List<String> categories = DaoFactory.getAdsDao().getCatagories(ad);

//        sends those properties to the edit ad form
        request.setAttribute("categories",categories);
        request.setAttribute("Ad", ad);
        request.getRequestDispatcher("/WEB-INF/ads/editad.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        sends user to edit ad form
        request.getRequestDispatcher("/WEB-INF/ads/editad.jsp").forward(request, response);
    }
}
