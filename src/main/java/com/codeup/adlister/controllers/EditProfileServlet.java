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

import static com.codeup.adlister.controllers.RegisterServlet.meetsRequirement;

@WebServlet(name = "controllers.EditProfileServlet", urlPatterns = "/editprofile")
public class EditProfileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        grabs entered info for creating a new user
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String passwordConfirmation = request.getParameter("confirm_password");

//        checks if user has left email or password are empty
        boolean inputHasErrors = username.isEmpty()
                || email.isEmpty()
                || password.isEmpty();

        if (inputHasErrors) {
            response.sendRedirect("/editprofile");
            return;
        }
//        makes sure password meets requirement
        if (!RegisterServlet.meetsRequirement(password)){
            request.setAttribute("username", request.getParameter("username"));
            request.setAttribute("email", request.getParameter("email"));
            request.setAttribute("passwordNoMatch", true);
            request.getRequestDispatcher("/WEB-INF/editprofile.jsp").forward(request, response);
            return;
        }

//        ensures proper formatting for entered email address
        if (!RegisterServlet.isValidEmailAddress(email)){
            request.setAttribute("username", request.getParameter("username"));
            request.setAttribute("password", request.getParameter("password"));
            request.setAttribute("notFormatEmail", true);
            request.getRequestDispatcher("/WEB-INF/editprofile.jsp").forward(request, response);
            return;
        }

//        ensures password and confirmation match
        if ((! password.equals(passwordConfirmation))) {
            request.setAttribute("username", request.getParameter("username"));
            request.setAttribute("email", request.getParameter("email"));
            request.setAttribute("passNotMatch", true);
            request.getRequestDispatcher("/WEB-INF/editprofile.jsp").forward(request, response);
            return;
        }

        // edit the user and set attributes
        request.removeAttribute("hasDuplicate");
        User user = DaoFactory.getUsersDao().findByUsername((String) request.getSession().getAttribute("username"));
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);

//        edits the user in the database
        DaoFactory.getUsersDao().editUser(user);
//        assigns a cookie that updates all info related to user
        request.getSession().setAttribute("user", user);
        List<Ad> userAds = DaoFactory.getAdsDao().getUsersAds(user.getId());

        request.getSession().setAttribute("username", user.getUsername());
        request.getSession().setAttribute("password", user.getPassword());
        request.getSession().setAttribute("email", user.getEmail());
        request.getSession().setAttribute("userid", user.getId());
        request.getSession().setAttribute("userAds", userAds);
        response.sendRedirect("/profile");
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/editprofile");
            return;
        }

        String username = user.getUsername();
        String email = user.getEmail();
        request.setAttribute("username",username);
        request.setAttribute("email",email);
        request.getRequestDispatcher("/WEB-INF/editprofile.jsp").forward(request, response);
    }
}
