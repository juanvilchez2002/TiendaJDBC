
package tienda.servicios;

import java.util.Collection;
import tienda.entidad.Fabricante;
import tienda.entidad.Producto;
import tienda.persistencia.ProductoDAO;


public class ProductoServicio {
    
    private ProductoDAO dao;
    
    public ProductoServicio(){
        this.dao = new ProductoDAO();
    }
    
    public void crearProducto(String nombre, Double precio, Fabricante fabricante) throws Exception{
        try{
            //validamos
            if(nombre == null || nombre.trim().isEmpty()){
                throw new Exception("Debe de ingresar un nombre");
            }
            if(precio == null || precio < 0){
                throw new Exception("Debe de ingresar un precio");            
            }
            if(fabricante == null){                
                throw new Exception("Debe de ingresar un Fabricante");   
            }
            
            //creando el producto nuevo
            Producto producto = new Producto();
            producto.setNombre(nombre);
            producto.setPrecio(precio);
            producto.setFabricante(fabricante);
            
            dao.guardarProducto(producto);            
        }catch(Exception e){
            throw e;
        }
    }
    
    public void editarProducto(Integer codigoProducto, String nombre, Double precio, String codigoFabricante) throws Exception{
        try{
            //validando
            if(codigoProducto == null || codigoProducto < 0){
                throw new Exception("Debe de ingresar un codigo de producto valido");
            }
            if(nombre == null || nombre.trim().isEmpty()){
                throw new Exception("Debe de ingresar un nombre de producto");
            }
            if(precio == null || precio < 0){
                throw new Exception("Debe de ingresar un precio valido");
            }
            if(codigoProducto == null || codigoProducto < 0){
                throw new Exception("Debe de ingresar un codigo de producto valido");
            }
            
            //buscamos el producto 
            Producto producto = dao.buscarProductoPorId(codigoProducto);
                    
            //actualizamos los valores        
            producto.setNombre(nombre);
            producto.setPrecio(precio);
            
            Fabricante fabricante = dao.getFabricanteServicio().buscarFabricantePorId(codigoProducto);
            producto.setFabricante(fabricante);
            
            dao.modificarProducto(producto);            
        }catch(Exception e){
            throw e;
        }
    }
    
    public void listarProducto() throws Exception{
        try{
            //llamamos al metodo listar
            Collection<Producto> producto = dao.listarProductos();
            
            //imprimimos
            if(producto.isEmpty()){
                throw new Exception("No hay productos");
            }else{
                System.out.println("Codigo\t\t Producto");
                for(Producto product: producto){
                    System.out.print(product.getCodigo()+" \t\t");
                    System.out.print(product.getNombre());
                    System.out.println("");
                }
            }
            
        }catch(Exception e){
            throw e;
        }
    }
    
    public void listarProductoPrecio() throws Exception{
        try{
            //llamamos al metodo listar
            Collection<Producto> producto = dao.listarProductos();
            
            //imprimimos
            if(producto.isEmpty()){
                throw new Exception("No hay productos");
            }else{
                System.out.println("Codigo\t\t Producto\t\t Precio");
                for(Producto product: producto){
                    System.out.print(product.getCodigo()+" \t\t");
                    System.out.print(product.getNombre()+" \t\t");
                    System.out.print(product.getPrecio()+" \t\t");
                    System.out.println("");
                }
            }            
        }catch(Exception e){
            throw e;
        }
    }
    
    public void listarProductoPorRango(Double precio1, Double precio2)throws Exception{
        try{
            //validamos los precios
            if(precio1<0 || precio2<0){
                throw new Exception("Los precios deben ser positivos");
            }
            
            Double min = Math.min(precio1, precio2);
            Double max = Math.max(precio1, precio2);
            
            Collection<Producto> producto = dao.listaProductosPorRangos(min, max);
            
            //imprimimos
            if(producto.isEmpty()){
                throw new Exception("No hay productos");
            }else{
                System.out.println("Codigo\t\t Producto\t\t Precio");
                for(Producto product: producto){
                    System.out.print(product.getCodigo()+" \t\t");
                    System.out.print(product.getNombre()+" \t\t");
                    System.out.print(product.getPrecio()+" \t\t");
                    System.out.println("");
                }
            }            
        }catch(Exception e){
            throw e;
        }
    }
    
    public void listarProductoPorNombre(String nombre) throws Exception{
        try{
            if(nombre == null || nombre.trim().isEmpty()){
                throw new Exception("Debe de ingresar un nombre");
            }
            
            Collection<Producto> producto = dao.listarProductosPorNombre(nombre);
            
            //imprimimos
            if(producto.isEmpty()){
                throw new Exception("No hay productos");
            }else{
                System.out.println("Codigo\t\t Producto\t\t Precio");
                for(Producto product: producto){
                    System.out.print(product.getCodigo()+" \t\t");
                    System.out.print(product.getNombre()+" \t\t");
                    System.out.print(product.getPrecio()+" \t\t");
                    System.out.println("");
                }
            }
        }catch(Exception e){
            throw e;
        }
        
    }
    
    public void listarProductoBarato()throws Exception{
        try{
            Collection<Producto> producto = dao.listarProductosMasBaratos();

            //imprimimos
            if(producto.isEmpty()){
                throw new Exception("No hay productos");
            }else{
                System.out.println("Codigo\t\t Producto\t\t Precio");
                for(Producto product: producto){
                    System.out.print(product.getCodigo()+" \t\t");
                    System.out.print(product.getNombre()+" \t\t");
                    System.out.print(product.getPrecio()+" \t\t");
                    System.out.println("");
                }
            }            
        }catch(Exception e){
            throw e;
        }
    }
}
