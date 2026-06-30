/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import Exception.NegocioException;
import conexion.ConexionBD;
import daos.MuestraDAO;
import dtos.MuestraDTO;
import entidades.MuestraEntidad;
import exception.PersistenciaException;
import interfaces.IMuestraDAO;
import java.util.ArrayList;
import java.util.List;

/**
 * Servicio para la administración del catálogo de tipos de muestra.
 *
 * @author rafaelgb
 */
public class MuestraService {

    private final IMuestraDAO muestraDAO;

    public MuestraService() {
        this.muestraDAO = new MuestraDAO(new ConexionBD());
    }

    public List<MuestraDTO> obtenerTodas() throws NegocioException {
        try {
            List<MuestraEntidad> entidades = muestraDAO.consultarTodas();
            List<MuestraDTO> dtos = new ArrayList<>();

            for (MuestraEntidad entidad : entidades) {
                dtos.add(new MuestraDTO(entidad.getId_muestra(), entidad.getNombre()));
            }

            return dtos;
        } catch (PersistenciaException ex) {
            throw new NegocioException("No se pudieron obtener los tipos de muestra: " + ex.getMessage());
        }
    }
}
