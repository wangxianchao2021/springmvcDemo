package com.bewg.token.dao;

import com.bewg.token.entity.DDParam;
import com.bewg.token.entity.TokenParam;

public interface TokenJobDao {

	public TokenParam getParam(String param);

	public DDParam getParamService(String param);

	public void updateToken(TokenParam param);
}
