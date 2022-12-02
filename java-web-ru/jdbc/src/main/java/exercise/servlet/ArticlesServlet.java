package exercise.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import org.apache.commons.lang3.ArrayUtils;

import exercise.TemplateEngineUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;



public class ArticlesServlet extends HttpServlet {

    private String getId(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            return null;
        }
        String[] pathParts = pathInfo.split("/");
        return ArrayUtils.get(pathParts, 1, null);
    }

    private String getAction(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            return "list";
        }
        String[] pathParts = pathInfo.split("/");
        return ArrayUtils.get(pathParts, 2, getId(request));
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        String action = getAction(request);

        switch (action) {
            case "list":
                showArticles(request, response);
                break;
            default:
                showArticle(request, response);
                break;
        }
    }

    private void showArticles(HttpServletRequest request,
                          HttpServletResponse response)
                throws IOException, ServletException {

        ServletContext context = request.getServletContext();
        Connection connection = (Connection) context.getAttribute("dbConnection");
        // BEGIN

        List<Map<String, String>> articles = new ArrayList<>();
        String pageNumber = request.getParameter("page");
        Integer page = 0;
        String query = "SELECT id, title FROM articles ORDER BY id LIMIT 10 OFFSET ?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);

            if (pageNumber == null) {
                statement.setInt(1, 0);
                page = 1;
            } else {
                page = Integer.parseInt(pageNumber);
                statement.setInt(1,page * 10 - 10);
            }

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                articles.add(Map.of("id", rs.getString("id"), "title", rs.getString("title")));
            }
        } catch (SQLException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        request.setAttribute("articles", articles);
        request.setAttribute("page", page);

        // END
        TemplateEngineUtil.render("articles/index.html", request, response);
    }

    private void showArticle(HttpServletRequest request,
                         HttpServletResponse response)
                 throws IOException, ServletException {

        ServletContext context = request.getServletContext();
        Connection connection = (Connection) context.getAttribute("dbConnection");
        // BEGIN

        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");
        String id = ArrayUtils.get(pathParts, 1, null);
        Map<String, String> article = new HashMap<>();
        String query = "SELECT title, body FROM articles WHERE id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, Integer.parseInt(id));
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                article.put("title", rs.getString("title"));
                article.put("body", rs.getString("body"));
            }

            if (article.isEmpty()) {
                response.setStatus(404);
                return;
            }

        } catch (SQLException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }

        request.setAttribute("article", article);

        // END
        TemplateEngineUtil.render("articles/show.html", request, response);
    }
}
