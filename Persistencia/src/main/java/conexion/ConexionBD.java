/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Implementación de la interfaz IConexionBD que gestiona la fábrica 
 * de EntityManager de JPA.
 * @author golea
 */
public class ConexionBD implements IConexionBD {
    
private static final String PERSISTENCE_UNIT = "ConexionPU";
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);

    /**
     * Crea y devuelve una nueva instancia de EntityManager.
     * @return EntityManager para ejecutar transacciones.
     */
    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}