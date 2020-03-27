package com.eshi.addis.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import org.springframework.data.geo.Point;

import javax.persistence.*;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
//@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="orderMenus")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateCreated;
    @Convert(converter = StatusConverter.class)
    private Status status;

    @OneToMany(mappedBy = "pk.order")
    @Valid
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<OrderMenu> orderMenus = new ArrayList<>();

    private Double totalPrice;
    private Double tax;
    private Double subTotal;
    private String specialNotes;
    private LocalDateTime deliveryTime;
    private Point deliveryLocation;


    @Transient
    public Double getSubTotalOrderPrice() {
        double sum = 0D;
        List<OrderMenu> orderProducts = getOrderMenus();
        for (OrderMenu om : orderProducts) {
            sum += om.getTotalPrice();
        }

        return sum;
    }

    @Transient
    public Double getTax() {
        return getSubTotalOrderPrice()*0.15;
    }
    @Transient
    public Double getTotalOrderPrice(){
        return getTax()+getSubTotalOrderPrice();
    }

    @Transient
    public int getNumberOfMenus() {
        return this.orderMenus.size();
    }

}
