package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "controllers.SearchAdServlet", urlPatterns = "/ads/search")
public class SearchAdServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/ads/search.jsp")
				.forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchTerm = request.getParameter("search");

		List<Ad> adResults = DaoFactory.getAdsDao().searchAds(searchTerm);

		HttpSession session = request.getSession();

//		was calling "ads" plural in jsp but defined it as ad in here

		session.setAttribute("ads", adResults);


//    	request.setAttribute("ad", DaoFactory.getAdsDao().searchAds(searchTerm));

    	request.getRequestDispatcher("/WEB-INF/ads/search.jsp")
				.forward(request, response);



    }
}
