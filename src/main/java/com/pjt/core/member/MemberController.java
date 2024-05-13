package com.pjt.core.member;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/api/member")
    public ResponseEntity<?> getMember(@RequestParam("id") String id) {
        UserDetails user = memberService.loadUserByUsername(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/api/member")
    public ResponseEntity<?> postMember(@RequestBody CreateMemberRequest request) {
        CreateMemberResponse response = memberService.save(request);
        return ResponseEntity.ok(response);
    }

}
