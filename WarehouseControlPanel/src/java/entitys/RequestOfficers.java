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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author 
 */
@Entity
@Table(name = "request_officers", catalog = "warehouse_logistics_system", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RequestOfficers.findAll", query = "SELECT r FROM RequestOfficers r")
    , @NamedQuery(name = "RequestOfficers.findById", query = "SELECT r FROM RequestOfficers r WHERE r.id = :id")})
public class RequestOfficers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    
    @JoinColumn(name = "employer_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Employees employerId; 
    
    @JoinColumn(name = "request_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Requests requestId;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "requestOfficerId")
    private List<Tasks> tasksList;

    public RequestOfficers() {
    }
    
    public RequestOfficers(Integer id, Employees employerId, Requests requestId) {
        this.id = id;
        this.employerId = employerId;
        this.requestId = requestId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Employees getEmployerId() {
        return employerId;
    }

    public void setEmployerId(Employees employerId) {
        this.employerId = employerId;
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
        if (!(object instanceof RequestOfficers)) {
            return false;
        }
        RequestOfficers other = (RequestOfficers) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Запрос: \"" + requestId + "\". Работник: \"" + employerId + "\"";
    }
    
}
