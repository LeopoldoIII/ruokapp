package com.ruokapp.session;

import androidx.test.InstrumentationRegistry;

import com.ruokapp.core.Session;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

public class SessionTest {

    private Session session =  Session.getInstance(InstrumentationRegistry.getTargetContext());

    @After
    public void tearDown(){
        session.closeSession();
    }

    @Test
    public void verifyCreatedSession(){
        String user = "UserTest";
        String pass = "UserPass";

        session.createSession(user,pass);
        Assert.assertTrue(session.getUserSession()!=null);
        Assert.assertEquals(user,session.getUserSession()[0]);
        Assert.assertEquals(pass,session.getUserSession()[1]);
    }

    @Test
    public void verifyUncreatedSession(){
        session.createSession(null,null);
        Assert.assertEquals(null,session.getUserSession());
    }

    @Test
    public void verifySessionClosed(){
        String user = "UserTest";
        String pass = "UserPass";

        session.createSession(user,pass);
        session.closeSession();
        Assert.assertEquals(null,Session
                .getSession());
    }
}
