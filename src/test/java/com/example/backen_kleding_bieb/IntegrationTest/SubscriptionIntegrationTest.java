//
//package com.example.backen_kleding_bieb.IntegrationTest;
//
//import com.example.backen_kleding_bieb.dto.SubscriptionDto;
//import com.example.backen_kleding_bieb.models.Subscription;
//import com.example.backen_kleding_bieb.repository.SubscriptionRepository;
//import com.example.backen_kleding_bieb.service.SubscriptionService;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.SerializationFeature;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.time.LocalDate;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc(addFilters = false)
//@ActiveProfiles("test")
//
//
//
//
//
//
//    class SubscriptionIntegrationTest {
//
//        @Autowired
//        private MockMvc mockMvc;
//
//
//        @Autowired
//        private SubscriptionService subscriptionService;
//
//
//        @Autowired
//        private SubscriptionRepository subscriptionRepository;
//
//
//        Subscription subscription100;
//        Subscription subscription200;
//
//
//
//        SubscriptionDto subscriptionDto1;
//
//        SubscriptionDto subscriptionDto2;
//        SubscriptionDto subscriptionDto3;
//
//        @BeforeEach
//        void setUp() {
//
//
//            if (subscriptionRepository.count()>0) {
//                subscriptionRepository.deleteAll();
//            }
//
//            subscription100 = new Subscription(1L, "annual", LocalDate.of(2023, 11, 30), null, null  );
//            subscription200 = new Subscription(2L, "annual", LocalDate.of(2023, 12, 31), null, null);
//
//            subscription100 = subscriptionRepository.save(subscription100);
//            subscription200 = subscriptionRepository.save(subscription200);
//
//
//            subscriptionDto1 = new SubscriptionDto(subscription100.getId(), "annual", LocalDate.of(2023, 11, 30), null, null);
//            subscriptionDto2 = new SubscriptionDto(subscription200.getId(), "annual", LocalDate.of(2023, 12, 31), null, null);
//            subscriptionDto3 = new SubscriptionDto(3L, "annual", LocalDate.of(2023, 12, 31), null, null);
//        }
//
//        @Test
//        void GetAllSubscriptions() throws Exception {
//            mockMvc.perform(get("/subscriptions"))
//
//
//                    .andExpect(status().isOk())
//                    .andExpect(jsonPath("$[0].id").value(subscription100.getId().toString()))
//                    .andExpect(jsonPath("$[0].typeSubscription").value("annual"))
//                    .andExpect(jsonPath("$[0].expirationDate").value(LocalDate.of(2023, 11, 30).toString()))
//                    .andExpect(jsonPath("$[0].subscriptionStatus").isEmpty())
//                    .andExpect(jsonPath("$[0].account").isEmpty())
//
//
//                    .andExpect(jsonPath("$[1].id").value(subscription200.getId().toString()))
//                    .andExpect(jsonPath("$[1].typeSubscription").value("annual"))
//                    .andExpect(jsonPath("$[1].expirationDate").value(LocalDate.of(2023, 12, 31).toString()))
//                    .andExpect(jsonPath("$[1].subscriptionStatus").isEmpty())
//                    .andExpect(jsonPath("$[1].account").isEmpty());
//        }
//
//
//        @Test
//        void getSubscription() throws Exception {
//
//            mockMvc.perform(get("/subscriptions/" + subscription100.getId()))
//
//                    .andExpect(status().isOk())
//                    .andExpect(jsonPath("id").value(subscription100.getId().toString()))
//                    .andExpect(jsonPath("typeSubscription").value("annual"))
//                    .andExpect(jsonPath("expirationDate").value(LocalDate.of(2023, 11, 30).toString()))
//                    .andExpect(jsonPath("subscriptionStatus").isEmpty())
//                    .andExpect(jsonPath("account").isEmpty());
//        }
//
//
//        @Test
//        void createSubscription() throws Exception {
//
//            mockMvc.perform(post("/subscriptions")
//                            .contentType(MediaType.APPLICATION_JSON)
//                            .content(asJsonString(subscriptionDto1)))
//                    .andExpect(status().isCreated());
//        }
//
//
//        @Test
//        void deleteSubscription() throws Exception {
//
//
//            mockMvc.perform(delete("/subscriptions/" + subscription100.getId().toString()))
//                    .andExpect(status().isNoContent());
//        }
//
//
//        public static String asJsonString(final Object obj) {
//            try {
//                ObjectMapper mapper = new ObjectMapper();
//                mapper.registerModule(new JavaTimeModule());
//                mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//                return mapper.writeValueAsString(obj);
//            } catch (JsonProcessingException e) {
//                throw new RuntimeException(e);
//            }
//        }
//
//    }
//
