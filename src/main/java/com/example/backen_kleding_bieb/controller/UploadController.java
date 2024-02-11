package com.example.backen_kleding_bieb.controller;

import com.example.backen_kleding_bieb.dto.ItemDto;
import com.example.backen_kleding_bieb.dto.UploadWithItemDto;
import com.example.backen_kleding_bieb.service.ItemService;
import com.example.backen_kleding_bieb.service.UploadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController

public class UploadController {
    private final UploadService service;
    private final ItemService itemService;
    private String id;


    public UploadController(UploadService service, ItemService itemService ) {
        this.itemService = itemService;
        this.service = service;
    }

    @GetMapping("/downloadAllFiles")
    public ResponseEntity<List<UploadWithItemDto>> downloadAllFiles() {
        List<UploadWithItemDto> filesWithItems = new ArrayList<>();

        List<byte[]> files = service.getAllFiles();
        List<ItemDto> items = itemService.getAllItems();

        if (files.size() != items.size()) {

        }
        for (int i = 0; i < files.size(); i++) {
            UploadWithItemDto UploadWithItemDto = new UploadWithItemDto();
            UploadWithItemDto.setFile(files.get(i));
            UploadWithItemDto.setItem(items.get(i));
            filesWithItems.add(UploadWithItemDto);
        }

        return ResponseEntity.ok().body(filesWithItems);
    }


}

