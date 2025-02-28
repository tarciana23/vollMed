package com.br.alura.vollMed.Controller;

import com.br.alura.vollMed.domain.consulta.AgendaDeConsultas;
import com.br.alura.vollMed.domain.consulta.DadosAgendamentoConsulta;
import com.br.alura.vollMed.domain.consulta.DadosDetalhamentoConsulta;
import com.br.alura.vollMed.domain.medico.Especialidade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ConsultaControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DadosAgendamentoConsulta> dadosAgendamentoConsultaJson;

    @Autowired
    private JacksonTester<DadosDetalhamentoConsulta>dadosDetalhamentoConsultaJson;

    @MockBean
    private AgendaDeConsultas agenda;

    @Test
    @DisplayName("Deveria devolver codigo http 400 quando informacoes estao invalidas")
    @WithMockUser//para ignorar a segurança e não dar o erro 403, passa um usuário mockado para simular q está logado
    void agendar_cenario1() throws Exception {
        var response = mvc.perform(post("/consultas"))
            .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver codigo http 200 quando informacoes estao validas")
    @WithMockUser//para ignorar a segurança e não dar o erro 403, passa um usuário mockado para simular q está logado
    void agendar_cenario2() throws Exception {
            var data = LocalDateTime.now().plusHours(1);
            var especialidade = Especialidade.CARDIOLOGIA;

            var dadosDetalhamento = new DadosDetalhamentoConsulta(null, 2l, 5l, data);
            when(agenda.agendar(any())).thenReturn(dadosDetalhamento);

            var response = mvc
                    .perform(
                            post("/consultas")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(dadosAgendamentoConsultaJson.write(
                                            new DadosAgendamentoConsulta(2l, 5l, data, especialidade)
                                    ).getJson())
                    )
                    .andReturn().getResponse();

            assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

            var jsonEsperado = dadosDetalhamentoConsultaJson.write(
                    dadosDetalhamento
            ).getJson();

            assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
        }
    }
