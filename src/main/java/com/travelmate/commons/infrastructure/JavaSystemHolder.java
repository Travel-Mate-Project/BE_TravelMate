package com.travelmate.commons.infrastructure;

import com.travelmate.commons.service.port.SystemHolder;
import org.springframework.stereotype.Component;

// 테스트 환경에서 시간을 쉽게 제어하거나 모킹할 수 있도록 하는 용도로 사용
@Component
public class JavaSystemHolder implements SystemHolder {
    @Override
    public long currentTimeMillis() {
        return System.currentTimeMillis();}
}
