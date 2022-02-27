package ch16;

import java.io.Serializable;
import java.net.*;
class Chat implements Serializable {
    transient String currentID;
    
    String userName;

    // more code
}