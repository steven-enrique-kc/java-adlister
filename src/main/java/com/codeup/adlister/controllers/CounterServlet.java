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

@WebServlet(name = "controllers.CounterServlet", urlPatterns = "/count")
public class CounterServlet extends HttpServlet {
    private int counter = 0;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        counter += 1;
        response.getWriter().println("<h1>The count is " + counter + ".</h1>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String pic = req.getParameter("picture");
        Ad ad = DaoFactory.getAdsDao().findAdd(title);
        List<Integer> catagory = DaoFactory.getAdsDao().addPicture(pic, ad);
        if (catagory.size() == 0){
            resp.sendRedirect("/ads");
            return;
        }
        resp.sendRedirect("/ads");

        }
}
