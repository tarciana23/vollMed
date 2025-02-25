package com.br.alura.vollMed.infra.security;

import com.br.alura.vollMed.domain.usuario.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jdk.swing.interop.SwingInterOpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component//É utilizada para que o spring carregue uma classe/componente genérico
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var tokenJWT = recuperarToken(request);

        if(tokenJWT != null){
            var subject = tokenService.getSubject(tokenJWT);
            var usuario = usuarioRepository.findByLogin(subject);

            //UsernamePasswordAuthenticationToken é uma classe do spring como se fosse um dto de um usuário logado
            var authentication = new UsernamePasswordAuthenticationToken(usuario,null,usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        //FilterChain representa a cadeia de filtros que existem na aplicação
        filterChain.doFilter(request,response);//para ele encaminhar para os próximos filtros da aplicação
    }

    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if(authorizationHeader != null) {
            //para retornar apenas o token sem a palavra "Bearer"
            return authorizationHeader.replace("Bearer ", "").trim();
        }

        return null;
    }
}
