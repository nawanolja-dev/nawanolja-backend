package com.nawanolja.backend.module.local.controller.dto;

import com.nawanolja.backend.module.local.domain.Local;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LocalResponse {
    private String code;
    private String name;
    private String fullCode;
    private String fullName;


    public static LocalResponse fromTriplerSetting(Local local) {

        String name = null;
        String code = null;

        switch (local.getLocalType()){
            case sido -> {
                name = local.getSido();
                code = local.getSidoCode();
            }
            case sgg -> {
                name = local.getSgg();
                code = local.getSggCode();
            }
            case emd -> {
                name = local.getEmd();
                code = local.getEmdCode();
            }
            case ri ->{
                name = local.getRi();
                code = local.getRiCode();
            }

        }
        return LocalResponse.builder()
                .code(code)
                .fullCode(local.getCode())
                .name(name)
                .fullName(local.getFullName())
                .build();

    }
}
