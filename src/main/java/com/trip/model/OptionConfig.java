/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trip.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "OPTION_CONFIG")
public class OptionConfig implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "OPTION_KEY", length = 50, unique=true)
    private String key;
    @Column(name = "OPTION_VALUE", length = 100)
    private String value;

    public OptionConfig() {
    }

    public OptionConfig(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    
    @Override
    public String toString() {
        return "com.tripmodel.OptionConfig[ id=" + id + " ]";
    }

   
    
}
