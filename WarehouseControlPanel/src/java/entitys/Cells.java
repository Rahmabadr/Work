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
    @NamedQuery(name = "Cells.findAll", query = "SELECT c FROM Cells c")
    , @NamedQuery(name = "Cells.findById", query = "SELECT c FROM Cells c WHERE c.id = :id")
    , @NamedQuery(name = "Cells.findByNumber", query = "SELECT c FROM Cells c WHERE c.number = :number")
    , @NamedQuery(name = "Cells.findByArea", query = "SELECT c FROM Cells c WHERE c.area = :area")})
public class Cells implements Serializable {

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
    @Column(nullable = false)
    private double area;
    
    @JoinColumn(name = "section_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Sections sectionId;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cellId")
    private List<CellProduct> cellProductList;
    
    @OneToMany(mappedBy = "cellFromId")
    private List<Tasks> tasksList;
    
    @OneToMany(mappedBy = "cellToId")
    private List<Tasks> tasksList1;

    public Cells() {
    }

    public Cells(Integer id) {
        this.id = id;
    }

    public Cells(Integer id, int number, double area) {
        this.id = id;
        this.number = number;
        this.area = area;
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

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public Sections getSectionId() {
        return sectionId;
    }

    public void setSectionId(Sections sectionId) {
        this.sectionId = sectionId;
    }

    @XmlTransient
    public List<CellProduct> getCellProductList() {
        return cellProductList;
    }

    public void setCellProductList(List<CellProduct> cellProductList) {
        this.cellProductList = cellProductList;
    }

    @XmlTransient
    public List<Tasks> getTasksList() {
        return tasksList;
    }

    public void setTasksList(List<Tasks> tasksList) {
        this.tasksList = tasksList;
    }

    @XmlTransient
    public List<Tasks> getTasksList1() {
        return tasksList1;
    }

    public void setTasksList1(List<Tasks> tasksList1) {
        this.tasksList1 = tasksList1;
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
        if (!(object instanceof Cells)) {
            return false;
        }
        Cells other = (Cells) object;
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
