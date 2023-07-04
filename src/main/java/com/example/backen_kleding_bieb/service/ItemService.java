package com.example.backen_kleding_bieb.service;


import com.example.backen_kleding_bieb.dto.ItemDto;
import com.example.backen_kleding_bieb.exceptions.RecordNotFoundException;
import com.example.backen_kleding_bieb.models.Item;
import com.example.backen_kleding_bieb.repository.ItemRepository;
import jakarta.persistence.criteria.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {


    private final ItemRepository itemRepository;
    private Item Item;

    private UploadRepository uploadRepository;

    public ItemService(ItemRepository itemRepository, UploadRepository uploadRepository, UploadRepository uploadRepository2) {

        this.itemRepository = itemRepository;

        this.uploadRepository = uploadRepository;

    }


    public List<ItemDto> getAllItems() {
        List<Item>items = itemRepository.findAll();
        ArrayList<ItemDto> itemDtos = new ArrayList<>();
        for (Item Item : items) {
            ItemDto itemDto = transferItemToItemDto(Item);
            itemDtos.add(itemDto);
        }
        return itemDtos;
    }

    public ItemDto getItem(Long id) {
        Optional<com.example.backen_kleding_bieb.models.Item> optionalItem = itemRepository.findById(id);
        if (optionalItem.isPresent()) {
            Item item1 = optionalItem.get();
            return transferItemToItemDto(item1);
        } else {
            throw new RecordNotFoundException("No Item found with id: " + id + ".");
        }
    }

    public Long createItem(ItemDto itemDto) {
        Item newItem;
        newItem = transferItemDtoToItem(itemDto);
        Item savedItem = itemRepository.save(newItem);
       return savedItem.getId();
    }

    public ItemDto putItem(Long id, ItemDto itemDto) {
        {
            if (itemRepository.findById(id).isPresent()) {
                Item item = itemRepository.findById(id).get();
                Item item1 = transferItemDtoToItem(itemDto);
              item1.setId(item.getId());
                itemRepository.save(item1);
                return transferItemToItemDto(item1);
            } else {
                throw new RecordNotFoundException("No Item found with id: " + id + ".");
            }
        }
    }

    public ItemDto patchItem(Long id, ItemDto itemDto) {
        Optional<Item> optionalItem = itemRepository.findById(id);
        if (itemRepository.existsById(id)) {
            Item itemToUpdate = optionalItem.get();

            if (itemDto.getNameInfo() != null) {
                itemToUpdate.setNameInfo(itemDto.getNameInfo());
            }
           // if (itemDto.getQuality() != null) {
            //    itemToUpdate.setQuality(itemDto.getQuality());
           // }

            Item savedItem = itemRepository.save(itemToUpdate);
            return transferItemToItemDto(savedItem);
        } else {
            throw new RecordNotFoundException("No item with id " + id);
        }
    }

    public String deleteById(Long id) {
        if (itemRepository.existsById(id)) {
            Optional<Item> deletedItem = itemRepository.findById(id);
            Item item1 = deletedItem.get();
            itemRepository.delete(item1);
            return "Item with id: " + id + " deleted.";
        } else {
            throw new RecordNotFoundException("No Item found with id: " + id + ".");
        }
    }
    public void assignPhotoToItem(String fileName, Long id) {
        Optional<Item> optionalItem = itemRepository.findById(id);
        Optional<Upload> Upload= uploadRepository.findByFileName(fileName);

        //Optional<Upload> fileUploadResponse = uploadRepository.findByFileName(fileName);
        //if (optionalItem.isPresent() && fileUploadResponse.isPresent()) {
           // fileUploadResponse photo = fileUploadResponse.get();

            if (optionalItem.isPresent() && UploadisPresent()) {
           Upload photo = Upload.get();
            Item item = optionalItem.get();
            item.setUpload(photo);
            //item.setUpload(photo);
            itemRepository.save(item);
        }
    }

    private boolean UploadisPresent() {
        return false;
    }


    //    helper methods.......................................................
    private ItemDto transferItemToItemDto (Item item) {
        ItemDto itemDto = new ItemDto();
        itemDto.setUser(item.getUser());
       itemDto.setId(item.getId());
        itemDto.setNameInfo(item.getNameInfo());
        //itemDto.setUploadFileName(item.getUploadFileName());
    //    itemDto.setQuality(item.getQuality());
        if (item.getUser() != null) {
            itemDto.setUser(item.getUser());
        }
        if (item.getOrder() != null) {
            itemDto.setOrder((Order) item.getOrder());
        }

        return itemDto;
    }

    private Item transferItemDtoToItem (ItemDto itemDto) {
        Item item = new Item();
        item.setUser((User) itemDto.getUser());
       item.setId(itemDto.getId());
        item.setNameInfo(itemDto.getNameInfo());
     //   item.setUploadFileName(itemDto.getUploadFileName());
    //    item.setQuality(itemDto.getQuality());

        return item;
    }

    public List<Item> transferItemDtoListToItemList(List<ItemDto> itemsdtos) {
        List<Item> items = new ArrayList<>();
        for (ItemDto itemsdto : itemsdtos) {
            items.add(transferItemDtoToItem(itemsdto));
        }
        return items;
    }


}