package com.netcracker.model;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="customer")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="surname")
    @NonNull
    private String surname;

    @Column(name="district")
    @NonNull
    private String district;

    @Column(name="discount")
    @NonNull
    private Integer discount;


}
