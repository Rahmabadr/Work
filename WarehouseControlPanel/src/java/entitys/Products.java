/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitys;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.*;
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
    @NamedQuery(name = "Products.findAll", query = "SELECT p FROM Products p")
    , @NamedQuery(name = "Products.findById", query = "SELECT p FROM Products p WHERE p.id = :id")
    , @NamedQuery(name = "Products.findByName", query = "SELECT p FROM Products p WHERE p.name = :name")
    , @NamedQuery(name = "Products.findByQuantity", query = "SELECT p FROM Products p WHERE p.quantity = :quantity")
    , @NamedQuery(name = "Products.findBySupplier", query = "SELECT p FROM Products p WHERE p.supplier = :supplier")
    , @NamedQuery(name = "Products.findByProductPriority", query = "SELECT p FROM Products p WHERE p.productPriority = :productPriority")})
public class Products implements Serializable {

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
    @Column(nullable = false)
    private int quantity;
    
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String supplier;
    
    @Basic(optional = false)
    @Column(name = "product_priority", nullable = false)
    private int productPriority;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productId")
    private List<CellProduct> cellProductList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productId")
    private List<RequestProducts> requestProductsList;
    

    public Products() {
    }

    public Products(Integer id) {
        this.id = id;
    }

    public Products(Integer id, String name, int quantity, String supplier, int productPriority) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.supplier = supplier;
        this.productPriority = productPriority;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public int getProductPriority() {
        return productPriority;
    }

    public void setProductPriority(int productPriority) {
        this.productPriority = productPriority;
    }

    @XmlTransient
    public List<CellProduct> getCellProductList() {
        return cellProductList;
    }

    public void setCellProductList(List<CellProduct> cellProductList) {
        this.cellProductList = cellProductList;
    }

    @XmlTransient
    public List<RequestProducts> getRequestProductsList() {
        return requestProductsList;
    }

    public void setRequestProductsList(List<RequestProducts> requestProductsList) {
        this.requestProductsList = requestProductsList;
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
        if (!(object instanceof Products)) {
            return false;
        }
        Products other = (Products) object;
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
