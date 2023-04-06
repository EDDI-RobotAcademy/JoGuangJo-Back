package com.jgj.byl_process.domain.boards.qna.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class QnaComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qnaCommentId;

    @Lob
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "qna_board_id")
    private QnaBoard qnaBoard;

    @Column(length = 32, nullable = false)
    private String writer;


    public void update(String comment) {
        this.comment = comment;
    }

}
