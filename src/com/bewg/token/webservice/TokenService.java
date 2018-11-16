package com.bewg.token.webservice;

import com.bewg.token.entity.DDParam;

import javax.jws.WebService;

/**
 * Created by jh on 2017/7/21.
 */
@WebService
public interface TokenService {
   public DDParam getDDTocken();
   public DDParam getDDTicket();
   public DDParam getWXTocken();
}
