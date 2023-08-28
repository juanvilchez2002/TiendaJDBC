
package tienda.persistencia;

import java.util.ArrayList;
import java.util.Collection;
import tienda.entidad.Fabricante;

public final class FabricanteDAO extends DAO{
    
    //metodos
    public void guardarFabricante(Fabricante fabricante) throws Exception{
        try{
            //validando
            if(fabricante == null){
                throw new Exception("Debe ingresar el fabricante");
            }
            
            String consulta = "INSERT INTO Fabricante (nombre) "+
                    "VALUES('"+fabricante.getNombre()+"');";
            
            insertarModificarEliminar(consulta);            
        }catch(Exception e){
            throw e;
        }finally{
            desconectarBD();
        }
    }
    
    public void modificarFabricante(Fabricante fabricante) throws Exception{
        try{
            if(fabricante == null){
                throw new Exception("Debe de ingresar un usuario");
            }
            
            String consulta = "UPDATE Fabricante SET nombre = '"+fabricante.getNombre()+
                    "' WHERE codigo="+fabricante.getCodigo();
            
            insertarModificarEliminar(consulta);
        }catch(Exception e){
            throw e;
        }finally{
            desconectarBD();
        }
    }
    
    public void eliminarFabricante(Integer id) throws Exception{
        try{
            //validando
            if(id == null || id == 0){
                throw new Exception("Debe de ingresar un código valido");
            }
            
            String consulta = "DELETE FROM Fabricante WHERE codigo ="+id+";";
            insertarModificarEliminar(consulta);
        }catch(Exception e){
            throw e;
        }finally{
            desconectarBD();
        }
    }
    
    public Fabricante buscarFabricantePorId(Integer id)throws Exception{
        try{
            if(id==null || id == 0){
                throw new Exception("Debe de Ingresar un codigo correcto");
            }
            String consultaSQL = "SELECT * FROM Fabricante WHERE codigo="+id+";";
            
            consutarBD(consultaSQL);
            Fabricante fabricante  = null;
            
            while(resultado.next()){
                fabricante = new Fabricante();
                
                fabricante.setCodigo(resultado.getInt(1));
                fabricante.setNombre(resultado.getString(2));
            }
            
            desconectarBD();
            return fabricante;
        }catch(Exception e){
            desconectarBD();
            throw e;
        }
    }
    
        public Fabricante buscarFabricantePorNombre(String nombre)throws Exception{
        try{
            if(nombre ==null || nombre.trim().isEmpty()){
                throw new Exception("Debe de Ingresar un nobre");
            }
            String consultaSQL = "SELECT * FROM Fabricante WHERE nombre LIKE '%"+nombre+"%';";
            
            consutarBD(consultaSQL);
            Fabricante fabricante  = null;
            
            while(resultado.next()){
                fabricante = new Fabricante();
                
                fabricante.setCodigo(resultado.getInt(1));
                fabricante.setNombre(resultado.getString(2));
            }
            
            desconectarBD();
            return fabricante;
        }catch(Exception e){
            desconectarBD();
            throw e;
        }
    }
        
    public Collection<Fabricante> listaFabricante() throws Exception{
        try{
            String consultaSQL = "SELECT * FROM Fabricante;";
            
            consutarBD(consultaSQL);
            Fabricante fabricante = null;
            Collection<Fabricante> fabricantes = new ArrayList();
            
            while(resultado.next()){
                fabricante = new Fabricante();
                
                fabricante.setCodigo(resultado.getInt(1));
                fabricante.setNombre(resultado.getString(2));
                
                fabricantes.add(fabricante);
            }
            
            desconectarBD();
            return fabricantes;
        }catch(Exception e){
            e.printStackTrace();
            desconectarBD();
            throw e;
        }
    }
}
