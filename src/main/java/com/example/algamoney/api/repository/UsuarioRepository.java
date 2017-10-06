package com.example.algamoney.api.repository;

import com.example.algamoney.api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by victor on 02/10/2017.
 */
public interface UsuarioRepository extends JpaRepository<Usuario,Long>{

    Optional<Usuario> findByEmail(String email);
}
