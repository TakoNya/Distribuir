/* MySQL código
drop database if exists prueba;
create database prueba;

use prueba;

create table persona (
id int primary key,
nombre varchar(64),
apellido varchar(64),
fecha date );

insert into persona value(1, 'Juan', 'Perez', '2017-08-17');
insert into persona value(2, 'Ana', 'Maria', '2017-01-10');
insert into persona value(3, 'Elena', 'Ortiz', '2010-01-01');
insert into persona value(4, 'Ivan', 'Valdes', '2015-11-11');
*/
package tutor;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class ConectarMySQL {

    
    public static void main(String[] args) {
        testMySQLDriver();
        try{
            String url = "jdbc:mysql://localhost:3306/prueba";
            String username = "root";
            String password = "1234";
            
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet rs= statement.executeQuery("SELECT * FROM persona");
            
            while (rs.next()){
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                Date fecha = rs.getDate("fecha");
                JOptionPane.showMessageDialog(null, String.format("%d, %s, %s, %s", id, nombre, apellido, fecha));
                
            }
            rs.close();
            statement.close();
            connection.close();
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }
    private static void testMySQLDriver(){
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, "Error, no se ha podido cargar MySQL JDBC Driver");
        }
    }
    
}
