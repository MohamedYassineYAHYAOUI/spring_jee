package fr.uge.jee.servlet.rectangle;


import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@WebServlet("/rectangle")
public class Rectangle extends HttpServlet {

    private InputStream formCode;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setContentType("text/html ; charset=utf-8");
        resp.setCharacterEncoding("utf-8");

        //InputStream inputStream =getServletContext().getResourceAsStream("/WEB-INF/templates/rectangle-form.html");

        PrintWriter writer = resp.getWriter();
        writer.println(readFromInputStream(formCode));
        //writer.println(formCode);
        writer.flush();
    }


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        formCode = getServletContext().getResourceAsStream("/WEB-INF/templates/rectangle-form.html");
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html ; charset=utf-8");
        resp.setCharacterEncoding("utf-8");

        String height = req.getParameter("height");
        String width = req.getParameter("width");

        PrintWriter writer = resp.getWriter();
        writer.println("<html><p>Area if the rectangle is : "+Integer.valueOf(height) * Integer.valueOf(width)+"</p></html>");
        writer.flush();
    }

    public static String readFromInputStream(InputStream inputStream) throws IOException {
        var lines = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8)).lines();
        return lines.collect(Collectors.joining("\n"));
    }
}
