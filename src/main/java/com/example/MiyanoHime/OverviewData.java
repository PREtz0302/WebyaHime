package com.example.MiyanoHime;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

//public record OverviewData(
//		int id,
//		String title,
//		LocalDateTime createdAt,
//		String content
//) {
//	public OverviewData {
//		if(title.isEmpty()) {
//			throw new IllegalArgumentException("タイトルを設定してください。");
//		}
//
//		if(content.length()>5000 || content.isEmpty()) {
//			throw new IllegalArgumentException("概要欄は1~5000文字で登録してください。");
//		}
//	}
//}

@Entity
public class OverviewData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String title;
	private LocalDateTime createdAt;
	String content;

	public OverviewData() {}

	public OverviewData(String title, String content) {
		this.title = title;
		this.content = content;
		createdAt = LocalDateTime.now();
	}

	public Integer getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public String getContent() {
		return content;
	}


}
