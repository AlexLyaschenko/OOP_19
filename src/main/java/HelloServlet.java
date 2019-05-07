
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AbonentDAO abonentDAO = new AbonentDAO();
        Abonent abonent = abonentDAO.authentication(request.getParameter("login"), request.getParameter("pass"));
        if (abonent.getLogin() != null) {
            if (abonent.getIsAdmin() == 0) {
                request.setAttribute("sessionId", 100);
                request.setAttribute("type", "Abonent");
                request.setAttribute("name", abonent.getName());
                request.setAttribute("surname", abonent.getSurname());
                request.setAttribute("number", abonent.getPhoneNumber());
            }
            else {
                request.setAttribute("sessionId", 7);
                request.setAttribute("type", "Admin");
            }
        } else {
            request.setAttribute("sessionId", 0);
            request.setAttribute("type", "Wrong");
        }
        request.getRequestDispatcher("response.jsp").forward(request, response);

    }
}
