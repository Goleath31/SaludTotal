/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.AnalisisEntidad;
import exception.PersistenciaException;
import java.util.List;

/**
 *
 * @author golea
 */
public interface IAnalisisDAO {
    List<AnalisisEntidad> consultarAnalisisPorPrueba(Long idPrueba) throws PersistenciaException;
}
