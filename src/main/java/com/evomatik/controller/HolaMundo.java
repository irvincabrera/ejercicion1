package com.evomatik.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HolaMundo extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Clasico Hola mundo!</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Hola mundo de servlets!</h1>");
        out.println("<h2>Bienvenido " + request.getParameter("nombres") + "!!!</h2>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Clasico Hola mundo!</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Hola mundo de servlets!</h1>");
        out.println("<h2>Bienvenido " + req.getParameter("nombres") + "!!!</h2>");
        out.println("</body>");
        out.println("</html>");
        super.doPost(req, resp);
    }
}