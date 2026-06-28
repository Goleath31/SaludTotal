/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import Exception.NegocioException;
import conexion.ConexionBD;
import conexion.IConexionBD;
import daos.PruebaDAO;
import dtos.PruebaResumenDTO;
import entidades.PruebaEntidad;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author golea
 */
public class PruebaService {

    private PruebaDAO pruebaDAO;

    public PruebaService() {
        // 2. INICIALIZA el DAO
        IConexionBD conexion = new ConexionBD();
        this.pruebaDAO = new PruebaDAO(conexion);
    }

    public List<PruebaResumenDTO> obtenerResumenPorMedico(Long idDoctor) throws NegocioException {
        try {
            List<PruebaEntidad> pruebas = pruebaDAO.consultarPorDoctor(idDoctor);
            List<PruebaResumenDTO> lista = new ArrayList<>();
            
            for (PruebaEntidad p : pruebas) {
                // Si el error persiste, verifica en PruebaEntidad.java 
                // cómo se llama exactamente el método (ej. getCliente())
                String nombreCompleto = p.getCliente().getNombre() + " " + 
                                        p.getCliente().getApellido_paterno();
                lista.add(new PruebaResumenDTO(p.getFolio(), nombreCompleto));
            }
            return lista;
        } catch (Exception e) {
            throw new NegocioException("Error al obtener pruebas: " + e.getMessage());
        }
    }
}
