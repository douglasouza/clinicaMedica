package br.com.clinicamed.security.handlers;

import br.com.clinicamed.security.utils.SegurancaUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AcessoNaoAutorizadoHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        SegurancaUtils.enviarErro(response, exception, HttpServletResponse.SC_UNAUTHORIZED, "Acesso não autorizado.");
    }
}