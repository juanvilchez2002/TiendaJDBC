
package tienda.servicios;

import tienda.entidad.Fabricante;
import tienda.persistencia.FabricanteDAO;

public class FabricanteServicio {
    
    //creamos una instancia a FabricanteDAO
    public FabricanteDAO dao;
    
    //inicializamos el constructor
    public FabricanteServicio(){
        this.dao = new FabricanteDAO();
    }
    
    //metodos
    public void crearFabricante(String fabricante) throws Exception{
       try{
           //validaciones
           if(fabricante == null || fabricante.trim().isEmpty()){
               throw new Exception("Debe de ingresar un fabricante");
           }
           
           //creando el nuevo fabricante y guuardandolo en la BD
           Fabricante nuevoFabricante = new Fabricante();
           nuevoFabricante.setNombre(fabricante);
           dao.guardarFabricante(nuevoFabricante);           
       }catch(Exception e){
           throw e;
       }
    }
    
    public Fabricante buscarFabricantePorId(Integer codigo) throws Exception{
        try{
            //validando
            if(codigo == null || codigo == 0){
                throw new Exception("Debe de ingresar un codigo valido");
            }
            
            Fabricante fabricante = dao.buscarFabricantePorId(codigo);
            return fabricante;            
        }catch(Exception e){
            throw e;
        }
    }
    
    
}
