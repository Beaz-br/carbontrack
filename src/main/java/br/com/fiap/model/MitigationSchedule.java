package br.com.fiap.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "mitigation_schedule")
public class MitigationSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    private String title;

    @Temporal(TemporalType.DATE)
    private Date startDt;

    @Column(name = "target_tco2e")
    private Double targetTco2e;

    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartDt() {
        return startDt;
    }

    public void setStartDt(Date startDt) {
        this.startDt = startDt;
    }

    public Double getTargetTco2e() {
        return targetTco2e;
    }

    public void setTargetTco2e(Double targetTco2e) {
        this.targetTco2e = targetTco2e;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
