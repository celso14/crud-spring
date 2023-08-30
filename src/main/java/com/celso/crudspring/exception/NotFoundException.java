package com.celso.crudspring.exception;

public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NotFoundException(Long id) {
       super("Registro não encontrado com id:" + id);
    }
}
