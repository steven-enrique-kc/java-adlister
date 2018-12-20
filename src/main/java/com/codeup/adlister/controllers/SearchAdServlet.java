package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllers.SearchAdServlet", urlPatterns = "/ads/search")
public class SearchAdServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchTerm = request.getParameter("search");
		request.setAttribute("ads", DaoFactory.getAdsDao().searchAds(searchTerm));
//		request.setAttribute("categories", DaoFactory.);
		request.getRequestDispatcher("/WEB-INF/ads/search.jsp").forward(request, response);
    }

}
