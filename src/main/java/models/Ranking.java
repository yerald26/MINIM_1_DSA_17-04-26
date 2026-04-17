package models;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement // <--- ESTO ES LO QUE LE DICE A MOXy: "Empieza el JSON aquí"
public class Ranking {
    private List<String> lista; // Aquí guardaremos los nombres de los institutos

    public Ranking() {} // Constructor vacío necesario

    public Ranking(List<String> lista) {
        this.lista = lista;
    }

    public List<String> getLista() { return lista; }
    public void setLista(List<String> lista) { this.lista = lista; }
}