/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acq;

/**
 *
 * @author Markb
 */
public interface IResponse {


    /**
     *
     * @return boolean value indicates succes if the result
     */
    public boolean isSuccessful();

    /**
     *
     * @return The value of the response messages
     */
    public String getMessage();
}
