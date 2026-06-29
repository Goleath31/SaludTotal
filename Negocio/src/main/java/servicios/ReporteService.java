/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import conexion.ConexionBD;
import daos.ReporteDAO;
import dtos.ReportePacienteDTO;
import interfaces.IReporteDAO;

/**
 *
 * @author golea
 */
public class ReporteService {

    private IReporteDAO reporteDAO;

    public ReporteService() {
        // Inicializamos el DAO inyectando la conexión
        this.reporteDAO = new ReporteDAO(new ConexionBD());
    }

    /**
     * Obtiene los datos consolidados de un paciente para generar el reporte.
     *
     * @param folio El folio de la prueba a consultar.
     * @return El DTO con los datos del reporte.
     */
    public ReportePacienteDTO generarReporte(String folio) {
        if (folio == null || folio.isEmpty()) {
            return null;
        }
        return reporteDAO.obtenerDatosReporte(folio);
    }
}
