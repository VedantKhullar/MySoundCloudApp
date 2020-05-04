package com.upgrad.musichoster.api.controller;


import com.upgrad.musichoster.api.model.MusicUploadRequest;
import com.upgrad.musichoster.api.model.MusicUploadResponse;
import com.upgrad.musichoster.service.business.MusicUploadService;
import com.upgrad.musichoster.service.entity.MusicEntity;
import com.upgrad.musichoster.service.exception.UploadFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.time.ZonedDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/")
public class MusicUploadController {

    @Autowired
    private MusicUploadService musicUploadService;
//    @Autowired
//    QuestionsService questionsService;
//    @RequestMapping(method = RequestMethod.POST,path ="/question/create",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public ResponseEntity<QuestionResponse> createQuestion(final QuestionRequest questionRequest, @RequestHeader("authorization") final String accessToken) throws AuthorizationFailedException {
//        QuestionEntity questionEntity = new QuestionEntity();
//        questionEntity.setContent(questionRequest.getContent());
//        String uuid = questionsService.createQuestion(questionEntity,accessToken);
//        QuestionResponse questionResponse = new QuestionResponse().id(uuid).status("QUESTION CREATED");
//        return new ResponseEntity<QuestionResponse>(questionResponse, HttpStatus.CREATED);
//    }

    @RequestMapping(method = RequestMethod.POST, path = "/musicupload", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<MusicUploadResponse> musicupload(@RequestBody(required = false) final MusicUploadRequest musicUploadRequest, @RequestHeader("authorization") final String authorization) throws
            UploadFailedException, UnsupportedEncodingException {
        final MusicEntity musicEntity = new MusicEntity();

        musicEntity.setMusic(musicUploadRequest.getMusic());
        musicEntity.setDescription(musicUploadRequest.getDescription());
        musicEntity.setName(musicUploadRequest.getName());

        final MusicEntity createdmusicEntity = musicUploadService.upload(musicEntity, authorization);

        MusicUploadResponse uploadResponse = new MusicUploadResponse().id(String.valueOf(musicEntity.getId())).status("Music Uploaded");

        return new ResponseEntity<>(uploadResponse,HttpStatus.OK);
    }
}
