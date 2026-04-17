package models;

public class transferobjetos {
    private String expresion;
    private String idAlumno;
    private String idInstituto;
    private Double resultado;

    public transferobjetos() {
    }

    public transferobjetos(String expresion, String idAlumno, String idInstituto, double resultado) {
        this.expresion = expresion;
        this.idAlumno = idAlumno;
        this.idInstituto = idInstituto;
        this.resultado = resultado;
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

    public void setExpresion(String expresion) {
        this.expresion = expresion;
    }

    public void setIdAlumno(String idAlumno) {
        this.idAlumno = idAlumno;
    }

    public void setIdInstituto(String idInstituto) {
        this.idInstituto = idInstituto;
    }

    public void setResultado(Double resultado) {
        this.resultado = resultado;
    }

}