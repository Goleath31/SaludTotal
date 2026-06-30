/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import Exception.NegocioException;
import conexion.ConexionBD;
import daos.AnalisisDAO;
import dtos.AnalisisDTO;
import dtos.ParametroDTO;
import dtos.RangoDTO;
import entidades.AnalisisEntidad;
import entidades.MuestraEntidad;
import entidades.ParametroEntidad;
import entidades.RangoEvaluacionEntidad;
import entidades.UnidadMedidaEntidad;
import exception.PersistenciaException;
import interfaces.IAnalisisDAO;
import java.util.ArrayList;
import java.util.List;

/**
 * Servicio para la administración del catálogo de análisis: alta, baja
 * lógica (en cascada hacia parámetros y rangos), búsqueda y paginación.
 *
 * @author rafaelgb
 */
public class AnalisisService {

    private final IAnalisisDAO analisisDAO;

    public AnalisisService() {
        this.analisisDAO = new AnalisisDAO(new ConexionBD());
    }

    public List<AnalisisDTO> obtenerCatalogo(int pagina, int tamanoPagina) throws NegocioException {
        try {
            List<AnalisisEntidad> entidades = analisisDAO.consultarTodos(pagina, tamanoPagina);
            return convertirLista(entidades);
        } catch (PersistenciaException ex) {
            throw new NegocioException("No se pudo obtener el catálogo de análisis: " + ex.getMessage());
        }
    }

    public long contarTotal() throws NegocioException {
        try {
            return analisisDAO.contarTodos();
        } catch (PersistenciaException ex) {
            throw new NegocioException("No se pudo contar el catálogo de análisis: " + ex.getMessage());
        }
    }

    public List<AnalisisDTO> filtrar(String texto) throws NegocioException {
        try {
            List<AnalisisEntidad> entidades = analisisDAO.buscarPorFiltro(texto == null ? "" : texto);
            return convertirLista(entidades);
        } catch (PersistenciaException ex) {
            throw new NegocioException("No se pudo filtrar el catálogo de análisis: " + ex.getMessage());
        }
    }

    public List<AnalisisDTO> obtenerNombresParaEliminar() throws NegocioException {
        return filtrar("");
    }

    public void guardarAnalisisCompleto(AnalisisDTO dto) throws NegocioException {
        try {
            AnalisisEntidad analisis = new AnalisisEntidad();
            analisis.setNombre(dto.getNombre());
            analisis.setNota_descriptiva(dto.getNotaDescriptiva());

            MuestraEntidad muestraRef = new MuestraEntidad();
            muestraRef.setId_muestra(dto.getMuestraId());
            analisis.setMuestra(muestraRef);

            List<ParametroEntidad> parametros = new ArrayList<>();
            for (ParametroDTO parametroDTO : dto.getParametros()) {
                ParametroEntidad parametro = new ParametroEntidad();
                parametro.setNombre(parametroDTO.getNombre());
                parametro.setOrden(parametroDTO.getOrden());
                parametro.setNota_descriptiva(parametroDTO.getNotaDescriptiva());
                parametro.setAnalisis(analisis);

                UnidadMedidaEntidad unidadRef = new UnidadMedidaEntidad();
                unidadRef.setId(parametroDTO.getUnidadMedidaId());
                parametro.setUnidadMedida(unidadRef);

                List<RangoEvaluacionEntidad> rangos = new ArrayList<>();
                for (RangoDTO rangoDTO : parametroDTO.getRangos()) {
                    RangoEvaluacionEntidad rango = new RangoEvaluacionEntidad();
                    rango.setRangoInicial(rangoDTO.getRangoInicial());
                    rango.setRangoFinal(rangoDTO.getRangoFinal());
                    rango.setEdadInicial(rangoDTO.getEdadInicial());
                    rango.setEdadFinal(rangoDTO.getEdadFinal());
                    rango.setSexo(rangoDTO.getSexo());
                    rango.setParametro(parametro);
                    rangos.add(rango);
                }
                parametro.setRangos(rangos);

                parametros.add(parametro);
            }
            analisis.setParametros(parametros);

            analisisDAO.guardar(analisis);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al guardar el análisis completo: " + ex.getMessage());
        }
    }

    public void eliminarAnalisis(Long id) throws NegocioException {
        try {
            AnalisisEntidad analisis = analisisDAO.buscarPorId(id);
            analisisDAO.eliminar(analisis);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al eliminar el análisis: " + ex.getMessage());
        }
    }

    private List<AnalisisDTO> convertirLista(List<AnalisisEntidad> entidades) {
        List<AnalisisDTO> dtos = new ArrayList<>();
        for (AnalisisEntidad entidad : entidades) {
            dtos.add(convertir(entidad));
        }
        return dtos;
    }

    private AnalisisDTO convertir(AnalisisEntidad entidad) {
        String nombreMuestra = entidad.getMuestra() != null ? entidad.getMuestra().getNombre() : "N/A";
        AnalisisDTO dto = new AnalisisDTO(entidad.getId_analisis(), entidad.getNombre(), entidad.getNota_descriptiva(), nombreMuestra);
        if (entidad.getMuestra() != null) {
            dto.setMuestraId(entidad.getMuestra().getId_muestra());
        }
        return dto;
    }
}
