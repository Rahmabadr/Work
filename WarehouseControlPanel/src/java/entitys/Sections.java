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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    @NamedQuery(name = "Sections.findAll", query = "SELECT s FROM Sections s")
    , @NamedQuery(name = "Sections.findById", query = "SELECT s FROM Sections s WHERE s.id = :id")
    , @NamedQuery(name = "Sections.findByNumber", query = "SELECT s FROM Sections s WHERE s.number = :number")
    , @NamedQuery(name = "Sections.findByNumberOfCells", query = "SELECT s FROM Sections s WHERE s.numberOfCells = :numberOfCells")
    , @NamedQuery(name = "Sections.findByProductType", query = "SELECT s FROM Sections s WHERE s.productType = :productType")
    , @NamedQuery(name = "Sections.findBySectionPriority", query = "SELECT s FROM Sections s WHERE s.sectionPriority = :sectionPriority")})
public class Sections implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    
    @Basic(optional = false)
    @Column(nullable = false)
    private int number;
    
    @Basic(optional = false)
    @Column(name = "number_of_cells", nullable = false)
    private int numberOfCells;
    
    @Basic(optional = false)
    @Column(name = "product_type", nullable = false, length = 45)
    private String productType;
    
    @Basic(optional = false)
    @Column(name = "section_priority", nullable = false)
    private int sectionPriority;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sectionId")
    private List<Cells> cellsList;
    
    @JoinColumn(name = "warehouse_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Warehouses warehouseId;
    

    public Sections() {
    }

    public Sections(Integer id) {
        this.id = id;
    }

    public Sections(Integer id, int number, int numberOfCells, String productType, int sectionPriority) {
        this.id = id;
        this.number = number;
        this.numberOfCells = numberOfCells;
        this.productType = productType;
        this.sectionPriority = sectionPriority;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumberOfCells() {
        return numberOfCells;
    }

    public void setNumberOfCells(int numberOfCells) {
        this.numberOfCells = numberOfCells;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public int getSectionPriority() {
        return sectionPriority;
    }

    public void setSectionPriority(int sectionPriority) {
        this.sectionPriority = sectionPriority;
    }

    @XmlTransient
    public List<Cells> getCellsList() {
        return cellsList;
    }

    public void setCellsList(List<Cells> cellsList) {
        this.cellsList = cellsList;
    }

    public Warehouses getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Warehouses warehouseId) {
        this.warehouseId = warehouseId;
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
        if (!(object instanceof Sections)) {
            return false;
        }
        Sections other = (Sections) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return number + "";
    }
    
}
