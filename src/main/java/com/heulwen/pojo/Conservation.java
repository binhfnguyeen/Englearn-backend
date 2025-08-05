/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.heulwen.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import  jakarta.persistence.Basic;
import  jakarta.persistence.Column;
import  jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import  jakarta.persistence.GeneratedValue;
import  jakarta.persistence.GenerationType;
import  jakarta.persistence.Id;
import  jakarta.persistence.JoinColumn;
import  jakarta.persistence.ManyToOne;
import  jakarta.persistence.NamedQueries;
import  jakarta.persistence.NamedQuery;
import  jakarta.persistence.OneToMany;
import  jakarta.persistence.Table;
import  jakarta.persistence.Temporal;
import  jakarta.persistence.TemporalType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

/**
 *
 * @author Dell
 */
@Entity
@Table(name = "conservation")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@NamedQueries({
    @NamedQuery(name = "Conservation.findAll", query = "SELECT c FROM Conservation c"),
    @NamedQuery(name = "Conservation.findById", query = "SELECT c FROM Conservation c WHERE c.id = :id"),
    @NamedQuery(name = "Conservation.findByTitle", query = "SELECT c FROM Conservation c WHERE c.title = :title"),
    @NamedQuery(name = "Conservation.findByStartTime", query = "SELECT c FROM Conservation c WHERE c.startTime = :startTime"),
    @NamedQuery(name = "Conservation.findByEndTime", query = "SELECT c FROM Conservation c WHERE c.endTime = :endTime")})
public class Conservation implements Serializable {

    static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    Integer id;
    @Column(name = "title")
    String title;
    @Column(name = "start_time")
    @Temporal(TemporalType.TIMESTAMP)
    Date startTime;
    @Column(name = "end_time")
    @Temporal(TemporalType.TIMESTAMP)
    Date endTime;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    User userId;
    @OneToMany(mappedBy = "conservationId", fetch = FetchType.EAGER)
    Set<Message> messageSet;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Conservation)) {
            return false;
        }
        Conservation other = (Conservation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.heulwen.pojo.Conservation[ id=" + id + " ]";
    }
    
}
