package com.carsite.controller;


import com.carsite.entity.carEntity.Make;
import com.carsite.entity.carEntity.MakeModel;
import com.carsite.entity.carEntity.Trim;
import com.carsite.model.MakeModelResponse;
import com.carsite.model.MakeResponse;
import com.carsite.model.TrimResponse;
import com.carsite.model.TrimVehicleData;
import com.carsite.repository.carRepo.MakeModelRepository;
import com.carsite.repository.carRepo.MakeRepository;
import com.carsite.repository.carRepo.TrimRepository;
import com.carsite.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class CarController {

    @Autowired
    VehicleService vehicleService;
    @Autowired
    MakeRepository makeRepository;
    @Autowired
    MakeModelRepository makeModelRepository;
    @Autowired
    TrimRepository trimRepository;

    @GetMapping("makes")
    public MakeResponse getMake() {
        return vehicleService.getMake();
    }

    @GetMapping("make/{name}")
    public MakeResponse getMakeByName(@PathVariable String name) {
        return vehicleService.getMakeByName(name);
    }

    @GetMapping("models/{id}")
    public MakeModelResponse getModelById(@PathVariable Long id) {
        return vehicleService.getModelById(id);
    }

    @GetMapping("trimbycarmodel/{id}/{model}")
    public TrimResponse getTrimsByCarModel(@PathVariable String model, @PathVariable Long id) {
        return vehicleService.getTrimsByCarModel(model, id);
    }

    @GetMapping("trimbycar/{id}")
    public TrimResponse getTrimsByCarId(@PathVariable Long id) {
        return vehicleService.getTrimsByCarId(id);
    }

    @GetMapping("trims/{id}")
    public TrimVehicleData getTrimsById(@PathVariable Long id) {
        return vehicleService.getTrimsById(id);
    }



    @GetMapping("save")
    public  String saveToDb(){
        vehicleService.saveResponseToDatabase();
        return "Saved";
    }
    @GetMapping("makesdb")
    public List<Make> getMakeDb() {
        return makeRepository.findAll();
    }
    @GetMapping("makedb/{name}")
    public Make getMakeDbByName(@PathVariable String name) {
        return makeRepository.findByName(name);
    }
    @GetMapping("modelsdb")
    public List<MakeModel> getModelsDb() {
        return makeModelRepository.findAll();
    }
    @GetMapping("modelsdb/{id}")
    public Optional<MakeModel> getModelDbById(@PathVariable Long id) {
        return makeModelRepository.findById(id);
    }
    @GetMapping("trimdb")
    public List<Trim> getTrimDb() {
        return trimRepository.findAll();
    }
//    @GetMapping("models")
//    public ModelResponse getModel() {
//        RestTemplate restTemplate = new RestTemplate();
//        String url = "https://carapi.app/api/models?year=2020";
//        ResponseEntity<ModelResponse> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, ModelResponse.class);
//        return responseEntity.getBody();
//    }
    //    @GetMapping("trims")
//    public TrimResponse getTrims() {
//        RestTemplate restTemplate = new RestTemplate();
//        String url = "https://carapi.app/api/trims/?year=2020";
//        ResponseEntity<TrimResponse> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, TrimResponse.class);
//
//        return responseEntity.getBody();
//    }


}
