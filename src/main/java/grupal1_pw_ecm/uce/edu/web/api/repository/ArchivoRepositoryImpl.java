package grupal1_pw_ecm.uce.edu.web.api.repository;

import grupal1_pw_ecm.uce.edu.web.api.repository.modelo.Archivo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@ApplicationScoped
public class ArchivoRepositoryImpl implements IArchivoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void insertar(Archivo archivo) {
        entityManager.persist(archivo);
    }

    @Override
    public Archivo seleccionar(int id) {
        return entityManager.find(Archivo.class, id);
    }

    @Override
    public Archivo seleccionarNombre(String nombre) {
        try {
            TypedQuery<Archivo> query = entityManager.createQuery(
                    "SELECT a FROM Archivo a WHERE a.nombre = :nombre",
                    Archivo.class);
            query.setParameter("nombre", nombre);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null; // Manejo de excepción para evitar errores si no se encuentra el archivo
        }
    }

}
