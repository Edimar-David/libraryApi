package com.NovaStack.biblioteca.model;

import com.NovaStack.biblioteca.dto.client.ClientRequestDTO;
import com.NovaStack.biblioteca.model.enums.TypeClient;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String accessCode;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeClient typeClient;
    private int reserveLimit;
    private boolean isBanned;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private List<Loan> loan;


    public Client(ClientRequestDTO requestDTO, User user) {
        this.name = requestDTO.name();
        this.typeClient = TypeClient.fromString(requestDTO.typeClient());
        this.accessCode = requestDTO.accessCode();
        this.user = user;
    }

    public Client() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TypeClient getTypeClient() {
        return typeClient;
    }

    public void setTypeClient(TypeClient typeClient) {
        this.typeClient = typeClient;

        if(typeClient == TypeClient.SPECIAL){
            this.reserveLimit = 2;
        }else{
            this.reserveLimit = 1;
        }
    }

    public int getReserveLimit() {
        return reserveLimit;
    }

    public void setReserveLimit(int reserveLimit) {
        this.reserveLimit = reserveLimit;
    }

    public String getAccessCode() {
        return accessCode;
    }

    public void setAccessCode(String acessCode) {
        this.accessCode = acessCode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isBanned() {
        return isBanned;
    }

    public void setBanned(boolean banned) {
        isBanned = banned;
    }

    public List<Loan> getLoan() {
        return loan;
    }

    public void setLoan(List<Loan> loan) {
        this.loan = loan;
    }
}
