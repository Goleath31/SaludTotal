/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.util.Objects;
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
@Table(name = "Resultado")
public class ResultadoEntidad implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resultado")
    private Long id_resultado;

    @Column(name = "valor_obtenido", nullable = true, length = 50)
    private String valor_obtenido;

    @Column(name = "observacion", nullable = true, length = 50)
    private String observacion;
    
    @ManyToOne
    @JoinColumn(name = "id_prueba")
    private PruebaEntidad prueba;

    @ManyToOne
    @JoinColumn(name = "id_parametro")
    private ParametroEntidad parametro;
    
    
    public ResultadoEntidad() {
    }

    public PruebaEntidad getPrueba() {
        return prueba;
    }

    public void setPrueba(PruebaEntidad prueba) {
        this.prueba = prueba;
    }

    public ParametroEntidad getParametro() {
        return parametro;
    }

    public void setParametro(ParametroEntidad parametro) {
        this.parametro = parametro;
    }

    
    
    public Long getId_resultado() {
        return id_resultado;
    }

    public void setId_resultado(Long id_resultado) {
        this.id_resultado = id_resultado;
    }

    public String getValor_obtenido() {
        return valor_obtenido;
    }

    public void setValor_obtenido(String valor_obtenido) {
        this.valor_obtenido = valor_obtenido;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id_resultado);
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
        final ResultadoEntidad other = (ResultadoEntidad) obj;
        return Objects.equals(this.id_resultado, other.id_resultado);
    }

    @Override
    public String toString() {
        return "ResultadoEntidad{" + "id_resultado=" + id_resultado + '}';
    }
    
    

}
