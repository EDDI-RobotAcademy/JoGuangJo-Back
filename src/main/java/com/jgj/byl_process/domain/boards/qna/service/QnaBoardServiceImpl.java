package com.jgj.byl_process.domain.boards.qna.service;

import com.jgj.byl_process.domain.boards.qna.controller.dto.request.QnaBoardRequest;
import com.jgj.byl_process.domain.boards.qna.controller.dto.response.QnaBoardImgResponse;
import com.jgj.byl_process.domain.boards.qna.controller.dto.response.QnaBoardListResponse;
import com.jgj.byl_process.domain.boards.qna.controller.dto.response.QnaBoardReadResponse;
import com.jgj.byl_process.domain.boards.qna.entity.QnaBoard;
import com.jgj.byl_process.domain.boards.qna.entity.QnaBoardImgResource;
import com.jgj.byl_process.domain.boards.qna.repository.QnaBoardImgRepository;
import com.jgj.byl_process.domain.boards.qna.repository.QnaBoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class QnaBoardServiceImpl implements QnaBoardService {

    final private QnaBoardRepository qnaBoardRepository;

    final private QnaBoardImgRepository qnaBoardImgRepository;

    public QnaBoardServiceImpl(QnaBoardRepository qnaBoardRepository, QnaBoardImgRepository qnaBoardImgRepository) {
        this.qnaBoardRepository = qnaBoardRepository;
        this.qnaBoardImgRepository = qnaBoardImgRepository;
    }

@Override
public void register(List<MultipartFile> imageBase64List, QnaBoardRequest qnaBoardRequest) {
    List<QnaBoardImgResource> qnaBoardImgResourcesList = new ArrayList<>();
    final String uploadPath = "E:/Project/JoGuangJo-Front/src/assets/qnaUploadImgs";

    QnaBoard qnaBoard = new QnaBoard();
    qnaBoard.setTitle(qnaBoardRequest.getTitle());
    qnaBoard.setWriter(qnaBoardRequest.getWriter());
    qnaBoard.setContent(qnaBoardRequest.getContent());

    if (imageBase64List != null && !imageBase64List.isEmpty()) {
        try {
            for (MultipartFile imageBase64 : imageBase64List) {
                // base64 디코딩
                byte[] decodedBytes = Base64.getDecoder().decode(imageBase64.getBytes());

                // 파일 경로 생성
                String fileName = "image_" + System.currentTimeMillis() + ".jpg";
                String filePath = uploadPath + "/" + fileName;

                // 이미지 파일 저장
                File targetFile = new File(filePath);
                FileOutputStream fos = new FileOutputStream(targetFile);
                fos.write(decodedBytes);
                fos.close();

                // 이미지 정보를 저장
                QnaBoardImgResource qnaBoardImgResource = new QnaBoardImgResource(fileName, filePath, "");
                qnaBoardImgResourcesList.add(qnaBoardImgResource);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    qnaBoard.setImages(qnaBoardImgResourcesList);
    // 생성자 생성
    qnaBoardRepository.save(qnaBoard);
    qnaBoardImgRepository.saveAll(qnaBoardImgResourcesList);
}


    @Override
    public List<QnaBoardImgResponse> findQnaBoardImage(Long qnaBoardId) {
        List<QnaBoardImgResource> qnaBoardImgResources = qnaBoardImgRepository.findImagePathByQnaBoardId(qnaBoardId);
        List<QnaBoardImgResponse> qnaBoardImgResponses = new ArrayList<>();

        for (QnaBoardImgResource qnaBoardImgResource: qnaBoardImgResources) {
//            System.out.println("imageResource path: " + imageResource.getImageResourcePath());

            qnaBoardImgResponses.add(new QnaBoardImgResponse(
                    qnaBoardImgResource.getImageResourcePath()));
        }

        return qnaBoardImgResponses;
    }
    @Override
    public List<QnaBoardListResponse> list() {
        List<QnaBoard> QnaBoardList = qnaBoardRepository.findAll();
        List<QnaBoardListResponse> QnaBoardResponseList = new ArrayList<>();

        for (QnaBoard QnaBoard: QnaBoardList) {
            QnaBoardResponseList.add(new QnaBoardListResponse(
                    QnaBoard.getQnaBoardId(), QnaBoard.getTitle(),
                    QnaBoard.getWriter(), QnaBoard.getRegDate()
            ));
        }

        return QnaBoardResponseList;
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
