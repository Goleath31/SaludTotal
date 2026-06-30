/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author golea
 */
@Entity
@Table(name = "Rango_evaluacion")
public class RangoEvaluacionEntidad implements Serializable {

   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rango_evaluacion")
    private Long id;

    @Column(name = "rango_inicial", nullable = false)
    private double rangoInicial;

    @Column(name = "rango_final", nullable = false)
    private double rangoFinal;

    @Column(name = "edad_inicial", nullable = false)
    private int edadInicial;

    @Column(name = "edad_final", nullable = false)
    private int edadFinal;

    @Column(name = "sexo", nullable = false, length = 10)
    private String sexo;

    
    @ManyToOne
    @JoinColumn(name = "id_parametro")
    private ParametroEntidad parametro;

    @Column(name = "fecha_eliminacion", nullable = true)
    private java.util.Date fechaEliminacion;

    public RangoEvaluacionEntidad() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getRangoInicial() {
        return rangoInicial;
    }

    public void setRangoInicial(double rangoInicial) {
        this.rangoInicial = rangoInicial;
    }

    public double getRangoFinal() {
        return rangoFinal;
    }

    public void setRangoFinal(double rangoFinal) {
        this.rangoFinal = rangoFinal;
    }

    public int getEdadInicial() {
        return edadInicial;
    }

    public void setEdadInicial(int edadInicial) {
        this.edadInicial = edadInicial;
    }

    public int getEdadFinal() {
        return edadFinal;
    }

    public void setEdadFinal(int edadFinal) {
        this.edadFinal = edadFinal;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public ParametroEntidad getParametro() {
        return parametro;
    }

    public void setParametro(ParametroEntidad parametro) {
        this.parametro = parametro;
    }

    public java.util.Date getFechaEliminacion() {
        return fechaEliminacion;
    }

    public void setFechaEliminacion(java.util.Date fechaEliminacion) {
        this.fechaEliminacion = fechaEliminacion;
    }

    @Override
    public String toString() {
        return "RangoEvaluacionEntidad{" + "id=" + id + '}';
    }

    
    
}
