package org.claimacademy.library.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Integer id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(unique = true, length = 150)
    private String email;

    @Column(name = "join_date", nullable = false)
    private LocalDate joinDate = LocalDate.now();

    // OneToMany defaults to lazy fetching
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Loan> loans = new ArrayList<>();

    public Member() {
        // required by JPA.
    }

    public Member(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Bidirectional association or relational helper
    public void addLoan(Loan loan) {
        loans.add(loan); //The list of loans is updated to include the new loan.
        loan.setMember(this);
    }

    public void removeLoan(Loan loan) {
        loans.remove(loan);
        loan.setMember(null);
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }

    @Override
    public String toString() {
        return "Member{" + "id=" + id + ", name='" + name + '\'' + ", email='" + email + '\'' + ", joinDate=" + joinDate + '}';
    }
}
