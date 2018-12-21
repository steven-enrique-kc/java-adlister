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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        grabs the link and Ad and links them together
        String title = req.getParameter("title");
        String pic = req.getParameter("picture");

//        bypasses link if user did not input a picture
        if (pic.equals("")){
            resp.sendRedirect("/ads");
            return;
        }
        Ad ad = DaoFactory.getAdsDao().findAdd(title);
        List<Integer> catagory = DaoFactory.getAdsDao().addPicture(pic, ad);
        resp.sendRedirect("/ads");

        }
}
