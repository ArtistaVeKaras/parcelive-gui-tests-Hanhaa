package net.hanhaa.dev.helpers;

import org.junit.Assert;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@Component
public class RestConnector {

    public DeviceObject postRequest(Map<String, String> values) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("u", values.get("SN"));
        params.add("t", values.get("Tracking"));
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
        RestTemplate restTemplate = new RestTemplate();
        DeviceObject deviceObject = restTemplate.postForObject("http://dev.hanhaa.net/parcelive_track/v2/t", request, DeviceObject.class);
        Assert.assertNotNull(deviceObject.getI());
        return deviceObject;
    }

    public DeviceObject postRequestToSetAdminCookies() {
//        final Map<String, String> queryParam = new HashMap<>();
//        queryParam.put("username", "admin");
//        queryParam.put("password", "password");
//        given().
//                contentType("application/x-www-form-urlencoded; charset").
//                formParams(queryParam).
//                post("https://dev.hanhaa.net/auth/login").
//                then().
//                statusCode(200);
        Path resourceDirectory = Paths.get("src/test/resources/truststore.jks");
        String filePath = resourceDirectory.toAbsolutePath().toString();
        System.setProperty("javax.net.ssl.trustStore", filePath);
        System.setProperty("javax.net.ssl.trustStorePassword", "123123");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("username", "admin");
        params.add("password", "password");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
        RestTemplate restTemplate = new RestTemplate();
        DeviceObject deviceObject = restTemplate.postForObject("https://dev.hanhaa.net/auth/login", request, DeviceObject.class);
        return deviceObject;
    }

    public String doPOST(Map<String, String> values) {
        RestTemplate restTemplate = new RestTemplate();
        String result = "";

        LinkedMultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("u", values.get("SN"));
        params.add("t", values.get("Tracking"));

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://dev.hanhaa.net/parcelive_track/v2/t");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity =
                new HttpEntity<>(params, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.POST,
                requestEntity,
                String.class);

        HttpStatus statusCode = responseEntity.getStatusCode();
        if (statusCode == HttpStatus.OK) {
            result = responseEntity.getBody();
        }
        return result;
    }
}
