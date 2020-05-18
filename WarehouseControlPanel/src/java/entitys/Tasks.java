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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 
 */
@Entity
@Table(catalog = "warehouse_logistics_system", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tasks.findAll", query = "SELECT t FROM Tasks t")
    , @NamedQuery(name = "Tasks.findById", query = "SELECT t FROM Tasks t WHERE t.id = :id")
    , @NamedQuery(name = "Tasks.findByStatus", query = "SELECT t FROM Tasks t WHERE t.status = :status")
    , @NamedQuery(name = "Tasks.findForEmployee", query = "SELECT t FROM Tasks t WHERE t.status = :status ORDER BY t.id ASC")
})
public class Tasks implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String status;
    
    @JoinColumn(nullable = true, name = "cell_from_id", referencedColumnName = "id")
    @ManyToOne
    private Cells cellFromId;
    
    @JoinColumn(nullable = true, name = "cell_to_id", referencedColumnName = "id")
    @ManyToOne
    private Cells cellToId;
    
    @JoinColumn(name = "request_officer_id", referencedColumnName = "id", nullable = true)
    @ManyToOne(optional = false)
    private RequestOfficers requestOfficerId;
    
    @JoinColumn(name = "request_product_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private RequestProducts requestProductId;

    public Tasks() {
    }

    public Tasks(Integer id) {
        this.id = id;
    }

    public Tasks(Integer id, String status, Cells cellFromId, Cells cellToId,
            RequestOfficers requestOfficerId, RequestProducts requestProductId) {
        this.id = id;
        this.status = status;
        this.cellFromId = cellFromId;
        this.cellToId = cellToId;
        this.requestOfficerId = requestOfficerId;
        this.requestProductId = requestProductId;
    }
    
    public Tasks(Integer id, String status) {
        this.id = id;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Cells getCellFromId() {
        return cellFromId;
    }

    public void setCellFromId(Cells cellFromId) {
        this.cellFromId = cellFromId;
    }

    public Cells getCellToId() {
        return cellToId;
    }

    public void setCellToId(Cells cellToId) {
        this.cellToId = cellToId;
    }

    public RequestOfficers getRequestOfficerId() {
        return requestOfficerId;
    }

    public void setRequestOfficerId(RequestOfficers requestOfficerId) {
        this.requestOfficerId = requestOfficerId;
    }

    public RequestProducts getRequestProductId() {
        return requestProductId;
    }

    public void setRequestProductId(RequestProducts requestProductId) {
        this.requestProductId = requestProductId;
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
        if (!(object instanceof Tasks)) {
            return false;
        }
        Tasks other = (Tasks) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  cellFromId + " - " + cellToId + ". Продукт: \"" + requestProductId + ". " + requestProductId;
    }
    
}
