/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import Exception.NegocioException;
import conexion.ConexionBD;
import conexion.IConexionBD;
import daos.ClienteDAO;
import dtos.ClienteDTO;
import entidades.ClienteEntidad;
import exception.PersistenciaException;
import interfaces.IClienteDAO;
import java.util.List;


/**
 * Servicio encargado de gestionar la lógica de negocio relacionada con los clientes.
 * Proporciona métodos para consultar información de clientes de forma segura.
 * @author golea
 */
public class ClienteService {

    private IClienteDAO clienteDAO;

    /**
     * Constructor que inicializa el DAO de clientes con una conexión a la BD.
     */
    public ClienteService() {
        IConexionBD conexion = new ConexionBD();
        this.clienteDAO = new ClienteDAO(conexion);
    }

    /**
     * Busca un cliente específico por su nombre.
     * @param nombre El nombre del cliente a buscar.
     * @return El DTO con la información del cliente, o null si no se encuentra.
     * @throws NegocioException Si ocurre un error durante el proceso de persistencia.
     */
    public ClienteDTO buscarPorNombre(String nombre) throws NegocioException {
        try {
            ClienteEntidad entidad = clienteDAO.consultarPorNombre(nombre);

            if (entidad == null) {
                return null;
            }

            // Transformación de Entidad a DTO
            return new ClienteDTO(
                    entidad.getId_cliente(),
                    entidad.getNombre(),
                    entidad.getApellido_paterno(),
                    entidad.getApellido_materno(),
                    entidad.getSexo()
            );
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar el cliente: " + e.getMessage());
        }
    }

    /**
     * Busca un cliente por Id (si el texto es numérico) o por nombre/apellido
     * (filtro parcial), para usarse en el buscador de la pantalla de
     * solicitudes.
     *
     * @param texto Id numérico o fragmento de nombre/apellido del cliente.
     * @return El DTO del cliente encontrado, o null si no hay coincidencias.
     */
    public ClienteDTO buscarPorTexto(String texto) throws NegocioException {
        try {
            if (texto == null || texto.trim().isEmpty()) {
                return null;
            }

            if (texto.trim().matches("\\d+")) {
                try {
                    ClienteEntidad entidad = clienteDAO.buscarPorId(Long.parseLong(texto.trim()));
                    return convertirCompleto(entidad);
                } catch (PersistenciaException ex) {
                    return null;
                }
            }

            List<ClienteEntidad> encontrados = clienteDAO.buscarPorFiltro(texto.trim());
            if (encontrados.isEmpty()) {
                return null;
            }
            return convertirCompleto(encontrados.get(0));
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al buscar el cliente: " + ex.getMessage());
        }
    }

    private ClienteDTO convertirCompleto(ClienteEntidad entidad) {
        ClienteDTO dto = new ClienteDTO();
        dto.setId(entidad.getId_cliente());
        dto.setNombreCompleto(entidad.getNombre() + " " + entidad.getApellido_paterno() + " " + entidad.getApellido_materno());
        dto.setSexo(entidad.getSexo());
        dto.setTipoSangre(entidad.getTipo_sangre());
        dto.setFechaNacimiento(entidad.getFecha_nacimiento());
        return dto;
    }
}
