package br.com.fiap.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "emission")
public class Emission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    private int scope;

    @Column(name = "amount_tco2e")
    private Double amountTco2e;

    @Column(name = "reference_dt")
    @Temporal(TemporalType.DATE)
    private Date referenceDate;

    @Column(length = 4000)
    private String notes;

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

    public int getScope() {
        return scope;
    }

    public void setScope(int scope) {
        this.scope = scope;
    }

    public Double getAmountTco2e() {
        return amountTco2e;
    }

    public void setAmountTco2e(Double amountTco2e) {
        this.amountTco2e = amountTco2e;
    }

    public Date getReferenceDate() {
        return referenceDate;
    }

    public void setReferenceDate(Date referenceDate) {
        this.referenceDate = referenceDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
