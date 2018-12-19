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
        if (request.getSession().getAttribute("user") != null) {
            response.sendRedirect("/profile");
            return;
        }
        String referer = request.getHeader("Referer");
        request.getSession().setAttribute("PrevPage", referer);
        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = DaoFactory.getUsersDao().findByUsername(username);
        if (user == null) {
            request.getSession().setAttribute("username", username);
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }

        List<Ad> userAds = DaoFactory.getAdsDao().getUsersAds(user.getId());

        boolean validAttempt = Password.check(password, user.getPassword());

        if (validAttempt) {
//            if (prevSite != null){
//                request.getSession().setAttribute("user", user);
//                request.getSession().setAttribute("username", user.getUsername());
//                request.getSession().setAttribute("email", user.getEmail());
//                request.getSession().setAttribute("userid", user.getId());
//                request.getSession().setAttribute("userAds", userAds);
//                response.sendRedirect("/profile");
//            }
            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("username", user.getUsername());
            request.getSession().setAttribute("email", user.getEmail());
            request.getSession().setAttribute("userid", user.getId());
            request.getSession().setAttribute("userAds", userAds);
            response.sendRedirect("/profile");
        } else {
            response.sendRedirect("/login");
        }
    }
}
