package br.com.manage.person.utils;

import br.com.manage.person.dto.request.PhoneDTO;
import br.com.manage.person.enums.PhoneEnum;
import br.com.manage.person.phone.model.Phone;

public class PhoneUtils {

    private static final String PHONE_NUMBER = "11-99999-9999";
    private static final PhoneEnum PHONE_TYPE = PhoneEnum.MOBILE;
    private static final long PHONE_ID = 1L;

    public static PhoneDTO createFakeDTO() {
        return PhoneDTO.builder()
                .number(PHONE_NUMBER)
                .type(PHONE_TYPE)
                .build();
    }

    public static Phone createFakeEntity() {
        return Phone.builder()
                .id(PHONE_ID)
                .number(PHONE_NUMBER)
                .type(PHONE_TYPE)
                .build();
    }
}
