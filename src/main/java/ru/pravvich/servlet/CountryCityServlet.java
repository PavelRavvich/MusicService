package ru.pravvich.servlet;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Form controller for refer Counties with Cities.
 */
public class CountryCityServlet extends HttpServlet{
    protected void doGet(final HttpServletRequest req,
                         final HttpServletResponse resp)
            throws ServletException, IOException {



        resp.setContentType("application/json");

        resp.getWriter()
                .write(new JsonConverter().getAllCity(
                        req.getParameter("country")
                ));
    }

    /**
     * Convert to JSON.
     */
    private class JsonConverter {

        public String getAllCity(final String country) {
            final List<String> list = new ArrayList<>();

            if (country.equals("USA")) {
                list.add("New York");
                list.add("Boston");
                list.add("Chicago");
                list.add("Dallas");
            } else if (country.equals("Israel")) {
                list.add("Jerusalem");
                list.add("Tel Aviv");
                list.add("Haifa");
                list.add("Ashdod");
            } else if (country.equals("Select Country")) {
                list.add("Select Country");
            }

            return new Gson().toJson(list);
        }

    }
}
