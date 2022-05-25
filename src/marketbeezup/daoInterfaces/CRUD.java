/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.daoInterfaces;

/**
 *
 * @author Manolo
 * @param <T>
 */
public interface CRUD<T> {

    public void registrar(T t) throws Exception;

    public void modificar(T t) throws Exception;

    public void eliminar(T t) throws Exception;
}
