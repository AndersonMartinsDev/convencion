package com.convention.domain.services.impl;

import com.convention.domain.entities.enums.StatusDocumentsEnum;
import com.convention.domain.exceptions.ConvencionException;
import com.convention.external.client.CpfCheckFeignClient;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import static com.convention.domain.exceptions.ConvenctionTextExecptions.ERRO_EXTERNAL_API;

public class CpfCheckServiceFutureImpl {

    private final CpfCheckFeignClient client;

    public CpfCheckServiceFutureImpl(CpfCheckFeignClient client) {
        this.client = client;
    }

    public CompletableFuture<Map<String, StatusDocumentsEnum>> verifyMember(String document){
        return CompletableFuture
                .supplyAsync(()-> client.checkCPF(document))
                .handle((result,ex)->{
                    if(Objects.nonNull(ex)){
                        throw new ConvencionException(ERRO_EXTERNAL_API);
                    }
                    return result;
                });
    }
}
