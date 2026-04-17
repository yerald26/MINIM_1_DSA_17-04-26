package manager;

import models.Operation;
import org.apache.log4j.Logger;

import java.util.*;

public class MathManagerImpl implements MathManager {
    private static MathManager instance;
    final static Logger log = Logger.getLogger(MathManagerImpl.class.getName());

    // Estructuras de datos
    private Queue<Operation> colaPendientes;
    private Map<String, List<Operation>> operacionesxAlumno;
    private Map<String, List<Operation>> operacionesxInsti;
    private Map<String, Integer> contOperacionesInsti;
    private ReversePolishNotation calculador;


    // Singleton
    private MathManagerImpl() {
        this.colaPendientes = new LinkedList<>();
        this.operacionesxAlumno = new HashMap<>();
        this.operacionesxInsti = new HashMap<>();
        this.contOperacionesInsti = new HashMap<>();
        this.calculador = new ReversePolishNotationImpl();
    }

    public static MathManager getInstance() {
        if (instance == null) instance = new MathManagerImpl();
        return instance;
    }

    @Override
    public void añadirOperacion(String expresion, String idAlumno, String idInstituto) {
        log.info("INICIO añadirOperacion: expresion=" + expresion + ", alumno=" + idAlumno + ", inst=" + idInstituto);

        Operation op = new Operation(expresion, idAlumno, idInstituto);

        // Guardamos en la cola para procesar luego
        this.colaPendientes.add(op);

        // Organizamos en los mapas para busquedas rapidas
        this.operacionesxAlumno.computeIfAbsent(idAlumno, k -> new ArrayList<>()).add(op);
        this.operacionesxInsti.computeIfAbsent(idInstituto, k -> new ArrayList<>()).add(op);

        // Sumamos 1 al contador del instituto para el ranking
        int total = this.contOperacionesInsti.getOrDefault(idInstituto, 0);
        this.contOperacionesInsti.put(idInstituto, total + 1);

        log.info("FIN añadirOperacion: Operación encolada correctamente.");
    }

    @Override
    public Operation procesarSiguienteOperacion() throws Exception {
        log.info("INICIO procesarSiguienteOperacion");

        Operation op = colaPendientes.poll(); // Extrae el primero que llegó

        if (op == null) {
            log.warn("No hay operaciones para procesar");
            return null;
        }

        try {
            // Aqui llamamos a la logica del reverse polish notation
            double resultado = calculador.process(op.getExpresion());
            op.setResultado(resultado);
            log.info("FIN procesarSiguienteOperacion: Resultado=" + resultado);
            return op;
        } catch (Exception e) {
            log.error("ERROR fatal procesando Reverse Polish Notation: " + op.getExpresion(), e);
            throw e;
        }
    }

    @Override
    public List<Operation> obtenerOperacionesPorInstituto(String idInstituto) {
        log.info("Consulta operaciones instituto: " + idInstituto);
        return operacionesxInsti.getOrDefault(idInstituto, new ArrayList<>());
    }

    @Override
    public List<Operation> obtenerOperacionesPorAlumno(String idAlumno) {
        log.info("Consulta operaciones alumno: " + idAlumno);
        return operacionesxAlumno.getOrDefault(idAlumno, new ArrayList<>());
    }

    @Override
    public List<String> obtenerInstitutosOrdenadosPorOperaciones() {
        log.info("INICIO ranking institutos");
        if (this.contOperacionesInsti == null) return new ArrayList<>();

        List<String> listaOrdenada = new ArrayList<>(this.contOperacionesInsti.keySet());

        // Ordenamos de mayor a menor según el contador
        listaOrdenada.sort((inst1, inst2) -> {
            Integer v1 = contOperacionesInsti.getOrDefault(inst1,0);
            Integer v2 = contOperacionesInsti.getOrDefault(inst2,0);
            return v2.compareTo(v1);
        });

        log.info("FIN ranking: " + listaOrdenada.size() + " institutos listados.");
        return listaOrdenada;
    }

    @Override
    public void clear() {
        this.colaPendientes.clear();
        this.operacionesxAlumno.clear();
        this.operacionesxInsti.clear();
        this.contOperacionesInsti.clear();
    }
}