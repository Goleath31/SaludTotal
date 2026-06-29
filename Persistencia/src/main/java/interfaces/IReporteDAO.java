/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.ReportePacienteDTO;

/**
 * Interfaz que define el contrato para la generación de reportes de pacientes.
 * Proporciona el acceso a la información consolidada necesaria para la
 * visualización de los resultados de pruebas médicas.
 *
 * @author golea
 */
public interface IReporteDAO {

    /**
     * Obtiene todos los datos requeridos para generar un reporte clínico
     * completo.
     *
     * @param folio El número de folio único que identifica la prueba médica.
     * @return Un objeto ReportePacienteDTO que contiene los detalles del
     * paciente, el médico, el análisis y los resultados asociados, o null si el
     * folio no existe.
     */
    ReportePacienteDTO obtenerDatosReporte(String folio);
}
