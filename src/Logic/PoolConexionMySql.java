package Logic;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.PooledConnection;
import javax.swing.JOptionPane;

/**
 *
 * @author Gloriana
 */
public class PoolConexionMySql {

    private MysqlConnectionPoolDataSource ds_con;
    private PooledConnection pool;

    public PoolConexionMySql() {
        ds_con = new MysqlConnectionPoolDataSource();
        ds_con.setURL("jdbc:mysql://localhost:3306/trasport");
        ds_con.setUser("root");
        ds_con.setPassword("1234");
        try {
            pool = ds_con.getPooledConnection();
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }

    public Connection getConexion() {
        try {
            return pool.getConnection();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Se perdio la conexión con el servidor :(", "Conexion al servidor", JOptionPane.ERROR_MESSAGE);
            System.err.println(ex);
        }
        return null;
    }
}
