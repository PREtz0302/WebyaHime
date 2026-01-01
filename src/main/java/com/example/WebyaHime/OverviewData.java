package com.example.WebyaHime;

import java.time.LocalDateTime;

import org.springframework.util.StringUtils;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class OverviewData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String title;
	private LocalDateTime createdAt;
	private String content;
	private String category;

	public OverviewData() {}

	public OverviewData(String title, String content, String category) {
		if(!StringUtils.hasText(title)) {
			throw new IllegalArgumentException("タイトルを設定してください。");
		}
		if(!StringUtils.hasText(content) || content.length()>5000) {
			throw new IllegalArgumentException("概要欄は1~5000文字で登録してください");
		}



		this.title = title;
		this.content = content;
		this.category = StringUtils.hasText(category) ? category : "未設定";

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

	public String getCategory() {
		return category;
	}


}
