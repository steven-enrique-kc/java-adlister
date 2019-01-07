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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "controllers.CreateAdServlet", urlPatterns = "/ads/create")
public class CreateAdServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    keeps user from assessing if not logged in, redirects to login
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/login");
            return;
        }

        request.getRequestDispatcher("/WEB-INF/ads/create.jsp")
            .forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
//    grabs useername of currently logged in user;
        User user = (User) request.getSession().getAttribute("user");
        List<Integer> categories = new ArrayList<>();

        String username = (String) request.getSession().getAttribute("username");

//        checks if the entered title already exists
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

//        forces user to enter something for title and description
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

//        check to see if checkboxes for each catagory are checked or not
        if (request.getParameter("1").equals("1")) {
            categories.add(1);
        }
        if (request.getParameter("2").equals("1")) {
            categories.add(2);
        }
        if (request.getParameter("3").equals("1")) {
            categories.add(3);
        }
        if (request.getParameter("4").equals("1")) {
            categories.add(4);
        }
        if (request.getParameter("5").equals("1")) {
            categories.add(5);
        }
        if (request.getParameter("6").equals("1")) {
            categories.add(6);
        }

//        generates new ad based on user input and ads it to the database
        Ad ad = new Ad(
            user.getId(),
            request.getParameter("title"),
            request.getParameter("description")
        );
        DaoFactory.getAdsDao().insert(ad);

//        ads the newly genearated ad to the cookie storing all ads for this user
        List<Ad> userAds = DaoFactory.getAdsDao().getUsersAds(user.getId());
        DaoFactory.getAdsDao().categories(categories, ad);
        request.getSession().setAttribute("userAds", userAds);

//        redirects back to ads index
        response.sendRedirect("/profile");


    }
}
