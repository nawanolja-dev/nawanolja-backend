package com.nawanolja.backend.module.local.application;

import com.nawanolja.backend.module.local.domain.Local;
import com.nawanolja.backend.module.local.domain.repository.LocalRepository;
import com.nawanolja.backend.module.local.domain.vo.LocalType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LocalServiceImpl implements LocalService{
    private final LocalRepository localRepository;

    public List<Local> getSidoList(){
        return localRepository.findByLocalType(LocalType.sido);
    }

    public List<Local> getSggList(String fullName){
        return localRepository.findByFullNameContainingAndLocalTypeAndSggCodeEndingWith(fullName,LocalType.sgg,"0");
    }

    public List<Local> getSubSggList(String fullName){
        List<Local> subSggList = localRepository.findByFullNameContainingAndLocalType(fullName, LocalType.sgg)
                .stream().filter(local -> !local.getSggCode().endsWith("0"))
                .collect(Collectors.toList());

        if(subSggList.isEmpty()){
            return localRepository.findByFullNameContainingAndLocalType(fullName, LocalType.emd);
        }else {
            return subSggList;
        }
    }

    public List<Local> getEmdList(String fullName){
        List<Local> riList = localRepository.findByFullNameContainingAndLocalType(fullName, LocalType.ri);
        if(riList.isEmpty()){
            return localRepository.findByFullNameContainingAndLocalType(fullName, LocalType.emd);
        }else{
            return riList;
        }
    }
}
