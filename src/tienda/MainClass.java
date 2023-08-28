/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tienda;

import tienda.servicios.FabricanteServicio;
import tienda.servicios.ProductoServicio;


public class MainClass {


    public static void main(String[] args) throws Exception {
        
        FabricanteServicio fs = new FabricanteServicio();
        ProductoServicio ps = new ProductoServicio();
        ps.listarProductoBarato();
    }
    
}
