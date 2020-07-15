package br.sc.senai.model;

import java.util.List;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "queue")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "users"})
public class Queue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String qrCode;

    private int length;

    private boolean status;

    private String begin;

    private String end;

    @OneToMany(mappedBy = "queue", fetch = FetchType.LAZY)
    private List<User> users;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}