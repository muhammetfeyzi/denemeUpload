package com.reading.getirreading.base.api.v1;

import com.reading.getirreading.base.domain.model.CustomResponseStatus;
import com.reading.getirreading.base.domain.model.GenericResponse;

public class BaseController {
    protected GenericResponse getGenericApiResponse(Object object){
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setData(object);
        genericResponse.setStatus(CustomResponseStatus.SUCCESS.getValue());

        return genericResponse;
    }
}