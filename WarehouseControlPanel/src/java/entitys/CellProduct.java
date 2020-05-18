/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitys;

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
 * @author 
 */
@Entity
@Table(name = "cell_product", catalog = "warehouse_logistics_system", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CellProduct.findAll", query = "SELECT c FROM CellProduct c")
    , @NamedQuery(name = "CellProduct.findById", query = "SELECT c FROM CellProduct c WHERE c.id = :id")
    , @NamedQuery(name = "CellProduct.findByQuantity", query = "SELECT c FROM CellProduct c WHERE c.quantity = :quantity")})
public class CellProduct implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id; 
    
    @Basic(optional = false)
    @Column(nullable = false)
    private int quantity;
    
    @JoinColumn(name = "cell_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Cells cellId;
    
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Products productId;

    public CellProduct() {
    }

    public CellProduct(Integer id) {
        this.id = id;
    }

    public CellProduct(Integer id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Cells getCellId() {
        return cellId;
    }

    public void setCellId(Cells cellId) {
        this.cellId = cellId;
    }

    public Products getProductId() {
        return productId;
    }

    public void setProductId(Products productId) {
        this.productId = productId;
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
        if (!(object instanceof CellProduct)) {
            return false;
        }
        CellProduct other = (CellProduct) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "#" + cellId + " - \"" + productId + "\": " + quantity + "шт.";
    }
    
}
