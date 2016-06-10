/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jgit.newpackage;

/**
 *
 * @author jairanderson
 */
public class FactoryGithub implements AbstractFactory {

    @Override
    public GitHub createGitHub() {
        return new GitHub();
    }

    @Override
    public SVN createSVN() {
        return new SVN();
    }

}
