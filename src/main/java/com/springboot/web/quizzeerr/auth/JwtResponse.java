package com.springboot.web.quizzeerr.auth;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class JwtResponse {
	private String jwtToken;
	private String username;
}
