package com.cgwwa.controller;

import com.cgwwa.Service.DashGraphService;

import com.cgwwa.entity.DashGraphResponse;
import lombok.extern.slf4j.Slf4j;


@CrossOrigin
@RequestMapping("/getDash")
@Slf4j
@RestController
public class DashGraphController {

    @Autowired
    private DashGraphService dashGraphService;

    @RequestMapping("/Graph/{unit}")
    public ResponseEntity<ApiResponse<DashGraphResponse>> getDashGraphData(@PathVariable(value = "unit")String unit){
        return new ResponseEntity<>(dashGraphService.dashGraphService(unit), HttpStatus.OK);
    }


    @RequestMapping("/getIP/{unit}")
    public ResponseEntity<ApiResponse<DashGraphResponse>> getIP(@PathVariable(value = "unit")String unit){
        return new ResponseEntity<>(dashGraphService.getIPaddress(unit), HttpStatus.OK);
    }
}