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
public interface IRepository {

    public IRepository clone(String url);

    public IRepository checkoutBranch(String branch);

    public IRepository connectRepository(String jass2125, String string);
    
    public IRepository getRepository();

}
