
package tienda.persistencia;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DAO {
    
    //conexcion a la BD
    protected Connection conexion;
    protected ResultSet resultado;
    protected Statement sentencia;
    
    //parametros para la coneccion
    private final String USER = "root";
    private final String PASSWORD = "root";
    private final String DATABASE = "tienda";
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    
    //metodos
    protected void conectarBD() throws Exception {
        try{
            Class.forName(DRIVER);
            String urlDB = "jdbc:mysql://localhost:3306/"+DATABASE+"?useSSL=false";
            conexion = DriverManager.getConnection(urlDB, USER, PASSWORD);
        }catch(ClassNotFoundException | SQLException  ex){
            throw ex;
        }
    }
    
    protected void desconectarBD() throws Exception{
        try{
            //cerrando las conexiones en Statement, ResultSet y Connection
            if(resultado!=null){
                resultado.close();
            }
            if(sentencia!=null){
                sentencia.close();
            }
            if(conexion!=null){
                conexion.close();
            }      
        }catch(Exception e){
            throw e;
        }
    }
    
    protected void insertarModificarEliminar(String sql) throws Exception{
        try{
            //validando
            if(sql == null || sql.trim().isEmpty()){
                throw new Exception("Debe de enviar una sentencia SQL correcta");                
            }
            
            conectarBD();
            sentencia = conexion.createStatement();
            sentencia.executeUpdate(sql);        
        }catch(Exception e){
            throw e;
        }finally{
            desconectarBD();
        }
    }
    
    protected void consutarBD(String sql) throws Exception{
        try{
            //validando
            if(sql == null || sql.trim().isEmpty()){
                throw new Exception("Debe de enviar una Sentencia SQL");
            }
            
            conectarBD();
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sql);
        }catch(Exception e){
            throw e;
        }
    }
    
}
