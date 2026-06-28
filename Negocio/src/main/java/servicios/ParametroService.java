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
 *
 * @author golea
 */
public class ParametroService {

    private IParametroDAO parametroDAO;

    public ParametroService() {
        // Asumiendo que usas ConexionBD para instanciar el DAO
        this.parametroDAO = new ParametroDAO(new ConexionBD());
    }

    public List<ParametroDTO> obtenerPorAnalisis(Long idAnalisis) throws Exception {
        try {
            List<ParametroEntidad> entidades = parametroDAO.consultarPorAnalisis(idAnalisis);
            List<ParametroDTO> dtos = new ArrayList<>();

            for (ParametroEntidad p : entidades) {
                // Obtenemos el rango (asumiendo que tomas el primero o el correspondiente a la edad/sexo)
                // Aquí deberás ajustar según la lógica de negocio real para elegir el rango correcto
                double min = 0.0;
                double max = 0.0;
                if (p.getRangos() != null && !p.getRangos().isEmpty()) {
                    RangoEvaluacionEntidad rango = p.getRangos().get(0);
                    min = rango.getRangoInicial();
                    max = rango.getRangoFinal();
                }

                dtos.add(new ParametroDTO(
                        p.getNombre(),
                        min,
                        max,
                        p.getUnidadMedida().getNombre()
                ));
            }
            return dtos;
        } catch (PersistenciaException e) {
            throw new Exception("Error en la capa de negocio: " + e.getMessage());
        }
    
}
}
