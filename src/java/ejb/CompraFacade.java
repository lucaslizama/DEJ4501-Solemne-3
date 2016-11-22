/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import db.Compra;
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
public class CompraFacade extends AbstractFacade<Compra> {
    @PersistenceContext(unitName = "DEJ4501-Solemne-3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CompraFacade() {
        super(Compra.class);
    }
    
    public Compra BuscarPorIdUsuario(int id) {
        TypedQuery consulta = em.createNamedQuery("Compra.findByIdUsuario", Compra.class);
        List<Compra> lista = consulta.setParameter("idUsuario", id).getResultList();
        return lista.get(0);
    } 
    
}
