/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.UnidadMedidaEntidad;
import exception.PersistenciaException;
import java.util.List;

/**
 *
 * @author rafaelgb
 */
public interface IUnidadMedidaDAO {

    List<UnidadMedidaEntidad> consultarTodas() throws PersistenciaException;

    UnidadMedidaEntidad buscarPorId(Long id) throws PersistenciaException;
}
