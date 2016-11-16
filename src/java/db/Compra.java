/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author lucas
 */
@Entity
@Table(name = "compra")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Compra.findAll", query = "SELECT c FROM Compra c"),
    @NamedQuery(name = "Compra.findById", query = "SELECT c FROM Compra c WHERE c.id = :id"),
    @NamedQuery(name = "Compra.findByIdOrigen", query = "SELECT c FROM Compra c WHERE c.idOrigen = :idOrigen"),
    @NamedQuery(name = "Compra.findByIdDestino", query = "SELECT c FROM Compra c WHERE c.idDestino = :idDestino"),
    @NamedQuery(name = "Compra.findByIdBarco", query = "SELECT c FROM Compra c WHERE c.idBarco = :idBarco"),
    @NamedQuery(name = "Compra.findByIdHabitacion", query = "SELECT c FROM Compra c WHERE c.idHabitacion = :idHabitacion"),
    @NamedQuery(name = "Compra.findByFechaEnbarque", query = "SELECT c FROM Compra c WHERE c.fechaEnbarque = :fechaEnbarque"),
    @NamedQuery(name = "Compra.findByFechaDesenbarque", query = "SELECT c FROM Compra c WHERE c.fechaDesenbarque = :fechaDesenbarque"),
    @NamedQuery(name = "Compra.findByNumeroPasajeros", query = "SELECT c FROM Compra c WHERE c.numeroPasajeros = :numeroPasajeros"),
    @NamedQuery(name = "Compra.findByValorPasaje", query = "SELECT c FROM Compra c WHERE c.valorPasaje = :valorPasaje")})
public class Compra implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_origen")
    private int idOrigen;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_destino")
    private int idDestino;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_barco")
    private int idBarco;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_habitacion")
    private int idHabitacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_enbarque")
    @Temporal(TemporalType.DATE)
    private Date fechaEnbarque;
    @Column(name = "fecha_desenbarque")
    @Temporal(TemporalType.DATE)
    private Date fechaDesenbarque;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numero_pasajeros")
    private int numeroPasajeros;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_pasaje")
    private int valorPasaje;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Usuario idUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCompra")
    private List<Pasaje> pasajeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCompra")
    private List<Puertodestino> puertodestinoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCompra")
    private List<Puertoorigen> puertoorigenList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCompra")
    private List<Barco> barcoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCompra")
    private List<Formapago> formapagoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCompra")
    private List<Habitacion> habitacionList;

    public Compra() {
    }

    public Compra(Integer id) {
        this.id = id;
    }

    public Compra(Integer id, int idOrigen, int idDestino, int idBarco, int idHabitacion, Date fechaEnbarque, int numeroPasajeros, int valorPasaje) {
        this.id = id;
        this.idOrigen = idOrigen;
        this.idDestino = idDestino;
        this.idBarco = idBarco;
        this.idHabitacion = idHabitacion;
        this.fechaEnbarque = fechaEnbarque;
        this.numeroPasajeros = numeroPasajeros;
        this.valorPasaje = valorPasaje;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdOrigen() {
        return idOrigen;
    }

    public void setIdOrigen(int idOrigen) {
        this.idOrigen = idOrigen;
    }

    public int getIdDestino() {
        return idDestino;
    }

    public void setIdDestino(int idDestino) {
        this.idDestino = idDestino;
    }

    public int getIdBarco() {
        return idBarco;
    }

    public void setIdBarco(int idBarco) {
        this.idBarco = idBarco;
    }

    public int getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(int idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public Date getFechaEnbarque() {
        return fechaEnbarque;
    }

    public void setFechaEnbarque(Date fechaEnbarque) {
        this.fechaEnbarque = fechaEnbarque;
    }

    public Date getFechaDesenbarque() {
        return fechaDesenbarque;
    }

    public void setFechaDesenbarque(Date fechaDesenbarque) {
        this.fechaDesenbarque = fechaDesenbarque;
    }

    public int getNumeroPasajeros() {
        return numeroPasajeros;
    }

    public void setNumeroPasajeros(int numeroPasajeros) {
        this.numeroPasajeros = numeroPasajeros;
    }

    public int getValorPasaje() {
        return valorPasaje;
    }

    public void setValorPasaje(int valorPasaje) {
        this.valorPasaje = valorPasaje;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    @XmlTransient
    public List<Pasaje> getPasajeList() {
        return pasajeList;
    }

    public void setPasajeList(List<Pasaje> pasajeList) {
        this.pasajeList = pasajeList;
    }

    @XmlTransient
    public List<Puertodestino> getPuertodestinoList() {
        return puertodestinoList;
    }

    public void setPuertodestinoList(List<Puertodestino> puertodestinoList) {
        this.puertodestinoList = puertodestinoList;
    }

    @XmlTransient
    public List<Puertoorigen> getPuertoorigenList() {
        return puertoorigenList;
    }

    public void setPuertoorigenList(List<Puertoorigen> puertoorigenList) {
        this.puertoorigenList = puertoorigenList;
    }

    @XmlTransient
    public List<Barco> getBarcoList() {
        return barcoList;
    }

    public void setBarcoList(List<Barco> barcoList) {
        this.barcoList = barcoList;
    }

    @XmlTransient
    public List<Formapago> getFormapagoList() {
        return formapagoList;
    }

    public void setFormapagoList(List<Formapago> formapagoList) {
        this.formapagoList = formapagoList;
    }

    @XmlTransient
    public List<Habitacion> getHabitacionList() {
        return habitacionList;
    }

    public void setHabitacionList(List<Habitacion> habitacionList) {
        this.habitacionList = habitacionList;
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
        if (!(object instanceof Compra)) {
            return false;
        }
        Compra other = (Compra) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db.Compra[ id=" + id + " ]";
    }
    
}
