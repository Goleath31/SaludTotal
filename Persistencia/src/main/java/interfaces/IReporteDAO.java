/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.ReportePacienteDTO;

/**
 *
 * @author golea
 */
public interface IReporteDAO {
    ReportePacienteDTO obtenerDatosReporte(String folio);
}
