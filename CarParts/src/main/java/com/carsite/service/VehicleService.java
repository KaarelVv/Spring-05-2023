package com.carsite.service;

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
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@org.springframework.stereotype.Service
public class VehicleService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    MakeRepository makeRepository;
    @Autowired
    MakeModelRepository makeModelRepository;
    @Autowired
    TrimRepository trimRepository;

    String carApiUrl = "https://carapi.app/api";
    @Transactional
    public void saveResponseToDatabase() {
        String makeUrl = carApiUrl + "/makes?year=2020";
        String modelUrl = carApiUrl + "/models?year=2020";
        String trimUrl = carApiUrl + "/trims?year=2020";

        MakeResponse makeResponse = restTemplate.getForObject(makeUrl, MakeResponse.class);
        List<com.carsite.model.Make> makes = makeResponse.getData();

        MakeModelResponse modelResponse = restTemplate.getForObject(modelUrl, MakeModelResponse.class);
        List<com.carsite.model.MakeModel> models = modelResponse.getData();

        TrimResponse trimResponse = restTemplate.getForObject(trimUrl, TrimResponse.class);
        List<com.carsite.model.Trim> trims = trimResponse.getData();

        for (com.carsite.model.Make make : makes) {
            Make makeEntity = new Make();
            makeEntity.setId(make.getId());
            makeEntity.setName(make.getName());
            makeRepository.save(makeEntity);
        }
        for (com.carsite.model.MakeModel model : models) {
            MakeModel modelEntity = new MakeModel();
            modelEntity.setId(model.getId());
            modelEntity.setName(model.getName());

            makeModelRepository.save(modelEntity);
        }
        for (com.carsite.model.Trim trim : trims) {
            Trim trimEntity = new Trim();
            trimEntity.setId(trim.getId());
            trimEntity.setMake_model_id(trim.getMake_model_id());
            trimEntity.setYear(trim.getYear());
            trimEntity.setName(trim.getName());
            trimEntity.setDescription(trim.getDescription());
            trimEntity.setMsrp(trim.getMsrp());
            trimEntity.setInvoice(trim.getInvoice());
            trimEntity.setCreated(trim.getCreated());

            trimRepository.save(trimEntity);
        }
    }
    public MakeResponse getMake() {
        return this.fetchEntity("https://carapi.app/api/makes?year=2020", MakeResponse.class);
    }

    public MakeResponse getMakeByName(String name) {
        return this.fetchEntity("https://carapi.app/api/makes?make=" + name + "&year=2020", MakeResponse.class);
    }

    public MakeModelResponse getModelById(Long id) {
        return this.fetchEntity("https://carapi.app/api/models?year=2020&make_id=" + id, MakeModelResponse.class);
    }

    public TrimResponse getTrimsByCarModel(String model, Long id) {
        return this.fetchEntity("https://carapi.app/api/trims?year=2020&model=" + model + "&make_id=" + id, TrimResponse.class);
    }

    public TrimResponse getTrimsByCarId(Long id) {
        return this.fetchEntity("https://carapi.app/api/trims/?year=2020&make_id=" + id, TrimResponse.class);
    }

    public TrimVehicleData getTrimsById(Long id) {
        return this.fetchEntity("https://carapi.app/api/trims/" + id + "?year=2020", TrimVehicleData.class);
    }
    private <T> T fetchEntity(String url, Class<T> responseType) {
        ResponseEntity<T> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, responseType);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            Logger logger = LoggerFactory.getLogger(getClass());
            logger.info("Response: {}", responseEntity.getBody());
            return responseEntity.getBody();
        }
        return null;
    }


}

