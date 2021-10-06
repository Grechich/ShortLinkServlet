package ua.kiev.prog.ShortLinkServlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collection;

@WebServlet(name = "statServlet", value = "/stat")
public class StatServlet extends HttpServlet {

    private final UrlDatabase urlDatabase = UrlDatabase.INSTANCE;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        Collection<UrlDatabase.UrlRecord> stat = urlDatabase.getStats();

        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(stat);

        response.setContentType("application/json");
        try {
            response.getWriter().print(json);
        } catch (IOException e) {
            response.setStatus(500);
        }
    }
}
