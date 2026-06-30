/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import Exception.NegocioException;
import conexion.ConexionBD;
import daos.RangoEvaluacionDAO;
import dtos.RangoDTO;
import entidades.RangoEvaluacionEntidad;
import exception.PersistenciaException;
import interfaces.IRangoEvaluacionDAO;
import java.util.ArrayList;
import java.util.List;

/**
 * Servicio para la administración de rangos de evaluación de un parámetro.
 *
 * @author rafaelgb
 */
public class RangoEvaluacionService {

    private final IRangoEvaluacionDAO rangoDAO;

    public RangoEvaluacionService() {
        this.rangoDAO = new RangoEvaluacionDAO(new ConexionBD());
    }

    public List<RangoDTO> obtenerPorParametro(Long idParametro) throws NegocioException {
        try {
            List<RangoEvaluacionEntidad> entidades = rangoDAO.buscarPorParametro(idParametro);
            List<RangoDTO> dtos = new ArrayList<>();

            for (RangoEvaluacionEntidad entidad : entidades) {
                RangoDTO dto = new RangoDTO(0, entidad.getRangoInicial(), entidad.getRangoFinal(),
                        entidad.getEdadInicial(), entidad.getEdadFinal(), entidad.getSexo());
                dto.setId(entidad.getId());
                dtos.add(dto);
            }

            return dtos;
        } catch (PersistenciaException ex) {
            throw new NegocioException("No se pudieron obtener los rangos del parámetro: " + ex.getMessage());
        }
    }

    public void eliminar(RangoEvaluacionEntidad rango) throws NegocioException {
        try {
            rangoDAO.eliminar(rango);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al eliminar el rango: " + ex.getMessage());
        }
    }
}
