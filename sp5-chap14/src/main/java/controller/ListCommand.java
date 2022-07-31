package controller;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ListCommand {
	
	@DateTimeFormat(pattern = "yyyyMMddHH")
	private LocalDateTime from;
	
	@DateTimeFormat(pattern = "yyyyMMddHH")
	private LocalDateTime to;
	
}
