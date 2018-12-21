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

@WebServlet(name = "controllers.IndivAdServlet", urlPatterns = "/ads/indiv")
public class IndivAdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        redirects the user if they are not logged in
        if (req.getSession().getAttribute("user") == null) {
            resp.sendRedirect("/login");
            return;
        }
        req.getRequestDispatcher("/WEB-INF/ads/showAds.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        finds the field related to title of ad and uses it to look up ad and categories
        String addLookingFor = request.getParameter("param1");
        Ad thisAdd = DaoFactory.getAdsDao().findAdd(addLookingFor);
        List<String> catagories = DaoFactory.getAdsDao().getCatagories(thisAdd);

//        sends all info to for the ad to the jsp
        HttpSession session = request.getSession();
//        checks to see if the ad has an associated pic link, and sends it if it does
        if (DaoFactory.getAdsDao().getPicture(thisAdd).size() != 0){
            String picLink = DaoFactory.getAdsDao().getPicture(thisAdd).get(0);
            request.setAttribute("picture", picLink);
        }
        session.setAttribute("thisAdd", thisAdd);
        session.setAttribute("catagories", catagories);
        request.getRequestDispatcher("/WEB-INF/ads/showAds.jsp").forward(request, response);
    }
}
