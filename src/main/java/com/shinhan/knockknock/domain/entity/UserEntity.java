package com.shinhan.knockknock.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_tb", uniqueConstraints = {@UniqueConstraint(
        name = "user_name_phone_unique",
        columnNames={"user_name", "user_phone"}
)})
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_no")
    private Long userNo;

    @Column(name = "user_id", unique = true)
    @NotNull
    private String userId;

    @Column(name = "user_password")
    @NotNull
    private String userPassword;

    @Column(name = "user_name")
    @NotNull
    private String userName;

    @Column(name = "user_phone")
    @NotNull
    private String userPhone;

    @Column(name = "user_type")
    @NotNull
    @Enumerated(EnumType.STRING)
    private UserRole userType;

    @Column(name = "user_birth")
    private Date userBirth;

    @Column(name = "user_address")
    private String userAddress;

    @Column(name = "user_gender")
    private int userGender;

    @Column(name = "user_height")
    private int userHeight;

    @Column(name = "user_weight")
    private int userWeight;

    @Column(name = "user_disease")
    private String userDisease;

    @Column(name = "user_iswithdraw")
    @NotNull
    private boolean userIsWithdraw;

    @Column(name = "user_joindate")
    private Date userJoinDate;

    @Column(name = "user_simplepassword")
    private String userSimplePassword;
}
