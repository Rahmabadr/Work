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
@Table(name = "request_products", catalog = "warehouse_logistics_system", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RequestProducts.findAll", query = "SELECT r FROM RequestProducts r")
    , @NamedQuery(name = "RequestProducts.findById", query = "SELECT r FROM RequestProducts r WHERE r.id = :id")
    , @NamedQuery(name = "RequestProducts.findByProductQuantity", query = "SELECT r FROM RequestProducts r WHERE r.productQuantity = :productQuantity")})
public class RequestProducts implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    
    @Basic(optional = false)
    @Column(name = "product_quantity", nullable = false)
    private int productQuantity;
    
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Products productId;
    
    @JoinColumn(name = "request_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Requests requestId;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "requestProductId")
    private List<Tasks> tasksList;

    public RequestProducts() {
    }

    public RequestProducts(Integer id) {
        this.id = id;
    }

    public RequestProducts(Integer id, int productQuantity) {
        this.id = id;
        this.productQuantity = productQuantity;
    }

    public RequestProducts(Integer id, int productQuantity, Products productId, Requests requestId) {
        this.id = id;
        this.productQuantity = productQuantity;
        this.productId = productId;
        this.requestId = requestId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public Products getProductId() {
        return productId;
    }

    public void setProductId(Products productId) {
        this.productId = productId;
    }

    public Requests getRequestId() {
        return requestId;
    }

    public void setRequestId(Requests requestId) {
        this.requestId = requestId;
    }

    @XmlTransient
    public List<Tasks> getTasksList() {
        return tasksList;
    }

    public void setTasksList(List<Tasks> tasksList) {
        this.tasksList = tasksList;
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
        if (!(object instanceof RequestProducts)) {
            return false;
        }
        RequestProducts other = (RequestProducts) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Запрос: \"" + requestId + "\". Продукт: \"" + productId + "\"";
    }
    
}
