package com.android_16.erso.onearmedbanditv1;

import android.app.Application;
/**
 * Created by erso on 11-02-2016.
 */
public class BanditApplication extends Application{
    private int bet;

    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }
}
