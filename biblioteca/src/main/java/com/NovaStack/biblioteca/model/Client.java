package com.NovaStack.biblioteca.model;

import com.NovaStack.biblioteca.dto.client.ClientRequestDTO;
import com.NovaStack.biblioteca.model.enums.TypeClient;
import jakarta.persistence.*;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeClient typeClient;

    private int reserveLimit;

    @Column(nullable = false)
    private String acessCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Client(ClientRequestDTO requestDTO, User user) {
        this.name = requestDTO.name();
        this.typeClient = TypeClient.fromString(requestDTO.typeClient());
        if(typeClient == TypeClient.SPECIAL){
            this.reserveLimit = 2;
        }else{
            this.reserveLimit = 1;
        }
        this.acessCode = requestDTO.acessCode();
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
    }

    public int getReserveLimit() {
        return reserveLimit;
    }

    public void setReserveLimit(int reserveLimit) {
        this.reserveLimit = reserveLimit;
    }

    public String getAcessCode() {
        return acessCode;
    }

    public void setAcessCode(String acessCode) {
        this.acessCode = acessCode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
