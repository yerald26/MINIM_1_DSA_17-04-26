package manager;

import models.Operation;

import java.util.List;

public interface MathManager {

    void añadirOperacion(String expresion, String idAlumno, String idInstituto);

    Operation procesarSiguienteOperacion() throws Exception;

    List<Operation> obtenerOperacionesPorInstituto(String idInstituto);

    List<Operation> obtenerOperacionesPorAlumno(String idAlumno);

    List<String> obtenerInstitutosOrdenadosPorOperaciones();

    void clear();
}