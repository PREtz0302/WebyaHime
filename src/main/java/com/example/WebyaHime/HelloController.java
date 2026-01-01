package com.example.WebyaHime;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class HelloController {

	@Autowired
	private DataRepository repository;


	@GetMapping("/list")
	public List<DataSummary> getList(@RequestParam(required = false) String category) {
		if(category == null || category.isEmpty()) {
			return repository.findByIsDeletedFalse().stream()
					.map(data -> new DataSummary(data.getId(), data.getTitle(), data.getCategory(), data.getCreatedAt()))
					.toList();
		}else {
			return repository.findByCategoryAndIsDeletedFalse(category).stream()
					.map(data -> new DataSummary(data.getId(), data.getTitle(), data.getCategory(), data.getCreatedAt()))
					.toList();
		}
	}

	@PostMapping("/list")
	public OverviewData createData(@RequestBody DataStrings requestData) {

		OverviewData newData = new OverviewData(
				requestData.title(),
				requestData.content(),
				requestData.category()
		);

		return repository.save(newData);
	}

	@GetMapping("/list/{id}")
	public OverviewData getDataById(@PathVariable int id) {
		return repository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@DeleteMapping("/list/{id}")
	public String deleteData(@PathVariable int id) {
		OverviewData data = repository.findById(id).orElse(null);
		if(data != null) {
			data.setDeleted(true);
			repository.save(data);
			return "ID: " + id + " を削除しました。";
		}else {
			return "ID: " + id + " は見つかりませんでした。";
		}
	}

}
