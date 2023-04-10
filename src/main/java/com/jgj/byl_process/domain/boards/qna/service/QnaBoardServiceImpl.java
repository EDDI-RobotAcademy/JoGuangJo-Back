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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

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
public void register(List<MultipartFile> imageFileList, QnaBoardRequest qnaBoardRequest) {
    List<QnaBoardImgResource> qnaBoardImgResourcesList = new ArrayList<>();
    final String uploadPath = "E:/Project/JoGuangJo-Front/src/assets/qnaUploadImgs";


    QnaBoard qnaBoard = new QnaBoard();
    qnaBoard.setTitle(qnaBoardRequest.getTitle());
    qnaBoard.setWriter(qnaBoardRequest.getWriter());
    qnaBoard.setContent(qnaBoardRequest.getContent());

    String content = qnaBoard.getContent();

    content = content.replaceAll("<img[^>]*>", ""); // <img> 태그 제거
    content = content.replaceAll("<p>", ""); // <p> 태그 시작 부분 제거
    content = content.replaceAll("</p>", ""); // <p> 태그 종료 부분 제거

    // base64로 디코딩 하지 않고 단순히 <img> <p> 태그 replace 후 저장
    qnaBoard.setContent(content);
    
    for (MultipartFile file : imageFileList) {
        String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        String filepath = uploadPath + "/" + filename;

        // 이미지 파일을 디스크에 저장
        try {
            file.transferTo(new File(filepath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // QnaBoardImgResource 객체 생성
        QnaBoardImgResource qnaBoardImgResource = new QnaBoardImgResource();
        qnaBoardImgResource.setQnaBoard(qnaBoard);
        qnaBoardImgResource.setFilePath(filepath);

        // QnaBoardImgResource 객체를 리스트에 추가
        qnaBoardImgResourcesList.add(qnaBoardImgResource);
    }

    qnaBoard.setImages(qnaBoardImgResourcesList);
    qnaBoardRepository.save(qnaBoard);
    qnaBoardImgRepository.saveAll(qnaBoardImgResourcesList);
}

    @Override
    public List<QnaBoardImgResponse> findQnaBoardImage(Long qnaBoardId) {
        List<QnaBoardImgResource> qnaBoardImgResources = qnaBoardImgRepository.findImagePathByQnaBoardId(qnaBoardId);
        List<QnaBoardImgResponse> qnaBoardImgResponses = new ArrayList<>();

        for (QnaBoardImgResource qnaBoardImgResource: qnaBoardImgResources) {

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
        Optional<List<QnaBoardImgResource>> maybeQnaBoardImgList = qnaBoardImgRepository.findByQnaBoard_QnaBoardId(qnaBoardId);

        if (maybeQnaBoard.isEmpty()) {
            log.info("읽을 수가 없습니다.");
            return null;
        }

        QnaBoard qnaBoard = maybeQnaBoard.get();
        List<QnaBoardImgResource> qnaBoardImgResourcesList = maybeQnaBoardImgList.orElse(new ArrayList<>());

        QnaBoardReadResponse qnaBoardReadResponse = new QnaBoardReadResponse(
                qnaBoard.getQnaBoardId(), qnaBoard.getTitle(), qnaBoard.getWriter(),
                qnaBoard.getContent(), qnaBoardImgResourcesList.isEmpty() ? null : qnaBoardImgResourcesList.get(0).getImageResourcePath(), qnaBoard.getRegDate()
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
