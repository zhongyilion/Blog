package org.blog.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/TestPiccodeServlet")
public class TestPiccodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String piccode = (String)request.getSession().getAttribute("piccode");
        piccode = piccode.toUpperCase();
        String value = request.getParameter("value");
        System.out.println("piccode:"+piccode+"========value:"+value);
        PrintWriter writer = response.getWriter();
        if(piccode.equals(value)){
            writer.print("true");
        }else{
            writer.print("true");
        }
        writer.flush();
        writer.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
