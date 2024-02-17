package com.springboot.web.quizzeerr.auth;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtRequest {
	private String userName;
	private String password;
}
