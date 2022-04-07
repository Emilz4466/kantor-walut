package nowewaluty.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import nowewaluty.service.RatesService;

@RestController
public class RatesController {

	@Autowired
	RatesService service;

	@GetMapping("/rates")
	public String getFirstTenRecords() {
		return service.get().toString();

	}

}
