package com.kaarel.webshop.model;

import lombok.Data;

import java.util.List;

@Data
public class ParcelMachineResponse {
    List<OmnivaParcelMachine> omnivaParcelMachines;
    List<SmartpostParcelMachine> smartpostParcelMachines;
}
