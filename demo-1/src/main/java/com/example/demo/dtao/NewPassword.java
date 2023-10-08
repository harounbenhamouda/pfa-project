package com.example.demo.dtao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewPassword {
	private String email;
	private String code;
	private String newpassword;

}
