package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.User;
import com.mysql.cj.x.json.JsonParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static com.mysql.cj.core.MysqlType.JSON;

@WebServlet(name = "controllers.AdPicServlet", urlPatterns = "/ads/pic")
public class AdPicServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        Ad ad = DaoFactory.getAdsDao().findAdd(title);
        req.setAttribute("ad", ad);
        req.getRequestDispatcher("/WEB-INF/adPics.jsp").forward(req, resp);
    }
}
