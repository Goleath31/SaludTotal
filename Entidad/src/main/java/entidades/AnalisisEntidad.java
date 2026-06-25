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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author golea
 */
@Entity
@Table(name = "Analisis")
public class AnalisisEntidad implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_analisis")
    private Long id_analisis;
    
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;
    
    @Column(name = "nota_descriptiva", nullable = false, length = 50)
    private String nota_descriptiva;
    
    @OneToOne
    @JoinColumn(name = "id_muestra")
    private MuestraEntidad muestra;
  
    @OneToMany(mappedBy = "analisis", cascade = CascadeType.ALL)
    private List<ParametroEntidad> parametros;

    public AnalisisEntidad() {
    }

    public Long getId_analisis() {
        return id_analisis;
    }

    public void setId_analisis(Long id_analisis) {
        this.id_analisis = id_analisis;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
        hash = 47 * hash + Objects.hashCode(this.id_analisis);
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
        final AnalisisEntidad other = (AnalisisEntidad) obj;
        return Objects.equals(this.id_analisis, other.id_analisis);
    }

    @Override
    public String toString() {
        return "AnalisisEntidad{" + "id_analisis=" + id_analisis + '}';
    }
    
    
    
}
