package com.bewg.token.manager;

import com.bewg.token.entity.DDParam;
import com.bewg.token.entity.TokenParam;

public interface TokenJobMng {

	public TokenParam getParam(String param);

	public DDParam getParamService(String param);

	public void updateToken(TokenParam param);
}
