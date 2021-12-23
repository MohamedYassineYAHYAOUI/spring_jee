package fr.uge.jee.servlet.hellosession;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/hellosessionbetter")
public class HelloSessionBetter  extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html ; charset=utf-8");
        resp.setCharacterEncoding("utf-8");
        PrintWriter writer = resp.getWriter();

        var session = req.getSession(true);

        if(session.isNew()){
            writer.println("<!DOCTYPE html><html><p>Bonjour pour la 1 ére fois</p></html>");
            session.setAttribute("helloSessionBetter", 1);
        }else{
            var count = (int) session.getAttribute("helloSessionBetter");
            count++;
            writer.println("<!DOCTYPE html><html><p>Bonjour pour "+count+"ème fois</p></html>");
            session.setAttribute("helloSessionBetter", count);
        }
        writer.flush();
    }
}
