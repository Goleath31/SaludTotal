/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaces;

import entidades.ClienteEntidad;
import exception.PersistenciaException;

/**
 *
 * @author golea
 */
public interface IClienteDAO {

    ClienteEntidad consultarPorNombre(String nombre) throws PersistenciaException;
}
