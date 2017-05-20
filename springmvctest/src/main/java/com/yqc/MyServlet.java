package com.yqc;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yangqc on 2017/4/12.
 */
@WebServlet(name = "myServlet",urlPatterns = "/myServlet")
public class MyServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        System.out.println(this.getServletContext().getAttribute("myData"));
    }

}
