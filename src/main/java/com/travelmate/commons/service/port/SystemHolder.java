package com.travelmate.commons.service.port;

// 테스트 환경에서 시간을 쉽게 제어하거나 모킹할 수 있도록 하는 용도로 사용
public interface SystemHolder {
    long currentTimeMillis();
}
