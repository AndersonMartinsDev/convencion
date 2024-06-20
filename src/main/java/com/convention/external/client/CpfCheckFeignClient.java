package com.convention.external.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;

@FeignClient(value = "cpfCheckClient",url = "${cpf.check.external.api}")
public interface CpfCheckFeignClient {
    @GetMapping(value = "/{cpf}")
    Object checkCPF(@RequestAttribute("cpf")String document);
}
