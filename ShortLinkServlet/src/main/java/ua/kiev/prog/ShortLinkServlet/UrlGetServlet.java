package ua.kiev.prog.ShortLinkServlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "getServlet", value = "/my/*")
public class UrlGetServlet extends HttpServlet {

    private final UrlDatabase urlDatabase = UrlDatabase.INSTANCE;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        String url = request.getRequestURL().toString();
        int idx = url.lastIndexOf('/');

        if (idx <= 0) {
            response.setStatus(400);
            return;
        }

        String shortUrl = url.substring(idx + 1);
        String redirectUrl = urlDatabase.get(shortUrl);

        try {
            response.sendRedirect(redirectUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}