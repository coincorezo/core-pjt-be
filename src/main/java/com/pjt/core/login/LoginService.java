package com.pjt.core.login;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pjt.core.login.dto.LoginHistory;
import com.pjt.core.login.dto.LoginReqDto;
import com.pjt.core.login.dto.UserResDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class LoginService {
	@Autowired
	LoginMapper userMapper;

	/**
	 * <pre>
	 * 로그인 조회
	 * </pre>
	 *
	 * @author KangMinJi (kmj0701@coremethod.co.kr)
	 * @param UserDto
	 * @return
	 */
	public UserResDto login(LoginReqDto loginReqDto, HttpServletRequest request) throws UnknownHostException {
		// 1. 회원 정보 및 비밀번호 조회
		// UserResDto user = new UserResDto();
		UserResDto user = userMapper.login(loginReqDto);
		System.out.println(user);

		// 2. 세션에 회원정보 저장 & 세션 유지시간 설정
		if (user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("loginInfo", user);
			session.setMaxInactiveInterval(60 * 30);
			// 3. 로그인 이력
			// X-Forwarded-For (XFF) 헤더 HTTP 프록시나 로드 밸런서 통해 웹서버 접속
			// 클라이언트의 원 IP 주소를 식별하는 표준 헤더로 쓰이고 있다
			// String ip = request.getHeader("X-Forwarded-For");
			// if (ip == null)
			// ip = request.getRemoteAddr();

			InetAddress ipAddress = InetAddress.getLocalHost();
			System.out.println("현재 아이피 : " + ipAddress.getHostAddress());
			System.out.println("현재 호스트명 : " + ipAddress.getHostName());
			LoginHistory history = new LoginHistory();
			history.setLoginIp(ipAddress.getHostAddress());
			history.setUserId(user.getUserId());

			int saveCount = userMapper.insertLoginLog(history);
			// this.userChk(request, "aaA", "api/login?userId=aaa&userPassword=1234");
		}

		return user;
	}

	public static void userChk(HttpServletRequest request, String token, String URL) {
		Map<String, Object> returnList = new HashMap<String, Object>();
		HttpSession session = request.getSession();

		try {
			URL url = new URL(URL);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("Authorization", "Bearer " + token);
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestMethod("POST");

			try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
				String output;
				StringBuffer sb = new StringBuffer();
				while ((output = in.readLine()) != null) {
					sb.append(output).append("\n");
				}

				if (!sb.equals("")) {
					returnList = new ObjectMapper().readValue(sb.toString(), new TypeReference<Map<String, Object>>() {
					});

				}
			}

		} catch (IOException e) {
			System.out.println("사용자정보가 없습니다");
			e.printStackTrace();
		}

	}

}
