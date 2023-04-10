package com.jgj.byl_process.domain.boards.qna.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class QnaBoardImgResource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageResourcePath;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "qna_board_id")
    private QnaBoard qnaBoard;

    public QnaBoardImgResource(String originalFilename, String s, String base64Encoded) {
    }

    public void setFilePath(String imageResourcePath) {
        this.imageResourcePath = imageResourcePath;
    }
}
