/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author golea
 */
@Entity
@Table(name = "Muestra")
public class MuestraEntidad implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_muestra")
    private Long id_muestra;
    
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @OneToOne(mappedBy = "muestra")
    private AnalisisEntidad analisis;
    
    public MuestraEntidad() {
    }

    public Long getId_muestra() {
        return id_muestra;
    }

    public void setId_muestra(Long id_muestra) {
        this.id_muestra = id_muestra;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.id_muestra);
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
        final MuestraEntidad other = (MuestraEntidad) obj;
        return Objects.equals(this.id_muestra, other.id_muestra);
    }

    @Override
    public String toString() {
        return "MuestraEntidad{" + "id_muestra=" + id_muestra + '}';
    }
    
    
    
}
