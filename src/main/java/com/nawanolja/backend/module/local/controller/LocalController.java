package com.nawanolja.backend.module.local.controller;


import com.nawanolja.backend.core.dto.ApiResponse;
import com.nawanolja.backend.module.local.application.LocalService;
import com.nawanolja.backend.module.local.controller.dto.LocalResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/local")
@RequiredArgsConstructor
public class LocalController {

    private final LocalService localService;

    @GetMapping("/sido-list")
    public ApiResponse<List<LocalResponse>> getSidoList() {
        List<LocalResponse> list = localService.getSidoList()
                .stream().map(LocalResponse::fromTriplerSetting).collect(Collectors.toList());
        return ApiResponse.ok(list);
    }

    @GetMapping("sgg-list")
    public ApiResponse<List<LocalResponse>> getSggList(@RequestParam("fullName") String fullName){
        List<LocalResponse> list = localService.getSggList(fullName)
                .stream().map(LocalResponse::fromTriplerSetting).collect(Collectors.toList());
        return ApiResponse.ok(list);
    }

    @GetMapping("sub-sgg-list")
    public ApiResponse<List<LocalResponse>>  getSubSggList(@RequestParam("fullName") String fullName){
        List<LocalResponse> list = localService.getSubSggList(fullName)
                .stream().map(LocalResponse::fromTriplerSetting).collect(Collectors.toList());
        return ApiResponse.ok(list);
    }

    @GetMapping("emd-ri-list")
    public ApiResponse<List<LocalResponse>>  getEmdList(@RequestParam("fullName") String fullName){
        List<LocalResponse> list = localService.getEmdList(fullName)
                .stream().map(LocalResponse::fromTriplerSetting).collect(Collectors.toList());
        return ApiResponse.ok(list);
    }
}
