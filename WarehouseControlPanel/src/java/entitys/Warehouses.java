/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitys;

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
 * @author 
 */
@Entity
@Table(catalog = "warehouse_logistics_system", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Warehouses.findAll", query = "SELECT w FROM Warehouses w")
    , @NamedQuery(name = "Warehouses.findById", query = "SELECT w FROM Warehouses w WHERE w.id = :id")
    , @NamedQuery(name = "Warehouses.findByName", query = "SELECT w FROM Warehouses w WHERE w.name = :name")
    , @NamedQuery(name = "Warehouses.findByAddress", query = "SELECT w FROM Warehouses w WHERE w.address = :address")
    , @NamedQuery(name = "Warehouses.findByNumberOfSections", query = "SELECT w FROM Warehouses w WHERE w.numberOfSections = :numberOfSections")})
public class Warehouses implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String name;
    
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String address;
    
    @Basic(optional = false)
    @Column(name = "number_of_sections", nullable = false)
    private int numberOfSections;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "warehouseId")
    private List<Sections> sectionsList;

    public Warehouses() {
    }

    public Warehouses(Integer id) {
        this.id = id;
    }

    public Warehouses(Integer id, String name, String address, int numberOfSections) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.numberOfSections = numberOfSections;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNumberOfSections() {
        return numberOfSections;
    }

    public void setNumberOfSections(int numberOfSections) {
        this.numberOfSections = numberOfSections;
    }

    @XmlTransient
    public List<Sections> getSectionsList() {
        return sectionsList;
    }

    public void setSectionsList(List<Sections> sectionsList) {
        this.sectionsList = sectionsList;
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
        if (!(object instanceof Warehouses)) {
            return false;
        }
        Warehouses other = (Warehouses) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return name;
    }
    
}
