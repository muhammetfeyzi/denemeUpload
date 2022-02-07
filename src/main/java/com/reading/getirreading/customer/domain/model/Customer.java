package com.reading.getirreading.customer.domain.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Builder
@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
//@Document(collection = "customers")
//@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String surname;

    //@Indexed(unique = true)
    private String email;

    private String phone;

    private String address;

    @CreationTimestamp
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    private  LocalDateTime updateDateTime;
}