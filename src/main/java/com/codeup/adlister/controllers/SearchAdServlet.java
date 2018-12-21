package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.Ads;
import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "controllers.SearchAdServlet", urlPatterns = "/ads/search")
public class SearchAdServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/ads/search.jsp").forward(request, response);
    }

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//    	search through ad titles and description and catagory based on inputed search term
		String searchTerm = req.getParameter("search");
		List<Ad> results = DaoFactory.getAdsDao().searchAds(searchTerm);
//		if no results, sends boolean back to jsp
		if (results.size() == 0){
			req.setAttribute("noResult", true);
			req.getRequestDispatcher("/WEB-INF/ads/search.jsp").forward(req, resp);
		}
		req.setAttribute("ads", DaoFactory.getAdsDao().searchAds(searchTerm));
		req.getRequestDispatcher("/WEB-INF/ads/search.jsp").forward(req, resp);
	}
}
