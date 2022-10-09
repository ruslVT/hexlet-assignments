package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.stream.Collectors;
import static exercise.Data.getCompanies;

public class CompaniesServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        // BEGIN
        String search = request.getParameter("search");
        PrintWriter pw = response.getWriter();
        if (search == null || search.equals("")) {
            pw.println(String.join("\n", getCompanies()));
            pw.close();
        } else {
            List<String> list = getCompanies().stream()
                    .filter(s -> s.contains(search)).toList();
            if (!list.isEmpty()) {
                pw.println(String.join("\n", list));
                pw.close();
            } else {
                pw.println("Companies not found");
                pw.close();
            }
        }
        // END
    }
}
