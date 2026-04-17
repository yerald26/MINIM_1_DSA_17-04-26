package models;

public class Operation {

    private String expresion;
    private String idAlumno;
    private String idInstituto;

    private Double resultado;

    // Constructor
    public Operation(String expresion, String idAlumno, String idInstituto) {
        this.expresion = expresion;
        this.idAlumno = idAlumno;
        this.idInstituto = idInstituto;
        this.resultado = null;
    }

    // GETTERS

    public String getExpresion() {
        return expresion;
    }

    public String getIdAlumno() {
        return idAlumno;
    }

    public String getIdInstituto() {
        return idInstituto;
    }

    public Double getResultado() {
        return resultado;
    }

    // SETTERS

    public void setResultado(Double resultado) {
        this.resultado = resultado;
    }
}