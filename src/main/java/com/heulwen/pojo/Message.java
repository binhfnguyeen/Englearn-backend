/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.heulwen.pojo;

import java.io.Serializable;
import java.util.Date;
import  jakarta.persistence.Basic;
import  jakarta.persistence.Column;
import  jakarta.persistence.Entity;
import  jakarta.persistence.GeneratedValue;
import  jakarta.persistence.GenerationType;
import  jakarta.persistence.Id;
import  jakarta.persistence.JoinColumn;
import  jakarta.persistence.Lob;
import  jakarta.persistence.ManyToOne;
import  jakarta.persistence.NamedQueries;
import  jakarta.persistence.NamedQuery;
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
@Table(name = "message")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@NamedQueries({
    @NamedQuery(name = "Message.findAll", query = "SELECT m FROM Message m"),
    @NamedQuery(name = "Message.findById", query = "SELECT m FROM Message m WHERE m.id = :id"),
    @NamedQuery(name = "Message.findBySentAt", query = "SELECT m FROM Message m WHERE m.sentAt = :sentAt")})
public class Message implements Serializable {

    static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    Integer id;
    @Lob
    @Column(name = "sender_role")
    String senderRole;
    @Lob
    @Column(name = "content")
    String content;
    @Column(name = "sent_at")
    @Temporal(TemporalType.TIMESTAMP)
    Date sentAt;
    @JoinColumn(name = "conservation_id", referencedColumnName = "id")
    @ManyToOne
    Conservation conservationId;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Message)) {
            return false;
        }
        Message other = (Message) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.heulwen.pojo.Message[ id=" + id + " ]";
    }
    
}
