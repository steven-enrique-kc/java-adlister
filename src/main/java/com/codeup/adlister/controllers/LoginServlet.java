package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.User;
import com.codeup.adlister.util.Password;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "controllers.LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        reidrects to user profile if they are already logged in
        if (request.getSession().getAttribute("user") != null) {
            response.sendRedirect("/profile");
            return;
        }
        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
//        grabs the inputted username and password and sees if there is a related user in database based on username
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = DaoFactory.getUsersDao().findByUsername(username);
//        if theyre isnt a matching user, redirects user back to login page
        if (user == null) {
            request.getSession().setAttribute("username", username);
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }

//        gets all ads to that user
        List<Ad> userAds = DaoFactory.getAdsDao().getUsersAds(user.getId());

//        checks to see if password matches one for the user attempting to log in
        boolean validAttempt = Password.check(password, user.getPassword());

//        if attempt is valid, sends user to their profile page
        if (validAttempt) {
            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("username", user.getUsername());
            request.getSession().setAttribute("email", user.getEmail());
            request.getSession().setAttribute("userid", user.getId());
            request.getSession().setAttribute("userAds", userAds);
            response.sendRedirect("/profile");
//            if not, redirects them back to login
        } else {
            request.setAttribute("incorrectPass", true);
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
    }
}
