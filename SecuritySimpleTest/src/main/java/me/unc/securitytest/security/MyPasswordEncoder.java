package me.unc.securitytest.security;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 自定义的密码编辑器
 */
public class MyPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(charSequence.toString());
    }
}
