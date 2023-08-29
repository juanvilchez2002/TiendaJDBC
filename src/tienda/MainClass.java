/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tienda;

import java.util.Scanner;
import tienda.entidad.Fabricante;
import tienda.entidad.Producto;
import tienda.servicios.FabricanteServicio;
import tienda.servicios.ProductoServicio;


public class MainClass {


    public static void main(String[] args) throws Exception {
        
        FabricanteServicio fs = new FabricanteServicio();
        ProductoServicio ps = new ProductoServicio();
        
        Scanner consola = new Scanner(System.in).useDelimiter("\n");
        boolean flag = true;
        int op = 0;
        
        try{
            do{
                System.out.println("---- Menu ----");
                System.out.println("1. Lista nombre de los Productos. ");
                System.out.println("2. Listar nombre y precios de los Productos. ");
                System.out.println("3. Listar productos por rango. ");
                System.out.println("4. Buscar productos por nombre. ");
                System.out.println("5. Listar producto mas barato. ");
                System.out.println("6. Ingresar un nuevo producto. ");
                System.out.println("7. Ingresar un nuevo fabricante. ");
                System.out.println("8. Editar un producto. ");
                System.out.println("9. Salir. ");
                System.out.print("Elija una opcion: ");
                op = consola.nextInt();
                
                switch(op){
                    case 1:
                        System.out.println("\n--- Lista de Productos ---");
                        ps.listarProducto();
                        break;
                        
                    case 2:
                        System.out.println("\n--- Lista de Productos con precio ---");
                        ps.listarProductoPrecio();
                        break;
                        
                    case 3:
                        System.out.print("Ingrese primer precio: ");
                        Double precio1 = consola.nextDouble();
                        System.out.print("Ingrese segundo precio: ");
                        Double precio2 = consola.nextDouble();
                        
                        System.out.println("\n--- Lista de Productos por Rangos ---");
                        ps.listarProductoPorRango(precio1, precio2);
                        break;
                        
                    case 4:
                        System.out.print("Ingrese un nombre: ");
                        String nombre = consola.nextLine();
                        
                        System.out.println("\n--- Lista de Productos por Nombre ---");
                        ps.listarProductoPorNombre(nombre);
                        break;
                        
                    case 5:
                        System.out.println("\n--- Lista de Productos mas baratos---");
                        ps.listarProductoBarato();
                        break;
                        
                    case 6:             
                        consola.nextLine();
                        System.out.print("Ingrese nombre del nuevo producto: ");
                        String nombreProducto = consola.nextLine();
                        System.out.print("Ingrese precio del nuevo producto: ");
                        Double precioProducto = consola.nextDouble();   
                        
                        
                        System.out.println("\n--- Lista de Fabricante ---");
                        fs.listarFabricante();
                        System.out.println("");
                        System.out.print("Ingrese codigo del fabricante: ");
                        Integer codigoFabricante = consola.nextInt();
                        
                        Fabricante fabricante = fs.buscarFabricantePorId(codigoFabricante);
                        
                        ps.crearProducto(nombreProducto, precioProducto, fabricante);
                        break;
                        
                    case 7:
                       System.out.print("Ingrese nombre del nuevo Fabricante: ");
                       String nombreFabricante = consola.nextLine();
                       fs.crearFabricante(nombreFabricante);
                       break;
                       
                    case 8:
                        System.out.println("\n--- Listado de Productos ---");
                        ps.listarProductoTotal();
                        System.out.println("");
                        System.out.print("Ingrese el codigo de producto a editar: ");
                        Integer codigoProducto = consola.nextInt();
                        consola.nextLine();
                        System.out.print("Ingrese el nombre de producto a editar: ");
                        String nombreProductos = consola.nextLine();
                        System.out.print("Ingrese el precio de producto a editar: ");
                        String precioProductosString = consola.nextLine();
                        
                        Double precioProductos = 0.0;
                        
                        if(precioProductosString.isEmpty()){
                            precioProductos = -1.0;
                        }else{
                            precioProductos = Double.valueOf(precioProductosString);
                        }
                        
                        System.out.println("\n--- Lista de Fabricante ---");
                        fs.listarFabricante();
                        System.out.println("");
                        System.out.print("Ingrese codigo del fabricante del producto a editar: ");    
                        String codigoFabricantesString = consola.nextLine();
                        
                        Integer codigoFabricantes = 0;
                        
                        if(codigoFabricantesString == ""){
                            codigoFabricantes = -1;
                        }else{
                            codigoFabricantes = Integer.valueOf(codigoFabricantesString);
                        }
                        
                        System.out.println("");
                        
                        ps.editarProducto(codigoProducto, nombreProductos, precioProductos, codigoFabricantes);
                                               
                        System.out.println("");
                        ps.listarProductoTotal();
                        System.out.println("");
                        break;
                        
                    case 9:
                        flag=false;
                        break;
                    
                    default:
                        System.out.println("Ingrese una opcion valida!!!");
                }
                
            
        
            }while(flag);
        }catch(Exception e){
            throw e;
        }
    }
    
}
