/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import Exception.NegocioException;
import conexion.ConexionBD;
import conexion.IConexionBD;
import daos.DoctorDAO;
import dtos.DoctorDTO;
import entidades.DoctorEntidad;
import exception.PersistenciaException;
import interfaces.IDoctorDAO;
import java.util.ArrayList;
import java.util.List;

/**
 * Servicio encargado de la gestión y recuperación de información de doctores.
 * Actúa como intermediario entre la capa de presentación y el DAO de doctores.
 * @author golea
 */
public class DoctorService {

    private IDoctorDAO doctorDAO;

    /**
     * Constructor que inicializa el DAO de doctores con una conexión a la BD.
     */
    public DoctorService() {
        IConexionBD conexion = new ConexionBD();
        this.doctorDAO = new DoctorDAO(conexion);
    }

    /**
     * Obtiene la lista de todos los doctores registrados.
     *
     * @return Una lista de DoctorDTO con los datos de los doctores.
     * @throws NegocioException Si ocurre un error al acceder a la base de
     * datos.
     */
    public List<DoctorDTO> obtenerDoctores() throws NegocioException {
        try {
            List<DoctorEntidad> entidades = doctorDAO.consultarTodos();
            List<DoctorDTO> dtos = new ArrayList<>();

            for (DoctorEntidad e : entidades) {
                DoctorDTO dto = new DoctorDTO(
                        e.getId_doctor(),
                        e.getNombre(),
                        e.getApellido_paterno(),
                        e.getApellido_materno()
                );
                dtos.add(dto);
            }
            return dtos;
        } catch (PersistenciaException e) {
            throw new NegocioException("No se pudieron recuperar los doctores del sistema: " + e.getMessage());
        }
    }
}
