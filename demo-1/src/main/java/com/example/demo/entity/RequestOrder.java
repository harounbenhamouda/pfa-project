package com.example.demo.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class RequestOrder {
	@Id
	  @GeneratedValue(strategy=GenerationType.IDENTITY)
	  private Long id;
	
	
	@Column(name = "code")
    private String code;

    @Column(name = "total_price")
    private int totalPrice;

    @Column(name = "total_quantity")
    private int totalQuantity;
    @Column(name = "data_create")
    @CreationTimestamp
    private Date dataCreate;

    @Column(name = "data_update")
    @UpdateTimestamp
    private Date dataUpdate;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "requestOrder")
    private List<Item> items = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client = new Client();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "to_address_id",referencedColumnName = "id")
    private Address toAddress = new Address();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "from_address_id",referencedColumnName = "id")
    private Address fromAddress = new Address();

    public void addItem(Item item){
        items.add(item);
        item.setRequestOrder(this);
    }



}
