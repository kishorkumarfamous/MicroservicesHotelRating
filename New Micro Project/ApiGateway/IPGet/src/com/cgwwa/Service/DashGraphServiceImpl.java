package com.cgwwa.Service;

import com.cgwwa.config.DataBaseConnection;
import com.cgwwa.exception.SDDException;
import com.cgwwa.response.*;
import com.cgwwa.service.DashGraphService;
import com.cgwwa.utils.ResponseUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.OracleTypes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


@Service
@Slf4j
@AllArgsConstructor
public class DashGraphServiceImpl implements DashGraphService {
    @Override
    public ApiResponse<DashGraphResponse> dashGraphService(String unit) {
        DashGraphResponse mainRes = new DashGraphResponse();
        ArrayList<DashGraphAddResponse> addPaidRes = new ArrayList<DashGraphAddResponse>();
        ArrayList<DashGraphAddResponse> addUnPaidRes = new ArrayList<DashGraphAddResponse>();
        Connection connection =  DataBaseConnection.getConnection1();
        CallableStatement callableStatement = null;
        try {
            String groupAdd = "{call PR_GET_DASHBOARD_GRAPH(?,?,?,?)}";
            callableStatement = connection.prepareCall(groupAdd);
            callableStatement.setString(1, unit);

            callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
            callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
            callableStatement.registerOutParameter(4, OracleTypes.VARCHAR); // General details cursor
            callableStatement.executeUpdate();
            ResultSet rset1 = (ResultSet) callableStatement.getObject(2);
            while (rset1.next()) {
                DashGraphAddResponse paidStatus=new DashGraphAddResponse();
                paidStatus.setTotal(rset1.getString(1));
                paidStatus.setMonth(rset1.getString(2));
                paidStatus.setYear(rset1.getString(3));
                paidStatus.setMonthId(rset1.getString(4));
                //  paidStatus.setYearId(rset1.getString(5));
                addPaidRes.add(paidStatus);
            }
            ResultSet rset2 = (ResultSet) callableStatement.getObject(3);
            while (rset2.next()) {
                DashGraphAddResponse unpaidStatus=new DashGraphAddResponse();
                unpaidStatus.setTotal(rset2.getString(1));
                unpaidStatus.setMonth(rset2.getString(2));
                unpaidStatus.setYear(rset2.getString(3));
                unpaidStatus.setMonthId(rset2.getString(4));
                unpaidStatus.setYearId(rset2.getString(5));
                addUnPaidRes.add(unpaidStatus);
            }

            mainRes.setMsg(callableStatement.getString(4));;
            mainRes.setAddPaidResponse(addPaidRes);
            mainRes.setAddUnpaidResponse(addUnPaidRes);
        } catch (Exception e) {
            e.printStackTrace();
            mainRes.setMsg("Failed");
            return ResponseUtils.customFailureResponse(new TypeReference<DashGraphResponse>() {
            }, HttpStatus.REQUEST_TIMEOUT.getReasonPhrase(),HttpStatus.REQUEST_TIMEOUT.value());
        }finally {
            if (connection != null) {
                try {
                    connection.close();
                }  catch (SQLException e) {
                    throw new SDDException(HttpStatus.BAD_REQUEST.value(), "Connection not close");
                }
            }
        }
        return ResponseUtils.createSuccessResponse(mainRes, new TypeReference<DashGraphResponse>() {
        });
    }


    @Override
    public ApiResponse<DashGraphResponse> getIPaddress(String unit) {
        DashGraphResponse mainRes = new DashGraphResponse();
        try {
//            InetAddress localhost = InetAddress.getLocalHost();
//            System.out.println("System IP Address : " + (localhost.getHostAddress()).trim());
//            mainRes.setMsg("success");
//            mainRes.setIpAddr((localhost.getHostAddress()).trim());

            URL url_name = new URL("https://google.com");
            //URL url_name = new URL("https://icg.net.in/yatra-adv/");

            BufferedReader sc =
                    new BufferedReader(new InputStreamReader(url_name.openStream()));

            // reads system IPAddress
            // String  systemipaddress = sc.readLine().trim();

            mainRes.setMsg("success");
            mainRes.setIpAddr((sc.readLine().trim()));

        } catch (Exception e) {
            e.printStackTrace();
            mainRes.setMsg("Failed");
            return ResponseUtils.customFailureResponse(new TypeReference<DashGraphResponse>() {
            }, HttpStatus.REQUEST_TIMEOUT.getReasonPhrase(),HttpStatus.REQUEST_TIMEOUT.value());
        }
        return ResponseUtils.createSuccessResponse(mainRes, new TypeReference<DashGraphResponse>() {
        });
    }
}