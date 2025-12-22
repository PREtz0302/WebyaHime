package com.example.MiyanoHime;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class HelloController {

	@Autowired
	private DataRepository repository;


	@GetMapping("/list")
	public List<DataSummary> index() {
		return repository.findAll().stream()
				.map(data -> new DataSummary(data.getId(), data.getTitle(), data.getCreatedAt()))
				.toList();
	}

	@PostMapping("/list")
	public OverviewData createData(@RequestBody DataStrings requestData) {

		OverviewData newData = new OverviewData(
				requestData.title(),
				requestData.content()
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
		if(repository.existsById(id)) {
			repository.deleteById(id);
			return "ID: " + id + " を削除しました。";
		}else {
			return "ID: " + id + " は見つかりませんでした。";
		}
	}


}
