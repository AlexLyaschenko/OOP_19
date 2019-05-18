package JUnit;

import com.univ.webService.servlet.HandlerServlet;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;

public class HandlerServletTest extends Mockito {

    @Test
    public void doPost() throws Exception{
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("sessionId")).thenReturn("2");
        when(request.getParameter("login")).thenReturn("admin");
        when(request.getParameter("pass")).thenReturn("admin");
        when(request.getRequestDispatcher("response.jsp")).thenReturn(dispatcher);
        new HandlerServlet().doPost(request, response);
        verify(request, atLeast(1)).getParameter("login");
        verify(request, atLeast(1)).getSession();

    }
}