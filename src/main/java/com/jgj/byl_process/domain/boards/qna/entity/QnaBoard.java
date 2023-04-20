package com.jgj.byl_process.domain.boards.qna.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Getter
@Setter
public class QnaBoard {

    @Id
    @Column(name = "qna_board_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qnaBoardId;
    @Column(length = 128, nullable = false)
    private String title;
    @Column(length = 32, nullable = false)
    private String writer;
    @Lob
    private String content;
    @ToString.Exclude
    @JsonManagedReference
    @OneToMany(mappedBy = "qnaBoard", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<QnaBoardImgResource> qnaBoardImgResourcesList = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "qnaBoard", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<QnaComment> qnaComments = new ArrayList<>();
    @CreationTimestamp
    private Date regDate;
    @UpdateTimestamp
    private Date updDate;

    public void setImages(List<QnaBoardImgResource> qnaBoardImgResourcesList) {
        this.qnaBoardImgResourcesList = qnaBoardImgResourcesList;
    }
}
