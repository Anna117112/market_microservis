package com.geekbrains.spring_data1.endpoints;


import com.geekbrains.spring_data1.services.ProductsService;
import com.geekbrains.spring_data1.soap.GetAllProductsRequest;
import com.geekbrains.spring_data1.soap.GetAllProductsResponse;
import com.geekbrains.spring_data1.soap.GetProductByNameRequest;
import com.geekbrains.spring_data1.soap.GetProductByNameResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
public class ProductEndpoint {
    private static final String NAMESPACE_URI = "http://www.geekbrains/.com/spring/data1/products";
    private final ProductsService productsService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getStudentByNameRequest")
    @ResponsePayload
    public GetProductByNameResponse getProductByName(@RequestPayload GetProductByNameRequest request) {
        GetProductByNameResponse response = new GetProductByNameResponse();
        response.setProduct(productsService.getByName(request.getTitle()));
        return response;
    }

    /*
        Пример запроса: POST http://localhost:8080/ws
        Header -> Content-Type: text/xml

        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:f="http://www.flamexander.com/spring/ws/students">
            <soapenv:Header/>
            <soapenv:Body>
                <f:getAllStudentsRequest/>
            </soapenv:Body>
        </soapenv:Envelope>
     */

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProductsRequest")
    @ResponsePayload
    public GetAllProductsResponse getAllProducts(@RequestPayload GetAllProductsRequest request) {
      GetAllProductsResponse response = new GetAllProductsResponse();
        productsService.getAllProducts().forEach(response.getProducts()::add);
        return response;
    }
}
