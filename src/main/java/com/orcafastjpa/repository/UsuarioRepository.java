package com.orcafastjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.orcafastjpa.entidades.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository <Usuario, Long> {

}
