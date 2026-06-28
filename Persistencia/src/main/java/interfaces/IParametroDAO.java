/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.ParametroEntidad;
import exception.PersistenciaException;
import java.util.List;

/**
 *
 * @author golea
 */
public interface IParametroDAO {

    List<ParametroEntidad> consultarPorAnalisis(Long idAnalisis) throws PersistenciaException;
}
