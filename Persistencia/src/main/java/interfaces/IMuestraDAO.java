/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.MuestraEntidad;
import exception.PersistenciaException;
import java.util.List;

/**
 *
 * @author rafaelgb
 */
public interface IMuestraDAO {

    List<MuestraEntidad> consultarTodas() throws PersistenciaException;

    MuestraEntidad buscarPorId(Long id) throws PersistenciaException;
}
