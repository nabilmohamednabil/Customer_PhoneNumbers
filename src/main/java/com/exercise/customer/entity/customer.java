package com.exercise.customer.entity;

import java.util.ArrayList;

import java.util.List;

import javax.persistence.CascadeType;

import javax.persistence.Column;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;

import javax.persistence.GenerationType;

import javax.persistence.Id;

import javax.persistence.OneToMany ;

import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

 

@Entity
@Table
public class customer {
              @Id
              @GeneratedValue(strategy=GenerationType.IDENTITY)
              @Column(name="id")
              private Integer id;

              @Column(name="name")
              private String name;

              @Column(name="phone")
              private String phone;

              public customer() {}

              public customer(  String name ,  String phone ) {
                  this.name = name ; 
                  this.phone = phone ; 
                             }

              public Integer getId() {
                        return id;
                         }

              public void setId(Integer id) {
                       this.id = id;
                             }
               
              public String getName() {
                      return name;
                             }
              public void setName(String name) {
                      this.name = name;
                             }
              
               public String getPhone() {
                      return phone;
                             }

                public void setPhone(String phone) {

                     this.phone = phone;

                             }                          

}