package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllers.RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
        request.setAttribute("notFilled", null);
        request.setAttribute("passNotMatch", null);
        request.setAttribute("hasDuplicate", null);
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String passwordConfirmation = request.getParameter("confirm_password");

        // validate input
        boolean duplicateUsername = false;
        if (DaoFactory.getUsersDao().findByUsername(username) != null){
            duplicateUsername = true;
        }

        boolean inputHasErrors = username.isEmpty()
            || email.isEmpty()
            || password.isEmpty();

        if (inputHasErrors) {
            request.setAttribute("username", request.getParameter("username"));
            request.setAttribute("email", request.getParameter("email"));
            request.setAttribute("password", request.getParameter("password"));
            request.setAttribute("notFilled", true);
            request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            return;
        }

        if ((! password.equals(passwordConfirmation))) {
            request.setAttribute("username", request.getParameter("username"));
            request.setAttribute("email", request.getParameter("email"));
            request.setAttribute("passNotMatch", true);
            request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            return;
        }

        if (duplicateUsername){
            request.setAttribute("password", request.getParameter("password"));
            request.setAttribute("email", request.getParameter("email"));
            request.setAttribute("hasDuplicate", true);
            request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
//            response.sendRedirect("/register");
            return;
        }

        // create and save a new user
        request.removeAttribute("hasDuplicate");
        User user = new User(username, email, password);
        DaoFactory.getUsersDao().insert(user);
        response.sendRedirect("/login");
    }
}
