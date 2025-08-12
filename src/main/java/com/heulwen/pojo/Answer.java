/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.heulwen.pojo;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "answer")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@NamedQueries({
    @NamedQuery(name = "Answer.findAll", query = "SELECT a FROM Answer a"),
    @NamedQuery(name = "Answer.findById", query = "SELECT a FROM Answer a WHERE a.id = :id")})
public class Answer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    Integer id;
    @JoinColumn(name = "test_results_id", referencedColumnName = "id")
    @ManyToOne
    @JsonBackReference
    TestResults testResultsId;
    @JoinColumn(name = "question_choice_id", referencedColumnName = "id")
    @ManyToOne
    @JsonBackReference
    QuestionChoice questionChoiceId;

    @Override
public int hashCode() {
    // Nếu id là null, hãy hash dựa trên các mối quan hệ
    if (this.id != null) {
        return Objects.hash(this.id);
    }
    // Đây là logic quan trọng cho các đối tượng mới
    return Objects.hash(testResultsId, questionChoiceId);
}

@Override
public boolean equals(Object o) {
    if (this == o) {
        return true;
    }
    if (!(o instanceof Answer)) {
        return false;
    }
    Answer other = (Answer) o;

    // Nếu id có giá trị, so sánh bằng id
    if (this.id != null && other.id != null) {
        return this.id.equals(other.id);
    }

    // Nếu id là null, so sánh dựa trên các mối quan hệ
    return Objects.equals(this.testResultsId, other.testResultsId)
            && Objects.equals(this.questionChoiceId, other.questionChoiceId);
}

    @Override
    public String toString() {
        return "com.heulwen.pojo.Answer[ id=" + id + " ]";
    }

}
