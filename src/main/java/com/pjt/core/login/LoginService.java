package com.pjt.core.login;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	 * @throws UnknownHostException
	 * @throws
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

		}
		return user;

	}

}
