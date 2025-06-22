package br.com.amitta.real_state;

import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.amitta.real_state.entities.RealState;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RealStateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private RealState sample;

    @BeforeAll
    void setup() {
        sample = new RealState(null, "123 Test Ave", 100000.0, "Test property");
    }

    @Test
    void shouldCreateRealState() throws Exception {
        mockMvc.perform(post("/realstates")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sample)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    void shouldGetAllRealStates() throws Exception {
        mockMvc.perform(get("/realstates"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void shouldUpdateRealState() throws Exception {
        MvcResult result = mockMvc.perform(post("/realstates")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sample)))
                .andReturn();

        RealState created = objectMapper.readValue(result.getResponse().getContentAsString(), RealState.class);
        created.setPrice(200000.0);

        mockMvc.perform(put("/realstates/" + created.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(created)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(200000.0));
    }

    @Test
    void shouldDeleteRealState() throws Exception {
        MvcResult result = mockMvc.perform(post("/realstates")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sample)))
                .andReturn();

        RealState created = objectMapper.readValue(result.getResponse().getContentAsString(), RealState.class);

        mockMvc.perform(delete("/realstates/" + created.getId()))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldRejectMissingAddress() throws Exception {
        RealState invalid = new RealState(null, "", 150000.0, "No address");

        mockMvc.perform(post("/realstates")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalid)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors").exists());
    }

    @Test
    void shouldRejectNullPrice() throws Exception {
        RealState invalid = new RealState(null, "456 Broken St", null, "Missing price");

        mockMvc.perform(post("/realstates")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalid)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors").exists());
    }

    @Test
    void shouldReturnNotFoundForInvalidUUID() throws Exception {
        UUID fakeId = UUID.randomUUID();

        mockMvc.perform(get("/realstates/" + fakeId))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnBadRequestForMalformedUUID() throws Exception {
        mockMvc.perform(get("/realstates/not-a-uuid"))
                .andExpect(status().isBadRequest());
    }
}