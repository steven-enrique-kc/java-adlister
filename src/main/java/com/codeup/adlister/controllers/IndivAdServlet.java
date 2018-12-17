package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
import com.mysql.cj.x.json.JsonParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.mysql.cj.core.MysqlType.JSON;

@WebServlet(name = "controllers.IndivAdServlet", urlPatterns = "/ads/indiv")
public class IndivAdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/ads/showAds.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String addLookingFor = request.getParameter("param1");
        System.out.println(addLookingFor);
        Ad thisAdd = DaoFactory.getAdsDao().findAdd(addLookingFor);
        HttpSession session = request.getSession();
        session.setAttribute("thisAdd", thisAdd);
        request.getRequestDispatcher("/WEB-INF/ads/showAds.jsp").forward(request, response);
    }
}
