
package tienda.servicios;

import java.util.Collection;
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
    
    public void listarFabricante() throws Exception{
        try{
            //llamamos al metodo listar
            Collection<Fabricante> fabricante = dao.listaFabricante();
            
            //imprimimos
            if(fabricante.isEmpty()){
                throw new Exception("No hay productos");
            }else{
                System.out.println("Codigo\t\t Producto");
                for(Fabricante fabricant: fabricante){
                    System.out.print(fabricant.getCodigo()+" \t\t");
                    System.out.print(fabricant.getNombre());
                    System.out.println("");
                }
            }
            
        }catch(Exception e){
            throw e;
        }
    }
}
