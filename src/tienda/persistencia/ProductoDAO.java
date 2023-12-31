
package tienda.persistencia;

import java.util.ArrayList;
import java.util.Collection;
import tienda.entidad.Fabricante;
import tienda.entidad.Producto;
import tienda.servicios.FabricanteServicio;


public class ProductoDAO extends DAO{
    
    //instanciando service de Fabricante
    private final FabricanteServicio fabricanteServicio;
    
    //inicializando fs en el constructor
    public ProductoDAO(){
        this.fabricanteServicio = new FabricanteServicio();
    }
    
    //creo un getter de FabricanteServicio para poder llamar metodos del service
    public FabricanteServicio getFabricanteServicio() {
        return fabricanteServicio;
    }    
    
    //metodos
    public void guardarProducto(Producto producto) throws Exception{
        try{
            //validando
            if(producto == null){
                throw new Exception("Debe de ingresar un Producto");
            }
            
            String consultaSQL = "INSERT INTO Producto (nombre, precio, codigo_fabricante) VALUES('"+producto.getNombre()+"', "
                    +producto.getPrecio()+", "+producto.getFabricante().getCodigo()+");";
            
            insertarModificarEliminar(consultaSQL);
        }catch(Exception e){
            throw e;
        }finally{
            desconectarBD();
        }
    }
    
    public void modificarProducto(Producto producto) throws Exception{
        try{
            //validar
            if(producto == null){
                throw new Exception("Debe de ingresar un producto");
            }
            
            String consultaSQLNombre = "";
            int bandera = 0;
            
            if(!producto.getNombre().isEmpty()){
                consultaSQLNombre = " nombre='"+producto.getNombre()+"' ";
                bandera = 1;
            }
            
            String consultaSQLPrecio = "";
            
            if(producto.getPrecio()>=0){
                consultaSQLPrecio = " precio="+producto.getPrecio();
                
                if(bandera == 1){
                    consultaSQLPrecio = ", "+consultaSQLPrecio;
                }
                
                bandera = 2;
            }
            
            String consultaSQLFabricante = "";
            
            if(producto.getFabricante() != null){
                consultaSQLFabricante = " codigo_fabricante="+producto.getFabricante().getCodigo();
                
                if(bandera == 2){
                    consultaSQLFabricante = ", "+consultaSQLFabricante;
                }
            }
            
            if(producto.getFabricante() == null && producto.getPrecio() == -1.0 && producto.getNombre().isEmpty()){
                System.out.println("No hay datos a actualizar");
            }else{
                String consultaSQL = "UPDATE Producto SET "+
                        consultaSQLNombre + consultaSQLPrecio + consultaSQLFabricante +                    
                        " WHERE codigo = "+producto.getCodigo()+";";

                System.out.println(consultaSQL);
                insertarModificarEliminar(consultaSQL);
            }
            
        }catch(Exception e){
            throw e;
        }finally{
            desconectarBD();
        }
    }
    
    public void eliminarProducto(Integer codigo) throws Exception{
        try{
            if(codigo == null || codigo == 0){
                throw new Exception("Debe de ingresar un codigo correcto");
            }
            
            String consultaSQL = "DELETE FROM Producto WHERE codigo = "+codigo+";";
            insertarModificarEliminar(consultaSQL);
        }catch(Exception e){
            throw e;
        }finally{
            desconectarBD();
        }
    }
    
    public Producto buscarProductoPorId(Integer codigo) throws Exception{
        try{
            //validando
            if(codigo == null || codigo == 0){
                throw new Exception("Debe de ingresar un codigo correcto");
            }
            
            String consultaSQL = "SELECT * FROM Producto WHERE codigo = "+codigo+";";
            
            consutarBD(consultaSQL);
            Producto producto = null;
            
            while(resultado.next()){
                producto = new Producto();
                
                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getDouble(3));
                
                Fabricante fabricante = fabricanteServicio.buscarFabricantePorId(resultado.getInt(4));
                producto.setFabricante(fabricante);                
            }
            
            desconectarBD();
            return producto;            
        }catch(Exception e){
            desconectarBD();
            throw e;
        }
    }
    
    public Collection<Producto> listarProductos() throws Exception{
        try{
            String consultaSQL = "SELECT * FROM Producto;";
            
            consutarBD(consultaSQL);
            Producto producto = null;
            Collection<Producto> productos = new ArrayList(); 
            
            while(resultado.next()){
                producto = new Producto();
                
                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getDouble(3));
                
                Fabricante fabricante = fabricanteServicio.buscarFabricantePorId(resultado.getInt(4));
                producto.setFabricante(fabricante);     
                
                productos.add(producto);
            }
            
            desconectarBD();
            return productos;
        }catch(Exception e){
            e.printStackTrace();
            desconectarBD();
            throw e;
        }
    }
    
    public Collection<Producto> listaProductosPorRangos(Double min, Double max) throws Exception{
        try{
                        
            String consultaSQL = "SELECT * FROM Producto WHERE Precio BETWEEN "+min+" AND "+max+";";
            
            consutarBD(consultaSQL);
            
            Producto producto = null;
            Collection<Producto> productos = new ArrayList(); 
            
            while(resultado.next()){
                producto = new Producto();
                
                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getDouble(3));
                
                Fabricante fabricante = fabricanteServicio.buscarFabricantePorId(resultado.getInt(4));
                producto.setFabricante(fabricante);     
                
                productos.add(producto);
            }
            
            desconectarBD();
            return productos;
        }catch(Exception e){            
            e.printStackTrace();
            desconectarBD();
            throw e;
        }        
    }
    
    public Collection<Producto> listarProductosPorNombre(String nombre) throws Exception{
        try{
            String consultaSQL = "SELECT * FROM Producto WHERE nombre LIKE '%"+nombre+"%';";
            
            consutarBD(consultaSQL);
            
            Producto producto = null;
            Collection<Producto> productos = new ArrayList(); 
            
            while(resultado.next()){
                producto = new Producto();
                
                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getDouble(3));
                
                Fabricante fabricante = fabricanteServicio.buscarFabricantePorId(resultado.getInt(4));
                producto.setFabricante(fabricante);     
                
                productos.add(producto);
            }
            
            desconectarBD();
            return productos;
        }catch(Exception e){
            e.printStackTrace();
            desconectarBD();
            throw e;
        }
    }
    
    public Collection<Producto> listarProductosMasBaratos()throws Exception{
        try{
            String consultaSQL = "SELECT * FROM Producto WHERE precio"+
                    "= (SELECT MIN(precio) FROM Producto)";
            
            consutarBD(consultaSQL);
            
            Producto producto = null;
            Collection<Producto> productos = new ArrayList(); 
            
            while(resultado.next()){
                producto = new Producto();
                
                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getDouble(3));
                
                Fabricante fabricante = fabricanteServicio.buscarFabricantePorId(resultado.getInt(4));
                producto.setFabricante(fabricante);     
                
                productos.add(producto);
            }
            
            desconectarBD();
            return productos;            
        }catch(Exception e){
            e.printStackTrace();
            desconectarBD();
            throw e;
        }
    }
    
}
