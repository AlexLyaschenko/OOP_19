package JUnit;

import com.univ.webService.DAO.AbonentDAO;
import com.univ.webService.dataConnection.DataConnection;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class AbonentDAOTest extends Mockito {


    @Test
    public void getAbonentFromDB() throws SQLException{
        Connection connection = mock(Connection.class);
        when(DataConnection.getDBConnection()).thenReturn(connection);

        AbonentDAO abonentDAO = new AbonentDAO();
        abonentDAO.getAbonentFromDB(1, "", "", "", -1, -1,"","",0);
        verify(connection, times(1)).createStatement();
    }

}