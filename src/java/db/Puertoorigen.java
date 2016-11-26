/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author lucas
 */
@Entity
@Table(name = "puertoorigen")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Puertoorigen.findAll", query = "SELECT p FROM Puertoorigen p"),
    @NamedQuery(name = "Puertoorigen.findById", query = "SELECT p FROM Puertoorigen p WHERE p.id = :id"),
    @NamedQuery(name = "Puertoorigen.findByNumeroPuerto", query = "SELECT p FROM Puertoorigen p WHERE p.numeroPuerto = :numeroPuerto"),
    @NamedQuery(name = "Puertoorigen.findByNombrePuerto", query = "SELECT p FROM Puertoorigen p WHERE p.nombrePuerto = :nombrePuerto")})
public class Puertoorigen implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numero_puerto")
    private int numeroPuerto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "nombre_puerto")
    private String nombrePuerto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOrigen")
    private List<Compra> compraList;

    public Puertoorigen() {
    }

    public Puertoorigen(Integer id) {
        this.id = id;
    }

    public Puertoorigen(Integer id, int numeroPuerto, String nombrePuerto) {
        this.id = id;
        this.numeroPuerto = numeroPuerto;
        this.nombrePuerto = nombrePuerto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getNumeroPuerto() {
        return numeroPuerto;
    }

    public void setNumeroPuerto(int numeroPuerto) {
        this.numeroPuerto = numeroPuerto;
    }

    public String getNombrePuerto() {
        return nombrePuerto;
    }

    public void setNombrePuerto(String nombrePuerto) {
        this.nombrePuerto = nombrePuerto;
    }

    @XmlTransient
    public List<Compra> getCompraList() {
        return compraList;
    }

    public void setCompraList(List<Compra> compraList) {
        this.compraList = compraList;
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
        if (!(object instanceof Puertoorigen)) {
            return false;
        }
        Puertoorigen other = (Puertoorigen) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db.Puertoorigen[ id=" + id + " ]";
    }
    
}
