package fr.uge.jee.servlet.hellosession;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet("/hellosession")
public class HelloSession extends HttpServlet {
    private final HelloSessionData sessionData = new HelloSessionData();

    private String checkCookiesForToken(Cookie[] cookies){

        for(Cookie cookie: cookies){
            if(cookie.getName().equals("hellosession")){
                return cookie.getValue();
            };
        }
        return null;
    }

    private HashMap<String,String> parseCookies(Cookie[] cookieList){
        var cookieMap = new HashMap<String,String>();
        for (int i = 0; cookieList!= null && i < cookieList.length; i++) {
            cookieMap.put(cookieList[i].getName(), cookieList[i].getValue());
        }
        return cookieMap;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html ; charset=utf-8");
        resp.setCharacterEncoding("utf-8");
        PrintWriter writer = resp.getWriter();
        var cookieMap = parseCookies(req.getCookies());
        var logInCookieToken = cookieMap.get("hellosession");


        if(logInCookieToken == null){
            // premiére authentification
            var newToken = sessionData.createToken();
            resp.addCookie(new Cookie("hellosession",newToken.toString()));
            writer.println("<!DOCTYPE html><html><p>Bonjour pour la 1 ére fois</p></html>");
        }else{
            // re authentification

            var tokenValuePair = sessionData.incrementCounter(UUID.fromString(logInCookieToken));
            if(tokenValuePair == null){
                writer.println("<!DOCTYPE html><html><p>token non valide</p></html>");
            }else{
                resp.addCookie(new Cookie("hellosession",tokenValuePair.getKey().toString()));
                writer.println("<!DOCTYPE html><html><p>Bonjour pour la "+tokenValuePair.getValue()+"ème fois</p></html>");
            }
        }
        writer.flush();
    }
}
