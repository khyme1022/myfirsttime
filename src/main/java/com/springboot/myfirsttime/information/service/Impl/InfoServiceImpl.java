package com.springboot.myfirsttime.information.service.Impl;

import com.springboot.myfirsttime.information.data.dto.InformationResponseDto;
import com.springboot.myfirsttime.information.data.repository.InformationRepository;
import com.springboot.myfirsttime.information.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class InfoServiceImpl implements InfoService {

    public InformationRepository informationRepository;

    @Autowired
    public InfoServiceImpl(InformationRepository informationRepository) {
        this.informationRepository = informationRepository;
    }

    @Override
    public void saveInfo() {

    }

    @Override
    public List<InformationResponseDto> showBoardList(int pageNum) {
        return null;
    }

    @Override
    public InformationResponseDto showBoard(int boardNum) {
        return null;
    }
}
