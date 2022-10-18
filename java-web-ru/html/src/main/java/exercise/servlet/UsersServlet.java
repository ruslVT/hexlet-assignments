package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;

import java.nio.file.Paths;
import java.nio.file.Files;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang3.ArrayUtils;

public class UsersServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        String pathInfo = request.getPathInfo();

        if (pathInfo == null) {
            showUsers(request, response);
            return;
        }

        String[] pathParts = pathInfo.split("/");
        String id = ArrayUtils.get(pathParts, 1, "");

        showUser(request, response, id);
    }

    private List getUsers() throws JsonProcessingException, IOException {
        // BEGIN
        String file = Files.readString(Paths.get("src/main/resources/users.json").toAbsolutePath());
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(file, List.class);
        // END
    }

    private void showUsers(HttpServletRequest request,
                          HttpServletResponse response)
                throws IOException {

        // BEGIN
        StringBuilder sb = new StringBuilder();
        sb.append("""
                <!DOCTYPE html>
                <html lang=\"ru\">
                  <head>
                    <meta charset=\"UTF-8\">
                    <title>Users</title>
                    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css\"
                          rel=\"stylesheet\"
                          integrity=\"sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We\"
                          crossorigin=\"anonymous\">
                  </head>
                  <body>
                    <table>
                """);

        List<Map<String, String>> users = getUsers();
        for (Map<String, String> user : users) {
            sb.append("<tr><td>");
            sb.append(user.get("id"));
            sb.append("</td><td>");
            sb.append("<a href=\"/users/").append(user.get("id")).append("\">");
            sb.append(user.get("firstName")).append(" ").append(user.get("lastName")).append("</a>");
            sb.append("</td> </tr>");
        }

        sb.append("""
                    </table>
                  </body>
                </html>
                """);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(sb);
        // END
    }

    private void showUser(HttpServletRequest request,
                         HttpServletResponse response,
                         String id)
                 throws IOException {

        // BEGIN
        if (id != null) {
            List<Map<String, String>> users = getUsers();
            for (Map<String, String> user : users) {
                if (user.get("id").equals(id)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("""
                            <!DOCTYPE html>
                            <html lang=\"ru\">
                              <head>
                                <meta charset=\"UTF-8\">
                                <title>User</title>
                                <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css\"
                            rel=\"stylesheet\"
                            integrity=\"sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We\"
                            crossorigin=\"anonymous\">
                              </head>
                              <body>
                                <table>
                            """);
                    sb.append("<tr><td>");
                    sb.append(user.get("id")).append(" ");
                    sb.append("</td><td>");
                    sb.append(user.get("firstName")).append(" ").append(user.get("lastName")).append(" ");
                    sb.append("</td><td>");
                    sb.append(user.get("email"));
                    sb.append("</td> </tr>");
                    sb.append("""
                                </table>
                              </body>
                            </html>
                            """);

                    response.setContentType("text/html;charset=UTF-8");
                    PrintWriter out = response.getWriter();
                    out.println(sb);
                    return;
                }
            }
        }
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
        // END
    }
}
