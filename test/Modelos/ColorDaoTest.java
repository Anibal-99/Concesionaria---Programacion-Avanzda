/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Modelos;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.jupiter.api.Assertions;

/**
 *
 * @author Anibal-99
 */
public class ColorDaoTest {

    public ColorDaoTest() {
    }

    /*
    @Test
    public void testGetColor() throws Exception {
        System.out.println("getColor");
        ColorDao instance = new ColorDao();
        ArrayList<Color> expResult = null;
        ArrayList<Color> result = instance.getColor();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
     */
    @Test
    public void testGetColorById() throws Exception {
        System.out.println("getColorById");
        Color c = new Color(7, "Amarillo");
        int id = 7;
        
        ColorDao cDao = new ColorDao();
        Color result = cDao.getColorById(id);
        System.out.println(c.nombre);
        System.out.println(result.nombre);
        assertEquals(c.nombre, result.nombre);
        //fail("The test case is a prototype.");
    }

}
