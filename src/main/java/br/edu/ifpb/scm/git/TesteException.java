/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.scm.git;

import java.io.IOException;

/**
 *
 * @author des02
 */
public class TesteException extends RuntimeException {

    public TesteException(String ocorreu_um_erro_tente_novamente_mais_tard, IOException e) {
    }

}
