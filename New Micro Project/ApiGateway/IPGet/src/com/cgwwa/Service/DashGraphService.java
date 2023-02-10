package com.cgwwa.Service;

import com.cgwwa.response.ApiResponse;
import com.cgwwa.response.DashGraphResponse;
import com.cgwwa.response.DashboardResponse;

public interface DashGraphService {

    ApiResponse<DashGraphResponse> dashGraphService(String unit);

    ApiResponse<DashGraphResponse> getIPaddress(String unit);
}