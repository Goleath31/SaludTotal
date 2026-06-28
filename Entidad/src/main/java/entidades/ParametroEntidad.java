/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author golea
 */
@Entity
@Table(name = "Parametro")
public class ParametroEntidad implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_parametro")
    private Long id_parametro;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "orden", nullable = false)
    private int orden;

    @Column(name = "nota_descriptiva", nullable = false, length = 50)
    private String nota_descriptiva;
    
    @ManyToOne
    @JoinColumn(name = "id_analisis")
    private AnalisisEntidad analisis;

    @ManyToOne
    @JoinColumn(name = "id_unidad_medida")
    private UnidadMedidaEntidad unidadMedida;

    @OneToMany(mappedBy = "parametro" , cascade = CascadeType.ALL)
    private List<RangoEvaluacionEntidad> rangos;
    
    public ParametroEntidad() {
    }

    public AnalisisEntidad getAnalisis() {
        return analisis;
    }

    public void setAnalisis(AnalisisEntidad analisis) {
        this.analisis = analisis;
    }

    public UnidadMedidaEntidad getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(UnidadMedidaEntidad unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public List<RangoEvaluacionEntidad> getRangos() {
        return rangos;
    }

    public void setRangos(List<RangoEvaluacionEntidad> rangos) {
        this.rangos = rangos;
    }
    
    

    public Long getId_parametro() {
        return id_parametro;
    }

    public void setId_parametro(Long id_parametro) {
        this.id_parametro = id_parametro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public String getNota_descriptiva() {
        return nota_descriptiva;
    }

    public void setNota_descriptiva(String nota_descriptiva) {
        this.nota_descriptiva = nota_descriptiva;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id_parametro);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ParametroEntidad other = (ParametroEntidad) obj;
        return Objects.equals(this.id_parametro, other.id_parametro);
    }

    @Override
    public String toString() {
        return "ParametroEntidad{" + "id_parametro=" + id_parametro + '}';
    }

    
}
