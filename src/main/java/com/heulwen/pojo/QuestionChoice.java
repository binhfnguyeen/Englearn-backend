/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.heulwen.pojo;

import java.io.Serializable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import java.util.Objects;
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
@Table(name = "question_choice")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@NamedQueries({
    @NamedQuery(name = "QuestionChoice.findAll", query = "SELECT q FROM QuestionChoice q"),
    @NamedQuery(name = "QuestionChoice.findById", query = "SELECT q FROM QuestionChoice q WHERE q.id = :id"),
    @NamedQuery(name = "QuestionChoice.findByIsCorrect", query = "SELECT q FROM QuestionChoice q WHERE q.isCorrect = :isCorrect")})
public class QuestionChoice implements Serializable {

    static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    Integer id;
    @Column(name = "isCorrect")
    Boolean isCorrect;
    @JoinColumn(name = "vocabulary_id", referencedColumnName = "id")
    @ManyToOne
    Vocabulary vocabularyId;
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    @ManyToOne
    Question questionId;

    @Override
    public int hashCode() {
        return Objects.hash(vocabularyId, questionId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QuestionChoice)) {
            return false;
        }

        QuestionChoice that = (QuestionChoice) o;

        return Objects.equals(vocabularyId, that.vocabularyId)
                && Objects.equals(questionId, that.questionId);
    }

    @Override
    public String toString() {
        return "com.heulwen.pojo.QuestionChoice[ id=" + id + " ]";
    }

}
