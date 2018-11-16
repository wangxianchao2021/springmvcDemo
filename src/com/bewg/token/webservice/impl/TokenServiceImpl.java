package com.bewg.token.webservice.impl;

import com.bewg.token.dao.TokenJobDao;
import com.bewg.token.entity.DDParam;
import com.bewg.token.webservice.TokenService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.WebService;

/**
 * Created by jh on 2017/7/21.
 */
@WebService
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenJobDao dao;

    @Override
    public DDParam getDDTocken() {
        DDParam tocken = dao.getParamService("TOKEN");
        return tocken;
    }

    @Override
    public DDParam getDDTicket() {
        DDParam ticket = dao.getParamService("jsapi_ticket");
        return ticket;
    }

    @Override
    public DDParam getWXTocken() {
        DDParam tocken = dao.getParamService("WxTocken");
        return tocken;
    }
}
