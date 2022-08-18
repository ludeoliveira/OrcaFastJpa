package com.orcafastjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orcafastjpa.entidades.Usuario;


public interface UsuarioRepository extends JpaRepository <Usuario, Long> {

}
