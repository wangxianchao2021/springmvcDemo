package com.bewg.token.mapper;

import com.bewg.token.entity.DDParam;
import com.bewg.token.entity.TokenParam;

public interface TokenJobMapper {

	public TokenParam getParam(String param);

	public DDParam getParamService(String param);

	public void updateToken(TokenParam param);
}
