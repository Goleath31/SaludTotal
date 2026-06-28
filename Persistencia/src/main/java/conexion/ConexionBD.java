/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author golea
 */
public class ConexionBD implements IConexionBD {
    
private static final String PERSISTENCE_UNIT = "ConexionPU";
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}