package com.nawanolja.backend.module.local.application;

import com.nawanolja.backend.module.local.domain.Local;
import java.util.List;

public interface LocalService {

    List<Local> getSidoList();
    List<Local> getSggList(String fullName);
    List<Local> getSubSggList(String fullName);
    List<Local> getEmdList(String fullName);
}
