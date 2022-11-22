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

    @Test
    public void testGetColorById() throws Exception {
        Color c = new Color(7, "Amarillo");
        ColorDao cDao = new ColorDao();
        Color result = cDao.getColorById(7);
        assertEquals(result.nombre, "Amarillo");
        assertEquals(result.id, 7);
    }

}
