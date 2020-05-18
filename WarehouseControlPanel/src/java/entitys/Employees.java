/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitys;

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
import javax.validation.constraints.Pattern;
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
    @NamedQuery(name = "Employees.findAll", query = "SELECT e FROM Employees e")
    , @NamedQuery(name = "Employees.findById", query = "SELECT e FROM Employees e WHERE e.id = :id")
    , @NamedQuery(name = "Employees.findByFullName", query = "SELECT e FROM Employees e WHERE e.fullName = :fullName")
    , @NamedQuery(name = "Employees.findByEmail", query = "SELECT e FROM Employees e WHERE e.email = :email")
    , @NamedQuery(name = "Employees.findByDateOfBirth", query = "SELECT e FROM Employees e WHERE e.dateOfBirth = :dateOfBirth")
    , @NamedQuery(name = "Employees.findByEmploymentDate", query = "SELECT e FROM Employees e WHERE e.employmentDate = :employmentDate")
    , @NamedQuery(name = "Employees.findByPhoneNumber", query = "SELECT e FROM Employees e WHERE e.phoneNumber = :phoneNumber")
    , @NamedQuery(name = "Employees.findByPassword", query = "SELECT e FROM Employees e WHERE e.password = :password")})
public class Employees implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    
    @Basic(optional = false)
    @Column(name = "full_name", nullable = false, length = 45)
    private String fullName;
    
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String email;
    
    @Basic(optional = false)
    @Column(name = "date_of_birth", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    
    @Basic(optional = false)
    @Column(name = "employment_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date employmentDate;
    
    @Basic(optional = false)
    @Column(name = "phone_number", length = 45)
    private String phoneNumber;
    
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String password;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employerId")
    private List<RequestOfficers> requestOfficersList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "requestManagerId")
    private List<Requests> requestsList;
    
    @JoinColumn(name = "postion_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Positions postionId;

    public Employees() {
    }

    public Employees(Integer id) {
        this.id = id;
    }

    public Employees(Integer id, String fullName, String email, Date dateOfBirth, Date employmentDate, String password) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.employmentDate = employmentDate;
        this.password = password;
    }
    
    public void clone(Employees toClone) {
        toClone.id = this.id;
        toClone.fullName = this.fullName;
        toClone.email = this.email;
        toClone.dateOfBirth = this.dateOfBirth;
        toClone.employmentDate = this.employmentDate;
        toClone.password = this.password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(Date employmentDate) {
        this.employmentDate = employmentDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @XmlTransient
    public List<RequestOfficers> getRequestOfficersList() {
        return requestOfficersList;
    }

    public void setRequestOfficersList(List<RequestOfficers> requestOfficersList) {
        this.requestOfficersList = requestOfficersList;
    }

    @XmlTransient
    public List<Requests> getRequestsList() {
        return requestsList;
    }

    public void setRequestsList(List<Requests> requestsList) {
        this.requestsList = requestsList;
    }

    public Positions getPostionId() {
        return postionId;
    }

    public void setPostionId(Positions postionId) {
        this.postionId = postionId;
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
        if (!(object instanceof Employees)) {
            return false;
        }
        Employees other = (Employees) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return fullName;
    }
    
}
