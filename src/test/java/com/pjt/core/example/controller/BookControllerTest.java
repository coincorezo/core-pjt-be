package com.pjt.core.example.controller;

import com.pjt.core.example.ControllerTestSupport;
import com.pjt.core.example.dto.CreateBookRequest;
import com.pjt.core.example.service.BookService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class BookControllerTest extends ControllerTestSupport {

	@MockBean
	private BookService bookService;

	@DisplayName("책의 제목, 부제, 글쓴이를 입력받아 책을 등록한다.")
	@Test
	void registerBook() throws Exception {
		// given
		String bookCode = "A00001";
		String title = "객체지향의 사실과 오해";
		String subtitle = "역할, 책임, 협력 관점에서 본 객체지향";
		String writer = "조영호";

		CreateBookRequest createBookRequest = CreateBookRequest.builder()
				.bookCode(bookCode)
				.title(title)
				.subtitle(subtitle)
				.writer(writer)
				.build();

		// when then
		mockMvc.perform(post("/api/book")
						.content(objectMapper.writeValueAsString(createBookRequest))
						.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isCreated());
	}

}
