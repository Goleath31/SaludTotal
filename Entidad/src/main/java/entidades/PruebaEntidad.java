/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
@Table(name = "Prueba")
public class PruebaEntidad implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prueba")
    private Long id_prueba;

    @Column(name = "folio", nullable = false, length = 50)
    private String folio;

    @Column(name = "fecha_hora_generacion", nullable = false)
    private Date fechaHoraGeneracion;

    
    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private ClienteEntidad cliente;
    
    @ManyToOne
    @JoinColumn(name = "id_doctor", nullable = false)
    private DoctorEntidad doctor;

    @OneToMany(mappedBy = "prueba", cascade = CascadeType.ALL)
    private List<ResultadoEntidad> resultados;
    
    

    public PruebaEntidad() {
    }

    public Long getId_prueba() {
        return id_prueba;
    }

    public void setId_prueba(Long id_prueba) {
        this.id_prueba = id_prueba;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public Date getFechaHoraGeneracion() {
        return fechaHoraGeneracion;
    }

    public void setFechaHoraGeneracion(Date fechaHoraGeneracion) {
        this.fechaHoraGeneracion = fechaHoraGeneracion;
    }

    @Override
    public String toString() {
        return "PruebaEntidad{" + "id_prueba=" + id_prueba + '}';
    }

}
