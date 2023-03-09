package gr.kariera.mindthecode.MyFirstProject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gr.kariera.mindthecode.MyFirstProject.DTOs.ProductWithQuantityDto;
import jakarta.persistence.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "orders")
public class Order {
    @Id
    @GeneratedValue
    private Integer id;

    public Order() {
        this.orderProducts = new ArrayList<>();
    }

    public Order(Collection<OrderProduct> products) {
        this.orderProducts = products;
    }

    public Order(String address) {
        this.address = address;
        this.orderProducts = new ArrayList<>();
    }

    public Order(Collection<OrderProduct> products, String address) {
        this.orderProducts = products;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Collection<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(Collection<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    private Collection<OrderProduct> orderProducts = new ArrayList<>();

    @Transient
    private Collection<ProductWithQuantityDto> products;

    public Collection<ProductWithQuantityDto>  getProducts() {
        return orderProducts
                .stream()
                .map(op -> {
                    ProductWithQuantityDto pwq = new ProductWithQuantityDto();
                    pwq.setProductId(op.getProduct().getId());
                    pwq.setQuantity(op.getQuantity());
                    return pwq;
                })
                .collect(Collectors.toList());
    }


    private String address;
}
