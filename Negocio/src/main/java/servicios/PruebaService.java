/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import Exception.NegocioException;
import conexion.ConexionBD;
import conexion.IConexionBD;
import daos.AnalisisDAO;
import daos.PruebaDAO;
import dtos.AnalisisDTO;
import dtos.ClientePruebaCompletaDTO;
import dtos.PruebaDetalleDTO;
import dtos.PruebaResumenDTO;
import entidades.AnalisisEntidad;
import entidades.PruebaEntidad;
import entidades.ResultadoEntidad;
import interfaces.IAnalisisDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author golea
 */
public class PruebaService {

    private IAnalisisDAO analisisDAO = new AnalisisDAO(new ConexionBD());
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
                String nombreCompleto = p.getCliente().getNombre() + " "
                        + p.getCliente().getApellido_paterno();
                lista.add(new PruebaResumenDTO(p.getFolio(), nombreCompleto));
            }
            return lista;
        } catch (Exception e) {
            throw new NegocioException("Error al obtener pruebas: " + e.getMessage());
        }
    }

    public PruebaDetalleDTO obtenerDetallePrueba(String folio) throws NegocioException {
        try {
            PruebaEntidad p = pruebaDAO.buscarPorFolio(folio);
            return new PruebaDetalleDTO(
                    p.getFolio(),
                    p.getCliente().getNombre() + " " + p.getCliente().getApellido_paterno(),
                    p.getCliente().getId_cliente().toString(),
                    p.getCliente().getSexo(),
                    p.getDoctor().getNombre() + " " + p.getDoctor().getApellido_paterno(),
                    p.getFechaHoraGeneracion().toString()
            );
        } catch (Exception e) {
            throw new NegocioException("Error al cargar detalle");
        }
    }

    public List<AnalisisDTO> obtenerAnalisisPorFolio(String folio) throws NegocioException {
        try {
            // Primero obtenemos la prueba para tener el ID
            PruebaEntidad p = pruebaDAO.buscarPorFolio(folio);

            // Luego consultamos los análisis mediante el nuevo DAO
            List<AnalisisEntidad> listaEntidades = analisisDAO.consultarAnalisisPorPrueba(p.getId_prueba());

            List<AnalisisDTO> listaDTO = new ArrayList<>();
            for (AnalisisEntidad a : listaEntidades) {
                listaDTO.add(new AnalisisDTO(a.getId_analisis(), a.getNombre()));
            }
            return listaDTO;
        } catch (Exception e) {
            throw new NegocioException("Error al cargar análisis: " + e.getMessage());
        }
    }

    public List<ClientePruebaCompletaDTO> obtenerClientesCompletos() {
        return pruebaDAO.obtenerClientesConPruebasCompletas();
    }
}
