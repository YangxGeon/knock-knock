package com.shinhan.knockknock.service;

import com.shinhan.knockknock.domain.dto.WelfareBookRequest;
import com.shinhan.knockknock.domain.dto.WelfareBookResponse;
import com.shinhan.knockknock.domain.entity.WelfareBookEntity;
import com.shinhan.knockknock.repository.WelfareBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class WelfareBookServiceImpl implements WelfareBookService{

    @Autowired
    WelfareBookRepository  welfareBookRepo;

    @Override
    public Long createWelfareBook(WelfareBookRequest request) {
        WelfareBookEntity newWelfareBook = welfareBookRepo.save(dtoToEntity(request));
        return newWelfareBook.getWelfareBookNo();
    }

    @Override
    public List<WelfareBookResponse> readAll(Long welfareBookNo) {
        List<WelfareBookEntity> entityList = welfareBookRepo.findAllById(Collections.singleton(welfareBookNo));
        Function<WelfareBookEntity, WelfareBookResponse> fn = en->entityToDto(en);
        return entityList.stream().map(fn).collect(Collectors.toList());
    }

    @Override
    public WelfareBookResponse readDetail(Long welfareBookNo) {
        Optional<WelfareBookEntity> entity = welfareBookRepo.findById(welfareBookNo);
        return entity.map(this::entityToDto).orElse(null);
    }


    @Override
    public void deleteWelfareBook(Long welfareBookNo) {
        welfareBookRepo.deleteById(welfareBookNo);
    }
}
