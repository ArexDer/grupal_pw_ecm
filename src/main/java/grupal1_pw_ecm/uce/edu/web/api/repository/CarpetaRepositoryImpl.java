package grupal1_pw_ecm.uce.edu.web.api.repository;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import grupal1_pw_ecm.uce.edu.web.api.repository.modelo.Carpeta;

@Transactional
@ApplicationScoped
public class CarpetaRepositoryImpl implements ICarpetaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Carpeta buscarPorId(Integer id) {
        return this.entityManager.find(Carpeta.class, id);
    }

    @Override
    public List<Carpeta> buscarTodos() {
        TypedQuery<Carpeta> myQuery =this.entityManager.createQuery("SELECT c from Carpeta c", Carpeta.class);
        return myQuery.getResultList();
    }

    @Override
    public void insertar(Carpeta carpeta) {
        this.entityManager.persist(carpeta);
    }

    /*@Override
    public void actualizar(Carpeta carpeta) {
        this.entityManager.merge(carpeta);
    }*/

    @Override
    public void eliminar(Integer id) {
        this.entityManager.remove(this.buscarPorId(id));
    }
}
