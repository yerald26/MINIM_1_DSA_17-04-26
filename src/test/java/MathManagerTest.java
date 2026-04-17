import manager.ReversePolishNotation;
import manager.ReversePolishNotationImpl;
import manager.MathManager;
import manager.MathManagerImpl;
import manager.ReversePolishNotationImpl;
import models.Operation;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;


public class MathManagerTest {

    private MathManager manager;

    @Before
    public void setUp() {
        manager = MathManagerImpl.getInstance();
    }

    @After
    public void tearDown() {
        // Limpiamos los datos tras cada prueba
        manager.clear();
    }

    @Test
    public void testAñadirYProcesarOperacion() throws Exception {
        manager.añadirOperacion("3 4 +", "Alumno1", "InstitutoA");

        Operation op = manager.procesarSiguienteOperacion();

        Assert.assertNotNull(op);
        Assert.assertEquals("Alumno1", op.getIdAlumno());
        Assert.assertEquals(Double.valueOf(7.0), op.getResultado());
    }

    @Test
    public void testRankingInstitutos() {
        manager.añadirOperacion("1 1 +", "A1", "InstitutoA");
        manager.añadirOperacion("2 2 +", "A2", "InstitutoA");
        manager.añadirOperacion("3 3 +", "A3", "InstitutoA");

        manager.añadirOperacion("4 4 +", "B1", "InstitutoB");

        manager.añadirOperacion("5 5 +", "C1", "InstitutoC");
        manager.añadirOperacion("6 6 +", "C2", "InstitutoC");

        List<String> ranking = manager.obtenerInstitutosOrdenadosPorOperaciones();

        Assert.assertEquals(3, ranking.size());
        Assert.assertEquals("InstitutoA", ranking.get(0));
        Assert.assertEquals("InstitutoC", ranking.get(1));
        Assert.assertEquals("InstitutoB", ranking.get(2));
    }
}
