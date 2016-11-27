/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import db.Habitacion;
import db.Tipohabitacion;
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
public class TipohabitacionFacade extends AbstractFacade<Tipohabitacion> {
    @PersistenceContext(unitName = "DEJ4501-Solemne-3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipohabitacionFacade() {
        super(Tipohabitacion.class);
    }
    
    public Habitacion findHabitacionVaciaByTipo(int idTipoHabitacion) {
        TypedQuery consulta = em.createNamedQuery("Tipohabitacion.findById", Tipohabitacion.class);
        Tipohabitacion tipo = (Tipohabitacion)consulta.setParameter("id", idTipoHabitacion).getResultList().get(0);
        List<Habitacion> habitaciones = tipo.getHabitacionList();
        
        for(Habitacion habitacion : habitaciones) {
            if(!habitacion.getOcupada())
                return habitacion;
        }
        
        return null;
    }
}
