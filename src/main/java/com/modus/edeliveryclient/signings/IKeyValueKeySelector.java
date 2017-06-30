/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modus.edeliveryclient.signings;

import javax.xml.crypto.AlgorithmMethod;
import javax.xml.crypto.KeySelector;
import javax.xml.crypto.KeySelectorException;
import javax.xml.crypto.KeySelectorResult;
import javax.xml.crypto.XMLCryptoContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;

/**
 *
 * @author Pantelispanka
 */
public interface IKeyValueKeySelector {
    
    
    public KeySelectorResult select(KeyInfo keyInfo,
                KeySelector.Purpose purpose,
                AlgorithmMethod method,
                XMLCryptoContext context)
                throws KeySelectorException;
    
    
}
