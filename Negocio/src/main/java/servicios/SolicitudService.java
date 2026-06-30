/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import Exception.NegocioException;
import conexion.ConexionBD;
import conexion.IConexionBD;
import daos.ParametroDAO;
import daos.PruebaDAO;
import dtos.SolicitudDTO;
import entidades.ClienteEntidad;
import entidades.DoctorEntidad;
import entidades.ParametroEntidad;
import entidades.PruebaEntidad;
import entidades.ResultadoEntidad;
import exception.PersistenciaException;
import interfaces.IParametroDAO;
import interfaces.IPruebaDAO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * "Solicitud" en pantalla es la entidad Prueba. Pedir un análisis significa
 * crear, por cada Parametro de ese análisis, un Resultado sin valor todavía
 * (pendiente de captura en el módulo de Resultados).
 *
 * @author rafaelgb
 */
public class SolicitudService {

    private final IPruebaDAO pruebaDAO;
    private final IParametroDAO parametroDAO;

    public SolicitudService() {
        IConexionBD conexion = new ConexionBD();
        this.pruebaDAO = new PruebaDAO(conexion);
        this.parametroDAO = new ParametroDAO(conexion);
    }

    public List<SolicitudDTO> obtenerUltimas(int pagina, int tamanoPagina) throws NegocioException {
        try {
            return convertirLista(pruebaDAO.consultarTodos(pagina, tamanoPagina));
        } catch (PersistenciaException ex) {
            throw new NegocioException("No se pudieron obtener las solicitudes: " + ex.getMessage());
        }
    }

    public long contarTotal() throws NegocioException {
        try {
            return pruebaDAO.contarTodos();
        } catch (PersistenciaException ex) {
            throw new NegocioException("No se pudieron contar las solicitudes: " + ex.getMessage());
        }
    }

    public List<SolicitudDTO> filtrar(String texto) throws NegocioException {
        try {
            return convertirLista(pruebaDAO.buscarPorFiltro(texto == null ? "" : texto));
        } catch (PersistenciaException ex) {
            throw new NegocioException("No se pudieron filtrar las solicitudes: " + ex.getMessage());
        }
    }

    public void guardarSolicitud(SolicitudDTO dto) throws NegocioException {
        try {
            if (dto.getAnalisisIds() == null || dto.getAnalisisIds().isEmpty()) {
                throw new NegocioException("La solicitud debe tener al menos una prueba seleccionada.");
            }
            if (dto.getDoctorId() == null) {
                throw new NegocioException("La solicitud debe tener un doctor asignado.");
            }

            PruebaEntidad prueba = new PruebaEntidad();
            prueba.setFolio(dto.getFolio());
            prueba.setFechaHoraGeneracion(new Date());

            ClienteEntidad clienteRef = new ClienteEntidad();
            clienteRef.setId_cliente(dto.getClienteId());
            prueba.setCliente(clienteRef);

            DoctorEntidad doctorRef = new DoctorEntidad();
            doctorRef.setId_doctor(dto.getDoctorId());
            prueba.setDoctor(doctorRef);

            List<ResultadoEntidad> resultadosPendientes = new ArrayList<>();
            for (Long idAnalisis : dto.getAnalisisIds()) {
                List<ParametroEntidad> parametros = parametroDAO.consultarPorAnalisis(idAnalisis);
                for (ParametroEntidad parametro : parametros) {
                    ResultadoEntidad resultado = new ResultadoEntidad();
                    resultado.setParametro(parametro);
                    resultado.setPrueba(prueba);
                    resultadosPendientes.add(resultado);
                }
            }

            if (resultadosPendientes.isEmpty()) {
                throw new NegocioException("Los análisis seleccionados no tienen parámetros configurados en el catálogo.");
            }

            prueba.setResultados(resultadosPendientes);

            pruebaDAO.guardar(prueba);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al guardar la solicitud: " + ex.getMessage());
        }
    }

    private List<SolicitudDTO> convertirLista(List<PruebaEntidad> entidades) {
        List<SolicitudDTO> dtos = new ArrayList<>();
        for (PruebaEntidad entidad : entidades) {
            dtos.add(convertir(entidad));
        }
        return dtos;
    }

    private SolicitudDTO convertir(PruebaEntidad entidad) {
        Set<String> nombresAnalisis = new LinkedHashSet<>();
        if (entidad.getResultados() != null) {
            for (ResultadoEntidad resultado : entidad.getResultados()) {
                nombresAnalisis.add(resultado.getParametro().getAnalisis().getNombre());
            }
        }

        String fechaFormateada = "";
        if (entidad.getFechaHoraGeneracion() != null) {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            fechaFormateada = formato.format(entidad.getFechaHoraGeneracion());
        }

        return new SolicitudDTO(entidad.getId_prueba(), entidad.getFolio(), String.join(", ", nombresAnalisis), fechaFormateada);
    }
}
