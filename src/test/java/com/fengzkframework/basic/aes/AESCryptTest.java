package com.fengzkframework.basic.aes;

import org.junit.Test;

import static org.junit.Assert.*;

public class AESCryptTest {

    @Test
    public void encryptAES() {
        //{"phone":"15010429415","mallcode":"512","openid":"123456","membername":"冯兆奎","wxtjrphone":"18210225665" }
        //"{'phone':'15010429416','mallcode':'512','membername':'冯兆奎','openid':'123456'}"
        //{"openid":"Id3EDUoQ4bnjaLNS5XVHOxf3yUwAeGs39lwiMtvfUqw7R9sZ","start":0,"type":1,"end":4}
        String str= AESCrypt.encryptAES("{'type':'1','openid':'140011','start':'0','end':'1'}","FHhPatHL4DF8link");
        System.out.print("加密后"+str); //7pgfZHVVv4g+CN7swTFXkC8p9pFc/jFC53Pdpc9qqRY=
    }

    @Test
    public void decryptAES() {

       String str= AESCrypt.decryptAES("G3C88l5ag1Tm4oQLgsNs38FTmAE//Q1A5FFJSlgz1/bCY2XHrwlCVybZyNx14lmFOr5pP3m54YvLm+9VwPfMM3s1/x25sOPgMSXf4QZu7RA=","FHhPatHL4DF8link");
       System.out.print("解密后"+str);
    }
}