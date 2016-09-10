/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.sql.SQLException;
import java.sql.ResultSet;
/**
 *
 * @author Alejandro
 */
public class vehicleDAO extends Conexion {
      public void save(vehicle Vehicle) {
        try {
            conectarBD();
            obj_Procedimiento = getConexion().prepareCall("call vehicleSave(?,?,?,?)");
            obj_Procedimiento.setInt(1, Vehicle.getEnrollment());
            obj_Procedimiento.setString(2, Vehicle.getModel());
            obj_Procedimiento.setString(3, Vehicle.getColor());
            obj_Procedimiento.setInt(4, Vehicle.getCapacity_passenger());
            obj_Procedimiento.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            desconectarBD();
        }
    }  
}
