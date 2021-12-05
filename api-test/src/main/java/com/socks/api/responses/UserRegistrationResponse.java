package com.socks.api.responses;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
public class UserRegistrationResponse{

	@JsonProperty("id")
	private String id;
}