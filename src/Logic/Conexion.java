package Logic;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Ing. Gloriana Peña Ramírez
 */
public class Conexion {
    //ATRIBUTOS DE LA CLASE CLSCONEXION

    static private PoolConexionMySql poolConexion;
    static protected Connection conexion;//Para hacer la conexión
    protected CallableStatement obj_Procedimiento;//Utilizar los procedure
    protected Statement stmt;//Hacer sentencias SQL
    protected ResultSet rs;//Guardar los resultados de las sentencias SQL
    //MÉTODOS PÚBLICOS DE LA CLASE CONEXCIONBD
    //Constructor default

    public Conexion() {
        // conexion = null;
        obj_Procedimiento = null;
        if (poolConexion == null) {
            poolConexion = new PoolConexionMySql();
        }
    }//=========================================================================

    public synchronized boolean conectarBD() {
        try {
            conexion = poolConexion.getConexion();
            if (conexion != null) {
                System.out.print("Conexion obtenida\n");
                return true;
            } else {
                conexion = poolConexion.getConexion();
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Problemas con la conexion, "
                    + "reingrese al sistema", "Servidor de bd", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }//=========================================================================
    //Desconecta la conexion con Oracle y el usuario establecidos anteriormente

    public synchronized void desconectarBD() {

        if (obj_Procedimiento != null) {
            try {
                obj_Procedimiento.close();
            } catch (SQLException e) {
            } // nothing we can do
        }
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
            } // nothing we can do
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
            } // nothing we can do
        }
        if (conexion != null) {
            try {
                conexion.close();
                System.out.println("Conexion devuelta\n");
            } catch (SQLException e) {
            } // nothing we can do
        }
    }//=========================================================================
    //MÉTODO ESPECIAL QUE PERMITE IMPRIMIR LOS RESULTADOS ESTABLECIDOS EN LAS 

    public synchronized ResultSet seleccionar(String sql) {
        try {
            stmt = conexion.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
        }
        return rs;
    }//=========================================================================
    //Método que ejecuta cualquier sentencia de actualización(update, delete, 
    //insert) pasada por parametro.

    public synchronized void ejecutar(String sql) throws SQLException {
        try {
            stmt = conexion.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            throw ex;
        }
    }//=========================================================================

    public Connection getConexion() {
        return conexion;
    }

    //MÉTODO ESPECIAL QUE PERMITE IMPRIMIR LOS RESULTADOS ESTABLECIDOS EN LAS 
    //CONSULTAS SQL EN LOS JTABLES DE LOS FORMULARIOS, EN FORMA GENERAL PARA 
    //CUALQUIER JTABLE Y CUALQUIER CONSULTA
    public synchronized DefaultTableModel dibujarEnTabla(ResultSet rs) {
        DefaultTableModel modelo = null; //Variable "vestido" tabla
        ResultSetMetaData metaDatos;//Informacion de los datos {numero columnas, tipo de la columna}
        Object[] etiquetas;//Sirve para los titulos del JTable
        final int numeroColumnas;
        try {
            metaDatos = rs.getMetaData();
            numeroColumnas = metaDatos.getColumnCount();
            etiquetas = new Object[numeroColumnas];
            // Se obtiene cada una de las etiquetas para cada columna
            for (int i = 0; i < numeroColumnas; i++) {
                // Nuevamente, para ResultSetMetaData la primera columna es la 1.
                etiquetas[i] = metaDatos.getColumnLabel(i + 1).toUpperCase();
            }

            modelo = new DefaultTableModel(
                    new Object[][]{},
                    etiquetas) {
                boolean[] canEdit = new boolean[numeroColumnas];//No editable
                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            };

            while (rs.next()) {
                // Se crea un array que será una de las filas de la tabla.
                Object[] fila = new Object[numeroColumnas]; // Hay tres columnas en la tabla

                // Se rellena cada posición del array con una de las columnas de la tabla en base de datos.
                for (int i = 0; i < numeroColumnas; i++) {
                    fila[i] = rs.getObject(i + 1); // El primer indice en rs es el 1, no el cero, por eso se suma 1.
                }
                // Se añade al modelo la fila completa.
                modelo.addRow(fila);
            }
        } catch (SQLException ex) {

            System.err.println(ex.toString());
        }
        return modelo;
    }//=========================================================================

}
