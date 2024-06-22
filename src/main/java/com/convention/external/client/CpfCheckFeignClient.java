package com.convention.external.client;

import com.convention.domain.entities.enums.StatusDocumentsEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "cpfCheckClient",url = "${cpf.check.external.api}")
public interface CpfCheckFeignClient {
    @GetMapping(value="{cpf}")
    Map<String, StatusDocumentsEnum> checkCPF(@RequestParam("cpf")String document);
}
