package br.sc.senai.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="TB_USER")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    private String name;

    private String telephone;

    //@ManyToOne
    //@JoinColumn(name="queue_id")
    //private Queue queue;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    //public Queue getQueue() {
    // return queue;
    //}

    //public void setQueue(Queue queue) {
    //  this.queue = queue;
    //}
}