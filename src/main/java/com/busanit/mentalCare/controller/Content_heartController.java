package com.busanit.mentalCare.controller;

import com.busanit.mentalCare.HttpResponseEntity;
import com.busanit.mentalCare.dto.ContentHeartDTO;
import com.busanit.mentalCare.service.ContentHeartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.busanit.mentalCare.HttpResponseEntity.success;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("content/heart")
public class Content_heartController {

    private final ContentHeartService contentHeartService;

    // 공감을 추가하는 기능 (공감 버튼을 눌렀을 때)
    @PostMapping
    public HttpResponseEntity.ResponseResult<?> insert(@RequestBody ContentHeartDTO contentHeartDTO) {
        contentHeartService.delete(contentHeartDTO);
        return success(null);
    }

    // 공감을 취소하는 기능 (공감 버튼을 눌렀을 때)
    @PostMapping
    public HttpResponseEntity.ResponseResult<?> delete(@RequestBody ContentHeartDTO contentHeartDTO) {
        contentHeartService.delete(contentHeartDTO);
        return success(null);
    }



}
