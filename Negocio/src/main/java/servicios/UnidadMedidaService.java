/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import Exception.NegocioException;
import conexion.ConexionBD;
import daos.UnidadMedidaDAO;
import dtos.UnidadMedidaDTO;
import entidades.UnidadMedidaEntidad;
import exception.PersistenciaException;
import interfaces.IUnidadMedidaDAO;
import java.util.ArrayList;
import java.util.List;

/**
 * Servicio para la administración del catálogo de unidades de medida.
 *
 * @author rafaelgb
 */
public class UnidadMedidaService {

    private final IUnidadMedidaDAO unidadMedidaDAO;

    public UnidadMedidaService() {
        this.unidadMedidaDAO = new UnidadMedidaDAO(new ConexionBD());
    }

    public List<UnidadMedidaDTO> obtenerTodas() throws NegocioException {
        try {
            List<UnidadMedidaEntidad> entidades = unidadMedidaDAO.consultarTodas();
            List<UnidadMedidaDTO> dtos = new ArrayList<>();

            for (UnidadMedidaEntidad entidad : entidades) {
                dtos.add(new UnidadMedidaDTO(entidad.getId(), entidad.getNombre()));
            }

            return dtos;
        } catch (PersistenciaException ex) {
            throw new NegocioException("No se pudieron obtener las unidades de medida: " + ex.getMessage());
        }
    }
}
