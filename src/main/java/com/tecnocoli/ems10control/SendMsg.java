/*
 *  Copyright (c) 2023.
 *  http://www.tecnocoli.com.br
 *  All rights reserved
 */

package com.tecnocoli.ems10control;

import static com.tecnocoli.ems10control.App.logger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Vector;

/**
 * @author jcoli - Jeferson Coli - Tecnocoli
 * @brief SendMsg
 * @package com.tecnocoli.ems10control
 * @date 8/28/23 - 17:34
 */
public class SendMsg {

    private EMSDevice emsDevice;

    public String[] splitMesg(String s){
        String[] sMsg = s.split(",", 6);
//        for (String x : sMsg){
//            logger.info("split: "+x);
//        }
        return sMsg;
    }

    public String mergemMesg(String[] splitMsg){
        String msg = "";

        return msg;
    }

    public void sendMsg(String msg){

    }
}
