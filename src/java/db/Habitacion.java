/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lucas
 */
@Entity
@Table(name = "habitacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Habitacion.findAll", query = "SELECT h FROM Habitacion h"),
    @NamedQuery(name = "Habitacion.findById", query = "SELECT h FROM Habitacion h WHERE h.id = :id"),
    @NamedQuery(name = "Habitacion.findByNumeroHabitacion", query = "SELECT h FROM Habitacion h WHERE h.numeroHabitacion = :numeroHabitacion")})
public class Habitacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numero_habitacion")
    private int numeroHabitacion;
    @JoinColumn(name = "id_tipo_habitacion", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Tipohabitacion idTipoHabitacion;
    @JoinColumn(name = "id_compra", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Compra idCompra;

    public Habitacion() {
    }

    public Habitacion(Integer id) {
        this.id = id;
    }

    public Habitacion(Integer id, int numeroHabitacion) {
        this.id = id;
        this.numeroHabitacion = numeroHabitacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getNumeroHabitacion() {
        return numeroHabitacion;
    }

    public void setNumeroHabitacion(int numeroHabitacion) {
        this.numeroHabitacion = numeroHabitacion;
    }

    public Tipohabitacion getIdTipoHabitacion() {
        return idTipoHabitacion;
    }

    public void setIdTipoHabitacion(Tipohabitacion idTipoHabitacion) {
        this.idTipoHabitacion = idTipoHabitacion;
    }

    public Compra getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Compra idCompra) {
        this.idCompra = idCompra;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Habitacion)) {
            return false;
        }
        Habitacion other = (Habitacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db.Habitacion[ id=" + id + " ]";
    }
    
}
