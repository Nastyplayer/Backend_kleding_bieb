package com.example.backen_kleding_bieb.IntegrationTest;

import com.example.backen_kleding_bieb.dto.ItemDto;
import com.example.backen_kleding_bieb.models.Item;
import com.example.backen_kleding_bieb.models.User;
import com.example.backen_kleding_bieb.repository.ItemRepository;
import com.example.backen_kleding_bieb.service.ItemService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")



public class ItemIntegrationTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ItemService itemService;
    @Autowired
    ItemRepository itemRepository;
    Item item100;
    Item item200;


    private User user1;
    private User user2;
    ItemDto itemDto1;
    ItemDto itemDto2;
    ItemDto itemDto3;
    @BeforeEach
    void setUp() {
        if(itemRepository.count()>0) {
            itemRepository.deleteAll();
        }
        item100 = new Item(1L, user1, "blouse", null, null,  null );
        item200 = new Item(2L, user2,"hat", null, null,  null );


        item100 = itemRepository.save(item100);
        item200 = itemRepository.save(item200);


        itemDto1 = new ItemDto(item100.getId(), "blouse", null, null, user1, null);
        itemDto2 = new ItemDto(item200.getId(), "hat", null, null, user2, null );
        itemDto3 = new ItemDto(2L, "hat", null, null, user2, null );
    }
    @Test
    public void GetAllItems() throws Exception {

        mockMvc.perform(get("/items"))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(item100.getId()))
                .andExpect(jsonPath("$[0].nameInfo").value("blouse"))
                .andExpect(jsonPath("$[0].tags").isEmpty())
                .andExpect(jsonPath("$[0].orders").isEmpty())
                .andExpect(jsonPath("$[0].user").value(user1))
                .andExpect(jsonPath("$[0].upload").isEmpty())




                .andExpect(jsonPath("$[1].id").value(item200.getId()))
                .andExpect(jsonPath("$[1].nameInfo").value("hat"))
                .andExpect(jsonPath("$[1].tags").isEmpty())
                .andExpect(jsonPath("$[1].orders").isEmpty())
                .andExpect(jsonPath("$[1].user").value(user2))
                .andExpect(jsonPath("$[1].upload").isEmpty());

    }


    @Test
    void getItem() throws Exception {

        mockMvc.perform(get("/items/" + item100.getId()))


                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1))
                .andExpect(jsonPath("nameInfo").value("blouse"))
                .andExpect(jsonPath("tags").isEmpty())
                .andExpect(jsonPath("orders").isEmpty())
                .andExpect(jsonPath("user").value(user1))
                .andExpect(jsonPath("upload").isEmpty());


    }


    @Test
    void createItem() throws Exception {

        mockMvc.perform(post("/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(itemDto1)))
                .andExpect(status().isCreated());
    }


    @Test
    void deleteItem() throws Exception{


        mockMvc.perform(delete("/items/"  + item100.getId().toString()))
                .andExpect(status().isNoContent());
    }


    public static String asJsonString(final Object obj) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }}}



