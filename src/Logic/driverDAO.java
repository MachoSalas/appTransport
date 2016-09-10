/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

/**
 *
 * @author Alejandro
 */
import java.sql.ResultSet;
import java.sql.SQLException;

public class driverDAO extends Conexion {

    public void save(driver Driver) {
        try {
            conectarBD();
            obj_Procedimiento = getConexion().prepareCall("call driverSave(?,?,?,?)");
            obj_Procedimiento.setInt(1, Driver.getId());
            obj_Procedimiento.setString(2, Driver.getName());
            obj_Procedimiento.setString(3, Driver.getDate_birthday());
            obj_Procedimiento.setString(4, Driver.getType_license());
            obj_Procedimiento.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            desconectarBD();
        }
    }

    public driver searchDriver(int idDriver) {
        driver Driver = null;
        try {
            conectarBD();
            obj_Procedimiento = getConexion().prepareCall("CALL driverSearch(?)");
            obj_Procedimiento.setInt(1, idDriver);
            rs = obj_Procedimiento.executeQuery();
            if (rs.next()) {
                Driver = new driver();
                Driver.setId(rs.getInt(1));
                Driver.setName(rs.getString(2));
                Driver.setDate_birthday(rs.getString(3));
                Driver.setType_license(rs.getString(4));
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            desconectarBD();
        }
        return Driver;
    }

    public ResultSet loadDriver() {
        try {
            obj_Procedimiento = getConexion().prepareCall("CALL driverSearchAll");
            rs = obj_Procedimiento.executeQuery();

        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return rs;
    }

    public void editDriver(driver Driver) {
        try {
            conectarBD();
            obj_Procedimiento = getConexion().prepareCall("call driverEdit(?,?,?,?)");
            obj_Procedimiento.setInt(1, Driver.getId());
            obj_Procedimiento.setString(2, Driver.getName());
            obj_Procedimiento.setString(3, Driver.getDate_birthday());
            obj_Procedimiento.setString(4, Driver.getType_license());
            obj_Procedimiento.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            desconectarBD();
        }
    }

    public boolean deleteDriver(int idDriver) {
        boolean rpta = false;
        try {
            conectarBD();
            obj_Procedimiento = getConexion().prepareCall("CALL driverDelete(?)");
            obj_Procedimiento.setInt(1, idDriver);
            rpta = obj_Procedimiento.executeUpdate() == 1;

        } catch (SQLException ex) {
            desconectarBD();
        } catch (Exception e) {
            desconectarBD();
        }
        return rpta;
    }
}
