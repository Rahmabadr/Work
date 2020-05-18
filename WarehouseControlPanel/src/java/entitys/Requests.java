package entitys;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(catalog = "warehouse_logistics_system", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Requests.findAll", query = "SELECT r FROM Requests r")
    , @NamedQuery(name = "Requests.findById", query = "SELECT r FROM Requests r WHERE r.id = :id")
    , @NamedQuery(name = "Requests.findByName", query = "SELECT r FROM Requests r WHERE r.name = :name")
    , @NamedQuery(name = "Requests.findByStatus", query = "SELECT r FROM Requests r WHERE r.status = :status")
    , @NamedQuery(name = "Requests.findByIsShippingRequest", query = "SELECT r FROM Requests r WHERE r.isShippingRequest = :isShippingRequest")
    , @NamedQuery(name = "Requests.findByDateOfDelivery", query = "SELECT r FROM Requests r WHERE r.dateOfDelivery = :dateOfDelivery")
    , @NamedQuery(name = "Requests.findByDeadlineDate", query = "SELECT r FROM Requests r WHERE r.deadlineDate = :deadlineDate")})
public class Requests implements Serializable {

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
    private String status;
    
    @Basic(optional = false)
    @Column(name = "is_shipping_request", nullable = false, length = 45)
    private String isShippingRequest;
    
    @Basic(optional = false)
    @Column(name = "date_of_delivery", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateOfDelivery;
    
    @Basic(optional = false)
    @Column(name = "deadline_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date deadlineDate;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "requestId")
    private List<RequestOfficers> requestOfficersList;
    
    @JoinColumn(name = "departament_from_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Departments departamentFromId;
    
    @JoinColumn(name = "request_manager_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Employees requestManagerId;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "requestId")
    private List<RequestProducts> requestProductsList;

    public Requests() {
    }

    public Requests(Integer id) {
        this.id = id;
    }

    public Requests(Integer id, String name, String status, String isShippingRequest, Date dateOfDelivery, Date deadlineDate) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.isShippingRequest = isShippingRequest;
        this.dateOfDelivery = dateOfDelivery;
        this.deadlineDate = deadlineDate;
    }

    public Requests(Integer id, String name, String status, String isShippingRequest,
            Date dateOfDelivery, Date deadlineDate, Departments departamentFromId,
            Employees requestManagerId) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.isShippingRequest = isShippingRequest;
        this.dateOfDelivery = dateOfDelivery;
        this.deadlineDate = deadlineDate;
        this.departamentFromId = departamentFromId;
        this.requestManagerId = requestManagerId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsShippingRequest() {
        return isShippingRequest;
    }

    public void setIsShippingRequest(String isShippingRequest) {
        this.isShippingRequest = isShippingRequest;
    }

    public Date getDateOfDelivery() {
        return dateOfDelivery;
    }

    public void setDateOfDelivery(Date dateOfDelivery) {
        this.dateOfDelivery = dateOfDelivery;
    }

    public Date getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(Date deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    @XmlTransient
    public List<RequestOfficers> getRequestOfficersList() {
        return requestOfficersList;
    }

    public void setRequestOfficersList(List<RequestOfficers> requestOfficersList) {
        this.requestOfficersList = requestOfficersList;
    }

    public Departments getDepartamentFromId() {
        return departamentFromId;
    }

    public void setDepartamentFromId(Departments departamentFromId) {
        this.departamentFromId = departamentFromId;
    }

    public Employees getRequestManagerId() {
        return requestManagerId;
    }

    public void setRequestManagerId(Employees requestManagerId) {
        this.requestManagerId = requestManagerId;
    }

    @XmlTransient
    public List<RequestProducts> getRequestProductsList() {
        return requestProductsList;
    }

    public void setRequestProductsList(List<RequestProducts> requestProductsList) {
        this.requestProductsList = requestProductsList;
    }


    public double findPercent() {
        List<RequestOfficers> requestOfficers = this.getRequestOfficersList();
        List<Tasks> tasksList = new ArrayList<>();
        for (int i = 0; i < requestOfficers.size(); i++) {
            tasksList.addAll(requestOfficers.get(0).getTasksList());
        }
        int countGood = 0, countBad = 0;
        for (int i = 0; i < tasksList.size(); i++) {
            if(tasksList.get(0).getStatus().equals("Выполнен")){
                countGood++;
            } else {
                countBad++;
            }
        }
        
        return (countBad == countGood && countBad == 0) ? 0.0 : countGood/(countGood + countBad)*100;
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
        if (!(object instanceof Requests)) {
            return false;
        }
        Requests other = (Requests) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "#" + id + " - " + name;
    }
    
}
