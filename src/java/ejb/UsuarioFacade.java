/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import db.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author lucas
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {
    @PersistenceContext(unitName = "DEJ4501-Solemne-3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    public Usuario findByRut(int rut) {
        TypedQuery consulta = em.createNamedQuery("Usuario.findByRutUsuario", Usuario.class);
        List<Usuario> lista = consulta.setParameter("rutUsuario", rut).getResultList();
        return lista.get(0);
    }
    
    public Usuario findByUsername(String username) {
        TypedQuery consulta = em.createNamedQuery("Usuario.findByUserName", Usuario.class);
        List<Usuario> lista = consulta.setParameter("userName", username).getResultList();
        return lista.get(0);
    }
}
