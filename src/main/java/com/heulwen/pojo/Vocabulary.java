/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.heulwen.pojo;

import java.io.Serializable;
import java.util.Set;
import  jakarta.persistence.Basic;
import  jakarta.persistence.Column;
import  jakarta.persistence.Entity;
import  jakarta.persistence.GeneratedValue;
import  jakarta.persistence.GenerationType;
import  jakarta.persistence.Id;
import  jakarta.persistence.JoinColumn;
import  jakarta.persistence.JoinTable;
import  jakarta.persistence.Lob;
import  jakarta.persistence.ManyToMany;
import  jakarta.persistence.NamedQueries;
import  jakarta.persistence.NamedQuery;
import  jakarta.persistence.OneToMany;
import  jakarta.persistence.Table;
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
@Table(name = "vocabulary")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@NamedQueries({
    @NamedQuery(name = "Vocabulary.findAll", query = "SELECT v FROM Vocabulary v"),
    @NamedQuery(name = "Vocabulary.findById", query = "SELECT v FROM Vocabulary v WHERE v.id = :id"),
    @NamedQuery(name = "Vocabulary.findByWord", query = "SELECT v FROM Vocabulary v WHERE v.word = :word"),
    @NamedQuery(name = "Vocabulary.findByPartOfSpeech", query = "SELECT v FROM Vocabulary v WHERE v.partOfSpeech = :partOfSpeech")})
public class Vocabulary implements Serializable {

    static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    Integer id;
    @Column(name = "word")
    String word;
    @Lob
    @Column(name = "meaning")
    String meaning;
    @Column(name = "part_of_speech")
    String partOfSpeech;
    @Lob
    @Column(name = "example")
    String example;
    @JoinTable(name = "vocabulary_topic", joinColumns = {
        @JoinColumn(name = "vocabulary_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "topic_id", referencedColumnName = "id")})
    @ManyToMany
    Set<Topic> topicSet;
    @OneToMany(mappedBy = "vocabularyId")
    Set<LearnedWord> learnedWordSet;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vocabulary)) {
            return false;
        }
        Vocabulary other = (Vocabulary) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.heulwen.pojo.Vocabulary[ id=" + id + " ]";
    }
    
}
