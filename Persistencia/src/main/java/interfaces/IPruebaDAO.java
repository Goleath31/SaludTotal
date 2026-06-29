/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.ClientePruebaCompletaDTO;
import entidades.PruebaEntidad;
import exception.PersistenciaException;
import java.util.List;

/**
 *
 * @author golea
 */
public interface IPruebaDAO {

    List<PruebaEntidad> consultarPorDoctor(Long idDoctor) throws PersistenciaException;
    
    PruebaEntidad buscarPorFolio(String folio) throws PersistenciaException;
    
    List<ClientePruebaCompletaDTO> obtenerClientesConPruebasCompletas();
}
