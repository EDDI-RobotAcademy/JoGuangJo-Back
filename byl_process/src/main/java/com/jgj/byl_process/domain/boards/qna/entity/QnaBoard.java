package com.jgj.byl_process.domain.boards.qna.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
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
    @CreationTimestamp
    private Date regDate;
    @UpdateTimestamp
    private Date updDate;
}
