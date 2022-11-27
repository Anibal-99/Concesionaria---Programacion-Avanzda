/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Controlador;

import Modelos.Auto;
import Modelos.Color;
import Modelos.Marca;
import Modelos.Modelo;
import Modelos.Pais;
import Modelos.Region;
import java.awt.event.ActionEvent;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Anibal-99
 */
public class GestorVentaTest {

    public GestorVentaTest() {
    }

    @Test
    public void testCalcularMontoConCantidad() {
        Region region = new Region(1, "Extranjero");
        Pais pais = new Pais(1, "Argentina", "ARG", region);
        Marca marca = new Marca(3, "Mercedes", pais, "buena marca");
        Modelo modelo = new Modelo(2, "Benz", 2022, marca);
        Color color = new Color(2, "Amarillo");
        Auto auto = new Auto(2, modelo, 300000, 490000, "nada", color);
        GestorVenta gestor = new GestorVenta();

        int cantidad = 2;
        float expResult = 600000;
        
        float result = gestor.calcularMontoConCantidad(auto, cantidad);
        assertEquals(expResult, result, 0);
    }

    @Test
    public void testCalcularImpuesto() {
        Region region = new Region(1, "Sudamerica");
        Pais pais = new Pais(1, "Argentina", "ARG", region);
        Marca marca = new Marca(3, "Mercedes", pais, "buena marca");
        Modelo modelo = new Modelo(2, "Benz", 2022, marca);
        Color color = new Color(2, "Amarillo");
        Auto auto = new Auto(2, modelo, 300000, 490000, "nada", color);
        GestorVenta gestor = new GestorVenta();

        float monto = 600000;
        float expResult = 60000;
        
        float result = gestor.calcularImpuesto(auto, monto);
        assertEquals(expResult, result, 0);
    }
}
