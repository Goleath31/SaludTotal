/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import conexion.ConexionBD;
import daos.ParametroDAO;
import dtos.ParametroDTO;
import entidades.ParametroEntidad;
import entidades.RangoEvaluacionEntidad;
import exception.PersistenciaException;
import interfaces.IParametroDAO;
import java.util.ArrayList;
import java.util.List;

/**
 * Servicio para la gestión de parámetros clínicos y sus rangos de referencia.
 * @author golea
 */
public class ParametroService {

    private IParametroDAO parametroDAO;

    public ParametroService() {
        this.parametroDAO = new ParametroDAO(new ConexionBD());
    }

    
    /**
     * Consulta y procesa los parámetros asociados a un análisis.
     * Extrae los rangos de evaluación para presentarlos de forma simplificada.
     * @param idAnalisis ID del análisis.
     * @return Lista de ParametroDTO con rangos de referencia calculados.
     * @throws Exception Si ocurre un error durante el acceso a datos.
     */
    public List<ParametroDTO> obtenerPorAnalisis(Long idAnalisis) throws Exception {
        try {
            List<ParametroEntidad> entidades = parametroDAO.consultarPorAnalisis(idAnalisis);
            List<ParametroDTO> dtos = new ArrayList<>();

            for (ParametroEntidad p : entidades) {
                double min = 0.0;
                double max = 0.0;
                if (p.getRangos() != null && !p.getRangos().isEmpty()) {
                    RangoEvaluacionEntidad rango = p.getRangos().get(0);
                    min = rango.getRangoInicial();
                    max = rango.getRangoFinal();
                }

                dtos.add(new ParametroDTO(
                        p.getId_parametro(),
                        p.getNombre(),
                        min,
                        max,
                        p.getUnidadMedida().getNombre(),
                        ""
                ));
            }
            return dtos;
        } catch (PersistenciaException e) {
            throw new Exception("Error en la capa de negocio: " + e.getMessage());
        }

    }
}
