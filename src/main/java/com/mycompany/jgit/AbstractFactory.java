/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jgit;

/**
 *
 * @author Anderson Souza
 */
public abstract class AbstractFactory {

    public abstract IRepository createGithub();

    public abstract IRepository createSVN();

}