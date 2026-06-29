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
 * Servicio encargado de la generación de reportes para el paciente.
 * Simplifica la obtención de datos consolidados para la capa de presentación.
 * @author golea
 */
public class ReporteService {

    private IReporteDAO reporteDAO;

    /**
     * Inicializa el servicio inyectando la implementación del DAO de reportes.
     */
    public ReporteService() {
        this.reporteDAO = new ReporteDAO(new ConexionBD());
    }

    /**
     * Obtiene los datos consolidados de un paciente para generar el reporte.
     * @param folio El número de folio de la prueba médica.
     * @return DTO con la estructura completa para imprimir o visualizar el reporte.
     */
    public ReportePacienteDTO generarReporte(String folio) {
        if (folio == null || folio.isEmpty()) {
            return null;
        }
        return reporteDAO.obtenerDatosReporte(folio);
    }
}
