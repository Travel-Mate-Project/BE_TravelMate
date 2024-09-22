package com.travelmate.commons.security.encryption;

public interface Crypto {
    public String decrypt(String cipher);
    public String encrypt(String plain);
}
