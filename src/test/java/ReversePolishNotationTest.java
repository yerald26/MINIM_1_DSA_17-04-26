import manager.MathManager;
import manager.MathManagerImpl;
import manager.ReversePolishNotationImpl;
import models.Operation;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import manager.ReversePolishNotation;
import manager.ReversePolishNotationImpl;


public class ReversePolishNotationTest {

    private ReversePolishNotationImpl calculador;

    @Before
    public void setUp() {
        this.calculador = new ReversePolishNotationImpl();
    }

    @Test
    public void testSumaSimple() throws Exception {
        Assert.assertEquals(7.0, calculador.process("3 4 +"), 0.001);
    }

    @Test
    public void testExpresionCompleja() throws Exception {
        Assert.assertEquals(4.0, calculador.process("5 1 + 2 * 3 /"), 0.001);
    }

    @Test(expected = Exception.class)
    public void testDivisionPorCero() throws Exception {
        calculador.process("5 0 /");
    }

}