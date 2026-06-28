/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.DoctorEntidad;
import exception.PersistenciaException;
import java.util.List;

/**
 *
 * @author golea
 */
public interface IDoctorDAO {
    List<DoctorEntidad> consultarTodos() throws PersistenciaException;
}
