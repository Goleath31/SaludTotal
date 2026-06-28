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
 *
 * @author golea
 */
public class DoctorService {
private IDoctorDAO doctorDAO;

   public DoctorService() {
        // 1. Instanciamos la implementación de la conexión
        IConexionBD conexion = new ConexionBD();
        
        // 2. Pasamos la conexión al DAO como pide el constructor
        this.doctorDAO = new DoctorDAO(conexion);
    }

    public List<DoctorDTO> obtenerDoctores() throws NegocioException {
        try {
            List<DoctorEntidad> entidades = doctorDAO.consultarTodos();
            List<DoctorDTO> dtos = new ArrayList<>();

            for (DoctorEntidad e : entidades) {
                // Mapeo de Entidad a DTO
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