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
@Table(name = "barco")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Barco.findAll", query = "SELECT b FROM Barco b"),
    @NamedQuery(name = "Barco.findById", query = "SELECT b FROM Barco b WHERE b.id = :id"),
    @NamedQuery(name = "Barco.findByPatente", query = "SELECT b FROM Barco b WHERE b.patente = :patente"),
    @NamedQuery(name = "Barco.findByNombreBarco", query = "SELECT b FROM Barco b WHERE b.nombreBarco = :nombreBarco")})
public class Barco implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "patente")
    private String patente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nombre_barco")
    private String nombreBarco;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idBarco")
    private List<Compra> compraList;

    public Barco() {
    }

    public Barco(Integer id) {
        this.id = id;
    }

    public Barco(Integer id, String patente, String nombreBarco) {
        this.id = id;
        this.patente = patente;
        this.nombreBarco = nombreBarco;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public String getNombreBarco() {
        return nombreBarco;
    }

    public void setNombreBarco(String nombreBarco) {
        this.nombreBarco = nombreBarco;
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
        if (!(object instanceof Barco)) {
            return false;
        }
        Barco other = (Barco) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db.Barco[ id=" + id + " ]";
    }
    
}
