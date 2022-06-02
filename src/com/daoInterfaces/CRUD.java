/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.daoInterfaces;

import java.sql.SQLException;
/**
 *
 * @author Manolo
 * @param <T>
 */
public interface CRUD<T> {

    public void registrar(T t) throws SQLException;

    public void modificar(T t) throws SQLException;

    public void eliminar(T t) throws SQLException;
}
