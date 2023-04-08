package com.jgj.byl_process.domain.boards.qna.service;

import com.jgj.byl_process.domain.boards.qna.controller.dto.request.QnaBoardRequest;
import com.jgj.byl_process.domain.boards.qna.entity.QnaBoard;
import com.jgj.byl_process.domain.boards.qna.repository.QnaBoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class QnaBoardServiceImpl implements QnaBoardService {

    final private QnaBoardRepository qnaBoardRepository;

    public QnaBoardServiceImpl(QnaBoardRepository qnaBoardRepository) {
        this.qnaBoardRepository = qnaBoardRepository;
    }


    @Override
    public void register(QnaBoardRequest qnaBoardRequest) {
        QnaBoard qnaBoard = new QnaBoard();
        qnaBoard.setTitle(qnaBoardRequest.getTitle());
        qnaBoard.setWriter(qnaBoardRequest.getWriter());
        qnaBoard.setContent(qnaBoardRequest.getContent());

        qnaBoardRepository.save(qnaBoard);
    }
    @Override
    public List<QnaBoard> list() {
        return qnaBoardRepository.findAll(Sort.by(Sort.Direction.DESC, "QnaBoardId"));
    }

    @Override
    public QnaBoardReadResponse read(Long qnaBoardId) {
        Optional<QnaBoard> maybeQnaBoard = qnaBoardRepository.findById(qnaBoardId);

        if (maybeQnaBoard.isEmpty()) {
            log.info("읽을 수가 없습니다.");
            return null;
        }

        QnaBoard qnaBoard = maybeQnaBoard.get();

        QnaBoardReadResponse qnaBoardReadResponse = new QnaBoardReadResponse (
                qnaBoard.getQnaBoardId(), qnaBoard.getTitle(), qnaBoard.getWriter(),
                qnaBoard.getContent(), qnaBoard.getRegDate()
        );

        return qnaBoardReadResponse;
    }

    @Override
    public QnaBoard modify(Long qnaBoardId, List<MultipartFile> imageFileList, QnaBoardRequest qnaBoardRequest) {
        Optional<QnaBoard> maybeQnaBoard = qnaBoardRepository.findById(qnaBoardId);

        if (maybeQnaBoard.isEmpty()) {
            System.out.println("QnaBoard 정보를 찾을 수 없습니다. : " + qnaBoardId);
            return null;
        }

        List<QnaBoardImgResource> qnaBoardImgResourcesList = new ArrayList<>();
        final String uploadPath = "E:/Project/JoGuangJo-Front/src/assets/qnaUploadImgs";

        QnaBoard qnaBoard = maybeQnaBoard.get();
        qnaBoard.setTitle(qnaBoardRequest.getTitle());
        qnaBoard.setContent(qnaBoardRequest.getContent());

        if (imageFileList != null && !imageFileList.isEmpty()) {
            try {
                for (MultipartFile multipartFile : imageFileList) {
                    String originalFilename = multipartFile.getOriginalFilename();
                    log.info("requestFileUploadWithText() - filename: " + originalFilename);

                    // 저장될 파일 경로 생성
                    String filePath = uploadPath + "/" + originalFilename;

                    // 이미지 파일 저장
                    File targetFile = new File(filePath);
                    multipartFile.transferTo(targetFile);

                    // 이미지 정보를 저장
                    QnaBoardImgResource qnaBoardImgResource = new QnaBoardImgResource(originalFilename, filePath, "");
                    qnaBoardImgResourcesList.add(qnaBoardImgResource);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        qnaBoard.setImages(qnaBoardImgResourcesList);
        // 생성자 생성

        qnaBoardImgRepository.saveAll(qnaBoardImgResourcesList);
        qnaBoardRepository.save(qnaBoard);
        return qnaBoard;
    }

    @Override
    public void remove(Long qnaBoardId) { qnaBoardRepository.deleteById(qnaBoardId);}

    @Override
    public Long getLastEntityId() {
        QnaBoard qnaBoard = qnaBoardRepository.findFirstByOrderByQnaBoardIdDesc();
        return qnaBoard.getQnaBoardId();
    }

}
