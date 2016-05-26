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
public class Factory extends AbstractFactory {

    @Override
    public IRepository createGithub() {
        return new Github();
    }

    @Override
    public IRepository createSVN() {
        return new SVN();
    }
    
}